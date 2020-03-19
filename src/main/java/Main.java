import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class Main {

    public static void main(String args[]) {
        System.out.println("HELLO WORLD");
        EmailClient.sendAsHtml("leandro.costa@i2s.pt",
                "Test email",
                "BOM DIA,\nEste é um email teste.  n\nAtenciosamente,\nLeandro Costa");
    }
}
