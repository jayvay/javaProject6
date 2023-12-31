package imsi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import meal.DAO;
import meal.FoodInput;
import meal.FoodSearch;
import meal.FoodVO;
import meal.MainMenu;
import meal.MealVO;

public class MealInput3 extends JFrame {

	private JPanel contentPane;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JTextField textAMealKcal, textDayKcal;
	
	Vector title, vData;
	DefaultTableModel dtm;
	
	DAO dao = new DAO();
	FoodVO fVO = new FoodVO();
	MealVO mVO = null;
	//public MealVO mVO2 = null;
	
	int res = 0;

	
	public MealInput3(MealVO mVO2) {
		//this.mVO2 = mVO2;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bobmukja_오늘의식단");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn2 = new JPanel();
		pn2.setBackground(new Color(254, 239, 216));
		pn2.setBounds(0, 328, 784, 233);
		contentPane.add(pn2);
		pn2.setLayout(null);
	
		String[] yy = new String[24];
		String[] MM = new String[12];
		String[] dd = new String[31];
		String[] hh = new String[24];
		String[] mm = new String[60];
		
		int imsi;
		for(int i=0; i<yy.length; i++) {
			imsi = i + 2000;
			yy[i] =	imsi + "";
		}

		for(int i=0; i<MM.length; i++) {
			MM[i] =	(i+1) + "";
		}

		for(int i=0; i<dd.length; i++) {
			dd[i] =	(i+1) + "";
		}
		
		for(int i=0; i<hh.length; i++) {
			hh[i] =	i + "";
		}
		
		for(int i=0; i<mm.length; i++) {
			mm[i] =	i + "";
		}
		
		JComboBox cbYY = new JComboBox(yy);
		cbYY.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		cbYY.setBackground(new Color(250, 230, 194));
		cbYY.setBounds(155, 69, 70, 28);
		mVO = defaultMealTime();
		cbYY.setSelectedItem(mVO.getStrYY());
		pn2.add(cbYY);
		
		JLabel lblNewLabel = new JLabel("식사종류");
		lblNewLabel.setBounds(28, 20, 125, 39);
		pn2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JRadioButton rdbtnBkft= new JRadioButton("아침");
		rdbtnBkft.setBackground(new Color(254, 239, 216));
		rdbtnBkft.setBounds(166, 28, 61, 23);
		buttonGroup.add(rdbtnBkft);
		pn2.add(rdbtnBkft);
		rdbtnBkft.setFont(new Font("굴림", Font.BOLD, 14));
		
		JRadioButton rdbtnLunch = new JRadioButton("점심");
		rdbtnLunch.setBackground(new Color(254, 239, 216));
		rdbtnLunch.setBounds(235, 28, 61, 23);
		buttonGroup.add(rdbtnLunch);
		pn2.add(rdbtnLunch);
		rdbtnLunch.setFont(new Font("굴림", Font.BOLD, 14));
		
		JRadioButton rdbtnDinner = new JRadioButton("저녁");
		rdbtnDinner.setBackground(new Color(254, 239, 216));
		rdbtnDinner.setBounds(312, 28, 61, 23);
		buttonGroup.add(rdbtnDinner);
		pn2.add(rdbtnDinner);
		rdbtnDinner.setFont(new Font("굴림", Font.BOLD, 14));
		
		JRadioButton rdbtnSnack = new JRadioButton("간식");
		rdbtnSnack.setBackground(new Color(254, 239, 216));
		rdbtnSnack.setBounds(382, 28, 61, 23);
		buttonGroup.add(rdbtnSnack);
		pn2.add(rdbtnSnack);
		rdbtnSnack.setFont(new Font("굴림", Font.BOLD, 14));
		
		JLabel lblMealTime = new JLabel("식사시간");
		lblMealTime.setBounds(28, 66, 125, 39);
		pn2.add(lblMealTime);
		lblMealTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMealTime.setFont(new Font("굴림", Font.BOLD, 14));
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setForeground(new Color(88, 72, 48));
		btnSave.setBackground(new Color(244, 200, 134));
		btnSave.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnSave.setBounds(644, 20, 110, 51);
		pn2.add(btnSave);
		
		JComboBox cbMM = new JComboBox(MM);
		cbMM.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		cbMM.setBackground(new Color(250, 230, 194));
		cbMM.setBounds(260, 69, 61, 28);
		mVO = defaultMealTime();
		cbMM.setSelectedItem(mVO.getStrMM());
		pn2.add(cbMM);
		
		JComboBox cbDD = new JComboBox(dd);
		cbDD.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		cbDD.setBackground(new Color(250, 230, 194));
		cbDD.setBounds(351, 69, 61, 28);
		mVO = defaultMealTime();
		cbDD.setSelectedItem(mVO.getStrDD());
		pn2.add(cbDD);
		
		JLabel lblYY = new JLabel("년");
		lblYY.setFont(new Font("굴림", Font.BOLD, 14));
		lblYY.setHorizontalAlignment(SwingConstants.CENTER);
		lblYY.setBounds(217, 66, 38, 35);
		pn2.add(lblYY);
		
		JLabel lblMM = new JLabel("월");
		lblMM.setFont(new Font("굴림", Font.BOLD, 14));
		lblMM.setHorizontalAlignment(SwingConstants.CENTER);
		lblMM.setBounds(313, 66, 38, 35);
		pn2.add(lblMM);
		
		JLabel lblDD = new JLabel("일");
		lblDD.setFont(new Font("굴림", Font.BOLD, 14));
		lblDD.setHorizontalAlignment(SwingConstants.CENTER);
		lblDD.setBounds(406, 66, 38, 35);
		pn2.add(lblDD);
		
		JComboBox cbHH = new JComboBox(hh);
		cbHH.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		cbHH.setBackground(new Color(250, 230, 194));
		cbHH.setBounds(444, 69, 50, 28);
		mVO = defaultMealTime();
		cbHH.setSelectedItem(mVO.getStrHH());
		pn2.add(cbHH);
		
		JComboBox cbmm = new JComboBox(mm);
		cbmm.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		cbmm.setBackground(new Color(250, 230, 194));
		cbmm.setBounds(529, 69, 50, 28);
		mVO = defaultMealTime();
		cbmm.setSelectedItem(mVO.getStrmm());
		pn2.add(cbmm);
		
		JLabel lblHH = new JLabel("시");
		lblHH.setFont(new Font("굴림", Font.BOLD, 14));
		lblHH.setHorizontalAlignment(SwingConstants.CENTER);
		lblHH.setBounds(490, 66, 38, 35);
		pn2.add(lblHH);
		
		JLabel lblmm = new JLabel("분");
		lblmm.setFont(new Font("굴림", Font.BOLD, 14));
		lblmm.setHorizontalAlignment(SwingConstants.CENTER);
		lblmm.setBounds(574, 66, 38, 35);
		pn2.add(lblmm);
		
		JPanel pn1 = new JPanel();
		pn1.setBackground(new Color(255, 255, 255));
		pn1.setBounds(0, 80, 784, 249);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JButton btnFoodSearch = new JButton("음식 찾기");
		btnFoodSearch.setForeground(new Color(255, 255, 255));
		btnFoodSearch.setFont(new Font("굴림", Font.BOLD, 13));
		btnFoodSearch.setBackground(new Color(243, 174, 107));
		btnFoodSearch.setBounds(546, 137, 180, 65);
		pn1.add(btnFoodSearch);
		
		JButton btnFoodInput = new JButton("새 음식 등록");
		btnFoodInput.setForeground(new Color(255, 255, 255));
		btnFoodInput.setFont(new Font("굴림", Font.BOLD, 13));
		btnFoodInput.setBackground(new Color(243, 174, 107));
		btnFoodInput.setBounds(546, 49, 180, 65);
		pn1.add(btnFoodInput);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 68, 350, 119);
		pn1.add(scrollPane);
		
