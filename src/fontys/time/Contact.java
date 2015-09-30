package fontys.time;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by linux on 30-9-2015.
 */
public class Contact {

    private String name;
    private ArrayList appointments;

    public Contact(String name) {
        this.name = name;
        appointments = new ArrayList();
    }

    public String getName(){

        return name;
    }

    public Boolean addAppointment(Appointment a){

        if (appointments.contains(a))   {
            return false;
        }
        else {
            appointments.add(a);
            return true;
        }
    }

    public void removeAppointment(Appointment a){
        if (appointments.contains(a))   {
            appointments.remove(a);
        }
    }

    public List<Appointment> appointments(){
        return Collections.unmodifiableList(appointments);
    }
}
