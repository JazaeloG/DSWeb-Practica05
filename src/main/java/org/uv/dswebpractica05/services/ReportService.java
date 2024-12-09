package org.uv.dswebpractica05.services;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.uv.dswebpractica05.models.Venta;

@Service
public class ReportService {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private DataSource dataSource;

    public byte[] generarReportePorIdVenta(Long idVenta) throws JRException, SQLException {
        Venta venta = ventaService.obtenerVentaPorId(idVenta);
        System.out.println("Datos de la venta: " + venta);

        if (venta == null) {
            throw new JRException("Venta no encontrada con ID: " + idVenta);
        }

        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/reportes/ReporteVentas.jrxml");
        
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idVenta", venta.getIdVenta());
        System.out.println("Parámetros del reporte: " + parametros);
        
        Connection connection = DataSourceUtils.getConnection(dataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, connection);
        
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}