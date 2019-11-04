package ru.prokurornsk.germes.app.presentation.admin.bean.secrurity;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@ViewScoped
/**
 * JSF-managed bean to store user credentials for authentication
 * @author Morenets
 *
 */
public class UserBean implements Serializable {
	private static final Logger log = LoggerFactory.getLogger(UserBean.class);

	private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword());

		try {
			subject.login(token);

			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");

		} catch (UnknownAccountException ex) {
			facesError("Unknown account");
			log.error(ex.getMessage(), ex);
		} catch (IncorrectCredentialsException ex) {
			facesError("Wrong password");
			log.error(ex.getMessage(), ex);
		} catch (LockedAccountException ex) {
			facesError("Locked account");
			log.error(ex.getMessage(), ex);
		} catch (AuthenticationException | IOException ex) {
			facesError("Unknown error: " + ex.getMessage());
			log.error(ex.getMessage(), ex);
		} finally {
			token.clear();
		}
	}

	private void facesError(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
}
