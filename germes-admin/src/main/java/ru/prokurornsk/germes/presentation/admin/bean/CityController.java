package ru.prokurornsk.germes.presentation.admin.bean;

import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.service.GeographicService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;

/**
 * Managed bean that keeps all the cities for the main page
 *
 * @author ProkurorNSk
 *
 */
@Named
@ApplicationScoped
public class CityController {

    private final GeographicService geographicService;

    @Inject
    public CityController(GeographicService geographicService) {
        this.geographicService = geographicService;
    }

    public List<City> getCities() {
        return geographicService.findCities();
    }

    public void saveCity(CityBean cityBean) {
        City city = new City();
        city.setName(cityBean.getName());
        city.setRegion(cityBean.getRegion());
        city.setDistrict(cityBean.getDistrict());
        geographicService.saveCity(city);
    }
}