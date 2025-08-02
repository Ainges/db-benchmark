package de.thi.benchmark;

import de.thi.entity.Order;
import de.thi.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.Duration;
import java.util.List;

@ApplicationScoped
public class BenchmarkService {

    private static final Logger LOGGER = Logger.getLogger(BenchmarkService.class);

    @Transactional
    public BenchmarkResult runBenchmark() {
        BenchmarkResult result = new BenchmarkResult();

        // 1. find User by ID
        var start = System.nanoTime();
        var user = User.findById(1L);
        var end = System.nanoTime();
        result.add("findUserById", Duration.ofNanos(end - start).toMillis());

        // 2. Complex join: orders with user + order items + product
        start = System.nanoTime();
        List<Order> orders = Order.find("""
            SELECT o FROM Order o
            JOIN FETCH o.user u
            WHERE u.email LIKE ?1
        """, "%example.com").list();
        end = System.nanoTime();
        result.add("findOrdersWithUser", Duration.ofNanos(end - start).toMillis());

        return result;
    }
}


