import DataBase.Config;
import DataBase.ProfileDAO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Profil extends JFrame implements ActionListener {
    ResultSet rs;
    ImageIcon img;
    JButton update;
    JLabel name,number,email,adress,profile,image;
    JTextField Jname,Jnumber,Jemail,Jadress;
    JPanel pnorth,center;
    URL url;
    Border b;
    Profil(ResultSet rs) throws SQLException {
        this.setTitle("Profile");
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);
        this.setResizable(true);

        this.rs=rs;

        pnorth=new JPanel(new BorderLayout());
        pnorth.setPreferredSize(new Dimension(700, 50));

        profile=new JLabel("profil");
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 40);
        profile.setFont(f);
        b = new LineBorder(Color.white, 2);
        profile.setHorizontalAlignment(SwingConstants.CENTER);
        pnorth.setBorder(b);
        pnorth.add(profile, BorderLayout.CENTER);
        this.add(pnorth,BorderLayout.NORTH);

        center=new JPanel(null);
        center.setPreferredSize(new Dimension(700,450));
        center.setBackground(Color.WHITE);
        this.add(center, BorderLayout.CENTER);

        url=getClass().getResource("/images/profile.png");
        img=new ImageIcon(url);
        image=new JLabel(img);
        image.setBounds(30,100,180,100);
        center.add(image);

        name=new JLabel("Name");
        name.setBounds(380,25,65,23);
        center.add(name);

        Jname=new JTextField();
        Jname.setBounds(380,54,250,23);
        center.add(Jname);

        number=new JLabel("Phone Number");
        number.setBounds(380,87,90,20);
        center.add(number);

        Jnumber=new JTextField();
        Jnumber.setBounds(380,114,250,23);
        center.add(Jnumber);

        email=new JLabel("Email");
        email.setBounds(380,150,65,20);
        center.add(email);

        Jemail=new JTextField();
        Jemail.setBounds(380,174,250,23);
        center.add(Jemail);

        adress=new JLabel("Address");
        adress.setBounds(380,200,65,23);
        center.add( adress);

        Jadress=new JTextField();
        Jadress.setBounds(380,229,250,23);
        center.add(Jadress);

        url=getClass().getResource("/images/save.png");
        img=new ImageIcon(url);
        update=new JButton("update",img);
        update.setBounds(510,260,120,30);
        center.add(update);

        update.addActionListener(this);


        Jname.setText(rs.getString("name"));
        Jnumber.setText(rs.getString("phone"));
        Jemail.setText(rs.getString("email"));
        Jadress.setText(rs.getString("address"));



        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==update)
         {
             ProfileDAO dao=new ProfileDAO(Config.URL,Config.USERNAME,Config.PASSWORD);

             try {
                 dao.update(Jname.getText(),Jnumber.getText(),Jemail.getText(),Jadress.getText());

             } catch (SQLException ex) {
                 throw new RuntimeException(ex);
             }
         }
    }
}
