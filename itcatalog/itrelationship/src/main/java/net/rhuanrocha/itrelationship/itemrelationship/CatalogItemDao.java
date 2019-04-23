package net.rhuanrocha.itrelationship.itemrelationship;

import org.apache.tinkerpop.gremlin.structure.Graph;
import org.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class CatalogItemDao {

    @Inject
    private GraphTemplate template;

    public CatalogItem insert(CatalogItem catalogItem){
        Graph thinkerpop = CDI.current().select(Graph.class).get();
        template.insert(catalogItem);
        thinkerpop.tx().commit();
        return findByIdCatalogItem(catalogItem.getIdCatalogItem()).get();
    }

    public Optional<CatalogItem> findByIdCatalogItem(String id){
        return template.getTraversalVertex()
                .hasLabel(CatalogItem.class.getSimpleName())
                .has("idCatalogItem",id)
                .next();
    }

    public Optional<CatalogItem> findById(Long id){
        return template.find(id);
    }

    public List<CatalogItem> findByRelationship(String idCatagogItem, Relationship relationship){

        return template.getTraversalVertex()
                .hasLabel(CatalogItem.class.getSimpleName())
                .has("idCatalogItem", idCatagogItem)
                .out(relationship.getValue())
                .getResultList();
    }
}

