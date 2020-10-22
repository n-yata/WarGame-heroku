package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GameMaster;
import model.DataManager;

/**
 * Servlet implementation class DataLoad
 */
@WebServlet("/DataLoad")
public class DataLoad extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 外部ファイルのパスを取得
        ServletContext context = this.getServletContext();
        String path = context.getRealPath("/resources/data");

        // 外部ファイルから中断データの情報をロード
        DataManager manager = new DataManager();
        GameMaster gameMaster = manager.load(path);

        // ゲーム情報をセッションスコープに保存
        HttpSession session = request.getSession();
        session.setAttribute("gameMaster", gameMaster);

    }

}
