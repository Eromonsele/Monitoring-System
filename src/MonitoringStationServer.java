import MonitoringSystem.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;


public class MonitoringStationServer {
	private JPanel WindowsPanel;
	private JButton activateButton;
	private JPanel ButtonPanel;
	private JButton deactivateButton;
	private JButton resetButton;
	private JPanel infoPanel;
	private JTextField stationNameTextField;
	private JTextField locationTextField;
	private JTextArea infoTextArea;
	private JScrollPane infoScrollPane;
	public Timer timer;
	MonitoringStationImpl monitoringStation;

	public MonitoringStationServer(String[] args){
		try{
			// Initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// Create the Count servant object
			monitoringStation = new MonitoringStationImpl();
			monitoringStation.setOrb(orb);

			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(monitoringStation);
			MonitoringSystem.MonitoringStation mref = MonitoringStationHelper.narrow(ref);
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
			NamingContextExt nameService = NamingContextExtHelper.narrow(nameServiceObj);
			if (nameService == null) {
				System.out.println("nameService = null");
				return;
			}

			// bind the Count object in the Naming service
			String name = "monitoringStation";
			NameComponent[] countName = nameService.to_name(name);
			nameService.rebind(countName, mref);

			//Initialize  buttons
			activateButton.setEnabled(true);
			deactivateButton.setEnabled(false);
			resetButton.setEnabled(false);

			activateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (stationNameTextField.getText().isEmpty()){
						//display error
						JOptionPane.showMessageDialog(WindowsPanel,"Station Name is not filled");
						return;
					}

					if (locationTextField.getText().isEmpty()){
						//display error
						JOptionPane.showMessageDialog(WindowsPanel,"Monitoring Station locaiton is not filled");
						return;
					}

					monitoringStation.setStation_name(stationNameTextField.getText());
					monitoringStation.setLocation(locationTextField.getText());
					monitoringStation.setIor(stringified_ior);

					if (monitoringStation.activate()){
						timer = new Timer(true);
						infoTextArea.append(monitoringStation.station_name() + " has been connected to the local server\n");
						/**
						 * If activation of the monitoring station is successful,
						 * this disables the activate button and
						 * enables the deactivate and reset button
						 */
						activateButton.setEnabled(false);
						deactivateButton.setEnabled(true);
						resetButton.setEnabled(true);
						Random rand = new Random();
						timer.schedule(new TimerTask() {
							@Override
							public void run() {
								if (monitoringStation.is_active()){
									NoxReading reading = monitoringStation.get_reading();
									infoTextArea.append(reading.datetime + ": from "+ reading.station_name + ": Reading: "+reading.reading_value+"\n");
								}
							}
						},0,5000);
					}else{
						//display error message
						JOptionPane.showMessageDialog(WindowsPanel,"Activation wasn't successful");
					}
					return;
				}
			});

			deactivateButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					monitoringStation.deactivate();
					timer.cancel();
					/**
					 * If deactivation is successful this enables the activate button
					 * and disables the deactivate and reset button
					 */
					activateButton.setEnabled(true);
					deactivateButton.setEnabled(false);
					resetButton.setEnabled(false);
				}
			});

			resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					monitoringStation.reset();
				}
			});

		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}

	public static void main(String[] args) {
		final String[] arguments = args;
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("MonitoringStationServer");
				frame.setContentPane(new MonitoringStationServer(arguments).WindowsPanel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Monitoring Station");
				frame.pack();
				frame.setVisible(true);
			}
		});

	}
}
