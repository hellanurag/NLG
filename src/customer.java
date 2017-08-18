
public class customer {

	//customer fields
	private int 	uniqueid;
	private int		customerid;
	private String	customername;
	private String	customerrep;
	private int		productid;
	private String	productname;
	private int		totallicenses;
	private int		activeusers;
	private int		weeksfrompurchase;
	
	//Setters and Getters
	public int getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(int uniqueid) {
		this.uniqueid = uniqueid;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int cutomerid) {
		this.customerid = cutomerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getCustomerrep() {
		return customerrep;
	}
	public void setCustomerrep(String customerrep) {
		this.customerrep = customerrep;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getTotallicenses() {
		return totallicenses;
	}
	public void setTotallicenses(int totallicenses) {
		this.totallicenses = totallicenses;
	}
	public int getActiveusers() {
		return activeusers;
	}
	public void setActiveusers(int activeusers) {
		this.activeusers = activeusers;
	}
	public int getWeeksfrompurchase() {
		return weeksfrompurchase;
	}
	public void setWeeksfrompurchase(int weeksfrompurchase) {
		this.weeksfrompurchase = weeksfrompurchase;
	}
	
	public double getPercUtil() {
		return ((double)activeusers/(double)totallicenses)*100;
	}
	
}
