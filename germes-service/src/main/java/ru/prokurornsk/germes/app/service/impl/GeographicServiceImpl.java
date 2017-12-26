package ru.prokurornsk.germes.app.service.impl;

import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.service.GeographicService;

import java.util.ArrayList;
import java.util.List;

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

    public GeographicServiceImpl() {
        this.cities = new ArrayList<>();
    }

    @Override
    public List<City> findCities() {
        return getSafeList(cities);
    }

    @Override
    public void saveCity(City city) {
        if (!cities.contains(city)) {
            cities.add(city);
        }
    }
}
