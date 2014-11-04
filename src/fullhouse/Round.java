/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse;

import java.text.SimpleDateFormat;

/**
 *
 * @author Liam
 */
public class Round {
    public static int tables = 20;
    public static int table = 8;
    public static int players = 150;
    public static boolean finals = false;
    public static int round = 0;
    
    public static void main(String[] args)
    {
        while(!finals)
        {
            if(players <= table)
            {
                finals = true;
            } else {
                players = (int)Math.ceil((double)players / (double)table);
            }
            round++;
        }
    }
}
