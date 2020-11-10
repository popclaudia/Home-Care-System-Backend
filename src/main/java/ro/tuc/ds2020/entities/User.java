package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="utilizator")
@DiscriminatorColumn(name="DTYPE", discriminatorType = DiscriminatorType.STRING)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer ID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate", nullable = false)
    private Date birth_date;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name="DTYPE", nullable=false, updatable=false, insertable=false)
    private String userType;

    //    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY,  cascade = CascadeType.ALL, orphanRemoval = true)
    // @PrimaryKeyJoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserAccount user_account;

    public User(){
    }

    public User(String name, Date birth_date, String gender, String address) {
        this.name = name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.address = address;
    }

    @Transient
    public String getDecriminatorValue() {
        return "Super";
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserType(){
        return this.userType;
    }

    public UserAccount getUser_account() {
        return user_account;
    }

    public void setUser_account(UserAccount user_account) {
        this.user_account = user_account;
    }
}
