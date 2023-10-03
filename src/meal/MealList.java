package meal;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Color;

public class MealList extends JFrame {

	private JPanel contentPane, pn1;
	private JTable tbl;
	private JScrollPane scroll;
	private JComboBox cbCondition;
	private JButton btnFoodDetail;
	private JTextField textCondition;
	private JButton btnList;
	private JButton btnAsc;
	private JButton btnDesc;
	private JButton btnMealDelete;
	
	Vector title, vData;
	DefaultTableModel dtm;
	
	DAO dao = new DAO();
	MealVO mVO = null;
	
	int res = 0;
	private JButton btnF5;
	private JButton btnSelect;
	private JButton btnNewButton;
	private JComboBox cbFood;
	
	public MealList() {
		setTitle("Bobmukja_나의식단일지");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 610);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pn1 = new JPanel();
		pn1.setForeground(new Color(237, 227, 211));
		pn1.setBackground(new Color(242, 236, 225));
		pn1.setBounds(0, 0, 884, 571);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		title = new Vector<>();
		title.add("시간");
		title.add("식사종류");
		title.add("칼로리");
		title.add("식사메뉴");
//		title.add("번호");
		
		vData = dao.getMealList("a");
		
		dtm = new DefaultTableModel(vData,title);
		tbl = new JTable(dtm);
		tbl.setFont(new Font("굴림", Font.PLAIN, 11));
		tbl.setBackground(new Color(249, 245, 240));
		scroll = new JScrollPane(tbl);
		scroll.setBounds(22, 116, 834, 320);
		scroll.setViewportView(tbl);
		pn1.add(scroll);
		
		tableCellAlign(tbl);
		tbl.getColumnModel().getColumn(0).setMaxWidth(100);
		tbl.getColumnModel().getColumn(1).setMaxWidth(100);
		tbl.getColumnModel().getColumn(2).setMaxWidth(100);
		
		JButton btnExit = new JButton("나가기");
		btnExit.setForeground(new Color(249, 245, 240));
		btnExit.setBackground(new Color(115, 94, 63));
		btnExit.setFont(new Font("굴림", Font.BOLD, 14));
		btnExit.setBounds(750, 446, 94, 82);
		pn1.add(btnExit);
		
		JButton btnCondition = new JButton("검 색");
		btnCondition.setForeground(new Color(249, 245, 240));
		btnCondition.setFont(new Font("굴림", Font.BOLD, 14));
		btnCondition.setBackground(new Color(74, 125, 82));
		btnCondition.setBounds(766, 63, 78, 43);
		pn1.add(btnCondition);
		
		cbCondition = new JComboBox();
		cbCondition.setFont(new Font("굴림", Font.BOLD, 13));
		cbCondition.setBackground(new Color(242, 236, 225));
		cbCondition.setModel(new DefaultComboBoxModel(new String[] {"날짜", "식사종류", "식사메뉴"}));
		cbCondition.setBounds(448, 63, 94, 43);
		pn1.add(cbCondition);
		
		textCondition = new JTextField();
		textCondition.setBackground(new Color(249, 245, 240));
		textCondition.setBounds(554, 64, 200, 42);
		pn1.add(textCondition);
		textCondition.setColumns(10);
		
		btnList = new JButton("전체조회");
		btnList.setForeground(new Color(249, 245, 240));
		btnList.setFont(new Font("굴림", Font.BOLD, 13));
		btnList.setBackground(new Color(74, 125, 82));
		btnList.setBounds(340, 63, 96, 43);
		pn1.add(btnList);
		
		btnAsc = new JButton("Asc");
		btnAsc.setForeground(new Color(249, 245, 240));
		btnAsc.setBackground(new Color(178, 159, 135));
		btnAsc.setFont(new Font("Verdana", Font.BOLD, 14));
		btnAsc.setBounds(22, 63, 97, 43);
		pn1.add(btnAsc);
		
		btnDesc = new JButton("Desc");
		btnDesc.setForeground(new Color(249, 245, 240));
		btnDesc.setBackground(new Color(178, 159, 135));
		btnDesc.setFont(new Font("Verdana", Font.BOLD, 14));
		btnDesc.setBounds(126, 63, 97, 43);
		pn1.add(btnDesc);
		
