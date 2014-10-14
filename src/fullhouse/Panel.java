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
        this.collectionButtons = collectionButtons;
        this.formButtons = formButtons;
        
        changeVisibility(formButtons, false);
    }
    
    public void toForm()
    {
        changeVisibility(formButtons, true);
        changeVisibility(collectionButtons, false);
    }
    
    public void toCollection()
    {
        changeVisibility(formButtons, false);
        changeVisibility(collectionButtons, true);
    }
    
    public void changeVisibility(JButton[] buttons, boolean visibility)
    {
        for(JButton button : buttons)
        {
            button.setVisible(visibility);
        }
    }
    
    public static javax.swing.JPanel changeView(JFrame main, JPanel target, JPanel panel)
    {
        size = target.getSize();
        point = target.getLocation();
        
        System.out.println(target);
        
        main.remove(target);        
        main.add(panel);

        panel.setLocation(point.x, point.y);
        panel.setSize(size.width, size.height);
        
        main.revalidate();
        
        return panel;
    }
    
    public static javax.swing.JPanel changeView(JPanel main, JPanel target, JPanel panel)
    {        
        size = target.getSize();
        point = target.getLocation();
        
        main.remove(target);        
        main.add(panel);

        panel.setLocation(point.x, point.y);
        panel.setSize(size.width, size.height);
        
        main.revalidate();
        
        return panel;
    }
}
