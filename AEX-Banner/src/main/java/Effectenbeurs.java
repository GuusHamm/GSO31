import fontys.observer.RemotePropertyListener;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Effectenbeurs implements IEffectenbeurs, Serializable
{
    private ArrayList<IFonds> fondsen;

    @Override
    public ArrayList<IFonds> getKoersen() {
        return fondsen;
    }


    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {

    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {

    }
}