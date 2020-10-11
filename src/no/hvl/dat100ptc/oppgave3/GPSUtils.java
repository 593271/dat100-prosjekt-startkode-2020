package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;


	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
	
		
			double[] latitude = new double[gpspoints.length];
			
			for (int i = 0; i < gpspoints.length; i++ ) {
				latitude[i] = gpspoints[i].getLatitude();
			}
			
			return latitude;
			
			

	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		
		double[] longitude = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++ ) {
			longitude[i] = gpspoints[i].getLongitude();
		}
		
		return longitude;
		
		
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2; 
		
		latitude1 = gpspoint1.getLatitude();
		latitude2 = gpspoint2.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		longitude2 = gpspoint2.getLongitude();
		
		latitude1 = toRadians(latitude1);
		latitude2 = toRadians(latitude2);
		double phi = (latitude2-latitude1);
		double lambda = toRadians(longitude2-longitude1);
		double a = Math.pow((Math.sin(phi/2)), 2) + (Math.cos(latitude1))*(Math.cos(latitude2))*Math.pow((Math.sin(lambda/2)), 2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		d = (R * c);
		
		return d;
		


		
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		double avstand = distance(gpspoint1, gpspoint2);
		
		secs = gpspoint2.getTime();
		secs -= gpspoint1.getTime();
		
		
		speed = (avstand/secs) * 3.6;
		
		return speed;

		

	}

	public static String formatTime(int secs) {
		
		String timestr;
		String TIMESEP = ":";
		
		int hh,mm,ss;
		
		hh = secs / 3600;
		secs = secs % 3600;
		mm = secs / 60;
		secs = secs % 60;
		ss = secs;
		
		timestr = String.format("  %02d%4$s%02d%4$s%02d",hh,mm,ss,TIMESEP);
		return timestr;
		
		
		
	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		
		
		str = String.format("%10.2f", d);
		
		return str;

	}
}
