package domain;

import java.util.Arrays;

public enum ResultStatics {
	NOTHING(0, 0, false),
	FIFTH(3, 5000, false),
	FOURTH(4, 50000, false),
	THIRD(5, 1500000, false),
	SECOND(5, 30000000, true),
	FIRST(6, 2000000000, false);

	private static final int MIN_NUMBER_TO_WIN = 3;

	private final int numberMatches;
	private final int price;
	private final boolean hitBonus;

	ResultStatics(int numberMatches, int price, boolean hitBonus) {
		this.numberMatches = numberMatches;
		this.price = price;
		this.hitBonus = hitBonus;
	}

	public static ResultStatics of(int numberMatches, boolean hitBonus) {
		if (numberMatches < MIN_NUMBER_TO_WIN) {
			return NOTHING;
		}

		return Arrays.stream(ResultStatics.values())
			.filter(r -> ((r.numberMatches == numberMatches) && (r.hitBonus == hitBonus)))
			.findFirst()
			.orElse(NOTHING);
	}

	public int getNumberMatches() {
		return numberMatches;
	}

	public int getPrice() {
		return price;
	}

	public boolean isHitBonus() {
		return hitBonus;
	}

}
