package ru.prokurornsk.germes.app.model.entity.geography;

import org.junit.Before;
import org.junit.Test;
import ru.prokurornsk.germes.app.model.entity.transport.TransportType;

import static org.junit.Assert.*;

/**
 * Contains unit-tests to check functionality of {@link City} class.
 *
 * @author ProkurorNSK
 */
public class CityTest {

    private City city;

    @Before
    public void setup() {
        city = new City("Novosibirsk");
    }

    @Test
    public void testAddValidStationSuccess() {
        Station station = city.addStation(TransportType.AUTO);

        assertTrue(containsStation(city, station));
        assertEquals(city, station.getCity());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullStationFailure() {
        city.addStation(null);

        assertTrue(false);
    }

    @Test
    public void testRemoveStationSuccess() {
        Station station = city.addStation(TransportType.AUTO);

        city.removeStation(station);

        assertTrue(city.getStations().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNullStationFailure() {
        city.removeStation(null);

        assertTrue(false);
    }

    private boolean containsStation(City city, Station station) {
        return city.getStations().contains(station);
    }
}