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
		setTitle("Bobmukja");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
//		ImageIcon imgMain = new ImageIcon(getClass().getResource("./images/main.jpg"));
//		imgMain =	imageSetSize(imgMain, 370, 300);
		
		JPanel pn1 = new JPanel();
		pn1.setBackground(new Color(237, 227, 211));
		pn1.setBounds(0, 0, 584, 561);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(58, 98, 19));
		btnLogin.setBounds(203, 289, 179, 68);
		pn1.add(btnLogin);
		btnLogin.setFont(new Font("Verdana", Font.BOLD, 17));
		
		btnSignUp = new JButton("SIGNUP:)");
		btnSignUp.setBounds(203, 191, 179, 68);
		pn1.add(btnSignUp);
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBackground(new Color(58, 98, 19));
		btnSignUp.setFont(new Font("Verdana", Font.BOLD, 17));
		
		btnExit = new JButton("BYE");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(58, 98, 19));
		btnExit.setBounds(203, 393, 179, 68);
		pn1.add(btnExit);
		btnExit.setFont(new Font("Verdana", Font.BOLD, 17));
		
		JLabel lblTitle = new JLabel("BOBMUKJA");
		lblTitle.setBounds(0, 56, 587, 79);
		pn1.add(lblTitle);
		lblTitle.setBackground(new Color(190, 202, 179));
		lblTitle.setForeground(new Color(64, 106, 21));
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 33));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 587, 561);
		ImageIcon imgBack = new ImageIcon(getClass().getResource("./images/mainBack.png"));
		imgBack =	imageSetSize(imgBack, 600, 600);
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
				btnSignUp.setVisible(false);	//사진 안 보이게
				btnLogin.setVisible(false);	
				btnExit.setVisible(false);
				lblNewLabel.setVisible(false);
				

				JPanel pn2 = new JPanel();
				pn2.setBounds(117, 150, 350, 300);
				pn1.add(pn2);
				pn2.setBackground(new Color(237, 227, 211));
				pn2.setLayout(null);

				JLabel lblId = new JLabel("ID");
				lblId.setHorizontalAlignment(SwingConstants.CENTER);
				lblId.setFont(new Font("Verdana", Font.BOLD, 15));
				lblId.setBounds(22, 64, 134, 58);
				pn2.add(lblId);
				
				textId = new JTextField();
				textId.setText("admin");
				textId.setFont(new Font("Verdana", Font.BOLD, 13));
				textId.setColumns(10);
				textId.setBounds(159, 70, 124, 39);
				pn2.add(textId);
				textId.requestFocus();
				
				JLabel lblPwd = new JLabel("PASSWORD");
				lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
				lblPwd.setFont(new Font("Verdana", Font.BOLD, 15));
				lblPwd.setBounds(22, 144, 134, 39);
				pn2.add(lblPwd);
				
				textPwd = new JPasswordField();
				textPwd.setBounds(159, 142, 124, 39);
				pn2.add(textPwd);
				
				JButton btnLoginOk = new JButton("LOGIN");
				btnLoginOk.setBackground(new Color(249, 228, 176));
				btnLoginOk.setFont(new Font("Verdana", Font.BOLD, 15));
				btnLoginOk.setBounds(70, 222, 207, 39);
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
				new MainMenu();
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
