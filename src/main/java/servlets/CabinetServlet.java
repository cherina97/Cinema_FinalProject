package servlets;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cabinet")
public class CabinetServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String userEmail = (String) req.getSession().getAttribute("userEmail");
//        Optional<User> userByEmail = userService.getByEmail(userEmail);
//        req.getSession().setAttribute("user", userByEmail);

        req.getSession().getAttribute("user");


        req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
    }
}
