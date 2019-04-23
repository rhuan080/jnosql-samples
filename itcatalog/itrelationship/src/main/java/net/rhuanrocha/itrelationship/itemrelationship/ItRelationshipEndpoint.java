package net.rhuanrocha.itrelationship.itemrelationship;

import org.jnosql.artemis.graph.EdgeEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/itrelationships")
public class ItRelationshipEndpoint {

    @Inject
    private ItRelationshipService itRelationshipService;

    @GET
    @Path("/{idCatalogItem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCatalogItem(@PathParam("idCatalogItem") String idCatalogItem, @QueryParam("relationship") String relationship){

        List<CatalogItem> catalogItems = itRelationshipService.findByRelationship(
                idCatalogItem,
                Relationship.valueOf(Optional.ofNullable(relationship).orElse(Relationship.SUPPORTS.getValue())));

        if(catalogItems.isEmpty()) {

            return Response
                    .noContent()
                    .entity(catalogItems)
                    .build();
        }

        return Response
                .ok(catalogItems)
                .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response insertCatalogItem(@FormParam("idCatalogItemOne")String idCatalogItemOne,
                                      @FormParam("nameCatalogItemOne") String nameCatalogItemOne,
                                      @FormParam("relationship") String relationship,
                                      @FormParam("idCatalogItemTwo") String idCatalogItemTwo,
                                      @FormParam("nameCatalogItemTwo") String nameCatalogItemTwo){

        CatalogItem catalogItemOne = CatalogItem.build(idCatalogItemOne,nameCatalogItemOne);
        CatalogItem catalogItemTwo = CatalogItem.build(idCatalogItemTwo,nameCatalogItemTwo);

        itRelationshipService.saveRelationship(catalogItemOne, Relationship.valueOf(relationship), catalogItemTwo);

        return Response
                .created(URI.create("/itrelationships/"))
                .build();

    }
}
