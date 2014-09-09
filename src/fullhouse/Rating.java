package fullhouse;

/**
 * This class represents the calculation of the ratings in the tournament.
 *
 * @author Guido Schmitz
 */
public class Rating {

    private final int totalRatings;
    private final int winRatio;
    private final int playedGames;

    Rating(int totalRatings, int winRatio, int playedGames) {
        this.totalRatings = totalRatings;
        this.winRatio = winRatio;
        this.playedGames = playedGames;
    }

    public int calculate() {
        int newRating = this.totalRatings + (400 * this.winRatio) / this.playedGames;
        return newRating;
    }

}
