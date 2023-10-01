package meal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MealListDetail extends JFrame {

	private JPanel contentPane;
	
	Vector title, vData;
	DefaultTableModel dtm;
	
	DAO dao = new DAO();
	FoodVO fVO = null;
	
	String foodTemp = "";
	private JTextField textField;
	private JTextField textField_1;
	
	@SuppressWarnings("unchecked")
	public MealListDetail(String mealTime) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("음식 찾기");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		title = new Vector<>();
		title.add("번호");
		title.add("상품명");
		title.add("음식 이름");
		title.add("칼로리");

		vData = dao.getFoodList();
		dtm = new DefaultTableModel(vData, title);
		
		JPanel pn2 = new JPanel();
		pn2.setBounds(0, 0, 784, 561);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JComboBox cbYY = new JComboBox(new Object[]{});
		cbYY.setBounds(173, 37, 70, 28);
		pn2.add(cbYY);
		
		JLabel lblNewLabel = new JLabel("식사종류");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel.setBounds(40, 100, 125, 39);
		pn2.add(lblNewLabel);
		
		JRadioButton rdbtnBkft = new JRadioButton("아침");
		rdbtnBkft.setFont(new Font("굴림", Font.PLAIN, 14));
		rdbtnBkft.setBounds(50, 145, 61, 23);
		pn2.add(rdbtnBkft);
		
		JRadioButton rdbtnLunch = new JRadioButton("점심");
		rdbtnLunch.setFont(new Font("굴림", Font.PLAIN, 14));
		rdbtnLunch.setBounds(119, 145, 61, 23);
		pn2.add(rdbtnLunch);
		
		JRadioButton rdbtnDinner = new JRadioButton("저녁");
		rdbtnDinner.setFont(new Font("굴림", Font.PLAIN, 14));
		rdbtnDinner.setBounds(49, 188, 61, 23);
		pn2.add(rdbtnDinner);
		
		JRadioButton rdbtnSnack = new JRadioButton("간식");
		rdbtnSnack.setFont(new Font("굴림", Font.PLAIN, 14));
		rdbtnSnack.setBounds(119, 188, 61, 23);
		pn2.add(rdbtnSnack);
		
		JLabel lblMealTime = new JLabel("식사시간");
		lblMealTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMealTime.setFont(new Font("굴림", Font.PLAIN, 14));
		lblMealTime.setBounds(55, 34, 125, 39);
		pn2.add(lblMealTime);
		
		JButton btnUpdate = new JButton("수 정");
		btnUpdate.setBounds(621, 28, 133, 51);
		pn2.add(btnUpdate);
		
		JComboBox cbMM = new JComboBox(new Object[]{});
		cbMM.setBounds(278, 37, 61, 28);
		pn2.add(cbMM);
		
		JComboBox cbDD = new JComboBox(new Object[]{});
		cbDD.setBounds(369, 37, 61, 28);
		pn2.add(cbDD);
		
		JLabel lblYY = new JLabel("년");
		lblYY.setHorizontalAlignment(SwingConstants.CENTER);
		lblYY.setBounds(235, 34, 38, 35);
		pn2.add(lblYY);
		
		JLabel lblMM = new JLabel("월");
		lblMM.setHorizontalAlignment(SwingConstants.CENTER);
		lblMM.setBounds(331, 34, 38, 35);
		pn2.add(lblMM);
		
		JLabel lblDD = new JLabel("일");
		lblDD.setHorizontalAlignment(SwingConstants.CENTER);
		lblDD.setBounds(424, 34, 38, 35);
		pn2.add(lblDD);
		
		JComboBox cbHH = new JComboBox(new Object[]{});
		cbHH.setBounds(462, 37, 50, 28);
		pn2.add(cbHH);
		
		JComboBox cbmm = new JComboBox(new Object[]{});
		cbmm.setBounds(547, 37, 50, 28);
		pn2.add(cbmm);
		
		JLabel lblHH = new JLabel("시");
		lblHH.setHorizontalAlignment(SwingConstants.CENTER);
		lblHH.setBounds(508, 34, 38, 35);
		pn2.add(lblHH);
		
		JLabel lblmm = new JLabel("분");
		lblmm.setHorizontalAlignment(SwingConstants.CENTER);
		lblmm.setBounds(592, 34, 38, 35);
		pn2.add(lblmm);
		
		JButton btnDelete = new JButton("삭 제");
		btnDelete.setBounds(621, 94, 133, 51);
		pn2.add(btnDelete);
		
		JButton btnExit_1 = new JButton("나가기");
		btnExit_1.setFont(new Font("굴림", Font.PLAIN, 12));
		btnExit_1.setBounds(621, 164, 133, 54);
		pn2.add(btnExit_1);
		
		JLabel lblAMealKcal = new JLabel("한 끼 총 섭취 칼로리");
		lblAMealKcal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAMealKcal.setFont(new Font("굴림", Font.PLAIN, 14));
		lblAMealKcal.setBounds(193, 102, 207, 39);
		pn2.add(lblAMealKcal);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(223, 138, 146, 30);
		pn2.add(textField);
		
		JLabel lblDayKcal = new JLabel("하루 총 섭취 칼로리");
		lblDayKcal.setHorizontalAlignment(SwingConstants.CENTER);
		lblDayKcal.setFont(new Font("굴림", Font.PLAIN, 14));
		lblDayKcal.setBounds(402, 112, 207, 39);
		pn2.add(lblDayKcal);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(429, 148, 146, 30);
		pn2.add(textField_1);

		/*--------------------------------------------*/
		
	}
}
