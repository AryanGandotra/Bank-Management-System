package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class Withdrawl extends JFrame implements ActionListener {

    
    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3, l4;
    String pin;

    Withdrawl(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankmanagementsystem/icons/withdrawl.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(100, 350, 400, 250);
        add(l3);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(53, 39, 136));
        panel_1.setBounds(10, 10, 580, 80);
        add(panel_1);

        l1 = new JLabel("WITHDRAW IN MULTIPLES OF 100");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 10));

        l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 28));

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));

        b1 = new JButton("WITHDRAW");
        b2 = new JButton("BACK");

        setLayout(null);
        
        l2.setBounds(80, 80, 450, 50);
        panel_1.add(l2);

        l1.setBounds(200, 150, 400, 20);
        panel_1.add(l1);

        

        t1.setBounds(140, 180, 330, 40);
        add(t1);

        b1.setBounds(140, 250, 150, 40);
        b1.setBackground(new Color(53, 39, 136));
        b1.setForeground(Color.WHITE);
        add(b1);

        b2.setBounds(320, 250, 150, 40);
        b2.setBackground(new Color(53, 39, 136));
        b2.setForeground(Color.WHITE);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(600, 600);
        getContentPane().setBackground(Color.WHITE);
        setLocation(500, 100);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = t1.getText();
            Date date = new Date();
            if (ae.getSource() == b1) {
                if (t1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                } else {
                    Conn c1 = new Conn();

                    ResultSet rs = c1.s.executeQuery("select * from bank Where pin = '" + pin + "'");
                    int balance = 0;
                    while (rs.next()) {
                        if (rs.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(rs.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }

//                    c1.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                    c1.s.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "')");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error: " + e);
        }

    }

    public static void main(String[] args) {
        new Withdrawl("").setVisible(true);
    }
}
