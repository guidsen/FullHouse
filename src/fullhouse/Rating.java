package fullhouse;

/**
 * This class represents the calculation of the ratings in the tournament.
 *
 * @author Guido Schmitz
 */
public class Rating {

    private int own;
    private int opponents;
    private int result;
    
    private double min = 500;
    private double max = 2000;
    private double score = 0.8;
    private int max_score = 150;
    
    public Rating(int own, int opponents)
    {
        this.own = own;
        this.opponents = opponents;
        
        calculate();
    }
    
    public int calculate()
    {
	double defider = max / opponents;
	double own_score = max / own;
        
	result = (int) Math.round( score * 100 / defider * own_score / ( ( max / min + 1 ) - ( max / own ) ) );	

	if(result > max_score)
	{
            result = max_score; 
	}
        
        System.out.println(opponents+" "+result);
        
        return result;
    }

}
