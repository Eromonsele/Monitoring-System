import MonitoringSystem.*;
import org.omg.CORBA.ORB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MonitoringStationImpl extends MonitoringStationPOA{
    private String station_name;
    private String location;
    private ORB orb;
    private boolean is_active = false;
    private MonitoringSystem.RegionalCentre server;
    private String ior;
    private NoxReading[] readings;

    MonitoringStationImpl(ORB orb_val){
        super();
        orb = orb_val;
    }

    @Override
    public String station_name() {
        return station_name;
    }

    @Override
    public String location() {
        return location;
    }

    @Override
    public boolean is_active() {
        return is_active;
    }

    @Override
    public String ior() {
        return ior;
    }

    @Override
    public NoxReading get_reading() {
        Random rand = new Random();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        NoxReading data = new NoxReading(dtf.format(now),station_name,rand.nextInt(50));
        return data;
    }

    @Override
    public boolean activate(String station_name, String location, String ior) {

        // look up the server
        try {
            // read in the 'stringified IOR'
            BufferedReader in = new BufferedReader(new FileReader("localServer.ref"));
            String stringified_ior = in.readLine();

            // get object reference from stringified IOR
            org.omg.CORBA.Object server_ref =
                    orb.string_to_object(stringified_ior);
            server = MonitoringSystem.RegionalCentreHelper.narrow(server_ref);


            this.station_name = station_name;
            this.location = location;
            this.ior = ior;
            this.is_active = true;
            server.add_monitoring_station(this.station_name,this.location,this.ior);
        } catch (Exception e) {
            return false;
        }

        return true;

    }


    @Override
    public void deactivate() {

    }

    @Override
    public void reset() {

    }
}
