package ru.prokurornsk.germes.app.presentation.admin.security;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;
import ru.prokurornsk.germes.app.service.UserService;


/**
 * Initializes authentication realm that delegates processing to CDIRealm
 * @author ProkurorNSK
 *
 */
@WebListener
public class CdiEnvironmentLoaderListener extends EnvironmentLoaderListener {
	@Inject
	private UserService userService;

	@Override
	protected WebEnvironment createEnvironment(ServletContext context) {
		WebEnvironment environment = super.createEnvironment(context);

		RealmSecurityManager rsm = (RealmSecurityManager) environment.getSecurityManager();

		rsm.setRealm(new CDIRealm(userService));

		return environment;
	}
}
