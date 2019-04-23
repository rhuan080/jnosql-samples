package com.rhuanrocha.itcatalog.catalogitem;

import org.jnosql.artemis.*;

import javax.enterprise.inject.spi.CDI;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CatalogItem implements Serializable {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String Description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void save(){

        Database database = DatabaseQualifier.ofDocument();
        CatalogItemRepository catalogItemRepository = CDI.current().select(CatalogItemRepository.class,database).get();
        catalogItemRepository.save(this);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogItem that = (CatalogItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
