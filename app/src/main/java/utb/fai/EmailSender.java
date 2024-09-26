package utb.fai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
        output.println("HELO");
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
        
        //System.out.println(input.readLine());

        // Mail FROM
        output.println("MAIL FROM:<" + from + ">");
        //System.out.println(input.readLine());

        //Mail TO
        output.println("RCPT TO:<" + to + ">");
        //System.out.println(input.readLine());

        // Data
        output.println("DATA");
        //System.out.println(input.readLine());

        // Subject
        output.println("Subject:" + subject + "\r\n\r\n" + text + "\r\n\r\n.\r\n\r\n");
    }

    /*
     * Sends QUIT and closes the socket
     */
    public void close() {
        output.println("QUIT");
        try {
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
