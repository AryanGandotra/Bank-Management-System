package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.JPanel;

public class Transactions extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    Transactions(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankmanagementsystem/icons/house.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(550, 100, 200, 150);
        add(l2);
        
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("bankmanagementsystem/icons/trans_2.png"));
        Image i5 = i4.getImage().getScaledInstance(550, 270, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l6 = new JLabel(i6);
        l6.setBounds(200, 550, 550, 270);
        add(l6);

        l1 = new JLabel("Please Select Your Transaction");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 40));

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(53, 39, 136));
        panel_1.setBounds(10, 10, 880, 80);
        add(panel_1);

        b1 = new JButton("DEPOSIT");
        b2 = new JButton("CASH WITHDRAWL");
        b3 = new JButton("FAST CASH");
        b4 = new JButton("MINI STATEMENT");
        b5 = new JButton("PIN CHANGE");
        b6 = new JButton("BALANCE ENQUIRY");
        b7 = new JButton("EXIT");

        setLayout(null);

        l1.setBounds(50, 120, 700, 35);
        panel_1.add(l1);

        JLabel l = new JLabel();
        l.setText("The Most Transparent ");
        l.setForeground(new Color(53, 39, 136));
        l.setFont(new Font("Raleway", Font.BOLD, 30));
        l.setBounds(140, 150, 600, 60);
        add(l);

        JLabel l1 = new JLabel();
        l1.setText("& Secure Banking Ever");
        l1.setForeground(new Color(53, 39, 136));
        l1.setFont(new Font("Raleway", Font.BOLD, 30));
        l1.setBounds(140, 200, 600, 60);
        add(l1);

        b1.setBounds(180, 330, 250, 50);
        b1.setBackground(new Color(53, 39, 136));
        b1.setForeground(Color.WHITE);
        add(b1);

        b2.setBounds(450, 330, 250, 50);
        b2.setBackground(new Color(53, 39, 136));
        b2.setForeground(Color.WHITE);
        add(b2);

        b3.setBounds(180, 430, 250, 50);

        b3.setBackground(new Color(53, 39, 136));
        b3.setForeground(Color.WHITE);
        add(b3);

        b4.setBounds(450, 430, 250, 50);

        b4.setBackground(new Color(53, 39, 136));
        b4.setForeground(Color.WHITE);
        add(b4);

        b5.setBounds(180, 530, 250, 50);

        b5.setBackground(new Color(53, 39, 136));
        b5.setForeground(Color.WHITE);
        add(b5);

        b6.setBounds(450, 530, 250, 50);

        b6.setBackground(new Color(53, 39, 136));
        b6.setForeground(Color.WHITE);
        add(b6);
        
        b7.setBounds(10, 740, 100, 50);
        b7.setBackground(new Color(53, 39, 136));
        b7.setForeground(Color.WHITE);
        add(b7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setSize(900, 800);
        getContentPane().setBackground(Color.WHITE);
        setLocation(350, 10);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            setVisible(false);
            new Deposit(pin).setVisible(true);
        } else if (ae.getSource() == b2) {
            setVisible(false);
            new Withdrawl(pin).setVisible(true);
        } else if (ae.getSource() == b3) {
            setVisible(false);
            new FastCash(pin).setVisible(true);
        } else if (ae.getSource() == b4) {
            new MiniStatement(pin).setVisible(true);
        } else if (ae.getSource() == b5) {
            setVisible(false);
            new Pin(pin).setVisible(true);
        } else if (ae.getSource() == b6) {
            this.setVisible(false);
            new BalanceEnquiry(pin).setVisible(true);
        } else if (ae.getSource() == b7) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Transactions("").setVisible(true);
    }
}
