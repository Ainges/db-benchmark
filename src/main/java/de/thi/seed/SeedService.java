package de.thi.seed;

import de.thi.entity.Product;
import de.thi.entity.User;
import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class SeedService {

    private static final Logger LOGGER = Logger.getLogger(SeedService.class);

    @Inject
    SeedBatchService batchService;

    @TransactionConfiguration(timeout = 7200) // 2h
    @Transactional
    public void seed(int userCount, int productCount, int orderCount) {
        LOGGER.infof("Starte Seeding: %d Nutzer, %d Produkte, %d Bestellungen", userCount, productCount, orderCount);

        List<User> users = batchService.seedUsers(userCount);
        LOGGER.info("User-Seeding abgeschlossen.");

        List<Product> products = batchService.seedProducts(productCount);
        LOGGER.info("Product-Seeding abgeschlossen.");

        batchService.seedOrders(orderCount, users, products);
        LOGGER.info("Order-Seeding abgeschlossen.");
    }
}
