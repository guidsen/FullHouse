/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.models;

import fullhouse.QueryBuilder;
import fullhouse.repositories.MasterclassDbRepository;

/**
 *
 * @author Guido
 */
public class Masterclass extends QueryBuilder<MasterclassDbRepository> {
    
    private int id;
    private int leaderId;
    private int minRating;
    private double price;
    private String name;
    private int maxPlayers;
    private String date;
    private int signups;
    private Player teacher;
    
    @Override
    public MasterclassDbRepository getRepository() {
        return new MasterclassDbRepository();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getMinRating() {
        return minRating;
    }

    public void setMinRating(int minRating) {
        this.minRating = minRating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSignups() {
        return signups;
    }

    public void setSignups(int signups) {
        this.signups = signups;
    }

    public Player getTeacher() {
        return teacher;
    }

    public void setTeacher(Player teacher) {
        this.teacher = teacher;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
