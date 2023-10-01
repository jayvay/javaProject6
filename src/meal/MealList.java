package meal;

import java.awt.EventQueue;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class MealList extends JFrame {

	private JPanel contentPane, pn1;
	private JTable tbl;
	private JScrollPane scroll;
	private JComboBox cbCondition;
	
	Vector title, vData;
	DefaultTableModel dtm;
	
	DAO dao = new DAO();
	private JButton btnDetail;
	private JTextField textCondition;
	private JButton btnList;
	private JButton btnAsc;
	private JButton btnDesc;

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
		
		
		title = new Vector<>();
		title.add("번호");
		title.add("식사종류");
		title.add("식사일자 및 시간");
		title.add("식사메뉴");
		title.add("한 끼 칼로리");
		title.add("하루 칼로리");
		
		vData = dao.getMealList();
		dtm = new DefaultTableModel(vData,title);
		tbl = new JTable(dtm);
		scroll = new JScrollPane(tbl);
		scroll.setBounds(22, 83, 834, 320);
		scroll.setViewportView(tbl);
		pn1.add(scroll);
		
		tableCellAlign(tbl);
		tbl.getColumnModel().getColumn(0).setMaxWidth(50);
		tbl.getColumnModel().getColumn(1).setMaxWidth(90);
		tbl.getColumnModel().getColumn(4).setMaxWidth(90);
		tbl.getColumnModel().getColumn(5).setMaxWidth(90);
		
		JButton btnExit = new JButton("나가기");
		btnExit.setFont(new Font("굴림", Font.PLAIN, 16));
		btnExit.setBounds(697, 479, 159, 44);
		pn1.add(btnExit);
		
		btnDetail = new JButton("조회");
		btnDetail.setFont(new Font("굴림", Font.PLAIN, 16));
		btnDetail.setBounds(696, 425, 160, 44);
		pn1.add(btnDetail);
		
		JButton btnCondition = new JButton("검색");
		btnCondition.setBounds(578, 28, 97, 34);
		pn1.add(btnCondition);
		
		cbCondition = new JComboBox();
		cbCondition.setModel(new DefaultComboBoxModel(new String[] {"날짜", "식사종류", "식사메뉴"}));
		cbCondition.setBounds(258, 28, 94, 34);
		pn1.add(cbCondition);
		
		textCondition = new JTextField();
		textCondition.setBounds(366, 29, 200, 33);
		pn1.add(textCondition);
		textCondition.setColumns(10);
		
		btnList = new JButton("전체목록");
		btnList.setBounds(149, 28, 97, 34);
		pn1.add(btnList);
		
		btnAsc = new JButton("날짜 오름차순");
		btnAsc.setFont(new Font("굴림", Font.PLAIN, 12));
		btnAsc.setBounds(37, 425, 160, 44);
		pn1.add(btnAsc);
		
		btnDesc = new JButton("날짜 내림차순");
		btnDesc.setFont(new Font("굴림", Font.PLAIN, 12));
		btnDesc.setBounds(37, 479, 160, 44);
		pn1.add(btnDesc);
		
		
		tbl.getColumnModel().getColumn(0).setMaxWidth(50);
		
		/*---------------------------------------------*/

		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vData = dao.getMealList();
				dtm.setDataVector(vData, title); 
				tableCellAlign(tbl);
				tbl.getColumnModel().getColumn(0).setMaxWidth(50);
				tbl.getColumnModel().getColumn(1).setMaxWidth(90);
				tbl.getColumnModel().getColumn(4).setMaxWidth(90);
				tbl.getColumnModel().getColumn(5).setMaxWidth(90);
			}
		});

		btnCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getConditionProcess();
			}
		});
		
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MealListDetail();	//새로 만들건지 MealInput창을 사용할건지
			}
		});
		

		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vData = dao.getMealTimeAscList("a");
				dtm.setDataVector(vData, title); 
				
				tableCellAlign(tbl);
				tbl.getColumnModel().getColumn(0).setMaxWidth(50);
				tbl.getColumnModel().getColumn(1).setMaxWidth(90);
				tbl.getColumnModel().getColumn(4).setMaxWidth(90);
				tbl.getColumnModel().getColumn(5).setMaxWidth(90);
			}
		});
		

		btnDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vData = dao.getMealTimeAscList("d");
				dtm.setDataVector(vData, title); 
				
				tableCellAlign(tbl);
				tbl.getColumnModel().getColumn(0).setMaxWidth(50);
				tbl.getColumnModel().getColumn(1).setMaxWidth(90);
				tbl.getColumnModel().getColumn(4).setMaxWidth(90);
				tbl.getColumnModel().getColumn(5).setMaxWidth(90);
			}
		});
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu();
			}
		});
	}
	
	private void tableCellAlign(JTable tbl) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		TableColumnModel tcm = tbl.getColumnModel();
		
		for(int i=0; i<tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}

	protected void getConditionProcess() {
		String cbCondi = cbCondition.getSelectedItem().toString();
		String textCondi = textCondition.getText();
		
		if(textCondi.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "검색할 내용을 입력하세요.");
			textCondition.requestFocus();
			return;
		}
		if(cbCondi.equals("날짜")) {
			if(!Pattern.matches("^[\\d]{4}?-?(0[1-9]|1[012])?-?(0[1-9]|[12][0-9]|3[01])?$", textCondi)) {
				JOptionPane.showMessageDialog(null, "날짜를 형식에 맞게 입력하세요. ex) 2023 , 2023-10 , 2023-10-01");
				textCondition.requestFocus();
			}
			else vData = dao.getConditionSearch("mealTime", textCondi);
		}
		else if(cbCondi.equals("식사종류")) vData = dao.getConditionSearch("meal", textCondi);
		else if(cbCondi.equals("식사메뉴")) vData = dao.getConditionSearch("mealMenu", textCondi);
		
		dtm.setDataVector(vData, title); 
		//JTable 안의 셀의 내용을 가운데 정렬
		tableCellAlign(tbl);
		//0번열(idx(고유번호)) 셀의 크기를 50픽셀로 조정하기
		tbl.getColumnModel().getColumn(0).setMaxWidth(50);
		tbl.getColumnModel().getColumn(1).setMaxWidth(90);
		tbl.getColumnModel().getColumn(4).setMaxWidth(90);
		tbl.getColumnModel().getColumn(5).setMaxWidth(90);
	}
}
