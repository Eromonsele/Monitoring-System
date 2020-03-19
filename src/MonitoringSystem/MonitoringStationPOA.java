package MonitoringSystem;


/**
 * Generated from IDL interface "MonitoringStation".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 18-Mar-2020 11:09:29
 */

public abstract class MonitoringStationPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, MonitoringSystem.MonitoringStationOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "activate", Integer.valueOf(0));
		m_opsHash.put ( "deactivate", Integer.valueOf(1));
		m_opsHash.put ( "_get_station_name", Integer.valueOf(2));
		m_opsHash.put ( "send_alerts", Integer.valueOf(3));
		m_opsHash.put ( "_get_location", Integer.valueOf(4));
		m_opsHash.put ( "get_reading", Integer.valueOf(5));
		m_opsHash.put ( "reset", Integer.valueOf(6));
		m_opsHash.put ( "_get_is_active", Integer.valueOf(7));
		m_opsHash.put ( "_get_ior", Integer.valueOf(8));
	}
	private String[] ids = {"IDL:MonitoringSystem/MonitoringStation:1.0"};
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
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // activate
			{
				_out = handler.createReply();
				_out.write_boolean(activate());
				break;
			}
			case 1: // deactivate
			{
				_out = handler.createReply();
				deactivate();
				break;
			}
			case 2: // _get_station_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult2 = station_name();
_out.write_string( tmpResult2 );
				break;
			}
			case 3: // send_alerts
			{
				MonitoringSystem.NoxReading _arg0=MonitoringSystem.NoxReadingHelper.read(_input);
				_out = handler.createReply();
				send_alerts(_arg0);
				break;
			}
			case 4: // _get_location
			{
			_out = handler.createReply();
			java.lang.String tmpResult3 = location();
_out.write_string( tmpResult3 );
				break;
			}
			case 5: // get_reading
			{
				_out = handler.createReply();
				MonitoringSystem.NoxReadingHelper.write(_out,get_reading());
				break;
			}
			case 6: // reset
			{
				_out = handler.createReply();
				reset();
				break;
			}
			case 7: // _get_is_active
			{
			_out = handler.createReply();
			_out.write_boolean(is_active());
				break;
			}
			case 8: // _get_ior
			{
			_out = handler.createReply();
			java.lang.String tmpResult4 = ior();
_out.write_string( tmpResult4 );
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
