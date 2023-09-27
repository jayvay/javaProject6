package meal;

public class CompanionVO {

	private int cIdx;
	private String id;
	private String pwd;
	private String name;
	private int age;
	private String gender;
	private String email;
	private String tel;
	
	public int getcIdx() {
		return cIdx;
	}
	public void setcIdx(int cIdx) {
		this.cIdx = cIdx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "CompanionVO [cIdx=" + cIdx + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", age=" + age + ", gender="
				+ gender + ", email=" + email + ", tel=" + tel + "]";
	}
}
