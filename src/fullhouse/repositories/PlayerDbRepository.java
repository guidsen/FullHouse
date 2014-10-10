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

    @Override
    public String getUpdateString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addPlayer() {
        try {
            System.out.println("Add player.");
            Player player = new Player();
            player.find(3);
//            player.setFirstName("Guido");
//            player.setLastName("Schmitz");
//            player.setAddress("Hodorlaan 29");
//
//            HashMap<String, Object> playerMap = new HashMap<>();
//            playerMap.put("first_name", player.getFirstName());
//            playerMap.put("last_name", player.getLastName());
//            playerMap.put("address", player.getAddress());

            //player.select(3);
            
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
