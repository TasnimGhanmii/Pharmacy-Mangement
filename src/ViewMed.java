import DataBase.Config;
import DataBase.ViewMedDAO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewMed extends JFrame {
    JPanel pnorth,psouth;
    JLabel delete,ViewMed;
    JTable table;
    Border b;
    JScrollPane scrollPane;

    ViewMed()
    {
        this.setTitle("View Medecine");
        this.setSize(800,800);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        delete = new JLabel("click twice to delete medecine");
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 20);
        delete.setFont(f);
        psouth= new JPanel(new BorderLayout()); // Use BorderLayout
        psouth.setPreferredSize(new Dimension(700, 30));
        b = new LineBorder(Color.white, 2);
        delete.setHorizontalAlignment(SwingConstants.CENTER);
        psouth.setBorder(b);
        psouth.add(delete, BorderLayout.CENTER);
        this.add(psouth, BorderLayout.SOUTH);

        ViewMed = new JLabel("View Medecine");
        Font f1 = new Font("Britannic Bold", Font.CENTER_BASELINE, 30);
        ViewMed.setFont(f1);
        pnorth = new JPanel(new BorderLayout()); // Use BorderLayout
        pnorth.setPreferredSize(new Dimension(700, 50));
        b = new LineBorder(Color.white, 2);
        ViewMed.setHorizontalAlignment(SwingConstants.CENTER);
        pnorth.setBorder(b);
        pnorth.add(ViewMed, BorderLayout.CENTER);
        this.add(pnorth, BorderLayout.NORTH);

        scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create the table
        table = new JTable();
        scrollPane.setViewportView(table);
        // Add double-click listener to the table for deletion
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0); // Assuming ID is in the first column
                        ViewMedDAO dao=new ViewMedDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                        boolean deleted = dao.deleteMedicineById(id);
                        if (deleted) {
                            ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
                            JOptionPane.showMessageDialog(ViewMed.this, "Medicine deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(ViewMed.this, "Failed to delete medicine!");
                        }
                    }
                }
            }
        });
        this.add(scrollPane,BorderLayout.CENTER);



        ViewMedDAO d=new ViewMedDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
        DefaultTableModel model = d.fetchMedicineData();

        TableColumnModel columnModel = table.getColumnModel();
        table.setModel(model);

        InaccessibleCellEditor in=new InaccessibleCellEditor();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellEditor(in);


        }





        this.setVisible(true);


    }

    public boolean isCellEditable(int row,int column)
    {
    return false;
    }




}
