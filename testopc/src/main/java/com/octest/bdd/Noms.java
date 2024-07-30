package com.octest.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Utilisateur;

public class Noms {

    private Connection connexion;

    // Méthode pour récupérer la liste des utilisateurs
    public List<Utilisateur> recupererUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Statement statement = null;
        ResultSet resultat = null;

     
        loadDatabase();
        
        try {
            statement = connexion.createStatement();

            // Exécution de la requête SQL pour sélectionner les noms et prénoms des utilisateurs
            resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");

            // Récupération des données du résultat de la requête
            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            // Gestion des exceptions SQL
        } finally {
            // Fermeture de la connexion et des ressources
            try {
                if (resultat != null) resultat.close();
                if (statement != null) statement.close();
                if (connexion != null) connexion.close();
            } catch (SQLException ignore) {
               
            }
        }
        
        return utilisateurs; 
    }
    
    // Méthode pour charger la base de données
    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Gestion de l'exception si le driver n'est pas trouvé
        }

        try {
            // Établissement de la connexion avec la base de données
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee", "root", "");
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    
    
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        loadDatabase(); 
        
        try {
            // Préparation de la requête SQL
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom()); // Remplir le premier paramètre (nom)
            preparedStatement.setString(2, utilisateur.getPrenom()); // Remplir le deuxième paramètre (prenom)
            
            preparedStatement.executeUpdate(); // Exécuter la requête d'insertion
        } catch (SQLException e) {
            e.printStackTrace(); // Affichage de la pile d'erreurs en cas de problème d'exécution
        }
    }
}
