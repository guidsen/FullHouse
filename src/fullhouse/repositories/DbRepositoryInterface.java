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
public interface DbRepositoryInterface<T> {

    public T getModel();
}
