package ru.prokurornsk.germes.presentation.admin.bean;

import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.service.GeographicService;

import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class CitiesBean {

    private final GeographicService geographicService;

    @Inject
    public CitiesBean(GeographicService geographicService) {
        this.geographicService = geographicService;
    }

    public List<City> getCities() {
        return geographicService.findCities();
    }
}