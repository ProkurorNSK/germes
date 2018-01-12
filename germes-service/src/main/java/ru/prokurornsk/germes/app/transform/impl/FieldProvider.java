package ru.prokurornsk.germes.app.transform.impl;

import ru.prokurornsk.germes.app.infra.util.ReflectionUtil;

import java.util.List;

/**
 * Base functionality of the field preparation
 * @author ProkurorNSK
 *
 */
public class FieldProvider {
    public List<String> getFieldNames(Class<?> source, Class<?> dest) {
        return ReflectionUtil.findSimilarFields(source, dest);
    }
}