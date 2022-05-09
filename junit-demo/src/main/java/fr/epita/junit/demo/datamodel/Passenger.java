package fr.epita.junit.demo.datamodel;

public class Passenger {
    private String name;
    private int age;
    private int pclass;

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

    private boolean survived;
    private int gender;

    public Passenger(String name, int age, int pclass, boolean survived, int gender) {
        this.name = name;
        this.age = age;
        this.pclass = pclass;
        this.survived = survived;
        this.gender = gender;
    }
}
