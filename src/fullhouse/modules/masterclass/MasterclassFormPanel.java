/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.masterclass;

import fullhouse.models.Masterclass;
import fullhouse.models.Player;
import fullhouse.repositories.MasterclassDbRepository;
import fullhouse.repositories.PlayerDbRepository;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author steve
 */
public class MasterclassFormPanel extends javax.swing.JPanel {

    private final MasterclassDbRepository masterclassRepo = new MasterclassDbRepository();
    private Masterclass masterclass;

    /**
     * Creates new form MasteclassFormPanel
     */
    public MasterclassFormPanel() {
        initComponents();
        populateLeaderBox();
    }

    public MasterclassFormPanel(Masterclass masterclass) {
        initComponents();
        populateLeaderBox();
        this.masterclass = masterclass;
        this.setValues();
    }

    public void populateLeaderBox() {
        PlayerDbRepository playerRepo = new PlayerDbRepository();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        ArrayList<Player> leaders = playerRepo.getLeaders();

        for (Player leader : leaders) {
            model.addElement(leader);
        }

        leaderIdBox.setModel(model);
    }

    public Masterclass getValues() {
        Player selectedLeader = (Player) leaderIdBox.getSelectedItem();
        Masterclass masterclass = new Masterclass();
        masterclass.setLeaderId(selectedLeader.getId());
        masterclass.setMinRating(Integer.parseInt(masterclassMinRating.getText()));
        masterclass.setPrice(Double.parseDouble(masterclassPrice.getText()));
        masterclass.setName(masterclassName.getText());
        masterclass.setMaxPlayers(Integer.parseInt(masterclassMaxPlayers.getText()));
        masterclass.setDate(masterclassDate.getText());

        return masterclass;
    }

    public Masterclass getValues(int id) {
        Player selectedLeader = (Player) leaderIdBox.getSelectedItem();
        Masterclass masterclass = new Masterclass();
        masterclass.setId(id);
        masterclass.setLeaderId(selectedLeader.getId());
        masterclass.setMinRating(Integer.parseInt(masterclassMinRating.getText()));
        masterclass.setPrice(Double.parseDouble(masterclassPrice.getText()));
        masterclass.setName(masterclassName.getText());
        masterclass.setMaxPlayers(Integer.parseInt(masterclassMaxPlayers.getText()));
        masterclass.setDate(masterclassDate.getText());
 
        return masterclass;
    }

    public void setValues() {
        for(int i = 0; i<leaderIdBox.getItemCount();i++) {
            Player leader = (Player) leaderIdBox.getItemAt(i);
            if(leader.getId() == this.masterclass.getTeacher().getId()) {
                leaderIdBox.setSelectedIndex(i);
            }
        }
        
        leaderIdBox.setSelectedItem(this.masterclass.getTeacher());
        masterclassMinRating.setText(Integer.toString(this.masterclass.getMinRating()));
        masterclassPrice.setText(Double.toString(this.masterclass.getPrice()));
        masterclassName.setText(this.masterclass.getName());
        masterclassMaxPlayers.setText(Integer.toString(this.masterclass.getMaxPlayers()));
        masterclassDate.setText(this.masterclass.getDate());
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
        masterclassDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        masterclassMinRating = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        masterclassPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        masterclassMaxPlayers = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        leaderIdBox = new javax.swing.JComboBox();
        nameValidationLabel = new javax.swing.JLabel();
        dateValidationLabel = new javax.swing.JLabel();
        masterclassName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        maxPlayersValidationLabel = new javax.swing.JLabel();
        minRatingValidationLabel = new javax.swing.JLabel();
        priceValidationLabel = new javax.swing.JLabel();

        jLabel1.setText("Naam");

        jLabel2.setText("Datum");

        jLabel3.setText("Min rating");

        jLabel5.setText("Prijs");

        jLabel4.setText("Max spelers");

        jLabel6.setText("Gegeven door");

        leaderIdBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        leaderIdBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaderIdBoxActionPerformed(evt);
            }
        });

        jLabel7.setText("(dd-mm-jjjj uu:mm)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5))
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(masterclassDate)
                    .addComponent(masterclassPrice)
                    .addComponent(masterclassMinRating)
                    .addComponent(masterclassMaxPlayers)
                    .addComponent(leaderIdBox, 0, 130, Short.MAX_VALUE)
                    .addComponent(masterclassName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maxPlayersValidationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateValidationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                    .addComponent(nameValidationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(minRatingValidationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceValidationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameValidationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(masterclassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(masterclassDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateValidationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(masterclassPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceValidationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minRatingValidationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(masterclassMinRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(masterclassMaxPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maxPlayersValidationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(leaderIdBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7))
                .addGap(139, 139, 139))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void leaderIdBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaderIdBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leaderIdBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel dateValidationLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox leaderIdBox;
    public javax.swing.JTextField masterclassDate;
    public javax.swing.JTextField masterclassMaxPlayers;
    public javax.swing.JTextField masterclassMinRating;
    public javax.swing.JTextField masterclassName;
    public javax.swing.JTextField masterclassPrice;
    public javax.swing.JLabel maxPlayersValidationLabel;
    public javax.swing.JLabel minRatingValidationLabel;
    public javax.swing.JLabel nameValidationLabel;
    public javax.swing.JLabel priceValidationLabel;
    // End of variables declaration//GEN-END:variables
}
