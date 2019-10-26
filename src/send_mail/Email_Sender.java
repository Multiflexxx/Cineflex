package send_mail;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email_Sender
{
    /**
     * Send normal E-Mail with text only
     * @param session
     * @param senderMail
     * @param receiverEmail
     * @param subject
     * @param body
     */
    private static void transferMail(Session session, String senderMail, String receiverEmail, String subject, String body)
    {
        try
        {
            // Create new MimeMessage
            MimeMessage msg = new MimeMessage(session);

            // Set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            // Set sender mail
            msg.setFrom(new InternetAddress(senderMail, "Cineflex Ticket System"));

            // set reply to field
            msg.setReplyTo(InternetAddress.parse(senderMail, false));

            // set subject
            msg.setSubject(subject, "UTF-8");

            // set body text
            msg.setText(body, "UTF-8");

            // set sent date to current date and time
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail, false));

            // send mail
            Transport.send(msg);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Send E-mail with file attachement
     * @param session
     * @param senderMail
     * @param receiverEmail
     * @param subject
     * @param body
     * @param filePath
     */
    private static void transferMultipartMail(Session session, String senderMail, String receiverEmail, String subject, String body, String filePath)
    {
        try
        {
            // Create new MimeMessage
            MimeMessage msg = new MimeMessage(session);

            /*
            // Set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");*/

            // Set sender mail
            msg.setFrom(new InternetAddress(senderMail, "Cineflex Ticket System"));

            // Create MimeBodyParts
            MimeBodyPart mimeBodyPart1 = new MimeBodyPart();
            mimeBodyPart1.setText(body, "UTF-8"); // Set body text

            MimeBodyPart mimeBodyPart2 = new MimeBodyPart();
            mimeBodyPart2.attachFile(filePath); // Set attach file

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(mimeBodyPart1);
            multipart.addBodyPart(mimeBodyPart2);

            // Set subject
            msg.setSubject(subject, "UTF-8");

            // Set content to msg
            msg.setContent(multipart);

            // Set sent date to current date and time
            msg.setSentDate(new Date());

            // Set reply to field
            msg.setReplyTo(InternetAddress.parse(senderMail, false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail, false));

            // Send mail
            Transport.send(msg);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Get login data and send mail
     * @param recMail
     * @param subject
     * @param emailBody
     */
    public static void sendMail(String recMail, String subject, String emailBody)
    {
        // Get Session
        //Session session = getSession(recMail);

        // Create new properties
        Properties prop = new Properties();

        try
        {
            // read login data from file
            prop.load(new FileInputStream("/usr/local/tomcat/conf/emailLoginData.properties"));
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        // set login data
        String senderMail = prop.getProperty("username");
        String password = prop.getProperty("password");

        // set host and port for mail transfer
        String smtpHost = "smtp.strato.de";
        String smtpPort = "587";

        // set receiver mail address
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

        // Create new Session
        Session session = Session.getInstance(properties, authenticator);

        // Call transferMail method with args
        transferMail(session, session.getProperty("username"), recMail, subject, emailBody);
    }

    /**
     * Get login data and send mail
     * @param recMail
     * @param subject
     * @param emailBody
     * @param filePath
     */
    public static void sendMultipartMail(String recMail, String subject, String emailBody, String filePath)
    {
        // Get Session
        //Session session = getSession(recMail);

        // Create new properties
        Properties prop = new Properties();

        try
        {
            // read login data from file
            prop.load(new FileInputStream("/usr/local/tomcat/conf/emailLoginData.properties"));
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        // set login data
        String senderMail = prop.getProperty("username");
        String password = prop.getProperty("password");

        // set host and port for mail transfer
        String smtpHost = "smtp.strato.de";
        String smtpPort = "587";

        // set receiver mail address
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

        // Create new Session
        Session session = Session.getInstance(properties, authenticator);

        // Call transferMail method with args
        transferMultipartMail(session, session.getProperty("username"), recMail, subject, emailBody, filePath);
    }

    /**
     * Create session with all information that is needed
     * @param recMail
     * @return session
     */
/*    private static Session getSession(String recMail)
    {
        // Create new properties
        Properties prop = new Properties();

        try
        {
            // read login data from file
            prop.load(new FileInputStream("/usr/local/tomcat/conf/emailLoginData.properties"));
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        // set login data
        String senderMail = prop.getProperty("username");
        String password = prop.getProperty("password");

        // set host and port for mail transfer
        String smtpHost = "smtp.strato.de";
        String smtpPort = "587";

        // set receiver mail address
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

        // Create new Session
        Session session = Session.getInstance(properties, authenticator);

        return session;
    }*/
}
