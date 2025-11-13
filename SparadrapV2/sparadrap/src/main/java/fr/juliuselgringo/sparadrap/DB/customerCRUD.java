package fr.juliuselgringo.sparadrap.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.juliuselgringo.sparadrap.model.Customer;

/**
 * requête CRUD de la table customer
 */
public class CustomerCRUD {
    
    public static List<Customer> selectFromCustomer(Connection con){

        String selectAllCustomers = "SELECT * FROM customer";
        List<Customer> customersList = new ArrayList<>();

        try{
            Statement stmt = con.createStatement();

            ResultSet res = stmt.executeQuery(selectAllCustomers);

            while(res.next()){
                String firstName = res.getString("customer_firstName");
                String lastName = res.getString("customer_lastname");
                Integer contact_id = res.getInt("contact_id");
                String socialSecurityId = res.getString("social_security_id");

                //Customer newCustomer = new Customer(firstName, lastName, socialSecurityId);
            }
            

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return customersList;

    }
}
