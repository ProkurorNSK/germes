package ru.prokurornsk.germes.app.model.entity.geography;

import org.junit.Test;
import ru.prokurornsk.germes.app.model.entity.transport.TransportType;
import ru.prokurornsk.germes.app.model.search.criteria.StationCriteria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Verifies functionality of the {@link Station} domain entity
 *
 * @author ProkurorNSK
 */
public class StationTest {
    @Test(expected = NullPointerException.class)
    public void testMatchCriteriaNotInitialized() {
        City city = new City("Moscow");
        Station station = new Station(city, TransportType.AUTO);

        station.match(null);
    }

    @Test
    public void testMatchByNameSuccess() {
        City city = new City("Moscow");
        Station station = new Station(city, TransportType.AUTO);

        assertTrue(station.match(StationCriteria.byName("Moscow")));
    }

    @Test
    public void testMatchByNameNotFound() {
        City city = new City("Moscow");
        Station station = new Station(city, TransportType.AUTO);

        assertFalse(station.match(StationCriteria.byName("Spb")));
    }
}
