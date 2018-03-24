package Adressbook;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class AddressBookManager {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public void startMyAddressBook() {
        System.out.println("Welcome!");
        LOGGER.info("Starting address book");
        AddressBook addressBook = new AddressBook();
        Client client = new Client();
        client.getCentralCatalog("getall\nexit");

        addressBook.personInfo = addressBook.file.readFromFile();
        if (addressBook.personInfo == null) {
            addressBook.personInfo = new ArrayList<>();
        }
        Scanner inData = new Scanner(System.in);
        addressBook.autoSaveData();

        while (true) {
            String[] order = inData.nextLine().trim().split("\\s+");

            switch (order[0]) {
                case "add":
                    if (order.length == 4) {
                        addressBook.add(order[1], order[2], order[3]);
                        LOGGER.fine("Running command add ");
                    } else addressBook.wrongNumberOfParameter();
                    break;
                case "list":
                    if (order.length == 1) {
                        addressBook.showList(addressBook.personInfo);
                        LOGGER.fine("Running command list ");
                    } else addressBook.wrongNumberOfParameter();
                    break;
                case "search":
                    if (order.length == 2) {
                        addressBook.search(addressBook.personInfo, order[1]);
                        LOGGER.fine("Running command search ");
                    } else addressBook.wrongNumberOfParameter();
                    break;
                case "delete":
                    if (order.length == 2) {
                        addressBook.deletePersonFromAddressBook(addressBook.personInfo, order[1]);
                        LOGGER.fine("Running command delete ");
                    } else addressBook.wrongNumberOfParameter();
                    break;
                case "help":
                    if (order.length == 1) {
                        addressBook.helpMe();
                        LOGGER.fine("Running command help");
                    } else addressBook.wrongNumberOfParameter();
                    break;
                case "quit":
                    if (order.length == 1) {
                        LOGGER.fine("Running command quit");
                        LOGGER.info("Stopping address book");
                        addressBook.file.saveToFile(addressBook.personInfo);
                        exitMyApp();
                    } else addressBook.wrongNumberOfParameter();
                    break;
                default:
                    System.err.println("wrong command : " + "'" + order[0] + "'");
                    LOGGER.fine("wrong command");
                    continue;

            }

        }

    }

    public void exitMyApp() {
        System.out.println("**********************************************");
        System.out.println("********* Good Bye, see you later ! **********");
        System.out.println("**********************************************");
        System.exit(0);
    }

}
