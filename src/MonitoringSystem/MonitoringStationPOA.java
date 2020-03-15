package MonitoringSystem;


/**
 * Generated from IDL interface "MonitoringStation".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
 */

public abstract class MonitoringStationPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, MonitoringSystem.MonitoringStationOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "_get_ior", Integer.valueOf(0));
		m_opsHash.put ( "_get_station_name", Integer.valueOf(1));
		m_opsHash.put ( "deactivate", Integer.valueOf(2));
		m_opsHash.put ( "get_reading", Integer.valueOf(3));
		m_opsHash.put ( "reset", Integer.valueOf(4));
		m_opsHash.put ( "_get_location", Integer.valueOf(5));
		m_opsHash.put ( "activate", Integer.valueOf(6));
		m_opsHash.put ( "_get_is_active", Integer.valueOf(7));
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
			case 0: // _get_ior
			{
			_out = handler.createReply();
			java.lang.String tmpResult5 = ior();
_out.write_string( tmpResult5 );
				break;
			}
			case 1: // _get_station_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult6 = station_name();
_out.write_string( tmpResult6 );
				break;
			}
			case 2: // deactivate
			{
				_out = handler.createReply();
				deactivate();
				break;
			}
			case 3: // get_reading
			{
				_out = handler.createReply();
				MonitoringSystem.NoxReadingHelper.write(_out,get_reading());
				break;
			}
			case 4: // reset
			{
				_out = handler.createReply();
				reset();
				break;
			}
			case 5: // _get_location
			{
			_out = handler.createReply();
			java.lang.String tmpResult7 = location();
_out.write_string( tmpResult7 );
				break;
			}
			case 6: // activate
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				_out.write_boolean(activate(_arg0,_arg1,_arg2));
				break;
			}
			case 7: // _get_is_active
			{
			_out = handler.createReply();
			_out.write_boolean(is_active());
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
