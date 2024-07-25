package com.octest.forms;

import jakarta.servlet.http.HttpServletRequest;

public class ConnectionForm {
    
    private String resultat;
    
    public void verifierIdentifiant(HttpServletRequest request) {
        
        String login = request.getParameter("login").trim();
        String pass = request.getParameter("pass").trim();
        
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

}
