package ru.prokurornsk.germes.app.model.entity.person;

import ru.prokurornsk.germes.app.model.entity.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity that encapsulates user of the application.
 * @author ProkurorNSK
 * @version 1.0
 */
@Table(name = "ACCOUNT")
@Entity
public class Account extends AbstractEntity{
}
