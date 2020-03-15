package MonitoringSystem;


/**
 * Generated from IDL interface "MonitoringCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
 */

public interface MonitoringCentreOperations
{
	/* constants */
	/* operations  */
	void raise_alarm(MonitoringSystem.NoxReading alarm_reading);
	void register_agency(java.lang.String who, java.lang.String contact_details, java.lang.String area_of_interest);
	void register_local_server(java.lang.String server_name);
}
