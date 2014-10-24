/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.models;

import fullhouse.QueryBuilder;
import fullhouse.repositories.TournamentDbRepository;

/**
 *
 * @author steve
 */
public class Tournament extends QueryBuilder<TournamentDbRepository> {
    
    private int id;
    private String name;
    private double entryFee;
    private int playerPerTable;
    private int roundAmount;
    private String gameType;
    private String place;
    
    @Override
    public TournamentDbRepository getRepository() {
        return new TournamentDbRepository();
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPlayerPerTable() {
        return playerPerTable;
    }

    public void setPlayerPerTable(int playerPerTable) {
        this.playerPerTable = playerPerTable;
    }

    public int getRoundAmount() {
        return roundAmount;
    }

    public void setRoundAmount(int roundAmount) {
        this.roundAmount = roundAmount;
    }
    
    
}