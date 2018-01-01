package ru.prokurornsk.germes.app.service.impl;

import org.apache.commons.lang3.StringUtils;
import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.model.entity.geography.Station;
import ru.prokurornsk.germes.app.model.search.criteria.StationCriteria;
import ru.prokurornsk.germes.app.model.search.criteria.range.RangeCriteria;
import ru.prokurornsk.germes.app.service.GeographicService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public GeographicServiceImpl() {
        this.cities = new ArrayList<>();
    }

    @Override
    public List<City> findCities() {
        return getSafeList(cities);
    }

    @Override
    public Optional<City> findCitiyById(int id) {
        return cities.stream().filter((city) -> city.getId() == id).findFirst();
    }

    @Override
    public List<Station> searchStations(StationCriteria criteria, RangeCriteria rangeCriteria) {
        Stream<City> stream = cities.stream().filter((city) -> StringUtils.isEmpty(criteria.getName()) || city.getName().equals(criteria.getName()));

        Optional<Set<Station>> stations = stream.map(City::getStations).reduce((stations1, stations2) -> {
            Set<Station> newStations = new HashSet<>(stations2);
            newStations.addAll(stations1);
            return newStations;
        });

        if (!stations.isPresent()) {
            return Collections.emptyList();
        }

        return stations.get()
                .stream()
                .filter((station) -> criteria.getTransportType() == null
                        || station.getTransportType() == criteria.getTransportType()).collect(Collectors.toList());
    }

    @Override
    public void saveCity(City city) {
        if (!cities.contains(city)) {
            city.setId(++counter);
            cities.add(city);
        }
    }
}
