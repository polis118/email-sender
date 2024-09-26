package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;


    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception
     * during opening,
     * the exception is not handled in the constructor.
     */
    public EmailSender(String host, int port) throws UnknownHostException, IOException {

        socket = new Socket(host, port);
        output = new PrintWriter(socket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println(input.readLine());
    }

    /*
     * Sends email from an email address to an email address with some subject and
     * text.
     * If the Socket throws an exception during sending, the exception is not
     * handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws IOException {
        // Mail EHLO
        output.println("EHLO " + from);
        System.out.println(input.readLine());

        // Mail FROM
        output.println("FROM:<" + from + ">");
        System.out.println(input.readLine());

        //Mail TO
        output.println("TO:<" + to + ">");
        System.out.println(input.readLine());

        // Data
        output.println("DATA");
        System.out.println(input.readLine());

        // Subject
        output.println("SUBJECT:<" + subject + ">");
        output.println();
        output.println(text);
        output.println("."); // mail must end with dot
        System.out.println(input.readLine());
    }

    /*
     * Sends QUIT and closes the socket
     */
    public void close() {
        output.println("QUIT");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
