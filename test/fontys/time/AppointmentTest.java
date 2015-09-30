package fontys.time;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Nekkyou on 30-9-2015.
 */
public class AppointmentTest {

    ITime bt;
    ITime et;
    TimeSpan ts;
    Appointment a;
    @Before
    public void setUp() throws Exception {
        bt = new Time(1970, 1, 1, 1, 1);
        et = new Time(1970, 1, 1, 1, 2);
        ts = new TimeSpan(bt, et);

        a = new Appointment("Math", ts);
    }

    @Test
    public void testGetSubject() throws Exception {
        setUp();
        assertEquals("De naam van het Subject klopt niet", a.getSubject(), "Math");
    }

    @Test
    public void testGetTimeSpan() throws Exception {
        setUp();
        assertEquals("De TimeSpan komt niet overeen", a.getTimeSpan(), ts);
    }

    @Test
    public void testInvitees() throws Exception {
        setUp();
        Contact cTest = new Contact("Pietje");
        a.addContact(cTest);
        Contact c = a.invitees().next();
        assertEquals("Contacten komen niet overeen.", c.getName(), cTest.getName());
    }

    @Test
    public void testAddContact() throws Exception {
        setUp();
        Contact c = new Contact("Pietje");
        assertTrue("Contact is niet goed toegevoegd", a.addContact(c));
        assertFalse("Contact is toch toegevoegd", a.addContact(c));
    }

    @Test
    public void testRemoveContact() throws Exception {
        setUp();
        Contact c = new Contact("Pietje");
        a.addContact(c);
        a.removeContact(c);
    }
}