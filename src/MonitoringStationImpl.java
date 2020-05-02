//Import statements
import MonitoringSystem.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;

import java.util.Random;

/**
 * The monitoring Station.
 * @Author Eromosele Okhilua U1671506
 */
public class MonitoringStationImpl extends MonitoringStationPOA{
	private String station_name; // the station name
	private String location;// the location of the station
	private ORB orb;
	private boolean is_active;
	private MonitoringStationServer parent; // parent GUI
	private MonitoringSystem.RegionalCentre regionalCentre; // regional centre

	// Constructor
	public MonitoringStationImpl(String station_name, String location, ORB orb, MonitoringStationServer parent) {
		this.station_name = station_name;
		this.location = location;
		this.orb = orb;
		this.parent = parent;
	}

	/**
	 * Returns the name of the station
	 * @return a string containing the name of the monitoring station
	 */
	public String station_name() {
		return station_name;
	}

	/**
	 * Returns the location of the monitor
	 * @return a string containing the location of the monitoring station
	 */
	public String location() {
		return location;
	}

	/**
	 * Returns the active state of the
	 * @return a boolean indicating if a monitoring station is active
	 */
	public boolean is_active() {
		return is_active;
	}
	/**
	 * The method gets the latest nox readings
	 * @return reading a
	 */
	public NoxReading get_reading() {

		int reading_value = new Random().nextInt(100);
//		int reading_value = 82;
		NoxReading reading = new NoxReading((int)System.currentTimeMillis(),(int)System.currentTimeMillis(),station_name(),reading_value);
		if (reading_value > 80){
			//raise alarm
			regionalCentre.raise_alarm(reading);
		}
		return reading;
	}

	/**
	 * This connects the monitoring  station to a local server
	 * @param local_server a string containing the name of the local server
	 */
	public void activate(String local_server) {
		parent.addMessage("Trying to connect to a regional center");
		if (local_server == null){
			parent.addMessage("Local server name was left blank");
			return;
		}
		try {
			// Get a reference to the Naming service
			org.omg.CORBA.Object nameServiceObj = orb.resolve_initial_references ("NameService");

			// Use NamingContextExt instead of NamingContext. This is
			// part of the Interoperable naming Service.
			NamingContextExt nameService = NamingContextExtHelper.narrow(nameServiceObj);

			regionalCentre = RegionalCentreHelper.narrow(nameService.resolve_str(local_server+".Regional Center"));
			regionalCentre.add_monitoring_station(station_name(),location());

			parent.activateButton.setName("Connected to Regional Center:"+ regionalCentre.name());
			parent.activateButton.setEnabled(false);
		} catch (Exception e) {
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
			parent.addMessage("Connection to regional center was unsuccessful");
			return;
		}
		parent.addMessage("Connection to regional center has been established");
		is_active = true;
	}

}
