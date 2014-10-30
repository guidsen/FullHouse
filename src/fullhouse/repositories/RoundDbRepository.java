/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import fullhouse.FullHouse;
import fullhouse.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Guido
 */
public class RoundDbRepository extends DbRepository<Round> {

    @Override
    public Round getModel() {
        return new Round();
    }

    public void add(Round round) {
        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO round (tournament_id, round) VALUES (?, ?)");
            
            stat.setInt(1, round.getTournament_id());
            stat.setInt(2, round.getRound());
            
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void collection(JTable table) {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM round");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Round round = new Round();

                Vector row = new Vector();
                row.addElement(round);
                tableModel.addRow(row);
            }

            table.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id, JTable table) {
        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("DELETE FROM round WHERE round_id = ?");
            stat.setInt(1, id);
            stat.executeUpdate();
            
            FullHouse.deleteRowFromTable(table);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
