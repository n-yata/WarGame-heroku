package bean;

/**
 * 比較結果情報のbeanクラス
 * @author yata1
 */
public class CompareResultInfo {

    /** CPUの引いたカード */
    private Card cpuCard;
    /** ユーザーの引いたカード */
    private Card userCard;
    /** 勝ち負け判定 */
    private String result;

    public Card getCpuCard() {
        return cpuCard;
    }
    public void setCpuCard(Card cpuCard) {
        this.cpuCard = cpuCard;
    }
    public Card getUserCard() {
        return userCard;
    }
    public void setUserCard(Card userCard) {
        this.userCard = userCard;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
