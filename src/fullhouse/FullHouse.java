/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import fullhouse.modules.HomeFrame;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Guido
 */
public class FullHouse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } 
        catch (Exception e) { }
        
        HomeFrame frame = new HomeFrame();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.revalidate();

        frame.mainPanel = Panel.changeView(frame, frame.mainPanel, new fullhouse.modules.HomePanel());

        /*System.out.println("Punten op 500");
         for(int i = 5; i <= 20; i++)
         {
         new Rating(500, i*100);
         }
         System.out.println("------------------");
         System.out.println("Punten op 1000");
         for(int i = 5; i <= 20; i++)
         {
         new Rating(1000, i*100);
         }
         System.out.println("------------------");
         System.out.println("Punten op 2000");
         for(int i = 5; i <= 20; i++)
         {
         new Rating(2000, i*100);
         }*/
    }

    public static Timestamp toSqlDate(int year, int month, int day, int hour, int minute) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.clear();

        cal.set(java.util.Calendar.YEAR, year);
        cal.set(java.util.Calendar.MONTH, month - 1);
        cal.set(java.util.Calendar.DAY_OF_MONTH, day);
        cal.set(java.util.Calendar.HOUR_OF_DAY, hour);
        cal.set(java.util.Calendar.MINUTE, minute);

        return new Timestamp(cal.getTimeInMillis());
    }

    public static String textToSqlDate(String dateString) {
        String day = dateString.substring(0, 2);
        String month = dateString.substring(3, 5);
        String year = dateString.substring(6, 10);
        return year + "-" + month + "-" + day;
    }
    
    public static String textToSqlDateTime(String dateString) {
        String day = dateString.substring(0, 2);
        String month = dateString.substring(3, 5);
        String year = dateString.substring(6, 10);
        String hours = dateString.substring(11, 13);
        String minutes = dateString.substring(14, 16);
        return year + "-" + month + "-" + day + " " + hours + ":" + minutes;
    }

    public static String fromSqlDateTime(Timestamp date) {
        return new SimpleDateFormat("dd-MM-YYYY HH:mm").format(date);
    }

    public static String fromSqlDate(Date date) {
        return new SimpleDateFormat("dd-MM-YYYY").format(date);
    }
    
    public static String fromSqlDateTime(Timestamp date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static void deleteRowFromTable(JTable table) {
        ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
    }
    
    public static double money(double value) {
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
}
}
