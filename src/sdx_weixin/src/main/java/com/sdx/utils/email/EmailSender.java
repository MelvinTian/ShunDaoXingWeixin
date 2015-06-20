package com.sdx.utils.email;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 发送软件大赛的相关邮件
 * 
 * @author 邢立庭
 * @date 2012-8-20-上午09:49:03
 * @description 邮件发送
 * 
 */
public class EmailSender {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm");

	/**
	 * 注册邮件发送
	 * 
	 * @return
	 */
//	public Long RegisterEmailSend(String loginName, String toEmail, String token)
//	{
//		StringBuffer content = new StringBuffer();
//		content.append("<div style='height: auto; min-height: 100px; font-size: 14px; padding: 0px; ");
//		content.append("font-family: 'lucida Grande', Verdana; margin-right: 170px;'>");
//		content.append("亲爱的<a href='mailto:" + toEmail + "'>" + toEmail + "</a>：<br />");
//		content.append("您的用户名是" + loginName + "<br /><br />");
//		content.append("感谢您注册蓝桥杯全国软件专业人才设计与创业大赛官方网站!<br />请点击下面的链接，完成您的邮箱验证：<br /><br />");
//		content.append("<a style='width: 100px;' href='" + token + "' target=_blank>" + token + "</a><br /><br /><br />");
//		content.append("提示：如果链接无法点击，请将它复制到浏览器的地址栏中打开。<br />");
//		content.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为确保帐号安全，链接7天内有效，若链接失效，");
//		content.append("<a href='" + BusinessDictionary.WEB_ACCESS_ADDRESS_URL + "' target='_blank'>请点击这里</a>。<br /><br /><br /><br />");
//		content.append("蓝桥杯全国软件专业人才设计与创业大赛官方网站<a href='http://www.lanqiao.org' target=_blank>www.lanqiao.org</a><br />");
//		content.append(DateTimeUtils.EmailDate());
//		content.append("<br /><font color='darkgray'>(本邮件由系统发送，请勿回复。)</font>");
//		content.append("</div>");
//
//		boolean htmlFlag = false;
//		htmlFlag = SendEmail.sendEmail(BusinessDictionary.EMAIL_MESSSAGE_SUBJECT, content.toString(), toEmail);
//
//		// 保存数据库
//		EmailLog emailLog = new EmailLog();
//		emailLog.setEmailReceiver(toEmail);
//		emailLog.setEmailSubject(BusinessDictionary.EMAIL_MESSSAGE_SUBJECT);
//		emailLog.setEmailContent(content.toString());
//		emailLog.setEmailToken(token);
//		emailLog.setEmailStatus(htmlFlag == true ? CommonVerify.VERIFY_SUBMIT_SUCCESS_STATUS : CommonVerify.VERIFY_SUBMIT_FAILED_STATUS);
//
//		Long i = Long.parseLong(Help.getService().getMapper(EmailLogMapper.class).insertSelective(emailLog) + "");
//		if (i > 0)
//		{
//			i = emailLog.getEmailId();
//		}
//
//		return i;
//	}

	/**
	 * 忘记密码发送邮件
	 * 
	 * @param toEmail
	 * @param passwd
	 * @return
	 */
	public boolean passwdSend(String title, String content, String toEmail)
	{
		StringBuffer emailContent = new StringBuffer();
		emailContent.append(content);
		emailContent.append("<br /><br /><br /><br /><br />");
		emailContent.append(sdf.format(new Date()));
		emailContent.append("<br /><font color='darkgray'>(本邮件由系统发送，请勿回复。)</font>");
		emailContent.append("</div>");
		String subject = "找回密码";
		return SendEmail.sendHtmlMail(SendEmail.genMailInfo(subject, content.toString(), toEmail));
	}
}