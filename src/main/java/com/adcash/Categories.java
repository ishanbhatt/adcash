package com.adcash;

import com.DAO.CategoryDAO;
import com.models.Category;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;


import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ishan on 24/08/19.
 */
@Path("categories")
public class Categories {

    CategoryDAO categoryDAO = new CategoryDAO();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll(@Context HttpHeaders httpHeaders) throws SQLException{
        String accept = httpHeaders.getHeaderString("Accept");

        List<Category> categories = categoryDAO.getAllCategories();
        GenericEntity list = null;
        if(accept.toLowerCase().contains("json")) {
            List<String> stringList = categories.stream().map(Category::getName).collect(Collectors.toList());
            list = new GenericEntity<List<String>>(stringList){};
        }
        else list = new GenericEntity<List<Category>>(categories){};
        return Response.ok(list).build();
    }

}