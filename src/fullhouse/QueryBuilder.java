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
public class QueryBuilder<T extends DbRepository> {

    protected HashMap<String, Object> values;
    protected T repository;
    protected HashMap<Integer, String> columns;

    public QueryBuilder(T repository) {
        this.repository = repository;
        this.columns = repository.getColumns();
    }

    public void insert(HashMap<String, Object> values) throws SQLException {
        System.out.println("insert");
//        Connection conn = DataSource.getConnection();
//        PreparedStatement stat = conn.prepareStatement(repository.getInsertString());
//
//        for (Map.Entry<Integer, String> entry : columns.entrySet()) {
//            stat.setObject(entry.getKey(), values.get(entry.getValue()));
//        }
//
//        System.out.println(stat);
    }

    /*
     *   Alias for the insert method.
     */
    public void add(HashMap<String, Object> values) throws SQLException {
        insert(values);
    }

    public void get(int id) throws SQLException {
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement("SELECT * FROM " + repository.getTable() + " WHERE id = ?");
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
