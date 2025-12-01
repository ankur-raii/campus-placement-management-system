/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trandpl.dbutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author hmayw
 */
public class DBConnection {
 
    private static Connection DBConnection;
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;
    
    static{
        loadDatabaseConfig();
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver successfully loaded");
            DBConnection=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the Database");
        }
        catch(ClassNotFoundException ex)
        {
            System.err.println("Driver could not be loaded: " + ex.getMessage());
            JOptionPane.showMessageDialog(null,"Oracle JDBC Driver not found. Please add ojdbc6.jar to lib folder.","DB Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
            System.err.println("Connection failed: " + ex.getMessage());
            JOptionPane.showMessageDialog(null,"Database connection failed. Check db.properties file.\nError: " + ex.getMessage(),"DB Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private static void loadDatabaseConfig() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            props.load(fis);
            DB_URL = props.getProperty("db.url", "jdbc:oracle:thin:@//localhost:1521/xe");
            DB_USER = props.getProperty("db.user", "trandpl");
            DB_PASSWORD = props.getProperty("db.password", "trandpl");
            System.out.println("Database configuration loaded from db.properties");
        } catch (IOException ex) {
            System.out.println("db.properties not found, using default values");
            DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
            DB_USER = "trandpl";
            DB_PASSWORD = "trandpl";
        }
    }
    
    public static Connection getConnection()
    {
        return DBConnection;
    }
    
    public static void closeConnection()
    {
        if(DBConnection!=null)
        {
            try{
                DBConnection.close();
                System.out.println("Database connection closed");
            }
            catch(SQLException ex)
            {
                System.err.println("Connection could not be closed: " + ex.getMessage());
            }
        }
    }
}


