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
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBounds(474, 113, 310, 448);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		btnSignUp = new JButton("회 원 가 입");
		btnSignUp.setFont(new Font("새굴림", Font.BOLD, 20));
		btnSignUp.setBounds(42, 61, 197, 68);
		pn1.add(btnSignUp);
		
		btnLogin = new JButton("로 그 인");
		btnLogin.setFont(new Font("새굴림", Font.BOLD, 20));
		btnLogin.setBounds(42, 190, 197, 68);
		pn1.add(btnLogin);
		
		btnExit = new JButton("종 료");
		btnExit.setFont(new Font("새굴림", Font.BOLD, 20));
		btnExit.setBounds(42, 319, 197, 68);
		pn1.add(btnExit);
		
		JPanel pn2 = new JPanel();
		pn2.setBounds(0, 113, 476, 448);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JLabel lblImg = new JLabel("");
		ImageIcon imgMain = new ImageIcon(getClass().getResource("./images/main.jpg"));
		imgMain =	imageSetSize(imgMain, 600, 400); 
		lblImg.setIcon(imgMain);
		lblImg.setBounds(24, 10, 452, 400);
		pn2.add(lblImg);
		
		JPanel pn3 = new JPanel();
		pn3.setBounds(0, 0, 784, 113);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JLabel lblTitle = new JLabel("밥먹어");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 41));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 24, 784, 79);
		pn3.add(lblTitle);
		
		
		
		/*------------------------------------------------------*/

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
				lblId.setFont(new Font("굴림", Font.PLAIN, 19));
				lblId.setBounds(82, 108, 134, 58);
				pn2.add(lblId);
				
				textId = new JTextField();
				textId.setText("admin");
				textId.setFont(new Font("굴림", Font.PLAIN, 14));
				textId.setColumns(10);
				textId.setBounds(228, 108, 149, 47);
				pn2.add(textId);
				textId.requestFocus();
				
				JLabel lblPwd = new JLabel("비밀번호");
				lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
				lblPwd.setFont(new Font("굴림", Font.PLAIN, 19));
				lblPwd.setBounds(82, 188, 134, 39);
				pn2.add(lblPwd);
				
				textPwd = new JPasswordField();
				textPwd.setBounds(228, 186, 149, 47);
				pn2.add(textPwd);
				
				JButton btnLoginOk = new JButton("확 인");
				btnLoginOk.setFont(new Font("굴림", Font.PLAIN, 19));
				btnLoginOk.setBounds(106, 269, 271, 47);
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

		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
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
