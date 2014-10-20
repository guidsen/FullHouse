/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Liam
 */
public class Panel extends JPanel {
    private static Dimension size;
    private static Point point;
    private static JButton[] collectionButtons;
    private static JButton[] formButtons;
    
    public void initializeButtons(JButton[] collectionButtons, JButton[] formButtons)
    {
        // put the two arrays of buttons into the class variables
        this.collectionButtons = collectionButtons;
        this.formButtons = formButtons;
        
        // becaus the function will be called right after loading the panel we can assume it will be on the collection panel, which means we can disable the form buttons
        changeVisibility(formButtons, false);
    }
    
    public void toForm()
    {
        // disabling the collection buttons and enabling the form buttons
        changeVisibility(formButtons, true);
        changeVisibility(collectionButtons, false);
    }
    
    public void toCollection()
    {
        // disabling the form buttons and enabling the collection buttons
        changeVisibility(formButtons, false);
        changeVisibility(collectionButtons, true);
    }
    
    public void changeVisibility(JButton[] buttons, boolean visibility)
    {
        // looping through the given buttons and setting them to the visibility value ( true/false )
        for(JButton button : buttons)
        {
            button.setVisible(visibility);
        }
    }
    
    public static javax.swing.JPanel changeView(JFrame main, JPanel target, JPanel panel)
    {
        // get size and location of the current panel
        size = target.getSize();
        point = target.getLocation();
  
        // remove the old panel and add the new one
        main.remove(target);        
        main.add(panel);

        // set the size and location
        panel.setLocation(point.x, point.y);
        panel.setSize(size.width, size.height);
        
        // revalidate the parent panel to draw the new panel on the screen
        main.revalidate();
        
        // return the new panel for further use
        return panel;
    }
    
    public static javax.swing.JPanel changeView(JPanel main, JPanel target, JPanel panel)
    {        
        // get size and location of the current panel
        size = target.getSize();
        point = target.getLocation();
  
        // remove the old panel and add the new one
        main.remove(target);        
        main.add(panel);

        // set the size and location
        panel.setLocation(point.x, point.y);
        panel.setSize(size.width, size.height);
        
        // revalidate the parent panel to draw the new panel on the screen
        main.revalidate();
        
        // return the new panel for further use
        return panel;
    }
}
