package meal;

public class FoodVO {

	private int fIdx;
	private String foodName;
	private String productName;
	private double intake;
	private double kcal;
	private double carbohydrate;
	private double dietaryFiber;
	private double sugars;
	private double protein;
	private double fat;
	private double saturatedFat;
	private double natrium;
	
	public int getfIdx() {
		return fIdx;
	}
	public void setfIdx(int fIdx) {
		this.fIdx = fIdx;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getIntake() {
		return intake;
	}
	public void setIntake(double intake) {
		this.intake = intake;
	}
	public double getKcal() {
		return kcal;
	}
	public void setKcal(double kcal) {
		this.kcal = kcal;
	}
	public double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public double getDietaryFiber() {
		return dietaryFiber;
	}
	public void setDietaryFiber(double dietaryFiber) {
		this.dietaryFiber = dietaryFiber;
	}
	public double getSugars() {
		return sugars;
	}
	public void setSugars(double sugars) {
		this.sugars = sugars;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getSaturatedFat() {
		return saturatedFat;
	}
	public void setSaturatedFat(double saturatedFat) {
		this.saturatedFat = saturatedFat;
	}
	public double getNatrium() {
		return natrium;
	}
	public void setNatrium(double natrium) {
		this.natrium = natrium;
	}
	
	@Override
	public String toString() {
		return "FoodVO [fIdx=" + fIdx + ", foodName=" + foodName + ", productName=" + productName + ", intake=" + intake
				+ ", kcal=" + kcal + ", carbohydrate=" + carbohydrate + ", dietaryFiber=" + dietaryFiber + ", sugars=" + sugars
				+ ", protein=" + protein + ", fat=" + fat + ", saturatedFat=" + saturatedFat + ", natrium=" + natrium + "]";
	}
}
