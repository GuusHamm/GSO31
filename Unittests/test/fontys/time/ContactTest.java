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

//        we create a new contact for test purposes
        name = "Pieter";
        contact = new Contact(name);

//        we create a new appointment for test purposes
        Time beginTime = new Time(1970, 1, 1, 1, 1);
        Time endTime = new Time(1970, 2, 1, 1, 1);
        TimeSpan appointmentTimeSpan = new TimeSpan(beginTime,endTime);
        appointment = new Appointment("Test Appointment",appointmentTimeSpan);

    }

    @Test
    public void testGetName() throws Exception {
        setUp();

        assertEquals("this is not the contact name that was set",contact.getName(),name);
    }

    @Test
    public void testAddAppointment() throws Exception {

        setUp();

        assertTrue("the appointment was not added correctly",contact.addAppointment(appointment));
//        now we add the appointment again
        assertFalse("this appointment is already set for this contact", contact.addAppointment(appointment));

//        now we test that the appointment is actually in the appointments of the contact
        List appointments = contact.appointments();
        assertTrue("the appointment was not added to the contact",appointments.contains(appointment));
    }

    @Test
    public void testRemoveAppointment() throws Exception {
        setUp();

        //first we add an appointment (we are assuming that this works because we have a different test too see if that works
        contact.addAppointment(appointment);

        // we then remove the appointment from the contact
        contact.removeAppointment(appointment);

        List appointments = contact.appointments();
        assertFalse("the appointment is still in the apppointments of the contact",appointments.contains(appointment));

    }


}