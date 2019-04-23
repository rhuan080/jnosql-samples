package net.rhuanrocha.itrelationship.itemrelationship;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.graph.EdgeEntity;
import org.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

public class ItRelationshipDao {

    @Inject
    private GraphTemplate template;


    public EdgeEntity insertRelationship( CatalogItem catalogItemOne, Relationship relationship, CatalogItem catalogItemTwo){

        Graph thinkerpop = CDI.current().select(Graph.class).get();
        EdgeEntity edgeEntity = template.edge(catalogItemOne, relationship.getValue(),catalogItemTwo);
        thinkerpop.tx().commit();

        return edgeEntity;

    }




}
