package meal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class FoodInput extends JFrame {

	private JPanel contentPane, pn1, pn2;
	private JLabel lblTitle, lblFoodName, lblProductName, lblCalorie, lblIntake, lblKcal;
	private JLabel lblCarbohydrate, lblDietaryFiber, lblSugars, lblProtein, lblFat, lblSaturatedFat, lblNatrium;
	private JTextField textFoodName, textProductName, textKcal, textIntake;
	private JTextField textCarbohydrate, textDietaryFiber, textSugars, textProtein, textFat, textSaturatedFat, textNatrium;
	private JLabel lblGram1, lblGram2, lblGram3, lblGram4, lblGram5, lblGram6, lblMilligram;
	private JComboBox comboBox;	
	private JButton btnEnter, btnCancel;
	int res = 0;

	DAO dao = new DAO();
	FoodVO fVO = null;
	
	public FoodInput() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("음식 등록하기");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pn1 = new JPanel();
		pn1.setBounds(0, 0, 784, 61);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		lblTitle = new JLabel("음식 등록");
		lblTitle.setFont(new Font("굴림", Font.PLAIN, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 12, 784, 41);
		pn1.add(lblTitle);
		
		pn2 = new JPanel();
		pn2.setBounds(0, 59, 784, 502);
		contentPane.add(pn2);
		pn2.setLayout(null);

		textIntake = new JTextField();
		textIntake.setColumns(10);
		textIntake.setBounds(156, 255, 140, 36);
		pn2.add(textIntake);
		
		textKcal = new JTextField();
		textKcal.setBounds(586, 40, 110, 36);
		pn2.add(textKcal);
		textKcal.setColumns(10);
		
		textCarbohydrate = new JTextField();
		textCarbohydrate.setBounds(586, 86, 110, 36);
		pn2.add(textCarbohydrate);
		textCarbohydrate.setColumns(10);
		
		textDietaryFiber = new JTextField();
		textDietaryFiber.setBounds(586, 133, 110, 36);
		textDietaryFiber.setColumns(10);
		pn2.add(textDietaryFiber);
		
		textSugars = new JTextField();
		textSugars.setBounds(586, 181, 110, 36);
		textSugars.setColumns(10);
		pn2.add(textSugars);
		
		textProtein = new JTextField();
		textProtein.setBounds(586, 227, 110, 36);
		textProtein.setColumns(10);
		pn2.add(textProtein);
		
		textFat = new JTextField();
		textFat.setBounds(586, 273, 110, 36);
		textFat.setColumns(10);
		pn2.add(textFat);
		
		textSaturatedFat = new JTextField();
		textSaturatedFat.setBounds(586, 321, 110, 36);
		textSaturatedFat.setColumns(10);
		pn2.add(textSaturatedFat);
		
		textNatrium = new JTextField();
		textNatrium.setBounds(586, 367, 110, 36);
		textNatrium.setColumns(10);
		pn2.add(textNatrium);
		
		lblCalorie = new JLabel("칼로리");
		lblCalorie.setBounds(453, 40, 113, 36);
		lblCalorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalorie.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblCalorie);
		
		lblCarbohydrate = new JLabel("탄수화물");
		lblCarbohydrate.setBounds(453, 86, 113, 36);
		lblCarbohydrate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarbohydrate.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblCarbohydrate);
		
		lblDietaryFiber = new JLabel("식이섬유");
		lblDietaryFiber.setBounds(453, 133, 113, 36);
		lblDietaryFiber.setHorizontalAlignment(SwingConstants.CENTER);
		lblDietaryFiber.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblDietaryFiber);
		
		lblSugars = new JLabel("당류");
		lblSugars.setBounds(453, 181, 113, 36);
		lblSugars.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugars.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblSugars);
		
		lblProtein = new JLabel("단백질");
		lblProtein.setBounds(453, 227, 113, 36);
		lblProtein.setHorizontalAlignment(SwingConstants.CENTER);
		lblProtein.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblProtein);
		
		lblFat = new JLabel("지방");
		lblFat.setBounds(453, 273, 113, 36);
		lblFat.setHorizontalAlignment(SwingConstants.CENTER);
		lblFat.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblFat);
		
		lblSaturatedFat = new JLabel("포화지방");
		lblSaturatedFat.setBounds(453, 321, 113, 36);
		lblSaturatedFat.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaturatedFat.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblSaturatedFat);
		
		lblNatrium = new JLabel("나트륨");
		lblNatrium.setBounds(453, 367, 113, 36);
		lblNatrium.setHorizontalAlignment(SwingConstants.CENTER);
		lblNatrium.setFont(new Font("굴림", Font.PLAIN, 14));
		pn2.add(lblNatrium);
		
		lblIntake = new JLabel("섭취량");
		lblIntake.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntake.setFont(new Font("굴림", Font.PLAIN, 14));
		lblIntake.setBounds(53, 255, 113, 36);
		pn2.add(lblIntake);
		
		lblKcal = new JLabel("kcal");
		lblKcal.setHorizontalAlignment(SwingConstants.CENTER);
		lblKcal.setFont(new Font("굴림", Font.PLAIN, 14));
		lblKcal.setBounds(699, 39, 34, 36);
		pn2.add(lblKcal);
		
		lblGram1 = new JLabel("g");
		lblGram1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGram1.setFont(new Font("굴림", Font.PLAIN, 16));
		lblGram1.setBounds(699, 85, 25, 36);
		pn2.add(lblGram1);
		
		lblGram2 = new JLabel("g");
		lblGram2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGram2.setFont(new Font("굴림", Font.PLAIN, 16));
		lblGram2.setBounds(699, 132, 25, 36);
		pn2.add(lblGram2);
		
		lblGram3 = new JLabel("g");
		lblGram3.setHorizontalAlignment(SwingConstants.CENTER);
		lblGram3.setFont(new Font("굴림", Font.PLAIN, 16));
		lblGram3.setBounds(699, 181, 25, 36);
		pn2.add(lblGram3);
		
		lblGram4 = new JLabel("g");
		lblGram4.setHorizontalAlignment(SwingConstants.CENTER);
		lblGram4.setFont(new Font("굴림", Font.PLAIN, 16));
		lblGram4.setBounds(699, 227, 25, 36);
		pn2.add(lblGram4);
		
		lblGram5 = new JLabel("g");
		lblGram5.setHorizontalAlignment(SwingConstants.CENTER);
		lblGram5.setFont(new Font("굴림", Font.PLAIN, 16));
		lblGram5.setBounds(699, 273, 25, 36);
		pn2.add(lblGram5);
		
		lblGram6 = new JLabel("g");
		lblGram6.setHorizontalAlignment(SwingConstants.CENTER);
		lblGram6.setFont(new Font("굴림", Font.PLAIN, 16));
		lblGram6.setBounds(699, 319, 25, 36);
		pn2.add(lblGram6);
		
		lblMilligram = new JLabel("mg");
		lblMilligram.setHorizontalAlignment(SwingConstants.CENTER);
		lblMilligram.setFont(new Font("굴림", Font.PLAIN, 16));
		lblMilligram.setBounds(699, 367, 34, 36);
		pn2.add(lblMilligram);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("굴림", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"g", "인분"}));
		comboBox.setBounds(301, 255, 63, 36);
		pn2.add(comboBox);
		
		lblFoodName = new JLabel("음식 이름");
		lblFoodName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoodName.setFont(new Font("굴림", Font.PLAIN, 14));
		lblFoodName.setBounds(53, 144, 113, 45);
		pn2.add(lblFoodName);
		
		textFoodName = new JTextField();
		textFoodName.setBounds(156, 144, 257, 42);
		pn2.add(textFoodName);
		textFoodName.setColumns(10);
		
		lblProductName = new JLabel("상품명");
		lblProductName.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductName.setFont(new Font("굴림", Font.PLAIN, 14));
		lblProductName.setBounds(53, 196, 113, 45);
		pn2.add(lblProductName);
		
		textProductName = new JTextField();
		textProductName.setColumns(10);
		textProductName.setBounds(156, 198, 257, 42);
		pn2.add(textProductName);
		
		btnEnter = new JButton("등 록");
		btnEnter.setFont(new Font("굴림", Font.PLAIN, 14));
		btnEnter.setBounds(23, 449, 359, 36);
		pn2.add(btnEnter);
		
		btnCancel = new JButton("취 소");
		btnCancel.setFont(new Font("굴림", Font.PLAIN, 14));
		btnCancel.setBounds(394, 449, 359, 36);
		pn2.add(btnCancel);
		
		/*----------------------------------------------------*/

		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String foodName = textFoodName.getText();
				String productName = textProductName.getText();
				String intake = textIntake.getText();
				String kcal = textKcal.getText();
				String carbohydrate = textCarbohydrate.getText();
				String dietaryFiber = textDietaryFiber.getText();
				String sugars = textSugars.getText();
				String protein = textProtein.getText();
				String fat = textFat.getText();
				String saturatedFat = textSaturatedFat.getText();
				String natrium = textNatrium.getText();
				
				fVO = dao.getFoodSearch(productName);
				if(fVO.getFoodName() != null) {
					JOptionPane.showMessageDialog(null, "이미 등록된 음식입니다.");
					textFoodName.requestFocus();
				}
				else {
					fVO.setFoodName(foodName);
					fVO.setProductName(productName);
					fVO.setIntake(Double.parseDouble(intake));
					fVO.setKcal(Double.parseDouble(kcal));
					fVO.setCarbohydrate(Double.parseDouble(carbohydrate));
					fVO.setDietaryFiber(Double.parseDouble(dietaryFiber));
					fVO.setSugars(Double.parseDouble(sugars));
					fVO.setProtein(Double.parseDouble(protein));
					fVO.setFat(Double.parseDouble(fat));
					fVO.setSaturatedFat(Double.parseDouble(saturatedFat));
					fVO.setNatrium(Double.parseDouble(natrium));
					
					res = dao.setFoodInput(fVO);
					if(res == 0) {
						JOptionPane.showMessageDialog(null, "등록 실패!");
					} else {
						JOptionPane.showMessageDialog(null, "음식이 등록되었습니다.");
						dispose();
					}
				}
			
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MealInput();
			}
		});
		
		
	}
}
