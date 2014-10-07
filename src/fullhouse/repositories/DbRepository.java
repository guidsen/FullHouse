/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.DataSource;
import java.sql.*;
import java.util.HashMap;

/**
 *
 * @author Guido
 * @param <T>
 */
public abstract class DbRepository<T> implements DbRepositoryInterface<T> {

    protected T model;

    public abstract String getInsertString();

    protected abstract String[] getColumnNames();

    protected DbRepository(T model) {
        this.model = model;
    }

    public HashMap<Integer, String> getColumns() {
        HashMap<Integer, String> columnsMap = new HashMap<>();
        for (int i = 0; i < getColumnNames().length; i++) {
            columnsMap.put(i + 1, getColumnNames()[i]);
        }
        return columnsMap;
    }

    @Override
    public T getModel() {
        return model;
    }

    public void add(T model) throws Exception {
    }
}
