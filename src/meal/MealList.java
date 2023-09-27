package meal;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MealList extends JFrame {

	private JPanel contentPane, pn1;
	private JTable tbl;
	private JScrollPane scroll;
	private JComboBox cbCondition;
	
	Vector title, vData;
	DefaultTableModel dtm;
	
	DAO dao = new DAO();
	private JButton btnDetail;

	public MealList() {
		setTitle("나의 식단 일지");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pn1 = new JPanel();
		pn1.setBounds(0, 0, 884, 561);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		cbCondition = new JComboBox();
		cbCondition.setBounds(281, 33, 200, 23);
		pn1.add(cbCondition);
		
		title = new Vector<>();
		title.add("번호");
		title.add("식사일자 및 시간");
		title.add("식사종류");
		title.add("음식명");
		title.add("상품명");
		title.add("한 끼 칼로리");
		title.add("하루 섭취 칼로리");
		title.add("목표 섭취 칼로리");
		
		vData = dao.getMealList();
		dtm = new DefaultTableModel(vData,title);
		tbl = new JTable(dtm);
		scroll = new JScrollPane(tbl);
		scroll.setBounds(22, 66, 834, 320);
		scroll.setViewportView(tbl);
		pn1.add(scroll);
		
		JButton btnExit = new JButton("나가기");
		btnExit.setFont(new Font("굴림", Font.PLAIN, 16));
		btnExit.setBounds(721, 10, 135, 44);
		pn1.add(btnExit);
		
		btnDetail = new JButton("조회");
		btnDetail.setFont(new Font("굴림", Font.PLAIN, 16));
		btnDetail.setBounds(697, 396, 160, 44);
		pn1.add(btnDetail);
		
		tbl.getColumnModel().getColumn(0).setMaxWidth(50);
		
		/*---------------------------------------------*/
		
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MealListDetail();	//새로 만들건지 MealInput창을 사용할건지
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
