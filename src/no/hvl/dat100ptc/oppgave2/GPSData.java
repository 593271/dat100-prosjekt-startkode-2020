package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		   gpspoints = new GPSPoint[n];
	        antall = 0;
	    }



	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

        boolean inserted = false;

        if (antall < gpspoints.length){
            gpspoints[antall] = gpspoint;
            antall += 1;
            inserted = true;
        }

        return inserted;
 
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {


	        // Sender inn data, disse blir konvertert, returnerer et GPS punkt
	        GPSPoint point = GPSDataConverter.convert(time, latitude, longitude, elevation);

	        // Legg punktet til i arrayet
	        boolean success = insertGPS(point);

	        return success;
		
	}

	public void print() {
		  System.out.println("====== Konvertert GPS Data - START ======");

	        for(int i = 0; i < antall; i++) {
	            // Alternativ 1
//	            System.out.print(gpspoints[i].toString());

	            // Alternativ 2
	            String resultat = gpspoints[i].toString();
	            System.out.print(resultat);
	        }

	        System.out.println("====== Konvertert GPS Data - SLUTT ======");

	    }

	}

