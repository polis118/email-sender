package utb.fai;

public class App {

    public static void main(String[] args) {
        // TODO: Implement input parameter processing

        if (args.length < 6) {
            System.out.println("Málo argumentů");
            System.out.println("java -jar app.jar <smtpServer> <port> <fromEmail> <toEmail> <subject> <body>");
            return;
        }

        String smtpServer = args[0];
        int port = Integer.parseInt(args[1]);
        String fromEmail = args[2];
        String toEmail = args[3];
        String subject = args[4];
        String body = args[5];
        
        try {
            EmailSender sender = new EmailSender(smtpServer, port);
            sender.send(fromEmail, toEmail, subject, body);
            sender.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
