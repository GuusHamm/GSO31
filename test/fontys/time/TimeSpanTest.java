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

    ITime bt;
    ITime et;
    TimeSpan t;

    @Before
    public void setUp() throws Exception {
        bt = new Time(1996, 5, 22, 20, 30);
        et = new Time(2015, 4, 10, 15, 45);
        t = new TimeSpan(bt, et);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetBeginTime() throws Exception {
        assertEquals("Begin tijd test", t.getBeginTime(), bt);

    }

    @Test
    public void testGetEndTime() throws Exception {
        assertEquals("Eind tijd test", t.getEndTime(), et);
    }

    @Test
    public void testLength() throws Exception {
        assertEquals("Test de lengte van de periode", t.length(), et.difference(bt));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBeginTime() throws Exception {
        ITime bt2 = new Time(2016,9,22,20,59);

        t.setBeginTime(bt2);
    }

    @Test(expected = IllegalArgumentException.class)
     public void testSetEndTime() throws Exception {
        ITime et2 = new Time(1994, 2, 25, 05, 30);

        t.setEndTime(et2);
    }

    @Test
    public void testMove() throws Exception {
        ITime bt3 = new Time(2015,3,5,20,30);
        ITime et3 = new Time(2015,3,5,20,50);
        TimeSpan t2 = new TimeSpan(bt3, et3);
        t2.move(20);

        ITime et4 = new Time(2015,3,5,20,50);

        assertEquals("tijd na verzetten afspraak", t2.getBeginTime(), et4);

    }

    @Test
    public void testChangeLengthWith() throws Exception {

    }

    @Test
    public void testIsPartOf() throws Exception {

    }

    @Test
    public void testUnionWith() throws Exception {

    }

    @Test
    public void testIntersectionWith() throws Exception {

    }
}