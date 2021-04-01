package ordering;

public class CreditCard {
	private final String cardNum;
	private final String cardHolder;
	
	public CreditCard(String cardNum, String cardHolder) throws IllegalArgumentException {
		if(isValid(cardNum, cardHolder)) {
			this.cardNum = cardNum;
			this.cardHolder = cardHolder;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String getCardNum() {
		return cardNum;
	}
	
	public String getCardHolder() {
		return cardHolder;
	}
	
	private boolean isValid(String cardNum, String cardHolder) {
		if(cardNum.length() < 13 || cardNum.length() > 16 || cardHolder.length() == 0)
			return false;
		else
			return true;
	}
	
	public String toString() {
		String tmp = cardHolder + " " + cardNum;
		return tmp;
	}
}
