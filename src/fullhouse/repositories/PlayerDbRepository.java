/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.repositories;

import fullhouse.models.Player;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guido
 */
public class PlayerDbRepository extends DbRepository<Player> {

    @Override
    public Player getModel(){
        return new Player();
    }

    public void addPlayer() {
        try {
            System.out.println("Add player.");
            //HashMap<String, Object> player = new Player().where("first_name", "=", "Hodor");
            //System.out.println(player.get("first_name"));
            new Player().delete(123);
//            Player player = new Player();
//            player.setId(3);
//            player.setFirstName("Guido");
//            player.setLastName("Schmitz");
//            player.setAddress("Hodorlaan 29");
//
//            HashMap<String, Object> playerMap = new HashMap<>();
//            playerMap.put("first_name", player.getFirstName());
//            playerMap.put("last_name", player.getLastName());
//            playerMap.put("address", player.getAddress());
//
//            player.update(playerMap, player.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
