/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules;

import fullhouse.Panel;
import javax.swing.JFrame;

/**
 *
 * @author Guido
 */
public class HomeFrame extends javax.swing.JFrame {

    /**
     * Creates new form HomeFrame
     */
    public HomeFrame() {
        initComponents();
        homeButton.setEnabled(false);
        
        /* Disable blue glow around buttons */
        homeButton.setFocusable(false);
        plannedTournamentsButton.setFocusable(false);
        plannedMasterclassButton.setFocusable(false);
        playersOverviewButton.setFocusable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playersOverviewButton = new javax.swing.JButton();
        plannedTournamentsButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        plannedMasterclassButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Full House");

        playersOverviewButton.setText("Spelersoverzicht");
        playersOverviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playersOverviewButtonActionPerformed(evt);
            }
        });

        plannedTournamentsButton.setText("Geplande toernooien");
        plannedTournamentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plannedTournamentsButtonActionPerformed(evt);
            }
        });

        homeButton.setText("Home");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 759, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        plannedMasterclassButton.setText("Geplande masterclass");
        plannedMasterclassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plannedMasterclassButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(playersOverviewButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plannedTournamentsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plannedMasterclassButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playersOverviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plannedTournamentsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(plannedMasterclassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playersOverviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playersOverviewButtonActionPerformed
        playersOverviewButton.setEnabled(false);
        plannedTournamentsButton.setEnabled(true);
        plannedMasterclassButton.setEnabled(true);
        homeButton.setEnabled(true);
        
        fullhouse.modules.player.PlayerPanel PlayerPanel = new fullhouse.modules.player.PlayerPanel();
        mainPanel = Panel.changeView(this, mainPanel, PlayerPanel);
        PlayerPanel.revalidate();
        PlayerPanel.subPanel = Panel.changeView(PlayerPanel, PlayerPanel.subPanel, new fullhouse.modules.player.PlayerCollectionPanel());
    }//GEN-LAST:event_playersOverviewButtonActionPerformed

    private void plannedTournamentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plannedTournamentsButtonActionPerformed
        plannedTournamentsButton.setEnabled(false);
        playersOverviewButton.setEnabled(true);
        plannedMasterclassButton.setEnabled(true);
        homeButton.setEnabled(true);
        
        fullhouse.modules.tournament.TournamentPanel TournamentPanel = new fullhouse.modules.tournament.TournamentPanel();
        mainPanel = Panel.changeView(this, mainPanel, TournamentPanel);
        TournamentPanel.revalidate();
        TournamentPanel.subPanel = Panel.changeView(TournamentPanel, TournamentPanel.subPanel, new fullhouse.modules.tournament.TournamentCollectionPanel());
    }//GEN-LAST:event_plannedTournamentsButtonActionPerformed

    private void plannedMasterclassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plannedMasterclassButtonActionPerformed
        plannedMasterclassButton.setEnabled(false);
        playersOverviewButton.setEnabled(true);
        plannedTournamentsButton.setEnabled(true);
        homeButton.setEnabled(true);
        
        fullhouse.modules.masterclass.MasterclassPanel MasterclassPanel = new fullhouse.modules.masterclass.MasterclassPanel();
        mainPanel = Panel.changeView(this, mainPanel, MasterclassPanel);
        MasterclassPanel.revalidate();
        MasterclassPanel.subPanel = Panel.changeView(MasterclassPanel, MasterclassPanel.subPanel, new fullhouse.modules.masterclass.MasterclassCollectionPanel());
    }//GEN-LAST:event_plannedMasterclassButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        homeButton.setEnabled(false);
        playersOverviewButton.setEnabled(true);
        plannedTournamentsButton.setEnabled(true);
        plannedMasterclassButton.setEnabled(true);

        HomePanel HomePanel = new HomePanel();
        mainPanel = Panel.changeView(this, mainPanel, HomePanel);
        HomePanel.revalidate();
    }//GEN-LAST:event_homeButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton homeButton;
    public javax.swing.JPanel mainPanel;
    private javax.swing.JButton plannedMasterclassButton;
    private javax.swing.JButton plannedTournamentsButton;
    private javax.swing.JButton playersOverviewButton;
    // End of variables declaration//GEN-END:variables
}
