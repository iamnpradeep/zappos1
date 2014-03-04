package zappos2;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import zappos2.ZapposChallenge2;
public class SendMail {
	
	 public void sendmail(String SavedEmailID, String ProductName, float Price) {

	        final String username = "zapposchallengejava@gmail.com";
	        final String password = "stoneridge";

	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("zapposchallengejava@gmail.com"));
	            message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(SavedEmailID));
	            message.setSubject("Price drop notification Zappos");
	            message.setText("Hi,"
	                + "\n\n Price of your favourite product:"+ProductName+" dropped by more than 20 percent of its original price"
	                +"\n\n It's current price is:"+Price);

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	        	System.out.println("Invalid email address given");
	            //throw new RuntimeException(e);
	        }
	    }

}
