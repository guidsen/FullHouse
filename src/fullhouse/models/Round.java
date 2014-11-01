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
public class Round {
    
    private int id;
    private int tournament_id;
    private int round;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }   
    
    public String toString()
    {
        return "Ronde "+this.getRound();
    }
}
