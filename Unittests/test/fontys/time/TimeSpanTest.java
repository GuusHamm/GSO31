package fontys.time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Nekkyou on 22-9-2015.
 */
public class TimeSpanTest {

    private ITime bt;
    private ITime et;
    private TimeSpan t;

    @Before
    public void setUp() throws Exception {
        bt = new Time(1970, 1, 1, 1, 1);
        et = new Time(1970, 2, 1, 1, 1);
        t = new TimeSpan(bt, et);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateTimeSpan() throws Exception {
        //Maak begin tijd van maand 3 en eindtijd van maand 2.
        ITime bt2 = new Time(1970, 3, 1, 1, 1);
        ITime et2 = new Time(1970, 2, 1, 1, 1);
        //De eindtijd is eerder dan de begintijd, hier krijg je dus een error van.
        TimeSpan t2 = new TimeSpan(bt2, et2);

    }

    @Test
    public void testGetBeginTime() throws Exception {
        /**
         *
         * @return the begin time of this time span
         */

        setUp();
        assertEquals("Begin tijd test", t.getBeginTime(), bt);
    }

    @Test
    public void testGetEndTime() throws Exception {
        /**
         *
         * @return the end time of this time span
         */

        setUp();
        assertEquals("Eind tijd test", t.getEndTime().compareTo(et), 0);
    }

    @Test
    public void testLength() throws Exception {
        /**
         *
         * @return the length of this time span expressed in minutes (always positive)
         */

        setUp();
        assertEquals("Test de lengte van de periode", t.length(), et.difference(bt));
    }

    @Test
    public void testSetBeginTime() throws Exception {
        /**
         * beginTime will be the new begin time of this time span
         * @param beginTime must be earlier than the current end time
         * of this time span
         */

        setUp();

        ITime bt2 = new Time(1970, 1, 1, 1, 0);
        t.setBeginTime(bt2);
        assertEquals("Begintijd is niet correct verzet", t.getBeginTime().compareTo(bt2), 0);

        ITime bt3 = new Time(2016,9,22,20,59);
        try {
            t.setBeginTime(bt3);
        }
        catch (Exception ex) {
            //De begintijd is later dan de eindtijd, hierom gaat het fout.
        }

    }

    @Test
     public void testSetEndTime() throws Exception {
        /**
         * endTime will be the new end time of this time span
         * @param endTime must be later than the current begin time
         * of this time span
         */

        setUp();

        ITime et2 = new Time(1971, 3, 3, 13, 12);
        t.setEndTime(et2);
        assertEquals("Eindtijd is niet correct verzet", t.getEndTime().compareTo(et2), 0);

        ITime et3 = new Time(1964, 2, 25, 05, 30);
        try {
            t.setEndTime(et3);
        }
        catch(Exception ex) {
            //Als je hier komt klopt het, et3 is voor de begindatum dus gaat fout.
        }
    }

    @Test
    public void testMove() throws Exception {
        /**
         * the begin and end time of this time span will be moved up both with [minutes]
         * minutes
         * @param minutes (a negative value is allowed)
         */

        setUp();
        // Nu is de tijd: Y: 1970, M: 01, D: 01, H: 01 Min: 01
        t.move(20);
        // Nu is de tijd: Y: 1970, M: 01, D: 01, H: 01 Min: 21

        //Deze tijd is 20 minuten later dan bt
        ITime bt2 = new Time(1970,1,1,1,21);
        ITime et2 = new Time(1970,2,1,1,21);
        TimeSpan ts2 = new TimeSpan(bt2, et2);

        //Test of de begintijden overeenkomen
        assertEquals("begintijd na verzetten afspraak", t.getBeginTime().compareTo(ts2.getBeginTime()), 0);

        //Test of de eindtijden overeenkomen
        assertEquals("Eindtijd na verzetten afspraak", t.getEndTime().compareTo(ts2.getEndTime()), 0);
    }

    @Test
    public void testChangeLengthWith() throws Exception {
        /**
         * the end time of this time span will be moved up with [minutes] minutes
         * @param minutes  minutes + length of this period must be positive
         */

        setUp();

        //De normale endtijd + 20 minuten.
        ITime et2 = new Time(1970,2,1,1,21);
        t.changeLengthWith(20);
        //Kijk of de eindtijden hetzelfde zijn.
        assertEquals("Lengte van de vergadering is verlengd met 20 min", t.getEndTime().compareTo(et2), 0);

        try {
            t.changeLengthWith(-5);
        }
        catch (Exception ex) {
            //Je wil alleen verlengen met positieve values. (weet ik door foutmelding: "length of period must be positive")
        }
    }

    @Test
    public void testIsPartOf() throws Exception {
        /**
         *
         * @param timeSpan
         * @return true if all moments within this time span are included within [timeSpan],
         * otherwise false
         */

        setUp();
        //Deze valt erin.
        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 1, 1, 1, 5);
        TimeSpan ts2 = new TimeSpan(bt2, et2);
        assertTrue("ts2 is deel van t", ts2.isPartOf(t));

        //Deze valt er niet helemaal in
        ITime bt3 = new Time(1970, 1, 1, 1, 2);
        ITime et3 = new Time(1970, 2, 1, 1, 5);
        TimeSpan ts3 = new TimeSpan(bt3, et3);
        assertFalse("ts3 is geen deel van t", ts3.isPartOf(t));

        //Deze valt er helemaal niet in
        ITime bt4 = new Time(1971, 1, 1, 1, 2);
        ITime et4 = new Time(1971, 2, 1, 1, 5);
        TimeSpan ts4 = new TimeSpan(bt4, et4);
        assertFalse("ts4 is geen deel van t", ts4.isPartOf(t));
    }

