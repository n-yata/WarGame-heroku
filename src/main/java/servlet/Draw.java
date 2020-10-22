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

import bean.CompareResultInfo;
import bean.GameMaster;
import model.DrawLogic;
import util.JsonConvertUtil;

/**
 * Servlet implementation class Draw
 */
@WebServlet("/Draw")
public class Draw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションスコープに保存されたゲーム情報を取得
        HttpSession session = request.getSession();
        GameMaster gameMaster = (GameMaster) session.getAttribute("gameMaster");

        // カードを引いたときの処理を実行
        DrawLogic logic = new DrawLogic();
        CompareResultInfo compareResultInfo = logic.execute(gameMaster);

        // ゲーム情報をセッションスコープに保存
        session.setAttribute("gameMaster", gameMaster);

        // 戻り値用のオブジェクト作成
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("cpuCard", compareResultInfo.getCpuCard().toString());
        resMap.put("userCard", compareResultInfo.getUserCard().toString());
        resMap.put("result", compareResultInfo.getResult());

        // JSONを戻す
        JsonConvertUtil.convertToJson(resMap, response);

	}

}
