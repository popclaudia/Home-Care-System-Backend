package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.dtos.UserPrivateDTO;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserAccount;

public class UserBuilder {

    public static UserDTO toUserDTO(UserAccount user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(),user.getUser().getID());
    }

    public static UserPrivateDTO toUserPrivateDTO(UserAccount user) {
        return new UserPrivateDTO(user.getId(), user.getUsername(), user.getUser().getID());
    }

    public static UserAccount toEntity(UserDTO user, User u) {
        System.out.println(user.getUser()+"***********************************");
        return new UserAccount( user.getUsername(), user.getPassword(), u);
    }
    
    
}
