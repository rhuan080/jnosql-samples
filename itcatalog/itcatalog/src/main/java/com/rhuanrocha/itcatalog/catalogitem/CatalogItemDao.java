package com.rhuanrocha.itcatalog.catalogitem;

import org.jnosql.artemis.document.DocumentTemplate;
import org.jnosql.diana.api.document.DocumentQuery;
import org.jnosql.diana.api.document.query.DocumentQueryBuilder;

import javax.inject.Inject;
import java.util.List;

public class CatalogItemDao {

    @Inject
    private DocumentTemplate documentTemplate;

    public List<CatalogItem> findAll(){

        DocumentQuery query = DocumentQueryBuilder.select().from(CatalogItem.class.getSimpleName()).build();
        return documentTemplate.select(query);

    }
}
