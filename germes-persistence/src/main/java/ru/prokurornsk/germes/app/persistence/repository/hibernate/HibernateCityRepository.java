package ru.prokurornsk.germes.app.persistence.repository.hibernate;

import org.hibernate.SessionFactory;
import ru.prokurornsk.germes.app.persistence.hibernate.SessionFactoryBuilder;

import javax.inject.Inject;

public class HibernateCityRepository {
    private final SessionFactory sessionFactory;

    @Inject
    public HibernateCityRepository(SessionFactoryBuilder builder) {
        sessionFactory = builder.getSessionFactory();
    }
}
