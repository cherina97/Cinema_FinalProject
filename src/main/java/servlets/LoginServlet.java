package servlets;

import entities.User;
import org.apache.commons.lang3.ObjectUtils;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (ObjectUtils.anyNull(email, password)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<User> userOptional = userService.getUserByEmailAndPassword(email, password);

        if (userOptional.isPresent()) {
            HttpSession session = req.getSession(true);
            session.setAttribute("userEmail", userOptional.get().getEmail());

//            String userEmail = (String) req.getSession().getAttribute("userEmail");
            Optional<User> userByEmail = userService.getByEmail(email);
            req.getSession().setAttribute("user", userByEmail.get());

            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }
}
