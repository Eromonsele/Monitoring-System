package MonitoringSystem;


/**
 * Generated from IDL interface "RegionalCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 18-Mar-2020 11:09:29
 */

public abstract class RegionalCentreHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RegionalCentreHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:MonitoringSystem/RegionalCentre:1.0", "RegionalCentre");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final MonitoringSystem.RegionalCentre s)
	{
			any.insert_Object(s);
	}
	public static MonitoringSystem.RegionalCentre extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:MonitoringSystem/RegionalCentre:1.0";
	}
	public static RegionalCentre read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(MonitoringSystem._RegionalCentreStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final MonitoringSystem.RegionalCentre s)
	{
		_out.write_Object(s);
	}
	public static MonitoringSystem.RegionalCentre narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof MonitoringSystem.RegionalCentre)
		{
			return (MonitoringSystem.RegionalCentre)obj;
		}
		else if (obj._is_a("IDL:MonitoringSystem/RegionalCentre:1.0"))
		{
			MonitoringSystem._RegionalCentreStub stub;
			stub = new MonitoringSystem._RegionalCentreStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static MonitoringSystem.RegionalCentre unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof MonitoringSystem.RegionalCentre)
		{
			return (MonitoringSystem.RegionalCentre)obj;
		}
		else
		{
			MonitoringSystem._RegionalCentreStub stub;
			stub = new MonitoringSystem._RegionalCentreStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
