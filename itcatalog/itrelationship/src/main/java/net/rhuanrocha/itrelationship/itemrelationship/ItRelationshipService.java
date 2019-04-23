package net.rhuanrocha.itrelationship.itemrelationship;

import org.jnosql.artemis.graph.EdgeEntity;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ItRelationshipService {

    @Inject
    private ItRelationshipDao itRelationshipDao;

    @Inject
    private CatalogItemDao catalogItemDao;

    public Optional<CatalogItem> findById(Long id){
        return catalogItemDao.findById(id);
    }

    public EdgeEntity saveRelationship(CatalogItem catalogItemOne,Relationship relationship, CatalogItem catalogItemTwo){

        catalogItemOne = getFromDBOrSave(catalogItemOne);
        catalogItemTwo = getFromDBOrSave(catalogItemTwo);

        return itRelationshipDao.insertRelationship(catalogItemOne, relationship, catalogItemTwo);

    }

    public List<CatalogItem> findByRelationship(String idCatagogItem, Relationship relationship){

        return catalogItemDao.findByRelationship(idCatagogItem,relationship);

    }

    private CatalogItem getFromDBOrSave(CatalogItem catalogItem){

        Optional<CatalogItem> catalogItemOp = catalogItemDao.findByIdCatalogItem(catalogItem.getIdCatalogItem());
        if(catalogItemOp.isPresent()){
            catalogItem = catalogItemOp.get();
        }
        else{
            catalogItem.insert();
        }

        return catalogItem;

    }
}
