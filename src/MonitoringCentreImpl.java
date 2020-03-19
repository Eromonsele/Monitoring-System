import MonitoringSystem.*;

import java.util.ArrayList;
import java.util.List;

class MonitoringCentreImpl extends MonitoringCentrePOA {
    private List<LocalServer> list_of_localServer;
    private List<String> list_of_clients;

    MonitoringCentreImpl(){
        list_of_localServer = new ArrayList<LocalServer>();
        list_of_clients = new ArrayList<String>();
    }

    @Override
    public void raise_alarm(NoxReading alarm_reading) {
        //raise alarm
        //message the clients
    }

    @Override
    public void register_agency(String who, String contact_details, String area_of_interest) {

    }

    @Override
    public void register_local_server(String server_name) {

    }

}
