package com.adcash;


import com.DAO.UserDAO;
import com.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.SQLException;
import java.util.List;

@Path("users")
public class Users {

    UserDAO userDAO = new UserDAO();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll() throws SQLException {

        List<User> allUsers = userDAO.getAllUsers();
        GenericEntity<List<User>> list = new GenericEntity<List<User>>(allUsers){};
        return Response.ok(list).build();

    }

}
