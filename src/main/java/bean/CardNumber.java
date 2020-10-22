package bean;

/**
 * カードナンバーのenumクラス
 * @author yata1
 */
public enum CardNumber{
	TWO("2", 1), THREE("3", 2), FOUR("4", 3), FIVE("5", 4), SIX("6",5), SEVEN("7", 6), EIGHT("8", 7),
	NINE("9", 8), TEN("10", 9), JACK("J", 10), QUEEN("Q", 11), KING("K", 12), ACE("A", 13);

    /** カードナンバー */
	private final String name;
	/** カードの強さ */
	private final int strength;

	/**
	 *  コンストラクタ
	 * @param name
	 * @param strength
	 */
	private CardNumber(String name, int strength) {
		this.name = name;
		this.strength = strength;
	}

	public String getName() {
		return name;
	}

	public int getStrength() {
		return strength;
	}
}