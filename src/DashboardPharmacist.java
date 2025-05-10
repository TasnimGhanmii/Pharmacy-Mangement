import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardPharmacist extends JFrame implements ActionListener {
    ResultSet rs;
    JLabel Dashboard;
    JPanel center, pnorth;
    JButton add_med, sell_med, view_med, view_bill, update_med, profile, logOut, Exit;
    ImageIcon img;
    Border b;
    URL URL;
    DashboardPharmacist(ResultSet rs) {
        this.setTitle("Pharmacist Dashboard");
        this.setSize(900, 700);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.darkGray);
        this.setResizable(true);

        this.rs=rs;


        Dashboard = new JLabel("Pharmacist Dashboard", SwingConstants.CENTER); // Center align the text
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

        URL = getClass().getResource("images/addMedicine.png");
        img=new ImageIcon(URL);
        Image original = img.getImage();
        Image resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        add_med=new JButton("add medecine",resizedIcon);
        add_med.setBounds(120,80,250,60);
        center.add(add_med);

        URL = getClass().getResource("images/sellMedicine.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        sell_med=new JButton("Sell medecine",resizedIcon);
        sell_med.setBounds(400,80,250,60);
        center.add(sell_med);

        URL = getClass().getResource("images/viewUser.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        view_med=new JButton("view medecine",resizedIcon);
        view_med.setBounds(120,150,250,60);
        center.add(view_med);

        URL = getClass().getResource("images/viewBill.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        view_bill=new JButton("view Bill",resizedIcon);
        view_bill.setBounds(400,150,250,60);
        center.add(view_bill);

        URL = getClass().getResource("images/updateUser.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        update_med=new JButton("Update medecine",resizedIcon);
        update_med.setBounds(120,220,250,60);
        center.add(update_med);

        URL = getClass().getResource("images/profileDashboard.png");
        img=new ImageIcon(URL);
        original = img.getImage();
        resizedImage = original.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        profile=new JButton("view profile",resizedIcon);
        profile.setBounds(400,220,250,60);
        center.add(profile);

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

        add_med.addActionListener(this);
        view_bill.addActionListener(this);
        view_med.addActionListener(this);
        update_med.addActionListener(this);
        profile.addActionListener(this);
        sell_med.addActionListener(this);
        logOut.addActionListener(this);
        Exit.addActionListener(this);



        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logOut)
        {
            LogIn a= new LogIn();
            dispose();
        }
        if(e.getSource()==Exit)
        {
            dispose();
        }
        if(e.getSource()==add_med)
        {
            AddMedecine a=new AddMedecine();
        }
        if(e.getSource()==sell_med)
        {
            try {
                SellMedecine a= new SellMedecine();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource()==update_med)
        {
            UpdateMedecine a= new UpdateMedecine();
        }
        if(e.getSource()==view_med)
        {
            ViewMed  a= new ViewMed();
        }
        if(e.getSource()==view_bill)
        {
            try {
                ViewBill  a= new ViewBill();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error fetching bills: " + ex.getMessage());

            }
        }
        if(e.getSource()==profile)
        {
            try {
                Profil p=new Profil(rs);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }


    }
}
