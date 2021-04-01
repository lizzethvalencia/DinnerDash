package ordering;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Payment {
	private BufferedWriter cardLogger;
	private FileWriter cardLoggerStream;
	private String inputFile;
	private ArrayList<CreditCard> cards;
	private StringTokenizer tokens;
	private Scanner sc;
	
	public Payment(File creditCardsFile) throws FileNotFoundException {
		cards = new ArrayList<CreditCard>();
		sc = new Scanner(creditCardsFile); 
		inputFile = creditCardsFile.getName();
	}

	public void readInputFile() {
		while(sc.hasNextLine()) {
			String card = sc.nextLine();
			tokens = new StringTokenizer(card, ";");
			while(tokens.hasMoreTokens()) {
				String cardHolder = tokens.nextToken();
				String cardNum = tokens.nextToken();
				CreditCard createCard = new CreditCard(cardNum, cardHolder);
				cards.add(createCard);
			}
		}
		sc.close();
	}
	
	public ArrayList<CreditCard> getCreditCards() {
		return cards;
	}
	
	public void logCreditCard(String cardHolder, String cardNum) throws IOException {
		cardLoggerStream = new FileWriter(inputFile,true);
		cardLogger = new BufferedWriter(cardLoggerStream);
		
		CreditCard card = new CreditCard(cardNum, cardHolder);
		cardLogger.write(card.toString() + "\n");
		
		cardLogger.close();
	}
}
