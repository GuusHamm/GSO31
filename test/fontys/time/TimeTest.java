package fontys.time;

import junit.extensions.TestSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
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
    private Time testTime;

    @Before
    public void setUp() throws Exception {
        y = 1970;
        m = 1;
        d = 1;
        h = 1;
        min = 1;

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonth() throws Exception{

       setUp();
        m = 16;
        testTime = new Time(y,m,d,h,min);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDay() throws Exception{
        setUp();

        d = 32;

        testTime = new Time(y,m,d,h,min);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHour() throws Exception{
        setUp();

        h = 32;

        testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMinute() throws Exception{
        setUp();

        min = 80;

        testTime = new Time(y,m,d,h,min);

    }

    @Test
    public void testGetDayInWeek() throws Exception {
        d = 1;
        testTime = new Time(y,m,d,h,min);
        assertEquals(testTime.getDayInWeek(),DayInWeek.THU);

        d = 2;
        testTime = new Time(y,m,d,h,min);
        assertEquals(testTime.getDayInWeek(),DayInWeek.FRI);

        d = 3;
        testTime = new Time(y,m,d,h,min);
        assertEquals(testTime.getDayInWeek(),DayInWeek.SAT);

        d = 4;
        testTime = new Time(y,m,d,h,min);
        assertEquals(testTime.getDayInWeek(),DayInWeek.SUN);

        d = 5;
        testTime = new Time(y,m,d,h,min);
        assertEquals(testTime.getDayInWeek(),DayInWeek.MON);

        d = 6;
        testTime = new Time(y,m,d,h,min);
        assertEquals(testTime.getDayInWeek(),DayInWeek.TUE);

        d = 7;
        testTime = new Time(y,m,d,h,min);
        assertEquals(testTime.getDayInWeek(),DayInWeek.WED);
    }

    @Test
    public void testGetYear() throws Exception {

        testTime = new Time(y,m,d,h,min);

        assertEquals(testTime.getYear(),1970);
    }

    @Test
    public void testGetMonth() throws Exception {
        testTime = new Time(y,m,d,h,min);
        assertEquals(testTime.getMonth(),1);
    }

    @Test
    public void testGetDay() throws Exception {
        testTime = new Time(y,m,d,h,min);

        assertEquals(testTime.getDay(),1);
    }

    @Test
    public void testGetHours() throws Exception {
        testTime = new Time(y,m,d,h,min);

        assertEquals(testTime.getHours(),1);
    }

    @Test
    public void testGetMinutes() throws Exception {
        testTime = new Time(y,m,d,h,min);

        assertEquals(testTime.getMinutes(),1);
    }

    @Test
    public void testPlus() throws Exception {
        setUp();
        testTime = new Time(y,m,d,h,min);

        int add = 1;

        ITime result = testTime.plus(add);

        assertEquals(min+add,result.getMinutes());
    }

    @Test
    public void testCompareTo() throws Exception {
        testTime = new Time(y,m,d,h,min);
        Time testTime2 = new Time(y,m,d,h,min+1);

        assertEquals(1,testTime.compareTo(testTime2));

        assertEquals(0,testTime.compareTo(testTime));

        assertEquals(-1,testTime2.compareTo(testTime));
    }

    @Test
    public void testDifference() throws Exception {
        testTime = new Time(y,m,d,h,min);
        Time testTime2 = new Time(y,m,d,h,min+1);

        assertEquals(1,testTime.difference(testTime2));

    }
}