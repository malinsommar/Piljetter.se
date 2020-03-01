package Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Statistics {

    JPanel statMenuPanel = new JPanel();
    JPanel firstStatPanel = new JPanel();
    JPanel secondStatPanel = new JPanel();
    JPanel thirdStatPanel = new JPanel();

    TextField firstStatStartField = new TextField();
    TextField firstStatEndField = new TextField();
    TextField secondStatStartField = new TextField();
    TextField secondStatEndField = new TextField();
    TextField thirdStatStartField = new TextField();
    TextField thirdStatEndField = new TextField();
    String startDate, endDate;

    ArrayList<String> unsortedBand = new ArrayList();
    ArrayList<Integer> unsortedTicket = new ArrayList();

    ArrayList<String> sortedBand = new ArrayList();
    ArrayList<Integer> sortedTicket = new ArrayList();

    ArrayList concertIdArray = new ArrayList();
    ArrayList concertCostArray = new ArrayList();

    int totalRevenue = 0, ticketsSold = 0, totalExpenditures = 0;

    JButton backButton, firstStatButton, secondStatButton, thirdStatButton, doneFirstButton, backFirstButton,doneSecondButton, backSecondButton,doneThirdButton, backThirdButton;

    void statMenu(){
        statMenuPanel.setBounds(0,0,600, 400);
        statMenuPanel.setLayout(null);
        statMenuPanel.setBackground(Color.white);
        statMenuPanel.setVisible(false);

        JLabel statTitle = new JLabel("Statistics ");
        statTitle.setForeground(Color.black);
        statTitle.setFont(new Font("serif", Font.PLAIN,40));
        statTitle.setBounds(240,20,1000,50);

        firstStatButton = new JButton("Sold tickets & total revenue");
        firstStatButton.setForeground(Color.black);
        firstStatButton.setBackground(Color.white);
        firstStatButton.setBounds(160,100,280,50);
        firstStatButton.setFocusPainted(false);
        firstStatButton.setFont(new Font("serif", Font.PLAIN,20));

        secondStatButton = new JButton("Bands total tickets purchased");
        secondStatButton.setForeground(Color.black);
        secondStatButton.setBackground(Color.white);
        secondStatButton.setBounds(160,170,280,50);
        secondStatButton.setFocusPainted(false);
        secondStatButton.setFont(new Font("serif", Font.PLAIN,20));

        thirdStatButton = new JButton("Expenditures");
        thirdStatButton.setForeground(Color.black);
        thirdStatButton.setBackground(Color.white);
        thirdStatButton.setBounds(160,240,280,50);
        thirdStatButton.setFocusPainted(false);
        thirdStatButton.setFont(new Font("serif", Font.PLAIN,20));

        backButton = new JButton("Back to menu");
        backButton.setForeground(Color.black);
        backButton.setBackground(Color.white);
        backButton.setBounds(200,310,200,50);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("serif", Font.PLAIN,20));

        statMenuPanel.add(statTitle);
        statMenuPanel.add(firstStatButton);
        statMenuPanel.add(secondStatButton);
        statMenuPanel.add(thirdStatButton);
        statMenuPanel.add(backButton);

    }
    void getFirstStatPanel(){
        firstStatPanel.setBounds(0,0,600, 400);
        firstStatPanel.setLayout(null);
        firstStatPanel.setBackground(Color.white);
        firstStatPanel.setVisible(false);

        JLabel statTitle = new JLabel("Sold tickets & total revenue");
        statTitle.setForeground(Color.black);
        statTitle.setFont(new Font("serif", Font.PLAIN,30));
        statTitle.setBounds(120,20,1000,50);

        JLabel info = new JLabel("Result will show in the console");
        info.setForeground(Color.orange);
        info.setFont(new Font("serif", Font.PLAIN,15));
        info.setBounds(210,80,1000,20);

        JLabel startDate = new JLabel("Start date (yyyy-mm-dd)");
        startDate.setForeground(Color.black);
        startDate.setBounds(50,153,150,20);
        startDate.setFont(new Font("serif", Font.PLAIN,15));

        JLabel secondDate = new JLabel("End date (yyyy-mm-dd)");
        secondDate.setForeground(Color.black);
        secondDate.setBounds(50,203,150,20);
        secondDate.setFont(new Font("serif", Font.PLAIN,15));

        firstStatStartField.setBounds(200,150,300,30);
        firstStatStartField.setFont(new Font("serif", Font.PLAIN,20));

        firstStatEndField.setBounds(200,200,300,30);
        firstStatEndField.setFont(new Font("serif", Font.PLAIN,20));

        doneFirstButton = new JButton("Done");
        doneFirstButton.setForeground(Color.black);
        doneFirstButton.setBackground(Color.white);
        doneFirstButton.setBounds(330,300,100,50);
        doneFirstButton.setFocusPainted(false);
        doneFirstButton.setFont(new Font("serif", Font.PLAIN,20));

        backFirstButton = new JButton("Back");
        backFirstButton.setForeground(Color.black);
        backFirstButton.setBackground(Color.white);
        backFirstButton.setBounds(180,300,100,50);
        backFirstButton.setFocusPainted(false);
        backFirstButton.setFont(new Font("serif", Font.PLAIN,20));

        firstStatPanel.add(statTitle);
        firstStatPanel.add(startDate);
        firstStatPanel.add(secondDate);
        firstStatPanel.add(firstStatStartField);
        firstStatPanel.add(firstStatEndField);
        firstStatPanel.add(backFirstButton);
        firstStatPanel.add(doneFirstButton);
        firstStatPanel.add(info);
    }

    void getTotalTickets(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select ticket_amount from piljetter3.bought_tickets WHERE purchase_date > '"+startDate+"' AND purchase_date < '"+endDate+"';");

            while (resultSet.next()){
                String x = resultSet.getString("ticket_amount");
                ticketsSold = ticketsSold + Integer.parseInt(x);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void getTotalRevenue(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select total_cost from piljetter3.bought_tickets WHERE purchase_date > '"+startDate+"' AND purchase_date < '"+endDate+"';");

            while (resultSet.next()){
                String x = resultSet.getString("total_cost");
                totalRevenue = totalRevenue + Integer.parseInt(x);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    void getSecondStatPanel(){
        secondStatPanel.setBounds(0,0,600, 400);
        secondStatPanel.setLayout(null);
        secondStatPanel.setBackground(Color.white);
        secondStatPanel.setVisible(false);

        JLabel statTitle = new JLabel("Bands total tickets purchased");
        statTitle.setForeground(Color.black);
        statTitle.setFont(new Font("serif", Font.PLAIN,40));
        statTitle.setBounds(150,20,1000,50);

        JLabel info = new JLabel("Result will show in the console");
        info.setForeground(Color.orange);
        info.setFont(new Font("serif", Font.PLAIN,15));
        info.setBounds(210,80,1000,20);

        JLabel startDate = new JLabel("Start date (yyyy-mm-dd)");
        startDate.setForeground(Color.black);
        startDate.setBounds(50,153,150,20);
        startDate.setFont(new Font("serif", Font.PLAIN,15));

        JLabel secondDate = new JLabel("End date (yyyy-mm-dd)");
        secondDate.setForeground(Color.black);
        secondDate.setBounds(50,203,150,20);
        secondDate.setFont(new Font("serif", Font.PLAIN,15));

        secondStatStartField.setBounds(200,150,300,30);
        secondStatStartField.setFont(new Font("serif", Font.PLAIN,20));

        secondStatEndField.setBounds(200,200,300,30);
        secondStatEndField.setFont(new Font("serif", Font.PLAIN,20));

        doneSecondButton = new JButton("Done");
        doneSecondButton.setForeground(Color.black);
        doneSecondButton.setBackground(Color.white);
        doneSecondButton.setBounds(330,300,100,50);
        doneSecondButton.setFocusPainted(false);
        doneSecondButton.setFont(new Font("serif", Font.PLAIN,20));

        backSecondButton = new JButton("Back");
        backSecondButton.setForeground(Color.black);
        backSecondButton.setBackground(Color.white);
        backSecondButton.setBounds(180,300,100,50);
        backSecondButton.setFocusPainted(false);
        backSecondButton.setFont(new Font("serif", Font.PLAIN,20));

        secondStatPanel.add(statTitle);
        secondStatPanel.add(info);
        secondStatPanel.add(startDate);
        secondStatPanel.add(secondDate);
        secondStatPanel.add(secondStatStartField);
        secondStatPanel.add(secondStatEndField);
        secondStatPanel.add(doneSecondButton);
        secondStatPanel.add(backSecondButton);
    }

    void getBestSellingBandsDatabse(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT b.band_name,bt.ticket_amount FROM piljetter3.bought_tickets bt, piljetter3.concerts c, piljetter3.bands b WHERE c.band_id = b.band_id AND c.concert_id = bt.concert_id AND purchase_date > '"+startDate+"' AND purchase_date < '"+endDate+"'");

            Boolean bandExists = false;

            while (resultSet.next()){
                unsortedBand.add(resultSet.getString("band_name"));
                unsortedTicket.add(Integer.parseInt(resultSet.getString("ticket_amount")));
            }
            //Go through unsortedBand
            for (int i = 0; i < unsortedBand.size(); i++) {

                //If it's the first loop
                if (sortedBand.size() == 0){
                    System.out.println(1);
                    sortedBand.add(unsortedBand.get(0));
                    sortedTicket.add(unsortedTicket.get(0));
                }
                //If its not the first loop
                else {
                    //Loop's through sortedBand
                    for (int j = 0; j < sortedBand.size(); j++) {

                        //If band already exist, add unsorted sold tickets to that band
                        if (sortedBand.get(j).equals(unsortedBand.get(i))) {
                            sortedTicket.set(j, sortedTicket.get(j) + unsortedTicket.get(i));
                            bandExists = true;
                        }
                    }
                    //If band does not exist, add new band and tickets
                    if (!bandExists){
                        sortedBand.add(unsortedBand.get(i));
                        sortedTicket.add(unsortedTicket.get(i));
                        bandExists = false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void getThirdStatPanel(){
        thirdStatPanel.setBounds(0,0,600, 400);
        thirdStatPanel.setLayout(null);
        thirdStatPanel.setBackground(Color.white);
        thirdStatPanel.setVisible(false);

        JLabel statTitle = new JLabel("Expenditures");
        statTitle.setForeground(Color.black);
        statTitle.setFont(new Font("serif", Font.PLAIN,40));
        statTitle.setBounds(240,20,1000,50);

        JLabel info = new JLabel("Result will show in the console");
        info.setForeground(Color.orange);
        info.setFont(new Font("serif", Font.PLAIN,15));
        info.setBounds(210,80,1000,20);

        JLabel startDate = new JLabel("Start date (yyyy-mm-dd)");
        startDate.setForeground(Color.black);
        startDate.setBounds(50,153,150,20);
        startDate.setFont(new Font("serif", Font.PLAIN,15));

        JLabel secondDate = new JLabel("End date (yyyy-mm-dd)");
        secondDate.setForeground(Color.black);
        secondDate.setBounds(50,203,150,20);
        secondDate.setFont(new Font("serif", Font.PLAIN,15));

        thirdStatStartField.setBounds(200,150,300,30);
        thirdStatStartField.setFont(new Font("serif", Font.PLAIN,20));

        thirdStatEndField.setBounds(200,200,300,30);
        thirdStatEndField.setFont(new Font("serif", Font.PLAIN,20));

        doneThirdButton = new JButton("Done");
        doneThirdButton.setForeground(Color.black);
        doneThirdButton.setBackground(Color.white);
        doneThirdButton.setBounds(330,300,100,50);
        doneThirdButton.setFocusPainted(false);
        doneThirdButton.setFont(new Font("serif", Font.PLAIN,20));

        backThirdButton = new JButton("Back");
        backThirdButton.setForeground(Color.black);
        backThirdButton.setBackground(Color.white);
        backThirdButton.setBounds(180,300,100,50);
        backThirdButton.setFocusPainted(false);
        backThirdButton.setFont(new Font("serif", Font.PLAIN,20));

        thirdStatPanel.add(statTitle);
        thirdStatPanel.add(startDate);
        thirdStatPanel.add(secondDate);
        thirdStatPanel.add(thirdStatStartField);
        thirdStatPanel.add(thirdStatEndField);
        thirdStatPanel.add(backThirdButton);
        thirdStatPanel.add(doneThirdButton);
        thirdStatPanel.add(info);
    }

    void getExpendituresDatabase(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT ce.concert_cost, ce.concert_id, c.date FROM piljetter3.concert_expenditure ce, piljetter3.concerts c WHERE c.concert_id = ce.concert_id AND c.date > '"+startDate+"' AND c.date < '"+endDate+"';");

            while (resultSet.next()){
                String x = resultSet.getString("concert_cost");
                totalExpenditures = totalExpenditures + Integer.parseInt(x);

                concertCostArray.add(resultSet.getString("concert_cost"));
                concertIdArray.add(resultSet.getString("concert_id"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}