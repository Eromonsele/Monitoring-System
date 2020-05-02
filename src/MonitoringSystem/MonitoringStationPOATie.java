package MonitoringSystem;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "MonitoringStation".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public class MonitoringStationPOATie
	extends MonitoringStationPOA
{
	private MonitoringStationOperations _delegate;

	private POA _poa;
	public MonitoringStationPOATie(MonitoringStationOperations delegate)
	{
		_delegate = delegate;
	}
	public MonitoringStationPOATie(MonitoringStationOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public MonitoringSystem.MonitoringStation _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		MonitoringSystem.MonitoringStation __r = MonitoringSystem.MonitoringStationHelper.narrow(__o);
		return __r;
	}
	public MonitoringSystem.MonitoringStation _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		MonitoringSystem.MonitoringStation __r = MonitoringSystem.MonitoringStationHelper.narrow(__o);
		return __r;
	}
	public MonitoringStationOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(MonitoringStationOperations delegate)
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
	public java.lang.String station_name()
	{
		return _delegate.station_name();
	}

	public void activate(java.lang.String local_server)
	{
_delegate.activate(local_server);
	}

	public MonitoringSystem.NoxReading get_reading()
	{
		return _delegate.get_reading();
	}

	public java.lang.String location()
	{
		return _delegate.location();
	}

	public boolean is_active()
	{
		return _delegate.is_active();
	}

}
