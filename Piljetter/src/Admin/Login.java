package Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Login {

    Search search = new Search();

    private Add add = new Add();
    private Edit edit = new Edit();
    private Delete delete = new Delete();
    private Coupon coupon = new Coupon();
    private SeeAll seeAll = new SeeAll();
    private Statistics statistics = new Statistics();

    private JFrame adminFrame = new JFrame();
    private JPanel loginPanel = new JPanel();
    private JPanel menuPanel = new JPanel();

    private ButtonGroup group1,group2;

    private TextField username;
    private JPasswordField password;
    private JLabel error;
    private JButton loginButton, exitLoginButton,logoutButton,nextButton,addCouponButton,statisticButton;
    private JRadioButton addRadio,editRadio,deleteRadio,searchRadio,radioBand,radioVenue,radioConcert,radioAdmin;

    String usernameInput, passwordInput, loginResult = "fail";

    void start(){
        adminFrame.setBounds(370,120,600, 400);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setUndecorated(true);

        getAdminPanel();
        getMenuPanel();
        add.addBand();
        add.addVenue();
        add.addConcert();
        add.addAdmin();
        coupon.giveCoupon();
        edit.editBand();
        edit.editVenue();
        delete.deleteConcert();
        statistics.statMenu();
        statistics.getFirstStatPanel();
        statistics.getSecondStatPanel();
        statistics.getThirdStatPanel();

        search.getSearchPanel();
        adminFrame.add(search.searchPanel);


        adminFrame.add(loginPanel);
        adminFrame.add(menuPanel);
        adminFrame.add(add.addBandPanel);
        adminFrame.add(add.addVenuePanel);
        adminFrame.add(add.addConcertPanel);
        adminFrame.add(add.addAdminPanel);
        adminFrame.add(coupon.couponPanel);
        adminFrame.add(edit.editBandPanel);
        adminFrame.add(edit.editVenuePanel);
        adminFrame.add(delete.deleteConcertPanel);
        adminFrame.add(statistics.statMenuPanel);
        adminFrame.add(statistics.firstStatPanel);
        adminFrame.add(statistics.secondStatPanel);
        adminFrame.add(statistics.thirdStatPanel);

        loginPanel.setVisible(true);
        adminFrame.setVisible(true);

        //________________Login________________

        loginButton.addActionListener(e -> {
            usernameInput = username.getText();
            passwordInput = password.getText();

            loginDatabase();

                if (usernameInput.equals(loginResult)){
                    loginPanel.setVisible(false);
                    backToMenu();
                }
                else{
                    error.setVisible(true);
                }
        });

        exitLoginButton.addActionListener(e -> {
            //System.exit(0);
          search.searchPanel.setVisible(true);
          loginPanel.setVisible(false);
          search.bandField.setText("");
          search.venueField.setText("");
          search.countryField.setText("");
          search.cityField.setText("");
          search.startDateField.setText("");
          search.endDateField.setText("");
        });

        search.doneButton.addActionListener(e->{
            search.band = search.bandField.getText();
            search.venue = search.venueField.getText();
            search.country = search.countryField.getText();
            search.city = search.cityField.getText();
            search.startDate = search.startDateField.getText();
            search.endDate = search.endDateField.getText();

            if (search.startDate.equals("") || search.endDate.equals("")){
                search.searchWithoutDateDatabase();
            }
            else{
                search.searchWithoutDateDatabase();
            }
        });


        //________________MainMenu________________

        logoutButton.addActionListener(e-> {
            loginPanel.setVisible(true);
            menuPanel.setVisible(false);
            username.setText("");
            password.setText("");
        });

        nextButton.addActionListener(e-> checkRadio());

        addCouponButton.addActionListener(e-> {

            coupon.couponError.setVisible(false);
            menuPanel.setVisible(false);
            coupon.userIdField.setText("");
            coupon.emailField.setText("");
            coupon.couponPanel.setVisible(true);
        });

        statisticButton.addActionListener(e-> {
            statistics.statMenuPanel.setVisible(true);
            menuPanel.setVisible(false);
        });

        //_________________Add_________________
        //Band
        add.doneBandButton.addActionListener(e-> {
            add.band = add.bandField.getText();
            add.genre = add.genreField.getText();
            add.bandPop = add.bandPopField.getText();

            if (add.band.equals("") || add.genre.equals("") || add.bandPop.equals("")){
                add.bandError.setVisible(true);
            }
            else {
                add.addBandPanel.setVisible(false);
                add.databaseAddBand();
                backToMenu();
            }
        });
        add.backBandButton.addActionListener(e-> {
            add.addBandPanel.setVisible(false);
            backToMenu();
        });

        //Venue
        add.doneVenueButton.addActionListener(e-> {
            add.venue = add.venueNameField.getText();
            add.country = add.countryField.getText();
            add.city = add.cityField.getText();
            add.venuePop = add.venuePopField.getText();
            add.crowd = add.crowdField.getText();

            if (add.venue.equals("") || add.country.equals("") || add.city.equals("") || add.venuePop.equals("") || add.crowd.equals("")){
                add.venueError.setVisible(true);
            }
            else {
                add.addVenuePanel.setVisible(false);
                add.databaseAddVenue();
                backToMenu();
            }
        });

        add.backVenueButton.addActionListener(e->{
            add.addVenuePanel.setVisible(false);
            backToMenu();
        });

        //Concert
        add.doneConcertButton.addActionListener(e-> {
            add.bandId = add.bandIdField.getText();
            add.venueId = add.venueIdField.getText();
            add.date = add.dateField.getText();

            add.isBandBusyDatabase();
            add.hasDatePassedDatabase();
            add.isVenueBusyDatabase();

            //Check if all fields are filled.
            if (add.bandId.equals("") || add.venueId.equals("") || add.date.equals("")){
                add.bandPlaysError.setVisible(false);
                add.venuePlaysError.setVisible(false);
                add.datePassedError.setVisible(false);
                add.concertError.setVisible(true);
            }
            //Check if band is busy on given date
                else if (add.bandAlreadyPlays){
                    add.concertError.setVisible(false);
                    add.venuePlaysError.setVisible(false);
                    add.datePassedError.setVisible(false);
                    add.bandPlaysError.setVisible(true);
                    add.bandAlreadyPlays = false;
                    add.venueAlreadyPlays = false;
            }
                 //Check if venue is busy on given date
                 else if (add.venueAlreadyPlays){
                    add.bandPlaysError.setVisible(false);
                    add.concertError.setVisible(false);
                    add.datePassedError.setVisible(false);
                    add.venuePlaysError.setVisible(true);
                    add.venueAlreadyPlays = false;
                    add.bandAlreadyPlays = false;
            }
                 //Check if date has passed
                else if (add.datePassed){
                    add.bandPlaysError.setVisible(false);
                    add.concertError.setVisible(false);
                    add.venuePlaysError.setVisible(false);
                    add.datePassedError.setVisible(true);
                    add.datePassed = false;
            }
                    else {
                        add.addConcertPanel.setVisible(false);
                        add.getBandCostDatabase();
                        add.getVenueCostDatabase();
                        add.getCrowdCapacityDataBase();
                        add.databaseAddConcert();
                        add.insertOrganiserCost();
                        backToMenu();
                        add.organizerCost = 0;
                    }
        });
        add.backConcertButton.addActionListener(e->{
            add.addConcertPanel.setVisible(false);
            menuPanel.setVisible(true);
            backToMenu();
    });

    //Admin
        add.doneAdminButton.addActionListener(e-> {
            add.adminUsername = add.adminUsernameField.getText();
            add.adminPassword = add.adminPasswordField.getText();

            if (add.adminUsername.equals("") || add.adminPassword.equals("")){
                add.adminError.setVisible(true);
            }
            else {
                add.addAdminPanel.setVisible(false);
                add.databaseAddAdmin();
                backToMenu();
            }
        });

        add.backAdminButton.addActionListener(e->{
            add.addAdminPanel.setVisible(false);
            menuPanel.setVisible(true);
            backToMenu();
        });

        //_________________Edit__________________
        //Band
        edit.updateBandNameButton.addActionListener(e->{
            edit.bandId = edit.bandIdField.getText();
            edit.awnser = edit.bandNameField.getText();

            if (edit.bandId.equals("")||edit.awnser.equals("")){
                edit.emptyError.setVisible(true);
            }
            else{
                edit.whatToChange = "band_name";
                edit.updateBandDatabase();
                edit.noError.setVisible(true);
            }
        });

        edit.updateGenreButton.addActionListener(e->{
            edit.bandId = edit.bandIdField.getText();
            edit.awnser = edit.genreField.getText();

            if (edit.bandId.equals("")||edit.awnser.equals("")){
                edit.emptyError.setVisible(true);
            }
            else{
                edit.whatToChange = "genre";
                edit.updateBandDatabase();
                edit.noError.setVisible(true);
            }
        });

        edit.updateVenueNameButton.addActionListener(e->{
            edit.venueId = edit.venueIdField.getText();
            edit.awnser = edit.venueNameField.getText();

            if (edit.venueId.equals("")||edit.awnser.equals("")){
                edit.emptyError.setVisible(true);
            }
            else{
                edit.whatToChange = "venue_name";
                edit.updateVenueDatabase();
                edit.noError.setVisible(true);
            }
        });

        edit.updateCountryButton.addActionListener(e->{
            edit.venueId = edit.venueIdField.getText();
            edit.awnser = edit.countryField.getText();

            if (edit.venueId.equals("")||edit.awnser.equals("")){
                edit.emptyError.setVisible(true);
            }
            else{
                edit.whatToChange = "country";
                edit.updateVenueDatabase();
                edit.noError.setVisible(true);
            }
        });

        edit.getUpdateCityButton.addActionListener(e->{
            edit.venueId = edit.venueIdField.getText();
            edit.awnser = edit.cityField.getText();

            if (edit.venueId.equals("")||edit.awnser.equals("")){
                edit.emptyError.setVisible(true);
            }
            else{
                edit.whatToChange = "city";
                edit.updateVenueDatabase();
                edit.noError.setVisible(true);
            }
        });

        edit.doneBandButton.addActionListener(e->{
            edit.editBandPanel.setVisible(false);
            backToMenu();
        });
        edit.doneVenueButton.addActionListener(e->{
            edit.editBandPanel.setVisible(false);
            backToMenu();
        });

        //_________________Delete__________________
        //Concert

        delete.deleteConcertButton.addActionListener(e-> {

            delete.conId = delete.concertIdField.getText();

            if (delete.conId.equals("")){
                delete.emptyConcertError.setVisible(true);
            }
            else {
                delete.moneyBackDatabase();
                delete.deleteTicketsDatabase();
                delete.deleteOrganizerCost();
                delete.deleteConcertDatabase();
            }
        });

        delete.backConcertButton.addActionListener(e-> {
            delete.deleteConcertPanel.setVisible(false);
            backToMenu();
        });

        //_________________Coupon__________________

        coupon.addOneButton.addActionListener(e-> {
            coupon.userId = coupon.userIdField.getText();
            coupon.email = coupon.emailField.getText();

            coupon.couponPanel.setVisible(false);
            coupon.couponDatabase();
            backToMenu();
        });
        coupon.backCouponButton.addActionListener(e->{
            coupon.couponPanel.setVisible(false);
            backToMenu();
        });

        //_________________Statistics__________________

        statistics.firstStatButton.addActionListener(e-> {
            statistics.firstStatPanel.setVisible(true);
            statistics.statMenuPanel.setVisible(false);
            statistics.firstStatStartField.setText("");
            statistics.firstStatEndField.setText("");
        });

        statistics.secondStatButton.addActionListener(e-> {
            statistics.secondStatPanel.setVisible(true);
            menuPanel.setVisible(false);
            statistics.secondStatStartField.setText("");
            statistics.secondStatEndField.setText("");
        });

        statistics.thirdStatButton.addActionListener(e-> {
            statistics.thirdStatPanel.setVisible(true);
            menuPanel.setVisible(false);
            statistics.thirdStatStartField.setText("");
            statistics.thirdStatEndField.setText("");
        });

        statistics.backButton.addActionListener(e->{
            statistics.statMenuPanel.setVisible(false);
            backToMenu();
        });
        statistics.doneFirstButton.addActionListener(e->{

            statistics.startDate = statistics.firstStatStartField.getText();
            statistics.endDate = statistics.firstStatEndField.getText();
            statistics.getTotalRevenue();
            statistics.getTotalTickets();

            System.out.println("________________________________________________");
            System.out.println("Between "+statistics.startDate + " and "+statistics.endDate);
            System.out.println("Sold tickets: "+statistics.ticketsSold);
            System.out.println("Total revenue: "+statistics.totalRevenue);
            System.out.println("________________________________________________");
        });

        statistics.doneFirstButton.addActionListener(e->{
            statistics.firstStatPanel.setVisible(false);
            backToMenu();
        });
        statistics.secondStatButton.addActionListener(e->{
            statistics.secondStatPanel.setVisible(true);
            statistics.statMenuPanel.setVisible(false);
            statistics.secondStatStartField.setText("");
            statistics.secondStatEndField.setText("");
        });
        statistics.doneSecondButton.addActionListener(e->{
            statistics.startDate = statistics.secondStatStartField.getText();
            statistics.endDate = statistics.secondStatEndField.getText();
            statistics.getBestSellingBandsDatabse();

            System.out.println("___________________________________");
            System.out.println("Bands sold tickets");
            System.out.println("Between "+statistics.startDate+" and "+statistics.endDate);

            for (int i = 0; i <statistics.sortedBand.size() ; i++) {
                System.out.println("\nBand: "+statistics.sortedBand.get(i)+"\nSold tickets: "+statistics.sortedTicket.get(i));
            }
            System.out.println("___________________________________");

            statistics.sortedTicket.clear();
            statistics.unsortedTicket.clear();
            statistics.unsortedBand.clear();
            statistics.sortedBand.clear();
        });
        statistics.backSecondButton.addActionListener(e->{
            statistics.firstStatPanel.setVisible(false);
            backToMenu();
        });
        statistics.thirdStatButton.addActionListener(e->{
            statistics.statMenuPanel.setVisible(false);
            statistics.thirdStatPanel.setVisible(true);
            statistics.thirdStatEndField.setText("");
            statistics.thirdStatStartField.setText("");
        });
        statistics.doneThirdButton.addActionListener(e->{

            statistics.startDate = statistics.thirdStatStartField.getText();
            statistics.endDate = statistics.thirdStatEndField.getText();
            statistics.getExpendituresDatabase();

            System.out.println("___________________________________________");
            System.out.println("Between "+statistics.startDate+" and "+statistics.endDate);
            System.out.println("Total expenditures: "+statistics.totalExpenditures);
            System.out.println("\nAll expenditures during chosen time:");
            for (int i = 0; i < statistics.concertIdArray.size(); i++) {
                System.out.println("Concert id: "+statistics.concertIdArray.get(i));
                System.out.println("Concert cost: "+statistics.concertCostArray.get(i)+"\n");
            }
            System.out.println("___________________________________________");

            statistics.concertIdArray.clear();
            statistics.concertCostArray.clear();
            statistics.totalExpenditures = 0;
        });
        statistics.backThirdButton.addActionListener(e->{
            statistics.thirdStatPanel.setVisible(false);
            backToMenu();
        });
    }

    void loginDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Therese25");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT username FROM piljetter3.admin_accounts WHERE username = '"+usernameInput+"' AND password = '"+passwordInput+"';");

            while (resultSet.next()){
                loginResult = resultSet.getString("username");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void backToMenu(){
        menuPanel.setVisible(true);
        group1.clearSelection();
        group2.clearSelection();
    }

    private void checkRadio(){

        //Add
            if (addRadio.isSelected()){

                if (radioBand.isSelected()) {
                    add.bandError.setVisible(false);
                    menuPanel.setVisible(false);
                    add.bandField.setText("");
                    add.genreField.setText("");
                    add.bandPopField.setText("");
                    add.addBandPanel.setVisible(true);
                }
                else if (radioVenue.isSelected()) {
                    add.venueError.setVisible(false);
                    menuPanel.setVisible(false);
                    add.venueNameField.setText("");
                    add.countryField.setText("");
                    add.cityField.setText("");
                    add.venuePopField.setText("");
                    add.crowdField.setText("");
                    add.addVenuePanel.setVisible(true);
                }
                else if (radioConcert.isSelected()) {
                    add.bandAlreadyPlays = false;
                    add.venueAlreadyPlays = false;
                    add.bandPlaysError.setVisible(false);
                    add.concertError.setVisible(false);
                    add.venuePlaysError.setVisible(false);
                    add.datePassedError.setVisible(false);
                    menuPanel.setVisible(false);
                    add.bandIdField.setText("");
                    add.venueIdField.setText("");
                    add.priceField.setText("");
                    add.dateField.setText("");
                    add.addConcertPanel.setVisible(true);

                }
                else if (radioAdmin.isSelected()) {
                    add.adminError.setVisible(false);
                    menuPanel.setVisible(false);
                    add.adminPasswordField.setText("");
                    add.adminUsernameField.setText("");
                    add.addAdminPanel.setVisible(true);
                }
            }
            //Edit
            else if (editRadio.isSelected()) {

                if (radioBand.isSelected()) {
                    edit.editBandPanel.setVisible(true);
                    menuPanel.setVisible(false);
                    edit.bandIdField.setText("");
                    edit.bandNameField.setText("");
                    edit.genreField.setText("");

                }
                else if (radioVenue.isSelected()) {
                    edit.editVenuePanel.setVisible(true);
                    menuPanel.setVisible(false);
                    edit.venueIdField.setText("");
                    edit.countryField.setText("");
                    edit.cityField.setText("");
                    edit.venueIdField.setText("");
                }
                else if (radioConcert.isSelected()) {
                    System.out.println("Can't edit concert");
                }
                else if (radioAdmin.isSelected()) {
                    System.out.println("Can't edit admin");
                }
            }
            //Delete
            else if (deleteRadio.isSelected()) {

                if (radioBand.isSelected()) {
                    System.out.println("Can't delete band");
                }
                else if (radioVenue.isSelected()) {
                    System.out.println("Can't delete venue");
                }
                else if (radioConcert.isSelected()) {
                    delete.deleteConcertPanel.setVisible(true);
                    menuPanel.setVisible(false);
                    delete.concertIdField.setText("");

                }
                else if (radioAdmin.isSelected()) {
                    System.out.println("Can't delete admin");
                }
            }
            //See all
            else if (searchRadio.isSelected()) {

                if (radioBand.isSelected()) {
                    seeAll.getAllBandsDatabase();
                }
                else if (radioVenue.isSelected()) {
                    seeAll.getAllVenuesDatabase();
                }
                else if (radioConcert.isSelected()) {
                    seeAll.getAllConcertsDatabase();
                }
                else if (radioAdmin.isSelected()) {
                    seeAll.getAllAdminsDatabase();
                }
            }
    }

    private void getMenuPanel(){

        //MenuPanel
        JLabel mainPage = new JLabel("Admin main page");
        mainPage.setForeground(Color.black);
        mainPage.setFont(new Font("serif", Font.PLAIN,30));
        mainPage.setBounds(200,20,1000,40);

        //____________ROW 1______________

        JLabel add = new JLabel("Add:");
        add.setForeground(Color.black);
        add.setFont(new Font("serif", Font.PLAIN,13));
        add.setBounds(50,98,100,20);

        addRadio = new JRadioButton();
        addRadio.setBounds(80,100,20,20);
        addRadio.setBackground(Color.white);

        JLabel edit = new JLabel("Edit:");
        edit.setForeground(Color.black);
        edit.setFont(new Font("serif", Font.PLAIN,13));
        edit.setBounds(120,98,100,20);

        editRadio = new JRadioButton();
        editRadio.setBounds(150,100,20,20);
        editRadio.setBackground(Color.white);

        JLabel delete = new JLabel("Delete:");
        delete.setForeground(Color.black);
        delete.setFont(new Font("serif", Font.PLAIN,13));
        delete.setBounds(190,98,100,20);

        deleteRadio = new JRadioButton();
        deleteRadio.setBounds(230,100,20,20);
        deleteRadio.setBackground(Color.white);

        JLabel search = new JLabel("See all:");
        search.setForeground(Color.black);
        search.setFont(new Font("serif", Font.PLAIN,13));
        search.setBounds(270,98,100,20);

        searchRadio = new JRadioButton();
        searchRadio.setBounds(310,100,20,20);
        searchRadio.setBackground(Color.white);

        group1 = new ButtonGroup();
        group1.add(addRadio);
        group1.add(deleteRadio);
        group1.add(editRadio);
        group1.add(searchRadio);

        // __________________ROW 2_____________________-

        JLabel band = new JLabel("Band");
        band.setForeground(Color.black);
        band.setFont(new Font("serif", Font.PLAIN,20));
        band.setBounds(85,158,100,20);

        radioBand = new JRadioButton();
        radioBand.setBounds(135,160,20,20);
        radioBand.setBackground(Color.white);

        JLabel venue = new JLabel("Venue");
        venue.setForeground(Color.black);
        venue.setFont(new Font("serif", Font.PLAIN,20));
        venue.setBounds(185,158,100,20);

        radioVenue = new JRadioButton();
        radioVenue.setBounds(245,160,20,20);
        radioVenue.setBackground(Color.white);

        JLabel concert = new JLabel("Concert");
        concert.setForeground(Color.black);
        concert.setFont(new Font("serif", Font.PLAIN,20));
        concert.setBounds(75,228,100,20);

        radioConcert = new JRadioButton();
        radioConcert.setBounds(145,230,20,20);
        radioConcert.setBackground(Color.white);

        JLabel admin = new JLabel("Admin account");
        admin.setForeground(Color.black);
        admin.setFont(new Font("serif", Font.PLAIN,20));
        admin.setBounds(175,228,1000,20);

        radioAdmin = new JRadioButton();
        radioAdmin.setBounds(300,230,20,20);
        radioAdmin.setBackground(Color.white);

        group2 = new ButtonGroup();
        group2.add(radioBand);
        group2.add(radioVenue);
        group2.add(radioConcert);
        group2.add(radioAdmin);

        logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.black);
        logoutButton.setBackground(Color.white);
        logoutButton.setBounds(80,300,100,50);
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(new Font("serif", Font.PLAIN,20));

        nextButton = new JButton("Next");
        nextButton.setForeground(Color.black);
        nextButton.setBackground(Color.white);
        nextButton.setBounds(220,300,100,50);
        nextButton.setFocusPainted(false);
        nextButton.setFont(new Font("serif", Font.PLAIN,20));

        addCouponButton = new JButton("Add coupon");
        addCouponButton.setForeground(Color.black);
        addCouponButton.setBackground(Color.white);
        addCouponButton.setBounds(400,130,150,50);
        addCouponButton.setFocusPainted(false);
        addCouponButton.setFont(new Font("serif", Font.PLAIN,15));

        statisticButton = new JButton("Show statistics");
        statisticButton.setForeground(Color.black);
        statisticButton.setBackground(Color.white);
        statisticButton.setBounds(400,220,150,50);
        statisticButton.setFocusPainted(false);
        statisticButton.setFont(new Font("serif", Font.PLAIN,15));

        menuPanel.setBounds(0,0,600, 400);
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.white);
        menuPanel.setVisible(false);

        menuPanel.add(mainPage);
        menuPanel.add(addRadio);
        menuPanel.add(add);
        menuPanel.add(edit);
        menuPanel.add(editRadio);
        menuPanel.add(delete);
        menuPanel.add(deleteRadio);
        menuPanel.add(search);
        menuPanel.add(searchRadio);
        menuPanel.add(logoutButton);
        menuPanel.add(nextButton);
        menuPanel.add(band);
        menuPanel.add(venue);
        menuPanel.add(concert);
        menuPanel.add(admin);
        menuPanel.add(radioBand);
        menuPanel.add(radioVenue);
        menuPanel.add(radioConcert);
        menuPanel.add(radioAdmin);
        menuPanel.add(addCouponButton);
        menuPanel.add(statisticButton);

    }

    private void getAdminPanel(){

        //Panel items
        JLabel adminLogin = new JLabel("Piljetter login ");
        adminLogin.setForeground(Color.black);
        adminLogin.setFont(new Font("serif", Font.PLAIN,40));
        adminLogin.setBounds(190,30,1000,50);

        username = new TextField("");
        username.setBounds(155,150,300,30);
        username.setFont(new Font("serif", Font.PLAIN,20));

        password = new JPasswordField("");
        password.setBounds(155,230,300,30);

        loginButton = new JButton("Log in");
        loginButton.setForeground(Color.black);
        loginButton.setBackground(Color.white);
        loginButton.setBounds(340,300,100,50);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("serif", Font.PLAIN,20));

        exitLoginButton = new JButton("Exit");
        exitLoginButton.setForeground(Color.black);
        exitLoginButton.setBackground(Color.white);
        exitLoginButton.setBounds(180,300,100,50);
        exitLoginButton.setFocusPainted(false);
        exitLoginButton.setFont(new Font("serif", Font.PLAIN,20));

        error = new JLabel("Wrong username or password.");
        error.setForeground(Color.red);
        error.setBounds(220,320,1000,100);
        error.setVisible(false);
        error.setFont(new Font("serif", Font.PLAIN,15));

        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameLabel.setForeground(Color.black);
        usernameLabel.setBounds(240,120,1000,20);
        usernameLabel.setFont(new Font("serif", Font.PLAIN,20));

        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setForeground(Color.black);
        passwordLabel.setBounds(245,160,1000,100);
        passwordLabel.setFont(new Font("serif", Font.PLAIN,20));

        //Panel
        loginPanel.setBounds(0,0,600, 400);
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.white);
        loginPanel.setVisible(false);

        loginPanel.add(adminLogin);
        loginPanel.add(username);
        loginPanel.add(password);
        loginPanel.add(loginButton);
        loginPanel.add(exitLoginButton);
        loginPanel.add(error);
        loginPanel.add(passwordLabel);
        loginPanel.add(usernameLabel);
    }
}