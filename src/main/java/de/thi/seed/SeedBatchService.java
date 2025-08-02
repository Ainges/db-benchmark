package de.thi.seed;

import de.thi.entity.Order;
import de.thi.entity.OrderItem;
import de.thi.entity.Product;
import de.thi.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static jakarta.transaction.Transactional.TxType.REQUIRES_NEW;

@ApplicationScoped
public class SeedBatchService {

    private static final Logger LOGGER = Logger.getLogger(SeedBatchService.class);

    private final Random random = new Random();

    @PersistenceContext
    EntityManager em;

    public List<User> seedUsers(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User u = new User();
            u.name = "User " + i;
            u.email = "user" + i + "@mail.com";
            u.createdAt = LocalDateTime.now().minusDays(random.nextInt(365));
            em.persist(u);
            users.add(u);

            if (i % 500 == 0) {

                LOGGER.infof("  → %d Nutzer erstellt", i);
            }
        }
        return users;
    }

    public List<Product> seedProducts(int count) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Product p = new Product();
            p.name = "Product " + i;
            p.description = "A useful product number " + i;
            p.price = BigDecimal.valueOf(5 + random.nextInt(100));
            p.category = switch (i % 5) {
                case 0 -> "Books";
                case 1 -> "Electronics";
                case 2 -> "Toys";
                case 3 -> "Fashion";
                default -> "Misc";
            };
            p.stock = 100 + random.nextInt(900);
            p.createdAt = LocalDateTime.now().minusDays(random.nextInt(60));
            p.persist();
            products.add(p);

            if (i % 500 == 0) {

                LOGGER.infof("  → %d Produkte erstellt", i);
            }
        }
        return products;
    }

    // todo: remove batching for orders
    public void seedOrders(int count, List<User> users, List<Product> products) {
        for (int i = 0; i < count; i++) {
            Order order = new Order();
            order.user = users.get(random.nextInt(users.size()));
            order.createdAt = LocalDateTime.now().minusDays(random.nextInt(30));
            order.status = switch (random.nextInt(3)) {
                case 0 -> "PENDING";
                case 1 -> "SHIPPED";
                default -> "CANCELLED";
            };
            order.persist();

            if (i % 500 == 0) {

                LOGGER.infof("  → %d Order erstellt", i);
            }

            int itemCount = 1 + random.nextInt(4);
            for (int j = 0; j < itemCount; j++) {
                OrderItem item = new OrderItem();
                item.order = order;
                Product product = products.get(random.nextInt(products.size()));
                item.product = product;
                item.quantity = 1 + random.nextInt(3);
                item.priceAtOrder = product.price;
                item.persist();

            }
        }
    }
}

