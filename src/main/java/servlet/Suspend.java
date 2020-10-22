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

import bean.GameMaster;
import model.DataManager;
import util.JsonConvertUtil;

/**
 * Servlet implementation class Suspend
 */
@WebServlet("/Suspend")
public class Suspend extends HttpServlet {
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

        // 外部ファイルのパスを取得
        ServletContext context = this.getServletContext();
        String path = context.getRealPath("/resources/data");

        // 外部ファイルに中断データを保存
        DataManager manager = new DataManager();
        boolean saveResult = manager.save(gameMaster, path);

        // 戻り値用のオブジェクト作成
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("saveResult", saveResult);

        // JSONを戻す
        JsonConvertUtil.convertToJson(resMap, response);

        // セッションスコープからゲーム情報を削除
        session.removeAttribute("gameMaster");
    }

}
