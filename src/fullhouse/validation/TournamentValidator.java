/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.validation;

import fullhouse.exceptions.FormValidationException;
import fullhouse.modules.tournament.TournamentInfoPanel;

/**
 *
 * @author steve
 */
public class TournamentValidator {
    
    public void validate(TournamentInfoPanel form) throws FormValidationException{
        FormValidator validator = FormValidator.getInstance();
        validator.resetErrors();
        validator.addTextField(form.tournamentName, form.tournamentNameValidationLabel).required();
    }
}
