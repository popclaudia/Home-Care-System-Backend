package ro.tuc.ds2020.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ro.tuc.ds2020.entities.Activity;
import ro.tuc.ds2020.services.MedicationService;
import ro.tuc.ds2020.websocket.MySessionHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);
    WebSocketClient client = new StandardWebSocketClient();
    WebSocketStompClient stompClient = new WebSocketStompClient(client);
    StompSessionHandler sessionHandler = new MySessionHandler();
    String loggerServerQueueUrl= "ws://spring-assignment-1.herokuapp.com/notify";
    StompSession stompSession = null;
    Integer caregiver = null;

    public void connectToSocket(){
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        try {
            stompSession = stompClient.connect(loggerServerQueueUrl, sessionHandler).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void persistActivity(Activity activity) throws IOException {

        URL url = new URL ("https://spring-assignment-1.herokuapp.com/activity");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(activity);
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()){
            byte[] input = message.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            caregiver = Integer.parseInt(response.toString());
        }

    }

    public void checkRules(Activity activity, String message){

        String warning;
        Date start = activity.getStartTime();
        Date end = activity.getEndTime();
        long startMillis = start.getTime();
        long endMillis = end.getTime();

        if(activity.getActivity().equals("Sleeping") && endMillis-startMillis>25200000) {
            warning = "Patient with id "+ activity.getPatientId() + " slept for more than 7 hours!";
            stompSession.send("/app/notif/"+caregiver, warning);
        }

        if(activity.getActivity().equals("Leaving\t") && endMillis-startMillis>18000000 ) {
            warning = "Patient with id " + activity.getPatientId() + " was outside for more than 5 hours!";
            stompSession.send("/app/notif/"+caregiver, warning);
        }

        if((activity.getActivity().equals("Showering\t") || activity.getActivity().equals("Toileting\t") || activity.getActivity().equals("Grooming\t")) && endMillis-startMillis>1800000 ){
            warning = "Patient with id " + activity.getPatientId() + " spent more that 30 minutes in the bathroom!";
            stompSession.send("/app/notif/"+caregiver, warning);
        }

    }
}
