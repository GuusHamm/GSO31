import fontys.observer.RemotePropertyListener;

import java.rmi.Remote;
import java.util.ArrayList;

/**
 * Created by xubuntu on 20-10-15.
 */
public interface IBanner extends Remote, RemotePropertyListener {
	public ArrayList<IFonds> setKoersen();
}
