//Tony Jiang
//Computer Science 20
//Henry Wise Wood High School
//2020-2021 Semester 1

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class UserInterface {

	private JFrame frmTc;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	TriangleClassifier tc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frmTc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTc = new JFrame();
		frmTc.setTitle("TC");
		frmTc.setBounds(100, 100, 165, 220);
		frmTc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTc.getContentPane().setLayout(null);
		frmTc.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Angle A:");
		lblNewLabel.setBounds(10, 10, 54, 15);
		frmTc.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Angle B:");
		lblNewLabel_1.setBounds(10, 35, 54, 15);
		frmTc.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Angle C:");
		lblNewLabel_2.setBounds(10, 60, 54, 15);
		frmTc.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Classify");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_btnNewButton_mouseClicked(arg0);

				int angleA, angleB, angleC;

				angleA = Integer.parseInt(textField.getText());
				angleB = Integer.parseInt(textField_1.getText());
				angleC = Integer.parseInt(textField_2.getText());

				if (angleA + angleB + angleC == 180) { //if the three sides add up to 180
             
					if (angleA == angleB && angleB == angleC) { //if all sides equal 
		
						textField_3.setText("equilateral");
				
					} else if (angleA == angleB || angleB == angleC || angleC == angleA) {  //if 2 of the sides equal
						
						if (angleA == angleB){ //if angleC is the biggest angle
							
							if (angleC > angleB + angleA) {
								textField_3.setText("obtuse isosceles");
							} else if (angleC < angleB +angleA) {
								textField_3.setText("acute isosceles");
							} else {
								textField_3.setText("right isosceles");
							}
		
						} else if (angleA == angleC) {  //if angleB is the biggest angle
		
							if (angleB > angleC + angleA) {
								textField_3.setText("obtuse isosceles");
							} else if (angleB < angleC +angleA) {
								textField_3.setText("acute isosceles");
							} else {
								textField_3.setText("right isosceles");
							}
		
						} else { //if angleA is the biggest angle
							
							if (angleA > angleC + angleB) {
								textField_3.setText("obtuse isosceles");
							} else if (angleA < angleC +angleB) {
								textField_3.setText("acute isosceles");
							} else {
								textField_3.setText("right isosceles");
							}
						}
		
					
					} else {    //if none of the size equal
						
						final int max = Math.max(Math.max(angleA, angleB),angleC); //finds the largest angle of the three
						final int min = Math.min(Math.min(angleA, angleB),angleC);
						final int mid = 180 - max- min;
		
						if (max > min + mid && max != 0 && min !=0 && mid != 0) {
		
							textField_3.setText("obtuse scalene");
						} else if (max == mid + min) {
		
							textField_3.setText("right scalene");
		
						} else if(max < min + mid) {
		
							textField_3.setText("acute scalene");
		
						} else {
							textField_3.setText("INVALID");
						}
		
		
					}
				
				} else { //if the three angles don't add up to 180
					textField_3.setText("INVALID");
				}
			}
			
		});
		btnNewButton.setBounds(10, 97, 129, 23);
		frmTc.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(59, 7, 80, 21);
		frmTc.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(59, 32, 80, 21);
		frmTc.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(59, 60, 80, 21);
		frmTc.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBackground(UIManager.getColor("Button.background"));
		textField_3.setBounds(10, 142, 129, 30);
		frmTc.getContentPane().add(textField_3);
		textField_3.setColumns(10);
	}

	protected static void do_btnNewButton_mouseClicked(MouseEvent arg0) {
	}
}