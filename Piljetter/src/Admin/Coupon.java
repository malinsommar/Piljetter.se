package Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class Coupon {

    JPanel couponPanel = new JPanel();
    TextField userIdField = new TextField();
    TextField emailField = new TextField();
    JButton addOneButton, backCouponButton;
    JLabel couponError;
    String userId, email;

    void giveCoupon(){
        couponPanel.setBounds(0,0,600, 400);
        couponPanel.setLayout(null);
        couponPanel.setBackground(Color.white);
        couponPanel.setVisible(false);


        JLabel addCouponTitle = new JLabel("Adding coupon ");
        addCouponTitle.setForeground(Color.black);
        addCouponTitle.setFont(new Font("serif", Font.PLAIN,40));
        addCouponTitle.setBounds(190,30,1000,50);

        JLabel userId = new JLabel("customer_id");
        userId.setForeground(Color.black);
        userId.setBounds(80,150,110,20);
        userId.setFont(new Font("serif", Font.PLAIN,20));

        JLabel or = new JLabel("OR");
        or.setForeground(Color.red);
        or.setBounds(100,175,110,20);
        or.setFont(new Font("serif", Font.PLAIN,15));

        userIdField = new TextField("");
        userIdField.setBounds(200,150,300,30);
        userIdField.setFont(new Font("serif", Font.PLAIN,20));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.black);
        emailLabel.setBounds(90,200,110,20);
        emailLabel.setFont(new Font("serif", Font.PLAIN,20));

        emailField = new TextField("");
        emailField.setBounds(200,200,300,30);
        emailField.setFont(new Font("serif", Font.PLAIN,20));

        addOneButton = new JButton("Add 1 coupon");
        addOneButton.setForeground(Color.black);
        addOneButton.setBackground(Color.white);
        addOneButton.setBounds(330,300,150,50);
        addOneButton.setFocusPainted(false);
        addOneButton.setFont(new Font("serif", Font.PLAIN,20));

        backCouponButton = new JButton("Back");
        backCouponButton.setForeground(Color.black);
        backCouponButton.setBackground(Color.white);
        backCouponButton.setBounds(180,300,100,50);
        backCouponButton.setFocusPainted(false);
        backCouponButton.setFont(new Font("serif", Font.PLAIN,20));

        couponError = new JLabel("No user with written id found.");
        couponError.setForeground(Color.red);
        couponError.setBounds(210,320,1000,100);
        couponError.setVisible(false);
        couponError.setFont(new Font("serif", Font.PLAIN,17));

        couponPanel.add(addCouponTitle);
        couponPanel.add(userIdField);
        couponPanel.add(couponError);
        couponPanel.add(userId);
        couponPanel.add(addOneButton);
        couponPanel.add(backCouponButton);
        couponPanel.add(emailLabel);
        couponPanel.add(emailField);
        couponPanel.add(or);
    }

    void couponDatabase(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            Date currentDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DATE,365);

            Date expiryDate = c.getTime();
            java.sql.Date sqlExpiryDate = new java.sql.Date(expiryDate.getTime());

            statement.executeQuery("INSERT INTO piljetter3.user_coupons(expiry_date,customer_id)VALUES('"+sqlExpiryDate+"',"+userId+");");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}