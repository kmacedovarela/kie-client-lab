package org.kvarela.rest.client;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class KieClientInitializer implements java.io.Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 4372318661233575236L;
	private static final Logger LOGGER = LoggerFactory.getLogger("KieClientInitializer");
	private static KieServicesClient kieServicesClient;
 
    
    void onStart(@Observes StartupEvent ev) {	
        LOGGER.info("The application is starting...");
        buildKieClient();
    }


	private void buildKieClient(){
        KieServicesConfiguration conf = KieServicesFactory.newRestConfiguration("http://localhost:8080/kie-server/services/rest/server","kieserver","kieserver1!",6000l); 
        conf.setMarshallingFormat(MarshallingFormat.JSON);
    
        KieServicesFactory.newKieServicesClient(conf);
    }


	public static KieServicesClient getKieServicesClient() {
		return kieServicesClient;
	}

}