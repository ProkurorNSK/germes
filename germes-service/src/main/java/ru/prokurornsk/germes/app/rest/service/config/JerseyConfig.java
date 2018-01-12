package ru.prokurornsk.germes.app.rest.service.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import ru.prokurornsk.germes.app.config.ComponentFeature;

/**
 * REST web-service configuration for Jersey
 * @author ProkurorNSK
 *
 */
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        super(ComponentFeature.class);
        packages("ru.prokurornsk.germes.app.rest");
    }
}

