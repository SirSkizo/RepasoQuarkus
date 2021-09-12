package org.acme.rest.json;

import java.net.URI;
import java.util.*;
import javax.ws.rs.*;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;


import io.quarkus.panache.common.Sort;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    @GET
    public List<Fruit> list() {
        return Fruit.listAll(Sort.by("name"));
    }

    @GET
    @Path("/{id}")
    public Fruit get(@PathParam("id") Long id) {
        Fruit entity = Fruit.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        return entity;
    }


    @POST
    @Transactional
    public Response create(Fruit fruit) {
        fruit.persist();
        return Response.created(URI.create("/fruits/" + fruit.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Fruit update(@PathParam("id") Long id, Fruit fruit) {
        Fruit entity = Fruit.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        entity.name = fruit.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Fruit entity = Fruit.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

    @GET
    @Path("/search/{name}")
    public Fruit search(@PathParam("name") String name) {
        return Fruit.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return Fruit.count();
    }
}