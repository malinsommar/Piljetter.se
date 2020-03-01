package Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Delete {

    JPanel deleteBandPanel = new JPanel();
    JPanel deleteVenuePanel = new JPanel();
    JPanel deleteConcertPanel = new JPanel();
    JPanel deleteAdminPanel = new JPanel();

    ResultSet resultSet;

    TextField concertIdField = new TextField();
    JButton deleteConcertButton, backConcertButton;
    JLabel emptyConcertError;
    String conId, date;

    void deleteConcert(){
        deleteConcertPanel.setBounds(0,0,600, 400);
        deleteConcertPanel.setLayout(null);
        deleteConcertPanel.setBackground(Color.white);
        deleteConcertPanel.setVisible(false);

        JLabel cancelConcertTitle = new JLabel("Cancel concert ");
        cancelConcertTitle.setForeground(Color.black);
        cancelConcertTitle.setFont(new Font("serif", Font.PLAIN,40));
        cancelConcertTitle.setBounds(190,30,1000,50);

        JLabel concertIdLabel = new JLabel("Enter concert id*");
        concertIdLabel.setForeground(Color.black);
        concertIdLabel.setFont(new Font("serif", Font.PLAIN,17));
        concertIdLabel.setBounds(70,95,130,50);

        concertIdField = new TextField("");
        concertIdField.setBounds(200,110,300,30);
        concertIdField.setFont(new Font("serif", Font.PLAIN,20));

        deleteConcertButton = new JButton("Delete");
        deleteConcertButton.setForeground(Color.black);
        deleteConcertButton.setBackground(Color.white);
        deleteConcertButton.setBounds(330,300,100,50);
        deleteConcertButton.setFocusPainted(false);
        deleteConcertButton.setFont(new Font("serif", Font.PLAIN,20));

        backConcertButton = new JButton("Back");
        backConcertButton.setForeground(Color.black);
        backConcertButton.setBackground(Color.white);
        backConcertButton.setBounds(180,300,100,50);
        backConcertButton.setFocusPainted(false);
        backConcertButton.setFont(new Font("serif", Font.PLAIN,20));

        emptyConcertError = new JLabel("ID field cannot be empty.");
        emptyConcertError.setForeground(Color.red);
        emptyConcertError.setBounds(210,320,1000,100);
        emptyConcertError.setVisible(false);
        emptyConcertError.setFont(new Font("serif", Font.PLAIN,17));

        deleteConcertPanel.add(cancelConcertTitle);
        deleteConcertPanel.add(concertIdLabel);
        deleteConcertPanel.add(concertIdField);
        deleteConcertPanel.add(deleteConcertButton);
        deleteConcertPanel.add(backConcertButton);
        deleteConcertPanel.add(emptyConcertError);

    }

    void deleteOrganizerCost(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement2 = connection.createStatement();
            statement2.execute("DELETE FROM piljetter3.concert_expenditure WHERE concert_id ="+conId+";");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void getDateDatabase(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM piljetter3.concerts WHERE concert_id ="+conId+"");

            while(resultSet.next()) {
                date = (resultSet.getString("start_date"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void deleteTicketsDatabase(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM piljetter3.bought_tickets WHERE concert_id ="+conId+";");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void deleteConcertDatabase(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            statement.execute("DELETE FROM piljetter3.concerts WHERE concert_id ="+conId+";");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void moneyBackDatabase(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            statement.execute("UPDATE piljetter3.user_accounts ua SET pesetas_balance = pesetas_balance+(c.price*bt.ticket_amount) FROM piljetter3.bought_tickets bt, piljetter3.concerts c WHERE bt.concert_id = "+conId+" AND bt.customer_id = ua.customer_id;");


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}