    @Test
    public void testUnionWith() throws Exception {
        /**
         *
         * @param timeSpan
         * @return if this time span and [timeSpan] are consecutive or possess a
         * common intersection, then the smallest time span ts will be returned,
         * whereby this time span and [timeSpan] are part of ts,
         * otherwise null will be returned
         */

        setUp();

        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 1, 1, 1, 3);
        TimeSpan ts2 = new TimeSpan(bt2, et2);

        //Dit is de actie
        TimeSpan ts3 = (TimeSpan) t.unionWith(ts2);
        //Hetzelfde maar dan omgedraait, hier moet hetzelfde uitkomen.
        TimeSpan ts4 = (TimeSpan) ts2.unionWith(t);

        //Hier test je de begin en eindtijd van de eerste Unionwith (ts3)
        assertEquals("De begintijd van ts3 is niet hetzelfde als die van t", ts3.getBeginTime().compareTo(t.getBeginTime()), 0);
        assertEquals("De eindtijd van ts3 is niet hetzelfde als die van t", ts3.getEndTime().compareTo(t.getEndTime()), 0);

        //Hier doe je precies hetzelfde als hierboven maar dan met ts4
        assertEquals("De begintijd van ts4 is niet hetzelfde als die van t", ts4.getBeginTime().compareTo(t.getBeginTime()), 0);
        assertEquals("De eindtijd van ts4 is niet hetzelfde als die van t", ts4.getEndTime().compareTo(t.getEndTime()), 0);

        //Voor Null terug te krijgen
        ITime bt5 = new Time(1990, 1, 31, 1, 1);
        ITime et5 = new Time(1990, 3, 1, 1, 1);
        TimeSpan ts5 = new TimeSpan(bt5, et5);
        //Er is geen Union met ts5 omdat deze 20 jaar uit elkaar liggen.
        assertNull("U krijgt geen Null object terug", ts2.unionWith(ts5));
    }

    @Test
    public void testIntersectionWith() throws Exception {
        /**
         *
         * @param timeSpan
         * @return the largest time span which is part of this time span
         * and [timeSpan] will be returned; if the intersection is empty null will
         * be returned
         */

        setUp();

        ITime btNull = new Time(2000, 1, 1, 1, 1);
        ITime etNull = new Time(2000, 2, 2, 2, 2);
        TimeSpan tsNull = new TimeSpan(btNull, etNull);

        //Hier Heb je geen intersection, daarom dus Null
        assertNull("U krijgt geen Null terug", tsNull.intersectionWith(t));

        //Maak een timeSpan die wel intersection heeft.
        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 2, 1, 1, 15);
        TimeSpan ts2 = new TimeSpan(bt2, et2);

        TimeSpan ts3 = null;
        TimeSpan ts4 = null;

        try {
            ts3 = (TimeSpan) t.intersectionWith(ts2);
        }
        catch (NullPointerException np) {
            System.out.println("Failed bij ts3 aanmaken");
        }

        try {
            ts4 = (TimeSpan) ts2.intersectionWith(t);
        }
        catch (NullPointerException np) {
            System.out.println("Failed bij ts4 aanmaken");
        }

        //Het verwachte resultaat
        TimeSpan tsVerwacht = new TimeSpan(bt2, et);

        if (ts3 != null)
        {
            //Test of de begin en eindtijd ook echt aan de verwachtingen voldoen.
            assertEquals("Je krijgt niet de juiste TimeSpan terug al krijg je deze melding", ts3.getBeginTime().compareTo(tsVerwacht.getBeginTime()), 0);
            assertEquals("Je krijgt niet de juiste TimeSpan terug al krijg je deze melding", ts3.getEndTime().compareTo(tsVerwacht.getEndTime()), 0);

            //Het omgekeerde van hierboven
            assertEquals("Je krijgt niet de juiste TimeSpan terug al krijg je deze melding", ts4.getBeginTime().compareTo(tsVerwacht.getBeginTime()), 0);
            assertEquals("Je krijgt niet de juiste TimeSpan terug al krijg je deze melding", ts4.getEndTime().compareTo(tsVerwacht.getEndTime()), 0);
        }
    }

}