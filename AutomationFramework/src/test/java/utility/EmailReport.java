package utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import readWriteTest.ReadExcel;


public class EmailReport {
	
	public static void sendEmail() throws IOException {
		
		ReadExcel rex =new ReadExcel();
		String filepath= System.getProperty("user.dir") + "\\";
		String filename= "TestCase.xlsx";
		Sheet recipient=rex.readExcel(filepath,filename,"Recipient");
		Row row;
		int noOfColumnsTo = recipient.getRow(1).getLastCellNum();
		int noOfColumnsCc = recipient.getRow(2).getLastCellNum();
		int noOfColumnsBcc = recipient.getRow(3).getLastCellNum();

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        Properties props = new Properties();  
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.debug", "true");  
        props.put("mail.smtp.port", 25);  
        props.put("mail.smtp.socketFactory.port", 25);  
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = null;
        
        String attachmentPath= System.getProperty("user.dir") + "\\"+"Results.html";

        mailSession = Session.getInstance(props,  
                new javax.mail.Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication("padaliyalokesh@gmail.com", "googlemails");  
            }  
        });  

        try {

            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Execution Summary Report" + " " + timeStamp );
            message.setFrom(new InternetAddress("padaliyalokesh@gmail.com"));
            String []to = new String[noOfColumnsTo];
            String []cc = new String[noOfColumnsCc];
            String []bcc= new String[noOfColumnsBcc];
            
            for(int i=0; i<noOfColumnsTo-1; i++)
            {
            	row = recipient.getRow(1);
            	Pattern regexEmail=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            	Matcher validEmail = regexEmail.matcher(row.getCell(i+1).toString());
            	if(validEmail.matches()==true)
            	{
            	to[i]= row.getCell(i+1).toString();
            	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
            	}
            }
            for(int j=0; j<noOfColumnsCc-1; j++)
            {
            	row = recipient.getRow(2);
            	Pattern regexEmail=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            	Matcher validEmail = regexEmail.matcher(row.getCell(j+1).toString());
            	if(validEmail.matches()==true)
            	{
            	cc[j]= row.getCell(j+1).toString();
            	message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[j]));
            	}
            }
            for(int k=0; k<noOfColumnsBcc-1; k++)
            {
            	row = recipient.getRow(3);
            	Pattern regexEmail=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            	Matcher validEmail = regexEmail.matcher(row.getCell(k+1).toString());
            	if(validEmail.matches()==true)
            	{
            	bcc[k]= row.getCell(k+1).toString();
            	message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[k]));
            	}
            }
            
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPartText = new MimeBodyPart();
            BodyPart messageBodyPartAttachment = new MimeBodyPart();
            
            String body = "Dear Recipient(s), PFA the execution report";
            messageBodyPartText.setText(body);
            multipart.addBodyPart(messageBodyPartText);
            
            DataSource source = new FileDataSource(attachmentPath);
            messageBodyPartAttachment.setDataHandler(new DataHandler(source));
            messageBodyPartAttachment.setFileName(attachmentPath);
            multipart.addBodyPart(messageBodyPartAttachment);
            
            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception exception) {
        	
        	System.out.println(exception);

        }
    }

	
}