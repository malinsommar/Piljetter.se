package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SeeAll {

    void getAllBandsDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM piljetter3.bands ORDER BY band_id");

            System.out.println("______________________________________");
            System.out.println("All bands");
            //Print out result
            while(resultSet.next()){
                System.out.println("Band ID: "+resultSet.getString("band_id")+"    Band: "+resultSet.getString("band_name")+"    Genre: "+resultSet.getString("genre"));
            }
            System.out.println("______________________________________\n");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    void getAllVenuesDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM piljetter3.venues ORDER BY venue_id");

            System.out.println("______________________________________");
            System.out.println("All venues");
            //Print out result
            while(resultSet.next()){
                System.out.println("Venue ID: "+resultSet.getString("venue_id")+"    Venue: "+resultSet.getString("venue_name")+"    Country: "+resultSet.getString("country")+"    City: "+resultSet.getString("city")+"    Crowd capacity: "+resultSet.getString("crowd_capacity"));
            }
            System.out.println("______________________________________\n");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    void getAllConcertsDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM piljetter3.concerts ORDER BY concert_id");

            System.out.println("______________________________________");
            System.out.println("All concerts");
            //Print out result
            while(resultSet.next()){
                System.out.println("Concert ID: "+resultSet.getString("concert_id")+"   Band ID: "+resultSet.getString("band_id")+"    Venue ID: "+resultSet.getString("venue_id")+"    Date: "+resultSet.getString("date")+"    Ticket amount: "+resultSet.getString("ticket_amount")+"    Price: "+resultSet.getString("price"));
            }
            System.out.println("______________________________________\n");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    void getAllAdminsDatabase(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Therese25");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM piljetter3.admin_accounts");

            System.out.println("______________________________________");
            System.out.println("All admins");
            //Print out result
            while(resultSet.next()){
                System.out.println("Admin ID: "+resultSet.getString("admin_id")+"    Username: "+resultSet.getString("username")+"    Password: "+resultSet.getString("password"));
            }
            System.out.println("______________________________________\n");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
