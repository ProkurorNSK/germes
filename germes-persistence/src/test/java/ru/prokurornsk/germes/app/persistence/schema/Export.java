package ru.prokurornsk.germes.app.persistence.schema;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Ignore;
import org.junit.Test;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.EnumSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * {@link Export} dynamically generates SQL schema
 *
 * @author ProkurorNSK
 */
public class Export {
    /**
     * Creates file with DDL statements to create project database from scratch
     * using specified dialect
     */
    @Test
    @Ignore
    public void exportDatabase() {
        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder().applySetting("hibernate.dialect", MySQL5Dialect.class.getName()).build());

        Reflections reflections = new Reflections("ru.prokurornsk.germes.app.model.entity");
        Set<Class<?>> entityClasses = reflections.getTypesAnnotatedWith(Entity.class);
        entityClasses.forEach(metadata::addAnnotatedClass);

        SchemaExport schema = new SchemaExport();
        schema.setDelimiter(";");
        schema.setOutputFile("" + "schema_" + MySQL5Dialect.class.getSimpleName() + ".sql");

        schema.create(EnumSet.of(TargetType.SCRIPT), metadata.buildMetadata());
        assertTrue(true);
    }
}
