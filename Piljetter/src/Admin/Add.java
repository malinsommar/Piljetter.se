package Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

class Add {

    JPanel addBandPanel = new JPanel();
    JPanel addVenuePanel = new JPanel();
    JPanel addConcertPanel = new JPanel();
    JPanel addAdminPanel = new JPanel();

    JButton doneBandButton, backBandButton,backVenueButton,doneVenueButton,doneConcertButton,backConcertButton,doneAdminButton,backAdminButton;
    TextField bandField = new TextField();
    TextField genreField = new TextField();
    TextField venueNameField = new TextField();
    TextField countryField = new TextField();
    TextField cityField = new TextField();
    TextField venuePopField = new TextField();
    TextField crowdField = new TextField();
    TextField bandIdField = new TextField();
    TextField bandPopField = new TextField();
    TextField venueIdField = new TextField();
    TextField priceField = new TextField();
    TextField dateField = new TextField();
    TextField adminUsernameField = new TextField();
    TextField adminPasswordField = new TextField();
    JLabel bandError, venueError, concertError,adminError, bandPlaysError, venuePlaysError,datePassedError;
    String band, genre, bandPop, venue, country, city, venuePop, crowd, bandId, venueId,date,adminPassword,adminUsername;
    private String venueCost, bandCost, ticketCapacity;
    int organizerCost = 0;

    boolean bandAlreadyPlays = false, venueAlreadyPlays = false, datePassed = false;

     void addBand(){

        addBandPanel.setBounds(0,0,600, 400);
        addBandPanel.setLayout(null);
        addBandPanel.setBackground(Color.white);
        addBandPanel.setVisible(false);

         JLabel addBandTitle = new JLabel("Adding new band ");
         addBandTitle.setForeground(Color.black);
         addBandTitle.setFont(new Font("serif", Font.PLAIN,40));
         addBandTitle.setBounds(190,30,1000,50);

         JLabel bandNameLabel = new JLabel("Band name *");
         bandNameLabel.setForeground(Color.black);
         bandNameLabel.setBounds(80,150,110,20);
         bandNameLabel.setFont(new Font("serif", Font.PLAIN,20));

         JLabel genreLabel = new JLabel("Genre *");
         genreLabel.setForeground(Color.black);
         genreLabel.setBounds(100,200,100,20);
         genreLabel.setFont(new Font("serif", Font.PLAIN,20));

         JLabel bandPopularity = new JLabel("Popularity (<- 1,2,3 ->)*");
         bandPopularity.setForeground(Color.black);
         bandPopularity.setBounds(80,250,100,20);
         bandPopularity.setFont(new Font("serif", Font.PLAIN,20));

         bandPopField = new TextField("");
         bandPopField.setBounds(200,250,300,30);
         bandPopField.setFont(new Font("serif", Font.PLAIN,20));

         bandField = new TextField("");
         bandField.setBounds(200,150,300,30);
         bandField.setFont(new Font("serif", Font.PLAIN,20));

         genreField = new TextField("");
         genreField.setBounds(200,200,300,30);
         genreField.setFont(new Font("serif", Font.PLAIN,20));

         doneBandButton = new JButton("Save");
         doneBandButton.setForeground(Color.black);
         doneBandButton.setBackground(Color.white);
         doneBandButton.setBounds(330,300,100,50);
         doneBandButton.setFocusPainted(false);
         doneBandButton.setFont(new Font("serif", Font.PLAIN,20));

         backBandButton = new JButton("Back");
         backBandButton.setForeground(Color.black);
         backBandButton.setBackground(Color.white);
         backBandButton.setBounds(180,300,100,50);
         backBandButton.setFocusPainted(false);
         backBandButton.setFont(new Font("serif", Font.PLAIN,20));

         bandError = new JLabel("Field with * cannot be empty.");
         bandError.setForeground(Color.red);
         bandError.setBounds(210,320,1000,100);
         bandError.setVisible(false);
         bandError.setFont(new Font("serif", Font.PLAIN,17));

        addVenuePanel.add(bandField);
        addBandPanel.add(genreField);
        addBandPanel.add(bandPopularity);
        addBandPanel.add(bandPopField);
        addBandPanel.add(genreLabel);
        addBandPanel.add(doneBandButton);
        addBandPanel.add(backBandButton);
        addBandPanel.add(addBandTitle);
        addBandPanel.add(bandNameLabel);
        addBandPanel.add(bandField);
        addBandPanel.add(bandError);
    }

    void databaseAddBand(){

            try {
                //Connecting to database
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

                //Retreving data
                Statement statement = connection.createStatement();

                statement.execute("INSERT INTO piljetter3.bands(band_name,genre,popularity)VALUES ('" + band + "','" + genre + "',"+bandPop+")");

            } catch (Exception e) {
                System.out.println(e.toString());
            }
    }

