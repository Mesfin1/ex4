import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class server {

	private JFrame frame;
	private static JTextArea txtSlog;
	private static JButton btnStart;

 static int port=1234;
 static String ip="127.0.0.1";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server window = new server();
					window.frame.setVisible(true);
					StartClick();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});



	}

	/**
	 * Create the application.
	 */
	public server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 491, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		 btnStart = new JButton("Start");

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					ServerSocket sSocket=new ServerSocket(port);
					String s="socket is open";
					txtSlog.setText(s);
					while(true){
						Socket s1=sSocket.accept();
						PrintStream data= new PrintStream(s1.getOutputStream());
						txtSlog.setText(data.toString());
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnStart.setBounds(10, 28, 89, 31);
		frame.getContentPane().add(btnStart);

		JPanel panel = new JPanel();
		panel.setBounds(0, 63, 465, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		txtSlog = new JTextArea();
		txtSlog.setBounds(10, 11, 450, 340);
		panel.add(txtSlog);


	}
	public static void StartClick() {
		btnStart =new JButton();
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(btnStart.getText().equals("Start")) {
					txtSlog.setText("");
					btnStart.setText("Close");
					//startListen();
				} else {
					btnStart.setText("Start");
					txtSlog.setText("");
					//stopListen();
				}
			}
		});
	}
	
}
