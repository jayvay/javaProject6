package meal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;

public class DAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	CompanionVO cVO = null;
	MealVO mVO = null;
	FoodVO fVO = null;
	
	String sql = "";
	int res = 0;
	
	public DAO() {
		String url = "jdbc:mysql://localhost:3306/javaProject6";	//mysql 서버 연결
		String user = "root";
		String Password = "1234";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, Password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패" + e.getMessage());
		}
	}
	
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	public void pstmtClose() {
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {}
	}
	
	public void rsClose() {
		try {
			if(rs != null) rs.close();			
		} catch (SQLException e) {}
		pstmtClose();
	}

	///////////////////////////////////////////////////
	
	public CompanionVO getCompSearch(String id) {	//회원정보찾기
		cVO = new CompanionVO();
		try {
			sql = "select * from companion where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cVO.setcIdx(rs.getInt("cIdx"));
				cVO.setId(rs.getString("id"));
				cVO.setPwd(rs.getString("pwd"));
				cVO.setName(rs.getString("name"));
				cVO.setAge(rs.getInt("age"));
				cVO.setGender(rs.getString("gender"));
				cVO.setEmail(rs.getString("email"));
				cVO.setTel(rs.getString("tel"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return cVO;
	}

	public int setCompInput(CompanionVO cVO) {	//회원가입
		res = 0;
		try {
			sql = "insert into companion values (default, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cVO.getId());
			pstmt.setString(2, cVO.getPwd());
			pstmt.setString(3, cVO.getName());
			pstmt.setInt(4, cVO.getAge());
			pstmt.setString(5, cVO.getGender());
			pstmt.setString(6, cVO.getEmail());
			pstmt.setString(7, cVO.getTel());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public Vector getMealList(String flag) {	//식사전체조회(jtable)
		Vector vData = new Vector<>();
		try {
			if(flag.equals("a")) sql = "select * from meal order by mealTime";
			else sql = "select * from meal order by mealTime desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int cnt = 0;	
			double aMealKcal = 0;
			double dayKcal = 0;	
			String strBeforeRow = "";
			String strNowRow ="";

			while(rs.next()) {
				Vector mVO = new Vector<>();
				mVO.add(rs.getString("mealTime").substring(10,16));	//시간
				mVO.add(rs.getString("meal"));
				mVO.add(rs.getDouble("aMealKcal"));
				mVO.add(rs.getString("mealMenu"));
				mVO.add(rs.getInt("mIdx"));
				
				strNowRow = rs.getString("mealTime").substring(0,10);	//날짜
				aMealKcal = rs.getDouble("aMealKcal");
				
				if(strBeforeRow.equals(strNowRow)) {
					dayKcal += aMealKcal;	//한끼를 누적해서 하루칼로리
					cnt++;
				} else {	
					Vector mVO2 = new Vector<>();	
					mVO2.add(strNowRow);
					mVO2.add("");	
					mVO2.add("");
					mVO2.add("");
					
					vData.add(mVO2);	//"YYYY-MM-DD"행을 테이블에 추가
					
					dayKcal = aMealKcal;	//한끼만 먹으면 한끼가 하루칼로리
					cnt = 1;
				}
				
				if(vData.size() > 0) {	//테이블에 1개라도 행이 있으면
					Vector vdata2 = (Vector)vData.get(vData.size() - cnt);	//테이블 (테이블크기-몇끼)행
					vdata2.set(2,dayKcal);																//에 4열에 하루칼로리를 넣는다.
				}
				
				strBeforeRow = strNowRow;	//다음 행으로 넘어가면 지금 행이 전 행이 된다.
				
				vData.add(mVO);	//한 행을 테이블에 넣는다.
			}	
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vData;
	}

	public FoodVO getFoodSearch(String productName) {	//음식상세정보
		fVO = new FoodVO();
		try {
			sql = "select * from food where productName = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				fVO.setfIdx(rs.getInt("fIdx"));
				fVO.setFoodName(rs.getString("foodName"));
				fVO.setProductName(rs.getString("productName"));
				fVO.setIntake(rs.getDouble("intake"));
				fVO.setKcal(rs.getDouble("kcal"));
				fVO.setCarbohydrate(rs.getDouble("carbohydrate"));
				fVO.setDietaryFiber(rs.getDouble("dietaryFiber"));
				fVO.setSugars(rs.getDouble("sugars"));
				fVO.setProtein(rs.getDouble("protein"));
				fVO.setFat(rs.getDouble("fat"));
				fVO.setSaturatedFat(rs.getDouble("saturatedFat"));
				fVO.setNatrium(rs.getDouble("natrium"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return fVO;
	}

	public int setFoodInput(FoodVO fVO) {	//새로운 음식등록
		res = 0;
		try {
			sql = "insert into food values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fVO.getFoodName());
			pstmt.setString(2, fVO.getProductName());
			pstmt.setDouble(3, fVO.getIntake());
			pstmt.setDouble(4, fVO.getKcal());
			pstmt.setDouble(5, fVO.getCarbohydrate());
			pstmt.setDouble(6, fVO.getDietaryFiber());
			pstmt.setDouble(7, fVO.getSugars());
			pstmt.setDouble(8, fVO.getProtein());
			pstmt.setDouble(9, fVO.getFat());
			pstmt.setDouble(10, fVO.getSaturatedFat());
			pstmt.setDouble(11, fVO.getNatrium());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public Vector getFoodList() {	//음식목록(jtable)
		Vector vData = new Vector<>();
		try {
			sql = "select * from food order by fIdx desc";
			//sql = "select m.*,f.foodName,f.productName from meal m, food f where m.fidx=f.fIdx order by mIdx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Vector fVO = new Vector<>();
				
				String foodName = rs.getString("foodName");
				int fIdx =rs.getInt("fIdx"); 
				
				fVO.add(fIdx);
				fVO.add(rs.getString("productName"));
				fVO.add(foodName);
				fVO.add(rs.getDouble("kcal"));
				vData.add(fVO);
				
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vData;
	}
	
	public ArrayList<FoodVO> getFoodList2() {
		ArrayList<FoodVO> vos = new ArrayList<FoodVO>();
		try {
			sql = "select * from food order by fIdx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				fVO = new FoodVO();
				fVO.setProductName(rs.getString("productName"));
				vos.add(fVO);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}
	

	public int setFoodUpdate(FoodVO fVO) {	//음식수정
		res = 0;
		try {
			sql = "update food set foodName=?, productName=?, intake=?, kcal=?, carbohydrate=?, dietaryFiber=?, sugars=?, protein=?, fat=?, saturatedFat=?, natrium=? where fIdx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fVO.getFoodName());
			pstmt.setString(2, fVO.getProductName());
			pstmt.setDouble(3, fVO.getIntake());
			pstmt.setDouble(4, fVO.getKcal());
			pstmt.setDouble(5, fVO.getCarbohydrate());
			pstmt.setDouble(6, fVO.getDietaryFiber());
			pstmt.setDouble(7, fVO.getSugars());
			pstmt.setDouble(8, fVO.getProtein());
			pstmt.setDouble(9, fVO.getFat());
			pstmt.setDouble(10, fVO.getSaturatedFat());
			pstmt.setDouble(11, fVO.getNatrium());
			pstmt.setInt(12, fVO.getfIdx());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setFoodDelete(String fIdx) {	//음식삭제
		res = 0;
		try {
			sql = "delete from food where fIdx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(fIdx));
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setMealMenuInput(MealVO mVO) {
		res = 0;
		try {
			sql = "insert into meal (mIdx, mealTime, mealMenu, aMealKcal) values (default, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVO.getMealTime());
			pstmt.setString(2, mVO.getMealMenu());
			pstmt.setDouble(3, mVO.getaMealKcal());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setMealInput(MealVO mVO) {
		res = 0;
		try {
//			if(i == 0) sql = "update meal set meal = ?, mealTime = ?, mealMenu = ?, aMealKcal = ? where mIdx = ?";
//			else if(i == 1) sql = "insert into meal (meal,mealTime,mealMenu,aMealKcal,midx) values (?,?,?,?,?)";
			sql = "insert into meal values (default,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVO.getMeal());
			pstmt.setString(2, mVO.getMealTime());
			pstmt.setString(3, mVO.getMealMenu());
			pstmt.setDouble(4, mVO.getaMealKcal());
//			pstmt.setDouble(5, mVO.getDayKcal());
//			pstmt.setInt(5, mVO.getmIdx());
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setMealDelete(MealVO mVO, String mealTemp) {
		res = 0;
		try {
			sql = "delete from meal where mIdx = ?";
			pstmt = conn.prepareStatement(sql);
			if(mealTemp.equals("temp")) pstmt.setInt(1, mVO.getmIdx());
			else pstmt.setInt(1, Integer.parseInt(mealTemp));
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setCompUpdate(CompanionVO cVO) {
		try {
			sql = "update companion set pwd = ?, name = ?, age = ?, gender = ?, email = ?, tel = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cVO.getPwd());
			pstmt.setString(2, cVO.getName());
			pstmt.setInt(3, cVO.getAge());
			pstmt.setString(4, cVO.getGender());
			pstmt.setString(5, cVO.getEmail());
			pstmt.setString(6, cVO.getTel());
			pstmt.setString(7, cVO.getId());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public int setCompDelete(String id) {
		try {
			sql = "delete from companion where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	public ArrayList<MealVO> getDaySearch(String mealTime) {
		ArrayList<MealVO> vos = new ArrayList<MealVO>();
		
		try {
			sql = "select * from meal where date(mealTime) = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mealTime);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mVO = new MealVO();
				mVO.setmIdx(rs.getInt("mIdx"));
				mVO.setMeal(rs.getString("meal"));
				mVO.setMealTime(rs.getString("mealTime"));
				mVO.setMealMenu(rs.getString("mealMenu"));
				mVO.setaMealKcal(rs.getDouble("aMealKcal"));
//				mVO.setDayKcal(rs.getDouble("dayKcal"));
				vos.add(mVO);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	public Vector getConditionSearch(String fieldName, String textCondi) {
		Vector vData = new Vector<>();
		try {
			sql = "select * from meal where " + fieldName + " like ? order by midx";	//?는 무조건 값이 옴. 
			pstmt = conn.prepareStatement(sql);
			
//			if(fieldName.equals("mealTime")) pstmt.setInt(1, Integer.parseInt(txtCond));
			pstmt.setString(1, "%"+textCondi+"%");	//?에 ''포함된 거라 ''를 따로 적지 않아야 함
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Vector vo = new Vector<>();
				vo.add(rs.getString("mealTime"));
				vo.add(rs.getString("meal"));
				vo.add(rs.getDouble("aMealKcal"));
				vo.add(rs.getString("mealMenu"));
//				vo.add(rs.getDouble("dayKcal"));
				vData.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rsClose();
		}
		return vData;
	}
	
//	public Vector getMealTimeAscList(String flag) {
//		Vector vData = new Vector<>();
//		try {
//			if(flag.equals("a")) sql = "select * from meal order by mealTime";
//			else sql = "select * from meal order by mealTime desc";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				Vector mVO = new Vector<>();
//				mVO.add(rs.getInt("mIdx"));
//				mVO.add(rs.getString("meal"));
//				mVO.add(rs.getString("mealTime"));
//				mVO.add(rs.getString("mealMenu"));
//				mVO.add(rs.getDouble("aMealKcal"));
////				mVO.add(rs.getDouble("dayKcal"));
//				
//				vData.add(mVO);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("SQL 오류 : " + e.getMessage());
//		} finally {
//			rsClose();
//		}
//		return vData;
//	}

	public int getMealOverlapdelete(MealVO mVO) {
		try {
			sql = "delete from meal where meal = ? and date(mealTime) = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVO.getMeal());
			pstmt.setString(2, mVO.getMealTime().substring(0,10));
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			rsClose();
		}
		return res;
	}


	public MealVO getMealSearch(MealVO mVO2 ,int i) {	//식사 개별조회
		mVO = new MealVO();
		try {
			if(i == 0) {
				sql = "select * from meal where mealTime = ? and mealMenu = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mVO2.getMealTime());
				pstmt.setString(2, mVO2.getMealMenu());
			}
			else if(i == 1) {
				sql = "select * from meal where meal = ? and date(mealTime) = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mVO2.getMeal());
				pstmt.setString(2, mVO2.getMealTime().substring(0,10));
			}
			else if(i == 2) {
				sql = "select * from meal where midx = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mVO2.getmIdx());
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mVO.setmIdx(rs.getInt("mIdx"));
				mVO.setMeal(rs.getString("meal"));
				mVO.setMealTime(rs.getString("mealTime"));
				mVO.setMealMenu(rs.getString("mealMenu"));
				mVO.setaMealKcal(rs.getDouble("aMealKcal"));
//				mVO.setDayKcal(rs.getDouble("dayKcal"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return mVO;
	}

	public Vector getMyFoodList() {	//음식목록(jtable)
		Vector vData = new Vector<>();
		try {
			sql = "select * from food order by fIdx desc";
			//sql = "select m.*,f.foodName,f.productName from meal m, food f where m.fidx=f.fIdx order by mIdx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Vector fVO = new Vector<>();
				String foodName = rs.getString("foodName");
				int fIdx =rs.getInt("fIdx"); 
				
				fVO.add(fIdx);
				fVO.add(rs.getString("productName"));
				fVO.add(foodName);
				fVO.add(rs.getDouble("kcal"));
				vData.add(fVO);
				
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vData;
	}

	
	public MealVO getDayKcal(String mealDate) {
		
		try {
			sql = "select sum(aMealKcal) as dayKcal from meal where date(mealTime) = ?";
			System.out.println("sql : " + sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mealDate);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mVO = new MealVO();
				mVO.setDayKcal(rs.getDouble("dayKcal"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			rsClose();
		}
		return mVO;
	}

	public MealVO getMealVSearch(Vector vdata) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
