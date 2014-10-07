/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.models.Player;

/**
 *
 * @author Guido
 */
public class PlayerDbRepository extends DbRepository<Player> {

    public PlayerDbRepository() {
        super(new Player());
    }
    
    public void addPlayer() {
        System.out.println("Add player.");
    }
    
}
