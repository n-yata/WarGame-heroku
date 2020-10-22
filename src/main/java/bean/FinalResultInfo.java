package bean;

/**
 * 最終結果情報のbeanクラス
 * @author yata1
 */
public class FinalResultInfo {

    /** CPUの獲得カード枚数 */
    private int cpuAcuisCardSize;
    /** ユーザーの獲得カード枚数 */
    private int userAcuisCardSize;
    /** 勝ち負け判定 */
    private String finalResult;
    /** ゲーム回数 */
    private String gameCount;
    /** 勝利回数 */
    private String winCount;
    /** 最大カード枚数 */
    private String maxAquisCardSize;
    /** 最終結果CSVの保存結果 */
    private boolean resultCsvSave;

    public int getCpuAcuisCardSize() {
        return cpuAcuisCardSize;
    }
    public void setCpuAcuisCardSize(int cpuAcuisCardSize) {
        this.cpuAcuisCardSize = cpuAcuisCardSize;
    }
    public int getUserAcuisCardSize() {
        return userAcuisCardSize;
    }
    public void setUserAcuisCardSize(int userAcuisCardSize) {
        this.userAcuisCardSize = userAcuisCardSize;
    }
    public String getFinalResult() {
        return finalResult;
    }
    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }
    public String getGameCount() {
        return gameCount;
    }
    public void setGameCount(String gameCount) {
        this.gameCount = gameCount;
    }
    public String getWinCount() {
        return winCount;
    }
    public void setWinCount(String winCount) {
        this.winCount = winCount;
    }
    public String getMaxAquisCardSize() {
        return maxAquisCardSize;
    }
    public void setMaxAquisCardSize(String maxAquisCardSize) {
        this.maxAquisCardSize = maxAquisCardSize;
    }
    public boolean isResultCsvSave() {
        return resultCsvSave;
    }
    public void setResultCsvSave(boolean resultCsvSave) {
        this.resultCsvSave = resultCsvSave;
    }

}
