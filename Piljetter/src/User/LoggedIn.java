package User;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoggedIn {

    JPanel mainMenuPanel = new JPanel();
    JPanel searchPanel = new JPanel();
    JPanel pesetasPanel = new JPanel();
    JPanel concertsPanel = new JPanel();
    JPanel ticketsListPanel = new JPanel();
    JPanel couponsListPanel = new JPanel();
    JLabel pesetas, coupons;
    JButton buyPesetasButton, logOutButton, allConcertsButton, confirmButton, backButton, backButton2, backButton3, allTicketsButton, allCouponsButton;
    TextField buyPesetasField;
    String currentUser = "";


    void menu() {
        mainMenuPanel.setBounds(0, 0, 600, 400);
        mainMenuPanel.setLayout(null);
        mainMenuPanel.setBackground(Color.white);
        mainMenuPanel.setVisible(false);

        pesetas = new JLabel("pesetas error");
        pesetas.setForeground(Color.black);
        pesetas.setFont(new Font("serif", Font.PLAIN, 20));
        pesetas.setBounds(80, 30, 1000, 50);

        mainMenuPanel.add(pesetas);

        coupons = new JLabel("coupons error");
        coupons.setForeground(Color.black);
        coupons.setFont(new Font("serif", Font.PLAIN, 20));
        coupons.setBounds(400, 30, 1000, 50);

        mainMenuPanel.add(coupons);

        buyPesetasButton = new JButton("Buy pesetas");
        buyPesetasButton.setForeground(Color.black);
        buyPesetasButton.setBackground(Color.white);
        buyPesetasButton.setBounds(70, 80, 120, 50);
        buyPesetasButton.setFocusPainted(false);
        buyPesetasButton.setFont(new Font("serif", Font.PLAIN, 15));

        logOutButton = new JButton("Log out");
        logOutButton.setForeground(Color.black);
        logOutButton.setBackground(Color.white);
        logOutButton.setBounds(400, 300, 100, 50);
        logOutButton.setFocusPainted(false);
        logOutButton.setFont(new Font("serif", Font.PLAIN, 15));

        allConcertsButton = new JButton("See concerts");
        allConcertsButton.setForeground(Color.black);
        allConcertsButton.setBackground(Color.white);
        allConcertsButton.setBounds(70, 300, 150, 50);
        allConcertsButton.setFocusPainted(false);
        allConcertsButton.setFont(new Font("serif", Font.PLAIN, 15));

        allTicketsButton = new JButton("Your tickets");
        allTicketsButton.setForeground(Color.black);
        allTicketsButton.setBackground(Color.white);
        allTicketsButton.setBounds(70, 150, 120, 50);
        allTicketsButton.setFocusPainted(false);
        allTicketsButton.setFont(new Font("serif", Font.PLAIN, 15));

        allCouponsButton = new JButton("Your coupons");
        allCouponsButton.setForeground(Color.black);
        allCouponsButton.setBackground(Color.white);
        allCouponsButton.setBounds(70, 220, 120, 50);
        allCouponsButton.setFocusPainted(false);
        allCouponsButton.setFont(new Font("serif", Font.PLAIN, 15));

        mainMenuPanel.add(logOutButton);
        mainMenuPanel.add(buyPesetasButton);
        mainMenuPanel.add(allConcertsButton);
        mainMenuPanel.add(allTicketsButton);
        mainMenuPanel.add(allCouponsButton);
    }


    void buyPesetas() {
        pesetasPanel.setBounds(0, 0, 600, 400);
        pesetasPanel.setLayout(null);
        pesetasPanel.setBackground(Color.white);
        pesetasPanel.setVisible(false);

        buyPesetasField = new TextField("");
        buyPesetasField.setBounds(155, 150, 300, 30);
        buyPesetasField.setFont(new Font("serif", Font.PLAIN, 20));

        confirmButton = new JButton("Confirm");
        confirmButton.setForeground(Color.black);
        confirmButton.setBackground(Color.white);
        confirmButton.setBounds(155, 200, 100, 50);
        confirmButton.setFocusPainted(false);
        confirmButton.setFont(new Font("serif", Font.PLAIN, 15));

        backButton = new JButton("Back");
        backButton.setForeground(Color.black);
        backButton.setBackground(Color.white);
        backButton.setBounds(400, 200, 100, 50);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("serif", Font.PLAIN, 15));

        pesetasPanel.add(buyPesetasField);
        pesetasPanel.add(confirmButton);
        pesetasPanel.add(backButton);

        pesetasAmount();
    }

    /*void seeConcerts(){
        concertsPanel.setBounds(0,0,600, 400);
        concertsPanel.setLayout(null);
        concertsPanel.setBackground(Color.white);
        concertsPanel.setVisible(false);
    }*/

    void pesetasAmount() {

        try {

            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            Statement statement = connection.createStatement();

            String pesetas = buyPesetasField.getText();

            statement.executeQuery("update piljetter3.user_accounts set pesetas_balance = pesetas_balance + " + pesetas + " where customer_id = " + currentUser + "");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void getCoupons() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select coupon_id from piljetter3.user_coupons where customer_id = " + currentUser + "");

            int test = 0;
            while (resultSet.next()) {
                test++;
            }
            coupons.setText("Coupons: " + test);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /*void ticketsList() {
        ticketsListPanel.setBounds(0, 0, 600, 400);
        ticketsListPanel.setLayout(null);
        ticketsListPanel.setBackground(Color.white);
        ticketsListPanel.setVisible(false);


        backButton2 = new JButton("Back");
        backButton2.setForeground(Color.black);
        backButton2.setBackground(Color.white);
        backButton2.setBounds(400, 200, 100, 50);
        backButton2.setFocusPainted(false);
        backButton2.setFont(new Font("serif", Font.PLAIN, 15));

        ticketsListPanel.add(backButton2);
        ticketsListPanel.add(ticketsText);
    }*/

    /*void couponsList() {
        couponsListPanel.setBounds(0, 0, 600, 400);
        couponsListPanel.setLayout(null);
        couponsListPanel.setBackground(Color.white);
        couponsListPanel.setVisible(false);

        couponsText.setBounds(50, 50, 320, 270);
        couponsText.setBackground(Color.black);

        backButton3 = new JButton("Back");
        backButton3.setForeground(Color.black);
        backButton3.setBackground(Color.white);
        backButton3.setBounds(400, 200, 100, 50);
        backButton3.setFocusPainted(false);
        backButton3.setFont(new Font("serif", Font.PLAIN, 15));

        couponsListPanel.add(backButton3);

        couponsListPanel.add(backButton3);
    }*/

    void getTicketsList() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT b.band_name,v.venue_name,bt.ticket_amount,bt.purchase_date,c.date, c.price FROM piljetter3.bought_tickets bt, piljetter3.user_accounts ua, piljetter.concerts c, piljetter3.bands b, piljetter3.venues v WHERE ua.customer_id = bt.customer_id AND ua.customer_id = " + currentUser + " AND c.concert_id = bt.concert_id AND c.band_id = b.band_id AND v.venue_id = c.venue_id;");

            while (resultSet.next()) {
                String band = resultSet.getString("band_name");
                String venue = resultSet.getString("venue_name");
                String price = resultSet.getString("price");
                String ticketA = resultSet.getString("ticket_amount");
                String pD = resultSet.getString("purchase_date");
                String cD = resultSet.getString("date");

                System.out.println("Band: " + band + " | Venue: " + venue + " | Price: " + price + " | Amount of tickets: " + ticketA + " | Purchase date: " + pD + " | Concert date: " + cD);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void getCouponsList() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select coupon_id from piljetter3.user_coupons where customer_id = " + currentUser + "");


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
