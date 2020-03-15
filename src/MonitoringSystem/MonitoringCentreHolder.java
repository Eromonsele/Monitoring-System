package MonitoringSystem;

/**
 * Generated from IDL interface "MonitoringCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
 */

public final class MonitoringCentreHolder	implements org.omg.CORBA.portable.Streamable{
	 public MonitoringCentre value;
	public MonitoringCentreHolder()
	{
	}
	public MonitoringCentreHolder (final MonitoringCentre initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return MonitoringCentreHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = MonitoringCentreHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		MonitoringCentreHelper.write (_out,value);
	}
}
