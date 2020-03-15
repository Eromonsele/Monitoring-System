package MonitoringSystem;


/**
 * Generated from IDL struct "NoxReading".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
 */

public abstract class NoxReadingHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(NoxReadingHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(MonitoringSystem.NoxReadingHelper.id(),"NoxReading",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("datetime", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("station_name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("reading_value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final MonitoringSystem.NoxReading s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static MonitoringSystem.NoxReading extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:MonitoringSystem/NoxReading:1.0";
	}
	public static MonitoringSystem.NoxReading read (final org.omg.CORBA.portable.InputStream in)
	{
		MonitoringSystem.NoxReading result = new MonitoringSystem.NoxReading();
		result.datetime=in.read_string();
		result.station_name=in.read_string();
		result.reading_value=in.read_long();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final MonitoringSystem.NoxReading s)
	{
		java.lang.String tmpResult0 = s.datetime;
out.write_string( tmpResult0 );
		java.lang.String tmpResult1 = s.station_name;
out.write_string( tmpResult1 );
		out.write_long(s.reading_value);
	}
}
