package servlets.user;

import entities.User;
import org.apache.commons.lang3.ObjectUtils;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


/**
 * The type Registration servlet.
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Optional<User> userOptional = userService.getByEmail(email);
        String errorUser = "User with such email is already present. Try another one";
        if(userOptional.isPresent()){
            req.setAttribute("userError", errorUser);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }

        if (ObjectUtils.allNotNull(firstName, lastName, email, password)) {
            userService.createUser(new User.Builder()
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withRoleId(1)
                    .withPassword(password)
                    .build());
            resp.sendRedirect("/cinema/login");
            return;
        }
        req.getRequestDispatcher("/register.jsp").forward(req, resp);

    }
}
