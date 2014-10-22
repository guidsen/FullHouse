/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import fullhouse.models.Tournament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Guido
 */
public class TournamentDbRepository extends DbRepository<Tournament> {

    @Override
    public Tournament getModel() {
        return new Tournament();
    }

    public void add(Tournament tournament) {
        try {
            System.out.println("Add tournament");
            HashMap<String, Object> tournamentMap = new HashMap<>();
            tournamentMap.put("name", tournament.getName());
            tournamentMap.put("entry_fee", tournament.getEntryFee());
            tournamentMap.put("player_per_table", tournament.getPlayerPerTable());
            tournamentMap.put("round_amount", tournament.getRoundAmount());
            tournamentMap.put("place", tournament.getPlace());

            tournament.insert(tournamentMap);

        } catch (SQLException ex) {
            Logger.getLogger(TournamentDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void collection(JTable table) {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM tournament");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Tournament tournament = new Tournament();
                tournament.setId(rs.getInt("tournament_id"));
                tournament.setName(rs.getString("name"));
                tournament.setEntryFee(rs.getDouble("entry_fee"));
                tournament.setPlayerPerTable(rs.getInt("player_per_table"));
                tournament.setRoundAmount(rs.getInt("round_amount"));
                tournament.setPlace(rs.getString("place"));
                
                Vector row = new Vector();
                row.addElement(tournament.getName());
                row.addElement(tournament.getPlace());
                tableModel.addRow(row);
            }

            table.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(TournamentDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
