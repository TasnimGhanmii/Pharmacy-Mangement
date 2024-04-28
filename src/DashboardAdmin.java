import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class DashboardAdmin extends JFrame implements ActionListener {
    JLabel Dashboard,imgLabel;
    JPanel center, pnorth,image;
    JButton add_user, Profile, view_user, logOut, Exit,update_user;
    ImageIcon img;
    Border b;
    URL URL;
    DashboardAdmin()  {
        this.setTitle("Admin Dashboard");
        this.setSize(900, 700);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.darkGray);
        this.setResizable(true);

        Dashboard = new JLabel("Admin Dashboard", SwingConstants.CENTER); // Center align the text
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 20);
        Dashboard.setFont(f);
        Dashboard.setForeground(Color.white);

        pnorth = new JPanel(new BorderLayout()); // Use BorderLayout
        pnorth.setBackground(Color.darkGray);
        pnorth.setPreferredSize(new Dimension(900, 50));
        b = new LineBorder(Color.white, 2);
        pnorth.setBorder(b);
        pnorth.add(Dashboard, BorderLayout.CENTER);

        // Add the pnorth panel to the JFrame's content pane
        this.add(pnorth, BorderLayout.NORTH);

        center = new JPanel();
        center.setLayout(null);
        center.setBackground(Color.darkGray);
        center.setPreferredSize(new Dimension(900, 650));
        this.add(center, BorderLayout.CENTER);

        URL = getClass().getResource("images/addUser.png");
        img=new ImageIcon(URL);
        Image original = img.getImage();
        Image resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        add_user=new JButton("add user",resizedIcon);
        add_user.setBounds(120,80,250,60);
        center.add(add_user);

        URL = getClass().getResource("images/profileDashboard.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        Profile=new JButton("profile",resizedIcon);
        Profile.setBounds(400,80,250,60);
        center.add(Profile);

        URL = getClass().getResource("images/viewUser.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        view_user=new JButton("view user",resizedIcon);
        view_user.setBounds(120,150,250,60);
        center.add(view_user);



        URL = getClass().getResource("images/logout.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        logOut=new JButton("Logout",resizedIcon);
        logOut.setBounds(120,400,250,60);
        center.add(logOut);

        URL = getClass().getResource("images/exit.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        Exit=new JButton("Exit",resizedIcon);
        Exit.setBounds(400,400,250,60);
        center.add(Exit);

        URL = getClass().getResource("images/updateUser.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        update_user=new JButton("update User",resizedIcon);
        update_user.setBounds(400,150,250,60);
        center.add(update_user);



        add_user.addActionListener(this);
        Profile.addActionListener(this);
        view_user.addActionListener(this);
        update_user.addActionListener(this);
        Exit.addActionListener(this);
        logOut.addActionListener(this);




        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==add_user)
        {
            AddUser a=new AddUser();
        }
        if(e.getSource()==update_user)
        {
            UpdateUser a= new UpdateUser();
        }
        if(e.getSource()==Profile)
        {
            Profil a= new Profil();
        }
        if(e.getSource()==view_user)
        {
            ViewUser a= new ViewUser();
        }
        if(e.getSource()==logOut)
        {
            LogIn a= new LogIn();
            dispose();
        }
        if(e.getSource()==Exit)
        {
            dispose();
        }

    }
}
