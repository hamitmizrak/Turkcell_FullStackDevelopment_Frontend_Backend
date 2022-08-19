package com.hamitmizrak.ui.mvc;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
@Log4j2
public class ContactController {

    //kullanıcı mail adresi
    private static final String FROM = "hamitmizrak@gmail.com";

    //Kime gönderilecek
    private static final String TO = "hrpmuhendislik44@gmail.com";

    //Konu: subject
    private static final String SUBJECT = "Konu Başlığı";


    //İçerik: ne hakkında
    private static final String CONTENT = "Merhabalar Site hakkında bilgi almak istiyorum";

    //Burayı Eklemeyi unutma
    @Autowired
    private static JavaMailSender mailSender;

    //http://localhost:8080/send/simplemail
    @GetMapping("/send/simplemail")
    @ResponseBody
    public static String simpleMailSendMethod() {
        //sadece yazı göndermek istediğimiz
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        //kim gönderiyor.
        simpleMailMessage.setFrom(FROM);

        //kime gönderiyor.
        simpleMailMessage.setTo(TO);

        //Konu başlığı
        simpleMailMessage.setSubject(SUBJECT);

        //BCC
        String[] setBccArray = {"bcc1@gmail.com", "bcc2@gmail.com", "bcc3@gmail.com"};
        simpleMailMessage.setBcc(setBccArray);

        //CC
        String[] setCcArray = {"cc1@gmail.com", "cc2@gmail.com", "cc3@gmail.com"};
        simpleMailMessage.setCc(setCcArray);

        //Birleştirme
        StringBuilder builder = new StringBuilder();
        builder.append(CONTENT).append("\n").append("<h4>Admin Bilgileri</h4>");
        String contentMail = builder.toString();
        simpleMailMessage.setText(contentMail);

        //Mail içeiği Gönderildi
        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage() + " " + e.getLocalizedMessage() + " " + e.hashCode());
        }
        return "Mailiniz Gönderildi (SimpleMailMessage)";
    }


    //http://localhost:8080/send/mimemail
    //Dikkat: Ek dosya pdf,txt,resim göndermek istediğimizde
    @GetMapping("/send/mimemail")
    @ResponseBody
    public String mimeMailSendMethod() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);
        String from = "hamitmizrak@gmail.com";
        String to = "hrpmuhendislik44@gmail.com";
        String content = "<b>Merhabalar</b>,<br><i>Please look at this nice picture:.</i>" + "<br><img src='cid:image001'/><br><b>Best Regards</b>";
        try {
            messageHelper.setSubject("Konu alanı");
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setText(content, true);
            //pdf txt resim göndermek
            FileSystemResource pdf = new FileSystemResource(new File("C:\\fileio\\document.pdf"));
            messageHelper.addAttachment("document.pdf", pdf);
            messageHelper.addAttachment("deneme.txt", new FileSystemResource(new File("C:\\fileio\\deneme.txt")));
            FileSystemResource resim = new FileSystemResource(new File("C:\\fileio\\document.pdf"));
            messageHelper.addAttachment("picture.png", resim);
            //messageHelper.addInline("image001", resim);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Gönderim Sırasında hata meydana geldi.";
        }
        mailSender.send(mimeMessage);
        return "Mail Gönderildi";
    }
}