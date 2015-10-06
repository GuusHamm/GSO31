package fontys.time;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by linux on 30-9-15.
 * @author Guus
 */
public class ContactTest {
private String name;
    private Appointment appointment;
    private Contact contact;
    @Before
    public void setUp() throws Exception {
        name = "Pieter";
        Time beginTime = new Time(1970, 1, 1, 1, 1);
        Time endTime = new Time(1970, 2, 1, 1, 1);
        TimeSpan appointmentTimeSpan = new TimeSpan(beginTime,endTime);

        appointment = new Appointment("Test Appointment",appointmentTimeSpan);

        contact = new Contact(name);
    }

    @Test
    public void testGetName() throws Exception {
        setUp();

        assertEquals(contact.getName(),name);
    }

    @Test
    public void testAddAppointment() throws Exception {

        setUp();

        assertTrue(contact.addAppointment(appointment));
        assertFalse(contact.addAppointment(appointment));

        List appointments = contact.appointments();

        assertTrue(appointments.contains(appointment));
    }

    @Test
    public void testRemoveAppointment() throws Exception {
        setUp();

        contact.addAppointment(appointment);

        contact.removeAppointment(appointment);

        List appointments = contact.appointments();
        assertFalse(appointments.contains(appointment));

    }


}