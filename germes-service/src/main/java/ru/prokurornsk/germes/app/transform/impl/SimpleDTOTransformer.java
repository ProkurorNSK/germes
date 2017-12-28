package ru.prokurornsk.germes.app.transform.impl;

import ru.prokurornsk.germes.app.infra.util.Checks;
import ru.prokurornsk.germes.app.infra.util.ReflectionUtil;
import ru.prokurornsk.germes.app.model.entity.base.AbstractEntity;
import ru.prokurornsk.germes.app.rest.dto.base.BaseDTO;
import ru.prokurornsk.germes.app.transform.Transformer;

/**
 * Default transformation engine that uses reflection to transform objects
 *
 * @author Morenets
 */
public class SimpleDTOTransformer implements Transformer {
    @Override
    public <T extends AbstractEntity, P extends BaseDTO<T>> P transform(T entity, Class<P> clz) {
        checkParams(entity, clz);

        P dto = ReflectionUtil.createInstance(clz);
        // Now just copy all the similar fields
        ReflectionUtil.copyFields(entity, dto, ReflectionUtil.findSimilarFields(entity.getClass(), clz));
        dto.transform(entity);

        return dto;
    }

    private void checkParams(final Object param, final Class<?> clz) {
        Checks.checkParameter(param != null, "Source transformation object is not initialized");
        Checks.checkParameter(clz != null, "No class is defined for transformation");
    }

    @Override
    public <T extends AbstractEntity, P extends BaseDTO<T>> T untransform(P dto, Class<T> clz) {
        checkParams(dto, clz);

        T entity = ReflectionUtil.createInstance(clz);

        ReflectionUtil.copyFields(dto, entity, ReflectionUtil.findSimilarFields(dto.getClass(), clz));
        dto.untransform(entity);

        return entity;
    }
}
