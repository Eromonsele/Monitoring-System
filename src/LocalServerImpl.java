import MonitoringSystem.*;
import MonitoringSystem.MonitoringStation;
import org.omg.CORBA.ORB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LocalServerImpl extends RegionalCentrePOA{

    private String name;
    private String location_name;
    private NoxReading[] log;
    private NoxReading[] Log_of_alarm_readings;
    private NoxReading[] Log_of_readings;
    private ArrayList<String> monitoringStations;
    private ORB orb;
    private LocalServer parent;

    public LocalServerImpl(LocalServer parentGUI,ORB orb_val){
        super();
        parent = parentGUI;
        orb = orb_val;
        monitoringStations = new ArrayList<String>();
        // look up the server
        try {
            // read in the 'stringified IOR'
            BufferedReader in = new BufferedReader(new FileReader("monitoringCenter.ref"));
            String stringified_ior = in.readLine();

            // get object reference from stringified IOR
            org.omg.CORBA.Object server_ref =
                    orb.string_to_object(stringified_ior);

//            server.add_monitoring_station(this.station_name,this.location,stringified_ior);
        } catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
    }
    @Override
    public String name() {
        return name;
    }

    @Override
    public String location_name() {
        return location_name;
    }

    @Override
    public NoxReading[] log() {
        return log;
    }

    @Override
    public void raise_alarm(NoxReading alarmReading) {

    }

    @Override
    public NoxReading[] take_readings() {
        return Log_of_readings;
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
