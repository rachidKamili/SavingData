package me.kamili.rachid.savingdata.model;

/**
 * Created by Admin on 3/28/2018.
 */

public class Person {
    private String firstName;
    private String lastName;
    private String gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Person(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    @Override
    public String toString() {

        return this.getFirstName() + " " + this.getLastName() + " : " + this.getGender();
    }
}

