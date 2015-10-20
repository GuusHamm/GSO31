
import java.rmi.Remote;
import java.util.ArrayList;

import fontys.observer.RemotePropertyListener;
import fontys.observer.RemotePublisher;

/**
 * Created by xubuntu on 20-10-15.
 */
public interface IBanner extends RemotePropertyListener, Remote
{
	public void setKoersen(ArrayList<IFonds> fondsen);
}
