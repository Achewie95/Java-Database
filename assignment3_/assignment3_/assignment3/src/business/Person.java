package business;

public class Person {

	private int recordId;
	private String firstName;
	private String lastName;
	private int age;
	private String phone;

	public Person() {
		this(0, "", "", 0, "");
	}

	public Person(int recordId, String firstName, String lastName, int age, String phone) {
		this.recordId = recordId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phone = phone;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}