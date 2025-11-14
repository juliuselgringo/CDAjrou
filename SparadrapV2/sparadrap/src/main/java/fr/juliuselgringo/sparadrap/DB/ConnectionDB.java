package fr.juliuselgringo.sparadrap.DB;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.logging.log4j.*;

/**
 * classe permettant de gérer la connexion à mysql
 */
public class ConnectionDB{

    private static final Logger logger = LogManager.getLogger(MutualCRUD.class);

    /**
     * connexion à mysql
     * @return Connection
     */
    public static Connection propertiesConnection() {
        
        Connection connection = null;

        //chemin properties
        final String PATHCONF = "configDB.properties";

        // attribut properties
        Properties prop = new Properties();

        //chargement properties
        try(InputStream is = ConnectionDB.class.getClassLoader().getResourceAsStream(PATHCONF)){

            prop.load(is);

        }catch(IOException e){
            System.err.printf("Error loading properties file: ", e.getMessage());
            logger.error("Error loading properties file: ", e.getMessage());
        }

        // connexion à la DB
        try{

            // chargement du driver
            Class.forName(prop.getProperty("jdbc.driver.class"));

            //récup des paramètres de connexion
            String url = prop.getProperty("jdbc.url");
            String user = prop.getProperty("jdbc.user");
            String password = prop.getProperty("jdbc.password");

            connection = DriverManager.getConnection(url,user,password);
            
            System.out.println("Connected to database from properties");

            

        }catch(ClassNotFoundException e){
            System.err.println("Error loading JDBC Driver: " + e.getMessage());
            logger.error("Error loading JDBC Driver: " + e.getMessage());
        }catch(SQLException e){
            System.err.println("Error connection MySql: " + e.getMessage());
            logger.error("Error connection MySql: " + e.getMessage());
        }

        return connection;

    }

    /**
     * déconnexion de mysql
     * @param con
     */
    public static void closeConnectionDB(Connection con){

        if(con != null){
            try{

                con.close();
                System.out.println("Connection closed successfully");

            }catch(SQLException e){

                System.err.println("Error closing connection");
                logger.error("Error closing connection" + e.getMessage());

            }
        }

    }

}
