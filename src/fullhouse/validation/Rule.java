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
            validator.addError(label, "Dit is een verplicht veld.");
        }
        return this;
    }

    public Rule minLength(int length) throws FormValidationException {
        if (input.length() < length && !input.isEmpty()) {
            validator.addError(label, "Dit veld moet een lengte van minimaal " + length + "hebben.");
        }
        return this;
    }

    public Rule maxLength(int length) throws FormValidationException {
        if (input.length() > length && !input.isEmpty()) {
            validator.addError(label, "Dit veld mag maximaal een lengte van " + length + "hebben.");
        }
        return this;
    }

    public Rule email() throws FormValidationException {
        if (!input.isEmpty()) {
            regex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", "Dit is geen geldig e-mail adres.");
        }
        return this;
    }

    public Rule date(String format) throws FormValidationException {
        if (!input.isEmpty()) {
            String[] parts = format.split("(?!d|m|Y|H|i|s)");

            if (format.length() == input.length()) {
                for (String part : parts) {
                    int begin = format.indexOf(part);
                    int end = begin + part.length();
                    if (begin != 0) {
                        begin++;
                    }

                    try {
                        int parser = Integer.parseInt(input.substring(begin, end));
                        String formatPart = format.substring(begin, end);

                        if (formatPart.equals("dd") && parser > 31) {
                            validator.addError(label, "Een maand heeft maximaal 31 dagen.");
                        } else if (formatPart.equals("mm") && parser > 12) {
                            validator.addError(label, "Een jaar heeft 12 maanden.");
                        } else if (formatPart.equals("HH") && parser > 23) {
                            validator.addError(label, "Een dag heeft 24 uur.");
                        } else if (formatPart.equals("ii") && parser > 59) {
                            validator.addError(label, "Een uur heeft 60 minuten.");
                        } else if (formatPart.equals("ss") && parser > 59) {
                            validator.addError(label, "Een minuut heeft 60 seconden.");
                        }
                    } catch (Exception e) {
                        validator.addError(label, "U heeft geen geldige datum ingevoerd.");
                    }
                }
            } else if (format.length() != input.length() && input.length() > 0) {
                validator.addError(label, "U heeft geen geldige datum ingevoerd.");
            }
        }

        return this;
    }

    public Rule phonenumber() throws FormValidationException {
        if (!input.isEmpty()) {
            try {
                Integer.parseInt(input.substring(0, 10));
            } catch (Exception e) {
                validator.addError(label, "Dit is geen geldig telefoonnummer");
            }
        }
        return this;
    }

    public Rule numeric() throws FormValidationException {
        if (!input.isEmpty()) {
            try {
                Integer.parseInt(input);
            } catch (Exception e) {
                validator.addError(label, "Dit is geen getal.");
            }
        }
        return this;
    }

    public void regex(String regex, String error) throws FormValidationException {
        if (!input.matches(regex) && !input.isEmpty()) {
            validator.addError(label, error);
        }
    }

    public Rule alpha() throws FormValidationException {
        if (!input.isEmpty()) {
            regex("[a-zA-Z]+$", "U mag alleen letters gebruiken.");
        }
        return this;
    }

    public Rule zipcode() throws FormValidationException {
        if (!input.isEmpty()) {
            regex("^[0-9]{4}[a-zA-z]{2}", "Dit is geen geldige postcode.");
        }
        return this;
    }

    public Rule money() throws FormValidationException {
        if (!input.isEmpty()) {
            regex("^(((\\d{1,3})(.\\d{3})*)|(\\d+))(,\\d+)?$", "Dit is geen geldig bedrag.");
        }
        return this;
    }
}
