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

    /**
     *
     * @param subject Waar je het in de afspraak in wil hebben.
     * @param timeSpan In dit object staat de begintijd en de eindtijd / duratie
     */
    public Appointment(String subject, ITimeSpan timeSpan) {
        this.subject = subject;
        this.timeSpan = timeSpan;
        contacten = new ArrayList<Contact>();
    }

    /**
     *
     * @return Geeft de subject van dit object terug.
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return Geeft de TimeSpan van dit object terug.
     */
    public ITimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     *
     * @return Geeft de lijst van contacten terug als een Iterator
     */
    public Iterator<Contact> invitees() {
        return contacten.iterator();
    }

    /**
     *
     * @param c Dit is het contact dat je wil toevoegen
     * @return Indien het gelukt is return je true
     */
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

    /**
     *
     * @param c Dit is het contact dat je wil verwijderen
     */
    public void removeContact(Contact c) {
        contacten.remove(c);
    }
}
