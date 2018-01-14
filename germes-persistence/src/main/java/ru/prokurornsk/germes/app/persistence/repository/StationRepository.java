package ru.prokurornsk.germes.app.persistence.repository;

import ru.prokurornsk.germes.app.model.entity.geography.Station;
import ru.prokurornsk.germes.app.model.search.criteria.StationCriteria;

import java.util.List;

/**
 * Defines CRUD methods to access Station objects in the persistent storage
 *
 * @author ProkurorNSK
 */
public interface StationRepository {
    /**
     * Returns all the stations that match specified criteria
     *
     * @param stationCriteria
     * @return
     */
    List<Station> findAllByCriteria(StationCriteria stationCriteria);
}
