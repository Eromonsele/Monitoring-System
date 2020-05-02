package MonitoringSystem;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "RegionalCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public class RegionalCentrePOATie
	extends RegionalCentrePOA
{
	private RegionalCentreOperations _delegate;

	private POA _poa;
	public RegionalCentrePOATie(RegionalCentreOperations delegate)
	{
		_delegate = delegate;
	}
	public RegionalCentrePOATie(RegionalCentreOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public MonitoringSystem.RegionalCentre _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		MonitoringSystem.RegionalCentre __r = MonitoringSystem.RegionalCentreHelper.narrow(__o);
		return __r;
	}
	public MonitoringSystem.RegionalCentre _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		MonitoringSystem.RegionalCentre __r = MonitoringSystem.RegionalCentreHelper.narrow(__o);
		return __r;
	}
	public RegionalCentreOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(RegionalCentreOperations delegate)
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

	public void raise_alarm(MonitoringSystem.NoxReading alarmReading)
	{
_delegate.raise_alarm(alarmReading);
	}

	public MonitoringSystem.NoxReading[] log()
	{
		return _delegate.log();
	}

	public boolean connect_to_monitoring_center(java.lang.String center_name)
	{
		return _delegate.connect_to_monitoring_center(center_name);
	}

	public java.lang.String[] log_of_events()
	{
		return _delegate.log_of_events();
	}

	public MonitoringSystem.NoxReading[] take_readings()
	{
		return _delegate.take_readings();
	}

	public void add_monitoring_station(java.lang.String station_name, java.lang.String station_location)
	{
_delegate.add_monitoring_station(station_name,station_location);
	}

	public void add_to_log(java.lang.String event)
	{
_delegate.add_to_log(event);
	}

	public java.lang.String location_name()
	{
		return _delegate.location_name();
	}

}
