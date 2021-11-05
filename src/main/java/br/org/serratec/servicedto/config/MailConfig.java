package br.org.serratec.servicedto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

    @Autowired
    private JavaMailSender javaMailSender; //instanciamos ele pra usar o SimpleMailMessage

    public void enviarEmail(String para, String assunto, String texto) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("mariana.hiath@estudante.firjan.senai.br");
        simpleMailMessage.setTo(para);
        simpleMailMessage.setSubject(assunto);
        simpleMailMessage.setText("Dados da inscrição do usuário" + "\n\nSerratec Residência - 2021\n" + texto);

        javaMailSender.send(simpleMailMessage);
    }
}
