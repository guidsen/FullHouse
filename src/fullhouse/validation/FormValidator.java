/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.validation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

/**
 *
 * @author Guido
 */
public class FormValidator {

    private static FormValidator instance = null;

    protected ArrayList<Rule> rules = new ArrayList<>();
    protected HashMap<JLabel, String> errors = new HashMap<>();

    public static FormValidator getInstance() {
        if (instance == null) {
            instance = new FormValidator();
        }
        return instance;
    }

    public void add(Rule rule) {
        rules.add(rule);
    }

    public void addError(JLabel label, String message) {
        errors.put(label, message);
    }

    public int getErrorCount() {
        return errors.size();
    }

    public void resetErrors() {
        for (Map.Entry<JLabel, String> entry : errors.entrySet()) {
            entry.getKey().setVisible(false);
        }
        errors.clear();
    }

    public HashMap<JLabel, String> getErrors() {
        return errors;
    }
}