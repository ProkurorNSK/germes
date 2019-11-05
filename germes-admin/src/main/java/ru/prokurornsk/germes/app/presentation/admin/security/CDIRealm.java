package ru.prokurornsk.germes.app.presentation.admin.security;

import java.util.Optional;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.prokurornsk.germes.app.model.entity.person.User;
import ru.prokurornsk.germes.app.service.UserService;

/**
 * Authorization component that integrates with {@link UserService} to fetch user by login
 * @author Morenets
 *
 */
public class CDIRealm extends AuthorizingRealm {
	private static final Logger LOGGER = LoggerFactory.getLogger(CDIRealm.class);

	private final UserService userService;

	public CDIRealm(UserService userService) {
		this.userService = userService;

		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("SHA-256");
		credentialsMatcher.setStoredCredentialsHexEncoded(true);

		setCredentialsMatcher(credentialsMatcher);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		try {
			String password = Optional.ofNullable(username).flatMap(userService::findByUserName).map(User::getPassword)
					.orElseThrow(() -> new UnknownAccountException("No account found for user " + username));

			return new SimpleAuthenticationInfo(username, password.toCharArray(), getName());

		} catch (Exception e) {
			String message = "There was a error while authenticating user " + username;
			LOGGER.error(message, e);

			throw new AuthenticationException(message, e);
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return new SimpleAccount();
	}
}
