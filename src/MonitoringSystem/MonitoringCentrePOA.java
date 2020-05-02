package MonitoringSystem;


/**
 * Generated from IDL interface "MonitoringCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public abstract class MonitoringCentrePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, MonitoringSystem.MonitoringCentreOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "_get_name", Integer.valueOf(0));
		m_opsHash.put ( "pull_local_servers_alarm_log", Integer.valueOf(1));
		m_opsHash.put ( "pull_local_servers_log", Integer.valueOf(2));
		m_opsHash.put ( "raise_alarm", Integer.valueOf(3));
		m_opsHash.put ( "pull_local_server_readings", Integer.valueOf(4));
		m_opsHash.put ( "register_local_server", Integer.valueOf(5));
		m_opsHash.put ( "notify_agency", Integer.valueOf(6));
		m_opsHash.put ( "register_agency", Integer.valueOf(7));
	}
	private String[] ids = {"IDL:MonitoringSystem/MonitoringCentre:1.0"};
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
			case 0: // _get_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult15 = name();
_out.write_string( tmpResult15 );
				break;
			}
			case 1: // pull_local_servers_alarm_log
			{
				_out = handler.createReply();
				pull_local_servers_alarm_log();
				break;
			}
			case 2: // pull_local_servers_log
			{
				_out = handler.createReply();
				pull_local_servers_log();
				break;
			}
			case 3: // raise_alarm
			{
				MonitoringSystem.NoxReading _arg0=MonitoringSystem.NoxReadingHelper.read(_input);
				_out = handler.createReply();
				raise_alarm(_arg0);
				break;
			}
			case 4: // pull_local_server_readings
			{
				_out = handler.createReply();
				pull_local_server_readings();
				break;
			}
			case 5: // register_local_server
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				register_local_server(_arg0);
				break;
			}
			case 6: // notify_agency
			{
				MonitoringSystem.NoxReading _arg0=MonitoringSystem.NoxReadingHelper.read(_input);
				_out = handler.createReply();
				notify_agency(_arg0);
				break;
			}
			case 7: // register_agency
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				register_agency(_arg0,_arg1,_arg2);
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
