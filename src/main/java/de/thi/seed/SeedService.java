package de.thi.seed;

import de.thi.entity.Product;
import de.thi.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class SeedService {

    private static final Logger LOGGER = Logger.getLogger(SeedService.class);

    @Inject
    SeedBatchService batchService;

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
