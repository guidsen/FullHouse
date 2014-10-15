/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.masterclass;

import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author steve
 */
public class MasterclassCollectionPanel extends javax.swing.JPanel {

    /**
     * Creates new form MasterclassCollectionPanel
     */
    public MasterclassCollectionPanel() {
        initComponents();
        
        TableCellRenderer r = MasterclassCollectionTable.getTableHeader().getDefaultRenderer();
        JLabel l = (JLabel) r;
        l.setHorizontalAlignment(JLabel.LEFT);
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
        MasterclassCollectionTable = new javax.swing.JTable();

        MasterclassCollectionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Noobs les geven", "2 januari 2015", "5", "800"},
                {"Tips voor de pros", "19 januari 2015", "9", "1400"}
            },
            new String [] {
                "Naam", "Datum", "Aantal inschrijvingen", "Minimum rating"
            }
        ));
        jScrollPane1.setViewportView(MasterclassCollectionTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable MasterclassCollectionTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
