package bean;

/**
 * カードマークのenumクラス
 * @author yata1
 */
public enum CardMark{
	DIAMOND("ダイヤ"), SPADE("スペード");

    /** カードマーク名 */
	private final String name;

	/**
	 * コンストラクタ
	 * @param name
	 */
	private CardMark(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}