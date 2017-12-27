package ru.prokurornsk.germes.app.rest.service.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * REST web-service configuration for Jersey
 * @author Morenets
 *
 */
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("ru.prokurornsk.germes.app.rest");
    }
}

