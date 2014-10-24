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
public class PlayerDbRepository extends DbRepository<Player> {

    @Override
    public Player getModel() {
        return new Player();
    }

    public void add(Player player) {
        try {
            System.out.println("Add player.");
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO player VALUES (0,?,?,?,?,?,?,?,?,?, default)");
            stat.setString(1, player.getFirstName());
            stat.setString(2, player.getMiddleName());
            stat.setString(3, player.getLastName());
            stat.setString(4, FullHouse.textToSqlDate(player.getDateOfBirth()));
            stat.setString(5, player.getAddress());
            stat.setString(6, player.getZipcode());
            stat.setString(7, player.getCity());
            stat.setString(8, player.getPhoneNum());
            stat.setString(9, player.getEmail());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Player player) {
        System.out.println("Update player.");
        System.out.println(player.toString());
        System.out.println("id: " + player.getId());
    }

    public void collection(JTable table) {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM player");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("player_id"));
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
                row.addElement(player.getRating());
                tableModel.addRow(row);
            }

            table.setModel(tableModel);

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
