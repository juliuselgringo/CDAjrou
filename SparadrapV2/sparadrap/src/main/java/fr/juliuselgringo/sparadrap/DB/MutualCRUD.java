package fr.juliuselgringo.sparadrap.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.*;

import fr.juliuselgringo.sparadrap.ExceptionTracking.InputException;
import fr.juliuselgringo.sparadrap.model.Contact;
import fr.juliuselgringo.sparadrap.model.Mutual;

public class MutualCRUD {

    private static final Logger logger = LogManager.getLogger(MutualCRUD.class);
    
    public static List<Mutual> selectAllMutual(Connection con) throws InputException{
        
        List<Mutual> mutualsList = new ArrayList<>();
        
        String allMutual = "SELECT * FROM mutual";

        try{
            //declaration statement
            Statement stmt = con.createStatement();
            // exécution de la requête
            ResultSet res = stmt.executeQuery(allMutual);

            // récupération des résultats
            while(res.next()){
                String mutualName = res.getString("mutual_name");
                Double rate = res.getDouble("rate");
                Integer contactId = res.getInt("contact_id");

                Contact contact = ContactCRUD.selectContactById(con, contactId);

                Mutual mutual = new Mutual(mutualName, contact, rate);

                mutualsList.add(mutual);
            }
            
            

        }catch(SQLException e){
            System.out.println("Error selecting all mutual: " + e.getMessage());
            logger.error("Error selecting all mutual: " + e);
        }
        
        
        return mutualsList;
    }
}
