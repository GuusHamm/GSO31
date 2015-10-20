import jdk.nashorn.internal.runtime.PropertyListeners;

import java.rmi.Remote;

/**
 * Created by xubuntu on 20-10-15.
 */
public interface IBanner extends Remote implements PropertyListeners{
	public void setKoersen();
}
