package Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Edit {

    JPanel editBandPanel = new JPanel();
    JPanel editVenuePanel = new JPanel();
    JPanel editConcertPanel = new JPanel();
    JPanel editAdminPanel = new JPanel();

    TextField bandIdField = new TextField();
    TextField bandNameField = new TextField();
    TextField genreField = new TextField();
    TextField venueIdField = new TextField();
    TextField venueNameField = new TextField();
    TextField countryField = new TextField();
    TextField cityField = new TextField();


    JLabel emptyError, noError;

    String bandId, awnser, whatToChange, venueId;

    JButton doneBandButton,updateBandNameButton,updateGenreButton, doneVenueButton, updateVenueNameButton,updateCountryButton,getUpdateCityButton;

    void editBand(){
        editBandPanel.setBounds(0,0,600, 400);
        editBandPanel.setLayout(null);
        editBandPanel.setBackground(Color.white);
        editBandPanel.setVisible(false);

        JLabel editBandTitle = new JLabel("Editing band ");
        editBandTitle.setForeground(Color.black);
        editBandTitle.setFont(new Font("serif", Font.PLAIN,40));
        editBandTitle.setBounds(190,30,1000,50);

        JLabel bandIdLabel = new JLabel("Enter band id*");
        bandIdLabel.setForeground(Color.black);
        bandIdLabel.setFont(new Font("serif", Font.PLAIN,20));
        bandIdLabel.setBounds(70,100,120,50);

        bandIdField = new TextField("");
        bandIdField.setBounds(200,110,300,30);
        bandIdField.setFont(new Font("serif", Font.PLAIN,20));

        JLabel bandNameLabel = new JLabel("Change band name");
        bandNameLabel.setForeground(Color.black);
        bandNameLabel.setBounds(50,190,120,50);
        bandNameLabel.setFont(new Font("serif", Font.PLAIN,15));

        bandNameField = new TextField("");
        bandNameField.setBounds(180,200,300,30);
        bandNameField.setFont(new Font("serif", Font.PLAIN,20));

        JLabel genreLabel = new JLabel("Change genre");
        genreLabel.setForeground(Color.black);
        genreLabel.setBounds(50,253,120,20);
        genreLabel.setFont(new Font("serif", Font.PLAIN,15));

        genreField = new TextField("");
        genreField.setBounds(180,250,300,30);
        genreField.setFont(new Font("serif", Font.PLAIN,20));

        doneBandButton = new JButton("Done");
        doneBandButton.setForeground(Color.black);
        doneBandButton.setBackground(Color.white);
        doneBandButton.setBounds(220,310,200,60);
        doneBandButton.setFocusPainted(false);
        doneBandButton.setFont(new Font("serif", Font.PLAIN,20));

        updateBandNameButton = new JButton("Edit");
        updateBandNameButton.setForeground(Color.black);
        updateBandNameButton.setBackground(Color.white);
        updateBandNameButton.setBounds(500,200,80,30);
        updateBandNameButton.setFocusPainted(false);
        updateBandNameButton.setFont(new Font("serif", Font.PLAIN,20));

        updateGenreButton = new JButton("Edit");
        updateGenreButton.setForeground(Color.black);
        updateGenreButton.setBackground(Color.white);
        updateGenreButton.setBounds(500,250,80,30);
        updateGenreButton.setFocusPainted(false);
        updateGenreButton.setFont(new Font("serif", Font.PLAIN,20));

        emptyError = new JLabel("ID and chosen field cannot be empty.");
        emptyError.setForeground(Color.red);
        emptyError.setBounds(190,320,1000,100);
        emptyError.setVisible(false);
        emptyError.setFont(new Font("serif", Font.PLAIN,17));

        noError = new JLabel("Change made.");
        noError.setForeground(Color.green);
        noError.setBounds(190,320,1000,100);
        noError.setVisible(false);
        noError.setFont(new Font("serif", Font.PLAIN,17));

        editBandPanel.add(editBandTitle);
        editBandPanel.add(bandIdLabel);
        editBandPanel.add(bandIdField);
        editBandPanel.add(bandNameLabel);
        editBandPanel.add(bandNameField);
        editBandPanel.add(genreLabel);
        editBandPanel.add(genreField);
        editBandPanel.add(updateBandNameButton);
        editBandPanel.add(updateGenreButton);
        editBandPanel.add(doneBandButton);

    }

    void updateBandDatabase(){

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");
            Statement statement = connection.createStatement();

            statement.executeQuery("UPDATE piljetter3.bands SET "+whatToChange+" = '"+awnser+"' WHERE band_id = "+bandId+";");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

     void editVenue(){
        editVenuePanel.setBounds(0,0,600, 400);
        editVenuePanel.setLayout(null);
        editVenuePanel.setBackground(Color.white);
        editVenuePanel.setVisible(false);

        JLabel editVenueTitle = new JLabel("Editing venue ");
        editVenueTitle.setForeground(Color.black);
        editVenueTitle.setFont(new Font("serif", Font.PLAIN,40));
        editVenueTitle.setBounds(190,30,1000,50);

        JLabel venueIdLabel = new JLabel("Enter venue id*");
        venueIdLabel.setForeground(Color.black);
        venueIdLabel.setFont(new Font("serif", Font.PLAIN,20));
        venueIdLabel.setBounds(70,100,125,50);

        venueIdField = new TextField("");
        venueIdField.setBounds(200,110,300,30);
        venueIdField.setFont(new Font("serif", Font.PLAIN,20));

        JLabel venueNameLabel = new JLabel("Change venue name");
        venueNameLabel.setForeground(Color.black);
        venueNameLabel.setBounds(50,168,120,50);
        venueNameLabel.setFont(new Font("serif", Font.PLAIN,15));

        venueNameField = new TextField("");
        venueNameField.setBounds(180,180,300,30);
        venueNameField.setFont(new Font("serif", Font.PLAIN,20));

        JLabel countryLabel = new JLabel("Change Country");
        countryLabel.setForeground(Color.black);
        countryLabel.setBounds(50,223,120,20);
        countryLabel.setFont(new Font("serif", Font.PLAIN,15));

        countryField = new TextField("");
        countryField.setBounds(180,220,300,30);
        countryField.setFont(new Font("serif", Font.PLAIN,20));

        JLabel cityLabel = new JLabel("Change City");
        cityLabel.setForeground(Color.black);
        cityLabel.setBounds(50,273,120,20);
        cityLabel.setFont(new Font("serif", Font.PLAIN,15));

        cityField = new TextField("");
        cityField.setBounds(180,270,300,30);
        cityField.setFont(new Font("serif", Font.PLAIN,20));

        doneVenueButton = new JButton("Done");
        doneVenueButton.setForeground(Color.black);
        doneVenueButton.setBackground(Color.white);
        doneVenueButton.setBounds(220,310,200,60);
        doneVenueButton.setFocusPainted(false);
        doneVenueButton.setFont(new Font("serif", Font.PLAIN,20));

        updateVenueNameButton = new JButton("Edit");
        updateVenueNameButton.setForeground(Color.black);
        updateVenueNameButton.setBackground(Color.white);
        updateVenueNameButton.setBounds(500,180,80,30);
        updateVenueNameButton.setFocusPainted(false);
        updateVenueNameButton.setFont(new Font("serif", Font.PLAIN,20));

        updateCountryButton = new JButton("Edit");
        updateCountryButton.setForeground(Color.black);
        updateCountryButton.setBackground(Color.white);
        updateCountryButton.setBounds(500,220,80,30);
        updateCountryButton.setFocusPainted(false);
        updateCountryButton.setFont(new Font("serif", Font.PLAIN,20));

         getUpdateCityButton = new JButton("Edit");
         getUpdateCityButton.setForeground(Color.black);
         getUpdateCityButton.setBackground(Color.white);
         getUpdateCityButton.setBounds(500,265,80,30);
         getUpdateCityButton.setFocusPainted(false);
         getUpdateCityButton.setFont(new Font("serif", Font.PLAIN,20));

        editVenuePanel.add(editVenueTitle);
        editVenuePanel.add(venueIdLabel);
        editVenuePanel.add(venueIdField);
        editVenuePanel.add(venueNameLabel);
        editVenuePanel.add(venueNameField);
        editVenuePanel.add(cityLabel);
        editVenuePanel.add(cityField);
        editVenuePanel.add(countryLabel);
        editVenuePanel.add(countryField);
        editVenuePanel.add(updateVenueNameButton);
        editVenuePanel.add(getUpdateCityButton);
        editVenuePanel.add(updateCountryButton);
        editVenuePanel.add(getUpdateCityButton);
        editVenuePanel.add(doneVenueButton);
    }
    void updateVenueDatabase(){

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");
            Statement statement = connection.createStatement();

            statement.executeQuery("UPDATE piljetter3.venues SET "+whatToChange+" = '"+awnser+"' WHERE venue_id = "+venueId+";");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

     void editConcert(){
        editConcertPanel.setBounds(0,0,600, 400);
        editConcertPanel.setLayout(null);
        editConcertPanel.setBackground(Color.white);
        editConcertPanel.setVisible(false);
    }

    private void editAdmin(){
        editAdminPanel.setBounds(0,0,600, 400);
        editAdminPanel.setLayout(null);
        editAdminPanel.setBackground(Color.white);
        editAdminPanel.setVisible(false);
    }
}
