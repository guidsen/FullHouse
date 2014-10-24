/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.exceptions;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

/**
 *
 * @author Guido
 */
public class FormValidationException extends Exception {
    private final HashMap<JLabel, String> errors;

    public FormValidationException(HashMap<JLabel, String> errors) {
        super("Form validation failed.");
        this.errors = errors;
    }
    
    public void setErrors() {
        for(Map.Entry<JLabel, String> entry : errors.entrySet()) {
            entry.getKey().setVisible(true);
            entry.getKey().setForeground(Color.red);
            entry.getKey().setText(entry.getValue());
        }
    }
    
}
