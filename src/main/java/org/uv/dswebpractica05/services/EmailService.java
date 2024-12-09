package org.uv.dswebpractica05.services;

import java.sql.SQLException;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ReportService reporteService;

    public void enviarReportePorIdVenta(String to, Long idVenta) throws MessagingException, JRException, SQLException {
        try {
            byte[] pdfData = reporteService.generarReportePorIdVenta(idVenta);
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("jazaelgalindo.contact@gmail.com");
            helper.setTo(to);
            helper.setSubject("Reporte de Venta");
            helper.setText("Adjunto se encuentra el reporte de la venta con ID: " + idVenta);

            ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfData, "application/pdf");
            helper.addAttachment("reporte_venta_" + idVenta + ".pdf", dataSource);

            emailSender.send(message);
            System.out.println("Correo enviado exitosamente con el reporte en PDF.");
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
