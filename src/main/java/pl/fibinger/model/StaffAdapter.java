package pl.fibinger.model;

public class StaffAdapter {

	private Staff staff;

    public StaffAdapter(Staff staff) {
		this.staff = staff;
	}
	
	public String getToken() {
		return staff.getToken();
	}
	
	public String getUsername() {
		return staff.getUsername();
	}
	
	public String getName() {
		return staff.getName();
	}
	
}
