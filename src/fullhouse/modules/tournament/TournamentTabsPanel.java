/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.tournament;

import fullhouse.models.Tournament;
import fullhouse.repositories.TournamentDbRepository;

/**
 *
 * @author steve
 */
public class TournamentTabsPanel extends javax.swing.JPanel {
    private final TournamentDbRepository repository = new TournamentDbRepository();

    /**
     * Creates new form TournamentFormPanel
     */
    public TournamentTabsPanel(Tournament tournament) {
        initComponents();
        
        TournamentFormPanel form = new TournamentFormPanel(tournament);
        
        boolean test = true;
        
        jTabbedPane1.addTab("Gegevens", form);
        jTabbedPane1.addTab("Speler inschrijven", new TournamentSignupPanel(tournament));
        jTabbedPane1.addTab("Spelers die nog niet betaald hebben", new TournamentTableLayoutCollectionNotPaidPanel(tournament.getId()));
        if(test)
        {
            jTabbedPane1.addTab("Tafelindeling", new TournamentTableLayoutCollectionPanel());
        } else {
            jTabbedPane1.addTab("Tafelindeling", new TournamentTableLayoutPanel(tournament.getId(), form));
        }
        jTabbedPane1.addTab("Uitslag", new TournamentResultsPanel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
