/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.tournament;

import fullhouse.models.Model;
import fullhouse.models.Player;
import fullhouse.models.Rating;
import fullhouse.models.Round;
import fullhouse.models.Table;
import fullhouse.repositories.PlayerDbRepository;
import fullhouse.repositories.RoundDbRepository;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Guido
 */
public class TournamentResultsPanel extends javax.swing.JPanel {

    private RoundDbRepository repository = new RoundDbRepository();
    private PlayerDbRepository playerRepository = new PlayerDbRepository();
    private ArrayList<Round> list;
    private int tournament_id;

    /**
     * Creates new form TournamentResultsPanel
     */
    public TournamentResultsPanel(int tournament_id) {
        initComponents();

        this.tournament_id = tournament_id;

        list = this.repository.getAll(tournament_id);

        roundComboBox.removeAllItems();
        for (Round round : list) {
            roundComboBox.addItem(round);
        }

        if (!list.isEmpty()) {
            ArrayList<Table> tables = this.repository.getTables(list.get(0).getId());

            tableComboBox.removeAllItems();
            for (Table table : tables) {
                tableComboBox.addItem(table);
            }

            playerComboBox.removeAllItems();
            if (!tables.isEmpty()) {
                Table table = tables.get(0);
                String[] players = table.getPlayers().split(", ");

                for (String player : players) {
                    playerComboBox.addItem(player);
                }
            }
        }

        placeComboBox.addItem(new Model(1, "1e plaats"));
        placeComboBox.addItem(new Model(2, "2e plaats"));
        placeComboBox.addItem(new Model(3, "3e plaats"));

        placeLabel.setVisible(false);
        placeComboBox.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundComboBox = new javax.swing.JComboBox();
        tableComboBox = new javax.swing.JComboBox();
        playerComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        placeLabel = new javax.swing.JLabel();
        placeComboBox = new javax.swing.JComboBox();

        roundComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundComboBoxActionPerformed(evt);
            }
        });

        tableComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Ronde");

        jLabel2.setText("Tafel");

        jLabel3.setText("Speler");

        jButton1.setText("Uitslag invoeren");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        placeLabel.setText("Plaats");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(41, 41, 41))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(placeLabel)
                                .addGap(43, 43, 43)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(roundComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tableComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playerComboBox, 0, 134, Short.MAX_VALUE)
                            .addComponent(placeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(placeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(141, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void roundComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundComboBoxActionPerformed
        // TODO add your handling code here:
        Round id = (Round) roundComboBox.getSelectedItem();

        ArrayList<Table> tables = this.repository.getTables(id.getId());

        tableComboBox.removeAllItems();
        for (Table table : tables) {
            tableComboBox.addItem(table);
        }

        playerComboBox.removeAllItems();
        if (!tables.isEmpty()) {
            Table table = tables.get(0);
            if (table.getPlayers().length() > 0) {
                String[] players = table.getPlayers().split(", ");

                for (String player : players) {
                    playerComboBox.addItem(player);
                }
            }
        }

        int roundIndex = roundComboBox.getSelectedIndex();
        if (roundIndex == list.size() - 1) {
            placeLabel.setVisible(true);
            placeComboBox.setVisible(true);
        } else {
            placeLabel.setVisible(false);
            placeComboBox.setVisible(false);
        }
    }//GEN-LAST:event_roundComboBoxActionPerformed

    private void tableComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableComboBoxActionPerformed
        // TODO add your handling code here:
        Table table = (Table) tableComboBox.getSelectedItem();

        playerComboBox.removeAllItems();
        if (table != null) {
            if (table.getPlayers().length() > 0) {
                String[] players = table.getPlayers().split(", ");

                for (String player : players) {
                    playerComboBox.addItem(player);
                }
            }
        }
    }//GEN-LAST:event_tableComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int dialog = JOptionPane.showConfirmDialog(null, "Weet u zeker dat u de uitslag wilt invoeren?");

        if (dialog == JOptionPane.YES_OPTION) {
            Round round = (Round) roundComboBox.getSelectedItem();
            int roundIndex = roundComboBox.getSelectedIndex();
            Table table = (Table) tableComboBox.getSelectedItem();
            int player = playerComboBox.getSelectedIndex();
            Model placeModel = (Model) placeComboBox.getSelectedItem();
            int place = placeModel.getId();

            // set the winner
            int player_id = 0;
            try {
                List ids = table.getIds();
                if (!ids.isEmpty()) {
                    player_id = Integer.parseInt((String) ids.get(player));
                }
            } catch (Exception e) {
            }

            if (roundIndex < list.size() - 1 && player_id > 0) {
                this.repository.setWinner(player_id, round.getId(), list.get(roundIndex + 1).getId());
            } else {
                this.repository.setTournamentWinner(tournament_id, player_id, round.getId(), place);
            }

            // update rating
            ArrayList<Player> list = this.repository.getPlayerPerTable(round.getId(), table.getTable());
            if (list != null && list.size() > 1) {
                int total = 0;
                int oldRating = 0;
                ArrayList<Player> opponents = new ArrayList<>();
                Player winner = new Player();
                for (Player playerObject : list) {
                    if (playerObject.getWinner() > 0) {
                        winner = playerObject;
                        oldRating = playerObject.getRating();
                    } else {
                        opponents.add(playerObject);
                        total += playerObject.getRating();
                    }
                }

                int result = new Rating(winner.getRating(), total / opponents.size()).calculate();
                winner.setRating(winner.getRating() + result);
                playerRepository.updateRating(winner);
                for (Player playerObject : opponents) {
                    int res = new Rating(oldRating, playerObject.getRating()).calculate();
                    playerObject.setRating(playerObject.getRating() - res);
                    playerRepository.updateRating(playerObject);
                }
            }

            roundComboBox.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox placeComboBox;
    private javax.swing.JLabel placeLabel;
    private javax.swing.JComboBox playerComboBox;
    private javax.swing.JComboBox roundComboBox;
    private javax.swing.JComboBox tableComboBox;
    // End of variables declaration//GEN-END:variables
}
