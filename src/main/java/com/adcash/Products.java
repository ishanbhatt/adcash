package com.adcash;

import com.DAO.CategoryDAO;
import com.DAO.ProductDAO;
import com.models.Category;
import com.models.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ishan on 24/08/19.
 */
@Path("products")
public class Products {

    ProductDAO productDAO = new ProductDAO();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll(@QueryParam("category_id") Integer category_id) throws SQLException{
        List<Product> productsByCategory = productDAO.getProductByCategory(category_id);
        GenericEntity<List<Product>> list = new GenericEntity<List<Product>>(productsByCategory){};
        return Response.ok(list).build();
    }

}