package main.java;

import java.util.Random;

/**
 * Created by linux on 6-10-15.
 */
public class Fonds implements IFonds{
    private String naam;
    private double koers;

    public Fonds(String naam, double koers) {
        this.naam = naam;
        this.koers = koers;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public double getKoers() {

        return koers;
    }

    public void setKoers(double modifierDouble){
        if (koers + modifierDouble < 0) {
            koers += (modifierDouble * -1);
        }
        koers += modifierDouble;
    }
}
