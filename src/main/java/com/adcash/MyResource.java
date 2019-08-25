package com.adcash;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Ishan on 24/08/19.
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        Connection conn;
        String url = "jdbc:sqlite:C:\\Users\\SONY\\Desktop\\Adcash\\Adcash";

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            return "Got it!"+ conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("EXCEPTIO IS "+e.getMessage());
        }

        return "NOTHING FOUND";
    }
}