/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import fullhouse.FullHouse;
import fullhouse.models.Masterclass;
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
public class MasterclassDbRepository extends DbRepository<Masterclass> {

    @Override
    public Masterclass getModel() {
        return new Masterclass();
    }

    public void add(Masterclass masterclass) {
        try {
            System.out.println("Add masterclass.");
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO masterclass VALUES (0,?,?,?,?)");
            stat.setInt(1, masterclass.getLeaderId());
            stat.setInt(2, masterclass.getMinRating());
            stat.setString(3, masterclass.getName());
            stat.setString(4, FullHouse.textToSqlDateTime(masterclass.getDate()));
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Masterclass masterclass) {
        try {
            System.out.println("Update masterclass.");
            Connection conn = DataSource.getConnection();
            String queryString = "UPDATE masterclass SET leader_id=?,min_rating=?,name=?,date=? WHERE masterclass_id=?";
            PreparedStatement stat = conn.prepareStatement(queryString);
            stat.setInt(1, masterclass.getLeaderId());
            stat.setInt(2, masterclass.getMinRating());
            stat.setString(3, masterclass.getName());
            stat.setString(4, FullHouse.textToSqlDateTime(masterclass.getDate()));
            stat.setInt(5, masterclass.getId());
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void collection(JTable table) {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            Connection conn = DataSource.getConnection();
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM masterclass");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                Masterclass masterclass = new Masterclass();
                masterclass.setId(rs.getInt("masterclass_id"));
                masterclass.setLeaderId(rs.getInt("leader_id"));
                masterclass.setMinRating(rs.getInt("min_rating"));
                masterclass.setName(rs.getString("name"));
                masterclass.setDate(FullHouse.fromSqlDateTime(rs.getTimestamp("date")));

                Vector row = new Vector();
                row.addElement(masterclass);
                row.addElement(masterclass.getDate());
                row.addElement("ok");
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
}
