package com.sdx.utils.email;

import java.util.Properties;

/**
 * 发送邮件需要使用的基本信息 author by wangfun http://www.5a520.cn 小说520
 */
public class MailSenderInfo {
	// 邮件接收者的地址
	private String toAddress;
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;
	// 邮件附件的文件名
	private String[] attachFileNames;

	/** */
	/**
	 * 获得邮件会话属性
	 */
	public Properties getProperties()
	{
		Properties p = new Properties();
		p.put("mail.smtp.host", SendEmail.SERVER_URL);
		p.put("mail.smtp.port", SendEmail.SERVER_PORT);
		p.put("mail.smtp.auth", "true");
		return p;
	}

	public String[] getAttachFileNames()
	{
		return attachFileNames;
	}

	public void setAttachFileNames(String[] fileNames)
	{
		this.attachFileNames = fileNames;
	}

	public String getToAddress()
	{
		return toAddress;
	}

	public void setToAddress(String toAddress)
	{
		this.toAddress = toAddress;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String textContent)
	{
		this.content = textContent;
	}
}