package com.adcash;


import com.DAO.ProductDAO;
import com.models.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


import java.sql.*;
import java.util.List;

/**
 * Created by Ishan on 24/08/19.
 */
@Path("products")
public class Products {

    ProductDAO productDAO = new ProductDAO();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAll(@QueryParam("category_id") Integer category_id) throws SQLException{
        List<Product> productsByCategory = productDAO.getProductByCategory(category_id);
        if (productsByCategory.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        GenericEntity<List<Product>> list = new GenericEntity<List<Product>>(productsByCategory){};
        return Response.ok(list).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getProductById(@PathParam("id") Integer id) throws SQLException{
        Product product = productDAO.getProduct(id);
        if(product == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(product).build();
    }

}