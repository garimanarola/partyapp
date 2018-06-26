package com.party.giftzzle.service;


import com.party.giftzzle.config.Constants;
import com.party.giftzzle.domain.User;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;


import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Service for sending emails.
 * <p>
 * We use the @Async annotation to send emails asynchronously.
 */
@Service
public class MailService {

  private final Logger log = LoggerFactory.getLogger(MailService.class);

  private static final String USER = "user";

  private static final String BASE_URL = "baseUrl";

  @Value("${giftzzel.mail.from}")
  private String mailFrom;

  @Value("${giftzzel.mail.base-url}")
  private String mailBaseUrl;

  private final JavaMailSender javaMailSender;

  private final MessageSource messageSource;

  private final SpringTemplateEngine templateEngine;

  public MailService(JavaMailSender javaMailSender,
                     MessageSource messageSource, SpringTemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.messageSource = messageSource;
    this.templateEngine = templateEngine;
  }

  @Async
  public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
    log.debug("Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
        isMultipart, isHtml, to, subject, content);

    // Prepare message using a Spring helper
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    try {
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, CharEncoding.UTF_8);
      message.setTo(to);
      message.setFrom(mailFrom);
      message.setSubject(subject);
      message.setText(content, isHtml);
      javaMailSender.send(mimeMessage);
      log.debug("Sent email to User '{}'", to);
    } catch (Exception e) {
      if (log.isDebugEnabled()) {
        log.warn("Email could not be sent to user '{}'", to, e);
      } else {
        log.warn("Email could not be sent to user '{}': {}", to, e.getMessage());
      }
    }
  }

  @Async
  public void sendEmailFromTemplate(User user, String templateName, String titleKey) {
    Locale locale = Locale.forLanguageTag(Constants.DEFAULT_LANGUAGE);
    Context context = new Context(locale);
    context.setVariable(USER, user);
    context.setVariable(BASE_URL, mailBaseUrl);
    String content = templateEngine.process(templateName, context);
    String subject = messageSource.getMessage(titleKey, null, locale);
    sendEmail(user.getEmail(), subject, content, false, true);

  }

  @Async
  public void sendActivationEmail(User user) {
    log.debug("Sending activation email to '{}'", user.getEmail());
    sendEmailFromTemplate(user, "activationEmail", "email.activation.title");
  }

  @Async
  public void sendCreationEmail(User user) {
    log.debug("Sending creation email to '{}'", user.getEmail());
    sendEmailFromTemplate(user, "creationEmail", "email.activation.title");
  }

  @Async
  public void sendPasswordResetMail(User user) {
    log.debug("Sending password reset email to '{}'", user.getEmail());
    sendEmailFromTemplate(user, "passwordResetEmail", "email.reset.title");
  }

  @Async
  public void sendPasswordResetOtp(User user) {
    log.debug("Sending password reset otp to email '{}'", user.getEmail());
    sendEmailFromTemplate(user, "passwordResetOtp", "email.reset.title");
  }

  @Async
  public void sendAccountUpdationEmail(User user) {
    log.debug("Sending creation email to '{}'", user.getEmail());
    sendEmailFromTemplate(user, "updateProfileEmail", "email.updation.title");
  }

}