		btnMealDelete = new JButton("삭 제");
		btnMealDelete.setForeground(new Color(249, 245, 240));
		btnMealDelete.setBackground(new Color(178, 159, 135));
		btnMealDelete.setFont(new Font("굴림", Font.BOLD, 14));
		btnMealDelete.setBounds(538, 446, 94, 82);
		pn1.add(btnMealDelete);
		
		btnF5 = new JButton("새로고침");
		btnF5.setForeground(new Color(249, 245, 240));
		btnF5.setBackground(new Color(178, 159, 135));
		btnF5.setFont(new Font("굴림", Font.BOLD, 14));
		btnF5.setBounds(644, 446, 94, 82);
		pn1.add(btnF5);
		
		
		btnSelect = new JButton("상세정보");
		btnSelect.setForeground(new Color(249, 245, 240));
		btnSelect.setFont(new Font("굴림", Font.BOLD, 14));
		btnSelect.setBackground(new Color(178, 159, 135));
		btnSelect.setBounds(22, 446, 94, 82);
		pn1.add(btnSelect);
		
	
		/*---------------------------------------------*/
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				Vector vdata = (Vector) vData.get(row);
				String [] listSelFood = vdata.get(3).toString().split("/");
				
				JScrollPane scrollList = new JScrollPane();
				scrollList.setBounds(126, 452, 356, 44);
				pn1.add(scrollList);
				
				cbFood = new JComboBox();
				cbFood.setBackground(new Color(242, 236, 225));
				scrollList.setViewportView(cbFood);
				for(int i=0; i<listSelFood.length; i++) {
					cbFood.addItem(listSelFood[i]);
				}
				
				btnFoodDetail = new JButton("음식 상세정보");
				btnFoodDetail.setForeground(new Color(249, 245, 240));
				btnFoodDetail.setBackground(new Color(178, 159, 135));
				scrollList.setRowHeaderView(btnFoodDetail);
				btnFoodDetail.setFont(new Font("굴림", Font.BOLD, 13));
				
				btnFoodDetail.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String food = cbFood.getSelectedItem().toString();
						FoodVO fVO = dao.getFoodSearch(food);
						new FoodSearchDetail(fVO);
						cbFood.removeAllItems();
					}
				});
			}
		});
		
		
		btnMealDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mVO = new MealVO();
				vData = dao.getMealList("a");
				int row = tbl.getSelectedRow();
				Vector vdata = (Vector)vData.get(row);
				String mealMIdxTemp = vdata.get(4).toString();
				System.out.println("vdata나야나:"+vdata);
				System.out.println("vdata.get(4)나야나:"+vdata.get(4));  
				System.out.println("mealMIdxTemp나야나:" + mealMIdxTemp);

				res = dao.setMealDelete(null, mealMIdxTemp);
				if(res == 0) JOptionPane.showMessageDialog(null, "삭제 실패! 다시 시도하세요.");
				
			}
		});
		
		btnF5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getmealList();
			}
		});
		
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getmealList();
			}
		});

		btnCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getConditionProcess();
			}
		});
		

		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vData = dao.getMealList("a");
				dtm.setDataVector(vData, title); 
				
				tableCellAlign(tbl);
				tbl.getColumnModel().getColumn(0).setMaxWidth(100);
				tbl.getColumnModel().getColumn(1).setMaxWidth(100);
				tbl.getColumnModel().getColumn(2).setMaxWidth(100);
			}
		});
		

		btnDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vData = dao.getMealList("d");
				dtm.setDataVector(vData, title); 
				
				tableCellAlign(tbl);
				tbl.getColumnModel().getColumn(0).setMaxWidth(100);
				tbl.getColumnModel().getColumn(1).setMaxWidth(100);
				tbl.getColumnModel().getColumn(2).setMaxWidth(100);
			}
		});
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu();
			}
		});
	}
	
	
	protected void getmealList() {
		vData = dao.getMealList("a");
		dtm.setDataVector(vData, title); 
		tableCellAlign(tbl);
		tbl.getColumnModel().getColumn(0).setMaxWidth(100);
		tbl.getColumnModel().getColumn(1).setMaxWidth(100);
		tbl.getColumnModel().getColumn(2).setMaxWidth(100);
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
		tbl.getColumnModel().getColumn(0).setMaxWidth(100);
		tbl.getColumnModel().getColumn(1).setMaxWidth(100);
		tbl.getColumnModel().getColumn(2).setMaxWidth(100);
	}
	
}
