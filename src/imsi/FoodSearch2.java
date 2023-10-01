package imsi;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import meal.DAO;
import meal.FoodSearchDetail;
import meal.FoodVO;
import meal.MealInput;

public class FoodSearch2 extends JFrame {

	private JPanel contentPane;
	private JTable tbl;
	private JScrollPane scroll;
	
	Vector title, vData;
	DefaultTableModel dtm;
	
	DAO dao = new DAO();
	FoodVO fVO = null;
	private JPanel panel;
	private JButton btnPull;
	private JPanel panel_1;
	private JButton btnDetail;
	private JButton btnExit;
	private JScrollPane scrollPane;
	private JList list;
	
	String foodTemp = "";
	
	@SuppressWarnings("unchecked")
	public FoodSearch2() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 42, 548, 315);
		contentPane.add(panel);
		panel.setLayout(null);
		
		title = new Vector<>();
		title.add("번호");
		title.add("음식 이름");
		title.add("상품명");
		title.add("칼로리");

		vData = dao.getFoodList();
		dtm = new DefaultTableModel(vData, title);
		tbl = new JTable(dtm);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scroll = new JScrollPane(tbl);
		scroll.setBounds(42, 10, 452, 283);
		scroll.setViewportView(tbl);
		panel.add(scroll);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 376, 584, 185);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnPull = new JButton("추가하기");
		btnPull.setBounds(388, 54, 141, 50);
		panel_1.add(btnPull);
		btnPull.setFont(new Font("굴림", Font.PLAIN, 18));
		
		btnDetail = new JButton("정보보기");
		btnDetail.setFont(new Font("굴림", Font.PLAIN, 18));
		btnDetail.setBounds(388, 0, 141, 50);
		panel_1.add(btnDetail);
		
		btnExit = new JButton("나가기");
		btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
		btnExit.setBounds(388, 114, 141, 50);
		panel_1.add(btnExit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 25, 258, 150);
		panel_1.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		tbl.getColumnModel().getColumn(0).setMaxWidth(50);

		/*--------------------------------------------*/
		
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				String foodName = tbl.getValueAt(row, 1).toString();
				String productName = tbl.getValueAt(row, 2).toString();
				fVO = dao.getFoodSearch(productName);
				if(fVO.getProductName() != null) {
					new FoodSearchDetail(fVO);
				}
			}
		});
		
		
		btnPull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				String foodName = tbl.getValueAt(row, 1).toString();
				String productName = tbl.getValueAt(row, 2).toString();
				Vector vdata2;
				
				vdata2 = (Vector)vData.get(row);
					
				fVO = dao.getFoodSearch((String)vdata2.get(2));
				
				//String foodProduct = (String)vdata2.get(1) + "/" + (String)vdata2.get(2);
				
				DefaultListModel listModel = new DefaultListModel();
				
				//listModel.addElement(foodProduct);
				listModel.addElement(foodTemp);
				list.setModel(listModel);
				
//				new MealInput(fVO);
//				dispose();
				
//				if(fVO.getProductName() != null) {
//					new FoodSearchDetail(fVO);
//				}
			}
		});
		
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MealInput();
			}
		});
		
		/*
		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();
				String foodName = tbl.getValueAt(row, 1).toString();
				String productName = tbl.getValueAt(row, 2).toString();
				Vector vdata2;
				
				vdata2 = (Vector)vData.get(row);
				foodTemp += (String)vdata2.get(1) + "/" + (String)vdata2.get(2) + "\n";
			}
		});
		*/
	}

}
