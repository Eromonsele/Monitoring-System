package MonitoringSystem;

/**
 * Generated from IDL struct "NoxReading".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public final class NoxReadingHolder
	implements org.omg.CORBA.portable.Streamable
{
	public MonitoringSystem.NoxReading value;

	public NoxReadingHolder ()
	{
	}
	public NoxReadingHolder(final MonitoringSystem.NoxReading initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return MonitoringSystem.NoxReadingHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = MonitoringSystem.NoxReadingHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		MonitoringSystem.NoxReadingHelper.write(_out, value);
	}
}
