package imsi;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import meal.CompanionVO;
import meal.DAO;
import meal.MainMenu;

public class Login extends JFrame {

	private JPanel contentPane, pn1, pn2;
	private JLabel lblId, lblPwd;
	private JTextField textId;
	private JButton btnLogin;
	
	DAO dao = new DAO();
	CompanionVO cVO = null;
	
	int res = 0;
	private JPasswordField textPwd;

	public Login() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 250);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pn1 = new JPanel();
		pn1.setBounds(0, 0, 384, 129);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		lblId = new JLabel("아이디");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("굴림", Font.PLAIN, 14));
		lblId.setBounds(52, 23, 134, 39);
		pn1.add(lblId);
		
		lblPwd = new JLabel("비밀번호");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("굴림", Font.PLAIN, 14));
		lblPwd.setBounds(52, 80, 134, 39);
		pn1.add(lblPwd);
		
		textId = new JTextField();
		textId.setFont(new Font("굴림", Font.PLAIN, 14));
		textId.setColumns(10);
		textId.setBounds(187, 26, 134, 34);
		pn1.add(textId);
		
		textPwd = new JPasswordField();
		textPwd.setBounds(187, 83, 134, 34);
		pn1.add(textPwd);
		
		pn2 = new JPanel();
		pn2.setBounds(0, 129, 384, 82);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("굴림", Font.PLAIN, 17));
		btnLogin.setBounds(80, 10, 244, 34);
		pn2.add(btnLogin);
		
		/*------------------------------------------------*/

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다. 아이디를 확인하세요.");
				}
			}
		});
		
	}
}
