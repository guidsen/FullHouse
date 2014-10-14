/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.player;

import fullhouse.Panel;
import fullhouse.repositories.PlayerDbRepository;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;

/**
 *
 * @author Liam Hubers
 */
public class PlayerPanel extends javax.swing.JPanel {

    private PlayerDbRepository playerRepo = new PlayerDbRepository();
    private Panel panel = new Panel();

    /**
     * Creates new form PlayerPanel
     */
    public PlayerPanel() {
        initComponents();

        panel.initializeButtons(
                new javax.swing.JButton[]{addPlayerButton, editPlayerButton, deletePlayerButton},
                new javax.swing.JButton[]{playerCancelButton, savePlayerButton}
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

        deletePlayerButton = new javax.swing.JButton();
        addPlayerButton = new javax.swing.JButton();
        editPlayerButton = new javax.swing.JButton();
        savePlayerButton = new javax.swing.JButton();
        subPanel = new javax.swing.JPanel();
        playerCancelButton = new javax.swing.JButton();

        deletePlayerButton.setText("Verwijder speler");
        deletePlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePlayerButtonActionPerformed(evt);
            }
        });

        addPlayerButton.setText("Voeg speler toe");
        addPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayerButtonActionPerformed(evt);
            }
        });

        editPlayerButton.setText("Wijzig speler");
        editPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPlayerButtonActionPerformed(evt);
            }
        });

        savePlayerButton.setText("Opslaan");
        savePlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePlayerButtonActionPerformed(evt);
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
            .addGap(0, 359, Short.MAX_VALUE)
        );

        playerCancelButton.setText("Annuleer");
        playerCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(addPlayerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editPlayerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deletePlayerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savePlayerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerCancelButton)
                .addContainerGap(386, Short.MAX_VALUE))
            .addComponent(subPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPlayerButton)
                    .addComponent(editPlayerButton)
                    .addComponent(deletePlayerButton)
                    .addComponent(savePlayerButton)
                    .addComponent(playerCancelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayerButtonActionPerformed
        subPanel = Panel.changeView(this, subPanel, new PlayerFormPanel(playerRepo));
        playerRepo.addPlayer();
        System.out.println(fullhouse.FullHouse.fromSqlDate(Timestamp.valueOf("2014-10-14 14:50:00.0")));
        panel.toForm();
    }//GEN-LAST:event_addPlayerButtonActionPerformed

    private void savePlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePlayerButtonActionPerformed
        // TODO add your handling code here:
        subPanel = Panel.changeView(this, subPanel, new PlayerCollectionPanel());
        panel.toCollection();
    }//GEN-LAST:event_savePlayerButtonActionPerformed

    private void editPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPlayerButtonActionPerformed
        // TODO add your handling code here:
        subPanel = Panel.changeView(this, subPanel, new PlayerFormPanel(playerRepo));
        panel.toForm();
    }//GEN-LAST:event_editPlayerButtonActionPerformed

    private void deletePlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePlayerButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deletePlayerButtonActionPerformed

    private void playerCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerCancelButtonActionPerformed
        // TODO add your handling code here:
        subPanel = Panel.changeView(this, subPanel, new PlayerCollectionPanel());
        panel.toCollection();
    }//GEN-LAST:event_playerCancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPlayerButton;
    private javax.swing.JButton deletePlayerButton;
    private javax.swing.JButton editPlayerButton;
    private javax.swing.JButton playerCancelButton;
    private javax.swing.JButton savePlayerButton;
    public javax.swing.JPanel subPanel;
    // End of variables declaration//GEN-END:variables
}
