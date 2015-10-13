import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nekkyou on 13-10-2015.
 */
public class RMIClient
{
    private static final String bindingName = "TestingTest";
    private Timer pollingTimer;

    // References to registry and student administration
    private Registry registry = null;
    private BannerController bannerController = null;

    // Constructor
    public RMIClient(String ipAddress, int portNumber, BannerController bc)
    {
        bannerController = bc;
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Locate registry at IP address and port number
        try
        {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        }
        catch (RemoteException ex)
        {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }
        // Print result locating registry
        if (registry != null)
        {
            System.out.println("Client: Registry located");
        }
        else
        {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }


        // Bind student administration using registry
        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (registry != null)
                {
                    try
                    {
                        bannerController.setEffectenbeurs((IEffectenbeurs) registry.lookup(bindingName));
                    }
                    catch (RemoteException ex)
                    {
                        System.out.println("Client: Cannot bind student administration");
                        System.out.println("Client: RemoteException: " + ex.getMessage());
                        bannerController = null;
                    }
                    catch (NotBoundException ex)
                    {
                        System.out.println("Client: Cannot bind student administration");
                        System.out.println("Client: NotBoundException: " + ex.getMessage());
                        bannerController = null;
                    }
                }
            }
        }, 0 , 1000);


        // Print result binding student administration
        if (bannerController != null)
        {
            System.out.println("Client: Effectenbeurs bound");
        }
        else
        {
            System.out.println("Client: IEffectenbeurs is null pointer");
        }

    }

    public IEffectenbeurs getEffectenbeurs(){
        return bannerController.getEffectenbeurs();
    }
}
