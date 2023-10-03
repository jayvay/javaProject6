package meal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.DropMode;

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
	MealVO mVO = new MealVO();
	
	String foodTemp = "";
	double ateFoodKcal = 0;
	
	private JTextField textIntake;
	private JLabel lblGram;
	private JTextField textChoiceFood;
	private JButton btnChoiceFood;
	private JLabel lblIntake;
	
	@SuppressWarnings("unchecked")
	public FoodSearch(String mealTime) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bobmukja_음식검색");
		setSize(700, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pn1 = new JPanel();
		pn1.setBackground(new Color(242, 236, 225));
		pn1.setBounds(0, 10, 684, 327);
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
		tbl.setFont(new Font("굴림", Font.PLAIN, 11));
		tbl.setBackground(new Color(249, 245, 240));
		scroll = new JScrollPane(tbl);
		scroll.setBounds(26, 21, 510, 296);
		scroll.setViewportView(tbl);
		pn1.add(scroll);
		
		btnDetail = new JButton("상세정보");
		btnDetail.setForeground(new Color(255, 255, 255));
		btnDetail.setBackground(new Color(178, 159, 135));
		btnDetail.setBounds(548, 39, 118, 62);
		pn1.add(btnDetail);
		btnDetail.setFont(new Font("굴림", Font.BOLD, 14));
		
		btnChoiceFood = new JButton("음식선택");
		btnChoiceFood.setForeground(new Color(255, 255, 255));
		btnChoiceFood.setBackground(new Color(178, 159, 135));
		btnChoiceFood.setFont(new Font("굴림", Font.BOLD, 14));
		btnChoiceFood.setBounds(548, 111, 118, 62);
		pn1.add(btnChoiceFood);
		
		btnExit = new JButton("나가기");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(178, 159, 135));
		btnExit.setBounds(548, 255, 118, 62);
		pn1.add(btnExit);
		btnExit.setFont(new Font("굴림", Font.BOLD, 14));
		
		JButton btnReset = new JButton("다시선택");
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setBackground(new Color(178, 159, 135));
		btnReset.setBounds(548, 183, 118, 62);
		pn1.add(btnReset);
		btnReset.setFont(new Font("굴림", Font.BOLD, 14));
		
		pn2 = new JPanel();
		pn2.setBackground(new Color(242, 236, 225));
		pn2.setBounds(0, 337, 684, 224);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		btnAdd = new JButton("추가");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(115, 94, 63));
		btnAdd.setBounds(30, 171, 293, 36);
		pn2.add(btnAdd);
		btnAdd.setFont(new Font("굴림", Font.BOLD, 14));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(351, 20, 293, 131);
		pn2.add(scrollPane);
		
		JTextArea textAddFood = new JTextArea();
		textAddFood.setEditable(false);
		textAddFood.setBackground(new Color(228, 222, 211));
		textAddFood.setFont(new Font("굴림", Font.BOLD, 13));
		scrollPane.setViewportView(textAddFood);
		
		btnSave = new JButton("저장");
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(115, 94, 63));
		btnSave.setFont(new Font("굴림", Font.BOLD, 14));
		btnSave.setBounds(351, 170, 293, 36);
		pn2.add(btnSave);
		
		textIntake = new JTextField();
		textIntake.setHorizontalAlignment(SwingConstants.CENTER);
		textIntake.setText("1");
		textIntake.setFont(new Font("Arial", Font.BOLD, 13));
		textIntake.setColumns(10);
		textIntake.setBackground(new Color(228, 222, 211));
		textIntake.setBounds(30, 118, 103, 31);
		pn2.add(textIntake);
		
		lblGram = new JLabel("X  1인분");
		lblGram.setHorizontalAlignment(SwingConstants.CENTER);
		lblGram.setFont(new Font("굴림", Font.BOLD, 14));
		lblGram.setBounds(145, 112, 61, 42);
		pn2.add(lblGram);
		
		textChoiceFood = new JTextField();
		textChoiceFood.setBackground(new Color(241, 237, 231));
		textChoiceFood.setFont(new Font("굴림", Font.BOLD, 13));
		textChoiceFood.setEditable(false);
		textChoiceFood.setHorizontalAlignment(SwingConstants.CENTER);
		textChoiceFood.setBounds(30, 42, 293, 37);
		pn2.add(textChoiceFood);
		textChoiceFood.setColumns(10);
		
		lblIntake = new JLabel("▶ 섭취량");
		lblIntake.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntake.setFont(new Font("굴림", Font.BOLD, 14));
		lblIntake.setBounds(0, 84, 126, 42);
		pn2.add(lblIntake);
		
		JLabel lblChoiceFood = new JLabel("▶ 선택한 음식");
		lblChoiceFood.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceFood.setFont(new Font("굴림", Font.BOLD, 14));
		lblChoiceFood.setBounds(0, 10, 155, 42);
		pn2.add(lblChoiceFood);
		
		tableCellAlign(tbl);
		tbl.getColumnModel().getColumn(0).setMaxWidth(30);
		tbl.getColumnModel().getColumn(2).setMaxWidth(80);
		tbl.getColumnModel().getColumn(3).setMaxWidth(80);

		/*--------------------------------------------*/
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAddFood.setText("");
				textChoiceFood.setText("");
				foodTemp = "";
				mVO.setIntakeMul(0);
				System.out.println("다 지워졌니..?"+ mVO);

			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MealInput();
			}
		});
		
		btnChoiceFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				Vector vdata = (Vector)vData.get(row);
				
				textChoiceFood.setText(vdata.get(1).toString());
			}
		});
		
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
				String choiceFood = textChoiceFood.getText();
				String intake = textIntake.getText();
				
				foodTemp += choiceFood + "\n";
				
				fVO = dao.getFoodSearch(choiceFood);
				ateFoodKcal += fVO.getKcal()*Double.parseDouble(intake);
				textAddFood.setText(foodTemp);
				mVO.setaMealKcal(ateFoodKcal);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textAddFood.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "음식을 추가하세요.");
				}
				else {
					mVO.setMealTime(mealTime);
					int res = 0;
					String temp = "";
					
					String[] foodTemps = textAddFood.getText().split("\n");
					
					for(String f : foodTemps) {
						temp += f + "/";
					}
					mVO.setMealMenu(temp.substring(0,temp.length()-1));
					
					res = dao.setMealMenuInput(mVO);
					if(res == 0) JOptionPane.showMessageDialog(null, "저장 실패! 다시 시도하세요.");
					else JOptionPane.showMessageDialog(null, "저장 성공!");
					
					System.out.println("다 들어있니..?"+ mVO);
					dispose();
					new MealInput(mVO);
				}
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
}