		JTextArea textAreaSelFoods = new JTextArea();
		textAreaSelFoods.setEditable(false);
		textAreaSelFoods.setFont(new Font("굴림", Font.BOLD, 14));
		scrollPane.setViewportView(textAreaSelFoods);
		textAreaSelFoods.setText(mVO.getMealMenu());
		
		JButton btnF5 = new JButton("새로고침");
		btnF5.setFont(new Font("굴림", Font.BOLD, 13));
		btnF5.setForeground(new Color(88, 72, 48));
		btnF5.setBackground(new Color(244, 200, 134));
		btnF5.setBounds(48, 68, 89, 119);
		pn1.add(btnF5);
		
		JLabel lblImg = new JLabel("New label");
		lblImg.setBounds(0, 0, 784, 265);
		pn1.add(lblImg);
		ImageIcon imgBack = new ImageIcon(getClass().getResource("./images/cake.png"));
		imgBack =	imageSetSize(imgBack, 800, 700);
		lblImg.setIcon(imgBack);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setForeground(new Color(88, 72, 48));
		btnReset.setBackground(new Color(244, 200, 134));
		btnReset.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnReset.setBounds(644, 80, 110, 51);
		pn2.add(btnReset);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setForeground(new Color(88, 72, 48));
		btnExit.setBackground(new Color(244, 200, 134));
		btnExit.setBounds(644, 141, 110, 54);
		pn2.add(btnExit);
		btnExit.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		JLabel lblAMealKcal = new JLabel("한 끼 총 섭취 칼로리");
		lblAMealKcal.setBounds(94, 129, 207, 39);
		pn2.add(lblAMealKcal);
		lblAMealKcal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAMealKcal.setFont(new Font("굴림", Font.BOLD, 14));
		
