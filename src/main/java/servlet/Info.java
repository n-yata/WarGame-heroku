package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GameMaster;
import model.InfoLogic;
import util.JsonConvertUtil;

/**
 * Servlet implementation class Info
 */
@WebServlet("/Info")
public class Info extends HttpServlet {
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

        // ゲーム情報がなければ新規作成
        if(gameMaster == null) {
            gameMaster = new GameMaster();
        }

        // ゲーム情報の表示処理を実行
        InfoLogic logic = new InfoLogic();
        logic.execute(gameMaster);

        // ゲーム情報をセッションスコープに保存
        session.setAttribute("gameMaster", gameMaster);

        // 戻り値用のオブジェクト作成
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("compeNumber", gameMaster.getCompeNumber());
        resMap.put("storeCard", gameMaster.getStoreCard().size());
        resMap.put("cpuHandSize", gameMaster.getCpu().YourHandSize());
        resMap.put("cpuAcuisSize", gameMaster.getCpu().AcuisCardSize());
        resMap.put("userHandSize", gameMaster.getUser().YourHandSize());
        resMap.put("userAcuisSize", gameMaster.getUser().AcuisCardSize());

        // JSONを戻す
        JsonConvertUtil.convertToJson(resMap, response);
    }

}
