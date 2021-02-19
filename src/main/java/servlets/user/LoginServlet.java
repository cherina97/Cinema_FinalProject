package servlets.user;

import entities.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        Optional<User> userOptional = userService.getUserByEmailAndPassword(email, password);

        Optional<User> optionalUser = userService.getByEmail(email);
        String errorUser = "User with such email doesn't exist";
        String errorPassword = "Wrong password. Try again";

        if(!optionalUser.isPresent()){
            req.setAttribute("userError", errorUser);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        if(!optionalUser.get().getPassword().equals(password)){
            req.setAttribute("userError", errorPassword);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

        if (userOptional.isPresent()) {
            req.getSession().setAttribute("user", userOptional.get());
            resp.sendRedirect("/cinema/cabinet");
            return;
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);

    }
}
