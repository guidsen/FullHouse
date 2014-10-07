/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

/**
 *
 * @author Guido
 * @param <T>
 */
public class DbRepository<T> implements DbRepositoryInterface<T>{

    protected T model;

    protected DbRepository(T model) {
        System.out.println("DbRepository created.");
        this.model = model;
        System.out.println(model);
    }

    @Override
    public T getModel() {
        return model;
    }
}
