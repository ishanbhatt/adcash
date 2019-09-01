package com.adcash;

import com.DAO.CategoryDAO;
import com.models.Category;

import javax.validation.Valid;
import javax.ws.rs.*;
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
        // Below logic is applied to get different response for XML and Json, it's just POC
        // It won't be done for any other endpoint
        GenericEntity list = null;
        if(accept.toLowerCase().contains("json")) {
            List<String> stringList = categories.stream().map(Category::getName).collect(Collectors.toList());
            list = new GenericEntity<List<String>>(stringList){};
        }
        else list = new GenericEntity<List<Category>>(categories){};
        return Response.ok(list).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCategoryById(@PathParam("id") Integer id) throws SQLException{

        Category category = categoryDAO.getCategory(id);
        if(category == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Category Not Found").build();
        return Response.ok(category).build();
    }

    @POST
    @Path("/add")
    public Response addCategory(@Valid Category category) throws SQLException {
        int updateStatus;
        if(category == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("CATEGORY TO BE ADDED CAN NOT BE NULL").build();
        else
             updateStatus = categoryDAO.addCategory(category);
            if (updateStatus == 1)
                return Response.ok(Response.Status.CREATED).entity("CATEGORY ADDED").build();

        return Response.status(Response.Status.EXPECTATION_FAILED).build();
    }

}