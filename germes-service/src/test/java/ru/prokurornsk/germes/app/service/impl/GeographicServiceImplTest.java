package ru.prokurornsk.germes.app.service.impl;

import org.junit.*;
import ru.prokurornsk.germes.app.infra.exception.flow.ValidationException;
import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.model.entity.geography.Station;
import ru.prokurornsk.germes.app.model.entity.transport.TransportType;
import ru.prokurornsk.germes.app.model.search.criteria.StationCriteria;
import ru.prokurornsk.germes.app.model.search.criteria.range.RangeCriteria;
import ru.prokurornsk.germes.app.persistence.hibernate.SessionFactoryBuilder;
import ru.prokurornsk.germes.app.persistence.repository.CityRepository;
import ru.prokurornsk.germes.app.persistence.repository.StationRepository;
import ru.prokurornsk.germes.app.persistence.repository.hibernate.HibernateCityRepository;
import ru.prokurornsk.germes.app.persistence.repository.hibernate.HibernateStationRepository;
import ru.prokurornsk.germes.app.service.GeographicService;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * Contain unit-tests for {@link GeographicServiceImpl}
 *
 * @author ProkurorNSK
 */
public class GeographicServiceImplTest {

    private static GeographicService service;

    private static ExecutorService executorService;

    @BeforeClass
    public static void setup() {
        SessionFactoryBuilder builder = new SessionFactoryBuilder();
        CityRepository repository = new HibernateCityRepository(builder);
        StationRepository stationRepository = new HibernateStationRepository(builder);
        service = new GeographicServiceImpl(repository, stationRepository);

        executorService = Executors.newCachedThreadPool();
    }

    @AfterClass
    public static void tearDown() {
        executorService.shutdownNow();
        service.deleteCities();
    }


    @Test
    public void testNoDataReturnedAtStart() {
        List<City> cities = service.findCities();
        assertFalse(cities.isEmpty());
    }

