/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import fullhouse.frames.HomeFrame;
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

        frame.mainPanel = Panel.changeView(frame, frame.mainPanel, new fullhouse.frames.HomePanel());
        
        for(int i = 5; i <= 20; i++)
        {
            for(int y = 5; y <= 20; y++)
            {
                //new Rating(i*100, y*100);
            }
        }
    }
}
