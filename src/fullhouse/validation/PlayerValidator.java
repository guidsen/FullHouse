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
        validator.addTextField(form.firstNameField, form.firstNameValidationLabel).alpha().required();
        validator.addTextField(form.lastNameField, form.lastNameValidationLabel).alpha().required();
        validator.addTextField(form.dateOfBirthField, form.dateOfBirthValidationLabel).date("dd-mm-YYYY");
        validator.addTextField(form.emailField, form.emailValidationLabel).required().email();

        if (validator.getErrorCount() > 0) {
            throw new FormValidationException(validator.getErrors());
        }
    }
}
