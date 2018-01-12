package ru.prokurornsk.germes.app.service.impl;

import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.model.entity.geography.Station;
import ru.prokurornsk.germes.app.model.search.criteria.StationCriteria;
import ru.prokurornsk.germes.app.model.search.criteria.range.RangeCriteria;
import ru.prokurornsk.germes.app.persistence.repository.CityRepository;
import ru.prokurornsk.germes.app.persistence.repository.inmemory.InMemoryCityRepository;
import ru.prokurornsk.germes.app.service.GeographicService;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Default implementation of the {@link GeographicService}
 *
 * @author ProkurorNSK
 */

public class GeographicServiceImpl implements GeographicService {

    private final CityRepository cityRepository;

    @Inject
    public GeographicServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findCities() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findCityById(int id) {
        return Optional.ofNullable(cityRepository.findById(id));
    }

    @Override
    public List<Station> searchStations(StationCriteria criteria, RangeCriteria rangeCriteria) {
        Set<Station> stations = new HashSet<>();
        cityRepository.findAll().forEach(city -> stations.addAll(city.getStations()));
        return stations.stream().filter(station -> station.match(criteria)).collect(Collectors.toList());
    }

    @Override
    public void saveCity(City city) {
        cityRepository.save(city);
    }
}
