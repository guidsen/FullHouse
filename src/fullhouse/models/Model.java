/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fullhouse.models;

/**
 *
 * @author Liam Hubers
 */
public class Model {
    private int id;
    private String description;
    
    public Model(int id, String description) {
        this.id = id;
        this.description = description;
        
    }
    
    public String toString()
    {
        return this.description;
    }
    
    public int getId() {
        return this.id;
    }
}
