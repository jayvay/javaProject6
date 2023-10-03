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
	MealVO mVO = null;
	
	String foodTemp = "";
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
		
		JButton btnDelete = new JButton("삭 제");
		btnDelete.setBounds(335, 384, 133, 51);
		pn2.add(btnDelete);
		
		JButton btnExit = new JButton("나가기");
		btnExit.setBounds(561, 382, 133, 54);
		btnExit.setFont(new Font("굴림", Font.PLAIN, 12));
		pn2.add(btnExit);
		
		title = new Vector<>();
		title.add("번호");
		title.add("상품명");
		title.add("음식 이름");
		title.add("칼로리");

		JScrollPane scroll = new JScrollPane(tbl);
		scroll.setBounds(165, 40, 452, 283);
		pn2.add(scroll);

		
		vData = dao.getMyFoodList();
		dtm = new DefaultTableModel(vData, title);
		tbl = new JTable(dtm);
		scroll.setViewportView(tbl);
		pn2.add(scroll);
		
		JButton btnFoodDetail = new JButton("상세정보");
		btnFoodDetail.setBounds(84, 384, 171, 51);
		pn2.add(btnFoodDetail);

		

		/*--------------------------------------------*/

		btnFoodDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tbl.getSelectedRow();
				String productName = tbl.getValueAt(row, 1).toString();
				fVO = dao.getFoodSearch(productName);
				if(fVO.getProductName() != null) {
					new FoodSearchDetail(fVO);
				}
			}
		});
	}
}
