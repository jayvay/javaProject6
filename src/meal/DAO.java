package meal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
	
	public CompanionVO getCompSearch(String id) {
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

	public int setCompInput(CompanionVO cVO) {
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

	public Vector getMealList() {
		Vector vData = new Vector<>();
		try {
			//sql = "select * from meal order by mIdx desc";
			sql = "select m.*,f.foodName as foodName,f.productName as productName from meal m, food f where m.fidx=f.fIdx order by mIdx desc";	//이것이 조인이다
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Vector mVO = new Vector<>();
				mVO.add(rs.getInt("mIdx"));
				mVO.add(rs.getString("mealTime"));
				mVO.add(rs.getString("meal"));
				mVO.add(rs.getString("foodname"));
				mVO.add(rs.getString("productName"));
				mVO.add(rs.getDouble("aMealKcal"));
				mVO.add(rs.getDouble("dayKcal"));
				mVO.add(rs.getDouble("dayGoalKcal"));
				
				vData.add(mVO);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vData;
	}

	public FoodVO getFoodSearch(String foodName, String productName) {
		fVO = new FoodVO();
		try {
			sql = "select * from food where foodName = ? and productName = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, foodName);
			pstmt.setString(2, productName);
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

	public int setFoodInput(FoodVO fVO) {
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

	public Vector getFoodList() {
		Vector vData = new Vector<>();
		try {
			sql = "select * from food order by fIdx desc";
			//sql = "select m.*,f.foodName,f.productName from meal m, food f where m.fidx=f.fIdx order by mIdx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Vector fVO = new Vector<>();
				String strName = rs.getString("foodName");
				int nIdx =rs.getInt("fIdx"); 
				System.out.println("fIdx : "+nIdx);
				System.out.println("foodName : "+strName);
				
				fVO.add(nIdx);
				fVO.add(strName);
				fVO.add(rs.getString("productName"));
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

	public int setFoodUpdate(FoodVO fVO) {
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

	public int setFoodDelete(String fIdx) {
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
	
}
