package in.sp.main.gettersetter;

import in.sp.main.entities.Farmer;

public class GetterSetter {
	
	public static Farmer farmer;
	
	public static int buyerId;

	public static int getBuyerId() {
		return buyerId;
	}

	public static void setBuyerId(int buyerId) {
		GetterSetter.buyerId = buyerId;
	}

	public static Farmer getFarmer() {
		return farmer;
	}

	public static void setFarmer(Farmer farmer) {
		GetterSetter.farmer = farmer;
	}
	
	public void clear() {
		GetterSetter.farmer = null;
	}

}
