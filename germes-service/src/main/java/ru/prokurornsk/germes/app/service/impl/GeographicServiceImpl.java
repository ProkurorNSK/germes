package ru.prokurornsk.germes.app.service.impl;

import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.model.entity.geography.Station;
import ru.prokurornsk.germes.app.model.search.criteria.StationCriteria;
import ru.prokurornsk.germes.app.model.search.criteria.range.RangeCriteria;
import ru.prokurornsk.germes.app.service.GeographicService;

import java.util.*;
import java.util.stream.Collectors;

import static ru.prokurornsk.germes.app.infra.util.CommonUtil.getSafeList;

/**
 * Default implementation of the {@link GeographicService}
 *
 * @author prokuror
 */

public class GeographicServiceImpl implements GeographicService {

    /**
     * Internal list of cities
     */
    private final List<City> cities;

    /**
     * Auto-increment counter for entity id generation
     */
    private int counter = 0;
    private int stationCounter = 0;

    public GeographicServiceImpl() {
        this.cities = new ArrayList<>();
    }

    @Override
    public List<City> findCities() {
        return getSafeList(cities);
    }

    @Override
    public Optional<City> findCityById(int id) {
        return cities.stream().filter((city) -> city.getId() == id).findFirst();
    }

    @Override
    public List<Station> searchStations(StationCriteria criteria, RangeCriteria rangeCriteria) {
        Set<Station> stations = new HashSet<>();
        for (City city : cities) {
            stations.addAll(city.getStations());
        }

        return stations.stream().filter((station) -> station.match(criteria)).collect(Collectors.toList());
    }

    @Override
    public void saveCity(City city) {
        if (!cities.contains(city)) {
            city.setId(++counter);
            cities.add(city);
        }
        city.getStations().forEach((station) -> {
            if (station.getId() == 0) {
                station.setId(++stationCounter);
            }
        });
    }
}
