/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fullhouse.modules.masterclass;

import fullhouse.repositories.MasterclassDbRepository;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author steve
 */
public class MasterclassCollectionPanel extends javax.swing.JPanel {

    private MasterclassDbRepository repository = new MasterclassDbRepository();
    
    /**
     * Creates new form MasterclassCollectionPanel
     */
    public MasterclassCollectionPanel() {
        initComponents();
        
        TableCellRenderer r = masterclassCollectionTable.getTableHeader().getDefaultRenderer();
        JLabel l = (JLabel) r;
        l.setHorizontalAlignment(JLabel.LEFT);
        
        masterclassCollectionTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        this.repository.collection(masterclassCollectionTable);
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
        masterclassCollectionTable = new javax.swing.JTable();

        masterclassCollectionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Naam", "Datum", "Aantal inschrijvingen", "Minimum rating"
            }
        ));
        jScrollPane1.setViewportView(masterclassCollectionTable);

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
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable masterclassCollectionTable;
    // End of variables declaration//GEN-END:variables
}
