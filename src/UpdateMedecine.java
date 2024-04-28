import DataBase.Config;
import DataBase.UpdateMedDAO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateMedecine extends JFrame implements ActionListener {
    JPanel pnorth,center;
    JLabel UpdateMed,price,name,MedId,quantity,Company,addQuantity;
    JTextField Jname,Jprice,JMedId,Jquantity,JCompany,JaddQuantity;
    JComboBox<String> role ;
    Border b;
    ImageIcon img;
    URL url;
    JButton update,search;
    UpdateMedecine()
    {
        this.setTitle("Pharmacist Dashboard");
        this.setSize(700, 500);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        UpdateMed=new JLabel("Update Medecine");
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 20);
        UpdateMed.setFont(f);
        pnorth = new JPanel(new BorderLayout()); // Use BorderLayout
        pnorth.setPreferredSize(new Dimension(700, 50));
        b = new LineBorder(Color.white, 2);
        UpdateMed.setHorizontalAlignment(SwingConstants.CENTER);
        pnorth.setBorder(b);
        pnorth.add(UpdateMed, BorderLayout.CENTER);
        this.add(pnorth,BorderLayout.NORTH);

        center=new JPanel(null);
        center.setBackground(Color.WHITE);
        center.setBounds(0, 0, 700, 450);
        this.add(center,BorderLayout.CENTER);


        MedId=new JLabel("Medecine ID");
        MedId.setBounds(180,10,80,23);
        center.add( MedId);

        JMedId=new JTextField();
        JMedId.setBounds(180,30,250,23);
        center.add(JMedId);

        url=getClass().getResource("/images/search.png");
        img=new ImageIcon(url);
        search=new JButton("Search",img);
        search.setBounds(450,33,100,20);
        center.add(search);

        price=new JLabel("Price per unit");
        price.setBounds(400,80,100,23);
        center.add(price);

        Jprice=new JTextField();
        Jprice.setBounds(400,100,250,23);
        center.add(Jprice);

        name=new JLabel("Name");
        name.setBounds(30,80,65,20);
        center.add(name);


        Jname=new JTextField();
        Jname.setBounds(30,120,250,23);
        center.add(Jname);

        Company=new JLabel("Company name");
        Company.setBounds(30,160,90,20);
        center.add(Company);

        JCompany=new JTextField();
        JCompany.setBounds(30,200,250,23);
        center.add(JCompany);

        quantity=new JLabel("Quantity");
        quantity.setBounds(30,250,80,20);
        center.add(quantity);

        Jquantity=new JTextField();
        Jquantity.setBounds(30,280,250,23);
        center.add(Jquantity);

        addQuantity=new JLabel("Add Quantity");
        addQuantity.setBounds(205,300,100,23);
        center.add( addQuantity);

        JaddQuantity=new JTextField();
        JaddQuantity.setBounds(205,330,70,23);
        center.add(JaddQuantity);

        url=getClass().getResource("/images/save.png");
        img=new ImageIcon(url);
        update=new JButton("Update",img);
        update.setBounds(552,130,100,30);
        center.add(update);




       update.addActionListener(this);
       search.addActionListener(this);


        this.setVisible(true);
    }

    /*public void actionPerformed(ActionEvent e) {

        if(e.getSource()==search)
        {
            int medicineID = Integer.parseInt(JMedId.getText());
            UpdateMedDAO dao=new UpdateMedDAO(Config.URL,Config.USERNAME,Config.PASSWORD);
            ResultSet rs=dao.rechrercher(medicineID);
            if(rs==null)
            {
                JOptionPane.showMessageDialog(null, "Medecine Doesn't EXIST!");
            }
            else {
                try {
                    if (rs != null && rs.next()) {
                        try {
                            Jname.setText(rs.getString("name"));
                            Jprice.setText(Double.toString(rs.getDouble("price")));
                            Jquantity.setText(Integer.toString(rs.getInt("quantity")));
                            JCompany.setText(rs.getString("company name"));
                            int JquantityToAdd= Integer.parseInt(JaddQuantity.getText());

                            if(e.getSource()==update)
                            {
                                UpdateMedDAO daoo=new UpdateMedDAO(Config.URL,Config.USERNAME,Config.PASSWORD);
                                daoo.update(rs, JquantityToAdd);

                            }

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }*/
    @Override
    public void actionPerformed(ActionEvent e) {
        ResultSet rs;
        if (e.getSource() == search) {
            String medIdText = JMedId.getText().trim(); // Trim to remove leading/trailing whitespaces
            if (!medIdText.isEmpty()) {
                try {
                    int medicineID = Integer.parseInt(medIdText);
                    UpdateMedDAO dao = new UpdateMedDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                    rs = dao.rechrercher(medicineID);
                    if (rs == null) {
                        JOptionPane.showMessageDialog(null, "Medecine Doesn't Exist!");
                    } else {
                        try {
                            if (rs.next()) {
                                Jname.setText(rs.getString("name"));
                                Jprice.setText(Double.toString(rs.getDouble("price")));
                                Jquantity.setText(Integer.toString(rs.getInt("quantity")));
                                JCompany.setText(rs.getString("company name"));
                                // No need to parse JaddQuantity.getText() here
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer for the medicine ID.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a medicine ID.");
            }
        } else if (e.getSource() == update) {
            try {
                int medicineID = Integer.parseInt(JMedId.getText().trim());
                int quantityToAdd = Integer.parseInt(JaddQuantity.getText().trim());
                UpdateMedDAO dao = new UpdateMedDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                rs = dao.rechrercher(medicineID);

                if (rs.next()) { // Check if ResultSet contains any rows
                    // Retrieve data from ResultSet
                    Jname.setText(rs.getString("name"));
                    Jprice.setText(Double.toString(rs.getDouble("price")));
                    Jquantity.setText(Integer.toString(rs.getInt("quantity")));
                    JCompany.setText(rs.getString("company name"));

                    double pr = rs.getDouble("price");
                    int qt = rs.getInt("quantity");
                    int qtToAdd = Integer.parseInt(JaddQuantity.getText().trim());
                    dao.update(rs, JCompany.getText(), Jname.getText(), pr, qt, qtToAdd);
                } else {
                    JOptionPane.showMessageDialog(null, "Medicine Doesn't Exist!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid integer for the quantity to add.");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }}