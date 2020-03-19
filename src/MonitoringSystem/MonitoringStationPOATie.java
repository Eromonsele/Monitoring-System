package MonitoringSystem;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "MonitoringStation".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 18-Mar-2020 11:09:29
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
	public boolean activate()
	{
		return _delegate.activate();
	}

	public void deactivate()
	{
_delegate.deactivate();
	}

	public java.lang.String station_name()
	{
		return _delegate.station_name();
	}

	public void send_alerts(MonitoringSystem.NoxReading reading)
	{
_delegate.send_alerts(reading);
	}

	public java.lang.String location()
	{
		return _delegate.location();
	}

	public MonitoringSystem.NoxReading get_reading()
	{
		return _delegate.get_reading();
	}

	public void reset()
	{
_delegate.reset();
	}

	public boolean is_active()
	{
		return _delegate.is_active();
	}

	public java.lang.String ior()
	{
		return _delegate.ior();
	}

}
