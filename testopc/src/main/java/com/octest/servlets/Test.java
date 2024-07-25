package com.octest.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.octest.beans.Auteur;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Test de récupération d'un paramètre via l'URL
		String name = request.getParameter("name");
		request.setAttribute("name", name);
		String[] noms = {"Bob", "Roxy", "Jake"};
		request.setAttribute("noms", noms);
		
		//Test avec un objet (beans)
		Auteur auteur = new Auteur();
		auteur.setPrenom("Jérôme");
		auteur.setNom("Marchand");
		auteur.setActif(true);
		
		//On envoi l'objet à le Jsp
		request.setAttribute("auteur", auteur);
		//Test array jstl
		String[] titres = {"Nouvel incendie", "Une île a été découverte", "Chute du dollar"};
		request.setAttribute("titres", titres);
		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nom = request.getParameter("nom");
		
		request.setAttribute("nom", nom);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	}

}
