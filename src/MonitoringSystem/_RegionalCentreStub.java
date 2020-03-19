package MonitoringSystem;


/**
 * Generated from IDL interface "RegionalCentre".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 18-Mar-2020 11:09:29
 */

public class _RegionalCentreStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements MonitoringSystem.RegionalCentre
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:MonitoringSystem/RegionalCentre:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = MonitoringSystem.RegionalCentreOperations.class;
	public java.lang.String name()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_name",true);
					_is = _invoke(_os);
					return _is.read_string();
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
						_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
						throw new RuntimeException("Unexpected exception " + _id );
				}
				finally
				{
					if (_os != null)
					{
						try
						{
							_os.close();
						}
						catch (java.io.IOException e)
						{
							throw new RuntimeException("Unexpected exception " + e.toString() );
						}
					}
					this._releaseReply(_is);
				}
			}

			else
			{
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_name", _opsClass);
				if( _so == null )
					continue;
				RegionalCentreOperations _localServant = (RegionalCentreOperations)_so.servant;
				java.lang.String _result;
				try
				{
					_result = _localServant.name();
					if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
						((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
						return _result;
				}
				catch (RuntimeException re) 
				{
					if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
					throw re;
				}
				catch (java.lang.Error err) 
				{
					if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
					throw err;
				}
				finally
				{
					_servant_postinvoke(_so);
				}
			}

		}

	}

	public void raise_alarm(MonitoringSystem.NoxReading alarmReading)
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "raise_alarm", true);
					MonitoringSystem.NoxReadingHelper.write(_os,alarmReading);
					_is = _invoke(_os);
					return;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "raise_alarm", _opsClass );
			if( _so == null )
				continue;
			RegionalCentreOperations _localServant = (RegionalCentreOperations)_so.servant;
			try
			{
				_localServant.raise_alarm(alarmReading);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

	public java.lang.String location_name()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_location_name",true);
					_is = _invoke(_os);
					return _is.read_string();
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
						_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
						throw new RuntimeException("Unexpected exception " + _id );
				}
				finally
				{
					if (_os != null)
					{
						try
						{
							_os.close();
						}
						catch (java.io.IOException e)
						{
							throw new RuntimeException("Unexpected exception " + e.toString() );
						}
					}
					this._releaseReply(_is);
				}
			}

			else
			{
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_location_name", _opsClass);
				if( _so == null )
					continue;
				RegionalCentreOperations _localServant = (RegionalCentreOperations)_so.servant;
				java.lang.String _result;
				try
				{
					_result = _localServant.location_name();
					if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
						((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
						return _result;
				}
				catch (RuntimeException re) 
				{
					if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
					throw re;
				}
				catch (java.lang.Error err) 
				{
					if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
					throw err;
				}
				finally
				{
					_servant_postinvoke(_so);
				}
			}

		}

	}

	public void add_monitoring_station(java.lang.String station_name, java.lang.String station_location, java.lang.String station_ior)
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "add_monitoring_station", true);
					java.lang.String tmpResult9 = station_name;
_os.write_string( tmpResult9 );
					java.lang.String tmpResult10 = station_location;
_os.write_string( tmpResult10 );
					java.lang.String tmpResult11 = station_ior;
_os.write_string( tmpResult11 );
					_is = _invoke(_os);
					return;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "add_monitoring_station", _opsClass );
			if( _so == null )
				continue;
			RegionalCentreOperations _localServant = (RegionalCentreOperations)_so.servant;
			try
			{
				_localServant.add_monitoring_station(station_name,station_location,station_ior);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

	public MonitoringSystem.NoxReading[] take_readings()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "take_readings", true);
					_is = _invoke(_os);
					MonitoringSystem.NoxReading[] _result = MonitoringSystem.Set_of_readingsHelper.read(_is);
					return _result;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "take_readings", _opsClass );
			if( _so == null )
				continue;
			RegionalCentreOperations _localServant = (RegionalCentreOperations)_so.servant;
			MonitoringSystem.NoxReading[] _result;
			try
			{
				_result = _localServant.take_readings();
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

	public MonitoringSystem.NoxReading[] log()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request("_get_log",true);
					_is = _invoke(_os);
					return MonitoringSystem.Log_of_alarm_readingsHelper.read(_is);
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
						_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
						throw new RuntimeException("Unexpected exception " + _id );
				}
				finally
				{
					if (_os != null)
					{
						try
						{
							_os.close();
						}
						catch (java.io.IOException e)
						{
							throw new RuntimeException("Unexpected exception " + e.toString() );
						}
					}
					this._releaseReply(_is);
				}
			}

			else
			{
				org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "_get_log", _opsClass);
				if( _so == null )
					continue;
				RegionalCentreOperations _localServant = (RegionalCentreOperations)_so.servant;
				MonitoringSystem.NoxReading[] _result;
				try
				{
					_result = _localServant.log();
					if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
						((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
						return _result;
				}
				catch (RuntimeException re) 
				{
					if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
					throw re;
				}
				catch (java.lang.Error err) 
				{
					if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
						((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
					throw err;
				}
				finally
				{
					_servant_postinvoke(_so);
				}
			}

		}

	}

}
