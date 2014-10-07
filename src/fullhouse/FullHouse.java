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
        JFrame frame = new HomeFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
