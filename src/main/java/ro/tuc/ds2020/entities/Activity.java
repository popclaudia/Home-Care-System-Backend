package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    Integer Id;
    @Column(name = "patient_id", nullable = false)
    Integer patientId;
    @Column(name = "start_time", nullable = false)
    Date startTime;
    @Column(name = "end_time", nullable = false)
    Date endTime;
    @Column(name = "activity", nullable = false)
    String activity;

    public Activity(){

    }

    public Activity(Integer idPatient, Date startTime, Date endTime, String activity) {
        this.patientId = idPatient;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "patient_id: " + patientId +
                "\nactivity: " + activity +
                "\nstart: " + startTime +
                "\nend: " + endTime +
                "\n";
    }

}
