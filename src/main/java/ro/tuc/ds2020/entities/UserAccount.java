package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @Column(unique = true, updatable = false)
    private String username;

    @Column(updatable = false)
    private String password;

    @OneToOne( fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name = "account_user",
            joinColumns =
                    { @JoinColumn(name = "account_id", referencedColumnName = "id",  unique=true) },
            inverseJoinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "ID",  unique=true) })

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @MapsId
//    @JoinColumn(name = "id")
    private User user;


    public UserAccount() {

    }

    public UserAccount(String username, String parola, User user) {
        this.username = username;
        this.password = parola;
        this.user = user;
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

    public void setPassword(String parola) {
        this.password = parola;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
