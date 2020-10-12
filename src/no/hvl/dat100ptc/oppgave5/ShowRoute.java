package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));



		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;

		
	}

	public void showRouteMap(int ybase) {
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));


		
		setColor(51,255,0);
		for (int i = 0; i < gpspoints.length-1; i++) {
			

			
			fillCircle((int) (MARGIN+(gpspoints[i].getLongitude()-minlon)*xstep()),(int)(ybase-(gpspoints[i].getLatitude()-minlat)*ystep()),5);
			
			drawLine((int) (MARGIN+(gpspoints[i].getLongitude()-minlon)*xstep()),(int)(ybase-(gpspoints[i].getLatitude()-minlat)*ystep()),
					(int) (MARGIN+(gpspoints[i+1].getLongitude()-minlon)*xstep()),(int)(ybase-(gpspoints[i+1].getLatitude()-minlat)*ystep()));

		}
			
		

	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);

		String streker = "==============================================";
		String totaltid = "Total Time     :" + GPSUtils.formatTime(gpscomputer.totalTime());
		String totaldistance = "Total Distance :" + GPSUtils.formatDouble(gpscomputer.totalDistance()/1000)+" km";
		String totalelevation = "Total elevation:" + GPSUtils.formatDouble(gpscomputer.totalElevation())+" m";
		String maxSpeed = "Max Speed       :" + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t";
		String averageSpeed = "Average speed  :" + GPSUtils.formatDouble(gpscomputer.averageSpeed())+" km/t";
		String totalKcal = "Energy         :" + GPSUtils.formatDouble(gpscomputer.totalKcal(80))+" kcal";

		//for (int i = 0; i < gpspoints.length; i++) {

			//totaltid = "Total Time     :" + GPSUtils.formatTime(gpspoints[i].getTime());
			//drawString(totaltid,TEXTDISTANCE,90);

			
		//}
		
		
		
		drawString(streker,TEXTDISTANCE,10);
		drawString(totaltid,TEXTDISTANCE,20);
		drawString(totaldistance,TEXTDISTANCE,30);
		drawString(totalelevation,TEXTDISTANCE,40);
		drawString(maxSpeed,TEXTDISTANCE,50);
		drawString(averageSpeed,TEXTDISTANCE,60);
		drawString(totalKcal,TEXTDISTANCE,70);
		drawString(streker,TEXTDISTANCE,80);

		

	}

}
