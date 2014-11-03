/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import fullhouse.FullHouse;
import fullhouse.models.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Guido
 */
public class PlayerDbRepository extends DbRepository<Player> {

    @Override
    public Player getModel() {
        return new Player();
    }

    public void add(Player player) {
        try {
            System.out.println("Add player.");
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO player "
                    + "(teacher, first_name, middle_name, last_name, date_of_birth, address, zipcode, city, phonenum, email, rating) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?, default)");
            stat.setInt(1, player.getTeacher());
            stat.setString(2, player.getFirstName());
            stat.setString(3, player.getMiddleName());
            stat.setString(4, player.getLastName());
            stat.setString(5, FullHouse.textToSqlDate(player.getDateOfBirth()));
            stat.setString(6, player.getAddress());
            stat.setString(7, player.getZipcode());
            stat.setString(8, player.getCity());
            stat.setString(9, player.getPhoneNum());
            stat.setString(10, player.getEmail());
            System.out.println(stat);
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Player player) {
        try {
            System.out.println("Update player.");
            Connection conn = DataSource.getConnection();
            String queryString = "UPDATE player SET first_name=?,middle_name=?,last_name=?,date_of_birth=?,address=?,zipcode=?,city=?,phonenum=?,email=?,teacher=? WHERE player_id=?";
            PreparedStatement stat = conn.prepareStatement(queryString);
            stat.setString(1, player.getFirstName());
            stat.setString(2, player.getMiddleName());
            stat.setString(3, player.getLastName());
            stat.setString(4, FullHouse.textToSqlDate(player.getDateOfBirth()));
            stat.setString(5, player.getAddress());
            stat.setString(6, player.getZipcode());
            stat.setString(7, player.getCity());
            stat.setString(8, player.getPhoneNum());
            stat.setString(9, player.getEmail());
            stat.setInt(10, player.getTeacher());
            stat.setInt(11, player.getId());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void collection(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        ArrayList<Player> players = this.getPlayers();

        for (Player player : players) {
            Vector row = new Vector();
            row.addElement(player);
            row.addElement(player.getEmail());
            row.addElement(player.getRating());
            row.addElement(player.getParticipations());
            tableModel.addRow(row);
        }

        table.setModel(tableModel);
    }

    public void delete(int id, JTable table) {
        try {
            System.out.println("Delete player.");
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("DELETE FROM player WHERE player_id = ?");
            stat.setInt(1, id);
            stat.executeUpdate();

            FullHouse.deleteRowFromTable(table);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comboboxCollection(JComboBox combobox, int tournamentId) {
        try {
            DefaultComboBoxModel comboboxModel = new DefaultComboBoxModel();
            Connection conn = DataSource.getConnection();
            String query = "SELECT * FROM player p WHERE player_id NOT IN (SELECT player_id FROM player_tournament pt WHERE p.player_id = pt.player_id AND tournament_id = ?)";
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
                comboboxModel.addElement(player);
            }

            combobox.setModel(comboboxModel);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void comboboxCollectionMasterclass(int masterclassId, JComboBox combobox) {
        DefaultComboBoxModel comboboxModel = new DefaultComboBoxModel();
        ArrayList<Player> players = this.getPlayersMasterclass(masterclassId);

        for (Player player : players) {
            comboboxModel.addElement(player);
        }

        combobox.setModel(comboboxModel);
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            Connection conn = DataSource.getConnection();
            String query = "SELECT p.*, COUNT(pt.player_id) as participations FROM player p LEFT JOIN player_tournament pt ON p.player_id = pt.player_id GROUP BY p.player_id";
            PreparedStatement stat = conn.prepareStatement(query);
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
                player.setParticipations(rs.getInt("participations"));

                players.add(player);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return players;
    }

    public ArrayList<Player> getPlayersMasterclass(int masterclassId) {
        ArrayList<Player> players = new ArrayList<>();
        try {
            Connection conn = DataSource.getConnection();
            String query = "SELECT p.*, COUNT(ms.masterclass_id) AS participations, m.max_players "
                    + "FROM player p "
                    + "LEFT JOIN masterclass m ON p.rating >= m.min_rating "
                    + "LEFT JOIN masterclass_signup ms ON m.masterclass_id = ms.masterclass_id "
                    + "WHERE m.masterclass_id =? "
                    + "AND p.rating >= m.min_rating "
                    + "GROUP BY p.player_id";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setInt(1, masterclassId);
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
                player.setParticipations(rs.getInt("participations"));

                players.add(player);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return players;
    }

    public ArrayList<Player> getLeaders() {
        ArrayList<Player> leaders = new ArrayList<>();
        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM player WHERE teacher = 1");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setFirstName(rs.getString("first_name"));
                player.setMiddleName(rs.getString("middle_name"));
                player.setLastName(rs.getString("last_name"));

                leaders.add(player);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leaders;
    }

    public void setPaid(int playerId, int tournamentId, boolean paid) {
        try {
            int paidInt = (paid) ? 1 : 0;
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("UPDATE player_tournament SET paid = ? WHERE player_id = ? AND tournament_id = ?");
            stat.setInt(1, paidInt);
            stat.setInt(2, playerId);
            stat.setInt(3, tournamentId);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void setPaidMasterclass(int playerId, int masterclassId, boolean paid) {
        try {
            int paidInt = (paid) ? 1 : 0;
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("UPDATE masterclass_signup SET paid = ? WHERE player_id = ? AND masterclass_id = ?");
            stat.setInt(1, paidInt);
            stat.setInt(2, playerId);
            stat.setInt(3, masterclassId);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void signup(int playerId, int tournamentId, boolean paid) throws SQLException {
            int paidInt = (paid) ? 1 : 0;
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO player_tournament (player_id, tournament_id, paid) VALUES (?, ?, ?)");
            stat.setInt(1, playerId);
            stat.setInt(2, tournamentId);
            stat.setInt(3, paidInt);
            stat.executeUpdate();
    }

    public void signupMasterclass(int playerId, int masterclassId, boolean paid) throws SQLException {
        int paidInt = (paid) ? 1 : 0;
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement("INSERT INTO masterclass_signup (player_id, masterclass_id, paid) VALUES (?, ?, ?)");
        stat.setInt(1, playerId);
        stat.setInt(2, masterclassId);
        stat.setInt(3, paidInt);
        stat.executeUpdate();
    }

    public void signOut(int playerId, int tournamentId) {
        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("DELETE FROM player_tournament WHERE player_id = ? AND tournament_id = ?");
            stat.setInt(1, playerId);
            stat.setInt(2, tournamentId);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void signOutMasterclass(int playerId, int masterclassId) {
        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("DELETE FROM masterclass_signup WHERE player_id = ? AND masterclass_id = ?");
            stat.setInt(1, playerId);
            stat.setInt(2, masterclassId);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void collectionPlayersNotPaid(int tournament_id, JTable table) {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM player WHERE player_id "
                    + "IN(SELECT player_id FROM player_tournament WHERE tournament_id = ? AND paid = 0)");
            stat.setInt(1, tournament_id);
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

                Vector row = new Vector();
                row.addElement(player);
                row.addElement(player.getEmail());
                tableModel.addRow(row);
            }
            table.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
