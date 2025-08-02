package de.thi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/clear")
@ApplicationScoped
public class ClearRessource {

    @Inject
    ClearService clearService;

    @DELETE
    public Response clear() {
        clearService.clear();
        return Response.ok("Clear done!").build();
    }

}
