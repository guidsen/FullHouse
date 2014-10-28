/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import fullhouse.FullHouse;
import fullhouse.models.Tournament;
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
 * @author Steve
 */
public class TournamentDbRepository extends DbRepository<Tournament> {

    @Override
    public Tournament getModel() {
        return new Tournament();
    }

    public void add(Tournament tournament) {
        try {
            System.out.println("Add tournament.");
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO tournament "
                    + "(name,date,entry_fee,players_per_table,round_amount,game_type,place)"
                    + " VALUES (?,?,?,?,?,default,?)");
            stat.setString(1, tournament.getName());
            stat.setString(2, FullHouse.textToSqlDateTime(tournament.getDate()));
            stat.setDouble(3, tournament.getEntryFee());
            stat.setInt(4, tournament.getPlayersPerTable());
            stat.setInt(5, tournament.getRoundAmount());
            stat.setString(6, tournament.getPlace());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TournamentDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Tournament tournament) {
        try {
            System.out.println("Update tournament.");
            Connection conn = DataSource.getConnection();
            String queryString = "UPDATE tournament SET name=?,date=?,entry_fee=?,players_per_table=?,round_amount=?,place=? WHERE tournament_id=?";
            PreparedStatement stat = conn.prepareStatement(queryString);
            stat.setString(1, tournament.getName());
            stat.setString(2, FullHouse.textToSqlDateTime(tournament.getDate()));
            stat.setDouble(3, tournament.getEntryFee());
            stat.setInt(4, tournament.getPlayersPerTable());
            stat.setInt(5, tournament.getRoundAmount());
            stat.setString(6, tournament.getPlace());
            stat.setInt(7, tournament.getId());
            stat.executeUpdate();

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
                tournament.setDate(FullHouse.fromSqlDateTime(rs.getTimestamp("date")));
                tournament.setEntryFee(rs.getDouble("entry_fee"));
                tournament.setPlayersPerTable(rs.getInt("players_per_table"));
                tournament.setRoundAmount(rs.getInt("round_amount"));
                tournament.setPlace(rs.getString("place"));

                Vector row = new Vector();
                row.addElement(tournament);
                row.addElement(tournament.getPlace());
                row.addElement(tournament.getDate());
                tableModel.addRow(row);
            }

            table.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(TournamentDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id, JTable table) {
        try {
            System.out.println("Delete tournament.");
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("DELETE FROM tournament WHERE tournament_id = ?");
            stat.setInt(1, id);
            stat.executeUpdate();

            FullHouse.deleteRowFromTable(table);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void collectionHome(JTable table){
        try{    
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM tournament");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Tournament tournament = new Tournament();
                tournament.setId(rs.getInt("tournament_id"));
                tournament.setName(rs.getString("name"));
                tournament.setDate(FullHouse.fromSqlDateTime(rs.getTimestamp("date")));
                tournament.setEntryFee(rs.getDouble("entry_fee"));
                tournament.setPlayersPerTable(rs.getInt("players_per_table"));
                tournament.setRoundAmount(rs.getInt("round_amount"));
                tournament.setPlace(rs.getString("place"));

                Vector row = new Vector();
                row.addElement(tournament);
                row.addElement(tournament.getPlace());
                row.addElement(tournament.getDate());
                tableModel.addRow(row);
            }

            table.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(TournamentDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
