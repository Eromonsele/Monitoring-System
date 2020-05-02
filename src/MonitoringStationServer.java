import MonitoringSystem.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

/**
 * The Monitoring Station Server
 * @author Eromosele Okhilua
 */
public class MonitoringStationServer {
	private JPanel WindowsPanel;
	public JButton activateButton;
	private JPanel ButtonPanel;
	private JButton deactivateButton;
	private JButton resetButton;
	private JTextArea infoTextArea;
	private JScrollPane infoScrollPane;
	private JLabel infoLabel;
	public Timer timer;
	MonitoringStationImpl monitoringStation;
	String station_name;
	String location;
	String localServer;
	NameComponent[] names;
	NamingContextExt nameService;

	public MonitoringStationServer(String[] args){

		//input the station name
		station_name = JOptionPane.showInputDialog(WindowsPanel,"Please enter station_name",null);
		if (station_name == null){
			//display error
			JOptionPane.showMessageDialog(WindowsPanel,"Station Name is not filled");
			System.exit(0);
		}

		//input the station name
		location = JOptionPane.showInputDialog(WindowsPanel,"Please enter location",null);
		if (location == null){
			//display error
			JOptionPane.showMessageDialog(WindowsPanel,"Monitoring Station locaiton is not filled");
			System.exit(0);
		}

		//Connect to the naming service
		try{
			// Initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// Create the Count servant object
			monitoringStation = new MonitoringStationImpl(station_name,location,orb,this);

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(monitoringStation);

			// Get a reference to the Naming service
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			nameService = NamingContextExtHelper.narrow(orb.resolve_initial_references ("NameService"));
			if (nameService == null) {
				JOptionPane.showMessageDialog(WindowsPanel,"Naming service can not be found, this program will be exiting now");
				System.exit(0);
			}

			if (validateConnection(nameService)){
				names = new NameComponent[1];
				names[0] = new NameComponent(station_name, "Monitoring Station");
				nameService.rebind(names, ref);
				addMessage("Monitoring Station "+station_name+" Started");
			}else{
				JOptionPane.showMessageDialog(WindowsPanel,"This monitoring Station " +station_name+ " already exist");
				System.exit(0);
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}

		infoLabel.setText("Station Name: "+station_name+" | Location: "+location);

		/**
		 * Generate the nox reading , every 5 seconds
		 * This is for testing the push alarm system
		 */
		timer = new Timer(true);
		Random rand = new Random();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (monitoringStation.is_active()){
					NoxReading reading = monitoringStation.get_reading();
					infoTextArea.append(reading.date+ ": from "+ reading.station_name + ": Reading: "+reading.reading_value+"\n");
				}
			}
		},0,5000);

		//Initialize  buttons
		activateButton.setEnabled(true);

		activateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localServer = JOptionPane.showInputDialog(
						WindowsPanel,
						"Please enter a local server name",
						null);
				monitoringStation.activate(localServer);
			}
		});
	}

	/**
	 * Validate Connection: Checks the station already exist
	 */
	public boolean validateConnection(NamingContextExt nc){
		BindingListHolder bl = new BindingListHolder();
		BindingIteratorHolder blIt= new BindingIteratorHolder();
		nc.list(1000, bl, blIt);

		Binding bindings[] = bl.value;

		for (int i=0; i < bindings.length; i++) {

			int lastIx = bindings[i].binding_name.length-1;
			String bindKind =  bl.value[i].binding_name[lastIx].kind;
			String bindName =  bl.value[i].binding_name[lastIx].id;

			// check to see if this is a naming context
			if (bindName.equals(station_name) && bindKind.equals("Monitoring Station")){
				return false;
			}
		}
		return true;
	}

	/**
	 * add message to the notice board
	 * @param message
	 */
	public void addMessage(String message){
		infoTextArea.append(message+"\n");
	}

	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		final String[] arguments = args;
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("MonitoringStationServer");
				frame.setResizable(false);

				Dimension size = new Dimension();
				size.setSize(400, 500);

				frame.setPreferredSize(size);
				MonitoringStationServer monitoringStationServer = new MonitoringStationServer(arguments);
				frame.setContentPane(monitoringStationServer.WindowsPanel);

				// unbind the name after disconnection
				frame.addWindowListener(new java.awt.event.WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent e) {
						try {
							monitoringStationServer.nameService.unbind(monitoringStationServer.names);
						} catch (Exception ex) {
							System.err.println("ERROR: " + ex);
							ex.printStackTrace(System.out);
						}
					}
				});
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Monitoring Station");
				frame.pack();
				frame.setVisible(true);
			}
		});

	}
}
