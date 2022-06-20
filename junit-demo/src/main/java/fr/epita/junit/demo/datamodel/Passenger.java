package fr.epita.junit.demo.datamodel;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="PASSENGERS")
public class Passenger {

    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private int age;

    @Column(name = "PCLASS")
    private int pclass;

    @Column(name = "SURVIVED")
    private boolean survived;

    @Column(name = "GENDER")
    private int gender;

    protected Passenger(){
    }

    public Passenger(String name, int age, int pclass, boolean survived, int gender) {
        this.name = name;
        this.age = age;
        this.pclass = pclass;
        this.survived = survived;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPclass() {
        return pclass;
    }

    public void setPclass(int pclass) {
        this.pclass = pclass;
    }

    public boolean isSurvived() {
        return survived;
    }

    public void setSurvived(boolean survived) {
        this.survived = survived;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
