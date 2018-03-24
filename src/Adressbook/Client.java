package Adressbook;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

public class Client {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    static ArrayList<Person> catalog = new ArrayList<>();

    public void getCentralCatalog(String request) {

        try {
            Socket socket = new Socket("localhost", 61616);
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);


            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println(request);
            writer.flush();
            for (String centralCatalog = reader.readLine(); centralCatalog != null; centralCatalog = reader.readLine()) {
                String[] temp = centralCatalog.trim().split("\\s+");
                catalog.add(new Person(temp[0], temp[1], temp[2], temp[3]));
            }
            reader.close();
            writer.close();

        } catch (Exception e) {
            System.out.println("we can't load centralCatalog because there is no connection with server");
            LOGGER.severe("we can't load centralCatalog because there is no connection with server");
        }


    }

}