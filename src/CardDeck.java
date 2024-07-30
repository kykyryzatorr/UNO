
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


import javafx.scene.image.Image;

public class CardDeck {
	public static ArrayList<Card> defaultCardDeck;

	public static ArrayList<Card> cards;
	public static ArrayList<Card> usedcards;
	
	
	public static void shuffle() {
		
		 Collections.shuffle(cards);

	};

	public static Card takeCard(int i) {
		
		return cards.get(i);
	};


	public static void initDefaultDeck(String dir, String suffix)
			throws IllegalArgumentException, FileNotFoundException {
		Card.backImage = new Image(new FileInputStream("src/cards/card_back.png"));
		defaultCardDeck = new ArrayList<Card>();
		for (String color : Card.COLORS) {
			for (String id : Card.IDS) {

				defaultCardDeck.add(
						new Card(id, color, new Image(new FileInputStream(dir + color + "_" + id + "." + suffix))));
				if (!id.equals("0")) {
					defaultCardDeck.add(
							new Card(id, color, new Image(new FileInputStream(dir + color + "_" + id + "." + suffix))));
				}
			}
		//	dir + color + "_" + id + "." + suffix
			//dir + wild + "." + suffix

		}

		for (String wild : Card.WILDS) {
			defaultCardDeck.add(new Card(wild, wild, new Image(new FileInputStream(dir + wild + "." + suffix))));
			defaultCardDeck.add(new Card(wild, wild, new Image(new FileInputStream(dir + wild + "." + suffix))));
			defaultCardDeck.add(new Card(wild, wild, new Image(new FileInputStream(dir + wild + "." + suffix))));
			defaultCardDeck.add(new Card(wild, wild, new Image(new FileInputStream(dir + wild + "." + suffix))));

		}
		cards = new ArrayList<Card>(CardDeck.defaultCardDeck);
		usedcards=new ArrayList<Card>();

	}
}
