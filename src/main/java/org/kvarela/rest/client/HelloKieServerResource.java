package org.kvarela.rest.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kie.server.api.model.KieContainerResource;
import org.kie.server.api.model.KieContainerResourceList;
import org.kie.server.api.model.ServiceResponse;

@Path("/hello-kieserver")
public class HelloKieServerResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
    
        ServiceResponse<KieContainerResourceList> kieContainers = KieClientInitializer.getKieServicesClient().listContainers();
        StringBuffer containersString = new StringBuffer(); containersString.append("Existing containers: \n");
    
        for (KieContainerResource kieContainer :
            kieContainers.getResult().getContainers()) {
            containersString.append("\n " + kieContainer.toString());
        }
    
    return containersString.toString();
    
    }
    
}