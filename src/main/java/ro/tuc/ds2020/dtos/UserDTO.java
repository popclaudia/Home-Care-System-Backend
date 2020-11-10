package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserDTO extends RepresentationModel<UserDTO> {

    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    private Integer user;

    public UserDTO(){

    }

    public UserDTO(Integer id, String username, String password, Integer user) {
        this.user = user;
        this.password= password;
        this.username = username;
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(username, userDTO.username) &&
                Objects.equals(password, userDTO.password) &&
                Objects.equals(user, userDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, user);
    }
}
