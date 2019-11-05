package ru.prokurornsk.germes.app.infra.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

/**
 * Security and encryption-related routines
 * @author Morenets
 *
 */
public class SecurityUtil {
	private SecurityUtil() {
	}

	/**
	 * Encryptes source text using SHA-2 encoding
	 * @param source
	 * @return
	 */
	public static String encryptSHA(String source) {
		return Hashing.sha256().hashString(source, StandardCharsets.UTF_8).toString();
	}

}