    @Test
    public void testSaveNewCitySuccess() {

        int cityCount = service.findCities().size();

        City city = createCity();
        service.saveCity(city);

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount + 1);
    }

    @Test
    public void testFindCityByIdSuccess() {
        City city = createCity();
        service.saveCity(city);

        Optional<City> foundCity = service.findCitiyById(city.getId());
        assertTrue(foundCity.isPresent());
        assertEquals(foundCity.get().getId(), city.getId());
    }

    @Test
    public void testFindCityByIdNotFound() {
        Optional<City> foundCity = service.findCitiyById(1_000_000);
        assertFalse(foundCity.isPresent());
    }

    @Test
    public void testSearchStationsByNameSuccess() {

        int cityCount = service.searchStations(StationCriteria.byName("Moscow"), new RangeCriteria(1, 5)).size();

        City city = createCity();
        city.addStation(TransportType.AUTO);
        city.addStation(TransportType.RAILWAY);
        service.saveCity(city);

        List<Station> stations = service.searchStations(StationCriteria.byName("Moscow"), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertEquals(stations.size(), cityCount + 2);
        assertEquals(stations.get(cityCount).getCity(), city);
    }

    @Test
    public void testSearchStationsByNameNotFound() {
        List<Station> stations = service.searchStations(StationCriteria.byName("London"), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertTrue(stations.isEmpty());
    }

    @Test
    public void testSearchStationsByTransportTypeSuccess() {
        int stationsCount = service.searchStations(new StationCriteria(TransportType.AUTO), new RangeCriteria(1, 5)).size();

        City city = createCity();
        city.addStation(TransportType.AUTO);
        service.saveCity(city);
        City city2 = new City("Spb");
        city2.setDistrict("Spb");
        city2.setRegion("Spb");
        city2.addStation(TransportType.AUTO);
        service.saveCity(city2);

        List<Station> stations = service.searchStations(new StationCriteria(TransportType.AUTO), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertEquals(stations.size(), stationsCount + 2);
    }

    @Test
    public void testSearchStationsByTransportTypeNotFound() {
        City city = createCity();
        city.addStation(TransportType.AUTO);
        service.saveCity(city);
        City city2 = new City("Spb");
        city2.setDistrict("Spb");
        city2.setRegion("Spb");
        city2.addStation(TransportType.RAILWAY);
        service.saveCity(city2);

        List<Station> stations = service.searchStations(new StationCriteria(TransportType.AVIA), new RangeCriteria(1, 5));
        assertNotNull(stations);
        assertTrue(stations.isEmpty());
    }


    private City createCity() {
        City city = new City("Moscow");
        city.setDistrict("Moscow");
        city.setRegion("Moscow");

        return city;
    }

    @Test
    public void testSaveMultipleCitiesSuccess() {
        int cityCount = service.findCities().size();

        int addedCount = 1_000;
        for (int i = 0; i < addedCount; i++) {
            City city = new City("Odessa" + i);
            city.setDistrict("Odessa");
            city.setRegion("Odessa");
            city.addStation(TransportType.AUTO);
            service.saveCity(city);
        }

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount + addedCount);
    }

    @Test
    public void testSaveMultipleCitiesInBatchSuccess() {
        int cityCount = service.findCities().size();
        int addedCount = 5_000;

        List<City> cities = new ArrayList<>(addedCount);

        for (int i = 0; i < addedCount; i++) {
            City city = new City("Odessa" + i);
            city.setDistrict("Odessa");
            city.setRegion("Odessa");
            city.addStation(TransportType.AUTO);
            cities.add(city);
        }
        service.saveCities(cities);

        List<City> allCities = service.findCities();
        assertEquals(allCities.size(), cityCount + addedCount);
    }

    private void waitForFutures(List<Future<?>> futures) {
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    @Test
    public void testSaveMultipleCitiesConcurrentlySuccess() {
        int cityCount = service.findCities().size();

        int threadCount = 20;
        int batchCount = 5;

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(executorService.submit(() -> {
                for (int j = 0; j < batchCount; j++) {
                    City city = new City("Lviv_" + Math.random());
                    city.setDistrict("Lviv");
                    city.setRegion("Lviv");
                    city.addStation(TransportType.AUTO);
                    service.saveCity(city);
                }
            }));
        }

        waitForFutures(futures);

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount + threadCount * batchCount);
    }

    @Test
    public void testSaveOneCityConcurrentlySuccess() {
        City city = new City("Nikolaev");
        city.setDistrict("Nikolaev");
        city.setRegion("Nikolaev");
        city.addStation(TransportType.AUTO);
        service.saveCity(city);

        int cityCount = service.findCities().size();

        int threadCount = 20;

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            futures.add(executorService.submit(() -> {
                city.setName("Nikolaev" + Math.random());
                service.saveCity(city);
            }));
        }

        waitForFutures(futures);

        List<City> cities = service.findCities();
        assertEquals(cities.size(), cityCount);
    }

    private void assertValidation(ValidationException ex, String fieldName, Class<?> clz, String messageKey) {
        assertFalse(ex.getConstraints().isEmpty());
        ConstraintViolation<?> constraint = ex.getConstraints().iterator().next();
        assertTrue(constraint.getMessageTemplate().equals(messageKey));
        assertTrue(constraint.getPropertyPath().toString().equals(fieldName));
        assertTrue(constraint.getRootBeanClass().equals(clz));
    }

    @Test
    public void testSaveCityMissingNameValidationExceptionThrown() {
        try {
            City city = new City();
            city.setDistrict("Nikolaev");
            city.setRegion("Nikolaev");
            service.saveCity(city);

            fail("City name validation failed");
        } catch (ValidationException ex) {
            assertValidation(ex, "name", City.class, "{javax.validation.constraints.NotNull.message}");
        }
    }

    @Test
    public void testSaveCityNameTooShortValidationExceptionThrown() {
        try {
            City city = new City("N");
            city.setDistrict("Nikolaev");
            city.setRegion("Nikolaev");
            service.saveCity(city);

            fail("City name validation failed");
        } catch (ValidationException ex) {
            assertValidation(ex, "name", City.class, "{javax.validation.constraints.Size.message}");
        }
    }

    @Test
    public void testSaveCityNameTooLongValidationExceptionThrown() {
        try {
            City city = new City("N1234567890123456789012345678901234567890");
            city.setDistrict("Nikolaev");
            city.setRegion("Nikolaev");
            service.saveCity(city);

            fail("City name validation failed");
        } catch (ValidationException ex) {
            assertValidation(ex, "name", City.class, "{javax.validation.constraints.Size.message}");
        }
    }
}