/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import fullhouse.repositories.DbRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Guido
 */
public abstract class QueryBuilder<T extends DbRepository> {

    public abstract T getRepository();

    public void insert(HashMap<String, Object> values) throws SQLException {
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement(getRepository().getInsertString());

        HashMap<Integer, String> columns = getRepository().getColumns();

        for (Map.Entry<Integer, String> entry : columns.entrySet()) {
            stat.setObject(entry.getKey(), values.get(entry.getValue()));
        }

        System.out.println(stat);

        stat.close();
    }

    public void update(HashMap<String, Object> values, int id) throws SQLException {
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement(getRepository().getUpdateString());

        HashMap<Integer, String> columns = getRepository().getColumns();

        for (Map.Entry<Integer, String> entry : columns.entrySet()) {
            stat.setObject(entry.getKey(), values.get(entry.getValue()));
        }

        stat.setInt(columns.size() + 1, id);

        System.out.println(stat);

        stat.close();
    }

    public ArrayList<Object> all() throws SQLException {
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement("SELECT *, COUNT(deelname) FROM " + getRepository().getTable());
        ResultSet rs = stat.executeQuery();

        HashMap<Integer, String> columns = getRepository().getColumns();
        
        ArrayList<Object> collection = new ArrayList<>();
        
        while (rs.next()) {
            HashMap<String, Object> resultMap = new HashMap<>();
            for (Map.Entry<Integer, String> entry : columns.entrySet()) {
                System.out.println(entry.getValue() + " : " + rs.getString(entry.getValue()));
                resultMap.put(entry.getValue(), rs.getString(entry.getValue()));
            }
            collection.add(resultMap);
        }

        stat.close();
        return collection;
    }

    public HashMap<String, Object> find(int id) throws SQLException {
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement("SELECT * FROM " + getRepository().getTable() + " WHERE id = ?");
        stat.setInt(1, id);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("first_name", "Hodor");

        System.out.println(stat);
        stat.close();
        return resultMap;
    }

    public HashMap<String, Object> where(String column, String operator, Object value) throws SQLException {
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement("SELECT * FROM " + getRepository().getTable() + " WHERE ? " + operator + " ?");
        stat.setString(1, column);
        stat.setObject(2, value);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("first_name", "Hodor");

        System.out.println(stat);
        stat.close();
        return resultMap;
    }

    public HashMap<String, Object> where(String column, Object value) throws SQLException {
        return where(column, "=", value);
    }

    public void delete(int id) throws SQLException {
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement("DELETE FROM " + getRepository().getTable() + " WHERE id = ?");
        stat.setInt(1, id);

        System.out.println(stat);
        stat.close();
    }
}
