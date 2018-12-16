package ru.prokurornsk.germes.app.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.prokurornsk.germes.app.persistence.hibernate.SessionFactoryBuilder;
import ru.prokurornsk.germes.app.persistence.repository.CityRepository;
import ru.prokurornsk.germes.app.persistence.repository.StationRepository;
import ru.prokurornsk.germes.app.persistence.repository.hibernate.HibernateCityRepository;
import ru.prokurornsk.germes.app.persistence.repository.hibernate.HibernateStationRepository;
import ru.prokurornsk.germes.app.service.GeographicService;
import ru.prokurornsk.germes.app.service.impl.GeographicServiceImpl;
import ru.prokurornsk.germes.app.transform.Transformer;
import ru.prokurornsk.germes.app.transform.impl.SimpleDTOTransformer;

import javax.inject.Singleton;

/**
 * Registers DI bindings
 * @author ProkurorNSK
 *
 */
public class ComponentBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(HibernateCityRepository.class).to(CityRepository.class).in(Singleton.class);
        bind(HibernateStationRepository.class).to(StationRepository.class).in(Singleton.class);
        bind(SimpleDTOTransformer.class).to(Transformer.class).in(Singleton.class);
        bind(GeographicServiceImpl.class).to(GeographicService.class).in(Singleton.class);
        bind(SessionFactoryBuilder.class).to(SessionFactoryBuilder.class).in(Singleton.class);
    }
}
