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
        repository.getTable();

        System.out.println(repository.getInsertString());
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement(repository.getInsertString());

        for (Map.Entry<Integer, String> entry : columns.entrySet()) {
            System.out.println(entry.getKey() + " : " + values.get(entry.getValue()));
            stat.setObject(entry.getKey(), values.get(entry.getValue()));
        }
        
        System.out.println(stat);
    }
    
    /*
    *   Alias for the insert method.
    */
    public void add(HashMap<String, Object> values) throws SQLException {
        insert(values);
    }
}
