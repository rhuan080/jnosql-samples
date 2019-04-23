package com.rhuanrocha.itcatalog.catalogitem;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/catalogitems")
public class CatalogItemEndpoint {

    @Inject
    private CatalogItemService catalogItemService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){

        return Response
                .ok(catalogItemService.findAll())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(CatalogItem catalogItem){

        catalogItem.save();
        return Response
                .created(URI.create("/catalogitems/"+catalogItem.getId()))
                .build();

    }


}
