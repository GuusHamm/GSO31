package main.java;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

	private AEXBanner banner;
	private IEffectenbeurs effectenbeurs;
	private Timer pollingTimer;

	public BannerController(AEXBanner banner) {

		this.banner = banner;
		this.effectenbeurs = new MockEffectenbeurs();

		// Start polling timer: update banner every two seconds
		pollingTimer = new Timer();
		pollingTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				Update();
			}
		}, 0 , 1000);
	}

	// Stop banner controller
	public void stop() {
		pollingTimer.cancel();
		// Stop simulation timer of effectenbeurs
		// TODO
	}
public void Update() {
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