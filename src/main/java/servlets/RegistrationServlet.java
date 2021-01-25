package servlets;

import entities.User;
import entities.UserRole;
import org.apache.commons.lang3.ObjectUtils;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (ObjectUtils.allNotNull(firstName, lastName, email, password)){
            userService.createUser(new User.Builder()
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withRole(UserRole.USER.name())
                    .withPassword(password)
                    .build());
            req.setAttribute("userEmail", email);
            req.getRequestDispatcher("cabinet.jsp").forward(req, resp);
        }

        req.getRequestDispatcher("index.jsp").forward(req, resp);


    }
}
