package cn.itcast.shop.utils;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUitls {
	/**
	 * �����ʼ��ķ���
	 * @param to	:�ռ���
	 * @param code	:������
	 */
	public static void sendMail(String to,String code){
		/**
		 * 1.���һ��Session����.
		 * 2.����һ������ʼ��Ķ���Message.
		 * 3.�����ʼ�Transport
		 */
		// 1.������Ӷ���
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com", "123");
			}
			
		});
		// 2.�����ʼ�����:
		Message message = new MimeMessage(session);
		// ���÷�����:
		try {
			message.setFrom(new InternetAddress("service@shop.com"));
			// �����ռ���:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// ���� CC   ����BCC
			// ���ñ���
			message.setSubject("来自丰丰商城官方激活邮件");
			// �����ʼ�����:
			message.setContent("<h1>丰丰商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://localhost:8080/shop/user_active.action?code="+code+"'>http://localhost:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			// 3.�����ʼ�:
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	//测试
	/*public static void main(String[] args) {
		sendMail("yc@shop.com","11111111111111");
	}*/
}
