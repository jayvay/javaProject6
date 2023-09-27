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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

public class FoodSearch extends JFrame {

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
	
	String foodTemp = "";
	private JButton btnSave;
	
	@SuppressWarnings("unchecked")
	public FoodSearch() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("음식 찾기");
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
		panel_1.setBounds(0, 356, 584, 205);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnPull = new JButton("추가하기");
		btnPull.setBounds(301, 66, 141, 31);
		panel_1.add(btnPull);
		btnPull.setFont(new Font("굴림", Font.PLAIN, 18));
		
		btnDetail = new JButton("정보보기");
		btnDetail.setFont(new Font("굴림", Font.PLAIN, 18));
		btnDetail.setBounds(301, 25, 141, 31);
		panel_1.add(btnDetail);
		
		btnExit = new JButton("나가기");
		btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
		btnExit.setBounds(301, 148, 141, 31);
		panel_1.add(btnExit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 25, 258, 150);
		panel_1.add(scrollPane);
		
		JTextArea txtaFood = new JTextArea();
		scrollPane.setViewportView(txtaFood);
		
		JButton btnReset = new JButton("다시선택");
		btnReset.setFont(new Font("굴림", Font.PLAIN, 18));
		btnReset.setBounds(301, 107, 141, 31);
		panel_1.add(btnReset);
		
		btnSave = new JButton("저장");
		btnSave.setFont(new Font("굴림", Font.PLAIN, 18));
		btnSave.setBounds(466, 25, 91, 154);
		panel_1.add(btnSave);
		
		tbl.getColumnModel().getColumn(0).setMaxWidth(50);

		/*--------------------------------------------*/
		
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				String foodName = tbl.getValueAt(row, 1).toString();
				String productName = tbl.getValueAt(row, 2).toString();
				fVO = dao.getFoodSearch(foodName, productName);
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
				Vector vdata = (Vector)vData.get(row);
					
				foodTemp += (String)vdata.get(1) + "/" + (String)vdata.get(2) + "\n";
				txtaFood.setText(foodTemp);
				
				
			}
		});
		
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtaFood.setText("");
				foodTemp = "";
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String temp = "";
				String[] foodTemps = txtaFood.getText().split("\n");
				for(String f : foodTemps) {
					temp += f + ":";
				}
				System.out.println("temp: " + temp);
				
				
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MealInput();
			}
		});
		
	}
}
