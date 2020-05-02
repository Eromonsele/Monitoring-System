import MonitoringSystem.MonitoringCentre;
import MonitoringSystem.MonitoringCentreHelper;
import MonitoringSystem.MonitoringStationHelper;
import MonitoringSystem.RegionalCentre;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MonitoringCenterServer {
	private JPanel WindowsPanel;
	private JButton registerClientsButton;
	private JButton requestReadingsButton;
	private JTextArea informationAreaTextArea;
	private JButton registerLocalServersButton;
	private JButton pullLocalServerLogButton;
	private JButton showRegisteredClientsButton;
	private JButton showRegisteredLocalServersButton;
	private JButton requestAlarmLogButton;
	private String name;
	private MonitoringCentreImpl monitoringCentre;
	private NameComponent[] names;
	private NamingContextExt nameService;

	public MonitoringCenterServer(String[] args){

		name = JOptionPane.showInputDialog(WindowsPanel,"Please enter name",null);
		if (name == null || name.isEmpty()){
			//display error
			JOptionPane.showMessageDialog(WindowsPanel,"Monitoring Center Name is not filled");
			System.exit(0);
		}

		//Try to connect to the naming service
		try {
			// Initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			monitoringCentre = new MonitoringCentreImpl(orb,this,name);

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(monitoringCentre);
			MonitoringSystem.MonitoringCentre mref = MonitoringCentreHelper.narrow(ref);
			String stringified_ior = orb.object_to_string(ref);

			// Get a reference to the Naming service
			org.omg.CORBA.Object nameServiceObj =
					orb.resolve_initial_references ("NameService");
			if (nameServiceObj == null) {
				System.out.println("nameServiceObj = null");
				return;
			}

			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			nameService = NamingContextExtHelper.narrow(nameServiceObj);
			if (nameService == null) {
				System.out.println("nameService = null");
				return;
			}

			if (validateConnection(nameService)) {
				// bind the Monitoring Center object in the Naming service
				names = new NameComponent[1];
				names[0] = new NameComponent(name, "HQ");
				nameService.rebind(names, mref);
				addMessage("Monitoring Centre "+name+" Started");
			}else{
				JOptionPane.showMessageDialog(WindowsPanel,"This Monitoring Centre " +name+ " already exist");
				System.exit(0);
			}


		}catch (Exception e){
			JOptionPane.showMessageDialog(WindowsPanel,"Can't connect to the naming service");
			return;
		}
		// Register local server action listener
		registerLocalServersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String localServer = JOptionPane.showInputDialog(WindowsPanel,"Please enter name",null);
				if (localServer == null || localServer.isEmpty()){
					//display error
					JOptionPane.showMessageDialog(WindowsPanel,"Monitoring Center Name is not filled");
					return;
				}
				addMessage("Trying to connect to " + localServer + "........");
				monitoringCentre.register_local_server(localServer);

			}
		});
		// Register client action listener
		registerClientsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String who = JOptionPane.showInputDialog(WindowsPanel,"Please enter client name:",null,JOptionPane.QUESTION_MESSAGE);
				if (who == null || who.isEmpty()){
					//display error
					JOptionPane.showMessageDialog(WindowsPanel,"Monitoring Center Name is not filled");
					return;
				}

				String contact_details = JOptionPane.showInputDialog(WindowsPanel,"Please Enter contact details",null,JOptionPane.QUESTION_MESSAGE);
				if (contact_details == null || contact_details.isEmpty()){
					contact_details = "";
				}

				String area_of_interest = JOptionPane.showInputDialog(WindowsPanel,"Please input Area of interest name",null,JOptionPane.QUESTION_MESSAGE);
				if (area_of_interest == null || area_of_interest.isEmpty()){
					//display error
					JOptionPane.showMessageDialog(WindowsPanel,"Monitoring Center Name is not filled");
					return;
				}

				monitoringCentre.register_agency(who,contact_details,area_of_interest);
			}
		});
		//Request nox readings from the connected local servers
		requestReadingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monitoringCentre.pull_local_server_readings();
			}
		});

		//Request event log from the connected local servers
		pullLocalServerLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monitoringCentre.pull_local_servers_log();
			}
		});

		// Show all registered clients
		showRegisteredClientsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append("Registered Clients:\n");
				for (int i = 0; i < monitoringCentre.getList_of_clients().size(); i++) {
					String[] agency = monitoringCentre.getList_of_clients().get(i).split(";");
					sb1.append((i+1)+": \nClient:"+ agency[0] + "\n");
				}

				JOptionPane.showMessageDialog(WindowsPanel, sb1);
			}
		});

		// Show all registered local servers
		showRegisteredLocalServersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append("Local Servers Connected:\n");
				for (int i = 0; i < monitoringCentre.getList_of_localServer().size(); i++) {
					sb1.append((i+1)+": "+monitoringCentre.getList_of_localServer().get(i) + "\n");
				}

				JOptionPane.showMessageDialog(WindowsPanel, sb1);
			}
		});
		requestAlarmLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				monitoringCentre.pull_local_servers_alarm_log();
			}
		});
	}

	/**
	 * Validate Connection: Checks if monitoring centre already exist
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
			if (bindName.equals(name) && bindKind.equals("HQ")){
				return false;
			}
		}
		return true;
	}

	void addMessage(String message){
		informationAreaTextArea.append(message + "\n");
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("MonitoringCenterServer");
		frame.setResizable(false);

		Dimension size = new Dimension();
		size.setSize(900, 500);

		frame.setPreferredSize(size);
		MonitoringCenterServer monitoringCenterServer = new MonitoringCenterServer(args);
		frame.setContentPane(monitoringCenterServer.WindowsPanel);
		// unbind the name after disconnection
		frame.addWindowListener(new java.awt.event.WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					monitoringCenterServer.nameService.unbind(monitoringCenterServer.names);
				} catch (Exception ex) {
					System.err.println("ERROR: " + ex);
					ex.printStackTrace(System.out);
				}
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
