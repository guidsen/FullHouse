/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.masterclass;

import fullhouse.models.Masterclass;
import fullhouse.models.Player;
import fullhouse.repositories.MasterclassDbRepository;
import fullhouse.repositories.PlayerDbRepository;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author steve
 */
public class MasterclassSignUpPanel extends javax.swing.JPanel {

    private MasterclassDbRepository masterclassRepo = new MasterclassDbRepository();
    private PlayerDbRepository playerRepo = new PlayerDbRepository();
    private Masterclass selectedMasterclass;
    private Player selectedPlayer;

    /**
     * Creates new form MasterclassSignUpPanel
     */
    public MasterclassSignUpPanel(Masterclass selectedMasterclass) {
        initComponents();

        selectedPlayerLabel.setVisible(false);
        signOutPlayerButton.setVisible(false);
        backButton.setVisible(false);
        this.selectedMasterclass = selectedMasterclass;
        this.playerRepo.comboboxCollectionMasterclass(selectedMasterclass.getId(), playerCombobox);
        this.masterclassRepo.populateSignups(masterclassSignupsTable, selectedMasterclass.getId());
        
        masterclassSignupsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = masterclassSignupsTable.rowAtPoint(evt.getPoint());
                int col = masterclassSignupsTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println(masterclassSignupsTable.getValueAt(row, 0));
                    selectedPlayerLabel.setVisible(true);
                    signOutPlayerButton.setVisible(true);
                    backButton.setVisible(true);
                    playerCombobox.setVisible(false);
                    playerSignupButton.setVisible(false);

                    this.setSelectedPlayer((Player) masterclassSignupsTable.getValueAt(row, 0));
                }
            }

            private void setSelectedPlayer(Player player) {
                selectedPlayerLabel.setText(player.toString());
                selectedPlayer = player;
                paidCheckbox.setSelected(player.isPaid());
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        signOutPlayerButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        selectedPlayerLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        playerCombobox = new javax.swing.JComboBox();
        filterCombobox = new javax.swing.JComboBox();
        paidCheckbox = new javax.swing.JCheckBox();
        playerSignupButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        masterclassSignupsTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        backButton.setText("Terug");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        signOutPlayerButton.setText("Speler uitschrijven");
        signOutPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutPlayerButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Speler");

        jLabel2.setText("Betaald");

        jLabel4.setText("Filter:");

        playerCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        filterCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alle", "Hebben betaald", "Hebben niet betaald" }));
        filterCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterComboboxActionPerformed(evt);
            }
        });

        paidCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidCheckboxActionPerformed(evt);
            }
        });

        playerSignupButton.setText("Speler inschrijven");
        playerSignupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerSignupButtonActionPerformed(evt);
            }
        });

        masterclassSignupsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Naam", "Email", "Rating", "Tel. nummer", "Betaald"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(masterclassSignupsTable);

        jLabel3.setText("Ingeschreven spelers voor deze masterclass");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(filterCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(paidCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(playerCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(playerSignupButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(selectedPlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(signOutPlayerButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(backButton)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(playerCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(playerSignupButton)
                        .addComponent(selectedPlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(signOutPlayerButton)
                        .addComponent(backButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paidCheckbox)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(filterCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        selectedPlayerLabel.setVisible(false);
        signOutPlayerButton.setVisible(false);
        backButton.setVisible(false);
        playerCombobox.setVisible(true);
        playerSignupButton.setVisible(true);

        paidCheckbox.setSelected(false);
        masterclassSignupsTable.clearSelection();
        this.selectedPlayer = null;
    }//GEN-LAST:event_backButtonActionPerformed

    private void signOutPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutPlayerButtonActionPerformed
        playerRepo.signOutMasterclass(selectedPlayer.getId(), selectedMasterclass.getId());
        this.backButtonActionPerformed(evt);
        this.masterclassRepo.populateSignups(masterclassSignupsTable, selectedMasterclass.getId());
    }//GEN-LAST:event_signOutPlayerButtonActionPerformed

    private void filterComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterComboboxActionPerformed
        int index = filterCombobox.getSelectedIndex();
        if (index == 0) {
            masterclassRepo.populateSignups(masterclassSignupsTable, selectedMasterclass.getId());
        } else if (index == 1) {
            masterclassRepo.populateSignups(masterclassSignupsTable, selectedMasterclass.getId(), true);
        } else if (index == 2) {
            masterclassRepo.populateSignups(masterclassSignupsTable, selectedMasterclass.getId(), false);
        }
    }//GEN-LAST:event_filterComboboxActionPerformed

    private void paidCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidCheckboxActionPerformed
        if (this.selectedPlayer != null) {
            playerRepo.setPaidMasterclass(selectedPlayer.getId(), selectedMasterclass.getId(), paidCheckbox.isSelected());
            //this.masterclassRepo.populateSignups(masterclassSignupsTable, selectedMasterclass.getId());
            filterComboboxActionPerformed(evt);
        }
    }//GEN-LAST:event_paidCheckboxActionPerformed

    private void playerSignupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerSignupButtonActionPerformed
        Player selectedPlayer = (Player) playerCombobox.getSelectedItem();

        int dialog = JOptionPane.showConfirmDialog(playerCombobox, String.format("Weet u zeker dat u %s wilt inschrijven voor deze masterclass? ", selectedPlayer.toString()));

        if (dialog == JOptionPane.YES_OPTION) {
            try {
                this.playerRepo.signupMasterclass(selectedPlayer.getId(), this.selectedMasterclass.getId(), paidCheckbox.isSelected());
                this.masterclassRepo.populateSignups(masterclassSignupsTable, this.selectedMasterclass.getId());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Deze gebruiker is al ingeschreven voor deze masterclass.");
            }
        }
    }//GEN-LAST:event_playerSignupButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox filterCombobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable masterclassSignupsTable;
    private javax.swing.JCheckBox paidCheckbox;
    private javax.swing.JComboBox playerCombobox;
    private javax.swing.JButton playerSignupButton;
    private javax.swing.JLabel selectedPlayerLabel;
    private javax.swing.JButton signOutPlayerButton;
    // End of variables declaration//GEN-END:variables
}
