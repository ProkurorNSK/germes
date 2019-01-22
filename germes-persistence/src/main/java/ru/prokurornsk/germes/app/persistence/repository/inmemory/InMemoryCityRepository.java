package ru.prokurornsk.germes.app.persistence.repository.inmemory;

import static ru.prokurornsk.germes.app.infra.util.CommonUtil.getSafeList;
import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.persistence.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory implementation of the {@link CityRepository} that stores
 * data in the RAM
 *
 * @author ProkurorNSK
 */
public class InMemoryCityRepository implements CityRepository {

    /**
     * Internal list of cities
     */
    private final List<City> cities;

    /**
     * Auto-increment counter for entity id generation
     */
    private int counter = 0;

    private int stationCounter = 0;

    public InMemoryCityRepository() {
        cities = new ArrayList<>();
    }

    @Override
    public void save(City city) {
        if (!cities.contains(city)) {
            city.setId(++counter);
            cities.add(city);
        }
        city.getStations().forEach(station -> {
            if ((station.getId() == 0)) {
                station.setId(++stationCounter);
            }
        });
    }

    @Override
    public City findById(int cityId) {
        return cities.stream().filter(city -> city.getId() == cityId).findFirst().orElse(null);
    }

    @Override
    public void delete(int cityId) {

    }

    @Override
    public List<City> findAll() {
        return getSafeList(cities);
    }

    @Override
    public void deleteAll() {
        cities.clear();
    }

    @Override
    public void saveAll(List<City> cities) {
        cities.forEach(this::save);
    }
}
