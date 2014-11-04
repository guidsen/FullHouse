/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.player;

import fullhouse.repositories.PlayerDbRepository;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Guido
 */
public class PlayerCollectionPanel extends javax.swing.JPanel {

    private PlayerDbRepository repository = new PlayerDbRepository();

    /**
     * Creates new form PlayerCollectionPanel1
     */
    public PlayerCollectionPanel() {
        initComponents();

        TableCellRenderer r = playerCollectionTable.getTableHeader().getDefaultRenderer();
        JLabel l = (JLabel) r;
        l.setHorizontalAlignment(JLabel.LEFT);
        
        playerCollectionTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        this.repository.collection(playerCollectionTable);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        playerCollectionTable = new javax.swing.JTable();

        playerCollectionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Naam", "Email", "Rating", "Toernooi deelnamens", "Gewonnen inleggeld", "Te betalen bedrag"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        playerCollectionTable.setAlignmentX(2.0F);
        jScrollPane1.setViewportView(playerCollectionTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable playerCollectionTable;
    // End of variables declaration//GEN-END:variables
}
