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
        System.out.println(form.nameField.getText());
        validator.addTextField(form.nameField, form.nameValidationLabel).required();
        validator.addTextField(form.dateField, form.dateValidationLabel).date("HH:ii");
        //validator.addTextField(form.maxPlayersInfo, form.dateValidationLabel).date("HH:ii");

        if (validator.getErrorCount() > 0) {
            throw new FormValidationException(validator.getErrors());
        }
    }
}
