package fontys.time;

import junit.extensions.TestSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by linux on 22-9-15.
 */
public class TimeTest {
    private int y;
    private int m;
    private int d;
    private int h;
    private int min;

    @Before
    public void setUp() throws Exception {
        y = 1970;
        m = 1;
        d = 1;
        h = 01;
        min = 01;

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonth() throws Exception{

       setUp();
        m = 16;
        Time testTime = new Time(y,m,d,h,min);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDay() throws Exception{
        setUp();

        d = 32;

        Time testTime = new Time(y,m,d,h,min);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHour() throws Exception{
        setUp();

        h = 32;

        Time testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMinute() throws Exception{
        setUp();

        m = 100;

        Time testTime = new Time(y,m,d,h,min);

    }

    @Test
    public void testGetDayInWeek() throws Exception {
        d = 1;
        Time testTime = new Time(y,m,d,h,min);

        assertEquals(testTime.getDayInWeek(),DayInWeek.SUN);


    }

    @Test
    public void testGetYear() throws Exception {

    }

    @Test
    public void testGetMonth() throws Exception {

    }

    @Test
    public void testGetDay() throws Exception {

    }

    @Test
    public void testGetHours() throws Exception {

    }

    @Test
    public void testGetMinutes() throws Exception {

    }

    @Test
    public void testPlus() throws Exception {

    }

    @Test
    public void testCompareTo() throws Exception {

    }

    @Test
    public void testDifference() throws Exception {

    }
}