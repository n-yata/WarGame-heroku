package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.FinalResultInfo;
import bean.GameMaster;
import model.DataManager;
import model.FinalResultLogic;
import util.JsonConvertUtil;

/**
 * Servlet implementation class FinalResult
 */
@WebServlet("/FinalResult")
public class FinalResult extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // セッションスコープに保存されたゲーム情報を取得
        HttpSession session = request.getSession();
        GameMaster gameMaster = (GameMaster) session.getAttribute("gameMaster");

        // 最終結果の処理を実行
        FinalResultLogic logic = new FinalResultLogic();
        FinalResultInfo finalResultInfo = logic.execute(gameMaster);

        // 外部ファイルのパスを取得
        ServletContext context = this.getServletContext();
        String path = context.getRealPath("/resources/data");

        // 外部ファイルに保存された中断データを削除
        DataManager manager = new DataManager();
        manager.deleteInterruptFile(path);

        // 最終結果をCSVに保存
        finalResultInfo =  manager.resultToCsv(finalResultInfo, path);

        // 戻り値用のオブジェクト作成
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("cpuAcuisCard", finalResultInfo.getCpuAcuisCardSize());
        resMap.put("userAcuisCard", finalResultInfo.getUserAcuisCardSize());
        resMap.put("finalResult", finalResultInfo.getFinalResult());
        resMap.put("gameCount", finalResultInfo.getGameCount());
        resMap.put("winCount", finalResultInfo.getWinCount());
        resMap.put("maxAquisCardSize", finalResultInfo.getMaxAquisCardSize());
        resMap.put("resultCsvSave", finalResultInfo.isResultCsvSave());

        // JSONを戻す
        JsonConvertUtil.convertToJson(resMap, response);

        // セッションスコープからゲーム情報を削除
        session.removeAttribute("gameMaster");

    }

}
