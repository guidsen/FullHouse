/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.validation;

import fullhouse.exceptions.FormValidationException;
import javax.swing.JLabel;

/**
 *
 * @author Guido
 */
public class Rule {

    private final FormValidator validator = FormValidator.getInstance();
    private final String input;
    private final JLabel label;

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

    public Rule maxLength(int length) throws FormValidationException {
        if (input.length() > length) {
            validator.addError(label, "Minimale lengte van " + length);
        }
        return this;
    }

    public Rule email() throws FormValidationException {
        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (input.matches(emailreg)) {
            return this;
        } else {
            validator.addError(label, "Email klopt niet.");
        }
        return this;
    }

    public Rule date(String format) throws FormValidationException {
        String[] parts = format.split("(?!d|m|Y|H|i|s)");

        if (format.length() == input.length()) {
            for (String part : parts) {
                int begin = format.indexOf(part);
                int end = begin + part.length();
                if (begin != 0) begin++;
                
                try {
                    int parser = Integer.parseInt(input.substring(begin, end));
                    String formatPart = format.substring(begin, end);

                    if(formatPart == "dd") {
                        if (parser > 31) validator.addError(label, "Een maand heeft maximaal 31 dagen.");
                    } else if(formatPart == "mm") {
                        if (parser > 12) validator.addError(label, "Een jaar heeft maar 12 maanden.");
                    } else if(formatPart == "HH") {
                        if (parser > 23) validator.addError(label, "Een dag heeft maar 24 uur.");
                    } else if(formatPart == "ii") {
                        if (parser > 59) validator.addError(label, "Een uur heeft maar 60 minuten.");
                    } else if(formatPart == "ss") {
                        if (parser > 59) validator.addError(label, "Een minuut heeft maar 60 seconden.");
                    }
                } catch (Exception e) {
                    validator.addError(label, "Ongeldige datum.");
                }
            }
        } else {
            validator.addError(label, "Ongeldige datum.");
        }

        return this;
    }

    public Rule phonenumber() throws FormValidationException {

        return this;
    }

    public Rule numeric() throws FormValidationException {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            validator.addError(label, "Dit is geen getal.");
        }
        return this;
    }

    public Rule regex(String regex, String error) throws FormValidationException {

        return this;
    }

    public Rule alpha() throws FormValidationException {

        return this;
    }

    public Rule money() throws FormValidationException {

        return this;
    }

    public Rule bool() throws FormValidationException {

        return this;
    }
}
