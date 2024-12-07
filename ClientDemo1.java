package com.klef.jfsd.exam.ClientDemo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ClientDemo1 {
	public static void main(String[] args) {
		Session session = HCQLUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        // Insert Records
        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setEmail("alice@example.com");
        customer1.setAge(25);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Bob");
        customer2.setEmail("bob@example.com");
        customer2.setAge(30);
        customer2.setLocation("California");

        session.save(customer1);
        session.save(customer2);

        transaction.commit();

        // Fetch records using Criteria API
        System.out.println("Fetching records using Criteria API:");
        CriteriaBuilder cb = session.getCriteriaBuilder();

        // Create CriteriaQuery and specify the type
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

        // Define the root (table) for the query
        Root<Customer> root = cq.from(Customer.class);

        // Apply restrictions
        Predicate agePredicate = cb.gt(root.get("age"), 20); // Age > 20
        Predicate locationPredicate = cb.like(root.get("location"), "%New%"); // Location contains "New"

        // Combine predicates with AND condition
        cq.where(cb.and(agePredicate, locationPredicate));

        // Execute query and retrieve results
        List<Customer> customers = session.createQuery(cq).getResultList();

        // Print results
        for (Customer customer : customers) {
            System.out.println(customer.getName() + " - " + customer.getLocation());
        }

        session.close();
        HCQLUtil.shutdown();
    }
	}
