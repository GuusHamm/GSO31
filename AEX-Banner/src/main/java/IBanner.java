import jdk.nashorn.internal.runtime.PropertyListeners;

import javax.swing.plaf.basic.BasicToolBarUI;

import fontys.observer.RemotePropertyListener;
import java.rmi.Remote;

/**
 * Created by xubuntu on 20-10-15.
 */
public interface IBanner extends Remote, RemotePropertyListener {
	public void setKoersen();
}
