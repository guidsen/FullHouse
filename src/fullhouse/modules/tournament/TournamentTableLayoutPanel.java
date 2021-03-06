/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.tournament;

import fullhouse.models.Player;
import fullhouse.models.Round;
import fullhouse.models.Tournament;
import fullhouse.repositories.RoundDbRepository;
import fullhouse.repositories.TournamentDbRepository;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Guido
 */
public class TournamentTableLayoutPanel extends javax.swing.JPanel {

    private RoundDbRepository repository = new RoundDbRepository();
    private TournamentDbRepository tournamentRepository = new TournamentDbRepository();
    private int tournament_id;
    private TournamentFormPanel form;
    private javax.swing.JTabbedPane tabs;
    
    /**
     * Creates new form TournamentTableLayoutPanel
     */
    public TournamentTableLayoutPanel(int tournament_id, TournamentFormPanel form, javax.swing.JTabbedPane tabs) {
        initComponents();
        this.tournament_id = tournament_id;
        this.form = form;
        this.tabs = tabs;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setText("Genereer tafelindeling");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        int dialog = JOptionPane.showConfirmDialog(null, "Weet u zeker dat u de rondes wilt genereren?");

        if (dialog == JOptionPane.YES_OPTION) {
            ArrayList<Player> players = this.tournamentRepository.getSignups(this.tournament_id);

            int perTable = Integer.parseInt(form.playersPerTableBox.getSelectedItem().toString());

            for(int i = 1; i < Integer.parseInt(form.roundAmountBox.getSelectedItem().toString())+1; i++)
            {
                Round round = new Round();
                round.setTournament_id(this.tournament_id);
                round.setRound(i);

                int id = repository.add(round);

                if(i == 1)
                {
                    int y = 0;
                    for(Player player : players)
                    {
                        if(player.isPaid())
                        {
                            this.repository.addPlayer(id, player, (int)Math.ceil(y / perTable) + 1);
                            y++;
                        }
                    }
                }
            }
            
            tabs.removeTabAt(2);
            tabs.addTab("Tafelindeling", new TournamentTableLayoutCollectionPanel(tournament_id));
            tabs.addTab("Uitslag", new TournamentResultsPanel(tournament_id));
            tabs.setSelectedIndex(2);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
