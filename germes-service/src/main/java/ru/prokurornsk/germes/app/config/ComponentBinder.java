package ru.prokurornsk.germes.app.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.prokurornsk.germes.app.persistence.repository.CityRepository;
import ru.prokurornsk.germes.app.persistence.repository.inmemory.InMemoryCityRepository;
import ru.prokurornsk.germes.app.service.GeographicService;
import ru.prokurornsk.germes.app.service.impl.GeographicServiceImpl;
import ru.prokurornsk.germes.app.transform.Transformer;
import ru.prokurornsk.germes.app.transform.impl.SimpleDTOTransformer;

import javax.inject.Singleton;

/**
 * Binds bean implementations and implemented interfaces
 * @author ProkurorNSK
 *
 */
public class ComponentBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(InMemoryCityRepository.class).to(CityRepository.class).in(Singleton.class);
        bind(SimpleDTOTransformer.class).to(Transformer.class).in(Singleton.class);
        bind(GeographicServiceImpl.class).to(GeographicService.class).in(Singleton.class);
    }
}