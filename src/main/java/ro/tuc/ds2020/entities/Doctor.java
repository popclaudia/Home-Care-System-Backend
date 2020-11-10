package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@DiscriminatorValue("Doctor")
public class Doctor extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer ID;

    public Doctor() {
    }

    public Doctor(String name, Date birth_date, String gender, String address) {
        super(name, birth_date, gender, address);
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }
}
