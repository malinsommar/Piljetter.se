package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuyTickets {

    static JPanel buyTicketsPanel = new JPanel();

    String buttonTextBand;
    String buttonTextVenue;
    String buttonTextDate;
    String buttonTextCountry;
    String buttonConcertId;

    String searchword;
    JTextField search = new JTextField("");
    static public JTextField ticketToBuy = new JTextField("Ticket to buy");
    JButton searchbutton = new JButton("search");
    JButton couponButton = new JButton("use coupon");
    JButton backToMenu = new JButton("Back");
    ;

    static boolean coupon = false;


    public void allConcerts() {
        //panel
        buyTicketsPanel.setBounds(0, 0, 600, 400);
        buyTicketsPanel.setLayout(null);
        buyTicketsPanel.setVisible(true);
        buyTicketsPanel.setBackground(Color.white);

        //searchbar
        search.setBounds(10, 10, 100, 25);
        search.setVisible(true);
        buyTicketsPanel.add(search);

        ticketToBuy.setBounds(750, 10, 100, 25);
        ticketToBuy.setVisible(true);
        buyTicketsPanel.add(ticketToBuy);

        //confirm search button
        searchbutton.setForeground(Color.black);
        searchbutton.setBackground(Color.green);
        searchbutton.setBounds(400, 0, 200, 25);
        searchbutton.setFocusPainted(false);
        searchbutton.setFont(new Font("serif", 0, 20));
        buyTicketsPanel.add(searchbutton);

        //coonfirm search button
        couponButton.setBackground(Color.gray);
        couponButton.setBounds(200, 0, 200, 25);
        couponButton.setFocusPainted(false);
        couponButton.setFont(new Font("serif", 0, 20));
        buyTicketsPanel.add(couponButton);
        couponButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (coupon) {
                    coupon = false;
                    couponButton.setBackground(Color.gray);
                } else {
                    coupon = true;
                    couponButton.setBackground(Color.blue);
                }
            }
        });

        searchbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Component[] components = buyTicketsPanel.getComponents();

                for (Component component : components) {
                    if (!(component == search || component == searchbutton || component == ticketToBuy || component == couponButton))
                        buyTicketsPanel.remove(component);
                }
                buyTicketsPanel.revalidate();
                buyTicketsPanel.repaint();

                bandButton.buttonPositionX = 100;
                bandButton.buttonPositionY = 50;
                searchword = search.getText();
                printbuttons();

            }
        });
        backToMenu.setForeground(Color.black);
        backToMenu.setBackground(Color.white);
        backToMenu.setBounds(400, 200, 100, 50);
        backToMenu.setFocusPainted(false);
        backToMenu.setFont(new Font("serif", Font.PLAIN, 15));

        buyTicketsPanel.add(backToMenu);


    }

    private void printbuttons() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(
                    "select DISTINCT * from piljetter3.bands b, piljetter3.concerts c, piljetter3.venues v WHERE b.band_id = c.band_id and v.venue_id = c.venue_id " +
                            "and ((UPPER (b.band_name) like UPPER ('%" + searchword + "%')) " +
                            "or (UPPER (v.venue_name) like UPPER ('%" + searchword + "%')) " +
                            "or (UPPER (v.country) like UPPER ('%" + searchword + "%')) " +
                            "or (UPPER (v.city) like UPPER ('%" + searchword + "%')) " +
                            "or ((c.date <= '" + searchword + "') and (c.date >= current_date)) " + //finds concerts between now and the searchdate


                            "and (c.date) > current_date"); //filters out past concerts

            while (resultset.next()) {
                buttonTextBand = resultset.getString("band_name");
                buttonTextVenue = resultset.getString("venue_name");
                buttonTextDate = resultset.getString("date");
                buttonTextCountry = resultset.getString("country");
                buttonConcertId = resultset.getString("concert_id");


                new bandButton(buttonTextBand, buttonConcertId);
                new bandButton(buttonTextVenue, buttonConcertId);
                new bandButton(buttonTextDate, buttonConcertId);
                new bandButton(buttonTextCountry, buttonConcertId);
            }
        } catch (Exception var3) {
            System.out.println(var3.toString());
        }
    }
}
