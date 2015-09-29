/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

/**
 *
 * @author Frank Peeters, Nico Kuijpers
 * 
 * LET OP: De klasse TimeSpan bevat enkele fouten.
 * 
 */
public class TimeSpan implements ITimeSpan {

    /* class invariant: 
     * A stretch of time with a begin time and end time.
     * The end time is always later then the begin time; the length of the time span is
     * always positive
     * 
     */
    private ITime bt, et;
    
    /**
     * 
     * @param bt must be earlier than et
     * @param et is the endtime
     */
    public TimeSpan(ITime bt, ITime et) {
        if (bt.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        this.bt = bt;
        this.et = et;
    }
    @Override
    public ITime getEndTime() {
        return et;
    }


    @Override
    public int length() {
        return et.difference(bt);
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        if (beginTime.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) > 0) {
            throw new IllegalArgumentException("end time "
                    + et + " must be later then begin time " + bt);
        }

        et = endTime;
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
        et = et.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }

        et = et.plus(minutes);
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) <= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) >= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        if (bt.compareTo(timeSpan.getEndTime()) < 0 || et.compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }

        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) > 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }

        return new TimeSpan(begintime, endtime);

    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {

        ITime begintime, endtime;
        //Test:
        System.out.println("bt : " + StringFromTime(bt));
        System.out.println("bt2: " + StringFromTime(timeSpan.getBeginTime()));

        System.out.println(bt.compareTo(timeSpan.getBeginTime()));
        System.out.println(timeSpan.getBeginTime().compareTo(bt));
        //Einde test
        //In dit geval is dit object (bt) groter dan timeSpan.getBeginTime. (dan krijg je True) als ze hetzelfde of als timeSpan groter is dan krijg je false
        if (bt.compareTo(timeSpan.getBeginTime()) > 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        //Test
        System.out.println("Begintijd: " + StringFromTime(begintime));

        System.out.println("et : " + StringFromTime(et));
        System.out.println("et2: " + timeSpan.getEndTime());
        //Einde test

        if (et.compareTo(timeSpan.getEndTime()) < 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }

        //Test items
        System.out.println("Begin tijd: " + StringFromTime(begintime));
        System.out.println("Eind tijd: " + StringFromTime(endtime));
        System.out.println(begintime.compareTo(endtime));
        //Einde test items

        //Verandert van >= naar <
        if (begintime.compareTo(endtime) <= 0) {
            return null;
        }

        return new TimeSpan(begintime, endtime);
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    //Test Methode omdat het tijd mechanisme niet lekker werkt
    private String StringFromTime(ITime i)
    {
        return "Y" + i.getYear() + "M" + i.getMonth() + "D" + i.getDay() + "H" + i.getHours() + "Min" + i.getMinutes();
    }
}
