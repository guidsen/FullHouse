/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Guido
 */
public class Eloquent {

    protected Connection conn;

    public Eloquent() {
        try {
            this.conn = DataSource.getConnection();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void find(int id) {

    }

}
