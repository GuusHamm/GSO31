import jdk.nashorn.internal.runtime.PropertyListeners;

import javax.swing.plaf.basic.BasicToolBarUI;
import java.rmi.Remote;

/**
 * Created by xubuntu on 20-10-15.
 */
public interface IBanner extends Remote implements PropertyListener {
	public void setKoersen();
}
