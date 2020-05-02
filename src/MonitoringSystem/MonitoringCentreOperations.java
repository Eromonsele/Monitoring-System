package MonitoringSystem;


/**
 * Generated from IDL interface "MonitoringCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public interface MonitoringCentreOperations
{
	/* constants */
	/* operations  */
	java.lang.String name();
	void raise_alarm(MonitoringSystem.NoxReading alarm_reading);
	void register_agency(java.lang.String who, java.lang.String contact_details, java.lang.String area_of_interest);
	void register_local_server(java.lang.String server_name);
	void pull_local_server_readings();
	void pull_local_servers_log();
	void pull_local_servers_alarm_log();
	void notify_agency(MonitoringSystem.NoxReading alarm_reading);
}
