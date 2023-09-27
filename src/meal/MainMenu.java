package meal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	public MainMenu() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 210, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imgMain = new ImageIcon(getClass().getResource("./images/menu.jpg"));
		imgMain =	imageSetSize(imgMain, 500, 300); 
		
		JPanel pn1 = new JPanel();
		pn1.setBackground(new Color(206, 224, 251));
		pn1.setBounds(62, 370, 449, 134);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JButton btnToday = new JButton("오 늘 의 식 단");
		btnToday.setBackground(new Color(87, 87, 255));
		btnToday.setForeground(new Color(255, 255, 255));
		btnToday.setFont(new Font("굴림", Font.PLAIN, 12));
		btnToday.setBounds(12, 10, 289, 51);
		pn1.add(btnToday);
		
		JButton btnTodayUpdate = new JButton("회 원 정 보 수 정");
		btnTodayUpdate.setForeground(new Color(87, 87, 255));
		btnTodayUpdate.setFont(new Font("굴림", Font.PLAIN, 12));
		btnTodayUpdate.setBounds(12, 71, 289, 51);
		pn1.add(btnTodayUpdate);
		
		JButton btnAllDay = new JButton("나의 식단 일지");
		btnAllDay.setForeground(new Color(87, 87, 255));
		btnAllDay.setBounds(313, 11, 124, 111);
		pn1.add(btnAllDay);
		
		JLabel lblImg = new JLabel("New label");
		lblImg.setBounds(62, 25, 449, 366);
		contentPane.add(lblImg);
		lblImg.setIcon(imgMain);
		
		/*----------------------------------------------------------*/

		btnAllDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MealList();
			}
		});
		
		btnToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MealInput();
			}
		});

		btnTodayUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	ImageIcon imageSetSize (ImageIcon icon, int i, int j) {	//이미지 크기 조절
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i , j, Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
}
