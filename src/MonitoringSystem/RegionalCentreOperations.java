package MonitoringSystem;


/**
 * Generated from IDL interface "RegionalCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
 */

public interface RegionalCentreOperations
{
	/* constants */
	/* operations  */
	java.lang.String name();
	java.lang.String location_name();
	MonitoringSystem.NoxReading[] log();
	void raise_alarm(MonitoringSystem.NoxReading alarmReading);
	MonitoringSystem.NoxReading[] take_readings();
	void add_monitoring_station(java.lang.String station_name, java.lang.String station_location, java.lang.String station_ior);
}
