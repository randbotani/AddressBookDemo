package Adressbook;

import java.io.Serializable;
import java.util.UUID;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String eMail;
    private String id;


    public Person(String idCode, String firstName, String lastName, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.id = idCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id : " + id + "\nfirstName : " + firstName +
                "\nlastName : " + lastName +
                "\neMail : " + eMail + "\n";
    }

}