    void addVenue(){

        addVenuePanel.setBounds(0,0,600, 400);
        addVenuePanel.setLayout(null);
        addVenuePanel.setBackground(Color.white);
        addVenuePanel.setVisible(false);

        JLabel addVenueTitle = new JLabel("Adding new venue ");
        addVenueTitle.setForeground(Color.black);
        addVenueTitle.setFont(new Font("serif", Font.PLAIN,40));
        addVenueTitle.setBounds(150,15,1000,50);

        JLabel venueNameLabel = new JLabel("Venue name *");
        venueNameLabel.setForeground(Color.black);
        venueNameLabel.setBounds(50,80,120,20);
        venueNameLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel countryLabel = new JLabel("Country *");
        countryLabel.setForeground(Color.black);
        countryLabel.setBounds(50,130,120,20);
        countryLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel cityLabel = new JLabel("City *");
        cityLabel.setForeground(Color.black);
        cityLabel.setBounds(50,180,120,20);
        cityLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel venuePopLabel = new JLabel("Popularity (<- 1,2,3 ->) *");
        venuePopLabel.setForeground(Color.black);
        venuePopLabel.setBounds(50,230,120,20);
        venuePopLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel crowdCapacityLabel = new JLabel("Crowd capacity *");
        crowdCapacityLabel.setForeground(Color.black);
        crowdCapacityLabel.setBounds(50,280,150,20);
        crowdCapacityLabel.setFont(new Font("serif", Font.PLAIN,20));

        venueNameField = new TextField("");
        venueNameField.setBounds(220,80,300,30);
        venueNameField.setFont(new Font("serif", Font.PLAIN,20));

        countryField = new TextField("");
        countryField.setBounds(220,130,300,30);
        countryField.setFont(new Font("serif", Font.PLAIN,20));

        cityField = new TextField("");
        cityField.setBounds(220,180,300,30);
        cityField.setFont(new Font("serif", Font.PLAIN,20));

        venuePopField = new TextField("");
        venuePopField.setBounds(220,230,300,30);
        venuePopField.setFont(new Font("serif", Font.PLAIN,20));

        crowdField = new TextField("");
        crowdField.setBounds(220,280,300,30);
        crowdField.setFont(new Font("serif", Font.PLAIN,20));

        venueError = new JLabel("Field with * cannot be empty.");
        venueError.setForeground(Color.red);
        venueError.setBounds(210,335,1000,100);
        venueError.setVisible(false);
        venueError.setFont(new Font("serif", Font.PLAIN,17));

        doneVenueButton = new JButton("Save");
        doneVenueButton.setForeground(Color.black);
        doneVenueButton.setBackground(Color.white);
        doneVenueButton.setBounds(330,320,100,50);
        doneVenueButton.setFocusPainted(false);
        doneVenueButton.setFont(new Font("serif", Font.PLAIN,20));

        backVenueButton = new JButton("Back");
        backVenueButton.setForeground(Color.black);
        backVenueButton.setBackground(Color.white);
        backVenueButton.setBounds(180,320,100,50);
        backVenueButton.setFocusPainted(false);
        backVenueButton.setFont(new Font("serif", Font.PLAIN,20));

        addVenuePanel.add(addVenueTitle);
        addVenuePanel.add(venueNameLabel);
        addVenuePanel.add(venueNameField);
        addVenuePanel.add(countryLabel);
        addVenuePanel.add(countryField);
        addVenuePanel.add(cityLabel);
        addVenuePanel.add(cityField);
        addVenuePanel.add(venuePopLabel);
        addVenuePanel.add(venuePopField);
        addVenuePanel.add(crowdCapacityLabel);
        addVenuePanel.add(crowdField);
        addVenuePanel.add(doneVenueButton);
        addVenuePanel.add(backVenueButton);
        addVenuePanel.add(venueError);
    }

