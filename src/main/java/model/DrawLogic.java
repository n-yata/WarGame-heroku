package model;

import bean.Card;
import bean.CompareResultInfo;
import bean.GameMaster;
import bean.GameResult;

/**
 * カードを引いたときのmodelクラス
 * @author yata1
 */
public class DrawLogic {
    public CompareResultInfo execute(GameMaster gameMaster) {
        // CPUの引いたカード
        Card cpuCard = gameMaster.getCpu().drawCard();
        // ユーザーの引いたカード
        Card playerCard = gameMaster.getUser().drawCard();

        // 比較結果情報の生成
        CompareResultInfo resultInfo = new CompareResultInfo();
        resultInfo.setCpuCard(cpuCard);
        resultInfo.setUserCard(playerCard);

        // カードの比較
        switch (compare(playerCard, cpuCard)) {
        case WIN:
            gameMaster.getUser().addAcuisCard(playerCard, cpuCard, gameMaster.getStoreCard());
            gameMaster.getStoreCard().clear();
            resultInfo.setResult("win");
            break;
        case LOSE:
            gameMaster.getCpu().addAcuisCard(playerCard, cpuCard, gameMaster.getStoreCard());
            gameMaster.getStoreCard().clear();
            resultInfo.setResult("lose");
            break;
        case DRAW:
            gameMaster.getStoreCard().add(playerCard);
            gameMaster.getStoreCard().add(cpuCard);
            resultInfo.setResult("draw");
            break;
        }

        // 対戦回数を1増やす
        gameMaster.addCompeNumger();

        return resultInfo;
    }

    /**
     * カードの強さを比較する
     * @param player
     * @param cpu
     * @return GameResult
     */
    public GameResult compare(Card player, Card cpu) {
        if (player.getNumber().getStrength() > cpu.getNumber().getStrength()) {
            return GameResult.WIN;
        } else if (player.getNumber().getStrength() < cpu.getNumber().getStrength()) {
            return GameResult.LOSE;
        } else {
            return GameResult.DRAW;
        }
    }
}
