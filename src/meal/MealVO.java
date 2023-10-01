package meal;

public class MealVO {

	private int mIdx;
	private String meal;
	private String mealTime;
	private String mealMenu;
	private double aMealKcal;
	private double dayKcal;
	
	private String strYY;
	private String strMM;
	private String strDD;
	private String strHH;
	private String strmm;
	
	public int getmIdx() {
		return mIdx;
	}
	public void setmIdx(int mIdx) {
		this.mIdx = mIdx;
	}
	public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public String getMealTime() {
		return mealTime;
	}
	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}
	public String getMealMenu() {
		return mealMenu;
	}
	public void setMealMenu(String mealMenu) {
		this.mealMenu = mealMenu;
	}
	public double getaMealKcal() {
		return aMealKcal;
	}
	public void setaMealKcal(double aMealKcal) {
		this.aMealKcal = aMealKcal;
	}
	public double getDayKcal() {
		return dayKcal;
	}
	public void setDayKcal(double dayKcal) {
		this.dayKcal = dayKcal;
	}
	public String getStrYY() {
		return strYY;
	}
	public void setStrYY(String strYY) {
		this.strYY = strYY;
	}
	public String getStrMM() {
		return strMM;
	}
	public void setStrMM(String strMM) {
		this.strMM = strMM;
	}
	public String getStrDD() {
		return strDD;
	}
	public void setStrDD(String strDD) {
		this.strDD = strDD;
	}
	public String getStrHH() {
		return strHH;
	}
	public void setStrHH(String strHH) {
		this.strHH = strHH;
	}
	public String getStrmm() {
		return strmm;
	}
	public void setStrmm(String strmm) {
		this.strmm = strmm;
	}
	
	@Override
	public String toString() {
		return "MealVO [mIdx=" + mIdx + ", meal=" + meal + ", mealTime=" + mealTime + ", mealMenu=" + mealMenu
				+ ", aMealKcal=" + aMealKcal + ", dayKcal=" + dayKcal + ", strYY=" + strYY + ", strMM=" + strMM
				+ ", strDD=" + strDD + ", strHH=" + strHH + ", strmm=" + strmm + "]";
	}
	
}
