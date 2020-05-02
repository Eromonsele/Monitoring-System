package MonitoringSystem;


/**
 * Generated from IDL interface "RegionalCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public interface RegionalCentreOperations
{
	/* constants */
	/* operations  */
	java.lang.String name();
	java.lang.String location_name();
	MonitoringSystem.NoxReading[] log();
	java.lang.String[] log_of_events();
	void raise_alarm(MonitoringSystem.NoxReading alarmReading);
	MonitoringSystem.NoxReading[] take_readings();
	void add_monitoring_station(java.lang.String station_name, java.lang.String station_location);
	void add_to_log(java.lang.String event);
	boolean connect_to_monitoring_center(java.lang.String center_name);
}
