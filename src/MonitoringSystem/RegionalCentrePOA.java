package MonitoringSystem;


/**
 * Generated from IDL interface "RegionalCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
 */

public abstract class RegionalCentrePOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, MonitoringSystem.RegionalCentreOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "_get_name", Integer.valueOf(0));
		m_opsHash.put ( "raise_alarm", Integer.valueOf(1));
		m_opsHash.put ( "_get_location_name", Integer.valueOf(2));
		m_opsHash.put ( "add_monitoring_station", Integer.valueOf(3));
		m_opsHash.put ( "take_readings", Integer.valueOf(4));
		m_opsHash.put ( "_get_log", Integer.valueOf(5));
	}
	private String[] ids = {"IDL:MonitoringSystem/RegionalCentre:1.0"};
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
			case 1: // raise_alarm
			{
				MonitoringSystem.NoxReading _arg0=MonitoringSystem.NoxReadingHelper.read(_input);
				_out = handler.createReply();
				raise_alarm(_arg0);
				break;
			}
			case 2: // _get_location_name
			{
			_out = handler.createReply();
			java.lang.String tmpResult16 = location_name();
_out.write_string( tmpResult16 );
				break;
			}
			case 3: // add_monitoring_station
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				_out = handler.createReply();
				add_monitoring_station(_arg0,_arg1,_arg2);
				break;
			}
			case 4: // take_readings
			{
				_out = handler.createReply();
				MonitoringSystem.Set_of_readingsHelper.write(_out,take_readings());
				break;
			}
			case 5: // _get_log
			{
			_out = handler.createReply();
			MonitoringSystem.Log_of_alarm_readingsHelper.write(_out,log());
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
