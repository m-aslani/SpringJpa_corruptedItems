package com.example.springjpa_corrupteditems;

import com.example.springjpa_corrupteditems.entity.Corrupted;
import com.example.springjpa_corrupteditems.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaBasedRun {

    @PersistenceContext
    private EntityManager entityManager;


    public void sampleRun() {
        entityManager = entityManager.getEntityManagerFactory().createEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Transaction transaction = session.getTransaction();

///////Save//////////////
        transaction.begin();
        Corrupted corruptedItem = new Corrupted();
        corruptedItem.setReason("some reason!!");

        Item corruptedItem1 = new Item();
        corruptedItem1.setName("corruptedItem1");
        corruptedItem1.setPrice("1200$");
        corruptedItem1.setDescription("corruptedItem1");
        corruptedItem1.setCorrupted(corruptedItem);
        session.save(corruptedItem1);
        transaction.commit();

        transaction.begin();
        Corrupted corrupted2 = new Corrupted();
        Item corruptedItem2 = new Item();
        corruptedItem2.setName("corruptedItem2");
        corruptedItem2.setPrice("100$");
        corruptedItem2.setDescription("corruptedItem2");
        corruptedItem2.setCorrupted(corrupted2);
        session.save(corruptedItem2);

        transaction.commit();

        ////Find/////////////////
        transaction.begin();
        List<Item> items = session.createQuery("""
                from Item i where i.price < "1200$"\s""",Item.class).getResultList();
        items.stream().forEach(item -> System.out.println(item.getName()));
        transaction.commit();

        ///Update////
        transaction.begin();
        items.get(0).setName("newCorruptedItem1");
        session.update(items.get(0));
        transaction.commit();

//        /Delete/////////
        transaction.begin();
        session.remove(items.get(0));
        transaction.commit();

    }
}
