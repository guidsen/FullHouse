/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

/**
 *
 * @author Guido
 */
public class FullHouse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer[] players = {1560, 1400, 977, 632, 1274};
        
        int total = 0;
        for(int player : players) {
            total += player;
        }
        
        Rating rating = new Rating(total, 6, 2);
        System.out.println(rating.calculate());
    }

}
