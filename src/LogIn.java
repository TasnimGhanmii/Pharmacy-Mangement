import DataBase.Config;
import DataBase.Connexion;
import DataBase.LogInDAO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventListener;

public class LogIn extends JFrame implements ActionListener {
    JButton okay,cancel;
    JLabel email,password,login,welcome,ImageLabel;
    JTextField jemail;
    JPasswordField jpassword;
    JPanel pnorth,center,ImgPanel;
    Border border;
    ImageIcon img;
    ResultSet rs;



LogIn()
{

    this.setTitle("LogIN");
    this.setSize(700,700);
    this.setBackground(Color.white);
    this.setLayout(new BorderLayout());
    this.setResizable(true);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    //welcome
    pnorth=new JPanel();
    pnorth.setLayout(null);
    pnorth.setPreferredSize(new Dimension(200,100));
    pnorth.setBackground(Color.green);
    this.getContentPane().add(pnorth);
    //this.add(pnorth,BorderLayout.NORTH);

    welcome=new JLabel("<html>WELCOME TO<br>PHARMACY<br>MANAGEMENT<br>SYSTEM</html>");
    Font font=new Font("Copperplate Gothic Bold",Font.BOLD,18);
    welcome.setFont(font);
    welcome.setForeground(Color.BLACK);
    welcome.setBounds(28,250,500,100);
    pnorth.add(welcome);

    //login
    center=new JPanel();
    center.setLayout(null);
    border = new LineBorder(Color.BLACK, 3);
    center.setBorder(border);

    login=new JLabel("LogIn");
    Font Logfont=new Font("Britannic Bold",Font.BOLD,20);
    login.setFont(Logfont);
    login.setForeground(Color.BLACK);
    login.setBounds(220,0,100,50);
    center.add(login);

    email=new JLabel("Email");
    Font Emailfont=new Font("Arial Rounded MT Bold",Font.BOLD,15);
    email.setFont(Emailfont);
    email.setForeground(Color.BLACK);
    email.setBounds(40,100,50,50);
    center.add(email);

    jemail=new JTextField();
    jemail.setBounds(40,150,300,25);
    Color color=new Color(208, 246, 208, 255);
    jemail.setBackground(color);
    center.add(jemail);

    password=new JLabel("password");
    password.setFont(Emailfont);
    password.setForeground(Color.BLACK);
    password.setBounds(40,170,100,50);
    center.add(password);

    jpassword=new JPasswordField();
    jpassword.setBounds(40,220,300,25);
    jpassword.setBackground(color);
    /*jpassword.addKeyListener(new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            e.setKeyChar('*');
        }
    });*/
    center.add(jpassword);

    okay=new JButton("ok");
    okay.setBounds(40,260,300,27);
    Color OkayColor=new Color(21, 23, 21, 255);
    okay.setBackground(OkayColor);
    okay.setForeground(Color.white);
    center.add(okay);

    cancel=new JButton("cancel");
    cancel.setBounds(40,296,300,27);
    Color Cancelcolor=new Color(234, 13, 13, 255);
    cancel.setBackground(Cancelcolor);
    cancel.setForeground(Color.white);
    center.add(cancel);

    URL imageUrl = getClass().getResource("/images/PharmacyLogo.png");

    img=new ImageIcon(imageUrl);
    ImageLabel=new JLabel(img);
    ImgPanel=new JPanel(new BorderLayout());
    ImgPanel.add(ImageLabel,BorderLayout.CENTER);
    ImgPanel.setPreferredSize(new Dimension(300,300));
    ImgPanel.setBounds(60,350,300,300);
    center.add(ImgPanel);

    imageUrl = getClass().getResource("/images/medecine.png");
    img=new ImageIcon(imageUrl);
    ImageLabel=new JLabel(img);
    pnorth.add(ImageLabel,BorderLayout.NORTH);



    this.add(pnorth,BorderLayout.WEST);
    this.add(center,BorderLayout.CENTER);


    cancel.addActionListener(this);
    okay.addActionListener(this);

    this.setVisible(true);

}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            dispose();
        }
        if (e.getSource() == okay) {
            LogInDAO dao = new LogInDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
            String email = jemail.getText();
            String password = jpassword.getText();

            try {
                 rs = dao.log(email, password);
                if (rs == null || !rs.next()) {
                    JOptionPane.showMessageDialog(null, "Invalid credentials or role");
                } else {
                    String role = rs.getString("role");


                    if ("Admin".equals(role)) {
                        DashboardAdmin d = new DashboardAdmin(rs);
                        dispose();
                    } else if ("Pharmacist".equals(role)) {
                        DashboardPharmacist d = new DashboardPharmacist(rs);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Unknown role: " + role);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


}
