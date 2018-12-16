package ru.prokurornsk.germes.app.persistence.hibernate.interceptor;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import ru.prokurornsk.germes.app.model.entity.base.AbstractEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Initializes mandatory timestamp fields for the entities
 *
 * @author ProkurorNSK
 */
public class TimestampInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = 6825201844366406253L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof AbstractEntity) {
            AbstractEntity saved = (AbstractEntity) entity;
            if (saved.getId() == 0) {
                saved.setCreatedAt(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }
}
