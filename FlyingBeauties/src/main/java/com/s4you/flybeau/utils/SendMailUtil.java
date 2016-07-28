package com.s4you.flybeau.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * SendMailUtil 
 * Date: 14/05/2016 
 * ThienMV
 * 
 * */
public class SendMailUtil {
	
	private final static String MAIL_ADDRESS = CommonUtils.readProperties(ConstantUtil.PROPERTY_MAIL_ADDRESS);
	private final static String MAIL_PASSWORD = CommonUtils.readProperties(ConstantUtil.PROPERTY_MAIL_PASSWORD);
	private final static String MAIL_SMTP_AUTH = CommonUtils.readProperties(ConstantUtil.PROPERTY_MAIL_SMTP_AUTH);
	private final static String MAIL_SMTP_STARTTLS = CommonUtils.readProperties(ConstantUtil.PROPERTY_MAIL_SMTP_STARTTLS);
	private final static String MAIL_SMTP_HOST = CommonUtils.readProperties(ConstantUtil.PROPERTY_MAIL_SMTP_HOST);
	private final static String MAIL_SMTP_PORT = CommonUtils.readProperties(ConstantUtil.PROPERTY_MAIL_SMTP_PORT);

	/**
	 * 
	 * @param receiptMail
	 * @param mailTitle
	 * @param mailContent
	 * @return RetCode
	 */
	@SuppressWarnings("finally")
	public static int sendMail(String receiptMail, String mailTitle, String mailContent) {
		
		int retCode = ConstantUtil.RETCODE_NORMAL;
		
		/* Get Properties From File properties */
		Properties prop = new Properties();
		prop.put(ConstantUtil.PROPERTY_MAIL_SMTP_AUTH, MAIL_SMTP_AUTH);
		prop.put(ConstantUtil.PROPERTY_MAIL_SMTP_STARTTLS, MAIL_SMTP_STARTTLS);
		prop.put(ConstantUtil.PROPERTY_MAIL_SMTP_HOST, MAIL_SMTP_HOST);
		prop.put(ConstantUtil.PROPERTY_MAIL_SMTP_PORT, MAIL_SMTP_PORT);
		
		try {
		
			/* Authentication */
			Session session = Session.getInstance(prop,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(MAIL_ADDRESS, MAIL_PASSWORD);
				}
			  });

			/* Send Mail */
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_ADDRESS));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receiptMail));
			message.setSubject(mailTitle);
			message.setText(mailContent);

			Transport.send(message);

		} catch (MessagingException e) {
			retCode = ConstantUtil.RETCODE_SENDMAIL_ERROR;
			throw new RuntimeException(e);
		} finally {
			return retCode;
		}
	}
}