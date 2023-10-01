package meal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Run extends JFrame {

	private JPanel contentPane;
	private JButton btnSignUp, btnLogin, btnExit;
	private JTextField textId;
	private JPasswordField textPwd;
	
	DAO dao = new DAO();
	CompanionVO cVO = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Run() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 650);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imgMain = new ImageIcon(getClass().getResource("./images/main.jpg"));
		imgMain =	imageSetSize(imgMain, 370, 300);
		
		JPanel pn1 = new JPanel();
		pn1.setBackground(new Color(204, 202, 202));
		pn1.setBounds(0, 0, 784, 661);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JPanel pn2 = new JPanel();
		pn2.setBounds(45, 171, 394, 337);
		pn1.add(pn2);
		pn2.setBackground(new Color(249, 228, 176));
		pn2.setLayout(null);
		
		JLabel lblImg = new JLabel("");
		lblImg.setBounds(12, 10, 373, 284);
		pn2.add(lblImg);
		lblImg.setIcon(imgMain);
		
		btnLogin = new JButton("로 그 인");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(128, 0, 64));
		btnLogin.setBounds(481, 304, 141, 68);
		pn1.add(btnLogin);
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 17));
		
		btnSignUp = new JButton("회 원 가 입");
		btnSignUp.setBounds(481, 205, 141, 68);
		pn1.add(btnSignUp);
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBackground(new Color(128, 0, 64));
		btnSignUp.setFont(new Font("굴림", Font.BOLD, 17));
		
		btnExit = new JButton("종 료");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(128, 0, 64));
		btnExit.setBounds(481, 406, 141, 68);
		pn1.add(btnExit);
		btnExit.setFont(new Font("Dialog", Font.BOLD, 17));
		
		JLabel lblTitle = new JLabel("BOBMUKJA");
		lblTitle.setBounds(0, 61, 685, 79);
		pn1.add(lblTitle);
		lblTitle.setBackground(new Color(190, 202, 179));
		lblTitle.setForeground(new Color(128, 0, 64));
		lblTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 37));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 685, 612);
		ImageIcon imgBack = new ImageIcon(getClass().getResource("./images/mainBack.png"));
		imgBack =	imageSetSize(imgBack, 700, 700);
		pn1.add(lblNewLabel);
		lblNewLabel.setIcon(imgBack);
		
	
				
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUp();
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblImg.setVisible(false);	//사진 안 보이게
				
				JLabel lblId = new JLabel("아이디");
				lblId.setHorizontalAlignment(SwingConstants.CENTER);
				lblId.setFont(new Font("굴림", Font.BOLD, 15));
				lblId.setBounds(49, 70, 134, 58);
				pn2.add(lblId);
				
				textId = new JTextField();
				textId.setText("admin");
				textId.setFont(new Font("굴림", Font.PLAIN, 14));
				textId.setColumns(10);
				textId.setBounds(175, 70, 124, 47);
				pn2.add(textId);
				textId.requestFocus();
				
				JLabel lblPwd = new JLabel("비밀번호");
				lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
				lblPwd.setFont(new Font("굴림", Font.BOLD, 15));
				lblPwd.setBounds(49, 150, 134, 39);
				pn2.add(lblPwd);
				
				textPwd = new JPasswordField();
				textPwd.setBounds(175, 148, 124, 47);
				pn2.add(textPwd);
				
				JButton btnLoginOk = new JButton("확 인");
				btnLoginOk.setBackground(new Color(249, 228, 176));
				btnLoginOk.setFont(new Font("굴림", Font.BOLD, 15));
				btnLoginOk.setBounds(89, 231, 207, 39);
				pn2.add(btnLoginOk);
				
				
				
				/*---------------로그인------------------*/
				btnLoginOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getLoginProcess();
					}
				});
				
				btnLoginOk.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						getLoginProcess();
					}
				});
			}
		});
		
		
		
		/*------------------------------------------------------*/
		
	}

	ImageIcon imageSetSize (ImageIcon icon, int i, int j) {	//이미지 크기 조절
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i , j, Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
	
	public void getLoginProcess() {
		String id = textId.getText();
		cVO = dao.getCompSearch(id);
		if(cVO.getId() != null) {
			if(cVO.getPwd().equals(textPwd.getText())) {
				JOptionPane.showMessageDialog(null, cVO.getName() + "님 환영합니다~!");
				System.out.println(cVO.getName());
				dispose();
				new MainMenu(cVO);
			}
			else if(!cVO.getPwd().equals(textPwd.getText())) {
				JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다. 비밀번호를 확인하세요.");
				textPwd.requestFocus();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다. 아이디를 확인하세요.");
			textId.requestFocus();
		}
	}
}
