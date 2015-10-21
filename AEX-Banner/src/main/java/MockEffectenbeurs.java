import fontys.observer.BasicPublisher;
import fontys.observer.RemotePropertyListener;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MockEffectenbeurs  extends UnicastRemoteObject implements IEffectenbeurs
{
    private ArrayList<IFonds> fondsen;
    private Timer timer;

    private Random r ;
    private fontys.observer.BasicPublisher publisher = null;

    public MockEffectenbeurs() throws RemoteException
    {
        super();
        fondsen = new ArrayList<>();
        r = new Random();
        // Maak een paar fondsen aan.
        fondsen.add(new Fonds("Blij Brillen BV",23));
        fondsen.add(new Fonds("Pieters Pleintjes",12));
        fondsen.add(new Fonds("Gezichtenboek", 55));
        fondsen.add(new Fonds("Google", 80));
        fondsen.add(new Fonds("Lidl", 12));
        fondsen.add(new Fonds("Fonds6", 13));

        //Publisher
        String[] properties = new String[1];
        properties[0] = "fondsen";
        publisher = new fontys.observer.BasicPublisher(properties);


        //Timer om de koersen te updaten.
        timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Update();
            }
        }, 0, 2000);



        Registry registry = null;
        try
        {
            registry = LocateRegistry.createRegistry(1099);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        try {
            IEffectenbeurs effectenbeurs = (IEffectenbeurs)this;
            if (registry != null) registry.rebind("Effectenbeurs", effectenbeurs);
        }
        catch (RemoteException exc) {
            exc.printStackTrace();
        }

    }

    @Override
    public ArrayList<IFonds> getKoersen() {
        return fondsen;
    }


    public void Update() {
        for (IFonds f : fondsen)
        {
            double modifier = (r.nextInt(4)-2)+r.nextDouble();
            Fonds fonds = (Fonds)f;
            fonds.setKoers(modifier);
        }
        publisher.inform(this, "fondsen", null, fondsen);
    }

    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {
        publisher.addListener(listener, property);
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {
        publisher.removeListener(listener, property);
    }
}