package main.java;

import java.util.*;

public class MockEffectenbeurs implements IEffectenbeurs {
    private ArrayList<IFonds> fondsen;

    public MockEffectenbeurs() {
        fondsen = new ArrayList<>();
    }

    @Override
    public List<IFonds> getKoersen() {
        Random r = new Random();
        for (IFonds a : fondsen)
        {
            double modifier = (r.nextInt(6)-3)+r.nextDouble();
            a.setKoers(modifier);
        }
        return fondsen;
    }
}