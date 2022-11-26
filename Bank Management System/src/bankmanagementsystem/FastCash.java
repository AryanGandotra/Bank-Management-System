package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JLabel l1, l2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JTextField t1;
    String pin;

    FastCash(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankmanagementsystem/icons/withdrawl.jpg"));
        Image i2 = i1.getImage().getScaledInstance(380, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(120, 400, 380, 200);
        add(l3);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(53, 39, 136));
        panel_1.setBounds(10, 10, 580, 80);
        add(panel_1);

        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 25));

        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        b7 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(120, 80, 700, 40);
        panel_1.add(l1);

        b1.setBounds(100, 120, 150, 40);
        b1.setBackground(new Color(53, 39, 136));
        b1.setForeground(Color.WHITE);
        add(b1);

        b2.setBounds(340, 120, 150, 40);
        b2.setBackground(new Color(53, 39, 136));
        b2.setForeground(Color.WHITE);
        add(b2);

        b3.setBounds(100, 220, 150, 40);
        b3.setBackground(new Color(53, 39, 136));
        b3.setForeground(Color.WHITE);
        add(b3);

        b4.setBounds(340, 220, 150, 40);
        b4.setBackground(new Color(53, 39, 136));
        b4.setForeground(Color.WHITE);
        add(b4);

        b5.setBounds(100, 320, 150, 40);
        b5.setBackground(new Color(53, 39, 136));
        b5.setForeground(Color.WHITE);
        add(b5);

        b6.setBounds(340, 320, 150, 40);
        b6.setBackground(new Color(53, 39, 136));
        b6.setForeground(Color.WHITE);
        add(b6);

        b7.setBounds(10, 550, 100, 40);
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

        setSize(600, 600);
        getContentPane().setBackground(Color.WHITE);
        setLocation(450, 100);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = ((JButton) ae.getSource()).getText().substring(3); //k
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pin + "'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            String num = "17";
            if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insuffient Balance");
                return;
            }

            if (ae.getSource() == b7) {
                this.setVisible(false);
                new Transactions(pin).setVisible(true);
            } else {
                Date date = new Date();
                c.s.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Withdrawl', '" + amount + "')");
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}
