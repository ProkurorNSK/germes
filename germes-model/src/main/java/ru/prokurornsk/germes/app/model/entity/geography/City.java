package ru.prokurornsk.germes.app.model.entity.geography;

import ru.prokurornsk.germes.app.model.entity.base.AbstractEntity;
import ru.prokurornsk.germes.app.model.entity.transport.TransportType;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static ru.prokurornsk.germes.app.infra.util.CommonUtil.getSafeSet;

/**
 * Any locality that contains transport stations.
 *
 * @author ProkurorNSK
 */
public class City extends AbstractEntity {

    private String name;

    /**
     * Name of the district where city is placed.
     */
    private String district;

    /**
     * Name of the region where district is located.
     * Region is top-level area in the country.
     */
    private String region;

    /**
     * Set of transport stations that is linked to this
     * loyality.
     */
    private Set<Station> stations;

    public City() {
    }

    public City(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public Set<Station> getStations() {
        return getSafeSet(stations);
    }

    /**
     * Adds specified station to the city station list.
     *
     * @param transportType
     */
    public Station addStation(final TransportType transportType) {
        if (stations == null) {
            stations = new HashSet<>();
        }
        Station station = new Station(this, transportType);
        stations.add(station);
        return station;
    }

    /**
     * Removes specified station from city station list
     *
     * @param station
     */
    public void removeStation(Station station) {
        Objects.requireNonNull(station, "station parameter is not initialized");
        stations.remove(station);
    }
}
