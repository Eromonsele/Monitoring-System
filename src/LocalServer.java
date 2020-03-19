import MonitoringSystem.RegionalCentre;
import MonitoringSystem.RegionalCentreHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class LocalServer {
	private JPanel WindowsPanel;
	private JTextArea infoTextArea;
	private JList list1;
	private JButton getReadingsButton;
	private JButton activateButton;
	private JButton deactivateButton;
	private JButton resetButton;

	public LocalServer(String[] args){
		try{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			LocalServerImpl localServer = new LocalServerImpl(this,orb);
//
			// Get the 'stringified IOR'
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(localServer);
			MonitoringSystem.RegionalCentre mref = RegionalCentreHelper.narrow(ref);

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
			String name = "localServer";
			NameComponent[] countName = nameService.to_name(name);
			nameService.rebind(countName, mref);

			getReadingsButton.setEnabled(false);
			activateButton.setEnabled(false);
			deactivateButton.setEnabled(false);
			resetButton.setEnabled(false);

		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}

	void addMessage(String message){
		infoTextArea.append(message + "\n");
	}

	public static void main(String[] args) {
		final String[] arguments = args;
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("LocalServer");
				frame.setResizable(false);

				Dimension size = new Dimension();
				size.setSize(700, 500);

				frame.setPreferredSize(size);

				frame.setContentPane(new LocalServer(arguments).WindowsPanel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
