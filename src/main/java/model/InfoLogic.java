package model;

import java.util.Collections;

import bean.GameMaster;
import bean.Trump;

/**
 * ゲーム情報の表示処理
 * @author yata1
 *
 */
public class InfoLogic {
    public void execute(GameMaster gameMaster) {

        // 対戦回数が1かつ、ユーザーとCPUの手札が0のとき
        if (gameMaster.getCompeNumber() == 1
                && gameMaster.getUser().YourHandSize() == 0
                    && gameMaster.getCpu().YourHandSize() == 0
        ) {

                // 新しいデッキを作成してシャッフル
                Trump trump = new Trump();
                trump.createDeck();
                Collections.shuffle(trump.getTrumpList());

                // 1枚ずつデッキから配る
                distribute(gameMaster, trump);

        }
    }

    /**
     * 1枚ずつデッキから配る
     * @param gameMaster
     * @param trump
     */
    private void distribute(GameMaster gameMaster, Trump trump) {
        // トランプがなくなるまで
        while (!(trump.getTrumpList().size() == 0)) {
            // ユーザーとCPUに交互に配る
            if (trump.getTrumpList().size() % 2 == 0) {
                gameMaster.getUser().setYourHand(trump.distributeByDeck());
            } else {
                gameMaster.getCpu().setYourHand(trump.distributeByDeck());
            }
        }
    }
}
