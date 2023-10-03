package meal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class SignUp extends JFrame {

	private JPanel contentPane, pn1, pn2;
	private JLabel lblAgeMan, lblAgeSe, lblTelEx;
	private JLabel lblId, lblPwd, lblName, lblAge, lblGender, lblEmail, lblTel;
	private JTextField textId, textPwd, textName, textAge, textEmail, textTel;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdMale, rdFemale;
	private JButton btnSignUp, btnExit;
	private JButton btnReset;
	
	CompanionVO cVO = null;
	DAO dao = new DAO();

	int res = 0;
	private JLabel lblWelcome;
	
	public SignUp() {
		setTitle("Bobmukja_회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pn1 = new JPanel();
		pn1.setBackground(new Color(237, 227, 211));
		pn1.setBounds(0, 0, 584, 551);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		lblId = new JLabel("아이디 ▶");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("굴림", Font.BOLD, 14));
		lblId.setBounds(38, 79, 144, 37);
		pn1.add(lblId);
		
		lblPwd = new JLabel("비밀번호 ▶");
		lblPwd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPwd.setFont(new Font("굴림", Font.BOLD, 14));
		lblPwd.setBounds(38, 143, 144, 37);
		pn1.add(lblPwd);
		
		lblName = new JLabel("이름 ▶");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("굴림", Font.BOLD, 14));
		lblName.setBounds(38, 206, 144, 37);
		pn1.add(lblName);
		
		lblAge = new JLabel("나이 ▶");
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setFont(new Font("굴림", Font.BOLD, 14));
		lblAge.setBounds(38, 276, 144, 37);
		pn1.add(lblAge);
		
		lblGender = new JLabel("성별 ▶");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("굴림", Font.BOLD, 14));
		lblGender.setBounds(38, 341, 144, 37);
		pn1.add(lblGender);
		
		lblEmail = new JLabel("이메일 ▶");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("굴림", Font.BOLD, 14));
		lblEmail.setBounds(38, 406, 144, 37);
		pn1.add(lblEmail);
		
		lblTel = new JLabel("전화번호 ▶");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setFont(new Font("굴림", Font.BOLD, 14));
		lblTel.setBounds(38, 472, 144, 37);
		pn1.add(lblTel);
		
		textId = new JTextField();
		textId.setBackground(new Color(251, 247, 242));
		textId.setFont(new Font("굴림", Font.PLAIN, 14));
		textId.setHorizontalAlignment(SwingConstants.LEFT);
		textId.setBounds(237, 79, 177, 42);
		pn1.add(textId);
		textId.setColumns(10);
		
		textPwd = new JTextField();
		textPwd.setBackground(new Color(251, 247, 242));
		textPwd.setHorizontalAlignment(SwingConstants.LEFT);
		textPwd.setFont(new Font("굴림", Font.PLAIN, 14));
		textPwd.setColumns(10);
		textPwd.setBounds(237, 143, 177, 42);
		pn1.add(textPwd);
		
		textName = new JTextField();
		textName.setBackground(new Color(251, 247, 242));
		textName.setHorizontalAlignment(SwingConstants.LEFT);
		textName.setFont(new Font("굴림", Font.PLAIN, 14));
		textName.setColumns(10);
		textName.setBounds(237, 204, 177, 42);
		pn1.add(textName);
		
		textEmail = new JTextField();
		textEmail.setBackground(new Color(251, 247, 242));
		textEmail.setHorizontalAlignment(SwingConstants.LEFT);
		textEmail.setFont(new Font("굴림", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBounds(237, 404, 177, 42);
		pn1.add(textEmail);
		
		textTel = new JTextField();
		textTel.setBackground(new Color(251, 247, 242));
		textTel.setHorizontalAlignment(SwingConstants.LEFT);
		textTel.setFont(new Font("굴림", Font.PLAIN, 14));
		textTel.setColumns(10);
		textTel.setBounds(237, 467, 177, 42);
		pn1.add(textTel);
		
		rdMale = new JRadioButton("남자");
		rdMale.setBackground(new Color(237, 227, 211));
		rdMale.setFont(new Font("굴림", Font.BOLD, 14));
		rdMale.setBounds(236, 348, 67, 23);
		buttonGroup.add(rdMale);
		pn1.add(rdMale);
		
		rdFemale = new JRadioButton("여자");
		rdFemale.setBackground(new Color(237, 227, 211));
		rdFemale.setSelected(true);
		rdFemale.setFont(new Font("굴림", Font.BOLD, 14));
		rdFemale.setBounds(327, 348, 67, 23);
		buttonGroup.add(rdFemale);
		pn1.add(rdFemale);
		
		lblAgeMan = new JLabel("만");
		lblAgeMan.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgeMan.setFont(new Font("굴림", Font.BOLD, 15));
		lblAgeMan.setBounds(224, 271, 41, 42);
		pn1.add(lblAgeMan);
		
		textAge = new JTextField();
		textAge.setBackground(new Color(251, 247, 242));
		textAge.setHorizontalAlignment(SwingConstants.LEFT);
		textAge.setFont(new Font("굴림", Font.PLAIN, 14));
		textAge.setColumns(10);
		textAge.setBounds(265, 271, 116, 42);
		pn1.add(textAge);
		
		lblAgeSe = new JLabel("세");
		lblAgeSe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgeSe.setFont(new Font("굴림", Font.BOLD, 15));
		lblAgeSe.setBounds(383, 273, 41, 42);
		pn1.add(lblAgeSe);
		
		lblTelEx = new JLabel("ex)010-1234-5678");
		lblTelEx.setFont(new Font("굴림", Font.PLAIN, 11));
		lblTelEx.setBounds(428, 476, 105, 31);
		pn1.add(lblTelEx);
		
		lblWelcome = new JLabel("WELCOME");
		lblWelcome.setForeground(new Color(128, 64, 64));
		lblWelcome.setFont(new Font("Verdana", Font.BOLD, 21));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(0, 20, 584, 37);
		pn1.add(lblWelcome);
		
		pn2 = new JPanel();
		pn2.setBackground(new Color(98, 134, 87));
		pn2.setBounds(0, 550, 584, 111);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		btnSignUp = new JButton("SIGNUP");
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBackground(new Color(98, 134, 87));
		btnSignUp.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnSignUp.setBounds(30, 30, 160, 50);
		pn2.add(btnSignUp);
		
		btnExit = new JButton("EXIT");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(new Color(98, 134, 87));
		btnExit.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnExit.setBounds(392, 30, 160, 50);
		pn2.add(btnExit);
		
		btnReset = new JButton("RESET");
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(new Color(98, 134, 87));
		btnReset.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnReset.setBounds(211, 30, 160, 50);
		pn2.add(btnReset);
		
		/*---------------------------------------------*/

		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				String pwd = textPwd.getText();
				String name = textName.getText();
				String age = textAge.getText();
				String gender;
				String email = textEmail.getText();
				String tel = textTel.getText();
				
				if(id.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
					textId.requestFocus();
				} else if(pwd.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요");
					textPwd.requestFocus();
				} else if(name.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요");
					textName.requestFocus();
				} else if(age.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "나이를 입력하세요");
					textAge.requestFocus();
				} else if(email.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "이메일을 입력하세요");
					textEmail.requestFocus();
				} 
				else {
					if(!Pattern.matches("^[a-z]{1}[a-z0-9]{3,7}+$", id)) {
						JOptionPane.showMessageDialog(null, "아이디는 영문(소문자)이나 숫자로 4~8자리를 입력할 수 있습니다.");
						textId.requestFocus();
					} 
					else if(!Pattern.matches("^[a-zA-Z0-9]{4,}+$", pwd)) {
						JOptionPane.showMessageDialog(null, "비밀번호는 영문이나 숫자로 최소 4자리를 입력할 수 있습니다.");
						textPwd.requestFocus();
					} 
					else if(!Pattern.matches("^[a-zA-Z가-힣]+$", name)) {
						JOptionPane.showMessageDialog(null, "이름은 한글 또는 영문으로 입력하세요.");
						textName.requestFocus();
					} 
					else if(!Pattern.matches("^[0-9]{1,2}$", age)) {
						JOptionPane.showMessageDialog(null, "나이는 1~2자리 숫자로 입력하세요.");
						textAge.requestFocus();
					} 
					else if(!Pattern.matches("^\\w+@\\w+\\.\\w+(\\.\\w+)?$", email)) {
						JOptionPane.showMessageDialog(null, "이메일을 형식에 맞게 입력하세요. ex) green1@gmail.com");
						textEmail.requestFocus();
					}
					else if(!Pattern.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$", tel)) {
						JOptionPane.showMessageDialog(null, "전화번호는 숫자와 '-'로 입력하세요.");
						textTel.requestFocus();
					} 
					else {
						if(rdMale.isSelected()) gender = "남자";
						else gender = "여자";
						cVO = dao.getCompSearch(id);
						if(cVO.getId() != null) {
							JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다. 다시 입력하세요.");
							textId.requestFocus();
						}
						else {
							cVO.setId(id);
							cVO.setPwd(pwd);
							cVO.setName(name);
							cVO.setAge(Integer.parseInt(age));
							cVO.setGender(gender);
							cVO.setEmail(email);
							cVO.setTel(tel);
							
							res = dao.setCompInput(cVO);
							
							if(res == 0) {
								JOptionPane.showMessageDialog(null, "회원가입 실패! 다시 가입하세요");
								textId.requestFocus();
							}
							else {
								JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다");
								dispose();
								new Run();
							}
						}
					}
				}	
					
				}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textId.setText("");
				textPwd.setText("");
				textName.setText("");
				textAge.setText("");
				rdFemale.setSelected(true);
				textEmail.setText("");
				textTel.setText("");
				textId.requestFocus();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Run();
			}
		});
	}
}
