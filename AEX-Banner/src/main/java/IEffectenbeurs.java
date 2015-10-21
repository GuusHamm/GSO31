import fontys.observer.RemotePublisher;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IEffectenbeurs extends RemotePublisher {
	ArrayList<IFonds> getKoersen() throws RemoteException;

}