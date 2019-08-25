package com.adcash;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        List<Integer> a = new ArrayList<>();
        a.add(5);
        a.add(7);
        a.add(9);
        a.add(10);

        Integer integer = a.stream().max(Integer::compareTo).get();
        return "Got it!" +integer;
    }
}