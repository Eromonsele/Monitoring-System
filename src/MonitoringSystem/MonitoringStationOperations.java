package MonitoringSystem;


/**
 * Generated from IDL interface "MonitoringStation".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 18-Mar-2020 11:09:29
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
	void send_alerts(MonitoringSystem.NoxReading reading);
	boolean activate();
	void deactivate();
	void reset();
}
