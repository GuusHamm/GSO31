package fontys.time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Nekkyou on 30-9-2015.
 */
public class Appointment {

    private String subject;
    private ITimeSpan timeSpan;
    private ArrayList<Contact> contacten;

    public Appointment(String subject, ITimeSpan timeSpan) {
        this.subject = subject;
        this.timeSpan = timeSpan;
        contacten = new ArrayList<Contact>();
    }

    public String getSubject() {
        return subject;
    }

    public ITimeSpan getTimeSpan() {
        return timeSpan;
    }

    public Iterator<Contact> invitees() {
        return contacten.iterator();
    }

    public boolean addContact(Contact c) {
        try {
            contacten.add(c);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    public void removeContact(Contact c) {
        contacten.remove(c);
    }
}
