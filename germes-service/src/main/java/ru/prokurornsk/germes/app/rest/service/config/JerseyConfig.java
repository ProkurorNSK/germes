package ru.prokurornsk.germes.app.rest.service.config;

import javax.ws.rs.ApplicationPath;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import ru.prokurornsk.germes.app.config.ComponentFeature;

/**
 * REST web-service configuration for Jersey
 * @author ProkurorNSK
 *
 */
@ApplicationPath("api")
@SwaggerDefinition(info = @Info(description = "Booking and purchasing API definition", title = "Germes project", version = "0.7.4",
        contact = @Contact(email = "prokurornsk@gmail.com", name = "Pavel Fedyashin", url = "http://prokurornsk.github.io")))
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        super(ComponentFeature.class);
        packages("ru.prokurornsk.germes.app.rest");

        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
    }

    private void initBeanConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[] { "http" });
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage("ru.prokurornsk.germes.app.rest.service");
        beanConfig.setScan(true);
    }
}

