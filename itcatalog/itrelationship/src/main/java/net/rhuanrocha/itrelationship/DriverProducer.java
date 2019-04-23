package net.rhuanrocha.itrelationship;

import org.jnosql.artemis.ConfigurationUnit;
import org.neo4j.driver.v1.AuthToken;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import java.util.logging.Logger;

@ApplicationScoped
public class DriverProducer {

    private static final Logger LOGGER = Logger.getLogger(DriverProducer.class.getName());

    @Produces
    @ConfigurationUnit
    public Driver getDriver(InjectionPoint injectionPoint) {

        String url = "bolt://172.18.0.4:7687";
        String user = "neo4j";
        String password = "test";

        AuthToken basic = AuthTokens.basic(user, password);
        Driver driver = GraphDatabase.driver(url, basic);

        return driver;
    }


}
