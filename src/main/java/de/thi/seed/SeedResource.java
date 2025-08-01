package de.thi.seed;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/seed")
@ApplicationScoped
public class SeedResource {

    @Inject
    SeedService seedService;

    @POST
    @Path("/{users}/{products}/{orders}")
    public Response runSeed(
            @PathParam("users") int userCount,
            @PathParam("products") int productCount,
            @PathParam("orders") int orderCount
    ) {
        seedService.seed(userCount, productCount, orderCount);
        return Response.ok("Seeding done!").build();
    }
}

