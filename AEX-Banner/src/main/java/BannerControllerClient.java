import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Timer;

public class BannerControllerClient{

	private AEXBanner banner;
	private IEffectenbeurs effectenbeurs;
	private Timer pollingTimer;

	public BannerControllerClient(AEXBanner banner) {
        Registry registry = null;
            try {
               registry =
                        LocateRegistry.getRegistry("127.0.0.1", 1099);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                this.effectenbeurs = (Effectenbeurs)registry.lookup("Effectenbeurs");
                Update();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            catch (NotBoundException e)
            {
                e.printStackTrace();
            }

        }


	// Stop banner controller
	public void stop() {
		pollingTimer.cancel();
		// Stop simulation timer of effectenbeurs
		// TODO
	}
public void Update() throws RemoteException{
		ArrayList<IFonds> koersen = effectenbeurs.getKoersen();
		String koersenString = "";
		for (IFonds fonds: koersen)
		{
            koersenString += fonds.getNaam() + ": ";
			Double getal = fonds.getKoers();

			if (getal < 10)
			{
				koersenString += 0;
			}

            String koers = String.valueOf(fonds.getKoers());
            koers = koers.substring(0,koers.lastIndexOf('.')+3);


			koersenString += koers + ";     ";
		}
		banner.setKoersen(koersenString);

	}

}