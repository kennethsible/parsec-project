package parsecsei;


import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
 
 
public class Send
{
    
    public static boolean sendMail(String from, String password, String message, String to[]){
  
    String host= "smtp.gmail.com";
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable","true");
    props.put("mail.smtp.host",host);
    props.put("mail.smtp.user",from);
    props.put("mail.smtp.password", password);
    props.put("mail.smtp.port",587);
    props.put("mail.smtp.auth","true");
    Session session = Session.getDefaultInstance(props,null);
    MimeMessage mimeMessage = new MimeMessage(session);
    
    
    try{
        mimeMessage.setFrom(new InternetAddress(from));
        //now get the address of the recipents
        InternetAddress[] toAddress= new InternetAddress[to.length];
        for(int i=0; i<to.length; i++){
        toAddress[i]=new InternetAddress(to[i]);
        }
        //now add all the to address elemaents
        //mimeMessage
        for(int i=0; i<toAddress.length;i++){
        mimeMessage.addRecipient(RecipientType.TO, toAddress[i]);
        }
        //add subject
        mimeMessage.setSubject("SEI Reminder");
        //set message to mimeMessage
        mimeMessage.setText(message);
        Transport transport = session.getTransport("smtp");
        transport.connect(host,from,password);
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        return true;
        
    }catch(MessagingException me){me.printStackTrace();}
    return false;}
  
}
