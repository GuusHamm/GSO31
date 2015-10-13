import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BannerControllerServer
{

	private AEXBanner banner;
	private IEffectenbeurs effectenbeurs;
	private Timer pollingTimer;
    private boolean client = true;

	public BannerControllerServer(AEXBanner banner) {

        this.banner = banner;

        if (!client)
        {
            try
            {
                this.effectenbeurs = new MockEffectenbeurs();
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }

            // Start polling timer: update banner every two seconds
            pollingTimer = new Timer();
            pollingTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try
                    {
                        Update();
                    }
                    catch (RemoteException e)
                    {
                        e.printStackTrace();
                    }
                }
            }, 0 , 1000);

        }
        Registry registry = null;
		if(client){
            try {
               registry =
                        LocateRegistry.getRegistry("127.0.0.1", 2556);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                this.effectenbeurs = (IEffectenbeurs)registry.lookup("Effectenbeurs");
                Update();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            catch (NotBoundException e)
            {
                e.printStackTrace();
            }

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