		textAMealKcal = new JTextField();
		textAMealKcal.setFont(new Font("Arial", Font.BOLD, 13));
		textAMealKcal.setBackground(new Color(254, 239, 216));
		textAMealKcal.setBounds(124, 165, 146, 30);
		pn2.add(textAMealKcal);
		textAMealKcal.setColumns(10);
		
		JLabel lblDayKcal = new JLabel("하루 총 섭취 칼로리");
		lblDayKcal.setBounds(321, 129, 207, 39);
		pn2.add(lblDayKcal);
		lblDayKcal.setHorizontalAlignment(SwingConstants.CENTER);
		lblDayKcal.setFont(new Font("굴림", Font.BOLD, 14));
		
		textDayKcal = new JTextField();
		textDayKcal.setFont(new Font("Arial", Font.BOLD, 13));
		textDayKcal.setBackground(new Color(254, 239, 216));
		textDayKcal.setBounds(348, 165, 146, 30);
		pn2.add(textDayKcal);
		textDayKcal.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(115, 94, 63));
		panel.setBounds(0, 0, 784, 89);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Diet record");
		lblNewLabel_1.setForeground(new Color(255, 242, 213));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblNewLabel_1.setBounds(0, 10, 784, 69);
		panel.add(lblNewLabel_1);
		
		
		/*----------------------------------------------------------*/
		
		btnFoodSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FoodSearch();
			}
		});
		

		btnF5.addActionListener(new ActionListener() {
			MealVO mVO2 = null;
			public void actionPerformed(ActionEvent e) {
				//mVO = dao.getMealSearch(mVO2, 0);
				System.out.println("mVO2로 다 왔니" + mVO2);
				
				String temp = "";
				String[] foodTemps = mVO2.getMealMenu().split("/");
				for(String f : foodTemps) {
					temp += f + "\n";
				}
				textAreaSelFoods.setText(temp);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ArrayList<MealVO> vos = null;
				String meal = "";
				String mealTime = cbYY.getSelectedItem() + "-" + cbMM.getSelectedItem() + "-" + cbDD.getSelectedItem()
				+ " " + cbHH.getSelectedItem() + ":" + cbmm.getSelectedItem();
				String mealDate = cbYY.getSelectedItem() + "-" + cbMM.getSelectedItem() + "-" + cbDD.getSelectedItem();
				
				if(!(rdbtnBkft.isSelected()||rdbtnLunch.isSelected()||rdbtnDinner.isSelected()||rdbtnSnack.isSelected())) {
					JOptionPane.showMessageDialog(null, "식사 종류를 선택하세요.");
					rdbtnBkft.requestFocus();
				}
				else {
					if(rdbtnBkft.isSelected()) meal = "아침";
					else if(rdbtnLunch.isSelected()) meal = "점심";
					else if(rdbtnDinner.isSelected()) meal = "저녁";
					else if(rdbtnSnack.isSelected()) meal = "간식";
					
					mVO2.setMeal(meal);
					mVO2.setMealTime(mealTime);
//					double aMealKcal = 0.0;
//					String[] foods = textAreaSelFoods.getText().split("\n");
//					for(int i=0; i<foods.length; i++) {
//						fVO = dao.getFoodSearch(foods[i]);
//						if(fVO.getProductName() != null) {
//							aMealKcal += fVO.getKcal()*intakeMul;
//						}
//					}
//					System.out.println("한끼칼로리:" + aMealKcal);
//					mVO.setaMealKcal(aMealKcal);
					
//					System.out.println("mVO 1: " + mVO);
//					System.out.println("mVO 1 time: " + mVO.getMealTime());
//					System.out.println("mVO 1 aMealKcal: " + mVO.getaMealKcal());
//					System.out.println();
					
//					res = dao.setMealInput(mVO2);
//					System.out.println("mVO-한끼칼로리까지:" + mVO);
//					if(res == 0) JOptionPane.showMessageDialog(null, "입력 오류 발생!1 다시 시도하세요.");
//					res = 0;
					
				
					
//					vos = dao.getDaySearch(mealDate);
//					double dayKcal = 0;
//					for(int i=0; i<vos.size(); i++) {
//						mVO = vos.get(i);
//						dayKcal += mVO.getaMealKcal();
//					}
//					textDayKcal.setText(String.valueOf(dayKcal));
					mVO = dao.getDayKcal(mealDate);
					mVO2.setDayKcal(mVO.getDayKcal());	
//					System.out.println("mVO2-데이칼로리 포함:" + mVO2);
//					System.out.println("mVO-데이칼로리 포함:" + mVO);
					
					textAMealKcal.setText(String.valueOf(mVO2.getaMealKcal()));
					textDayKcal.setText(String.valueOf(mVO2.getDayKcal()));
					
					MealVO mVO3 = dao.getMealSearch(mVO2, 1);
//					System.out.println("mVO3:" + mVO3);
					if(mVO3.getMeal() != null) {
						res = dao.getMealOverlapdelete(mVO2);
						if(res == 0) JOptionPane.showMessageDialog(null, "중복된 식사 삭제 오류 발생! 다시 시도하세요.");
					}
					
					res = dao.setMealInput(mVO);
					if(res == 0) JOptionPane.showMessageDialog(null, "입력 오류 발생! 다시 시도하세요.");
					System.out.println("필드에 있는 mVO:" + mVO);
					System.out.println("생성자 매개변수 mVO2:" + mVO2);
				}
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(null, "다시 입력", "입력 중인 식단을 삭제하시겠습니까?", JOptionPane.YES_NO_OPTION);
				if(ans == JOptionPane.YES_OPTION) {
//					mVO = dao.getMealSearch(mVO2, 0);
//					res = dao.setMealDelete(mVO, "temp");
//					System.out.println(mVO.getmIdx());
					if(res == 0) JOptionPane.showMessageDialog(null, "오류 발생! 다시 시도하세요.");
					mVO = defaultMealTime();
					cbYY.setSelectedItem(mVO.getStrYY());
					cbMM.setSelectedItem(mVO.getStrMM());
					cbDD.setSelectedItem(mVO.getStrDD());
					cbHH.setSelectedItem(mVO.getStrHH());
					cbmm.setSelectedItem(mVO.getStrmm());
					textAreaSelFoods.setText("");
					rdbtnBkft.setSelected(true);
				}
			}
		});
		
		btnFoodInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FoodInput();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu();
			}
		});
		
	}
	
	public MealVO defaultMealTime() {
		MealVO mVO = new MealVO();
		
		//캘린더 객체는 싱글톤 객체. 싱글톤 객체는 getInstance()로 부른다.
		Calendar cal = Calendar.getInstance();
		mVO.setStrYY(cal.get(Calendar.YEAR) + "");
		mVO.setStrMM(cal.get(Calendar.MONTH)+1 + "");
		mVO.setStrDD(cal.get(Calendar.DATE) + "");
		mVO.setStrHH(cal.get(Calendar.HOUR_OF_DAY) + "");
		mVO.setStrmm(cal.get(Calendar.MINUTE) + "");
		
		return mVO;
	}
	
	ImageIcon imageSetSize (ImageIcon icon, int i, int j) {	//이미지 크기 조절
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i , j, Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
}
