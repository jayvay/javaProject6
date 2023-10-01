package imsi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import meal.DAO;
import meal.FoodInput;
import meal.FoodSearchDetail;
import meal.FoodVO;
import meal.MainMenu;

public class MealInput2 extends JFrame {

	private JPanel contentPane;
	private JFileChooser chooser;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	DAO dao = new DAO();
	FoodVO fVO = new FoodVO();
	
	public MealInput2() {
		this(null);
	}
	
	public MealInput2(FoodVO fVO) {
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
		pn2.setBounds(0, 294, 446, 267);
		contentPane.add(pn2);
		pn2.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(137, 44, 169, 28);
		pn2.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("식사종류");
		lblNewLabel.setBounds(0, 2, 125, 39);
		pn2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JRadioButton rdbtnBkft= new JRadioButton("아침");
		rdbtnBkft.setBounds(137, 10, 61, 23);
		buttonGroup.add(rdbtnBkft);
		pn2.add(rdbtnBkft);
		rdbtnBkft.setFont(new Font("굴림", Font.PLAIN, 14));
		
		JRadioButton rdbtnLunch = new JRadioButton("점심");
		rdbtnLunch.setBounds(206, 10, 61, 23);
		buttonGroup.add(rdbtnLunch);
		pn2.add(rdbtnLunch);
		rdbtnLunch.setFont(new Font("굴림", Font.PLAIN, 14));
		
		JRadioButton rdbtnDinner = new JRadioButton("저녁");
		rdbtnDinner.setBounds(283, 10, 61, 23);
		buttonGroup.add(rdbtnDinner);
		pn2.add(rdbtnDinner);
		rdbtnDinner.setFont(new Font("굴림", Font.PLAIN, 14));
		
		JRadioButton rdbtnSnack = new JRadioButton("간식");
		rdbtnSnack.setBounds(353, 10, 61, 23);
		buttonGroup.add(rdbtnSnack);
		pn2.add(rdbtnSnack);
		rdbtnSnack.setFont(new Font("굴림", Font.PLAIN, 14));
		
		JLabel lblMealTime = new JLabel("식사시간");
		lblMealTime.setBounds(0, 39, 125, 39);
		pn2.add(lblMealTime);
		lblMealTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMealTime.setFont(new Font("굴림", Font.PLAIN, 14));
		
		JPanel pn3 = new JPanel();
		pn3.setBounds(443, 294, 341, 267);
		contentPane.add(pn3);
		pn3.setLayout(null);
		
		JList list = new JList();
		list.setBounds(33, 82, 273, 160);
		pn2.add(list);
		
		if(fVO != null) {
			Vector vData = new Vector<>();
			vData.add(fVO);
			list.setListData(vData);
			
			JButton btnListDetail = new JButton("자세히보기");
			btnListDetail.setBounds(318, 214, 96, 28);
			pn2.add(btnListDetail);
			
			btnListDetail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new FoodSearchDetail(fVO);
					
				}
			});
		}

		JButton btnFoodInput = new JButton("새 음식 등록");
		btnFoodInput.setBounds(12, 10, 317, 40);
		pn3.add(btnFoodInput);
		
		JButton btnFoodSearch = new JButton("음식 찾기");
		btnFoodSearch.setBounds(12, 60, 317, 40);
		pn3.add(btnFoodSearch);
		
		JPanel pn1 = new JPanel();
		pn1.setBackground(new Color(154, 149, 191));
		pn1.setBounds(0, 0, 784, 292);
		contentPane.add(pn1);
		pn1.setLayout(null);
		
		JLabel lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(30, 61, 421, 221);
		pn1.add(lblImage);
		
		JButton btnUpload = new JButton("사진 불러오기");
		btnUpload.setBounds(30, 10, 127, 30);
		pn1.add(btnUpload);
		
		JButton btnExit = new JButton("나가기");
		btnExit.setFont(new Font("굴림", Font.PLAIN, 17));
		btnExit.setBounds(670, 10, 102, 41);
		pn1.add(btnExit);
		
		JLabel lblNewLabel_1_1 = new JLabel("하루 총 섭취 칼로리");
		lblNewLabel_1_1.setBounds(539, 152, 207, 39);
		pn1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("하루 목표 섭취 칼로리");
		lblNewLabel_1_1_1.setBounds(549, 201, 198, 39);
		pn1.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.PLAIN, 14));
		
		/*---------------------------------------------------------*/

		btnFoodInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FoodInput();
			}
		});
		

		btnFoodSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				new FoodSearch(mVO);
			}
		});
		
		
	//그림 업로드처리
			btnUpload.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chooser= new JFileChooser(); // 파일 다이얼로그 생성
					
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
	            "JPG & GIF Images", // 파일 이름 창에 출력될 문자열
	            "jpg", "gif"); // 파일 필터로 사용되는 확장자. *.jpg. *.gif만 나열됨
					chooser.setFileFilter(filter); // 파일 다이얼로그에 파일 필터 설정
					
					// 파일 다이얼로그 출력
					int ret = chooser.showOpenDialog(null);
					if(ret != JFileChooser.APPROVE_OPTION) { // 사용자가  창을 강제로 닫았거나 취소 버튼을 누른 경우
				    JOptionPane.showMessageDialog(null,"파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
					}
					else {
					  // 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
					  String filePath = chooser.getSelectedFile().getPath(); // 파일 경로명을 알아온다.
					  //lblImage.setIcon(new ImageIcon(filePath)); // 파일을 로딩하여 이미지 레이블에 출력한다.
					  //System.out.println("filePath : " + filePath);
					  //pack(); // 이미지의 크기에 맞추어 프레임의 크기 조절
					  
					  // 이미지 크기 조절
						ImageIcon icon = new ImageIcon(filePath.toString());
						
						// 이미지의 크기를 조절..하기위해 새롭게 불러와서 Image객체로 만든다.
						Image img = icon.getImage();
						
						// 추출된 이미지의 크기를 조정하여 새로운 Image객체로 생성시켜준다.
						Image updateImg = img.getScaledInstance(319, 245, Image.SCALE_SMOOTH);
						
						// 새로운 Image객체로 ImageIcon객체 생성
						ImageIcon updateIcon = new ImageIcon(updateImg);
						
						lblImage.setIcon(updateIcon);
					  
					  
					  // 파일 업로드하기
					  try {
							//URL imgURL = new URL(imageURL);		// URL을 통한 파일 가져오기
					  	File imageFile = new File(filePath);	// 파일시스템에서 가져온 정보로 파일객체 생성하기
							String fileName = filePath.substring(filePath.lastIndexOf("\\")+1); // 파일명+확장자 구하기
							String extension = filePath.substring(filePath.lastIndexOf(".")+1); // 확장자 구하기
							//System.out.println("fileName : " + fileName);
							
							BufferedImage image = ImageIO.read(imageFile);
							File file = new File("myImage/" + fileName);
							if(!file.exists()) { // 해당 경로의 폴더가 존재하지 않을 경우
								file.mkdirs(); // 해당 경로의 폴더 생성
							}
							
							ImageIO.write(image, extension, file); // image를 file로 업로드
							System.out.println("이미지 업로드 완료!");
							JOptionPane.showMessageDialog(null, "이미지 업로드 완료!!");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					  
					}
				}
			});
			

			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new MainMenu();
				}
			});
	}
}
