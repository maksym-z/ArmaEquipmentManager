
public class Stuff {
	public Stuff(String classname, int qty) {
		this.classname = classname;
		this.qty = qty;
	}

	private String classname;
	private int qty;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
}
