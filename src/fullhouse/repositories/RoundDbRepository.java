/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import fullhouse.FullHouse;
import fullhouse.models.Player;
import fullhouse.models.Round;
import fullhouse.models.Table;
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
public class RoundDbRepository extends DbRepository<Round> {

    @Override
    public Round getModel() {
        return new Round();
    }

    public int add(Round round) {
        int id = 0;
        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO round (tournament_id, round) VALUES (?, ?)", java.sql.Statement.RETURN_GENERATED_KEYS);
            
            stat.setInt(1, round.getTournament_id());
            stat.setInt(2, round.getRound());
            
            stat.executeUpdate();
            
            ResultSet rs = stat.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public ArrayList<Round> getAll(int tournament_id)
    {
        try{
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM round WHERE tournament_id = ?");
            stat.setInt(1, tournament_id);
            ResultSet rs = stat.executeQuery();
            
            ArrayList<Round> list = new ArrayList<>();
            
            while (rs.next()) {
                Round round = new Round();
                round.setId(rs.getInt("round_id"));
                round.setTournament_id(rs.getInt("tournament_id"));
                round.setRound(rs.getInt("round"));
                
                list.add(round);
            }
            
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<Round>();
    }
    
    public void getTables(JTable table, int round_id) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        
        try{
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement(
                "SELECT\n" +
                "pr.`table`,\n" +
                "GROUP_CONCAT(CONCAT(p.first_name,IF(p.middle_name = '', '', ' '),p.middle_name,' ',p.last_name)) AS players,\n" +
                "( SELECT CONCAT(p.first_name,IF(p.middle_name = '', '', ' '),p.middle_name,' ',p.last_name) FROM player_round AS ps LEFT OUTER JOIN player AS p ON ps.player_id = p.player_id WHERE round_id = ? AND winner > 0 AND pr.table = ps.`table`) AS winner\n" +
                "FROM player_round AS pr\n" +
                "LEFT OUTER JOIN player AS p ON pr.player_id = p.player_id\n" +
                "WHERE pr.round_id = ?\n" +
                "GROUP BY pr.`table`"
            );
            stat.setInt(1, round_id);
            stat.setInt(2, round_id);
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Table tableObject = new Table();
                
                Vector row = new Vector();
                row.addElement("Tafel "+rs.getString("table"));
                row.addElement(rs.getString("players"));
                row.addElement(rs.getString("winner"));
                tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void collection(JTable table, int tournament_id) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        ArrayList<Round> list = this.getAll(tournament_id);

        for(Round round : list){
            Vector row = new Vector();
            row.addElement(round);
            tableModel.addRow(row);
        }

        table.setModel(tableModel);
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
    
    public void addPlayer(int round_id, Player player, int table) {
        try {
            Connection conn = DataSource.getConnection();
            
            PreparedStatement stat = conn.prepareStatement("INSERT INTO player_round (`player_id`, `round_id`, `table`) VALUES (?, ?, ?)");    
            stat.setInt(1, player.getId());
            stat.setInt(2, round_id);
            stat.setInt(3, table);
            
            stat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
