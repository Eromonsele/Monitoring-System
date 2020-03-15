package MonitoringSystem;


/**
 * Generated from IDL interface "MonitoringStation".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
 */

public abstract class MonitoringStationHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(MonitoringStationHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:MonitoringSystem/MonitoringStation:1.0", "MonitoringStation");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final MonitoringSystem.MonitoringStation s)
	{
			any.insert_Object(s);
	}
	public static MonitoringSystem.MonitoringStation extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:MonitoringSystem/MonitoringStation:1.0";
	}
	public static MonitoringStation read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(MonitoringSystem._MonitoringStationStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final MonitoringSystem.MonitoringStation s)
	{
		_out.write_Object(s);
	}
	public static MonitoringSystem.MonitoringStation narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof MonitoringSystem.MonitoringStation)
		{
			return (MonitoringSystem.MonitoringStation)obj;
		}
		else if (obj._is_a("IDL:MonitoringSystem/MonitoringStation:1.0"))
		{
			MonitoringSystem._MonitoringStationStub stub;
			stub = new MonitoringSystem._MonitoringStationStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static MonitoringSystem.MonitoringStation unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof MonitoringSystem.MonitoringStation)
		{
			return (MonitoringSystem.MonitoringStation)obj;
		}
		else
		{
			MonitoringSystem._MonitoringStationStub stub;
			stub = new MonitoringSystem._MonitoringStationStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
