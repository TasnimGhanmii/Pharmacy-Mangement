import DataBase.AddUserDAO;
import DataBase.Config;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;

public class AddUser extends JFrame implements ActionListener {
    JLabel addUser,email,name,username,dob,number,password,adress,userRole;
    JButton save;
    JPanel pnorth,center;
    Border b;
    JComboBox<String> role ;
    JTextField Jname,Jemail,Jusername,Jdob,Jnumber,Jpassword,Jadress;
    ImageIcon img;
    URL url;

    AddUser()
    {
        this.setTitle("Pharmacist Dashboard");
        this.setSize(700, 500);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        /*this.setIconifiable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setClosable(true);*/
        //dispose on close

        addUser=new JLabel("ADD USER");
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 20);
        addUser.setFont(f);
        pnorth = new JPanel(new BorderLayout()); // Use BorderLayout
        pnorth.setPreferredSize(new Dimension(700, 50));
        b = new LineBorder(Color.white, 2);
        addUser.setHorizontalAlignment(SwingConstants.CENTER);
        pnorth.setBorder(b);
        pnorth.add(addUser, BorderLayout.CENTER);
        this.add(pnorth,BorderLayout.NORTH);

        center=new JPanel(null);
        center.setBackground(Color.WHITE);
        center.setBounds(0, 0, 700, 450);
        this.add(center,BorderLayout.CENTER);


        userRole=new JLabel("User Role");
        userRole.setBounds(30,30,65,20);
        center.add(userRole);

        role=new JComboBox<>();
        role.addItem("Admin");
        role.addItem("Pharmacist");
        role.setSelectedIndex(0); //par def option 1 selectionnée
        role.setBounds(30,55,250,20);
        center.add(role);

        name=new JLabel("Name");
        name.setBounds(30,100,65,20);
        center.add(name);

        Jname=new JTextField();
        Jname.setBounds(30,125,250,23);
        center.add(Jname);

        dob=new JLabel("Date Of Birth");
        dob.setBounds(30,170,80,20);
        center.add(dob);

        Jdob=new JTextField();
        Jdob.setBounds(30,195,250,23);
        center.add(Jdob);

        number=new JLabel("Phone Number");
        number.setBounds(30,242,90,20);
        center.add(number);

        Jnumber=new JTextField();
        Jnumber.setBounds(30,264,250,23);
        center.add(Jnumber);

        email=new JLabel("Email");
        email.setBounds(380,25,65,23);
        center.add(email);

        Jemail=new JTextField();
        Jemail.setBounds(380,54,250,23);
        center.add(Jemail);

        username=new JLabel("Username");
        username.setBounds(380,96,65,23);
        center.add( username);

        Jusername=new JTextField();
        Jusername.setBounds(380,124,250,23);
        center.add(Jusername);

        password=new JLabel("Password");
        password.setBounds(380,169,65,23);
        center.add( password);

        Jpassword=new JTextField();
        Jpassword.setBounds(380,196,250,23);
        center.add(Jpassword);

        adress=new JLabel("Address");
        adress.setBounds(380,241,65,23);
        center.add( adress);

        Jadress=new JTextField();
        Jadress.setBounds(380,266,250,23);
        center.add(Jadress);

        url=getClass().getResource("/images/save.png");
        img=new ImageIcon(url);
        save=new JButton("Save",img);
        save.setBounds(540,291,90,30);
        center.add(save);

         save.addActionListener(this);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==save)
        {
            String Role=(String) role.getSelectedItem();
            String Name=Jname.getText();
            String DateOfBirth=Jdob.getText();
            String phone=Jnumber.getText();
            String mail= Jemail.getText();
            String user= Jusername.getText();
            String pass=Jpassword.getText();
            String add= Jadress.getText();
            AddUserDAO dao=new AddUserDAO(Config.URL,Config.USERNAME,Config.PASSWORD);
            try {
                dao.addUser(mail,Name,user,DateOfBirth,phone,pass,add,Role);
                JOptionPane.showMessageDialog(null, "User added successfully!");
                Jname.setText("");
                Jemail.setText("");
                Jusername.setText("");
                Jdob.setText("");
                Jnumber.setText("");
                Jpassword.setText("");
                Jadress.setText("");

            } catch (SQLException ex) {
                throw new RuntimeException(ex);


            }

        }
    }
}
