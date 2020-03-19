package MonitoringSystem;

/**
 * Generated from IDL alias "Log_of_alarm_readings".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 18-Mar-2020 11:09:29
 */

public abstract class Log_of_alarm_readingsHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, MonitoringSystem.NoxReading[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static MonitoringSystem.NoxReading[] extract (final org.omg.CORBA.Any any)
	{
		if ( any.type().kind() == org.omg.CORBA.TCKind.tk_null)
		{
			throw new org.omg.CORBA.BAD_OPERATION ("Can't extract from Any with null type.");
		}
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(Log_of_alarm_readingsHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(MonitoringSystem.Log_of_alarm_readingsHelper.id(), "Log_of_alarm_readings",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(MonitoringSystem.NoxReadingHelper.id(),"NoxReading",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("datetime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("station_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("reading_value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:MonitoringSystem/Log_of_alarm_readings:1.0";
	}
	public static MonitoringSystem.NoxReading[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		MonitoringSystem.NoxReading[] _result;
		int _l_result1 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result1 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result1);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new MonitoringSystem.NoxReading[_l_result1];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=MonitoringSystem.NoxReadingHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, MonitoringSystem.NoxReading[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			MonitoringSystem.NoxReadingHelper.write(_out,_s[i]);
		}

	}
}
