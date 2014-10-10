/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import fullhouse.repositories.DbRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        conn.close();
    }

    /*
     *   Alias for the insert method.
     */
    public void add(HashMap<String, Object> values) throws SQLException {
        insert(values);
    }

    public void find(int id) throws SQLException {
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement("SELECT * FROM " + getRepository().getTable() + " WHERE id = ?");
        stat.setInt(1, id);

        System.out.println(stat);
    }

    public void where(String column, String operator, Object value) {
    }

    /*
     *  Will take operator '=' as default 
     */
    public void where(String column, Object value) {
    }

    public void update(HashMap<String, Object> values) {
    }

    public void delete(int id) {
    }
}
