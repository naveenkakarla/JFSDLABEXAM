package com.klef.jfsd.exam.ClientDemo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HCQLUtil {
	 private static final SessionFactory sessionFactory;

	    static {
	        try {
	            sessionFactory = new Configuration().configure().buildSessionFactory();
	        } catch (Throwable ex) {
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public static void shutdown() {
	        getSessionFactory().close();
	    }
}
