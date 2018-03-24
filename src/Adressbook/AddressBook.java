package Adressbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import java.util.logging.Logger;


public class AddressBook {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    ArrayList<Person> personInfo = new ArrayList<Person>();
    File_InOut file = new File_InOut();
    Client client = new Client();

    public void add(String firstName, String lastName, String eMail) {
        personInfo.add(new Person(UUID.randomUUID().toString(), firstName, lastName, eMail));
        System.out.println("(" + firstName + " " + lastName + ") added to the address book");
    }

    public void showList(ArrayList<Person> personInfo) {

        boolean emptyList = true;

        for (int i = 0; i < personInfo.size(); i++) {
            System.out.printf("\nId : %s\nFirst name : %s\nLast name : %s\nEmail : %s\n", personInfo.get(i).getId(),
                    personInfo.get(i).getFirstName(), personInfo.get(i).getLastName(), personInfo.get(i).geteMail());
            emptyList = false;
        }
        for (int i = 0; i < client.catalog.size(); i++) {
            System.out.printf("\nId : %s\nFirst name : %s\nLast name : %s\nEmail : %s\n", client.catalog.get(i).getId(),
                    client.catalog.get(i).getFirstName(), client.catalog.get(i).getLastName(), client.catalog.get(i).geteMail());
            emptyList = false;
        }
        if (emptyList) System.out.println("Address book is empty");


    }

    public void deletePersonFromAddressBook(ArrayList<Person> personInfo, String idCode) {
        boolean delete = false;
        for (int i = 0; i < personInfo.size(); i++) {
            if (idCode.equalsIgnoreCase(personInfo.get(i).getId().toString())) {
                personInfo.remove(i);
                System.out.println("Person with ID: (" + idCode + ") deleted from the address book");
                delete = true;
            }
        }
        if (!delete)
            System.out.println("You don't have a permission to delete this contact");

    }

    public void search(ArrayList<Person> personInfo, String keyWord) {
        boolean found = false;
        try {

            for (int i = 0; i < personInfo.size(); i++) {
                if (keyWord.equalsIgnoreCase(personInfo.get(i).getFirstName().substring(0, keyWord.length()))
                        || keyWord.equalsIgnoreCase(personInfo.get(i).getLastName().substring(0, keyWord.length()))) {
                    System.out.printf("\nId : %s\nFirst name : %s\nLast name : %s\nEmail : %s\n", personInfo.get(i).getId(),
                            personInfo.get(i).getFirstName(), personInfo.get(i).getLastName(), personInfo.get(i).geteMail());
                    found = true;
                }
            }
            for (int i = 0; i < client.catalog.size(); i++) {
                if (keyWord.equalsIgnoreCase(client.catalog.get(i).getFirstName().substring(0, keyWord.length()))
                        || keyWord.equalsIgnoreCase(client.catalog.get(i).getLastName().substring(0, keyWord.length()))) {
                    System.out.printf("\nId : %s\nFirst name : %s\nLast name : %s\nEmail : %s\n", client.catalog.get(i).getId(),
                            client.catalog.get(i).getFirstName(), client.catalog.get(i).getLastName(), client.catalog.get(i).geteMail());
                    found = true;
                }
            }

        } catch (StringIndexOutOfBoundsException e) {
            LOGGER.severe(e.getMessage());
        }
        if (!found) {
            System.out.println("There is no contact start with (" + keyWord + ")");
        }

    }

    public void helpMe() {
        System.out.println("Enter\tadd\t firstName+lastName+eMailto add new person ");
        System.out.println("Enter\tlist\tto show the address book ");
        System.out.println("Enter\tsearch\tkeyWord to search person ");
        System.out.println("Enter\tdelete\tID to delete person from address book ");
        System.out.println("Enter\tquit\tto exit");
    }

    public synchronized void autoSaveData() {
        LOGGER.info(" Data auto saving to file is running now ");
        Thread newThread1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5_000);
                    file.saveToFile(personInfo);
                    LOGGER.info("Data saved to file");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        newThread1.start();
    }

    public void wrongNumberOfParameter() {
        System.err.println("wrong number of parameter");
        LOGGER.fine("wrong number of parameter");
    }
}
