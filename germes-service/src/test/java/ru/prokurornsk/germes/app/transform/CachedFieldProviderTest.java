package ru.prokurornsk.germes.app.transform;

import org.junit.Before;
import org.junit.Test;
import ru.prokurornsk.germes.app.transform.impl.CachedFieldProvider;
import ru.prokurornsk.germes.app.transform.impl.FieldProvider;
import ru.prokurornsk.germes.app.transform.impl.SimpleDTOTransformer;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Verifies functionality of the {@link SimpleDTOTransformer}
 * unit
 * @author Morenets
 *
 */
public class CachedFieldProviderTest {
    private FieldProvider provider;

    @Before
    public void setup() {
        provider = new CachedFieldProvider();
    }

    @Test
    public void testGetFieldNamesSuccess() {
        List<String> fields = provider.getFieldNames(Source.class, Destination.class);
        assertFalse(fields.isEmpty());
        assertTrue(fields.contains("value"));
    }

    @Test
    public void testGetFieldNamesCachedSuccess() {
        List<String> fields = provider.getFieldNames(Source.class, Destination.class);
        List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
        assertFalse(fields.isEmpty());
        assertEquals(fields, fields2);
    }

}

class Source {
    int value;
}

class Destination {
    int value;
}