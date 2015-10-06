package fontys.time;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by linux on 30-9-2015.
 * @author Guus
 */
public class Contact {


    private String name;
    private ArrayList appointments;

    /**
     *
     * @param name the name of the Contact
     */
    public Contact(String name) {
        this.name = name;
        appointments = new ArrayList();
    }

    /**
     *
     * @return the name  of the contact
     */
    public String getName(){

        return name;
    }

    /**
     *
     * @param appointment the appointment we want to add
     * @return if the appointment was not yet in the contacts calender returns true, if it's already there returns false
     */
    protected Boolean addAppointment(Appointment appointment){

        if (appointments.contains(appointment))   {
            return false;
        }
        else {
            appointments.add(appointment);
            return true;
        }
    }

    /**
     *
     * @param appointment the appointment we want to remove from the contact
     */
    protected void removeAppointment(Appointment appointment){
        if (appointments.contains(appointment))   {
            appointments.remove(appointment);
        }
    }

    /**
     *
     * @return a list of all the appointments of the contact
     */
    public List<Appointment> appointments(){
        return Collections.unmodifiableList(appointments);
    }
}
