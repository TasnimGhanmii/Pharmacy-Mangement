import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;


public class InaccessibleCellEditor extends DefaultCellEditor {

    public InaccessibleCellEditor() {
        super(new JTextField());
    }


    public boolean isCellEditable(int row, int column) {
        return false; // Make all cells uneditable
    }

    @Override
    public boolean isCellEditable(java.util.EventObject e) {
        return false; // Make all cells uneditable
    }



    // Override other methods if necessary
}
