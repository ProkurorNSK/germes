package ru.prokurornsk.germes.app.transform;

import org.junit.Before;
import org.junit.Test;
import ru.prokurornsk.germes.app.infra.exception.flow.InvalidParameterException;
import ru.prokurornsk.germes.app.model.entity.geography.City;
import ru.prokurornsk.germes.app.rest.dto.CityDTO;
import ru.prokurornsk.germes.app.transform.impl.SimpleDTOTransformer;

import static org.junit.Assert.*;

/**
 * Verifies functionality of the {@link SimpleDTOTransformer}
 * unit
 *
 * @author ProkurorNSK
 */
public class SimpleDTOTransformerImplTest {
    private Transformer transformer;

    @Before
    public void setup() {
        transformer = new SimpleDTOTransformer();
    }

    @Test
    public void testTransformCitySuccess() {
        City city = new City("Odessa");
        city.setId(1);
        city.setRegion("Od");
        city.setDistrict("None");

        CityDTO dto = transformer.transform(city, CityDTO.class);
        assertNotNull(dto);
        assertEquals(dto.getId(), city.getId());
        assertEquals(dto.getName(), city.getName());
        assertEquals(dto.getDistrict(), city.getDistrict());
        assertEquals(dto.getRegion(), city.getRegion());
    }

    @Test(expected = InvalidParameterException.class)
    public void testTransformNullCityFailure() {
        transformer.transform(null, CityDTO.class);
    }

    @Test(expected = InvalidParameterException.class)
    public void testTransformNullDTOClassFailure() {
        transformer.transform(new City("Odessa"), null);
    }

    @Test
    public void testUnTransformCitySuccess() {
        CityDTO dto = new CityDTO();
        dto.setId(1);
        dto.setRegion("Od");
        dto.setDistrict("None");
        dto.setName("Odessa");

        City city = transformer.untransform(dto, City.class);
        assertNotNull(city);
        assertEquals(dto.getId(), city.getId());
        assertEquals(dto.getName(), city.getName());
        assertEquals(dto.getDistrict(), city.getDistrict());
        assertEquals(dto.getRegion(), city.getRegion());
    }

    @Test(expected = InvalidParameterException.class)
    public void testUnTransformNullCityFailure() {
        transformer.untransform(null, City.class);
    }

    @Test(expected = InvalidParameterException.class)
    public void testUnTransformNullEntityClassFailure() {
        transformer.untransform(new CityDTO(), null);
    }
}
