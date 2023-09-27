package meal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MealListDetail extends JFrame {

	private JPanel contentPane;

	public MealListDetail() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("식단 리스트");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 281);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPhoto = new JLabel("사진");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setBounds(185, 28, 377, 220);
		panel.add(lblPhoto);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 280, 784, 281);
		contentPane.add(panel_1);
	}
}
