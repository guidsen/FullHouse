/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
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
            HashMap<String, Object> playerMap = new HashMap<>();
            playerMap.put("first_name", player.getFirstName());
            playerMap.put("last_name", player.getLastName());
            playerMap.put("address", player.getAddress());
            playerMap.put("phonenum", player.getPhoneNum());
            playerMap.put("email", player.getEmail());

            player.insert(playerMap);

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
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
