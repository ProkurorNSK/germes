package ru.prokurornsk.germes.app.rest.service;

import org.apache.commons.lang3.math.NumberUtils;
import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.model.entity.transport.TransportType;
import ru.prokurornsk.germes.app.rest.dto.CityDTO;
import ru.prokurornsk.germes.app.rest.service.base.BaseResource;
import ru.prokurornsk.germes.app.service.GeographicService;
import ru.prokurornsk.germes.app.transform.Transformer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * {@link CityResource} is REST web-service that handles city-related requests
 *
 * @author ProkurorNSK
 */
@Path("cities")
public class CityResource extends BaseResource {

    /**
     * Underlying source of data
     */
    private final GeographicService service;

    /**
     * DTO <-> Entity transformer
     */
    private final Transformer transformer;

    @Inject
    public CityResource(GeographicService service, Transformer transformer) {
        this.transformer = transformer;
        this.service = service;

//        City city = new City("Moscow");
//        city.addStation(TransportType.AUTO);
//        city.setDistrict("Moscow");
//        city.setRegion("Moscow");
//        service.saveCity(city);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns all the existing cities
     * @return
     */
    public List<CityDTO> findCities() {
        return service.findCities().stream().map((city) -> transformer.transform(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    /**
     * Saves new city instance
     * @return
     */
    public void saveCity(CityDTO cityDTO) {
        service.saveCity(transformer.untransform(cityDTO, City.class));
    }

    @Path("/{cityId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Returns city with specified identifier
     * @return
     */
    public Response findCityById(@PathParam("cityId") final String cityId) {
        if(!NumberUtils.isNumber(cityId)) {
            return BAD_REQUEST;
        }

        Optional<City> city = service.findCitiyById(NumberUtils.toInt(cityId));
        if (!city.isPresent()) {
            return NOT_FOUND;
        }
        return ok(transformer.transform(city.get(), CityDTO.class));
    }

}