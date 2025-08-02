package de.thi.benchmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/benchmark")
public class BenchmarkRessource {

    @Inject
    BenchmarkService benchmarkService;

    @GET
    public Response runBenchmark() throws JsonProcessingException {
        BenchmarkResult result = benchmarkService.runBenchmark();
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(result);
        System.out.println(json);
        return Response.ok(result).build();

    }
}
