package ru.prokurornsk.germes.app.presentation.admin.bean;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.service.GeographicService;
import ru.prokurornsk.germes.app.service.transform.Transformer;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import static org.mockito.Mockito.*;

@RunWith(CdiRunner.class)
/**
 * Verifies functionality of CityController
 * using Mockito for producing dependecy mocks
 * @author Morenets
 *
 */
public class CityControllerTest {

    @Inject
    private CityController cityController;

    @Produces
    @Mock
    private GeographicService geographicService;

    @Produces
    @Mock
    private Transformer transformer;

    @Test
    public void saveCity_cityInitialized_citySuccessfullySaved() {
        CityBean cityBean = new CityBean();
        when(transformer.untransform(cityBean, City.class)).thenReturn(new City());
        cityController.saveCity(cityBean);
        verify(geographicService, atLeastOnce()).saveCity(any(City.class));
    }

    @Test
    public void update_cityInitialized_cityBeanTransformed() {
        City city = new City();
        city.setId(1);
        city.setName("name");
        city.setDistrict("district");
        city.setRegion("region");
        CityBean cityBean = new CityBean();
        cityController.update(city, cityBean);
        verify(transformer, atLeastOnce()).transform(city, cityBean);
    }

}