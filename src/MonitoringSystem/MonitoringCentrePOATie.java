package MonitoringSystem;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "MonitoringCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public class MonitoringCentrePOATie
	extends MonitoringCentrePOA
{
	private MonitoringCentreOperations _delegate;

	private POA _poa;
	public MonitoringCentrePOATie(MonitoringCentreOperations delegate)
	{
		_delegate = delegate;
	}
	public MonitoringCentrePOATie(MonitoringCentreOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public MonitoringSystem.MonitoringCentre _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		MonitoringSystem.MonitoringCentre __r = MonitoringSystem.MonitoringCentreHelper.narrow(__o);
		return __r;
	}
	public MonitoringSystem.MonitoringCentre _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		MonitoringSystem.MonitoringCentre __r = MonitoringSystem.MonitoringCentreHelper.narrow(__o);
		return __r;
	}
	public MonitoringCentreOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(MonitoringCentreOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public java.lang.String name()
	{
		return _delegate.name();
	}

	public void pull_local_servers_alarm_log()
	{
_delegate.pull_local_servers_alarm_log();
	}

	public void pull_local_servers_log()
	{
_delegate.pull_local_servers_log();
	}

	public void raise_alarm(MonitoringSystem.NoxReading alarm_reading)
	{
_delegate.raise_alarm(alarm_reading);
	}

	public void pull_local_server_readings()
	{
_delegate.pull_local_server_readings();
	}

	public void register_local_server(java.lang.String server_name)
	{
_delegate.register_local_server(server_name);
	}

	public void notify_agency(MonitoringSystem.NoxReading alarm_reading)
	{
_delegate.notify_agency(alarm_reading);
	}

	public void register_agency(java.lang.String who, java.lang.String contact_details, java.lang.String area_of_interest)
	{
_delegate.register_agency(who,contact_details,area_of_interest);
	}

}
