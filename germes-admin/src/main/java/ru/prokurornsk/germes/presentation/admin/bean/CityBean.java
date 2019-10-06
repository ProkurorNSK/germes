package ru.prokurornsk.germes.presentation.admin.bean;

import ru.prokurornsk.germes.app.model.entity.geography.City;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * {@link CityBean} is value holder of the city data
 * for admin project
 * @author ProkurorNsk
 *
 */

@ManagedBean(name = "currentCity")
@ViewScoped
public class CityBean extends City {
}