package com.rhuanrocha.itcatalog.catalogitem;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.artemis.document.DocumentTemplate;

import javax.inject.Inject;
import java.util.List;

public class CatalogItemService {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private CatalogItemRepository catalogItemRepository;

    @Inject
    private CatalogItemDao catalogItemDao;

    public List<CatalogItem> findAllWithRepository(){
        return catalogItemRepository.findAll();
    }

    public List<CatalogItem> findAll(){
        return catalogItemDao.findAll();
    }
}
