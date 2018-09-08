package rs.ac.uns.ftn.sbz.backend;

import org.drools.core.ClassObjectFilter;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rs.ac.uns.ftn.sbz.backend.model.Diagnosis;
import rs.ac.uns.ftn.sbz.backend.model.Disease;
import rs.ac.uns.ftn.sbz.backend.model.Patient;
import rs.ac.uns.ftn.sbz.backend.model.codebook.Diseases;
import rs.ac.uns.ftn.sbz.backend.model.monitoring.PatientMonitoring;
import rs.ac.uns.ftn.sbz.backend.model.monitoring.event.HeartBeat;
import rs.ac.uns.ftn.sbz.backend.model.monitoring.event.Notification;
import rs.ac.uns.ftn.sbz.backend.model.monitoring.event.Urination;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class EmergencyDialysisTest
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


        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDiseases(new HashSet<>());
        diagnosis.addDisease(new Disease(null, Diseases.CHRONIC_KINDEY_DISEASE, null, null, null));

        Patient patient = new Patient();
        patient.setId(-1L);
        patient.setDiagnoses(new HashSet<>());
        patient.getDiagnoses().add(diagnosis);

        PatientMonitoring monitor = new PatientMonitoring(patient, null, null);

        kieSession.insert(monitor);
        kieSession.insert(diagnosis);

        SessionPseudoClock clock = kieSession.getSessionClock();

        // for 10 seconds patient has a heartbeat each second, patient is ok
        for (int i = 0; i < 10; i++)
        {
            kieSession.insert(new HeartBeat(patient));
            clock.advanceTime(1, TimeUnit.SECONDS);
            Assert.assertEquals(0, kieSession.fireAllRules());
        }

        // for 10 seconds patient had 15 heartbeats, patient is not ok
        for (int i = 0; i < 15; i++)
            kieSession.insert(new HeartBeat(patient));
        clock.advanceTime(10, TimeUnit.SECONDS);
        Assert.assertEquals(1, kieSession.fireAllRules());


        // for 12 hours patient hasn't urinated over 100ml, patient is not ok
        kieSession.insert(new Urination(patient, 90));
        clock.advanceTime(12, TimeUnit.HOURS);
        Assert.assertEquals(1, kieSession.fireAllRules());


        // for 12 hours patient has urinated over 100ml, patient is ok
        kieSession.insert(new Urination(patient, 200));
        clock.advanceTime(11, TimeUnit.HOURS);
        clock.advanceTime(59, TimeUnit.SECONDS);
        Assert.assertEquals(0, kieSession.fireAllRules());

        Collection<?> newEvents = kieSession.getObjects(new ClassObjectFilter(Notification.class));
        Assert.assertEquals(2, newEvents.size());
    }
}
