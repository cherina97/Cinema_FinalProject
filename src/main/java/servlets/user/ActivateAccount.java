package servlets.user;

import utils.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ActivateAccount")
public class ActivateAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("key1");
        String hash = request.getParameter("key2");

        Connection connection = ConnectionPool.getInstance().getConnection();

        try {
            PreparedStatement pst = connection.prepareStatement(
                    "select email, hash, active from users where email=? and hash=? and active='0'");
            pst.setString(1, email);
            pst.setString(2, hash);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                PreparedStatement pst1 = connection.prepareStatement(
                        "update users set active='1' where email=? and hash=?");
                pst1.setString(1, email);
                pst1.setString(2, hash);

                int i = pst1.executeUpdate();

                if (i == 1) {
                    response.sendRedirect("/cinema/login");
                } else {
                    response.sendRedirect("/cinema/register");
                }
            }

        } catch (Exception e) {
            //todo log
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
