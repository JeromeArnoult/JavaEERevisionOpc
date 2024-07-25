package com.octest.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet("/Accueil")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 15    // 15 MB
)
public class Accueil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "/Users/jerom/fichierstmp/";

    public Accueil() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        request.setAttribute("description", description);

        Part part = request.getPart("fichier");

        if (part != null) {
            String nomFichier = getNomFichier(part);

            if (nomFichier != null && !nomFichier.isEmpty()) {
                String nomChamp = part.getName();
                nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                        .substring(nomFichier.lastIndexOf('\\') + 1);

                ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

                request.setAttribute(nomChamp, nomFichier);
            }
        } else {
            request.setAttribute("error", "No file uploaded");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

    private void ecrireFichier(Part part, String nomFichier, String chemin) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                if (sortie != null) sortie.close();
            } catch (IOException ignore) {
            }
            try {
                if (entree != null) entree.close();
            } catch (IOException ignore) {
            }
        }
    }

    private static String getNomFichier(Part part) {
        if (part != null) {
            for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
                if (contentDisposition.trim().startsWith("filename")) {
                    return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
        }
        return null;
    }
}
