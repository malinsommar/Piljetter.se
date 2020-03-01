package User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class bandButton {

    BuyTickets buyTickets = new BuyTickets();
    LoggedIn loggedIn = new LoggedIn();

    public static int buttonPositionX = 50;
    public static int buttonPositionY = 50;

    JButton bandButton;
    int couponsUsed;

    bandButton(String buttonTextBand, String buttonConcertId) {
        button(buttonTextBand, buttonConcertId);
    }

    private void button(String buttonTextBand, String buttonConcertId) {
        bandButton = new JButton(buttonTextBand);
        bandButton.setForeground(Color.black);
        bandButton.setBackground(Color.gray);
        bandButton.setBounds(buttonPositionX, buttonPositionY, 200, 25);
        bandButton.setFocusPainted(false);
        bandButton.setFont(new Font("serif", 0, 20));
        buyTickets.buyTicketsPanel.add(bandButton);

        bandButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    //gets ticket_amount
                    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");
                    Statement statement = connection.createStatement();
                    ResultSet resultset = statement.executeQuery(
                            "select * from piljetter3.concerts c, piljetter3.user_accounts u WHERE (c.concert_id = ('" + buttonConcertId + "')) and (u.customer_id = '" + loggedIn.currentUser + "') ");
                    //change customer id to currently loged in user

                    //removes expired tickets
                    Statement statement6 = connection.createStatement();
                    statement6.execute("delete from piljetter3.user_coupons where expiry_date < current_date ");


                    Statement statement3 = connection.createStatement();
                    ResultSet resultset3 = statement3.executeQuery("select Count(*) from piljetter3.user_coupons where customer_id = '" + loggedIn.currentUser + "'");
                    resultset3.next();
                    int couponAmount = Integer.parseInt(resultset3.getString("count"));
                    //change customer id to currently loged in user


                    while (resultset.next()) {
                        //if there are enough tickets left on the concert
                        //and you can afford them or you can afford them with coupons and "use coupon" is checked
                        if ((Integer.parseInt(resultset.getString("ticket_amount")) > Integer.parseInt(buyTickets.ticketToBuy.getText())) &&
                                ((Integer.parseInt(resultset.getString("pesetas_balance")) > (Integer.parseInt(resultset.getString("price")) * Integer.parseInt(buyTickets.ticketToBuy.getText())))
                                        || (buyTickets.coupon && (Integer.parseInt(resultset.getString("pesetas_balance")) > (Integer.parseInt(resultset.getString("price")) * (Integer.parseInt(buyTickets.ticketToBuy.getText()) - couponAmount)))))) {

                            if (couponAmount < Integer.parseInt(buyTickets.ticketToBuy.getText())) {
                                couponsUsed = couponAmount;
                            } else {
                                couponsUsed = Integer.parseInt(buyTickets.ticketToBuy.getText());
                            }

                            if (buyTickets.coupon) {
                                Statement statement4 = connection.createStatement();
                                ResultSet resultset4 = statement4.executeQuery("select coupon_id from piljetter3.user_coupons where customer_id = '" + loggedIn.currentUser + "' order by expiry_date desc limit '" + couponsUsed + "' ");
                                //change customer id to currently loged in user

                                while (resultset4.next()) {
                                    Statement statement5 = connection.createStatement();
                                    statement5.execute("delete from piljetter3.user_coupons where coupon_id = '" + Integer.parseInt(resultset4.getString("coupon_id")) + "' ");
                                }
                            }

                            Statement statement2 = connection.createStatement();
                            statement2.execute("update piljetter3.concerts set ticket_amount = (ticket_amount - ('" + Integer.parseInt(buyTickets.ticketToBuy.getText()) + "')) where concert_id = ('" + buttonConcertId + "')");
                            statement2.execute("update piljetter3.user_accounts set pesetas_balance = pesetas_balance - ('" + Integer.parseInt(resultset.getString("price")) * (Integer.parseInt(buyTickets.ticketToBuy.getText()) - couponsUsed) + "') where customer_id = '" + loggedIn.currentUser + "'");
                            //change customer id to currently loged in user


                            Statement statement7 = connection.createStatement();
                            statement7.execute("insert into piljetter3.bought_tickets (concert_id, customer_id, ticket_amount, purchase_date, total_cost, coupons_used) " +
                                    "values('" + buttonConcertId + "', '" + loggedIn.currentUser + "', '" + Integer.parseInt(buyTickets.ticketToBuy.getText()) + "', current_date,('" + Integer.parseInt(resultset.getString("price")) * Integer.parseInt(buyTickets.ticketToBuy.getText()) + "'), '" + couponsUsed + "')");
                            //change customer id to currently loged in user


                            System.out.println("Successful purchase");
                        } else if (Integer.parseInt(resultset.getString("ticket_amount")) < Integer.parseInt(buyTickets.ticketToBuy.getText())) {
                            System.out.println("There are not enough tickets for your purchase");
                        } else if (Integer.parseInt(resultset.getString("pesetas_balance")) < (Integer.parseInt(resultset.getString("price")) * Integer.parseInt(buyTickets.ticketToBuy.getText()))) {
                            System.out.println("You can't afford that");
                        } else {
                            System.out.println("You found an unexpected error, try again");
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.toString());
                    System.out.println("Incorrect input, enter a number");
                }
            }
        });

        //System.out.println(buttonPositionY);
        if (buttonPositionX < 650) buttonPositionX += 200;
        else {
            buttonPositionX = 100;
            buttonPositionY += 25;
        }
    }
}