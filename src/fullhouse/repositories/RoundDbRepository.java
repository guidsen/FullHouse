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
import java.util.Arrays;
import java.util.List;
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

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public ArrayList<Round> getAll(int tournament_id)
    {
        ArrayList<Round> list = new ArrayList<>();
        
        try{
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM round WHERE tournament_id = ?");
            stat.setInt(1, tournament_id);
            ResultSet rs = stat.executeQuery();
            
            while (rs.next()) {
                Round round = new Round();
                round.setId(rs.getInt("round_id"));
                round.setTournament_id(rs.getInt("tournament_id"));
                round.setRound(rs.getInt("round"));
                
                list.add(round);
            }
            
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public ArrayList<Table> getTables(int round_id) {
        ArrayList<Table> list = new ArrayList<>();
        
        try{
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement(
                "SELECT\n" +
                "pr.`table`,\n" +
                "GROUP_CONCAT(CONCAT(p.first_name,IF(p.middle_name = '', '', ' '),p.middle_name,' ',p.last_name) SEPARATOR ', ') AS players,\n" +
                "GROUP_CONCAT(p.player_id) AS player_ids," + 
                "( SELECT CONCAT(p.first_name,IF(p.middle_name = '', '', ' '),p.middle_name,' ',p.last_name) FROM player_round AS ps LEFT OUTER JOIN player AS p ON ps.player_id = p.player_id WHERE round_id = ? AND winner = 1 AND pr.table = ps.`table`) AS winner\n" +
                "FROM player_round AS pr\n" +
                "LEFT OUTER JOIN player AS p ON pr.player_id = p.player_id\n" +
                "WHERE pr.round_id = ?\n AND (SELECT COUNT(player_id) FROM player_round WHERE winner = 4 AND round_id = pr.round_id AND `table` = pr.`table`) = 0 " +
                "GROUP BY pr.`table`"
            );
            stat.setInt(1, round_id);
            stat.setInt(2, round_id);
            
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Table table = new Table();
                
                table.setTable(rs.getInt("table"));
                table.setPlayers(rs.getString("players"));
                String[] idsString = rs.getString("player_ids").split(",");
                List ids = Arrays.asList(idsString);
                table.setIds(ids);
                table.setWinner(rs.getString("winner"));
                
                list.add(table);
            }
            
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public void fillTable(JTable table, int round_id) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        
        ArrayList<Table> list = this.getTables(round_id);
        
        for(Table tableObject : list) {
            Vector row = new Vector();
            row.addElement("Tafel "+tableObject.getTable());
            row.addElement(tableObject.getPlayers());
            row.addElement(tableObject.getWinner());
            tableModel.addRow(row);
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
    
    public void setWinner(int player_id, int round_id, int next_round_id) {
        try {
            Connection conn = DataSource.getConnection();
            
            PreparedStatement stat = conn.prepareStatement("UPDATE player_round SET winner = 4 WHERE player_id = ? AND round_id = ?");    
            stat.setInt(1, player_id);
            stat.setInt(2, round_id);
            
            stat.executeUpdate();
            
            PreparedStatement insert = conn.prepareStatement("INSERT INTO player_round( player_id, round_id, `table`, winner) SELECT ? AS player_id, ? AS round_id, IF(CEIL(COUNT(player_id)/?) = 0, 1, CEIL(COUNT(player_id)/8)) AS `table`, 0 AS winner FROM `player_round` WHERE round_id = ?");
            insert.setInt(1, player_id);
            insert.setInt(2, next_round_id);
            insert.setInt(3, 8);
            insert.setInt(4, next_round_id);
            
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTournamentWinner(int tournament_id, int player_id, int round_id, int place) {
        try {
            Connection conn = DataSource.getConnection();
            
            PreparedStatement stat = conn.prepareStatement("UPDATE player_round SET winner = ? WHERE player_id = ? AND round_id = ?");    
            stat.setInt(1, place);
            stat.setInt(2, player_id);
            stat.setInt(3, round_id);
            
            stat.executeUpdate();
            
            PreparedStatement money = conn.prepareStatement("SELECT (SELECT COUNT(player_id) FROM player_tournament WHERE tournament_id = t.tournament_id AND paid = 1) * t.entry_fee AS total FROM `tournament` AS t WHERE tournament_id = ?");
            money.setInt(1, tournament_id);
            
            ResultSet rs = money.executeQuery();

            double priceMoney = 0.0;
            while (rs.next()) {
                priceMoney = rs.getDouble("total");
            }
            
            double updateMoney = 0.0;
            if(place == 1) updateMoney = priceMoney*0.4;
            if(place == 2) updateMoney = priceMoney*0.25;
            if(place == 3) updateMoney = priceMoney*0.1;
            
            PreparedStatement update = conn.prepareStatement("UPDATE player SET money_won = money_won + ? WHERE player_id = ?");
            update.setDouble(1, updateMoney);
            update.setInt(2, player_id);
            
            update.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Player> getPlayerPerTable(int round_id, int table) {
        ArrayList<Player> list = new ArrayList<>();
        
        try{
            Connection conn = DataSource.getConnection();

            PreparedStatement stat = conn.prepareStatement("SELECT p.*, pr.winner FROM player_round AS pr LEFT JOIN player AS p ON pr.player_id = p.player_id WHERE pr.round_id = ? AND pr.`table` = ?");
            stat.setInt(1, round_id);
            stat.setInt(2, table);
            
            ResultSet rs = stat.executeQuery();
            
            while(rs.next()) {
                Player player = new Player();
                player.setId(rs.getInt("player_id"));
                player.setRating(rs.getInt("rating"));
                player.setWinner(rs.getInt("winner"));
                
                list.add(player);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
