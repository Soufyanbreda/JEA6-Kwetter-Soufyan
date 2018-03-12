package controller;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService service;

    @GET
    public Response getUsers() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{username}")
    public Response findbyUsername(@PathParam("username") String username) {
        User user = service.findByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        User createdUser = service.create(new User(user.getRole(),user.getEmail(),user.getUsername(), user.getPassword()));
        return Response.ok(createdUser).build();
    }


    @PUT
    @Path("/{id}/username")
    public Response updateUsername(@QueryParam("username") String username, @PathParam("id") long id)  {
        User user = service.findById(id);
        if(user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        service.updateUsername(username, user);
        return Response.ok(user).build();
    }

    @PUT
    @Path("{id}/following/{followingId}")
    public Response updateFollowing(@PathParam("id") long id, @PathParam("followingId") long followingId)  {
       service.addFollowing(followingId, id);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}/follower/{followerId}")
    public Response updateFollower(@PathParam("id") long id, @PathParam("followerId") long followerId)  {
        service.removeFollowing(followerId, id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        service.delete(id);
        return Response.ok().build();
    }


}


