/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

/**
 *
 * @author Guido
 */
public class Rating {

    private final int scoreA;
    private final int scoreB;
    private final int playerWon;

    /**
     * @param scoreA score of playerA
     * @param scoreB score of playerB
     * @param playerWon 0 is playerA, 1 is playerB
     */
    Rating(int scoreA, int scoreB, int playerWon) {
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.playerWon = playerWon;
    }

    /**
     * Gets the score of playerA
     *
     * @return scoreA
     */
    public int getScoreA() {
        return this.scoreA;
    }

    /**
     * Gets the score of playerB
     *
     * @return scoreB
     */
    public int getScoreB() {
        return this.scoreB;
    }

    /**
     * Gets the winner. 0 is playerA, 1 is playerB
     *
     * @return
     */
    public int getWinner() {
        return this.playerWon;
    }
}
