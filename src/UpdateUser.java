import DataBase.Config;
import DataBase.UpdateMedDAO;
import DataBase.UpdateUserDAO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateUser extends JFrame implements ActionListener {
    JPanel pnorth, center;
    JLabel UpdateUser, email, name, username, dob, number, adress, userRole;
    JTextField Jname, Jemail, Jusername, Jdob, Jnumber, Jadress;
    JComboBox<String> role;
    Border b;
    ImageIcon img;
    URL url;
    JButton update, search;

    UpdateUser() {
        this.setTitle("Pharmacist Dashboard");
        this.setSize(700, 500);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        UpdateUser = new JLabel("Update User");
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 20);
        UpdateUser.setFont(f);
        pnorth = new JPanel(new BorderLayout()); // Use BorderLayout
        pnorth.setPreferredSize(new Dimension(700, 50));
        b = new LineBorder(Color.white, 2);
        UpdateUser.setHorizontalAlignment(SwingConstants.CENTER);
        pnorth.setBorder(b);
        pnorth.add(UpdateUser, BorderLayout.CENTER);
        this.add(pnorth, BorderLayout.NORTH);

        center = new JPanel(null);
        center.setBackground(Color.WHITE);
        center.setBounds(0, 0, 700, 450);
        this.add(center, BorderLayout.CENTER);


        username = new JLabel("Username");
        username.setBounds(180, 10, 65, 23);
        center.add(username);

        Jusername = new JTextField();
        Jusername.setBounds(180, 30, 250, 23);
        center.add(Jusername);

        url = getClass().getResource("/images/search.png");
        img = new ImageIcon(url);
        search = new JButton("Search", img);
        search.setBounds(450, 33, 100, 20);
        center.add(search);

        userRole = new JLabel("User Role");
        userRole.setBounds(30, 80, 65, 20);
        center.add(userRole);

        role = new JComboBox<>();
        role.addItem("Admin");
        role.addItem("Pharmacist");
        role.setSelectedIndex(0); //par def option 1 selectionnée
        role.setBounds(30, 100, 250, 20);
        center.add(role);

        email = new JLabel("Email");
        email.setBounds(400, 80, 65, 23);
        center.add(email);

        Jemail = new JTextField();
        Jemail.setBounds(400, 100, 250, 23);
        center.add(Jemail);

        name = new JLabel("Name");
        name.setBounds(30, 160, 65, 20);
        center.add(name);


        Jname = new JTextField();
        Jname.setBounds(30, 180, 250, 23);
        center.add(Jname);

        number = new JLabel("Phone Number");
        number.setBounds(400, 160, 90, 20);
        center.add(number);

        Jnumber = new JTextField();
        Jnumber.setBounds(400, 180, 250, 23);
        center.add(Jnumber);

        dob = new JLabel("Date Of Birth");
        dob.setBounds(30, 250, 80, 20);
        center.add(dob);

        Jdob = new JTextField();
        Jdob.setBounds(30, 280, 250, 23);
        center.add(Jdob);

        adress = new JLabel("Address");
        adress.setBounds(400, 250, 65, 23);
        center.add(adress);

        Jadress = new JTextField();
        Jadress.setBounds(400, 280, 250, 23);
        center.add(Jadress);

        url = getClass().getResource("/images/save.png");
        img = new ImageIcon(url);
        update = new JButton("Update", img);
        update.setBounds(552, 320, 100, 30);
        center.add(update);


        search.addActionListener(this);
        update.addActionListener(this);


        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ResultSet rs;
        if (e.getSource() == search) {
            String username = Jusername.getText().trim();
            if (!username.isEmpty()) {
                try {
                    UpdateUserDAO dao = new UpdateUserDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                    rs = dao.rechercher(username);
                    if (rs == null) {
                        JOptionPane.showMessageDialog(null, "Username Doesn't Exist!");
                    } else {
                        try {
                            if (rs.next()) {
                                Jname.setText(rs.getString("name"));
                                Jemail.setText(rs.getString("email"));
                                Jdob.setText(rs.getString("dob"));
                                Jadress.setText(rs.getString("address"));
                                Jnumber.setText(rs.getString("phone"));
                                String userRole = rs.getString("role");
                                role.setSelectedItem(userRole);
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } catch (StringIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Error occurred: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a Username.");
            }
        } else if (e.getSource() == update) {
            try {
                String usernameToUpdate = Jusername.getText();
                String name = Jname.getText();
                String email = Jemail.getText();
                String dob = Jdob.getText();
                String address = Jadress.getText();
                String phone = Jnumber.getText();
                String userRole = (String) role.getSelectedItem();

                UpdateUserDAO dao = new UpdateUserDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                dao.update(userRole, name, email, phone, dob, address, usernameToUpdate);


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }}