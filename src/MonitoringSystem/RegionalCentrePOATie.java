package MonitoringSystem;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "RegionalCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
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

	public java.lang.String location_name()
	{
		return _delegate.location_name();
	}

	public void add_monitoring_station(java.lang.String station_name, java.lang.String station_location, java.lang.String station_ior)
	{
_delegate.add_monitoring_station(station_name,station_location,station_ior);
	}

	public MonitoringSystem.NoxReading[] take_readings()
	{
		return _delegate.take_readings();
	}

	public MonitoringSystem.NoxReading[] log()
	{
		return _delegate.log();
	}

}
