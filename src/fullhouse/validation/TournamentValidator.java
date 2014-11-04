/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.validation;

import fullhouse.exceptions.FormValidationException;
import fullhouse.modules.tournament.TournamentFormPanel;

/**
 *
 * @author Guido
 */
public class TournamentValidator {

    public void validate(TournamentFormPanel form) throws FormValidationException {
        FormValidator validator = FormValidator.getInstance();
        validator.resetErrors();
        validator.addTextField(form.nameField, form.nameValidationLabel).required();
        validator.addTextField(form.dateField, form.dateValidationLabel).required().date("dd-mm-YYYY HH:ii");
        validator.addTextField(form.placeField, form.placeValidationLabel).required();
        validator.addTextField(form.entryFeeField, form.entryFeeValidationLabel).required();

        if (validator.getErrorCount() > 0) {
            throw new FormValidationException(validator.getErrors());
        }
    }
}
