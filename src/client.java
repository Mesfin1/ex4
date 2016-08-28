import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class client {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtadress;
	private JTextField txtUser;
	private JTextField txtMsg;
    private static JTextArea txtClog;
    
	 static Socket c_s;
	static DataInputStream c_inData;
	static DataOutputStream c_outData;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client window = new client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			c_s=new Socket("127.0.0.1",1234); // ip and port of the client
			c_inData=new DataInputStream(c_s.getInputStream());
			c_outData=new DataOutputStream(c_s.getOutputStream());
			
			String msg="";
			while (!msg.equals("exit")) {
				 msg=c_inData.readUTF();
				 txtClog.setText(txtClog.getText().trim()+"\n"+msg);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the application.
	 */
	public client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 719, 431);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCon = new JButton("Connect");
		btnCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cmsg="";
				cmsg=txtUser.getText().trim();
				try {
					c_outData.writeUTF(cmsg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				
			}
		});
		btnCon.setBounds(20, 28, 79, 23);
		frame.getContentPane().add(btnCon);
		
		txtName = new JTextField();
		txtName.setBounds(146, 28, 86, 22);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(109, 30, 34, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAdress = new JLabel("Adresss");
		lblAdress.setBounds(243, 28, 52, 19);
		frame.getContentPane().add(lblAdress);
		
		txtadress = new JTextField();
		txtadress.setColumns(10);
		txtadress.setBounds(293, 28, 86, 22);
		frame.getContentPane().add(txtadress);
		
		JButton btnShow = new JButton("Show online");
		btnShow.setBounds(390, 28, 112, 23);
		frame.getContentPane().add(btnShow);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(510, 28, 66, 23);
		frame.getContentPane().add(btnClear);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(579, 28, 89, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		  txtClog = new JTextArea();
		txtClog.setBounds(20, 62, 658, 268);
		frame.getContentPane().add(txtClog);
		
		txtUser = new JTextField();
		txtUser.setBounds(30, 338, 135, 26);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtMsg = new JTextField();
		txtMsg.setColumns(10);
		txtMsg.setBounds(185, 338, 417, 26);
		frame.getContentPane().add(txtMsg);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(612, 341, 66, 23);
		frame.getContentPane().add(btnSend);
	}
}
