package Adressbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        setupLogging();
        AddressBookManager start = new AddressBookManager();
        start.startMyAddressBook();


    }

    private static void setupLogging() {
        String loggingFilePath = "C:\\Users\\Rand Botani\\IdeaProjects\\AddressBookDemo\\src\\logging.properties";
        try (FileInputStream fileInputStream = new FileInputStream(loggingFilePath)) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load log properties.", e);
        }
    }
}
