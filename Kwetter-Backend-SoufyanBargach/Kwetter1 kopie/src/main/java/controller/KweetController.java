package controller;

import domain.Kweet;
import domain.User;
import service.TweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/kweet")
@Produces({MediaType.APPLICATION_JSON})
public class KweetController {

    @Inject
    private TweetService tweetService;

    @Inject
    private UserService userService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        Kweet tweet = tweetService.findById(id);
        return Response.ok(tweet).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postKweet(Kweet kweet) {
        Kweet postedKweet = tweetService.create(new Kweet(kweet.getMessage(), kweet.getUser()));
        if (postedKweet == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(postedKweet).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteKweet(@PathParam("id") long id) {
        tweetService.delete(id);
        return Response.ok().build();
    }
}
