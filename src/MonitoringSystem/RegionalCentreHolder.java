package MonitoringSystem;

/**
 * Generated from IDL interface "RegionalCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 15-Mar-2020 17:24:50
 */

public final class RegionalCentreHolder	implements org.omg.CORBA.portable.Streamable{
	 public RegionalCentre value;
	public RegionalCentreHolder()
	{
	}
	public RegionalCentreHolder (final RegionalCentre initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return RegionalCentreHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = RegionalCentreHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		RegionalCentreHelper.write (_out,value);
	}
}
