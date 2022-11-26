package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;
  
    Login(){
        setTitle("Fortunate Banking - Bank Management System");
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("bankmanagementsystem/icons/bg_1.jpg"));
        Image i5 = i4.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel i7 = new JLabel(i6);
        i7.setBounds(400, 0, 400, 600);
        add(i7);

        JLabel l = new JLabel();
        l.setText("BANKING");
        l.setForeground(Color.WHITE );
        l.setFont(new Font("Raleway", Font.BOLD, 40));
        l.setBounds(150,340,600,60);
        i7.add(l);
        
        JLabel l1 = new JLabel();
        l1.setText("AT YOUR");
        l1.setForeground(Color.WHITE );
        l1.setFont(new Font("Raleway", Font.BOLD, 40));
        l1.setBounds(150,390,600,60);
        i7.add(l1);
        
        JLabel l2 = new JLabel();
        l2.setText("FINGERTIPS");
        l2.setForeground(Color.WHITE );
        l2.setFont(new Font("Raleway", Font.BOLD, 40));
        l2.setBounds(90,440,600,60);
        i7.add(l2);
        
        l1 = new JLabel("FORTUNATE BANKING");
        l1.setForeground(new Color(1, 24, 56) );
        l1.setFont(new Font("Raleway", Font.BOLD, 30));
        l1.setBounds(30,20,600,60);
        add(l1);
        
        
        l2 = new JLabel("CARD NUMBER");
        l2.setForeground(new Color(1, 24, 56));
        l2.setFont(new Font("Raleway", Font.BOLD, 20));
        l2.setBounds(50,150,375,30);
        add(l2);
        
        tf1 = new JTextField(15);
        tf1.setBounds(50,200,300,45);
        tf1.setForeground(new Color(39, 12, 57) );
        tf1.setFont(new Font("Raleway", Font.BOLD, 20));
        add(tf1);
        
        l3 = new JLabel("PIN");
        l3.setForeground(new Color(1, 24, 56));
        l3.setFont(new Font("Raleway", Font.BOLD, 20));
        l3.setBounds(50,260,375,30);
        add(l3);
        
        pf2 = new JPasswordField(15);
        pf2.setForeground(new Color(39, 12, 57) );
        pf2.setFont(new Font("Raleway", Font.BOLD, 20));
        pf2.setBounds(50,300,300,45);
        add(pf2);
                
        b1 = new JButton("SIGN IN");
        b1.setBackground(new Color(53, 39, 136));
        b1.setForeground(Color.WHITE);
        
        
        b2 = new JButton("CLEAR");
        b2.setBackground(new Color(53, 39, 136));
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("SIGN UP");
        b3.setBackground(new Color(53, 39, 136));
        b3.setForeground(Color.WHITE);
        
        setLayout(null);
        
        b1.setFont(new Font("Raleway", Font.BOLD, 14));
        b1.setBounds(50,380,300,40);
        add(b1);
        
        b2.setFont(new Font("Raleway", Font.BOLD, 14));
        b2.setBounds(50,450,140,40);
        add(b2);
        
        b3.setFont(new Font("Raleway", Font.BOLD, 14));
        b3.setBounds(210,450,140,40);
        add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,600);
        setLocation(380,100);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        try{        
            if(ae.getSource()==b1){
                Conn c1 = new Conn();
                String cardno  = tf1.getText();
                String pin  = pf2.getText();
                String q  = "select * from login where cardnumber = '"+cardno+"' and pin = '"+pin+"'";

                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            }else if(ae.getSource()==b2){
                tf1.setText("");
                pf2.setText("");
            }else if(ae.getSource()==b3){
                setVisible(false);
                new Signup().setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Login().setVisible(true);
    }

    
}



