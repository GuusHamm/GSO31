import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController extends UnicastRemoteObject implements IBanner
{
	private AEXBanner banner;
	private IEffectenbeurs effectenbeurs;
	private Timer pollingTimer;
    private RMIClient client;
    ArrayList<IFonds> koersen;

	public BannerController(AEXBanner banner) throws RemoteException {
        super();
        this.banner = banner;
        client = new RMIClient("145.93.56.177", 5081, this);
//        client = new RMIClient("145.93.240.139", 5081, this);



        //TODO subscribe this BannerController
//
//        pollingTimer = new Timer();
//        pollingTimer.schedule(new TimerTask()
//        {
//            @Override
//            public void run()
////            {
////                try
////                {
//////                    Update();
////                }
////                catch (RemoteException e)
////                {
////                    e.printStackTrace();
////                }
////                catch (Exception e) {
////                    return;
////                }
//            }
//        }, 0, 1000);

    }


	// Stop banner controller
	public void stop() throws RemoteException {
		pollingTimer.cancel();
		effectenbeurs.removeListener(this,"fondsen");
	}

    public void Update() throws RemoteException {

        try {
            koersen = client.getEffectenbeurs().getKoersen();
        } catch (Exception e) {
            return;
        }

    }

    public void setEffectenbeurs(IEffectenbeurs effectenbeurs) {
        this.effectenbeurs = effectenbeurs;
    }

    public IEffectenbeurs getEffectenbeurs()
    {
        return effectenbeurs;
    }

    public void addListener() throws RemoteException{
        effectenbeurs.addListener(this,"fondsen");
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException    {
        //TODO als een property verandert.
        setKoersen((ArrayList<IFonds>) propertyChangeEvent.getNewValue());

    }


    @Override
    public void setKoersen(ArrayList<IFonds> fondsen) {
        koersen = fondsen;

        String koersenString = "";
        if (koersen	 != null)
        {
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
    }
