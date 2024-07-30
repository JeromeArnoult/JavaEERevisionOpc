package com.octest.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.octest.bdd.Noms;
import com.octest.beans.Utilisateur;
import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;

@WebServlet(name = "ServletSql", urlPatterns = {"/sql"})
/**
 * Servlet implementation class ServletSql
 */
public class ServletSql extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private UtilisateurDao utilisateurDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public void init() throws ServletException {
	        DaoFactory daoFactory = DaoFactory.getInstance();
	        this.utilisateurDao = daoFactory.getUtilisateurDao();
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("utilisateurs", utilisateurDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/TestSql.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	Utilisateur utilisateur = new Utilisateur();
	        utilisateur.setNom(request.getParameter("nom"));
	        utilisateur.setPrenom(request.getParameter("prenom"));
	        
	        utilisateurDao.ajouter(utilisateur);
	        
	        request.setAttribute("utilisateurs", utilisateurDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/TestSql.jsp").forward(request, response);
	}

}
