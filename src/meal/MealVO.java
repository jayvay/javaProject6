package meal;

public class MealVO {

	private int mIdx;
	private int cIdx;
	private int fIdx;
	private String meal;
	private String mealTime;
	private double aMealKcal;
	private double dayKcal;
	private double dayGoalKcal;
	
	public int getmIdx() {
		return mIdx;
	}
	public void setmIdx(int mIdx) {
		this.mIdx = mIdx;
	}
	public int getcIdx() {
		return cIdx;
	}
	public void setcIdx(int cIdx) {
		this.cIdx = cIdx;
	}
	public int getfIdx() {
		return fIdx;
	}
	public void setfIdx(int fIdx) {
		this.fIdx = fIdx;
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
	public double getDayGoalKcal() {
		return dayGoalKcal;
	}
	public void setDayGoalKcal(double dayGoalKcal) {
		this.dayGoalKcal = dayGoalKcal;
	}
	
	@Override
	public String toString() {
		return "MealVO [mIdx=" + mIdx + ", cIdx=" + cIdx + ", fIdx=" + fIdx + ", meal=" + meal + ", mealTime=" + mealTime
				+ ", aMealKcal=" + aMealKcal + ", dayKcal=" + dayKcal + ", dayGoalKcal=" + dayGoalKcal + "]";
	}
}
