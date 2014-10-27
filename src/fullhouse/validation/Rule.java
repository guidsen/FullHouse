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
        if (input.length() < length) {
            validator.addError(label, "Dit veld moet een lengte van minimaal " + length + "hebben.");
        }
        return this;
    }
    
    public Rule maxLength(int length) throws FormValidationException {
        if (input.length() > length) {
            validator.addError(label, "Dit veld mag maximaal een lengte van " + length + "hebben.");
        }
        return this;
    }
    
    public Rule email() throws FormValidationException {
        regex("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", "Dit is geen geldig e-mail adres.");
        return this;
    }
    
    public Rule date(String format) throws FormValidationException {
        String[] parts = format.split("(?!d|m|Y|H|i|s)");

        if(format.length() == input.length()){
            for(String part : parts){
                int begin = format.indexOf(part);
                int end = begin + part.length();
                if(begin != 0){
                    begin++;
                }
                try{
                    int parser = Integer.parseInt(input.substring(begin, end));
                    
                    switch(format.substring(begin, end)){
                        case "dd":
                            if(parser > 31){
                                validator.addError(label, "Een maand heeft maximaal 31 dagen.");
                            }
                            break;
                        case "mm":
                            if(parser > 12){
                                validator.addError(label, "Een jaar heeft maar 12 maanden.");
                            }
                            break;
                        case "HH":
                            if(parser > 23){
                                validator.addError(label, "Een dag heeft maar 24 uur.");
                            }
                            break;
                        case "ii":
                            if(parser > 59){
                                validator.addError(label, "Een uur heeft maar 60 minuten.");
                            }
                            break;
                        case "ss":
                            if(parser > 59){
                                validator.addError(label, "Een minuut heeft maar 60 seconden.");
                            }
                            break;
                    }
                } catch(Exception e){
                    validator.addError(label, "U heeft geen geldige datum ingevoerd.");
                }
            }
        } else {
            System.out.println("asd");
            validator.addError(label, "U heeft geen geldige datum ingevoerd.");
        }
        
        return this;
    }
    
    public Rule phonenumber() throws FormValidationException {
        
        return this;
    }
    
    public Rule numeric() throws FormValidationException {
        try{
            Integer.parseInt(input);
        } catch(Exception e) {
            validator.addError(label, "Dit is geen getal.");
        }
        return this;
    }
    
    public void regex(String regex, String error) throws FormValidationException {
        if(!input.matches(regex)){
            validator.addError(label, error);
            
        }
    }
    
    public Rule alpha() throws FormValidationException {
        regex("[a-zA-Z]+$", "U mag alleen letters gebruiken.");
        return this;
    }
    
    public Rule money() throws FormValidationException {
        regex("^(((\\d{1,3})(.\\d{3})*)|(\\d+))(,\\d+)?$", "Dit is geen geldig bedrag");
        return this;
    }
}