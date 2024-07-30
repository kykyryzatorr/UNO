
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
	public static final String[] COLORS = { "yellow", "red", "blue", "green" };
	public static final String[] IDS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "skip", "picker",
			"reverse" };
	public static final String[] WILDS = { "wild_color_changer", "wild_pick_four" };
	public static Image backImage;
	private String color;
	private String id;
	private ImageView image;

	public Card(String id, String initColor, Image image) throws IllegalArgumentException {
		this.image = new ImageView(image);
		color = initColor;
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public String getId() {
		return id;
	}

	public ImageView getImage() {
		return image;
	}

	public String getName() {
		return this.color + "_" + this.id;
	}

}
