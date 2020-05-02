package MonitoringSystem;

/**
 * Generated from IDL struct "NoxReading".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 30-Apr-2020 10:30:15
 */

public final class NoxReading
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public NoxReading(){}
	public int time;
	public int date;
	public java.lang.String station_name = "";
	public int reading_value;
	public NoxReading(int time, int date, java.lang.String station_name, int reading_value)
	{
		this.time = time;
		this.date = date;
		this.station_name = station_name;
		this.reading_value = reading_value;
	}
}
