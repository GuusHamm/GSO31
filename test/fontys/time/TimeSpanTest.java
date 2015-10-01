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
        ITime bt2 = new Time(1970, 3, 1, 1, 1);
        ITime et2 = new Time(1970, 2, 1, 1, 1);
        TimeSpan t2 = new TimeSpan(bt2, et2);

    }

    @Test
    public void testGetBeginTime() throws Exception {
        setUp();
        assertEquals("Begin tijd test", t.getBeginTime(), bt);

    }

    @Test
    public void testGetEndTime() throws Exception {
        setUp();
        assertEquals("Eind tijd test", t.getEndTime().compareTo(et), 0);
    }

    @Test
    public void testLength() throws Exception {
        setUp();
        assertEquals("Test de lengte van de periode", t.length(), et.difference(bt));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBeginTime() throws Exception {
        setUp();

        ITime bt2 = new Time(1970, 1, 1, 1, 0);
        ITime bt3 = new Time(2016,9,22,20,59);

        t.setBeginTime(bt2);
        t.setBeginTime(bt3);
    }

    @Test(expected = IllegalArgumentException.class)
     public void testSetEndTime() throws Exception {
        setUp();

        ITime et3 = new Time(1964, 2, 25, 05, 30);
        ITime et2 = new Time(1971, 3, 3, 13, 12);

        t.setEndTime(et2);
        t.setEndTime(et3);
    }

    @Test
    public void testMove() throws Exception {
        setUp();
        // Nu is de tijd: Y: 1970, M: 01, D: 01, H: 01 Min: 01

        //System.out.println("Voor move jaar: " + t.getBeginTime().getYear() + " , Maand: " + t.getBeginTime().getMonth() + " , Dag: " + t.getBeginTime().getDay() + " , Uur: " + t.getBeginTime().getHours() + " , Minuten: " + t.getBeginTime().getMinutes());

        t.move(20);


        //System.out.println("Na move jaar: " + t.getBeginTime().getYear() + " , Maand: " + t.getBeginTime().getMonth() + " , Dag: " + t.getBeginTime().getDay() + " , Uur: " + t.getBeginTime().getHours() + " , Minuten: " + t.getBeginTime().getMinutes());
        // Nu is de tijd: Y: 1970, M: 01, D: 01, H: 01 Min: 21

        //Deze tijd is 20 minuten later dan bt3
        ITime bt2 = new Time(1970,1,1,1,21);
        ITime et2 = new Time(1970,2,1,1,21);
        TimeSpan ts2 = new TimeSpan(bt2, et2);


        String TS1 = StringFromTime(t.getBeginTime());
        String TS2 = StringFromTime(ts2.getBeginTime());
        //System.out.println("Van TimeSpan 2: " + ts2.getBeginTime().getYear() + " , Maand: " + ts2.getBeginTime().getMonth() + " , Dag: " + ts2.getBeginTime().getDay() + " , Uur: " + ts2.getBeginTime().getHours() + " , Minuten: " + ts2.getBeginTime().getMinutes());

        assertEquals("tijd na verzetten afspraak", TS1, TS2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeLengthWith() throws Exception {
        setUp();

        ITime et2 = new Time(1970,2,1,1,21);
        t.changeLengthWith(20);

        assertEquals("Lengte van de vergadering is verlengd", StringFromTime(t.getEndTime()), StringFromTime(et2));

        t.changeLengthWith(-5);
    }

    @Test
    public void testIsPartOf() throws Exception {
        setUp();
        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 1, 1, 1, 5);
        TimeSpan ts2 = new TimeSpan(bt2, et2);

        assertTrue("ts2 is deel van t", ts2.isPartOf(t));
    }

    @Test
    public void testUnionWith() throws Exception {
        setUp();


        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 1, 1, 1, 3);

        TimeSpan ts2 = new TimeSpan(bt2, et2);

        //Dit is de actie
        TimeSpan ts3 = (TimeSpan) t.unionWith(ts2);
        //Hetzelfde maar dan omgedraait, hier moet hetzelfde uitkomen.
        TimeSpan ts5 = (TimeSpan) ts2.unionWith(t);

        //Voor Null terug te krijgen
        ITime bt5 = new Time(1990, 1, 31, 1, 1);
        ITime et5 = new Time(1990, 3, 1, 1, 1);
        TimeSpan ts6 = new TimeSpan(bt5, et5);

        //Nodig omdat het tijds mechanisme niet goed werkt
        String TS3 = StringFromTime(ts3.getBeginTime()) + StringFromTime(ts3.getEndTime());
        String T = StringFromTime(t.getBeginTime()) + StringFromTime(ts2.getEndTime());
        String TS5 = StringFromTime(ts5.getBeginTime()) + StringFromTime(ts5.getEndTime());

        assertEquals("Je krijgt niet de juiste TimeSpan terug al krijg je deze melding", TS3, T);
        assertEquals("Je krijgt niet de juiste TimeSpan terug al krijg je deze melding", TS5, T);
        assertNull("U krijgt geen Null object terug", ts2.unionWith(ts6));
    }

    @Test
    public void testIntersectionWith() throws Exception {
        setUp();

        ITime btNull = new Time(1970, 1, 1, 1, 1);
        ITime btNull2 = new Time(2000, 1, 1, 1, 1);
        ITime etNull = new Time(1970, 2, 2, 2, 3);
        ITime etNull2 = new Time(2000, 2, 2, 2, 2);
        TimeSpan tis = new TimeSpan(btNull, etNull);
        TimeSpan tis2 = new TimeSpan(btNull2, etNull2);

        assertNull("U krijgt geen Null terug", tis2.intersectionWith(tis));



        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 2, 1, 1, 15);
        TimeSpan ts2 = new TimeSpan(bt2, et2);
        TimeSpan ts3 = null;
        TimeSpan ts4 = null;
        try {
            ts3 = (TimeSpan) t.intersectionWith(ts2);
        }
        catch (NullPointerException np) {
            System.out.println("Failed");
        }

        try {
            ts4 = (TimeSpan) ts2.intersectionWith(t);
        }
        catch (NullPointerException np) {
            System.out.println("Failed");
        }

        //Het verwachte resultaat
        TimeSpan ts5 = new TimeSpan(bt2, et);

        System.out.println(StringFromTime(ts4.getBeginTime()) + ts4.getEndTime());
        if (ts3 != null)
        {
            String TsActual = StringFromTime(ts3.getBeginTime()) + StringFromTime(ts3.getEndTime());
            String TsExpected = StringFromTime(ts5.getBeginTime()) + StringFromTime(ts5.getEndTime());

            assertEquals("Je krijgt niet de juiste TimeSpan terug al krijg je deze melding", TsActual, TsExpected);
        }




    }



    private String StringFromTime(ITime i)
    {
        return "Y" + i.getYear() + "M" + i.getMonth() + "D" + i.getDay() + "H" + i.getHours() + "Min" + i.getMinutes();
    }
}