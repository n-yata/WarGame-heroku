package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import bean.FinalResultInfo;
import bean.GameMaster;

/**
 * 外部ファイルの読み込み・書き込みを行うmodelクラス
 * @author yata1
 */
public class DataManager {
    /** 中断データ */
    private static final String INTERRUPT_FILE_NAME = "/game_interrupt.dat";
    /** 結果データ */
    private static final String RESULT_FILE_NAME = "/game_result.csv";

    /**
     * 中断データをロードする
     * @param path
     * @return loadResult
     */
    public GameMaster load(String path) {
        GameMaster gameMaster = new GameMaster();
        Path filePath = Paths.get(path + INTERRUPT_FILE_NAME);

        if (Files.exists(filePath)) {
            try (ObjectInputStream is = new ObjectInputStream(Files.newInputStream(filePath));) {
                gameMaster = (GameMaster) is.readObject();
            } catch (IOException | ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }

        return gameMaster;
    }

    /**
     * 中断データをセーブする
     * @param gameMaster
     * @param path
     * @return saveResult
     */
    public boolean save(GameMaster gameMaster, String path) {
        Path filePath = Paths.get(path + INTERRUPT_FILE_NAME);

        try (ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(filePath));) {
            os.writeObject(gameMaster);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 中断データを削除する
     * @param path
     * @return removeResult
     */
    public boolean deleteInterruptFile(String path) {
        Path filePath = Paths.get(path + INTERRUPT_FILE_NAME);

        if (Files.exists(filePath)) {
            try {
                Files.delete(filePath);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }

    /**
     * 最終結果をセーブする
     * @param result
     * @param acquis
     * @return resultCsvSave
     */
    public FinalResultInfo resultToCsv(FinalResultInfo finalResultInfo, String path) {
        String comment1 = "ゲーム回数";
        String comment2 = "勝利回数";
        String comment3 = "最大カード枚数";
        String[] values = { "0", "0", "0" };
        Path filePath = Paths.get(path + RESULT_FILE_NAME);

        if (Files.exists(filePath)) {
            try (BufferedReader in = Files.newBufferedReader(filePath);) {
                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        break;
                    }
                    values = line.split(",");
                }
                Files.delete(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                finalResultInfo.setResultCsvSave(false);
                return finalResultInfo;
            }

        }

        // ゲーム回数を1増やす
        values[0] = String.valueOf(Integer.parseInt(values[0]) + 1);

        // ゲームに勝ったら勝利回数を１増やす
        if (finalResultInfo.getFinalResult().equals("win")) {
            values[1] = String.valueOf(Integer.parseInt(values[1]) + 1);

            // 勝利時、カード獲得枚数が最大であれば更新
            if (finalResultInfo.getUserAcuisCardSize() > Integer.parseInt(values[2])) {
                values[2] = String.valueOf(finalResultInfo.getUserAcuisCardSize());
            }

        }

        try (BufferedWriter out = Files.newBufferedWriter(filePath);) {
            out.write(
                    String.format("%s,%s,%s%n%s,%s,%s", comment1, comment2, comment3, values[0], values[1], values[2]));
        } catch (IOException e) {
            e.printStackTrace();
            finalResultInfo.setResultCsvSave(false);
            return finalResultInfo;
        }

        finalResultInfo.setResultCsvSave(true);

        // 最終結果画面に表示する値をセット
        finalResultInfo.setGameCount(values[0]);
        finalResultInfo.setWinCount(values[1]);
        finalResultInfo.setMaxAquisCardSize(values[2]);

        return finalResultInfo;
    }
}
