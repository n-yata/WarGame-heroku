package model;

import bean.FinalResultInfo;
import bean.GameMaster;
import bean.GameResult;

/**
 * 最終結果のmodelクラス
 * @author yata1
 */
public class FinalResultLogic {
    public FinalResultInfo execute(GameMaster gameMaster) {
        // CPUの獲得枚数
        int cpuAcuisCardSize = gameMaster.getCpu().AcuisCardSize();
        // ユーザーの獲得枚数
        int userAcuisCardSize = gameMaster.getUser().AcuisCardSize();

        // 最終結果情報の生成
        FinalResultInfo resultInfo = new FinalResultInfo();
        resultInfo.setCpuAcuisCardSize(cpuAcuisCardSize);
        resultInfo.setUserAcuisCardSize(userAcuisCardSize);

        // 獲得枚数の比較
        switch(result(userAcuisCardSize, cpuAcuisCardSize)) {
        case WIN:
            resultInfo.setFinalResult("win");
            break;
        case LOSE:
            resultInfo.setFinalResult("lose");
            break;
        case DRAW:
            resultInfo.setFinalResult("draw");
            break;
        }

        return resultInfo;
    }

    /**
     * 獲得枚数の比較
     * @param player
     * @param cpu
     * @return GameResult
     */
    public GameResult result(int player, int cpu) {
        if(player > cpu) {
            return GameResult.WIN;
        }else if(player < cpu) {
            return GameResult.LOSE;
        }else {
            return GameResult.DRAW;
        }
    }
}
