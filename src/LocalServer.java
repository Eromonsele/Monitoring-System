import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A Local server
 * @Author Eromosele Okhilua
 */
public class LocalServer {
	private JPanel WindowsPanel;
	private JTextArea infoTextArea;
	private JButton connectToMCButton;
	private String serverName,location;
	private LocalServerImpl localServer;
	private NameComponent[] names;
	private NamingContextExt nameService;

	// Constructor
	public LocalServer(String[] args){
		serverName = JOptionPane.showInputDialog(WindowsPanel,"Please enter the regional center name:",null);
		if (serverName == null){
			//display error
			JOptionPane.showMessageDialog(WindowsPanel,"Local Server name not set ");
			System.exit(0);
		}

		location = JOptionPane.showInputDialog(WindowsPanel,"Please enter the regional center location:",null);
		if (location == null){
			//display error
			JOptionPane.showMessageDialog(WindowsPanel,"Local Server location not set ");
			System.exit(0);
		}
		try{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			localServer = new LocalServerImpl(serverName,location,orb,this);
//
			// Get the 'stringified IOR'
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(localServer);

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
			// check if the server name is unique
			if (validateConnection(nameService)){
				// bind the Regional Center object in the Naming service
				names = new NameComponent[1];
				names[0] = new NameComponent(serverName, "Regional Center");
				nameService.rebind(names, ref);

				addMessage("Regional Centre "+serverName+" Started");
			}else{
				JOptionPane.showMessageDialog(WindowsPanel,"This regional centre " +serverName+ " already exist");
				System.exit(0);
			}

		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		// Connect to monitoring Station
		connectToMCButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mcName = JOptionPane.showInputDialog(WindowsPanel,"Please enter the Monitoring center name:",null);
				if (mcName == null){
					//display error
					JOptionPane.showMessageDialog(WindowsPanel,"Monitoring Center name isn't set");
					System.exit(0);
				}

				if (localServer.connect_to_monitoring_center(mcName))
				{
					// if connection is successful
					connectToMCButton.setEnabled(false);
					connectToMCButton.setText("Connected to Monitoring Center:");
				}

			}
		});


	}

	/**
	 * Add messages to the notice board
	 * @param message a string containing the message to be posted on the notice area
	 */
	void addMessage(String message){
		infoTextArea.append(message + "\n");
	}

	/**
	 * Validate Connection: Checks the station already exist
	 * @param nc NamingContextExt
	 * @return a boolean indicating a if a connection is valid; or otherwise
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
			if (bindName.equals(serverName) && bindKind.equals("Regional Center")){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		final String[] arguments = args;
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("LocalServer");
				frame.setResizable(false);

				Dimension size = new Dimension();
				size.setSize(600, 500);

				frame.setPreferredSize(size);
				LocalServer server = new LocalServer(arguments);
				frame.setContentPane(server.WindowsPanel);

				// unbind the name after disconnection
				frame.addWindowListener(new java.awt.event.WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent e) {
						try {
							server.nameService.unbind(server.names);
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
		});
	}
}
