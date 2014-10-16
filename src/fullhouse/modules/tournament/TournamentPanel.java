/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fullhouse.modules.tournament;

import fullhouse.Panel;

/**
 *
 * @author Liam Hubers
 */
public class TournamentPanel extends javax.swing.JPanel {
    private Panel panel = new Panel();
    
    /**
     * Creates new form TournamentPanel
     */
    
    public TournamentPanel() {
        initComponents();
        
        panel.initializeButtons(
            new javax.swing.JButton[] { addTournamentButton, editTournamentButton, deleteTournamentButton },
            new javax.swing.JButton[] { cancelTournamentButton, saveTournamentButton }  
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editTournamentButton = new javax.swing.JButton();
        deleteTournamentButton = new javax.swing.JButton();
        addTournamentButton = new javax.swing.JButton();
        subPanel = new javax.swing.JPanel();
        saveTournamentButton = new javax.swing.JButton();
        cancelTournamentButton = new javax.swing.JButton();

        editTournamentButton.setText("Wijzig toernooi");
        editTournamentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTournamentButtonActionPerformed(evt);
            }
        });

        deleteTournamentButton.setText("Verwijder toernooi");

        addTournamentButton.setText("Voeg toernooi toe");
        addTournamentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTournamentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout subPanelLayout = new javax.swing.GroupLayout(subPanel);
        subPanel.setLayout(subPanelLayout);
        subPanelLayout.setHorizontalGroup(
            subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        subPanelLayout.setVerticalGroup(
            subPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        saveTournamentButton.setText("Opslaan");
        saveTournamentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTournamentButtonActionPerformed(evt);
            }
        });

        cancelTournamentButton.setText("Annuleer");
        cancelTournamentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelTournamentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(addTournamentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editTournamentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteTournamentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveTournamentButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelTournamentButton)
                .addContainerGap(145, Short.MAX_VALUE))
            .addComponent(subPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTournamentButton)
                    .addComponent(editTournamentButton)
                    .addComponent(deleteTournamentButton)
                    .addComponent(saveTournamentButton)
                    .addComponent(cancelTournamentButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addTournamentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTournamentButtonActionPerformed
        // TODO add your handling code here:
        subPanel = Panel.changeView(this, subPanel, new TournamentTabsPanel());
        panel.toForm();
    }//GEN-LAST:event_addTournamentButtonActionPerformed

    private void saveTournamentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTournamentButtonActionPerformed
        // TODO add your handling code here:
        subPanel = Panel.changeView(this, subPanel, new TournamentCollectionPanel());
        panel.toCollection();
    }//GEN-LAST:event_saveTournamentButtonActionPerformed

    private void cancelTournamentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelTournamentButtonActionPerformed
        // TODO add your handling code here:
        subPanel = Panel.changeView(this, subPanel, new TournamentCollectionPanel());
        panel.toCollection();
    }//GEN-LAST:event_cancelTournamentButtonActionPerformed

    private void editTournamentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTournamentButtonActionPerformed
        // TODO add your handling code here:
        subPanel = Panel.changeView(this, subPanel, new TournamentTabsPanel());
        panel.toForm();
    }//GEN-LAST:event_editTournamentButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTournamentButton;
    private javax.swing.JButton cancelTournamentButton;
    private javax.swing.JButton deleteTournamentButton;
    private javax.swing.JButton editTournamentButton;
    private javax.swing.JButton saveTournamentButton;
    public javax.swing.JPanel subPanel;
    // End of variables declaration//GEN-END:variables
}
