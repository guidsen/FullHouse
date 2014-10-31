/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.validation;

import fullhouse.exceptions.FormValidationException;
import fullhouse.modules.masterclass.MasterclassFormPanel;

/**
 *
 * @author Guido
 */
public class MasterclassValidator {
    public void validate(MasterclassFormPanel form) throws FormValidationException {
        FormValidator validator = FormValidator.getInstance();
        validator.resetErrors();
        validator.addTextField(form.masterclassName, form.nameValidationLabel).required();
        validator.addTextField(form.masterclassDate, form.dateValidationLabel).required().date("dd-mm-YYYY HH:ii");
        validator.addTextField(form.masterclassMaxPlayers, form.maxPlayersValidationLabel).required();
        validator.addTextField(form.masterclassMinRating, form.minRatingValidationLabel).required().minLength(4).maxLength(4);
        
        if (validator.getErrorCount() > 0) {
            throw new FormValidationException(validator.getErrors());
        }
    }
}
