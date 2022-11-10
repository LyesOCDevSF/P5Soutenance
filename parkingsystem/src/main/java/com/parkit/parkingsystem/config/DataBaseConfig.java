package com.parkit.parkingsystem.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.util.Properties;
import java.sql.*;

public class DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseConfig");

    //private static ConnexionBDD connexionBDD = new ConnexionBDD();




    public Connection getConnectionTest() throws ClassNotFoundException, SQLException, IOException {

        Properties props = new Properties();
        FileInputStream fis = null;
        Connection con = null;
        try{
            fis = new FileInputStream("src/main/java/domaine");
            props.load(fis);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (fis != null){
                fis.close();
            }
        }


        logger.info("Create DB connection");
        Class.forName(props.getProperty("sgbd.driver"));
        //Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(props.getProperty("sgbd.url"), props.getProperty("sgbd.login"), props.getProperty("sgbd.password"));
        return con;
                //DriverManager.getConnection(
               // "jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Kumquatfroid8708*");
    }


    public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {

        Properties props = new Properties();
        FileInputStream fis = null;
        Connection con = null;
        try{
            fis = new FileInputStream("src/main/java/domaine");
            props.load(fis);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (fis != null){
                fis.close();
            }
        }



        logger.info("Create DB connection");
        Class.forName(props.getProperty("sgbd.driver"));
        //Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(props.getProperty("sgbd.url"), props.getProperty("sgbd.login"), props.getProperty("sgbd.password"));
        return con;

                /*DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/prod?serverTimezone=UTC","root","Kumquatfroid8708*");*/
    }


    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.error("Error while closing connection",e);
            }
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
                logger.info("Closing Prepared Statement");
            } catch (SQLException e) {
                logger.error("Error while closing prepared statement",e);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
                logger.info("Closing Result Set");
            } catch (SQLException e) {
                logger.error("Error while closing result set",e);
            }
        }
    }
}
