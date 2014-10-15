/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import fullhouse.modules.HomeFrame;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;

/**
 *
 * @author Guido
 */
public class FullHouse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        cal.set(java.util.Calendar.MONTH, month-1);
        cal.set(java.util.Calendar.DAY_OF_MONTH, day);
        cal.set(java.util.Calendar.HOUR_OF_DAY, hour);
        cal.set(java.util.Calendar.MINUTE, minute);
        
        return new Timestamp(cal.getTimeInMillis());
    }
    
    public static String fromSqlDate(Timestamp date) {
        return new SimpleDateFormat("dd-MM-YYYY HH:mm").format(date);
    }
}
