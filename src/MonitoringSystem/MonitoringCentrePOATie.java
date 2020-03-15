package MonitoringSystem;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "MonitoringCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
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
	public void raise_alarm(MonitoringSystem.NoxReading alarm_reading)
	{
_delegate.raise_alarm(alarm_reading);
	}

	public void register_local_server(java.lang.String server_name)
	{
_delegate.register_local_server(server_name);
	}

	public void register_agency(java.lang.String who, java.lang.String contact_details, java.lang.String area_of_interest)
	{
_delegate.register_agency(who,contact_details,area_of_interest);
	}

}
