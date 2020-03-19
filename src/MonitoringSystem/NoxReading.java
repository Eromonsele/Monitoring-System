package MonitoringSystem;

/**
 * Generated from IDL struct "NoxReading".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 18-Mar-2020 11:09:29
 */

public final class NoxReading
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public NoxReading(){}
	public java.lang.String datetime = "";
	public java.lang.String station_name = "";
	public int reading_value;
	public NoxReading(java.lang.String datetime, java.lang.String station_name, int reading_value)
	{
		this.datetime = datetime;
		this.station_name = station_name;
		this.reading_value = reading_value;
	}
}
