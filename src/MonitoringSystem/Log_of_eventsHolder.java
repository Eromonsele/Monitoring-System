package MonitoringSystem;

/**
 * Generated from IDL alias "Log_of_events".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public final class Log_of_eventsHolder
	implements org.omg.CORBA.portable.Streamable
{
	public java.lang.String[] value;

	public Log_of_eventsHolder ()
	{
	}
	public Log_of_eventsHolder (final java.lang.String[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return Log_of_eventsHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = Log_of_eventsHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		Log_of_eventsHelper.write (out,value);
	}
}
