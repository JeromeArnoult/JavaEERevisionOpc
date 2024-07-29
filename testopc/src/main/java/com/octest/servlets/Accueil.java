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

@WebServlet(name = "Accueil", urlPatterns = {"/"})
// Annotation pour indiquer que cette servlet gère les téléchargements de fichiers
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // Taille de fichier à partir de laquelle le fichier est écrit sur le disque (1 MB)
    maxFileSize = 1024 * 1024 * 10,      // Taille maximale d'un fichier (10 MB)
    maxRequestSize = 1024 * 1024 * 15    // Taille maximale de la requête (15 MB)
)
public class Accueil extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Constante pour la taille du tampon (10 KB)
    public static final int TAILLE_TAMPON = 10240;
    // Chemin où les fichiers seront stockés
    public static final String CHEMIN_FICHIERS = "/Users//jerom/git/JavaEERevisionOpc/testopc/src/main/webapp/WEB-INF/upload/";

    // Constructeur par défaut
    public Accueil() {
        super();
    }

    // Méthode doGet pour gérer les requêtes GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirige vers la page accueil.jsp
        this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

    // Méthode doPost pour gérer les requêtes POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de la description du fichier depuis le formulaire
        String description = request.getParameter("description");
        request.setAttribute("description", description);

        // Récupération de la partie du fichier depuis la requête
        Part part = request.getPart("fichier");

        // Vérification que la partie du fichier n'est pas nulle
        if (part != null) {
            // Récupération du nom du fichier
            String nomFichier = getNomFichier(part);

            // Vérification que le nom du fichier n'est pas vide ou nul
            if (nomFichier != null && !nomFichier.isEmpty()) {
                String nomChamp = part.getName();
                // Traitement pour corriger les bugs avec Internet Explorer
                nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                        .substring(nomFichier.lastIndexOf('\\') + 1);

                // Écriture du fichier sur le disque
                ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

                // Ajout du nom du fichier comme attribut de la requête
                request.setAttribute(nomChamp, nomFichier);
            }
        } else {
            // Si aucun fichier n'est téléchargé, ajout d'un message d'erreur
            request.setAttribute("error", "No file uploaded");
        }

        // Redirige vers la page bonjour.jsp
        this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

    // Méthode pour écrire le fichier sur le disque
    private void ecrireFichier(Part part, String nomFichier, String chemin) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            // Création des flux d'entrée et de sortie avec des tampons
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            // Lecture du fichier depuis le flux d'entrée et écriture dans le flux de sortie
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            // Fermeture des flux dans le bloc finally pour s'assurer qu'ils sont toujours fermés
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

    // Méthode pour extraire le nom du fichier à partir de l'en-tête Content-Disposition
    private static String getNomFichier(Part part) {
        if (part != null) {
            // Parcours des en-têtes Content-Disposition pour trouver le nom du fichier
            for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
                if (contentDisposition.trim().startsWith("filename")) {
                    // Extraction du nom du fichier en supprimant les guillemets
                    return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
        }
        return null;
    }
}
