import java.util.Properties;
import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

public class EmailClient {
    private static final String senderEmail = "leandro.costa@i2s.pt";//change with your sender email
    private static final String senderPassword = "k8A5G1B019";//change with your sender password

    public static void sendAsHtml(String to, String title, String mensagemAEnviar) {
        try{
            System.out.println("Enviar email para " + to);
            System.out.println("A criar sessao");
            Session session = createSession();
            System.out.println("Sessao criada");
            System.out.println("Preparar mensagem");
            MimeMessage message = new MimeMessage(session);
            prepareEmailMessage(message, to, title, mensagemAEnviar);
            System.out.println("Mensagem "+ message.toString() + " preparada");
            System.out.println("A mandar mensagem");
            Transport.send(message);
            System.out.println("Done");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private static void prepareEmailMessage(MimeMessage message, String to, String title, String mensagemAEnviar) {
        try {
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(title);
            message.setText(mensagemAEnviar);
        } catch (MessagingException e) {
            System.out.println("ERRO A ENVIAR");
        }
    }

    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");//Outgoing server requires authentication
        props.put("mail.smtp.starttls.enable", "true");//TLS must be activated
        props.put("mail.smtp.host", "smtp-mail.outlook.com"); //Outgoing server (SMTP) - change it to your SMTP server
        props.put("mail.smtp.port", "587");//Outgoing port
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        return session;
    }
}