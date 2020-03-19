import MonitoringSystem.*;
import MonitoringSystem.MonitoringStation;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class MonitoringStationImpl extends MonitoringStationPOA{

    private String station_name;
    private String location;
    private ORB orb;
    private boolean is_active = false;
    private String ior;
    private RegionalCentre regionalCentre;

    MonitoringStationImpl(){

    }

    public String station_name() {
        return station_name;
    }


    public String location() {
        return location;
    }


    public boolean is_active() {
        return is_active;
    }

    public String ior() {
        return ior;
    }

     /**
     *  Send Alerts
     * @param reading
     */
    public void send_alerts(NoxReading reading) {
        if (reading.reading_value > 50 || reading.reading_value < 0){
            regionalCentre.raise_alarm(reading);
        }
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOrb(ORB orb) {
        this.orb = orb;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setIor(String ior) {
        this.ior = ior;
    }

    public NoxReading get_reading() {
        Random rand = new Random();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        NoxReading data = new NoxReading(dtf.format(now),station_name,rand.nextInt(50));
        send_alerts(data);
        return data;
    }

    public boolean activate() {

        try {
            // Get a reference to the Naming service
            org.omg.CORBA.Object nameServiceObj = orb.resolve_initial_references ("NameService");

            if (nameServiceObj == null) {
                System.out.println("nameServiceObj = null");
            }

            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt nameService = NamingContextExtHelper.narrow(nameServiceObj);
            if (nameService == null) {
                System.out.println("nameService = null");
                return false;
            }

            // resolve the Count object reference in the Naming service
            String name = "localServer";
            regionalCentre = RegionalCentreHelper.narrow(nameService.resolve_str(name));

            regionalCentre.add_monitoring_station(station_name(),location(),ior());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        is_active = true;

        return true;
    }

    public void deactivate() {

    }

    public void reset() {

    }
}
