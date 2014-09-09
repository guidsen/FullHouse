/*
 * To change this template, choose Tools | Templates
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
        Rating rating = new Rating(160, 105, 0);
        System.out.println(rating.getScoreA());
        System.out.println(rating.getScoreB());
    }
}
