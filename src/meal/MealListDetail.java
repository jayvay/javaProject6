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
import java.awt.Component;

public class MealListDetail extends JFrame {

	private JPanel contentPane;
	
	Vector title, vData;
	DefaultTableModel dtm;
	
	DAO dao = new DAO();
	FoodVO fVO = null;
	
	String foodTemp = "";
	private JTextField textField;
	private JTextField textField_1;
	private JTable tbl;
	
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
		
		
		JPanel pn2 = new JPanel();
		pn2.setBounds(0, 0, 784, 561);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JComboBox cbYY = new JComboBox(new Object[]{});
		cbYY.setBounds(152, 46, 70, 28);
		pn2.add(cbYY);
		
		JLabel lblNewLabel = new JLabel("식사종류");
		lblNewLabel.setBounds(55, 92, 125, 39);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblNewLabel);
		
		JRadioButton rdbtnBkft = new JRadioButton("아침");
		rdbtnBkft.setBounds(55, 137, 61, 23);
		rdbtnBkft.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(rdbtnBkft);
		
		JRadioButton rdbtnLunch = new JRadioButton("점심");
		rdbtnLunch.setBounds(126, 137, 61, 23);
		rdbtnLunch.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(rdbtnLunch);
		
		JRadioButton rdbtnDinner = new JRadioButton("저녁");
		rdbtnDinner.setBounds(55, 180, 61, 23);
		rdbtnDinner.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(rdbtnDinner);
		
		JRadioButton rdbtnSnack = new JRadioButton("간식");
		rdbtnSnack.setBounds(126, 180, 61, 23);
		rdbtnSnack.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(rdbtnSnack);
		
		JLabel lblMealTime = new JLabel("식사시간");
		lblMealTime.setBounds(34, 43, 125, 39);
		lblMealTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMealTime.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblMealTime);
		
		JComboBox cbMM = new JComboBox(new Object[]{});
		cbMM.setBounds(257, 46, 61, 28);
		pn2.add(cbMM);
		
		JComboBox cbDD = new JComboBox(new Object[]{});
		cbDD.setBounds(348, 46, 61, 28);
		pn2.add(cbDD);
		
		JLabel lblYY = new JLabel("년");
		lblYY.setBounds(214, 43, 38, 35);
		lblYY.setHorizontalAlignment(SwingConstants.CENTER);
		pn2.add(lblYY);
		
		JLabel lblMM = new JLabel("월");
		lblMM.setBounds(310, 43, 38, 35);
		lblMM.setHorizontalAlignment(SwingConstants.CENTER);
		pn2.add(lblMM);
		
		JLabel lblDD = new JLabel("일");
		lblDD.setBounds(403, 43, 38, 35);
		lblDD.setHorizontalAlignment(SwingConstants.CENTER);
		pn2.add(lblDD);
		
		JComboBox cbHH = new JComboBox(new Object[]{});
		cbHH.setBounds(441, 46, 50, 28);
		pn2.add(cbHH);
		
		JComboBox cbmm = new JComboBox(new Object[]{});
		cbmm.setBounds(526, 46, 50, 28);
		pn2.add(cbmm);
		
		JLabel lblHH = new JLabel("시");
		lblHH.setBounds(487, 43, 38, 35);
		lblHH.setHorizontalAlignment(SwingConstants.CENTER);
		pn2.add(lblHH);
		
		JLabel lblmm = new JLabel("분");
		lblmm.setBounds(571, 43, 38, 35);
		lblmm.setHorizontalAlignment(SwingConstants.CENTER);
		pn2.add(lblmm);
		
		JButton btnDelete = new JButton("삭 제");
		btnDelete.setBounds(621, 46, 133, 51);
		pn2.add(btnDelete);
		
		JButton btnExit_1 = new JButton("나가기");
		btnExit_1.setBounds(621, 121, 133, 54);
		btnExit_1.setFont(new Font("굴림", Font.PLAIN, 12));
		pn2.add(btnExit_1);
		
		JLabel lblAMealKcal = new JLabel("한 끼 총 섭취 칼로리");
		lblAMealKcal.setBounds(192, 100, 207, 39);
		lblAMealKcal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAMealKcal.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblAMealKcal);
		
		textField = new JTextField();
		textField.setBounds(223, 138, 146, 30);
		textField.setColumns(10);
		pn2.add(textField);
		
		JLabel lblDayKcal = new JLabel("하루 총 섭취 칼로리");
		lblDayKcal.setBounds(399, 100, 207, 39);
		lblDayKcal.setHorizontalAlignment(SwingConstants.CENTER);
		lblDayKcal.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblDayKcal);
		
		textField_1 = new JTextField();
		textField_1.setBounds(427, 138, 146, 30);
		textField_1.setColumns(10);
		pn2.add(textField_1);
		
		title = new Vector<>();
		title.add("번호");
		title.add("상품명");
		title.add("음식 이름");
		title.add("칼로리");

		JScrollPane scroll = new JScrollPane(tbl);
		scroll.setBounds(39, 226, 452, 283);
		pn2.add(scroll);

		
		vData = dao.getMyFoodList();
		dtm = new DefaultTableModel(vData, title);
		tbl = new JTable(dtm);
		scroll.setViewportView(tbl);
		pn2.add(scroll);

		

		/*--------------------------------------------*/
		
	}
}
