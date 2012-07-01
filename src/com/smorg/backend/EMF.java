package com.smorg.backend;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton class that creates a single instance of a JPA entity
 * factory as the factory is an expensive creation operation.
 */
public final class EMF {
    private static final EntityManagerFactory emfInstance = Persistence
	    .createEntityManagerFactory("transactions-optional");

    private EMF() {
	// intentionally empty
    }

    /**
     * Gets the singleton instance of a
     * EntityManagerFactory. This instance is statically
     * created upon class loading.
     */
    public static EntityManagerFactory get() {
	return emfInstance;
    }
}