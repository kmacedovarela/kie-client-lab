package org.kvarela.rest.client;


import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.drools.core.command.SetKieContainerCommand;
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
    
    /** KieServicesClient is one of the many available clients that facilitate
     consuming the engine api. 
     Check documentation for info about
     ProcessServicesClient, QueryServicesClient, UserTaskServicesClient, 
     SolverServicesClient, DMNServicesClient, RuleServicesClient and more. */
    private static KieServicesClient kieServicesClient;
    
    private static final String KIESERVER_URL = "http://localhost:8080/kie-server/services/rest/server";
    private static final String KIESERVER_USER = "kieserver";
    private static final String KIESERVER_PASSWORD = "kieserver1!";
    
    private static final long KIESERVER_CONNECTION_TIMEOUT = 6000l;
    
    private static final MarshallingFormat KIESERVER_MARSHALLING = MarshallingFormat.JSON;
    
    // This method is invoked when the application starts
    
    void onStart(@Observes final StartupEvent ev) {	
        LOGGER.info("The application is starting...");
      
        buildKieServicesClient();
        LOGGER.info("Kie Services Client built successfully. Connecting to remote Kie Server: "+KIESERVER_URL);

    }
    
    private void buildKieServicesClient(){
        final KieServicesConfiguration connectionConfig = KieServicesFactory.newRestConfiguration(
        KIESERVER_URL, 
        KIESERVER_USER,
        KIESERVER_PASSWORD,
        KIESERVER_CONNECTION_TIMEOUT); 
        connectionConfig.setMarshallingFormat(KIESERVER_MARSHALLING);
        
        addExtraClasses(connectionConfig);
        
        setKieServicesClient(KieServicesFactory.newKieServicesClient(connectionConfig));
    }
    
    private void addExtraClasses(KieServicesConfiguration connectionConfig) {
        //If you use custom classes, such as Obj.class, add them to the configuration.
        Set<Class<?>> extraClassList = new HashSet<Class<?>>();
        
        /**  Uncomment the line below and add as many custom classes as you need. 
         This will allow the engine to automatically marshall/unmarshall complex objects into/out of the
         process variables */
        //extraClassList.add(MyCustomObject.class);  
        connectionConfig.addExtraClasses(extraClassList); 
    }
    
    public static KieServicesClient getKieServicesClient() {
        return kieServicesClient;
    }
    
    private static void setKieServicesClient(final KieServicesClient _kieServicesClient){
        kieServicesClient = _kieServicesClient;
    }
    
}