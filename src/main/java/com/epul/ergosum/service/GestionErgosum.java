package com.epul.ergosum.service;

import com.epul.ergosum.meserreurs.MonException;
import com.epul.ergosum.metier.Catalogue;
import com.epul.ergosum.metier.Categorie;
import com.epul.ergosum.metier.Jouet;
import com.epul.ergosum.metier.Trancheage;
import com.epul.ergosum.persistance.DialogueBd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Antoine CARON on 23/03/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class GestionErgosum {
    
    public GestionErgosum() {
    }

    public Jouet rechercherJouet(String id) throws MonException {
        List<Object> rs;
        Jouet jouet = new Jouet();
        int index = 0;
        try {
            String mysql = "SELECT * FROM jouet WHERE id="+id+";";

            rs = DialogueBd.lecture(mysql);

            if(rs.size()== 1){
                jouet.getNumero()
            }

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return null;
    }

    public Categorie rechercherCategorie(String codecateg) {
        return null;
    }

    public Trancheage rechercherTrancheage(String codetranche) {
        return null;
    }

    public void modifier(Jouet unJouet) {

    }

    public Catalogue rechercherCatalogue(String codecatalogue) {
        return null;
    }

    public void modifierCatalogue(Catalogue leCatalogue) {
    }

    public void ajouter(Jouet unJouet) {
    }

    public Object listerTousLesJouets() {
        return null;
    }

    public void effacer(String[] ids) {
    }

    public Object listerCatalogueQuantites(int anneeDebut, int nbAnnees) {
        return null;
    }

    public HashMap<Categorie,Integer> rechercherDictionnaire(String annee) {
        return null;
    }

    public Object listerToutesLesCategories() {
        return null;
    }

    public Object listerToutesLesTranches() {
        return null;
    }

    public Object listerTousLesCatalogues() {
        return null;
    }

    public Object listerTousLesJouets(int categorieCode, int trancheCode) {
        return null;
    }
}
