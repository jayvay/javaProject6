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

public class FoodSearch extends JFrame {

	private JPanel contentPane;
	private JTable tbl;
	private JScrollPane scroll;
	private JPanel pn1;
	private JButton btnAdd;
	private JPanel pn2;
	private JButton btnDetail;
	private JButton btnExit;
	private JButton btnSave;
	private JScrollPane scrollPane;
	
	Vector title, vData;
	DefaultTableModel dtm;
	
	DAO dao = new DAO();
	FoodVO fVO = null;
	
	String foodTemp = "";
	
	@SuppressWarnings("unchecked")
	public FoodSearch(String mealTime) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("음식 찾기");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pn1 = new JPanel();
		pn1.setBounds(12, 42, 548, 315);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		title = new Vector<>();
		title.add("번호");
		title.add("상품명");
		title.add("음식 이름");
		title.add("칼로리");

		vData = dao.getFoodList();
		dtm = new DefaultTableModel(vData, title);
		tbl = new JTable(dtm);
		scroll = new JScrollPane(tbl);
		scroll.setBounds(42, 10, 452, 283);
		scroll.setViewportView(tbl);
		pn1.add(scroll);
		
		pn2 = new JPanel();
		pn2.setBounds(0, 356, 584, 205);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		btnAdd = new JButton("추가하기");
		btnAdd.setBounds(301, 66, 141, 31);
		pn2.add(btnAdd);
		btnAdd.setFont(new Font("굴림", Font.PLAIN, 18));
		
		btnDetail = new JButton("정보보기");
		btnDetail.setFont(new Font("굴림", Font.PLAIN, 18));
		btnDetail.setBounds(301, 25, 141, 31);
		pn2.add(btnDetail);
		
		btnExit = new JButton("나가기");
		btnExit.setFont(new Font("굴림", Font.PLAIN, 18));
		btnExit.setBounds(301, 148, 141, 31);
		pn2.add(btnExit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 25, 258, 150);
		pn2.add(scrollPane);
		
		JTextArea txtAddFood = new JTextArea();
		scrollPane.setViewportView(txtAddFood);
		
		JButton btnReset = new JButton("다시선택");
		btnReset.setFont(new Font("굴림", Font.PLAIN, 18));
		btnReset.setBounds(301, 107, 141, 31);
		pn2.add(btnReset);
		
		btnSave = new JButton("저장");
		btnSave.setFont(new Font("굴림", Font.PLAIN, 18));
		btnSave.setBounds(466, 25, 91, 154);
		pn2.add(btnSave);
		
		tbl.getColumnModel().getColumn(0).setMaxWidth(50);

		/*--------------------------------------------*/
		
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				String productName = tbl.getValueAt(row, 1).toString();
				fVO = dao.getFoodSearch(productName);
				if(fVO.getProductName() != null) {
					new FoodSearchDetail(fVO);
				}
			}
		});
		
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				Vector vdata = (Vector)vData.get(row);
					
				foodTemp += (String)vdata.get(1) + "\n";
				txtAddFood.setText(foodTemp);
				
			}
		});
		
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAddFood.setText("");
				foodTemp = "";
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MealVO mVO = new MealVO();
				mVO.setMealTime(mealTime);
				int res = 0;
				String temp = "";
				String[] foodTemps = txtAddFood.getText().split("\n");
				for(String f : foodTemps) {
					temp += f + "/";
				}
				mVO.setMealMenu(temp);
				res = dao.setMealMenuInput(mVO);
				
				if(res == 0) JOptionPane.showMessageDialog(null, "저장 실패! 다시 시도하세요.");
				else JOptionPane.showMessageDialog(null, "저장 성공!");
				
				dispose();
				new MealInput(mVO);
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
