package fr.juliuselgringo.sparadrap.DAO.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DriverManager;

/**
 * Classe qui sécurise une connection unique
 */
public class Singleton {

    private static final Logger logger = LogManager.getLogger(Singleton.class);
    private static final Properties props = new Properties();
    private static Connection connection;

    private final String PATHCONF = "configDB.properties";

    /**
     * constructeur de la connexion
     */
    private Singleton() {

        try(InputStream is = Singleton.class.getClassLoader().getResourceAsStream(PATHCONF)){

            // chargement properties
            props.load(is);

            // chargement du driver
            Class.forName(props.getProperty("jdbc.driver.class"));

            // création de la connexion
            String url = props.getProperty("jdbc.url");
            String user = props.getProperty("jdbc.user");
            String password = props.getProperty("jdbc.password");

            connection = DriverManager.getConnection(url, user, password);

        }catch(IOException | ClassNotFoundException e){
            System.err.printf("Error loading driver: ", e.getMessage());
            logger.error("Error loading driver: ", e);
        } catch (SQLException e) {
            System.err.printf("Error connection mysql: ", e.getMessage());
            logger.error("Error connection to DB MYSQL: ", e);
        }

    }

    /**
     * créateur de l'instance de connexion
     * @return Connection
     */
    public static Connection getInstanceDB() {

        try{
            if (getConnection() == null || getConnection().isClosed()) {

                new Singleton();

            }else{
                logger.info("Connection already existing");
            }
        } catch (SQLException e) {
            System.err.printf("Error getting database connection: ", e.getMessage());
            logger.error("Error getting database connection: ", e);
        }

        return connection;

    }

    /**
     * getter connexion
     * @return Connection
     */
    private static Connection getConnection() {
        return connection;
    }

    /**
     * ferme la connection
     */
    public static void closeInstanceDB() {
        try{
            if(getConnection() != null && !getConnection().isClosed()){
                getConnection().close();
            }
        } catch (SQLException e) {
            System.err.printf("Error closing connection: ", e.getMessage());
            logger.error("Error closing connection: ", e);
        }
    }

}
