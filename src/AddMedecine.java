import DataBase.AddMedDAO;
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

public class AddMedecine extends JFrame implements ActionListener {
   private JLabel addMed,MedId,Name,CompanyName,Quantity,Price,JLImage;
    private JTextField JMedId,JName,JCompanyName,JQuantity,JPrice;
    private JButton save;
    private JPanel pnorth,center,image;
    private Border b;
    private ImageIcon img;
    private URL url;

    AddMedecine()
    {
        this.setTitle("ADD MEDECINE");
        this.setSize(700, 500);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        /*this.setIconifiable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setClosable(true);*/
        //dispose on close

        addMed=new JLabel("ADD MEDECINE");
        Font f = new Font("Britannic Bold", Font.CENTER_BASELINE, 20);
        addMed.setFont(f);
        pnorth = new JPanel(new BorderLayout()); // Use BorderLayout
        pnorth.setPreferredSize(new Dimension(700, 50));
        b = new LineBorder(Color.white, 2);
        addMed.setHorizontalAlignment(SwingConstants.CENTER);
        pnorth.setBorder(b);
        pnorth.add(addMed, BorderLayout.CENTER);
        this.add(pnorth,BorderLayout.NORTH);

        center=new JPanel(null);
        center.setBackground(Color.WHITE);
        center.setBounds(0, 0, 700, 450);
        this.add(center,BorderLayout.CENTER);

        MedId=new JLabel("Medecine ID");
        MedId.setBounds(30,25,80,20);
        center.add(MedId);

        JMedId=new JTextField();
        JMedId.setBounds(30,54,250,23);
        center.add(JMedId);

        Name=new JLabel("Medecine Name");
        Name.setBounds(30,100,100,20);
        center.add(Name);

        JName=new JTextField();
        JName.setBounds(30,130,250,23);
        center.add(JName);

        CompanyName=new JLabel("Company Name");
        CompanyName.setBounds(30,175,90,20);
        center.add(CompanyName);

        JCompanyName=new JTextField("");
        JCompanyName.setBounds(30,205,250,23);
        center.add(JCompanyName);

        Quantity=new JLabel("Quantiy");
        Quantity.setBounds(30,250,65,23);
        center.add(Quantity);

        JQuantity=new JTextField();
        JQuantity.setBounds(30,280,250,23);
        center.add(JQuantity);

        Price=new JLabel("Price Per Unit ");
        Price.setBounds(380,25,90,23);
        center.add( Price);

        JPrice=new JTextField();
        JPrice.setBounds(380,54,250,23);
        center.add(JPrice);


        url=getClass().getResource("/images/save.png");
        img=new ImageIcon(url);
        save=new JButton("Save",img);
        save.setBounds(540,85,90,30);
        center.add(save);

        url=getClass().getResource("/images/Symbol.png");
        img=new ImageIcon(url);
        image=new JPanel(new BorderLayout());
        image.setBackground(Color.WHITE);
        Image original = img.getImage();
        Image resizedImage = original.getScaledInstance(260, 240, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        image.setBounds(380, 70, 250, 400);
        JLImage=new JLabel(resizedIcon);
        image.add(JLImage,BorderLayout.CENTER);
        center.add(image);

        save.addActionListener(this);



        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==save)
         {
             int medID= Integer.parseInt(JMedId.getText());
             int qt=Integer.parseInt(JQuantity.getText());
             double pr=Double.parseDouble(JPrice.getText());
             String Medname=JName.getText();
             String Compname=JCompanyName.getText();

             AddMedDAO dao=new AddMedDAO(Config.URL,Config.USERNAME,Config.PASSWORD);
             try {
                 dao.AddMed(medID, qt, pr, Medname, Compname);
                 JOptionPane.showMessageDialog(null, "Medecine added SUCCESSFULLY!");
                 JMedId.setText("");
                  JName.setText("");
                 JCompanyName.setText("");
                 JQuantity.setText("");
                 JPrice.setText("");

             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "ADD ERROR !");
                 throw new RuntimeException(ex);
             }
         }
    }
}
