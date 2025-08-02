package de.thi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClearService {

    @Inject
    EntityManager em;

    @Transactional
    public void clear(){
        em.createQuery("DELETE FROM OrderItem").executeUpdate();
        em.createQuery("DELETE FROM Order").executeUpdate();
        em.createQuery("DELETE FROM Product").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();


        System.out.println("All data cleared successfully.");
    }
}
