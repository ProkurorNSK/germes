package ru.prokurornsk.germes.app.service.impl;

import ru.prokurornsk.germes.app.infra.exception.flow.ValidationException;
import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.model.entity.geography.Station;
import ru.prokurornsk.germes.app.model.search.criteria.StationCriteria;
import ru.prokurornsk.germes.app.model.search.criteria.range.RangeCriteria;
import ru.prokurornsk.germes.app.persistence.repository.CityRepository;
import ru.prokurornsk.germes.app.persistence.repository.StationRepository;
import ru.prokurornsk.germes.app.service.GeographicService;

import javax.inject.Inject;
import javax.validation.*;
import java.util.*;

/**
 * Default implementation of the {@link GeographicService}
 *
 * @author ProkurorNSK
 */

public class GeographicServiceImpl implements GeographicService {
    private final CityRepository cityRepository;
    private final StationRepository stationRepository;
    private final Validator validator;

    @Inject
    public GeographicServiceImpl(CityRepository cityRepository, StationRepository stationRepository) {
        this.cityRepository = cityRepository;
        this.stationRepository = stationRepository;

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public List<City> findCities() {
        return cityRepository.findAll();
    }

    @Override
    public void saveCity(City city) {
        Set<ConstraintViolation<City>> constraintViolations = validator.validate(city);
        if (!constraintViolations.isEmpty()) {
            throw new ValidationException("City validation failure", constraintViolations);
        }
        cityRepository.save(city);
    }

    @Override
    public void deleteCities() {
        cityRepository.deleteAll();
    }

    @Override
    public Optional<City> findCitiyById(final int id) {
        return Optional.ofNullable(cityRepository.findById(id));
    }

    @Override
    public List<Station> searchStations(final StationCriteria criteria, final RangeCriteria rangeCriteria) {
        return stationRepository.findAllByCriteria(criteria);
    }
}
