package model;

import com.formdev.flatlaf.FlatDarkLaf;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class A {

    public static void main(String[] args) throws JRException, Exception {
        FlatDarkLaf.setup();

        String filepath = "src//reports//grn.jrxml";
        JasperReport report = JasperCompileManager.compileReport(filepath);
        HashMap parameters = new HashMap();

//        User u1 = new User(1, "che", "111");
//        User u2 = new User(2, "cum", "111");
//        User u3 = new User(3, "bam", "111");
////
//        Vector v = new Vector();
//        v.add(u1);
//        v.add(u2);
//        v.add(u3);

        Connection datasource=mysql.getConnection();
        JasperPrint jp;

        jp = JasperFillManager.fillReport(report, parameters, datasource);
        JasperViewer.viewReport(jp);
//JasperExportManager.exportReportToHtmlFile(jp, "src//reports//report.html");


    }
}
