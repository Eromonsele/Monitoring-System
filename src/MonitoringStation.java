import MonitoringSystem.NoxReading;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MonitoringStation {
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

	public MonitoringStation(String[] args){
		try{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			MonitoringStationImpl monitoringCentre = new MonitoringStationImpl(orb);

			// Get the 'stringified IOR'
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(monitoringCentre);
			String stringified_ior = orb.object_to_string(ref);

			// Save IOR to file
			BufferedWriter out = new BufferedWriter(new FileWriter("monitoringStation.ref"));
			out.write(stringified_ior);
			out.close();

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

					if (monitoringCentre.activate(stationNameTextField.getText(),locationTextField.getText(),stringified_ior)){
						timer = new Timer(true);
						infoTextArea.append(monitoringCentre.station_name() + " has been connected to the local server\n");
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
								if (monitoringCentre.is_active()){
									NoxReading reading = monitoringCentre.get_reading();
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
					monitoringCentre.deactivate();
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
					monitoringCentre.reset();
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
				JFrame frame = new JFrame("MonitoringStation");
				frame.setContentPane(new MonitoringStation(arguments).WindowsPanel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Monitoring Station");
				frame.pack();
				frame.setVisible(true);
			}
		});

	}
}
