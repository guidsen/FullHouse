/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fullhouse.models;

import java.util.List;

/**
 *
 * @author Liam Hubers
 */
public class Table {
    private int table;
    private String players;
    private String winner;
    private List ids;
    
    public List getIds() {
        return this.ids;
    }
    
    public void setIds(List ids) {
        this.ids = ids;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }    
    
    public String toString()
    {
        return "Tafel "+this.table;
    }
}
