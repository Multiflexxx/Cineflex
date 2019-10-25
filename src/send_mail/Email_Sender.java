package send_mail;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Email_Sender
{
    private static void transferMail(Session session, String senderMail, String receiverEmail, String subject, String body)
    {
        try
        {
            MimeMessage msg = new MimeMessage(session);

            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(senderMail, "Cineflex Ticket System"));

            msg.setReplyTo(InternetAddress.parse(senderMail, false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail, false));

            Transport.send(msg);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void sendMail(String recMail, String subject, String emailBody)
    {
        Properties prop = new Properties();

        try
        {
            prop.load(new FileInputStream("/usr/local/tomcat/conf/emailLoginData.properties"));
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        //TODO Add User Credentials
        String senderMail = prop.getProperty("username");
        String password = prop.getProperty("password");

        String smtpHost = "smtp.strato.de";
        String smtpPort = "587";

        String receiverMail = recMail;

        Properties properties = new Properties();

        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail, password);
            }
        };

        Session session = Session.getInstance(properties, authenticator);

        transferMail(session, senderMail, receiverMail, subject, emailBody);
    }
}
