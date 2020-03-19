import MonitoringSystem.*;
import MonitoringSystem.MonitoringStation;
import org.omg.CORBA.ORB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LocalServerImpl extends RegionalCentrePOA{

    private String name;
    private String location_name;
    private ArrayList<NoxReading> logOfAlarmReadings;
    private ArrayList<String> monitoringStations;
    private ORB orb;
    private LocalServer parent;
    private Set_of_readingsHolder sets_of_readings;

    private Log_of_alarm_readingsHolder log_of_alarm_readings;

    public LocalServerImpl(LocalServer parentGUI,ORB orb_val){
        super();
        parent = parentGUI;
        orb = orb_val;
        monitoringStations = new ArrayList<String>();
        logOfAlarmReadings = new ArrayList<NoxReading>();
        sets_of_readings = new Set_of_readingsHolder();
        log_of_alarm_readings = new Log_of_alarm_readingsHolder();
    }

    public String name() {
        return name;
    }

    public String location_name() {
        return location_name;
    }

    public NoxReading[] log() {
        return log_of_alarm_readings.value;
    }

    public NoxReading[] take_readings() {
        return sets_of_readings.value;
    }


    public void raise_alarm(NoxReading alarmReading) {
        logOfAlarmReadings.add(alarmReading);
    }

    public void add_monitoring_station(String station_name, String station_location, String station_ior) {
        String mStation = station_name+":"+station_location+":"+station_ior;

        if (!monitoringStations.contains(mStation)){
            monitoringStations.add(mStation);
            parent.addMessage(station_name + " at " + station_location + " has connected to this local server");
        }else{
            parent.addMessage(station_name + " at " + station_location + " has reactivated to this local server");
        }
    }

}
