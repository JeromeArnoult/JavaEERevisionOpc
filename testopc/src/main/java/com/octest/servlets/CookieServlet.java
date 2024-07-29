package com.octest.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.IOException;

@WebServlet(name = "CookieServlet", urlPatterns = {"/cookie"})
/**
 * Servlet implementation class CookieServlet
 */
public class CookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("prenom")) {
                    request.setAttribute("prenom", cookie.getValue());
                }
            }
        }
    
        this.getServletContext().getRequestDispatcher("/WEB-INF/TestCookie.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	  String nom = request.getParameter("nom");
          String prenom = request.getParameter("prenom");
          
          Cookie cookie = new Cookie("prenom", prenom);
          cookie.setMaxAge(60 * 60 * 24 * 30);
          response.addCookie(cookie);

        this.getServletContext().getRequestDispatcher("/WEB-INF/TestCookie.jsp").forward(request, response);
    }
}
