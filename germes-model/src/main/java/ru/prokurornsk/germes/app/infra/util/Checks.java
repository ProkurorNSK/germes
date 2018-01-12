package ru.prokurornsk.germes.app.infra.util;

import ru.prokurornsk.germes.app.infra.exception.flow.InvalidParameterException;

/**
 * Contains common check routines
 *
 * @author ProkurorNSK
 */
public class Checks {
    private Checks() {
    }

    /**
     * Verifies that specified check passed and throws exception otherwise
     *
     * @param check
     * @param message
     * @throws InvalidParameterException
     */
    public static void checkParameter(boolean check, String message) throws InvalidParameterException {
        if (!check) {
            throw new InvalidParameterException(message);
        }
    }
}
