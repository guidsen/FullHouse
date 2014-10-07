/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fullhouse.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Liam Hubers
 */
public class PlayerPanel extends javax.swing.JPanel {
    private Dimension size;
    private Point point;

    /**
     * Creates new form PlayerPanel
     */
    public PlayerPanel() {
        initComponents();
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
        jPanel1 = new fullhouse.frames.PlayerCollectionPanel();

        deletePlayerButton.setText("Verwijder speler");

        addPlayerButton.setText("Voeg speler toe");
        addPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayerButtonActionPerformed(evt);
            }
        });

        editPlayerButton.setText("Wijzig speler");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPlayerButton)
                    .addComponent(editPlayerButton)
                    .addComponent(deletePlayerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayerButtonActionPerformed
        // TODO add your handling code here:
        changeView(new PlayerItemPanel());
    }//GEN-LAST:event_addPlayerButtonActionPerformed

    public void changeView(javax.swing.JPanel panel)
    {        
        size = jPanel1.getSize();
        point = jPanel1.getLocation();
        
        remove(jPanel1);
        
        add(panel, BorderLayout.PAGE_END);

        panel.setLocation(point.x, point.y);
        panel.setSize(size.width, size.height);
        
        revalidate();
        validate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPlayerButton;
    private javax.swing.JButton deletePlayerButton;
    private javax.swing.JButton editPlayerButton;
    private fullhouse.frames.PlayerCollectionPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
