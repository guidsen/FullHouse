/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.validation;

import fullhouse.exceptions.FormValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;

/**
 *
 * @author Guido
 */
public class Rule {

    private final FormValidator validator = FormValidator.getInstance();
    private final String input;
    private final JLabel label;
    private Pattern pattern;
    private Matcher matcher;

    public Rule(String input, JLabel label) {
        this.input = input;
        this.label = label;
    }

    public Rule required() throws FormValidationException {
        if (input.isEmpty()) {
            validator.addError(label, "Veld is verplicht");
        }
        return this;
    }

    public Rule minLength(int length) throws FormValidationException {
        if (input.length() < length) {
            validator.addError(label, "Minimale lengte van " + length);
        }
        return this;
    }
    
    public Rule email() throws FormValidationException {
        String mailreg = 
	"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
        if(input.matches(mailreg)){
            return this;
        }
        else{
            validator.addError(label, "Email klopt niet" + mailreg);
        }
        
        return this;
    }

}
