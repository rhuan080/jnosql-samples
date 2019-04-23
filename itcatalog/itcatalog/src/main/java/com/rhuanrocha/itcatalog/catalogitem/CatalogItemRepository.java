package com.rhuanrocha.itcatalog.catalogitem;


import org.jnosql.artemis.Repository;

import java.util.List;

public interface CatalogItemRepository extends Repository<CatalogItem, String> {


    public List<CatalogItem> findAll();

}
