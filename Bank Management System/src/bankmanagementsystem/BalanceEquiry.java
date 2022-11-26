package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

class BalanceEnquiry extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankmanagementsystem/icons/balance.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(250, 300, 300, 300);
        add(l3);
        
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(53, 39, 136));
        panel_1.setBounds(10, 10, 580, 80);
        add(panel_1);

        l1 = new JLabel();
        l1.setForeground(new Color(53, 39, 136));
        l1.setFont(new Font("System", Font.BOLD, 20));
       
        

        l2 = new JLabel();
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 30));
        l2.setText("Check Your Balance");
        panel_1.add(l2);

        b1 = new JButton("BACK");

        setLayout(null);

        l2.setBounds(150, 40, 400, 35);
        l1.setBounds(80, 200, 450, 35);
        add(l1);

        b1.setBounds(10, 550, 150, 40);
        b1.setBackground(new Color(53, 39, 136));
        b1.setForeground(Color.WHITE);
        add(b1);
        int balance = 0;
        try {
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '" + pin + "'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
        }

        l1.setText("Your Current Account Balance is Rs "+balance);

        b1.addActionListener(this);

        setSize(600, 600);
        setUndecorated(true);
        getContentPane().setBackground(Color.WHITE);
        setLocation(450, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}
