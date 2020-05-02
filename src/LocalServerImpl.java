import MonitoringSystem.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Local Server Implementation
 * @author Eromosele Okhilua U1671506
 */
public class LocalServerImpl extends RegionalCentrePOA{
    private String name; // name of local server
    private String location_name; // location of local server
    private ORB orb;
    private LocalServer parent; // parent GUI
    private MonitoringSystem.MonitoringCentre monitoringCentre; // Ref to the monitoring Centre

    private ArrayList<String> monitoringStations; // Arraylist of monitoring stations
    private ArrayList<String> log; // Arraylist of events log
    private ArrayList<NoxReading> alarm_readings; // Arraylist of alarm readings

    private Set_of_readingsHolder sets_of_readings;
    private Log_of_alarm_readingsHolder log_of_alarm_readings;
    private Log_of_eventsHolder events;

    private boolean is_connected = false;

    // Constructor
    public LocalServerImpl(String name, String location_name,ORB orb, LocalServer parent) {
        this.name = name;
        this.location_name = location_name;
        this.orb = orb;
        this.parent = parent;

        monitoringStations = new ArrayList<String>();
        log = new ArrayList<String>();
        alarm_readings = new ArrayList<NoxReading>();

        sets_of_readings = new Set_of_readingsHolder();
        log_of_alarm_readings = new Log_of_alarm_readingsHolder();
        events = new Log_of_eventsHolder();
    }

    /**
     * Returns the local server name
     * @return a string containing the local server name
     */
    public String name() {
        return name;
    }

    /**
     * Returns the location of the local server
     * @return a string containing the location of the local server
     */
    public String location_name() {
        return location_name;
    }

    /**
     * Returns a log of alarm readings
     * @return an Array object containing NoxReading objects
     */
    public NoxReading[] log() {
        log_of_alarm_readings.value = alarm_readings.toArray(new NoxReading[alarm_readings.size()]);
        return log_of_alarm_readings.value;
    }

    /**
     *  Returns a log of registered events
     */
    public String[] log_of_events() {
        events.value = log.toArray(new String[log.size()]);
        return events.value;
    }

    /**
     * Raises an alarm on the local server and on the monitoring centre
     * @param alarmReading a nox reading object that contains the alarm value
     */
    public void raise_alarm(NoxReading alarmReading) {
        alarm_readings.add(alarmReading);
        add_to_log("Method call: raise_alarm from monitoring station "+alarmReading.station_name );

        if (alarm_readings.size() > 1){
            NoxReading prevAlarm = alarm_readings.get(alarm_readings.size()-1);

            if(prevAlarm.time - alarmReading.time <= 5 && monitoringCentre != null){
                add_to_log("Method call: raise_alarm at the environmental centre");
                monitoringCentre.raise_alarm(alarmReading);
            }
        }
    }

    /**
     * Take the readings from all the connected monitoring station
     * @return An array list object containing
     */
    public NoxReading[] take_readings() {
        add_to_log("Method call: take_readings: Requested by:"+monitoringCentre.name());
        NoxReading[] readings = new NoxReading[monitoringStations.size()];
        try{
            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt nameService = NamingContextExtHelper.narrow(orb.resolve_initial_references ("NameService"));
            if (nameService == null) {
                System.out.println("nameService = null");
            }

            for (int i = 0; i < monitoringStations.size(); i++) {
                String[] value = monitoringStations.get(i).split("\\:");
                String station_name = value[0];

                MonitoringStation monitoringStation = MonitoringStationHelper.narrow(nameService.resolve_str(station_name+".Monitoring Station"));
                readings[i] = monitoringStation.get_reading();
            }

            sets_of_readings.value = readings;
        }catch(Exception e){
            return null;
        }
        return sets_of_readings.value;
    }

    /**
     * Add a monitoring station to t
     * @param station_name a string containing the name of the station
     * @param station_location a string containing the location of the station
     */
    public void add_monitoring_station(String station_name, String station_location) {
        add_to_log("Method call: add_monitoring_station");

        String mStation = station_name+":"+station_location;

        if (!monitoringStations.contains(mStation)){
            monitoringStations.add(mStation);
            parent.addMessage(station_name + " at " + station_location + " has connected to this local server");
        }else{
            parent.addMessage(station_name + " at " + station_location + " has reactivated to this local server");
        }
    }

    /**
     * Add to the event log
     * @param event a string containing the name of the event log
     */
    public void add_to_log(String event) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log.add(dtf.format(now)+"::"+event);
        parent.addMessage(dtf.format(now)+"::"+event);
    }

    /**
     * Connects to a monitoring center
     * @param center_name a string containing the name of the monitoring centre
     */
    public boolean connect_to_monitoring_center(String center_name) {
        // add method to call
        add_to_log("Method call: Connect_to_monitoring_center");

        try {
            // Get a reference to the Naming service
            org.omg.CORBA.Object nameServiceObj = orb.resolve_initial_references ("NameService");

            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt nameService = NamingContextExtHelper.narrow(nameServiceObj);
            monitoringCentre = MonitoringCentreHelper.narrow(nameService.resolve_str(center_name+".HQ"));
            monitoringCentre.register_local_server(name());


        } catch (Exception e) {
            parent.addMessage("Connection to monitoring center was unsuccessful");
            return false;
        }

        parent.addMessage("Connection to local server has been established");
        is_connected = true;
        return true;
    }

}
