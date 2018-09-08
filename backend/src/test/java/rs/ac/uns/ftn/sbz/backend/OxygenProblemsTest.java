package rs.ac.uns.ftn.sbz.backend;

import org.drools.core.ClassObjectFilter;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbz.backend.model.Patient;
import rs.ac.uns.ftn.sbz.backend.model.monitoring.PatientMonitoring;
import rs.ac.uns.ftn.sbz.backend.model.monitoring.event.Notification;
import rs.ac.uns.ftn.sbz.backend.model.monitoring.event.RiseInOxygenLevels;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class OxygenProblemsTest
{
    @Test
    public void testCEPConfigThroughKModuleXML()
    {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("kSessionPseudoClock");

        runPseudoClockExample(kieSession);
    }


    private void runPseudoClockExample(KieSession kieSession)
    {
        Patient patient = new Patient();
        patient.setId(-1L);
        PatientMonitoring monitor = new PatientMonitoring(patient, null, 60.0);
        kieSession.insert(monitor);
        SessionPseudoClock clock = kieSession.getSessionClock();


        // for 15 minutes oxygen levels are rising, patient is ok
        for (int index = 0; index <= 15; index++)
        {
            clock.advanceTime(1, TimeUnit.MINUTES);
            kieSession.insert(new RiseInOxygenLevels(patient));
            Assert.assertEquals(0, kieSession.fireAllRules());
        }

        // for 15 minutes oxygen levels are not rising, patient is not ok
        clock.advanceTime(15, TimeUnit.MINUTES);
        Assert.assertEquals(1, kieSession.fireAllRules());

        // Only one notification event should be fired
        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Notification.class));
        Assert.assertEquals(1, newEvents.size());
    }
}
