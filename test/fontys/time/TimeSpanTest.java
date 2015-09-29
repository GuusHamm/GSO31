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

    @Test
    public void testGetBeginTime() throws Exception {
        setUp();
        assertEquals("Begin tijd test", t.getBeginTime(), bt);

    }

    @Test
    public void testGetEndTime() throws Exception {
        setUp();
        assertEquals("Eind tijd test", t.getEndTime(), et);
    }

    @Test
    public void testLength() throws Exception {
        setUp();
        assertEquals("Test de lengte van de periode", t.length(), et.difference(bt));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBeginTime() throws Exception {
        setUp();

        ITime bt2 = new Time(2016,9,22,20,59);

        t.setBeginTime(bt2);
    }

    @Test(expected = IllegalArgumentException.class)
     public void testSetEndTime() throws Exception {
        setUp();
        ITime et2 = new Time(1994, 2, 25, 05, 30);

        t.setEndTime(et2);
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

    @Test
    public void testChangeLengthWith() throws Exception {
        setUp();

        ITime et2 = new Time(1970,2,1,1,21);
        t.changeLengthWith(20);

        assertEquals("Lengte van de vergadering is verlengd", StringFromTime(t.getEndTime()), StringFromTime(et2));

    }

    @Test
    public void testIsPartOf() throws Exception {
        setUp();

    }

    @Test
    public void testUnionWith() throws Exception {
        setUp();
    }

    @Test
    public void testIntersectionWith() throws Exception {
        setUp();
    }

    private String StringFromTime(ITime i)
    {
        return "Y" + i.getYear() + "M" + i.getMonth() + "D" + i.getDay() + "H" + i.getHours() + "Min" + i.getMinutes();
    }
}