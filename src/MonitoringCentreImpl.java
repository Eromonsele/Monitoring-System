import MonitoringSystem.*;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Monitoring Centre Impl
 * @author Eromosele Okhilua U1671506
 */
class MonitoringCentreImpl extends MonitoringCentrePOA {
    private String name;
    private ORB orb;
    private MonitoringCenterServer parent;
    private ArrayList<String> list_of_localServer;
    private ArrayList<String> list_of_clients;

    // Constructor
    public MonitoringCentreImpl(ORB orb,MonitoringCenterServer parent,String name)
    {
        this.orb = orb;
        this.parent = parent;
        this.name = name;
        list_of_localServer = new ArrayList<String>();
        list_of_clients = new ArrayList<String>();
    }

    /**
     * Returns the name of the monitoring centre
     * @return a string containing the name of the monitoring centre
     */
    public String name() {
        return name;
    }

    /**
     * Get the list of local servers
     * @return An arraylist object containing the list of local servers
     */
    public ArrayList<String> getList_of_localServer() {
        return list_of_localServer;
    }

    /**
     * Get the list of clients
     * @return An arraylist object containing the list of clients
     */
    public ArrayList<String> getList_of_clients() {
        return list_of_clients;
    }

    /**
     *  Raises an alarm on HQ and Notifies the registered clients of alarm
     * @param alarm_reading The NoxReadings object that contains the alarm reading value
     */
    public void raise_alarm(NoxReading alarm_reading)
    {
        parent.addMessage("Alarm raised from monitoring station "+ alarm_reading.station_name);
        //notify registered clients
        if (list_of_clients.size() > 0) {
            notify_agency(alarm_reading);
        }
    }

    /**
     * Registers a client from the input provided by the client
     * @param who a string containing the name of the client
     * @param contact_details a string containing the contact_details of the client
     * @param area_of_interest a string containing the area of interest for the client
     */
    public void register_agency(String who, String contact_details, String area_of_interest)
    {
        String client = who + ";"+contact_details+";"+area_of_interest;
        list_of_clients.add(client);
        parent.addMessage("Client ("+who+") has been added to this monitoring center");
    }

    /**
     * Registers a local server to this HQ
     * @param server_name a string containing the server name
     */
    public void register_local_server(String server_name)
    {
        //Check if this is a valid local
        try {
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt nameService = NamingContextExtHelper.narrow(orb.resolve_initial_references ("NameService"));
            if (nameService == null) {
                System.out.println("nameService = null");
            }

            RegionalCentre regionalCentre = RegionalCentreHelper.narrow(nameService.resolve_str(server_name+".Regional Center"));
            list_of_localServer.add(server_name);
            parent.addMessage("Local Server("+regionalCentre.name()+") is registered to this Monitoring center");

            regionalCentre.add_to_log("Connected to Monitoring Center:"+name());

        } catch (Exception e) {
            parent.addMessage("Invalid Regional Server("+server_name+") Name, Please try to connect again");
        }
    }

    /**
     * Poll nox readings from the monitors connected ot he connected local servers
     */
    public void pull_local_server_readings()
    {
        parent.addMessage("===>Requesting data from all connected regional centres");

        if (list_of_localServer.size() <= 0){
            parent.addMessage("===>Data Request was denied!!!");
            return;
        }
        try {
            NamingContextExt nameService = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            for (String server_name :  list_of_localServer) {
                RegionalCentre localServer = RegionalCentreHelper.narrow(nameService.resolve_str(server_name + ".Regional Center"));
                parent.addMessage("===>Data Request Sent from Regional Center("+localServer.name()+")");
                NoxReading[] result = localServer.take_readings();

                for (int i = 0; i < result.length; i++) {
                    Date date = new Date(result[i].date);
                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
                    parent.addMessage("===>On "+dateFormat.format(date)+": From =>" + result[i].station_name +"==> Value:"+ result[i].reading_value);
                }
            }
        }catch (Exception e) {
            parent.addMessage("===>Data Request was denied");
            return;
        }
        parent.addMessage("===>End");
    }

    /**
     * Poll the local servers connected for events log
     */
    public void pull_local_servers_log()
    {
        parent.addMessage("===>Requesting the log from all regional centers connected");
        try {
            NamingContextExt nameService = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

            for (String server_name :  list_of_localServer) {
                RegionalCentre localServer = RegionalCentreHelper.narrow(nameService.resolve_str(server_name + ".Regional Center"));
                parent.addMessage("===>Log Request Sent from Regional Center("+localServer.name()+")");
                String[] result = localServer.log_of_events();

                for (int i = 0; i < result.length; i++) {
                   parent.addMessage("===>"+result[i]);
                }
            }

        }catch (Exception e) {
            parent.addMessage("===>Log Request was denied, Please Check ");
            return;
        }
        parent.addMessage("===>End");
    }

    /**
     * Poll the local servers connected for the alarm log
     */
    public void pull_local_servers_alarm_log()
    {
        parent.addMessage("===>Requesting the alarm log from all regional centers connected");
        try {
            NamingContextExt nameService = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

            for (String server_name :  list_of_localServer) {
                RegionalCentre localServer = RegionalCentreHelper.narrow(nameService.resolve_str(server_name + ".Regional Center"));
                parent.addMessage("===>Log Request Sent from Regional Center("+localServer.name()+")");
                NoxReading[] result = localServer.log();

                for (int i = 0; i < result.length; i++) {
                    parent.addMessage("===>"+result[i]);
                }
            }

        }catch (Exception e) {
            parent.addMessage("===>Alarm Log Request was denied, Please Check ");
            return;
        }
        parent.addMessage("===>End");
    }

    /**
     * Notifies registered clients of current alarms
     * @param alarm_reading  The NoxReadings object that contains the alarm reading value
     */
    public void notify_agency(NoxReading alarm_reading)
    {
        //notify the clients
        for (int i = 0; i < list_of_clients.size(); i++) {

            String[] area_of_interest = list_of_clients.get(i).split(";");

            try {
                NamingContextExt nameService = NamingContextExtHelper.narrow(orb.resolve_initial_references ("NameService"));
                if (nameService == null) {
                    System.out.println("nameService = null");
                }

                MonitoringSystem.MonitoringStation monitoringStation =  MonitoringStationHelper.narrow(nameService.resolve_str(alarm_reading.station_name+".Monitoring Station"));
                if (area_of_interest[2].equals(monitoringStation.location())){
                    parent.addMessage("Client "+area_of_interest[0]+" has been notified");
                }
            } catch (Exception e) {
                parent.addMessage("Notification was not possible");
            }
        }
    }
}
