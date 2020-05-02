package MonitoringSystem;


/**
 * Generated from IDL interface "MonitoringStation".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public interface MonitoringStationOperations
{
	/* constants */
	/* operations  */
	java.lang.String station_name();
	java.lang.String location();
	boolean is_active();
	MonitoringSystem.NoxReading get_reading();
	void activate(java.lang.String local_server);
}
