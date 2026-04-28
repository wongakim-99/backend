package org.project.ttokttok.infrastructure.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.ttokttok.infrastructure.email.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AsyncEmailSender {

    private final JavaMailSender mailSender;

    @Value("${email.from.address}")
    private String fromAddress;

    @Value("${email.from.name}")
    private String fromName;

    @Value("${email.reply-to}")
    private String replyTo;

    @Async("emailTaskExecutor")
    public void send(EmailRequest emailRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromAddress, fromName);
            helper.setReplyTo(replyTo);
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getContent(), emailRequest.isHtml());

            mailSender.send(message);
            log.info("이메일 비동기 발송 성공: {}", emailRequest.getTo());

        } catch (MessagingException e) {
            log.error("이메일 메시지 생성 실패: {} - {}", emailRequest.getTo(), e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.error("이메일 인코딩 실패: {} - {}", emailRequest.getTo(), e.getMessage());
        } catch (MailException e) {
            log.error("이메일 발송 실패: {} - {}", emailRequest.getTo(), e.getMessage());
        }
    }
}
