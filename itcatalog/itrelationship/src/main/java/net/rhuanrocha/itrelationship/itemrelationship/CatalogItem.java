package net.rhuanrocha.itrelationship.itemrelationship;

import org.jnosql.artemis.Column;
import org.jnosql.artemis.Entity;
import org.jnosql.artemis.Id;

import javax.enterprise.inject.spi.CDI;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CatalogItem implements Serializable {

    @Id
    private Long id;

    @Column
    private String idCatalogItem;

    @Column
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCatalogItem() {
        return idCatalogItem;
    }

    public void setIdCatalogItem(String idCatalogItem) {
        this.idCatalogItem = idCatalogItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogItem that = (CatalogItem) o;
        return Objects.equals(id, that.id);
    }

    public void insert(){

        CatalogItemDao catalogItemDao = (CatalogItemDao) CDI.current()
                .select(CatalogItemDao.class)
                .stream()
                .findFirst()
                .get();

        CatalogItem catalogItem = catalogItemDao.insert(this);
        this.setId(catalogItem.getId());
    }


    public static CatalogItem build( String idCatalogItem, String name){

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setIdCatalogItem(idCatalogItem);
        catalogItem.setName(name);

        return catalogItem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}