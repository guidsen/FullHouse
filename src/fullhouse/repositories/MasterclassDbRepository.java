/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import fullhouse.FullHouse;
import fullhouse.models.Masterclass;
import fullhouse.models.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Guido
 */
public class MasterclassDbRepository extends DbRepository<Masterclass> {

    @Override
    public Masterclass getModel() {
        return new Masterclass();
    }

    public void add(Masterclass masterclass) {
        try {
            System.out.println("Add masterclass.");
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO masterclass "
                    + "(leader_id,min_rating,price,name,max_players,date) VALUES (?,?,?,?,?,?)");
            stat.setInt(1, masterclass.getLeaderId());
            stat.setInt(2, masterclass.getMinRating());
            stat.setDouble(3, masterclass.getPrice());
            stat.setString(4, masterclass.getName());
            stat.setInt(5, masterclass.getMaxPlayers());
            stat.setString(6, FullHouse.textToSqlDateTime(masterclass.getDate()));
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Masterclass masterclass) {
        try {
            System.out.println("Update masterclass.");
            Connection conn = DataSource.getConnection();
            String queryString = "UPDATE masterclass SET leader_id=?,min_rating=?,price=?,name=?,max_players=?,date=? WHERE masterclass_id=?";
            PreparedStatement stat = conn.prepareStatement(queryString);
            stat.setInt(1, masterclass.getLeaderId());
            stat.setInt(2, masterclass.getMinRating());
            stat.setDouble(3, masterclass.getPrice());
            stat.setString(4, masterclass.getName());
            stat.setInt(5, masterclass.getMaxPlayers());
            stat.setString(6, FullHouse.textToSqlDateTime(masterclass.getDate()));
            stat.setInt(7, masterclass.getId());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void collection(JTable table) {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Connection conn = DataSource.getConnection();
            String query = "SELECT m.*, COUNT(ms.masterclass_id) as signups, p.player_id, p.first_name, p.middle_name, p.last_name "
                    + "FROM `masterclass` m "
                    + "LEFT JOIN masterclass_signup ms ON m.masterclass_id = ms.masterclass_id "
                    + "LEFT JOIN player p ON m.leader_id = p.player_id "
                    + "GROUP BY m.masterclass_id ORDER BY m.date";
            PreparedStatement stat = conn.prepareStatement(query);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Masterclass masterclass = new Masterclass();
                Player player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setFirstName(rs.getString("first_name"));
                player.setMiddleName(rs.getString("middle_name"));
                player.setLastName(rs.getString("last_name"));
                
                masterclass.setId(rs.getInt("masterclass_id"));
                masterclass.setLeaderId(rs.getInt("leader_id"));
                masterclass.setMinRating(rs.getInt("min_rating"));
                masterclass.setPrice(rs.getDouble("price"));
                masterclass.setName(rs.getString("name"));
                masterclass.setMaxPlayers(rs.getInt("max_players"));
                masterclass.setDate(FullHouse.fromSqlDateTime(rs.getTimestamp("date")));
                masterclass.setSignups(rs.getInt("signups"));
                masterclass.setTeacher(player);
                
                Vector row = new Vector();
                row.addElement(masterclass);
                row.addElement(masterclass.getDate());
                row.addElement(masterclass.getSignups());
                row.addElement(masterclass.getMinRating());
                tableModel.addRow(row);
            }

            table.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id, JTable table) {
        try {
            System.out.println("Delete masterclass.");
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("DELETE FROM masterclass WHERE masterclass_id = ?");
            stat.setInt(1, id);
            stat.executeUpdate();
            
            FullHouse.deleteRowFromTable(table);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Player> getSignups(int masterclassId) {
        ArrayList<Player> signups = new ArrayList<>();
        try {
            Connection conn = DataSource.getConnection();
            String query = "SELECT p.*, ms.paid FROM `masterclass_signup` ms LEFT JOIN player p ON ms.player_id = p.player_id WHERE ms.masterclass_id = ?";

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
                player.setPaid(rs.getBoolean("paid"));

                signups.add(player);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return signups;
    }

    public ArrayList<Player> getSignups(int masterclassId, boolean hasPaid) {
        ArrayList<Player> signups = new ArrayList<>();
        try {
            Connection conn = DataSource.getConnection();
            String query;
            if (hasPaid) {
                query = "SELECT p.*, ms.paid FROM `masterclass_signup` ms LEFT JOIN player p ON ms.player_id = p.player_id WHERE ms.masterclass_id = ? AND paid = 1";
            } else {
                query = "SELECT p.*, ms.paid FROM `masterclass_signup` ms LEFT JOIN player p ON ms.player_id = p.player_id WHERE ms.masterclass_id = ? AND paid = 0";
            }

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
                player.setPaid(rs.getBoolean("paid"));

                signups.add(player);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return signups;
    }

    public void populateSignups(JTable table, int masterclassId) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        ArrayList<Player> players = this.getSignups(masterclassId);

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
    
    public void populateSignups(JTable table, int masterclassId, boolean hasPaid) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        
        ArrayList<Player> players = this.getSignups(masterclassId, hasPaid);

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

    public void setSelectedTeacher() {
        System.out.println("test");
    }
}
