package bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ゲーム情報のBeanクラス
 * @author yata1
 */
public class GameMaster implements Serializable{
    private static final long serialVersionUID = 606215554616577266L;

    /** ユーザー情報 */
    private Player user;
    /** CPU情報 */
    private Player cpu;
    /** 第○回戦目 */
    private int compeNumber;
    /** 場に積まれたカードの枚数 */
    private ArrayList<Card> storeCard;

    /**
     * コンストラクタ
     */
    public GameMaster() {
        this.user = new Player();
        this.cpu = new Player();
        this.compeNumber = 1;
        this.storeCard = new ArrayList<>();
    }

    // 対戦回数を増やす
    public void addCompeNumger() {
        this.compeNumber++;
    }

    public Player getUser() {
        return user;
    }

    public void setUser(Player user) {
        this.user = user;
    }

    public Player getCpu() {
        return cpu;
    }

    public void setCpu(Player cpu) {
        this.cpu = cpu;
    }

    public int getCompeNumber() {
        return compeNumber;
    }

    public void setCompeNumber(int compeNumber) {
        this.compeNumber = compeNumber;
    }

    public ArrayList<Card> getStoreCard() {
        return storeCard;
    }

    public void setStoreCard(ArrayList<Card> storeCard) {
        this.storeCard = storeCard;
    }
}
