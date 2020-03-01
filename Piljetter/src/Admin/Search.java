package Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Search {

    JPanel searchPanel = new JPanel();

    TextField bandField = new TextField();
    TextField venueField = new TextField();
    TextField countryField = new TextField();
    TextField cityField = new TextField();
    TextField startDateField = new TextField();
    TextField endDateField = new TextField();

    JButton doneButton, backButton;

    String band,venue,country,city,startDate,endDate;

    void getSearchPanel(){

        searchPanel.setBounds(0,0,600, 400);
        searchPanel.setLayout(null);
        searchPanel.setBackground(Color.white);
        searchPanel.setVisible(false);

        JLabel searchTitle = new JLabel("Adding new venue ");
        searchTitle.setForeground(Color.black);
        searchTitle.setFont(new Font("serif", Font.PLAIN,40));
        searchTitle.setBounds(150,15,1000,50);

        JLabel bandLabel = new JLabel("Band");
        bandLabel.setForeground(Color.black);
        bandLabel.setBounds(80,80,120,20);
        bandLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel venueLabel = new JLabel("Venue");
        venueLabel.setForeground(Color.black);
        venueLabel.setBounds(80,130,120,20);
        venueLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel countryLabel = new JLabel("Country");
        countryLabel.setForeground(Color.black);
        countryLabel.setBounds(80,180,120,20);
        countryLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel cityLabel = new JLabel("City");
        cityLabel.setForeground(Color.black);
        cityLabel.setBounds(80,230,120,20);
        cityLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel startDateLabel = new JLabel("Start date");
        startDateLabel.setForeground(Color.black);
        startDateLabel.setBounds(50,280,80,20);
        startDateLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel endDateLabel = new JLabel("End date");
        endDateLabel.setForeground(Color.black);
        endDateLabel.setBounds(300,280,80,20);
        endDateLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel dateFormat = new JLabel("Date format: yyyy-mm-dd");
        dateFormat.setForeground(Color.red);
        dateFormat.setBounds(30,330,150,20);
        dateFormat.setFont(new Font("serif", Font.PLAIN,13));

        bandField.setBounds(220,80,300,30);
        bandField.setFont(new Font("serif", Font.PLAIN,20));

        venueField.setBounds(220,130,300,30);
        venueField.setFont(new Font("serif", Font.PLAIN,20));

        countryField.setBounds(220,180,300,30);
        countryLabel.setFont(new Font("serif", Font.PLAIN,20));

        cityField.setBounds(220,230,300,30);
        cityField.setFont(new Font("serif", Font.PLAIN,20));

        startDateField.setBounds(140,280,150,30);
        startDateField.setFont(new Font("serif", Font.PLAIN,20));

        endDateField.setBounds(400,280,150,30);
        endDateField.setFont(new Font("serif", Font.PLAIN,20));

        doneButton = new JButton("Search");
        doneButton.setForeground(Color.black);
        doneButton.setBackground(Color.white);
        doneButton.setBounds(330,320,100,50);
        doneButton.setFocusPainted(false);
        doneButton.setFont(new Font("serif", Font.PLAIN,20));

        backButton = new JButton("Back");
        backButton.setForeground(Color.black);
        backButton.setBackground(Color.white);
        backButton.setBounds(180,320,100,50);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("serif", Font.PLAIN,20));

        searchPanel.add(searchTitle);
        searchPanel.add(bandLabel);
        searchPanel.add(bandField);
        searchPanel.add(venueLabel);
        searchPanel.add(venueField);
        searchPanel.add(countryLabel);
        searchPanel.add(countryField);
        searchPanel.add(cityLabel);
        searchPanel.add(cityField);
        searchPanel.add(startDateLabel);
        searchPanel.add(startDateField);
        searchPanel.add(endDateLabel);
        searchPanel.add(endDateField);
        searchPanel.add(doneButton);
        searchPanel.add(backButton);
        searchPanel.add(dateFormat);
    }

    void searchWithoutDateDatabase(){
        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT b.band_name, v.venue_name, v.country, v.city, c.price, c.date FROM piljetter3.bands b, piljetter3.venues v, piljetter3.concerts c WHERE b.band_id = c.band_id AND v.venue_id = c.venue_id AND UPPER (b.band_name) LIKE UPPER ('%"+band+"%') AND UPPER (v.venue_name) LIKE UPPER ('%"+venue+"%') AND UPPER (v.country) LIKE UPPER ('%"+country+"%') AND UPPER (v.city) LIKE UPPER ('%"+city+"%');");

            System.out.println("____________________________________________");
            while (resultSet.next()){
                System.out.println("Band: "+resultSet.getString("band_name")+"  Venue: "+resultSet.getString("venue_name")+"  Country: "+resultSet.getString("country")+"  City: "+resultSet.getString("city")+"  Date: "+resultSet.getString("date")+"  Price:"+resultSet.getString("price"));
            }
            System.out.println("____________________________________________");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void searchWithDateDatabase(){
        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT b.band_name, v.venue_name, v.country, v.city, c.price, c.date FROM piljetter3.bands b, piljetter3.venues v, piljetter3.concerts c WHERE b.band_id = c.band_id AND v.venue_id = c.venue_id AND UPPER (b.band_name) LIKE UPPER ('%"+band+"%') AND UPPER (v.venue_name) LIKE UPPER ('%"+venue+"%') AND UPPER (v.country) LIKE UPPER ('%"+country+"%') AND UPPER (v.city) LIKE UPPER ('%"+city+"%') AND c.date > '"+startDate+" AND c.date < '"+endDate+"';");

            System.out.println("____________________________________________");
            while (resultSet.next()){
                System.out.println("Band: "+resultSet.getString("band_name")+"  Venue: "+resultSet.getString("venue_name")+"  Country: "+resultSet.getString("country")+"  City: "+resultSet.getString("city")+"  Date: "+resultSet.getString("date")+"  Price:"+resultSet.getString("price"));
            }
            System.out.println("____________________________________________");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}