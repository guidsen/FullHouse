/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import fullhouse.repositories.DbRepository;
import java.sql.*;
import java.util.HashMap;

/**
 *
 * @author Guido
 */
public class QueryBuilder<T extends DbRepository> {

    protected HashMap<String, Object> values;
    protected T repository;
    protected HashMap<Integer, String> columns;

    public QueryBuilder(HashMap<String, Object> values, T repository) {
        this.values = values;
        this.repository = repository;
        this.columns = repository.getColumns();
    }

    public void getValues() throws SQLException {
        System.out.println(repository.getInsertString());
        Connection conn = DataSource.getConnection();
        PreparedStatement stat = conn.prepareStatement(repository.getInsertString());

        for (HashMap.Entry<Integer, String> entry : columns.entrySet()) {
            System.out.println(entry.getKey() + " : " + values.get(entry.getValue()));
            stat.setObject(entry.getKey(), values.get(entry.getValue()));
        }
        
        System.out.println(stat);
    }
}
