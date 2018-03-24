package Adressbook;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;


public class File_InOut {
    private static final Logger LOGGER = Logger.getLogger(File_InOut.class.getName());


    public void saveToFile(ArrayList<Person> myList) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("addressbook.ser"));
            out.writeObject(myList);
            out.close();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    public ArrayList<Person> readFromFile() {
        ArrayList<Person> myList = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("addressbook.ser"));
            myList = (ArrayList<Person>) in.readObject();
            in.close();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        } catch (ClassNotFoundException e) {
            LOGGER.severe("File not found");
        }
        return myList;

    }
}
