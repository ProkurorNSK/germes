package ru.prokurornsk.germes.app.infra.exception;

import ru.prokurornsk.germes.app.infra.exception.base.AppException;

/**
 * Signals about exceptional cases in the application logic
 * @author ProkurorNSK
 *
 */
public class FlowException extends AppException {

	private static final long serialVersionUID = -2889607185988464072L;

	public FlowException(String message, Throwable cause) {
		super(message, cause);
	}

	public FlowException(String message) {
		super(message);
	}
}
