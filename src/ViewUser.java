import DataBase.Config;
import DataBase.ViewMedDAO;
import DataBase.ViewUserDAO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewUser extends JFrame {
    JPanel pnorth,psouth;
    JLabel delete,ViewUser;
    JTable table;
    Border b;
    JScrollPane scrollPane;

    ViewUser()
    {
        this.setTitle("View User");
        this.setSize(800,800);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        delete = new JLabel("click twice to delete user");
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 20);
        delete.setFont(f);
        psouth= new JPanel(new BorderLayout()); // Use BorderLayout
        psouth.setPreferredSize(new Dimension(700, 30));
        b = new LineBorder(Color.white, 2);
        delete.setHorizontalAlignment(SwingConstants.CENTER);
        psouth.setBorder(b);
        psouth.add(delete, BorderLayout.CENTER);
        this.add(psouth, BorderLayout.SOUTH);

        ViewUser = new JLabel("View User");
        Font f1 = new Font("Britannic Bold", Font.CENTER_BASELINE, 30);
        ViewUser.setFont(f1);
        pnorth = new JPanel(new BorderLayout()); // Use BorderLayout
        pnorth.setPreferredSize(new Dimension(700, 50));
        b = new LineBorder(Color.white, 2);
        ViewUser.setHorizontalAlignment(SwingConstants.CENTER);
        pnorth.setBorder(b);
        pnorth.add(ViewUser, BorderLayout.CENTER);
        this.add(pnorth, BorderLayout.NORTH);

        scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create the table
        table = new JTable();

        // Fetch data from the database and populate the table
        ViewUserDAO d=new ViewUserDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
        DefaultTableModel model = d.fetchUserData();

        // Set columns non-editable
        TableColumnModel columnModel = table.getColumnModel();
        table.setModel(model);
        scrollPane.setViewportView(table);
        this.add(scrollPane,BorderLayout.CENTER);
        InaccessibleCellEditor in=new InaccessibleCellEditor();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellEditor(in);

        }




        // Add mouse listener to delete user on double-click
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String Username = (String) table.getValueAt(selectedRow, 7); // Assuming ID is in the first column
                        ViewUserDAO dao=new ViewUserDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                        boolean deleted = dao.deleteUserByUsername( Username);
                        if (deleted) {
                            ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
                            JOptionPane.showMessageDialog(ViewUser.this, "User deleted successfully!");
                        } else {
                            JOptionPane.showMessageDialog(ViewUser.this, "Failed to delete User!");
                        }
                    }
                }
            }
        });




        this.setVisible(true);
    }


}
