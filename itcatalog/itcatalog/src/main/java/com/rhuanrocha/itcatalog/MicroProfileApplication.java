package com.rhuanrocha.itcatalog;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jnosql.diana.api.Settings;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.api.document.DocumentConfiguration;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.Map;

@ApplicationPath("/")
@ApplicationScoped
public class MicroProfileApplication extends Application {

    private static final String COLLECTION = "catalogs";

    @Inject
    @ConfigProperty(name="bd.itcatalog.path")
    private String bdPath;

    private DocumentCollectionManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        Map<String, Object> settings = Collections.singletonMap("mongodb-server-host-1",bdPath);
        managerFactory = new MongoDBDocumentConfiguration().get(Settings.of(settings));
    }

    @Produces
    public DocumentCollectionManager getManager() {
        return managerFactory.get(COLLECTION);

    }
}
