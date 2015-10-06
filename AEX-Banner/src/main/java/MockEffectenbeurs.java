package main.java;

import main.java.IEffectenbeurs;
import main.java.IFonds;

import java.util.*;

public class MockEffectenbeurs implements IEffectenbeurs {
    private ArrayList<IFonds> fondsen;
    private Timer timer;

    private Random r ;

    public MockEffectenbeurs() {
        fondsen = new ArrayList<>();

        r = new Random();
        // Maak een paar fondsen aan.
        fondsen.add(new Fonds("Blij Brillen BV",23));
        fondsen.add(new Fonds("Pieters Pleintjes",12));
        fondsen.add(new Fonds("Gezichtenboek", 55));
        fondsen.add(new Fonds("Fonds4", 11));
        fondsen.add(new Fonds("Fonds5", 12));
        fondsen.add(new Fonds("Fonds6", 13));

        //Timer om de koersen te updaten.
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Update();
            }
        }, 0, 2000);

    }

    @Override
    public ArrayList<IFonds> getKoersen() {
        return fondsen;
    }

    public void Update() {
        for (IFonds f : fondsen)
        {
            double modifier = (r.nextInt(4)-2)+r.nextDouble();
            f.setKoers(modifier);
        }
    }
}