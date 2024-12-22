package in.forFresher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	private JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;

    @Autowired
	public MailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
		this.javaMailSender = javaMailSender;
		this.templateEngine = templateEngine;
	}

    /**
     * 
     * @param to mail address
     * @param subject of the mail address
     * @param text for messages users sends
     */
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}
	
	/**
	 * 
	 * @param toEmail
	 * @param name of the user
	 * @throws MessagingException
	 */
	 @Async
	    public void sendPasswordChangeConfirmationEmail(String toEmail, String name) throws MessagingException {
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

	        // Load the email template with the user’s details
	        Context context = new Context();
	        context.setVariable("name", name);

	        // Render the Thymeleaf template into a String
	        String htmlContent = templateEngine.process("emailTemplate/password-changed", context);

	        helper.setTo(toEmail);
	        helper.setSubject("Password Change Confirmation");
	        helper.setText(htmlContent, true); // true indicates HTML content

	        // Send email asynchronously
	        javaMailSender.send(mimeMessage);
	    }
	 /**
	  * 
	  * @param toEmail
	  * @param resetLink
	  * @throws MessagingException
	  */
	 @Async
	  public void sendPasswordResetEmail(String toEmail, String resetLink, String name) throws MessagingException {
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

	        // Set up Thymeleaf context with variables
	        Context context = new Context();
	        context.setVariable("resetLink", resetLink);
	        context.setVariable("name", name);

	        // Process the HTML email template
	        String htmlContent = templateEngine.process("emailTemplate/password-reset-request", context);

	        helper.setTo(toEmail);
	        helper.setSubject("Reset Your Password - forfreshers.in");
	        helper.setText(htmlContent, true); // true indicates the content is HTML

	        // Send email
	        javaMailSender.send(mimeMessage);
	    }
}
