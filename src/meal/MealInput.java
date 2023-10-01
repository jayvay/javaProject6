package meal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MealInput extends JFrame {

	private JPanel contentPane;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	private JTextField textAMealKcal, textDayKcal;
	
	Vector title, vData;
	DefaultTableModel dtm;
	
	DAO dao = new DAO();
	FoodVO fVO = new FoodVO();
	MealVO mVO = null;
	int res = 0;

	
	public MealInput() {
		this(null);
	}
	
	public MealInput(MealVO mVO2) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("식단 등록하기");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn2 = new JPanel();
		pn2.setBackground(new Color(250, 230, 194));
		pn2.setBounds(0, 329, 784, 232);
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
		cbYY.setBounds(155, 80, 70, 28);
		mVO = defaultMealTime();
		cbYY.setSelectedItem(mVO.getStrYY());
		pn2.add(cbYY);
		
		JLabel lblNewLabel = new JLabel("식사종류");
		lblNewLabel.setBounds(28, 20, 125, 39);
		pn2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JRadioButton rdbtnBkft= new JRadioButton("아침");
		rdbtnBkft.setBackground(new Color(250, 230, 194));
		rdbtnBkft.setBounds(166, 28, 61, 23);
		buttonGroup.add(rdbtnBkft);
		pn2.add(rdbtnBkft);
		rdbtnBkft.setFont(new Font("굴림", Font.BOLD, 14));
		
		JRadioButton rdbtnLunch = new JRadioButton("점심");
		rdbtnLunch.setBackground(new Color(250, 230, 194));
		rdbtnLunch.setBounds(235, 28, 61, 23);
		buttonGroup.add(rdbtnLunch);
		pn2.add(rdbtnLunch);
		rdbtnLunch.setFont(new Font("굴림", Font.BOLD, 14));
		
		JRadioButton rdbtnDinner = new JRadioButton("저녁");
		rdbtnDinner.setBackground(new Color(250, 230, 194));
		rdbtnDinner.setBounds(312, 28, 61, 23);
		buttonGroup.add(rdbtnDinner);
		pn2.add(rdbtnDinner);
		rdbtnDinner.setFont(new Font("굴림", Font.BOLD, 14));
		
		JRadioButton rdbtnSnack = new JRadioButton("간식");
		rdbtnSnack.setBackground(new Color(250, 230, 194));
		rdbtnSnack.setBounds(382, 28, 61, 23);
		buttonGroup.add(rdbtnSnack);
		pn2.add(rdbtnSnack);
		rdbtnSnack.setFont(new Font("굴림", Font.BOLD, 14));
		
		JLabel lblMealTime = new JLabel("식사시간");
		lblMealTime.setBounds(28, 77, 125, 39);
		pn2.add(lblMealTime);
		lblMealTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMealTime.setFont(new Font("굴림", Font.BOLD, 14));
		
		JButton btnSave = new JButton("저 장");
		btnSave.setFont(new Font("굴림", Font.BOLD, 13));
		btnSave.setBounds(651, 20, 103, 51);
		pn2.add(btnSave);
		
		JComboBox cbMM = new JComboBox(MM);
		cbMM.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		cbMM.setBackground(new Color(250, 230, 194));
		cbMM.setBounds(260, 80, 61, 28);
		mVO = defaultMealTime();
		cbMM.setSelectedItem(mVO.getStrMM());
		pn2.add(cbMM);
		
		JComboBox cbDD = new JComboBox(dd);
		cbDD.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		cbDD.setBackground(new Color(250, 230, 194));
		cbDD.setBounds(351, 80, 61, 28);
		mVO = defaultMealTime();
		cbDD.setSelectedItem(mVO.getStrDD());
		pn2.add(cbDD);
		
		JLabel lblYY = new JLabel("년");
		lblYY.setFont(new Font("굴림", Font.BOLD, 14));
		lblYY.setHorizontalAlignment(SwingConstants.CENTER);
		lblYY.setBounds(217, 77, 38, 35);
		pn2.add(lblYY);
		
		JLabel lblMM = new JLabel("월");
		lblMM.setFont(new Font("굴림", Font.BOLD, 14));
		lblMM.setHorizontalAlignment(SwingConstants.CENTER);
		lblMM.setBounds(313, 77, 38, 35);
		pn2.add(lblMM);
		
		JLabel lblDD = new JLabel("일");
		lblDD.setFont(new Font("굴림", Font.BOLD, 14));
		lblDD.setHorizontalAlignment(SwingConstants.CENTER);
		lblDD.setBounds(406, 77, 38, 35);
		pn2.add(lblDD);
		
		JComboBox cbHH = new JComboBox(hh);
		cbHH.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		cbHH.setBackground(new Color(250, 230, 194));
		cbHH.setBounds(444, 80, 50, 28);
		mVO = defaultMealTime();
		cbHH.setSelectedItem(mVO.getStrHH());
		pn2.add(cbHH);
		
		JComboBox cbmm = new JComboBox(mm);
		cbmm.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		cbmm.setBackground(new Color(250, 230, 194));
		cbmm.setBounds(529, 80, 50, 28);
		mVO = defaultMealTime();
		cbmm.setSelectedItem(mVO.getStrmm());
		pn2.add(cbmm);
		
		JLabel lblHH = new JLabel("시");
		lblHH.setFont(new Font("굴림", Font.BOLD, 14));
		lblHH.setHorizontalAlignment(SwingConstants.CENTER);
		lblHH.setBounds(490, 77, 38, 35);
		pn2.add(lblHH);
		
		JLabel lblmm = new JLabel("분");
		lblmm.setFont(new Font("굴림", Font.BOLD, 14));
		lblmm.setHorizontalAlignment(SwingConstants.CENTER);
		lblmm.setBounds(574, 77, 38, 35);
		pn2.add(lblmm);
		
		JPanel pn1 = new JPanel();
		pn1.setBackground(new Color(255, 255, 255));
		pn1.setBounds(0, 83, 784, 249);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JButton btnFoodSearch = new JButton("음식 찾기");
		btnFoodSearch.setFont(new Font("굴림", Font.BOLD, 13));
		btnFoodSearch.setBackground(new Color(250, 230, 194));
		btnFoodSearch.setBounds(552, 122, 180, 65);
		pn1.add(btnFoodSearch);
		
		JButton btnFoodInput = new JButton("새 음식 등록");
		btnFoodInput.setFont(new Font("굴림", Font.BOLD, 13));
		btnFoodInput.setBackground(new Color(250, 230, 194));
		btnFoodInput.setBounds(552, 34, 180, 65);
		pn1.add(btnFoodInput);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 51, 350, 119);
		pn1.add(scrollPane);
		
		JTextArea textAreaSelFoods = new JTextArea();
		textAreaSelFoods.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(textAreaSelFoods);
		textAreaSelFoods.setText(mVO.getMealMenu());
		
		JButton btnF5 = new JButton("새로고침");
		btnF5.setFont(new Font("굴림", Font.BOLD, 13));
		btnF5.setForeground(new Color(255, 255, 255));
		btnF5.setBackground(new Color(149, 0, 74));
		btnF5.setBounds(48, 51, 89, 119);
		pn1.add(btnF5);
		
		JLabel lblImg = new JLabel("New label");
		lblImg.setBounds(0, 0, 784, 265);
		pn1.add(lblImg);
		ImageIcon imgBack = new ImageIcon(getClass().getResource("./images/cake.png"));
		imgBack =	imageSetSize(imgBack, 800, 700);
		lblImg.setIcon(imgBack);
		
		JButton btnDelete = new JButton("다시입력");
		btnDelete.setFont(new Font("굴림", Font.BOLD, 13));
		btnDelete.setBounds(651, 80, 103, 51);
		pn2.add(btnDelete);
		
		JButton btnExit = new JButton("나가기");
		btnExit.setBounds(651, 141, 103, 54);
		pn2.add(btnExit);
		btnExit.setFont(new Font("굴림", Font.BOLD, 12));
		
		JLabel lblAMealKcal = new JLabel("한 끼 총 섭취 칼로리");
		lblAMealKcal.setBounds(97, 123, 207, 39);
		pn2.add(lblAMealKcal);
		lblAMealKcal.setHorizontalAlignment(SwingConstants.CENTER);
		lblAMealKcal.setFont(new Font("굴림", Font.BOLD, 14));
		
		textAMealKcal = new JTextField();
		textAMealKcal.setFont(new Font("Arial", Font.BOLD, 13));
		textAMealKcal.setBackground(new Color(250, 230, 194));
		textAMealKcal.setBounds(127, 159, 146, 30);
		pn2.add(textAMealKcal);
		textAMealKcal.setColumns(10);
		
		JLabel lblDayKcal = new JLabel("하루 총 섭취 칼로리");
		lblDayKcal.setBounds(324, 123, 207, 39);
		pn2.add(lblDayKcal);
		lblDayKcal.setHorizontalAlignment(SwingConstants.CENTER);
		lblDayKcal.setFont(new Font("굴림", Font.BOLD, 14));
		
		textDayKcal = new JTextField();
		textDayKcal.setFont(new Font("Arial", Font.BOLD, 13));
		textDayKcal.setBackground(new Color(250, 230, 194));
		textDayKcal.setBounds(351, 159, 146, 30);
		pn2.add(textDayKcal);
		textDayKcal.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(242, 202, 134));
		panel.setBounds(0, 0, 784, 84);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		/*----------------------------------------------------------*/

		btnF5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mVO = dao.getMealSearch(mVO2);
				
				String temp = "";
				String[] foodTemps = mVO.getMealMenu().split("/");
				for(String f : foodTemps) {
					temp += f + "\n";
				}
				textAreaSelFoods.setText(temp);
			}
		});
		
		btnFoodSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mealTime = cbYY.getSelectedItem() + "-" + cbMM.getSelectedItem() + "-" + cbDD.getSelectedItem()
				+ " " + cbHH.getSelectedItem() + ":" + cbmm.getSelectedItem();
				dispose();
				new FoodSearch(mealTime);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MealVO> vos = null;
				String meal = "";
				String mealTime = cbYY.getSelectedItem() + "-" + cbMM.getSelectedItem() + "-" + cbDD.getSelectedItem()
				+ " " + cbHH.getSelectedItem() + ":" + cbmm.getSelectedItem();
				
				if(!(rdbtnBkft.isSelected()||rdbtnLunch.isSelected()||rdbtnDinner.isSelected()||rdbtnSnack.isSelected())) {
					JOptionPane.showMessageDialog(null, "식사 종류를 선택하세요");
					rdbtnBkft.requestFocus();
				}
				else {
					if(rdbtnBkft.isSelected()) meal = "아침";
					else if(rdbtnLunch.isSelected()) meal = "점심";
					else if(rdbtnDinner.isSelected()) meal = "저녁";
					else if(rdbtnSnack.isSelected()) meal = "간식";
					
					mVO.setMeal(meal);
					mVO.setMealTime(mealTime);
					
					double aMealKcal = 0.0;
					String[] foods = textAreaSelFoods.getText().split("\n");
					for(int i=0; i<foods.length; i++) {
						fVO = dao.getFoodSearch(foods[i]);
						if(fVO.getProductName() != null) {
							aMealKcal += fVO.getKcal();
						}
					}
					System.out.println("한끼칼로리:" + aMealKcal);
					textAMealKcal.setText(String.valueOf(aMealKcal));
					mVO.setaMealKcal(aMealKcal);
					res = dao.setMealInput(mVO);
					if(res == 0) JOptionPane.showMessageDialog(null, "입력 오류 발생! 다시 시도하세요.");
					
					String mealDate = cbYY.getSelectedItem() + "-" + cbMM.getSelectedItem() + "-" + cbDD.getSelectedItem();
					vos = dao.getDaySearch(mealDate);
					double dayKcal = 0;
					for(int i=0; i<vos.size(); i++) {
						mVO = vos.get(i);
						dayKcal += mVO.getaMealKcal();
					}
					textDayKcal.setText(String.valueOf(dayKcal));
					mVO.setDayKcal(dayKcal);	
					
					res = dao.setMealInput(mVO);
					if(res == 0) JOptionPane.showMessageDialog(null, "입력 오류 발생! 다시 시도하세요.");
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "다시 입력", "입력 중인 식단을 삭제하시겠습니까?", JOptionPane.YES_NO_OPTION);
				mVO = dao.getMealSearch(mVO2);
				res = dao.setmealInputDelete();
				System.out.println(mVO.getmIdx());
				if(res == 0) JOptionPane.showMessageDialog(null, "오류 발생! 다시 시도하세요.");
				mVO = defaultMealTime();
				cbYY.setSelectedItem(mVO.getStrYY());
				cbMM.setSelectedItem(mVO.getStrMM());
				cbDD.setSelectedItem(mVO.getStrDD());
				cbHH.setSelectedItem(mVO.getStrHH());
				cbmm.setSelectedItem(mVO.getStrmm());
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
