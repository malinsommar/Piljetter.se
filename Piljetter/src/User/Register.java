package User;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Register {
    JFrame frame = new JFrame();
    JPanel register = new JPanel();
    TextField usernameField;
    TextField passwordField;
    JButton loginButton,exitLoginButton,registerButton;
    JLabel error, error2;

    String usernameInput, pasInput;

    LoggedIn loggedIn = new LoggedIn();
    BuyTickets buyTickets = new BuyTickets();

    void start() {

        frame.setBounds(370,120,600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        //hämtar data om panelerna
        loginPanel();
        loggedIn.menu();
        loggedIn.buyPesetas();
        //loggedIn.ticketsList();
        //loggedIn.couponsList();
        //loggedIn.seeConcerts();

        //lägg till panelerna i framen
        frame.add(register);
        frame.add(loggedIn.mainMenuPanel);
        frame.add(loggedIn.searchPanel);
        frame.add(loggedIn.pesetasPanel);
        frame.add(loggedIn.concertsPanel);
        frame.add(buyTickets.buyTicketsPanel);
        frame.add(loggedIn.ticketsListPanel);
        frame.add(loggedIn.couponsListPanel);
        frame.add(buyTickets.buyTicketsPanel);



        frame.setVisible(true);
        register.setVisible(true);

        exitLoginButton.addActionListener(e->System.exit(0));

        registerButton.addActionListener(e->{
            usernameInput = usernameField.getText();
            pasInput = passwordField.getText();

            if (usernameInput.equals("")||pasInput.equals("")) {
                error2.setVisible(true);
            }
            else {
                registerNewUser();
            }
        });
        loginButton.addActionListener(e-> {
                    usernameInput = usernameField.getText();
                    });
                    loginButton.addActionListener(e -> {
                        usernameInput = usernameField.getText();
                        pasInput = passwordField.getText();

                        getUser();
                        if (!loggedIn.currentUser.equals("")) {
                            register.setVisible(false);
                            loggedIn.mainMenuPanel.setVisible(true);
                            updateMenu();

                            loggedIn.getCoupons();
                        } else {
                            System.out.println("incorrect username/password combination");
                        }
                    });

                    loggedIn.logOutButton.addActionListener(e -> {
                        register.setVisible(true);
                        loggedIn.mainMenuPanel.setVisible(false);
                        usernameField.setText("");
                        passwordField.setText("");
                        usernameField.setText("");
                        passwordField.setText("");
                        loggedIn.currentUser = "";

                    });


                    loggedIn.buyPesetasButton.addActionListener(e -> {
                        loggedIn.pesetasPanel.setVisible(true);
                        loggedIn.mainMenuPanel.setVisible(false);
                    });

                    loggedIn.allConcertsButton.addActionListener(e -> {
                        buyTickets.allConcerts();
                        buyTickets.buyTicketsPanel.setVisible(true);
                        loggedIn.mainMenuPanel.setVisible(false);
                    });


                    loggedIn.backButton.addActionListener(e -> {
                        loggedIn.mainMenuPanel.setVisible(true);
                        loggedIn.pesetasPanel.setVisible(false);
                    });


                    loggedIn.confirmButton.addActionListener(e -> {
                        loggedIn.pesetasAmount();
                        loggedIn.pesetasPanel.setVisible(false);
                        loggedIn.mainMenuPanel.setVisible(true);
                    });

       /* loggedIn.allTicketsButton.addActionListener(e -> {

        });*/

                    loggedIn.allTicketsButton.addActionListener(e -> {
                        loggedIn.getTicketsList();
                    });

                    loggedIn.allCouponsButton.addActionListener(e -> {
                        loggedIn.mainMenuPanel.setVisible(false);
                        loggedIn.couponsListPanel.setVisible(true);
                    });

                   /* loggedIn.backButton2.addActionListener(e -> {
                        loggedIn.ticketsListPanel.setVisible(false);
                        loggedIn.mainMenuPanel.setVisible(true);
                    });

                    loggedIn.backButton3.addActionListener(e -> {
                        loggedIn.couponsListPanel.setVisible(false);
                        loggedIn.mainMenuPanel.setVisible(true);
                    });*/


            buyTickets.backToMenu.addActionListener(e ->{
                buyTickets.allConcerts();
                buyTickets.buyTicketsPanel.setVisible(false);
                loggedIn.mainMenuPanel.setVisible(true);
            });
        }

        void updateMenu(){

            try{

                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

                Statement statement = connection.createStatement();

                ResultSet testResult = statement.executeQuery("select pesetas_balance from piljetter3.user_accounts where customer_id = "+loggedIn.currentUser+"");

                while(testResult.next()){
                    String x =  testResult.getString("pesetas_balance");
                    loggedIn.pesetas.setText("Pesetas: "+ x);
                }
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }

        void getUser(){

            try{
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT customer_id FROM piljetter3.user_accounts WHERE (email = '"+usernameInput+"') and (password = '"+pasInput+"') ");

                while(resultSet.next()){
                    loggedIn.currentUser = resultSet.getString("customer_id");
                }

            }
            catch (Exception e){
                System.out.println(e.toString());
                System.out.println("ojoj");
            }
        }

        private void loginPanel(){

            //Panel items
            JLabel adminLogin = new JLabel("Piljetter login ");
            adminLogin.setForeground(Color.black);
            adminLogin.setFont(new Font("serif", Font.PLAIN,40));
            adminLogin.setBounds(190,30,1000,50);

            usernameField = new TextField("");
            usernameField.setBounds(155,150,300,30);
            usernameField.setFont(new Font("serif", Font.PLAIN,20));

            passwordField = new TextField("");
            passwordField.setBounds(155,230,300,30);

            loginButton = new JButton("Log in");
            loginButton.setForeground(Color.black);
            loginButton.setBackground(Color.white);
            loginButton.setBounds(270,300,100,50);
            loginButton.setFocusPainted(false);
            loginButton.setFont(new Font("serif", Font.PLAIN,20));

            exitLoginButton = new JButton("Exit");
            exitLoginButton.setForeground(Color.black);
            exitLoginButton.setBackground(Color.white);
            exitLoginButton.setBounds(150,300,100,50);
            exitLoginButton.setFocusPainted(false);
            exitLoginButton.setFont(new Font("serif", Font.PLAIN,20));

            registerButton = new JButton("Register");
            registerButton.setForeground(Color.black);
            registerButton.setBackground(Color.white);
            registerButton.setBounds(390,300,100,50);
            registerButton.setFocusPainted(false);
            registerButton.setFont(new Font("serif", Font.PLAIN,20));


            error = new JLabel("Wrong username or password.");
            error.setForeground(Color.red);
            error.setBounds(220,320,1000,100);
            error.setVisible(false);
            error.setFont(new Font("serif", Font.PLAIN,15));

            error2 = new JLabel("Fields cannot be empty.");
            error2.setForeground(Color.red);
            error2.setBounds(220,320,1000,100);
            error2.setVisible(false);
            error2.setFont(new Font("serif", Font.PLAIN,15));

            JLabel usernameLabel = new JLabel("Enter Username:");
            usernameLabel.setForeground(Color.black);
            usernameLabel.setBounds(240,120,1000,20);
            usernameLabel.setFont(new Font("serif", Font.PLAIN,20));

            JLabel passwordLabel = new JLabel("Enter Password:");
            passwordLabel.setForeground(Color.black);
            passwordLabel.setBounds(245,160,1000,100);
            passwordLabel.setFont(new Font("serif", Font.PLAIN,20));

            //Panel
            register.setBounds(0,0,600, 400);
            register.setLayout(null);
            register.setBackground(Color.white);
            register.setVisible(false);
            register.add(adminLogin);
            register.add(usernameField);
            register.add(passwordField);
            register.add(loginButton);
            register.add(exitLoginButton);
            register.add(registerButton);
            register.add(error);
            register.add(error2);
            register.add(passwordLabel);
            register.add(usernameLabel);
        }

        private void registerNewUser() {
            try{

                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");

                Statement statement = connection.createStatement();

                statement.executeQuery("insert into piljetter3.user_accounts(email,password,pesetas_balance) values ('"+usernameInput+"','"+pasInput+"',150)");

            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }