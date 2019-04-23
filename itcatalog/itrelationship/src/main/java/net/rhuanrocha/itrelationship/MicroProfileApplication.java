package net.rhuanrocha.itrelationship;

import com.steelbridgelabs.oss.neo4j.structure.Neo4JElementIdProvider;
import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraph;
import com.steelbridgelabs.oss.neo4j.structure.providers.Neo4JNativeElementIdProvider;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.ConfigurationUnit;
import org.neo4j.driver.v1.Driver;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

@ApplicationPath("/")
@ApplicationScoped
public class MicroProfileApplication extends Application {

    private Neo4JGraph graph;

    @Inject
    @ConfigurationUnit
    private Driver driver;

    @PostConstruct
    public void init() {
        Neo4JElementIdProvider<?> vertexIdProvider = new Neo4JNativeElementIdProvider();
        Neo4JElementIdProvider<?> edgeIdProvider = new Neo4JNativeElementIdProvider();
        this.graph = new Neo4JGraph(driver, vertexIdProvider, edgeIdProvider);
        graph.setProfilerEnabled(true);
    }

    @Produces
    @ApplicationScoped
    public Graph getGraph() {
        return graph;
    }



    public void close(@Disposes Graph graph) throws Exception {
        graph.close();
        driver.close();
    }


}
