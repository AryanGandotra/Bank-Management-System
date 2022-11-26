package bankmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener{
 
    JButton b1, b2;
    JLabel l1;
    MiniStatement(String pin){
        
        super("Mini Statement");
        
        getContentPane().setBackground(Color.WHITE);
        setSize(600,600);
        setLocation(450,100);
        
        
        l1 = new JLabel();
        l1.setForeground(new Color(53, 39, 136));
        add(l1);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(53, 39, 136));
        panel_1.setBounds(10, 10, 565, 80);
        add(panel_1);
        
        JLabel l2 = new JLabel("B Bank - Bank Management System");
//        l2.setBounds(80, 30, 500, 30);
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 25));
        panel_1.add(l2);
        
        JLabel l3 = new JLabel();
        l3.setBounds(80, 100, 300, 20);
        l3.setForeground(new Color(53, 39, 136));
        add(l3);
        
        JLabel l4 = new JLabel();
        l4.setBounds(20, 400, 300, 20);
        l4.setForeground(new Color(53, 39, 136));
        add(l4);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pin+"'");
            while(rs.next()){
                l3.setText("Card Number:    " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }
        }catch(Exception e){}
        	 
        try{
            int balance = 0;
            Conn c1  = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '"+pin+"'");
            while(rs.next()){
                l1.setText(l1.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(rs.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else{
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            l4.setText("Your total Balance is Rs "+balance);
            l4.setForeground(new Color(53, 39, 136));
            l4.setBounds(70, 350, 400, 200);

        }catch(Exception e){
            e.printStackTrace();
        }
        
        setLayout(null);
        b1 = new JButton("Exit");
        b1.setBackground(new Color(53, 39, 136));
        b1.setForeground(Color.WHITE);
        add(b1);
        
        b1.addActionListener(this);
        
        l1.setBounds(80, 150, 400, 200);
        b1.setBounds(70, 500, 100, 30);
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    
    public static void main(String[] args){
        new MiniStatement("").setVisible(true);
    }
    
}
