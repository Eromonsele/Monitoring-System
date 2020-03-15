package MonitoringSystem;


/**
 * Generated from IDL interface "MonitoringStation".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
 */

public interface MonitoringStationOperations
{
	/* constants */
	/* operations  */
	java.lang.String station_name();
	java.lang.String location();
	boolean is_active();
	java.lang.String ior();
	MonitoringSystem.NoxReading get_reading();
	boolean activate(java.lang.String station_name, java.lang.String location, java.lang.String ior);
	void deactivate();
	void reset();
}
