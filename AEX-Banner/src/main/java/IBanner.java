import fontys.observer.RemotePropertyListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by xubuntu on 20-10-15.
 */
public interface IBanner extends Remote, RemotePropertyListener {
	public void setKoersen(ArrayList<IFonds> fondsen) throws RemoteException;
}
