package pk.wieik.it.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pk.wieik.it.model.Comic;
import pk.wieik.it.model.FCuser;
import pk.wieik.it.model.Tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FC", value = "/FC")
public class FC extends HttpServlet {

    public void init() {
        ServletContext context = getServletContext();
        ArrayList<Comic> comics = new ArrayList<Comic>();
        context.setAttribute("comics", comics);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        FCuser user = (FCuser) session.getAttribute("user");
        if (user == null) {
            user = new FCuser();
            session.setAttribute("user", user);
        }

        List<Comic> comics = (List<Comic>) session.getAttribute("comics");
        if (comics == null) {
            comics = Comic.initializeDb();
            session.setAttribute("comics", comics);
        }

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        ServletContext context = getServletContext();

        HttpSession session = request.getSession();

        FCuser user = (FCuser) session.getAttribute("user");
        if (user == null) {
            user = new FCuser();
            session.setAttribute("user", user);
        }

        String page = request.getParameter("page");
        System.out.println("page: " + page);
        if (page.equals("login")) {
            String[] username = request.getParameterValues("login");
            String[] password = request.getParameterValues("password");

            if (username != null && password != null) {
                if (username[0].equals("user") && password[0].equals("user")) {
                    user.setPrivileges(1);
                    user.setLogin(username[0]);
                } else if (username[0].equals("admin") && password[0].equals("admin")) {
                    user.setPrivileges(2);
                    user.setLogin(username[0]);
                }
            }
        } else if (page.equals("logout")) {
            session.invalidate();
            user = new FCuser();
        } else if (page.equals("settings")) {
            String[] nameList = request.getParameterValues("name");
            String[] surnameList = request.getParameterValues("surname");
            String[] ageList = request.getParameterValues("age");

            String name = nameList == null ? "" : nameList[0];
            String surname = surnameList == null ? "" : surnameList[0];
            String age = ageList == null ? "" : ageList[0];
        } else if (page.equals("favourite")) {
            String comicIdStr = request.getParameter("comicId");
            if (comicIdStr != null) {
                int comicId = Integer.parseInt(comicIdStr);
                user.toggleFavourite(comicId);
            }
        }

        response.sendRedirect(request.getContextPath());
    }


    private void doRequest(PrintWriter out, FCuser user, String page)  throws ServletException, IOException {
        ServletContext context = getServletContext();

        String template = Tools.getTemplate("index.html", context);

        template = Tools.addScripts(template, page, context);
        template = Tools.fill(template, "HEADER", "header.html", context);
        template = Tools.fill(template, "MENU", "menu.html", context);
        template = Tools.fill(template, "CONTENT", page + ".html", context);
        template = Tools.fill(template, "FOOTER", "footer.html", context);

        if (user.getPrivileges() > 0) {
            template = Tools.fill(template, "LOGIN", "logout.html", context);
        } else {
            template = Tools.fill(template, "LOGIN", "login.html", context);
        }

        out.println(template);
    }
}