    void databaseAddVenue(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            statement.execute("INSERT INTO piljetter3.venues(venue_name,country,city,crowd_capacity,popularity)VALUES ('" + venue + "','" + country + "','"+city+"','"+crowd+"',"+venuePop+")");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void addConcert(){

        addConcertPanel.setBounds(0,0,600, 400);
        addConcertPanel.setLayout(null);
        addConcertPanel.setBackground(Color.white);
        addConcertPanel.setVisible(false);

        JLabel addConcertTitle = new JLabel("Adding new concert ");
        addConcertTitle.setForeground(Color.black);
        addConcertTitle.setFont(new Font("serif", Font.PLAIN,40));
        addConcertTitle.setBounds(140,15,1000,50);

        JLabel bandIdLabel = new JLabel("Band ID *");
        bandIdLabel.setForeground(Color.black);
        bandIdLabel.setBounds(70,102,110,20);
        bandIdLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel venueIdLabel = new JLabel("Venue ID *");
        venueIdLabel.setForeground(Color.black);
        venueIdLabel.setBounds(70,152,100,20);
        venueIdLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel dateLabel = new JLabel("Date (yyyy-mm-dd) *");
        dateLabel.setForeground(Color.black);
        dateLabel.setBounds(70,202,130,20);
        dateLabel.setFont(new Font("serif", Font.PLAIN,15));

        bandIdField = new TextField("");
        bandIdField.setBounds(200,100,300,30);
        bandIdField.setFont(new Font("serif", Font.PLAIN,20));

        venueIdField = new TextField("");
        venueIdField.setBounds(200,150,300,30);
        venueIdField.setFont(new Font("serif", Font.PLAIN,20));

        dateField = new TextField("");
        dateField.setBounds(200,200,300,30);
        dateField.setFont(new Font("serif", Font.PLAIN,20));

        doneConcertButton = new JButton("Save");
        doneConcertButton.setForeground(Color.black);
        doneConcertButton.setBackground(Color.white);
        doneConcertButton.setBounds(330,300,100,50);
        doneConcertButton.setFocusPainted(false);
        doneConcertButton.setFont(new Font("serif", Font.PLAIN,20));

        backConcertButton = new JButton("Back");
        backConcertButton.setForeground(Color.black);
        backConcertButton.setBackground(Color.white);
        backConcertButton.setBounds(180,300,100,50);
        backConcertButton.setFocusPainted(false);
        backConcertButton.setFont(new Font("serif", Font.PLAIN,20));

        concertError = new JLabel("Field with * cannot be empty.");
        concertError.setForeground(Color.red);
        concertError.setBounds(210,320,1000,100);
        concertError.setVisible(false);
        concertError.setFont(new Font("serif", Font.PLAIN,17));

        bandPlaysError = new JLabel("Band is occupied that day.");
        bandPlaysError.setForeground(Color.red);
        bandPlaysError.setBounds(210,320,1000,100);
        bandPlaysError.setVisible(false);
        bandPlaysError.setFont(new Font("serif", Font.PLAIN,17));

        venuePlaysError = new JLabel("Venue is occupied that day.");
        venuePlaysError.setForeground(Color.red);
        venuePlaysError.setBounds(210,320,1000,100);
        venuePlaysError.setVisible(false);
        venuePlaysError.setFont(new Font("serif", Font.PLAIN,17));

        datePassedError = new JLabel("Date has already passed.");
        datePassedError.setForeground(Color.red);
        datePassedError.setBounds(210,320,1000,100);
        datePassedError.setVisible(false);
        datePassedError.setFont(new Font("serif", Font.PLAIN,17));

        addConcertPanel.add(bandIdLabel);
        addConcertPanel.add(venueIdLabel);
        addConcertPanel.add(bandIdField);
        addConcertPanel.add(venueIdField);
        addConcertPanel.add(dateLabel);
        addConcertPanel.add(dateField);
        addConcertPanel.add(doneConcertButton);
        addConcertPanel.add(backConcertButton);
        addConcertPanel.add(concertError);
        addConcertPanel.add(bandPlaysError);
        addConcertPanel.add(datePassedError);
        addConcertPanel.add(venuePlaysError);
        addConcertPanel.add(addConcertTitle);
    }

    void databaseAddConcert(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO piljetter3.concerts(band_id,venue_id,date,ticket_amount,price)VALUES ("+ bandId + "," + venueId +",'"+date+"',"+ticketCapacity+","+bandCost+"+"+venueCost+")");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    void getVenueCostDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();

            ResultSet venuePopularity = statement.executeQuery("SELECT popularity FROM piljetter3.venues WHERE venue_id = "+venueId+";");

            while(venuePopularity.next()){

                switch (venuePopularity.getString("popularity")) {
                    case "1":
                        venueCost = "200";
                        organizerCost = organizerCost + 10000;
                        break;
                    case "2":
                        venueCost = "300";
                        organizerCost = organizerCost + 50000;
                        break;
                    case "3":
                        venueCost = "500";
                        organizerCost = organizerCost + 100000;
                        break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    void getBandCostDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();

            ResultSet bandPopularity = statement.executeQuery("SELECT popularity FROM piljetter3.bands WHERE band_id = "+bandId+";");

            while(bandPopularity.next()){

                switch (bandPopularity.getString("popularity")) {
                    case "1":
                        bandCost = "200";
                        organizerCost = organizerCost + 10000;
                        break;
                    case "2":
                        bandCost = "300";
                        organizerCost = organizerCost + 50000;
                        break;
                    case "3":
                        bandCost = "500";
                        organizerCost = organizerCost + 100000;
                        break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    void getCrowdCapacityDataBase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();

            ResultSet crowdResult = statement.executeQuery("SELECT crowd_capacity FROM piljetter3.venues WHERE venue_id = "+venueId+";");

            while(crowdResult.next()){
                ticketCapacity = crowdResult.getString("crowd_capacity");
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    void isBandBusyDatabase(){

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT date FROM piljetter3.concerts WHERE band_id = "+bandId+";");

            //Print out result
            while(resultSet.next()){
                if (resultSet.getString("date").equals(date)) {
                    bandAlreadyPlays = true;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    void isVenueBusyDatabase(){

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT date FROM piljetter3.concerts WHERE venue_id = "+venueId+";");

            //Print out result
            while(resultSet.next()){
                if (resultSet.getString("date").equals(date)) {
                    venueAlreadyPlays = true;
                }
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    void hasDatePassedDatabase() {

        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        Date expiryDate = c.getTime();
        java.sql.Date sqlExpiryDate = new java.sql.Date(expiryDate.getTime());

        if(date.compareTo(String.valueOf(sqlExpiryDate)) < 0) {
            datePassed = true;
        }
    }

    void insertOrganiserCost(){
         String concertId = "";

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT concert_id FROM piljetter3.concerts WHERE band_id = "+bandId+" AND venue_id = "+venueId+" AND date = '"+date+"';");

            while (resultSet.next()){
                concertId = resultSet.getString("concert_id");
            }

            statement2.executeQuery("INSERT INTO piljetter3.concert_expenditure (concert_id,concert_cost)VALUES ("+concertId+","+organizerCost+");");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }


    void addAdmin(){

        addAdminPanel.setBounds(0,0,600, 400);
        addAdminPanel.setLayout(null);
        addAdminPanel.setBackground(Color.white);
        addAdminPanel.setVisible(false);

        JLabel addAdminTitle = new JLabel("Adding new Admin ");
        addAdminTitle.setForeground(Color.black);
        addAdminTitle.setFont(new Font("serif", Font.PLAIN,40));
        addAdminTitle.setBounds(190,30,1000,50);

        JLabel adminUsernameLabel = new JLabel("Username *");
        adminUsernameLabel.setForeground(Color.black);
        adminUsernameLabel.setBounds(80,150,110,20);
        adminUsernameLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel adminPasswordLabel = new JLabel("Password *");
        adminPasswordLabel.setForeground(Color.black);
        adminPasswordLabel.setBounds(100,200,100,20);
        adminPasswordLabel.setFont(new Font("serif", Font.PLAIN,20));

        adminUsernameField = new TextField("");
        adminUsernameField.setBounds(200,150,300,30);
        adminUsernameField.setFont(new Font("serif", Font.PLAIN,20));

        adminPasswordField = new TextField("");
        adminPasswordField.setBounds(200,200,300,30);
        adminPasswordField.setFont(new Font("serif", Font.PLAIN,20));

        doneAdminButton = new JButton("Save");
        doneAdminButton.setForeground(Color.black);
        doneAdminButton.setBackground(Color.white);
        doneAdminButton.setBounds(330,300,100,50);
        doneAdminButton.setFocusPainted(false);
        doneAdminButton.setFont(new Font("serif", Font.PLAIN,20));

        backAdminButton = new JButton("Back");
        backAdminButton.setForeground(Color.black);
        backAdminButton.setBackground(Color.white);
        backAdminButton.setBounds(180,300,100,50);
        backAdminButton.setFocusPainted(false);
        backAdminButton.setFont(new Font("serif", Font.PLAIN,20));

        adminError = new JLabel("Field with * cannot be empty.");
        adminError.setForeground(Color.red);
        adminError.setBounds(210,320,1000,100);
        adminError.setVisible(false);
        adminError.setFont(new Font("serif", Font.PLAIN,17));

        addAdminPanel.add(addAdminTitle);
        addAdminPanel.add(adminUsernameLabel);
        addAdminPanel.add(adminUsernameField);
        addAdminPanel.add(adminPasswordLabel);
        addAdminPanel.add(adminPasswordField);
        addAdminPanel.add(adminError);
        addAdminPanel.add(backAdminButton);
        addAdminPanel.add(doneAdminButton);
    }

    void databaseAddAdmin(){

        try {
            //Connecting to database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

            //Retreving data
            Statement statement = connection.createStatement();

            statement.execute("INSERT INTO piljetter3.admin_accounts(username,password)VALUES ('"+ adminUsername + "','" + adminPassword + "')");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}