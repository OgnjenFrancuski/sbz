package rs.ac.uns.ftn.sbz.backend;

import org.drools.core.ClockType;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.io.ResourceFactory;

public class CepConfigTest
{
    @Test
    public void testCEPConfigThroughCode()
    {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("monitoring/monitoring.drl"));

        KieBuilder kbuilder = kieServices.newKieBuilder(kieFileSystem);
        kbuilder.buildAll();

        if (kbuilder.getResults().hasMessages(Message.Level.ERROR))
            throw new IllegalArgumentException("Coudln't build knowledge module" + kbuilder.getResults());

        KieContainer kieContainer = kieServices.newKieContainer(kbuilder.getKieModule().getReleaseId());

        KieBaseConfiguration kieBaseConfiguration = kieServices.newKieBaseConfiguration();
        kieBaseConfiguration.setOption(EventProcessingOption.STREAM);

        KieBase kbase = kieContainer.newKieBase(kieBaseConfiguration);

        KieSessionConfiguration kieSessionConfiguration1 = kieServices.newKieSessionConfiguration();
        kieSessionConfiguration1.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
        KieSession kieSession1 = kbase.newKieSession(kieSessionConfiguration1, null);

        KieSessionConfiguration kieSessionConfiguration2 = kieServices.newKieSessionConfiguration();
        kieSessionConfiguration2.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
        KieSession kieSession2 = kbase.newKieSession(kieSessionConfiguration2, null);
    }
}
