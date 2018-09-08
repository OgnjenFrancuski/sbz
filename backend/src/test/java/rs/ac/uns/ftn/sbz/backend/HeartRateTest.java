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
import rs.ac.uns.ftn.sbz.backend.model.monitoring.event.HeartBeat;
import rs.ac.uns.ftn.sbz.backend.model.monitoring.event.Notification;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class HeartRateTest
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
        PatientMonitoring monitor = new PatientMonitoring(patient, null, null);
        kieSession.insert(monitor);
        SessionPseudoClock clock = kieSession.getSessionClock();

        // for 25 seconds heart beats each second, patient is ok
        for (int index = 0; index < 25; index++)
        {
            kieSession.insert(new HeartBeat(patient));
            clock.advanceTime(1, TimeUnit.SECONDS);
            Assert.assertEquals(0, kieSession.fireAllRules());
        }

        // for 10 seconds heart beats increase to 25 heartbeats, patient is not ok
        for (int index = 0; index < 26; index++)
            kieSession.insert(new HeartBeat(patient));
        clock.advanceTime(10, TimeUnit.SECONDS);
        Assert.assertEquals(1, kieSession.fireAllRules());

        // Only one notification event should be fired
        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Notification.class));
        Assert.assertEquals(1, newEvents.size());
    }
}
