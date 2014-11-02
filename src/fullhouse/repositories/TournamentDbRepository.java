/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import fullhouse.FullHouse;
import fullhouse.models.Player;
import fullhouse.models.Tournament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            String query = "SELECT t.*, COUNT(pt.tournament_id) as registered, (SELECT COUNT(round_id) FROM round WHERE tournament_id =  t.tournament_id) AS generatedRounds FROM tournament t LEFT JOIN player_tournament pt ON t.tournament_id = pt.tournament_id GROUP BY pt.tournament_id";
            PreparedStatement stat = conn.prepareStatement(query);
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
                if (rs.getInt("generatedRounds") > 0) {
                    tournament.setGeneratedRounds(true);
                } else {
                    tournament.setGeneratedRounds(false);
                }

                Vector row = new Vector();
                row.addElement(tournament);
                row.addElement(tournament.getPlace());
                row.addElement(tournament.getDate());
                row.addElement(rs.getInt("registered") + "/" + tournament.getMaxPlayers());

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

    public void collectionHome(JTable table) {
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
            sdf.format(date);

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM tournament");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                if (!sdf.format(date).equals(FullHouse.fromSqlDate(rs.getDate("date")))) {
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
            }

            table.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(TournamentDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Player> getSignups(int tournamentId) {
        ArrayList<Player> signups = new ArrayList<>();
        try {
            Connection conn = DataSource.getConnection();
            String query = "SELECT p.*, pt.paid FROM `player_tournament` pt LEFT JOIN player p ON pt.player_id = p.player_id WHERE pt.tournament_id = ?";

            PreparedStatement stat = conn.prepareStatement(query);
            stat.setInt(1, tournamentId);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setTeacher(rs.getInt("teacher"));
                player.setFirstName(rs.getString("first_name"));
                player.setMiddleName(rs.getString("middle_name"));
                player.setLastName(rs.getString("last_name"));
                player.setDateOfBirth(FullHouse.fromSqlDate(rs.getDate("date_of_birth")));
                player.setAddress(rs.getString("address"));
                player.setZipcode(rs.getString("zipcode"));
                player.setCity(rs.getString("city"));
                player.setPhoneNum(rs.getString("phonenum"));
                player.setEmail(rs.getString("email"));
                player.setRating(rs.getInt("rating"));
                player.setPaid(rs.getBoolean("paid"));

                signups.add(player);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return signups;
    }

    public ArrayList<Player> getSignups(int tournamentId, boolean hasPaid) {
        ArrayList<Player> signups = new ArrayList<>();
        try {
            Connection conn = DataSource.getConnection();
            String query;
            if (hasPaid) {
                query = "SELECT p.*, pt.paid FROM `player_tournament` pt LEFT JOIN player p ON pt.player_id = p.player_id WHERE pt.tournament_id = ? AND paid = 1";
            } else {
                query = "SELECT p.*, pt.paid FROM `player_tournament` pt LEFT JOIN player p ON pt.player_id = p.player_id WHERE pt.tournament_id = ? AND paid = 0";
            }

            PreparedStatement stat = conn.prepareStatement(query);
            stat.setInt(1, tournamentId);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setTeacher(rs.getInt("teacher"));
                player.setFirstName(rs.getString("first_name"));
                player.setMiddleName(rs.getString("middle_name"));
                player.setLastName(rs.getString("last_name"));
                player.setDateOfBirth(FullHouse.fromSqlDate(rs.getDate("date_of_birth")));
                player.setAddress(rs.getString("address"));
                player.setZipcode(rs.getString("zipcode"));
                player.setCity(rs.getString("city"));
                player.setPhoneNum(rs.getString("phonenum"));
                player.setEmail(rs.getString("email"));
                player.setRating(rs.getInt("rating"));
                player.setPaid(rs.getBoolean("paid"));

                signups.add(player);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return signups;
    }

    public void populateSignups(JTable table, int tournamentId) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        ArrayList<Player> players = this.getSignups(tournamentId);

        for (Player player : players) {
            Vector row = new Vector();
            row.addElement(player);
            row.addElement(player.getEmail());
            row.addElement(player.getRating());
            row.addElement(player.getPhoneNum());
            row.addElement((player.isPaid()) ? "Ja" : "Nee");
            tableModel.addRow(row);
        }

        table.setModel(tableModel);
    }
    
    public void populateSignups(JTable table, int tournamentId, boolean hasPaid) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        
        ArrayList<Player> players = this.getSignups(tournamentId, hasPaid);

        for (Player player : players) {
            Vector row = new Vector();
            row.addElement(player);
            row.addElement(player.getEmail());
            row.addElement(player.getRating());
            row.addElement(player.getPhoneNum());
            row.addElement((player.isPaid()) ? "Ja" : "Nee");
            tableModel.addRow(row);
        }

        table.setModel(tableModel);
    }
}
