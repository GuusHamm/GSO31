package fontys.time;

import junit.extensions.TestSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by guus on 22-9-15.
 * @author Guus
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

//        Deze datum is epoch en deze datum gebruiken wij als standaard tijd

        y = 1970;
        m = 1;
        d = 1;
        h = 1;
        min = 1;

        testTime = new Time(y,m,d,h,min);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonthTooBig() throws Exception{
       setUp();

//        this value is too big so this test should fail

        m = 16;
        testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonthTooSmall() throws Exception{
        setUp();

//        this value is too small so this test should fail

        m = -2;
        testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMonthZero() throws Exception{
        setUp();

//        this value is too small so this test should fail

        m = 0;
        testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDayTooBig() throws Exception{
        setUp();

        //        this value is too big so this test should fail

        d = 32;

        testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDayTooSmall() throws Exception{
        setUp();

        //        this value is too small so this test should fail

        d = -32;

        testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDayZero() throws Exception{
        setUp();

//        this value is too small so this test should fail

        d = 0;

        testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHourTooBig() throws Exception{
        setUp();

        //        this value is too big so this test should fail

        h = 32;

        testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHourTooSmall() throws Exception{
        setUp();

        //        this value is too small so this test should fail

        h = -32;

        testTime = new Time(y,m,d,h,min);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMinuteTooBig() throws Exception{
        setUp();

        min = 80;

        testTime = new Time(y,m,d,h,min);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMinuteTooSmall() throws Exception{
        setUp();

        //        this value is too small so this test should fail

        min = -80;

        testTime = new Time(y,m,d,h,min);

    }

    @Test
    public void testGetDayInWeek() throws Exception {
//        this test is designed to test the day switch in the constructor of Time

//        this is a thursday
        d = 1;
        testTime = new Time(y,m,d,h,min);
        assertEquals("the returned day is not the correct one for this day",testTime.getDayInWeek(),DayInWeek.THU);
//          this is a friday
        d = 2;
        testTime = new Time(y,m,d,h,min);
        assertEquals("the returned day is not the correct one for this day",testTime.getDayInWeek(),DayInWeek.FRI);
//            this is a saturday
        d = 3;
        testTime = new Time(y,m,d,h,min);
        assertEquals("the returned day is not the correct one for this day",testTime.getDayInWeek(),DayInWeek.SAT);
//            this is a sunday
        d = 4;
        testTime = new Time(y,m,d,h,min);
        assertEquals("the returned day is not the correct one for this day",testTime.getDayInWeek(),DayInWeek.SUN);
//            this is a monday
        d = 5;
        testTime = new Time(y,m,d,h,min);
        assertEquals("the returned day is not the correct one for this day",testTime.getDayInWeek(),DayInWeek.MON);
//            this is a tuesday
        d = 6;
        testTime = new Time(y,m,d,h,min);
        assertEquals("the returned day is not the correct one for this day",testTime.getDayInWeek(),DayInWeek.TUE);
//            this is a wednesday
        d = 7;
        testTime = new Time(y,m,d,h,min);
        assertEquals("the returned day is not the correct one for this day",testTime.getDayInWeek(),DayInWeek.WED);
    }

    @Test
    public void testGetYear() throws Exception {
        setUp();

        assertEquals("this is not the year that was set",testTime.getYear(),1970);
    }

    @Test
    public void testGetMonth() throws Exception {
        setUp();

        assertEquals("this is not the month that was set",testTime.getMonth(),1);
    }

    @Test
    public void testGetDay() throws Exception {
        setUp();

        assertEquals("this is not the day that was set",testTime.getDay(),1);
    }

    @Test
    public void testGetHours() throws Exception {
        setUp();

        assertEquals("this is not the hour that was set",testTime.getHours(),1);
    }

    @Test
    public void testGetMinutes() throws Exception {
        setUp();

        assertEquals("this is not the minute that was set",testTime.getMinutes(),1);
    }

    @Test
    public void testPlus() throws Exception {
        setUp();

//        adds 0 to test time
        int add = 0;
        ITime result = testTime.plus(add);
        assertEquals("The numbers are not the same",min+add,result.getMinutes());


//        adds 1 to test time
        add = 1;
        testTime = new Time(y,m,d,h,min);
        result = testTime.plus(add);
        assertEquals("The numbers are not the same",min+add,result.getMinutes());

//        adds -1 to test time
        add = -1;
        testTime = new Time(y,m,d,h,min);
        result = testTime.plus(add);
        assertEquals("The numbers are not the same",min+add,result.getMinutes());

    }

    @Test
    public void testCompareTo() throws Exception {
//        create a time that is one minute later then testtime
        Time testTime2 = new Time(y,m,d,h,min+1);

        assertEquals("this should return 1 because testtime is smaller then testtime2",1,testTime.compareTo(testTime2));

        assertEquals("this should return 1 because testtime is the same size as then testtime",0,testTime.compareTo(testTime));

        assertEquals("this should return 1 because testtime is bigger then testtime2",-1,testTime2.compareTo(testTime));
    }

    @Test
    public void testDifference() throws Exception {
//       creates a time one minute later then testtime
        Time testTime2 = new Time(y,m,d,h,min+1);

        assertEquals("Testtime2 is one minute later as Testtime so it should return a difference of one",1,testTime.difference(testTime2));

        assertEquals("testtime is the same time as testtime so it should return a difference of zero",0,testTime.difference(testTime));

        assertEquals("Testtime is one minute earlier as Testtime2 so it should return a difference of one",-1,testTime2.difference(testTime));
    }
}