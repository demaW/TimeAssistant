package com.java.task11.webapp;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.utils.FileUploadUtils;
import com.java.task11.utils.ValidationUtils;

/**
 * @author nlelyak
 * @version 1.00 2014-03-14
 */
@WebServlet("/edit")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {

    private static Logger log = Logger.getLogger(EditProfileServlet.class);
    private static final String DATA_DIRECTORY = "img/employees";
    private UserService employeeService;
    private User user;

    @Override
    public void init() throws ServletException {
        employeeService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("update"));
        user = employeeService.getByID(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/pages/editProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("update") != null) {
            updateUser(request);
            doGet(request, response);
        }
    }

    private void updateUser(HttpServletRequest request) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("update"));
        User user = employeeService.getByID(id);

        String firstName = (!ValidationUtils.isNullOrEmpty(request.getParameter("first_name-" + id)))
                ? request.getParameter("first_name-" + id) : user.getFirstName();
        String lastName = (!ValidationUtils.isNullOrEmpty(request.getParameter("last_name-" + id)))
                ? request.getParameter("last_name-" + id) : user.getLastName();
        String email = (!ValidationUtils.isNullOrEmpty(request.getParameter("email-" + id))
                && ValidationUtils.validEmail(request.getParameter("email-" + id)))
                ? request.getParameter("email-" + id) : user.getEmail();
        if (!employeeService.getByEmail(email).equals(null)) {
            email = user.getEmail();
        }
        String position = (!ValidationUtils.isNullOrEmpty(request.getParameter("position-" + id)))
                ? request.getParameter("position-" + id) : user.getPosition();

        String imageName = user.getImage();
        Part filePart = request.getPart("userImage-" + id);
        try {
            String contentType = filePart.getContentType();
            if (contentType.startsWith("image")) {
                File image = FileUploadUtils.uploadFile(this, DATA_DIRECTORY, filePart);
                imageName = FileUploadUtils.getFilename(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPosition(position);
        user.setImage(imageName);

        employeeService.update(user);
    }

}


