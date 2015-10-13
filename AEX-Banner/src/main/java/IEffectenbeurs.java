import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IEffectenbeurs extends Remote
{
	ArrayList<IFonds> getKoersen() throws RemoteException;

}