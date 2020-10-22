package bean;

import java.util.ArrayList;

/**
 * トランプのBeanクラス
 * @author yata1
 */
public class Trump {
    /** 26枚のデッキ */
    private ArrayList<Card> trumpList = new ArrayList<>();
    /** トランプのカード */
    private Card card;
    /** トランプのカードマーク */
    private CardMark cardMark;
    /** トランプのカードナンバー */
    private CardNumber cardNumber;

    /**
     * デッキを新規作成する
     */
    public void createDeck() {
        trumpList.clear();
        for (CardMark i : CardMark.values()) {
            for (CardNumber j : CardNumber.values()) {
                card = new Card(i, j);
                trumpList.add(card);
            }
        }
    }

    /**
     * デッキからカードを配布する
     * @return Card トランプカード
     */
    public Card distributeByDeck() {
        int i = trumpList.size() - 1;
        return trumpList.remove(i);
    }

    public ArrayList<Card> getTrumpList() {
        return trumpList;
    }

    public void setTrumpList(ArrayList<Card> trumpList) {
        this.trumpList = trumpList;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public CardMark getCardMark() {
        return cardMark;
    }

    public void setCardMark(CardMark cardMark) {
        this.cardMark = cardMark;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

}