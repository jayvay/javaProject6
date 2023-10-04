package meal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	private JPanel contentPane;
	DAO dao = new DAO();
	CompanionVO cVO = null;

	
	public MainMenu() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bobmukja_메뉴");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 210, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
//		ImageIcon imgMain = new ImageIcon(getClass().getResource("./images/menu.jpg"));
//		imgMain =	imageSetSize(imgMain, 500, 300); 
		
		JPanel pn1 = new JPanel();
		pn1.setBackground(new Color(249, 228, 176));
		pn1.setBounds(132, 251, 312, 233);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JButton btnToday = new JButton("오 늘 의 식 단");
		btnToday.setBackground(new Color(191, 115, 68));
		btnToday.setForeground(new Color(255, 255, 255));
		btnToday.setFont(new Font("굴림", Font.BOLD, 13));
		btnToday.setBounds(12, 97, 139, 123);
		pn1.add(btnToday);
		
		JButton btnCompUpdate = new JButton("회 원 정 보 수 정");
		btnCompUpdate.setBackground(new Color(74, 125, 82));
		btnCompUpdate.setForeground(new Color(255, 255, 255));
		btnCompUpdate.setFont(new Font("굴림", Font.BOLD, 13));
		btnCompUpdate.setBounds(12, 10, 139, 77);
		pn1.add(btnCompUpdate);
		
		JButton btnAllDay = new JButton("나의 식단 일지");
		btnAllDay.setBackground(new Color(64, 106, 82));
		btnAllDay.setFont(new Font("굴림", Font.BOLD, 13));
		btnAllDay.setForeground(new Color(255, 255, 255));
		btnAllDay.setBounds(163, 97, 137, 123);
		pn1.add(btnAllDay);
		
		JButton btnExit = new JButton("로 그 아 웃");
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("굴림", Font.BOLD, 13));
		btnExit.setBackground(new Color(74, 125, 82));
		btnExit.setBounds(163, 10, 139, 77);
		pn1.add(btnExit);
		
		ImageIcon imgBack = new ImageIcon(getClass().getResource("./images/cake.png"));
		imgBack =	imageSetSize(imgBack, 600, 600);
		
		JLabel label = new JLabel("New label");
		label.setBounds(40, 253, 493, -207);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Your diet is a bank account.");
		lblNewLabel.setForeground(new Color(74, 125, 82));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 81, 584, 69);
		contentPane.add(lblNewLabel);
		
		JLabel lblBethennyFrankel = new JLabel(" - Bethenny Frankel -");
		lblBethennyFrankel.setForeground(new Color(74, 125, 82));
		lblBethennyFrankel.setFont(new Font("Arial Black", Font.ITALIC, 15));
		lblBethennyFrankel.setHorizontalAlignment(SwingConstants.CENTER);
		lblBethennyFrankel.setBounds(0, 159, 584, 69);
		contentPane.add(lblBethennyFrankel);
		
		JLabel lblGoodFoodChoicess = new JLabel("Good food choices are good investments. \r\n");
		lblGoodFoodChoicess.setForeground(new Color(74, 125, 82));
		lblGoodFoodChoicess.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoodFoodChoicess.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 16));
		lblGoodFoodChoicess.setBounds(0, 112, 584, 69);
		contentPane.add(lblGoodFoodChoicess);
		
		JLabel lblBackImg = new JLabel("New label");
		lblBackImg.setBounds(0, 0, 584, 561);
		contentPane.add(lblBackImg);
		lblBackImg.setIcon(imgBack);
		
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
				new MealInput(null);
			}
		});

		btnCompUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ans = JOptionPane.showInputDialog("ID를 입력하세요");
				cVO =  dao.getCompSearch(ans);
				if(cVO.getId() != null) {
					dispose();
					new CompanionInfo(cVO);
				} else {
					JOptionPane.showMessageDialog(null, "없는 회원입니다.");
				}
			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Run();
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
