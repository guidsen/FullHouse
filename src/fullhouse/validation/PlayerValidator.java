/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.validation;

import fullhouse.exceptions.FormValidationException;
import fullhouse.modules.player.PlayerFormPanel;

/**
 *
 * @author Guido
 */
public class PlayerValidator {

    public void validate(PlayerFormPanel form) throws FormValidationException {
        FormValidator validator = FormValidator.getInstance();
        validator.resetErrors();
        validator.add(new Rule(form.firstNameField.getText(), form.firstNameValidationLabel).required().minLength(2));
        validator.add(new Rule(form.lastNameField.getText(), form.lastNameValidationLabel).minLength(5));

        if (validator.getErrorCount() > 0) {
            throw new FormValidationException(validator.getErrors());
        }
    }
}
