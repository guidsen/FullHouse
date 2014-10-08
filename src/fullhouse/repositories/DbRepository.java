/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guido
 * @param <T>
 */
public abstract class DbRepository<T> {

    protected T model;

    protected DbRepository(T model) {
        this.model = model;
    }

    public String getInsertString() {
        StringBuilder insertString = new StringBuilder();
        insertString.append("INSERT INTO " + getTable() + " (");
        for(int i=0; i<getColumnNames().size(); i++) {
            
        }
        insertString.append("test");
        
        return insertString.toString();
    }
    
    public abstract String getUpdateString();

    public String getTable() {
        return model.getClass().getSimpleName().toLowerCase();
    }

    public ArrayList<String> getColumnNames() {
        ArrayList<String> columns = new ArrayList<>();

        try {
            Connection conn = DataSource.getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM " + getTable() + " LIMIT 0,1");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int i = 2; i < columnCount + 1; i++) {
                String columnName = rsmd.getColumnName(i);
                columns.add(columnName);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return columns;
    }

    public HashMap<Integer, String> getColumns() {
        HashMap<Integer, String> columnsMap = new HashMap<>();
        for (int i = 0; i < getColumnNames().size(); i++) {
            columnsMap.put(i + 1, getColumnNames().get(i));
        }
        return columnsMap;
    }
}
