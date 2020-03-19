import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitoringStationDialog extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;

	public MonitoringStationDialog() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});
	}

	private void onOK() {
		// add your code here
		dispose();
	}

	public static void main(String[] args) {
		MonitoringStationDialog dialog = new MonitoringStationDialog();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}
