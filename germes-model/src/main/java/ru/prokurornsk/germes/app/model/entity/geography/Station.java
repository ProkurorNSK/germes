package ru.prokurornsk.germes.app.model.entity.geography;

import ru.prokurornsk.germes.app.model.entity.base.AbstractEntity;
import ru.prokurornsk.germes.app.model.entity.transport.TransportType;

import java.util.Objects;

/**
 * Station where passengers can get off or take specific kind
 * of transport. Multiple stationts compose route of the trip.
 *
 * @author admin
 */
public class Station extends AbstractEntity {
    private City city;

    private Address address;

    /**
     * (Optional) Phone of the inquiry office.
     */
    private String phone;

    private Coordinate coordinate;

    private TransportType transportType;


    /**
     * You shouldn't create station object directly. Use
     * {@link City} functionality instead
     *
     * @param city
     * @param transportType
     */
    Station(final City city, final TransportType transportType) {
        this.city = Objects.requireNonNull(city);
        this.transportType = Objects.requireNonNull(transportType);
    }

    public void setCity(final City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(final Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public TransportType getTransportType() {
        return transportType;
    }
}
