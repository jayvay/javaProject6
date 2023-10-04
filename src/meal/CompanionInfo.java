package meal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class CompanionInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textTel;
	private JTextField textEmail;
	private JTextField textAge;
	private JTextField textName;
	private JTextField textPwd;
	private JTextField textId;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	DAO dao = new DAO();
	MealVO mVO = new MealVO();
	
	int res = 0;

	public CompanionInfo(CompanionVO cVO) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bobmukja_회원정보수정");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn1 = new JPanel();
		pn1.setBackground(new Color(237, 227, 211));
		pn1.setBounds(0, 0, 497, 561);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JLabel lblId = new JLabel("아이디 ▶");
		lblId.setBackground(new Color(237, 227, 211));
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("굴림", Font.BOLD, 14));
		lblId.setBounds(28, 52, 144, 37);
		pn1.add(lblId);
		
		JLabel lblPwd = new JLabel("비밀번호 ▶");
		lblPwd.setBackground(new Color(237, 227, 211));
		lblPwd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPwd.setFont(new Font("굴림", Font.BOLD, 14));
		lblPwd.setBounds(28, 122, 144, 37);
		pn1.add(lblPwd);
		
		JLabel lblName = new JLabel("이름 ▶");
		lblName.setBackground(new Color(237, 227, 211));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("굴림", Font.BOLD, 14));
		lblName.setBounds(28, 192, 144, 37);
		pn1.add(lblName);
		
		JLabel lblAge = new JLabel("나이 ▶");
		lblAge.setBackground(new Color(237, 227, 211));
		lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAge.setFont(new Font("굴림", Font.BOLD, 14));
		lblAge.setBounds(28, 262, 144, 37);
		pn1.add(lblAge);
		
		JLabel lblGender = new JLabel("성별 ▶");
		lblGender.setBackground(new Color(237, 227, 211));
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("굴림", Font.BOLD, 14));
		lblGender.setBounds(28, 332, 144, 37);
		pn1.add(lblGender);
		
		JLabel lblEmail = new JLabel("이메일 ▶");
		lblEmail.setBackground(new Color(237, 227, 211));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("굴림", Font.BOLD, 14));
		lblEmail.setBounds(28, 402, 144, 37);
		pn1.add(lblEmail);
		
		JLabel lblTel = new JLabel("전화번호 ▶");
		lblTel.setBackground(new Color(237, 227, 211));
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setFont(new Font("굴림", Font.BOLD, 14));
		lblTel.setBounds(28, 472, 144, 37);
		pn1.add(lblTel);
		
		textTel = new JTextField();
		textTel.setBackground(new Color(247, 242, 234));
		textTel.setHorizontalAlignment(SwingConstants.LEFT);
		textTel.setFont(new Font("굴림", Font.BOLD, 14));
		textTel.setColumns(10);
		textTel.setBounds(227, 467, 187, 42);
		textTel.setText(cVO.getTel());
		pn1.add(textTel);
		
		textEmail = new JTextField();
		textEmail.setBackground(new Color(247, 242, 234));
		textEmail.setHorizontalAlignment(SwingConstants.LEFT);
		textEmail.setFont(new Font("굴림", Font.BOLD, 14));
		textEmail.setColumns(10);
		textEmail.setBounds(227, 402, 187, 42);
		textEmail.setText(cVO.getEmail());
		pn1.add(textEmail);
		
		textAge = new JTextField();
		textAge.setBackground(new Color(247, 242, 234));
		textAge.setHorizontalAlignment(SwingConstants.LEFT);
		textAge.setFont(new Font("굴림", Font.BOLD, 14));
		textAge.setColumns(10);
		textAge.setBounds(255, 257, 116, 42);
		textAge.setText(String.valueOf(cVO.getAge()));
		pn1.add(textAge);
		
		JLabel lblAgeMan = new JLabel("만");
		lblAgeMan.setBackground(new Color(237, 227, 211));
		lblAgeMan.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgeMan.setFont(new Font("굴림", Font.BOLD, 14));
		lblAgeMan.setBounds(214, 257, 41, 42);
		pn1.add(lblAgeMan);
		
		JLabel lblAgeSe = new JLabel("세");
		lblAgeSe.setBackground(new Color(237, 227, 211));
		lblAgeSe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgeSe.setFont(new Font("굴림", Font.BOLD, 14));
		lblAgeSe.setBounds(373, 259, 41, 42);
		pn1.add(lblAgeSe);
		
		JRadioButton rdMale = new JRadioButton("남자");
		rdMale.setBackground(new Color(237, 227, 211));
		rdMale.setFont(new Font("굴림", Font.BOLD, 14));
		rdMale.setBounds(226, 339, 67, 23);
		buttonGroup.add(rdMale);
		pn1.add(rdMale);
		
		JRadioButton rdFemale = new JRadioButton("여자");
		rdFemale.setBackground(new Color(237, 227, 211));
		rdFemale.setFont(new Font("굴림", Font.BOLD, 14));
		rdFemale.setBounds(317, 339, 67, 23);
		buttonGroup.add(rdFemale);
		pn1.add(rdFemale);
		
		if(cVO.getGender().equals("남자")) rdMale.setSelected(true);
		else if(cVO.getGender().equals("여자")) rdFemale.setSelected(true);
		
		textName = new JTextField();
		textName.setBackground(new Color(247, 242, 234));
		textName.setHorizontalAlignment(SwingConstants.LEFT);
		textName.setFont(new Font("굴림", Font.BOLD, 14));
		textName.setColumns(10);
		textName.setBounds(227, 192, 187, 42);
		textName.setText(cVO.getName());
		pn1.add(textName);
		
		textPwd = new JTextField();
		textPwd.setBackground(new Color(247, 242, 234));
		textPwd.setHorizontalAlignment(SwingConstants.LEFT);
		textPwd.setFont(new Font("굴림", Font.BOLD, 14));
		textPwd.setColumns(10);
		textPwd.setBounds(227, 122, 187, 42);
		textPwd.setText(cVO.getPwd());
		pn1.add(textPwd);
		
		textId = new JTextField();
		textId.setBackground(new Color(237, 227, 211));
		textId.setEditable(false);
		textId.setHorizontalAlignment(SwingConstants.LEFT);
		textId.setFont(new Font("굴림", Font.BOLD, 14));
		textId.setColumns(10);
		textId.setBounds(227, 52, 187, 42);
		textId.setText(cVO.getId());
		pn1.add(textId);
		
		JPanel pn2 = new JPanel();
		pn2.setBackground(new Color(237, 227, 211));
		pn2.setBounds(492, 0, 292, 561);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JButton btnUpdate = new JButton("정 보 수 정");
		btnUpdate.setBackground(new Color(74, 125, 82));
		btnUpdate.setForeground(new Color(237, 227, 211));
		btnUpdate.setFont(new Font("굴림", Font.BOLD, 14));
		btnUpdate.setBounds(60, 388, 167, 62);
		pn2.add(btnUpdate);
		
		JButton btnDelete = new JButton("회 원 탈 퇴");
		btnDelete.setBackground(new Color(74, 125, 82));
		btnDelete.setForeground(new Color(237, 227, 211));
		btnDelete.setFont(new Font("굴림", Font.BOLD, 14));
		btnDelete.setBounds(60, 460, 167, 62);
		pn2.add(btnDelete);
		
		JButton btnExit = new JButton("나 가 기");
		btnExit.setBackground(new Color(74, 125, 82));
		btnExit.setForeground(new Color(237, 227, 211));
		btnExit.setFont(new Font("굴림", Font.BOLD, 14));
		btnExit.setBounds(60, 316, 167, 62);
		pn2.add(btnExit);
		
		
		
		/*------------------------------------------------*/

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res = 0;
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
				} else {
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
						
						cVO.setId(id);
						cVO.setPwd(pwd);
						cVO.setName(name);
						cVO.setAge(Integer.parseInt(age));
						cVO.setGender(gender);
						cVO.setEmail(email);
						cVO.setTel(tel);
						
						res = dao.setCompUpdate(cVO);
						
						if(res == 0) {
							JOptionPane.showMessageDialog(null, "수정 실패! 다시 가입하세요");
							textId.requestFocus();
						}
						else {
							JOptionPane.showMessageDialog(null, "회원정보가 수정되었습니다");
						}
					}
				}
			}
		});
		

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				int ans = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?", "회원 탈퇴", JOptionPane.YES_NO_OPTION);
				if(ans == 0) {
					res = dao.setCompDelete(id);
					if(res == 0) JOptionPane.showMessageDialog(null, "탈퇴 실패! 다시 시도하세요.");
					else {
						JOptionPane.showMessageDialog(null, "탈퇴 완료되었습니다. 프로그램이 종료됩니다.");
						System.exit(0);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "탈퇴를 취소하셨습니다.");
				}
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu();
			}
		});
	}
}
