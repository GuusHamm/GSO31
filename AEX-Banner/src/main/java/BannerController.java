import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController
{
	private AEXBanner banner;
	private IEffectenbeurs effectenbeurs;
	private Timer pollingTimer;
    private RMIClient client;

	public BannerController(AEXBanner banner) {
        this.banner = banner;
        client = new RMIClient("145.93.240.77", 5081, this);
//        client = new RMIClient("145.93.240.139", 5081, this);

        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                try
                {
                    Update();
                }
                catch (RemoteException e)
                {
                    e.printStackTrace();
                }
//                catch (Exception e) {
//                    return;
//                }
            }
        }, 0, 1000);

    }


	// Stop banner controller
	public void stop() {
		pollingTimer.cancel();
	}

    public void Update() throws RemoteException{

        ArrayList<IFonds> koersen = new ArrayList<IFonds>();
		try {
            koersen  = client.getEffectenbeurs().getKoersen();
        }
        catch (Exception e) {
            return;
        }

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

    public void setEffectenbeurs(IEffectenbeurs effectenbeurs) {
        this.effectenbeurs = effectenbeurs;
    }

    public IEffectenbeurs getEffectenbeurs()
    {
        return effectenbeurs;
    }
}