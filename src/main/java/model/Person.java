package model;

import javax.inject.Named;
import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.total", query = "select p FROM Person p")
})
public class Person {

    @GeneratedValue()
    @Id
    private Long id;

    @Column
    public String gender;

    @Column
    public String firstName;

    public Person(String gender, String firstName) {
        this.gender = gender;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
