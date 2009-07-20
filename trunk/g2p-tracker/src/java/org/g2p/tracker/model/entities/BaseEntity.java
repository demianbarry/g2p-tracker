/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.g2p.tracker.model.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Column;
import javax.persistence.Version;

/**
 *
 * @author Administrador
 */
@org.hibernate.annotations.Entity(optimisticLock = org.hibernate.annotations.OptimisticLockType.ALL,
dynamicUpdate = true)
public abstract class BaseEntity {

    public abstract Object getPK();
    
    @Version
    @Column(name = "OBJ_VERSION")
    protected Timestamp version;

    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }
    
    public static void enviarEmail(String contenido, String asunto, String emailRemitente, String contraseniaRemitente, String[] emailDestinatarios) throws MessagingException {

        Properties conf = new Properties();

        // nombre del host de correo
        conf.put("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        conf.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        conf.setProperty("mail.smtp.port", "587");

        // Nombre del usuario
        conf.setProperty("mail.smtp.user", emailRemitente);

        // Si requiere o no usuario y password para conectarse.
        conf.setProperty("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(conf);
        Message mensaje = new MimeMessage(sesion);

        try {
            InternetAddress de = new InternetAddress(emailRemitente);

            InternetAddress a[] = new InternetAddress[emailDestinatarios.length];

            String userEmail = null;

            for (int i = 0; i < a.length;i++){
                userEmail = emailDestinatarios[i];
                if (userEmail != null && userEmail.length() > 0){
                    a[i] = new InternetAddress(userEmail);
                }
            }

            mensaje.setFrom(de);
            mensaje.setRecipients(Message.RecipientType.TO, a);
            mensaje.setSubject(asunto);
            mensaje.setContent(contenido, "text/plain");
            mensaje.setSentDate(new Date());

            Transport t = sesion.getTransport("smtp");
            t.connect(emailRemitente, contraseniaRemitente);
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();


        } catch (AddressException ex) {
            System.out.println("############### La direccion de email esta mal formada ###############");
        }
    }
}
