package Design.D3_DesignPattern;

public class User {
	private final String firstName;  // 必须，不可修改
	private final String lastName;   // 必须，不可修改
	private final int age;			 // 可选，不可修改
	private String phone;		     // 可选，可修改
	private String address;			 // 可选，可修改

	private User(UserBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public static class UserBuilder {
		private String firstName;  // 必须
		private String lastName;   // 必须
		private int age = 0;	   // 可选，默认为0
		private String phone = ""; // 可选，默认为空
		private String address;	   // 可选，默认为null

		public UserBuilder firstName(String firstNme) {
			this.firstName = firstNme;
			return this;
		}

		public UserBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public UserBuilder age(int age) {
			this.age = age;
			return this;
		}

		public UserBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public UserBuilder address(String addres) {
			this.address = addres;
			return this;
		}

		public User build() {
			if (firstName == null || lastName == null) {
				throw new IllegalArgumentException("required fields are not set");
			}
			return new User(this);
		}


	}


	public static void main(String[] args) {
		User user = new User.UserBuilder()
						.lastName("Zhang")
						.firstName("San")
						.phone("12345")
						.age(25)
						.address("fake address")
						.build();
	}
}
