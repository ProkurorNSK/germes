package ru.prokurornsk.germes.app.rest.service;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import static org.junit.Assert.*;

import ru.prokurornsk.germes.app.rest.dto.CityDTO;
import ru.prokurornsk.germes.app.rest.service.config.JerseyConfig;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * {@link CityResourceTest} is integration test that verifies
 * {@link CityResource}
 *
 * @author ProkurorNSK
 */
public class CityResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new JerseyConfig();
    }

    @Test
    public void testFindCitiesSuccess() {

        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Novosibirsk");
        cityDTO.setDistrict("Novosibirsk");
        cityDTO.setRegion("Novosibirsk");
        target("cities").request().post(Entity.entity(cityDTO, MediaType.APPLICATION_JSON));

        List cities = target("cities").request().get(List.class);
        assertNotNull(cities);
        assertFalse(cities.isEmpty());

        Map city = (Map) cities.get(cities.size() - 1);
        assertEquals(city.get("name"), "Novosibirsk");
    }

    @Test
    public void testFindCityByIdSuccess() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Novosibirsk");
        cityDTO.setDistrict("Novosibirsk");
        cityDTO.setRegion("Novosibirsk");
        target("cities").request().post(Entity.entity(cityDTO, MediaType.APPLICATION_JSON));

        List cities = target("cities").request().get(List.class);

        Integer id = (Integer)((Map)cities.get(cities.size() - 1)).get("id");
        CityDTO city = target("cities/" + id.toString()).request().get(CityDTO.class);
        assertNotNull(city);
        assertEquals(city.getId(), id.intValue());
        assertEquals(city.getName(), "Novosibirsk");
    }

    @Test
    public void testFindCityByIdNotFound() {
        Response response = target("cities/2000000").request().get(Response.class);
        assertNotNull(response);
        assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void testFindCityByIdInvalidId() {
        Response response = target("cities/test").request().get(Response.class);
        assertNotNull(response);
        assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void testSaveCitySuccess() {
        CityDTO city = new CityDTO();
        city.setName("Moscow");
        city.setDistrict("Moscow");
        city.setRegion("Moscow");

        Response response = target("cities").request().post(Entity.entity(city, MediaType.APPLICATION_JSON));
        assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode());
    }
}