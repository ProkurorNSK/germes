package ru.prokurornsk.germes.app.infra.util;

import org.junit.Test;
import ru.prokurornsk.germes.app.infra.exception.flow.InvalidParameterException;
import ru.prokurornsk.germes.app.infra.util.Checks;

import static org.junit.Assert.*;

/**
 * Verifies functionality of {@link Checks} class
 *
 * @author ProkurorNSK
 */

public class ChecksTest {
    @Test
    public void testCheckParameterGetException() {
        try {
            Checks.checkParameter(false, "test");
            assertTrue(false);
        } catch (Exception ex) {
            assertSame(ex.getClass(), InvalidParameterException.class);
            assertEquals(ex.getMessage(), "test");
        }
    }

    @Test
    public void testCheckParameterNoException() {
        Checks.checkParameter(true, "test");
        assertTrue(true);
    }

}
