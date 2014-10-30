/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.tournament;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import fullhouse.models.Player;
import fullhouse.repositories.PlayerDbRepository;
import fullhouse.repositories.TournamentDbRepository;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;

/**
 *
 * @author steve
 */
public class TournamentSignupPanel extends javax.swing.JPanel {

    private PlayerDbRepository playerRepo = new PlayerDbRepository();
    private TournamentDbRepository tournamentRepo = new TournamentDbRepository();

    /**
     * Creates new form TournamentAddPlayerPanel
     */
    public TournamentSignupPanel() {
        initComponents();

        this.playerRepo.comboboxCollection(playerCombobox);
        this.tournamentRepo.populateSignups(tournamentSignupsTable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        playerCombobox = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        playerSignupButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tournamentSignupsTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("Speler");

        jLabel2.setText("Betaald");

        playerCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        playerSignupButton.setText("Speler inschrijven");
        playerSignupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerSignupButtonActionPerformed(evt);
            }
        });

        tournamentSignupsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tournamentSignupsTable);

        jLabel3.setText("Ingeschreven spelers voor dit toernooi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(playerCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(playerSignupButton)))
                        .addGap(40, 40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(playerCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerSignupButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void playerSignupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerSignupButtonActionPerformed
        Player selectedPlayer = (Player) playerCombobox.getSelectedItem();

        int dialog = JOptionPane.showConfirmDialog(playerCombobox, String.format("Weet u zeker dat u %s wilt inschrijven voor dit toernooi? ", selectedPlayer.toString()));

        if (dialog == JOptionPane.YES_OPTION) {
            try {
                this.playerRepo.signup(selectedPlayer.getId(), jCheckBox1.isSelected());
                this.tournamentRepo.populateSignups(tournamentSignupsTable);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Deze gebruiker is al ingeschreven voor dit toernooi.");
            }
        }
    }//GEN-LAST:event_playerSignupButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox playerCombobox;
    private javax.swing.JButton playerSignupButton;
    private javax.swing.JTable tournamentSignupsTable;
    // End of variables declaration//GEN-END:variables
}