package ru.prokurornsk.germes.app.persistence.repository.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ru.prokurornsk.germes.app.infra.cdi.DBSource;
import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.model.entity.geography.Station;
import ru.prokurornsk.germes.app.model.search.criteria.StationCriteria;
import ru.prokurornsk.germes.app.persistence.hibernate.SessionFactoryBuilder;
import ru.prokurornsk.germes.app.persistence.repository.StationRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@DBSource
public class HibernateStationRepository extends BaseHibernateRepository implements StationRepository {

    @Inject
    public HibernateStationRepository(SessionFactoryBuilder builder) {
        super(builder);
    }

    @Override
    public List<Station> findAllByCriteria(StationCriteria stationCriteria) {
        return query(session -> {
            Criteria criteria = session.createCriteria(Station.class);

            if (stationCriteria.getTransportType() != null) {
                criteria.add(Restrictions.eq(Station.FIELD_TRANSPORT_TYPE, stationCriteria.getTransportType()));
            }

            if (!StringUtils.isEmpty(stationCriteria.getName())) {
                criteria = criteria.createCriteria(Station.FIELD_CITY);
                criteria.add(Restrictions.eq(City.FIELD_NAME, stationCriteria.getName()));
            }

            return criteria.list();

        });
    }
}
