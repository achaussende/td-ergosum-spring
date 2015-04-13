package com.epul.ergosum.service;

import com.epul.ergosum.meserreurs.MonException;
import com.epul.ergosum.metier.*;
import com.epul.ergosum.persistance.DialogueBd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
            String mysql = "SELECT * FROM jouet WHERE id=" + id + ";";

            rs = DialogueBd.lecture(mysql);

            if (rs.size() == 1) {
                jouet.getNumero();
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

    public ArrayList<Jouet> listerTousLesJouets() {
        List<Object> rs;
        ArrayList<Jouet> jouets = new ArrayList<Jouet>();
        int index = 0;
        DialogueBd unDialogueBd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT * FROM jouet NATURAL JOIN categorie NATURAL JOIN trancheage ORDER BY NUMERO*1";
            rs = unDialogueBd.lecture(mysql);
            while (index < rs.size()) {
                // On crée un stage
                Jouet jouet = new Jouet();
                // il faut redecouper la liste pour retrouver les lignes
                jouet.setNumero(rs.get(index + 2).toString());
                jouet.setLibelle(rs.get(index + 3).toString());
                Categorie cat = new Categorie();
                cat.setLibcateg(rs.get(index + 4).toString());
                jouet.setCategorie(cat);
                Trancheage tr = new Trancheage();
                tr.setAgemax(Integer.valueOf(rs.get(index + 6).toString()));
                tr.setAgemin(Integer.valueOf(rs.get(index + 5).toString()));
                jouet.setTrancheage(tr);
                // On incrémente tous les 6 champs
                index = index + 7;
                jouets.add(jouet);
            }
            return jouets;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void effacer(String[] ids) {
    }

    public Object listerCatalogueQuantites(int anneeDebut, int nbAnnees) {
        nbAnnees += anneeDebut;
        List<Object> rs;
        ArrayList<Catalogue> cats = new ArrayList<Catalogue>();
        int index = 0;
        DialogueBd unDialogueBd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT * FROM catalogue WHERE ANNEE>=" + anneeDebut + " AND ANNEE<=" + nbAnnees + " ORDER BY ANNEE";
            rs = unDialogueBd.lecture(mysql);
            while (index < rs.size()) {
                // On crée un stage
                Catalogue catalogue = new Catalogue();
                // il faut redecouper la liste pour retrouver les lignes
                catalogue.setAnnee(Integer.parseInt(rs.get(index).toString()));
                catalogue.setQuantiteDistribuee(Integer.parseInt(rs.get(index + 1).toString()));
                // On incrémente tous les 2 champs
                index = index + 2;
                cats.add(catalogue);
            }
            return cats;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CatalogueQuantites> listCatalogueByCateg(int anneeDebut, int nbAnnées) {
        nbAnnées += anneeDebut;
        List<Object> rs;
        ArrayList<CatalogueQuantites> cats = new ArrayList<CatalogueQuantites>();
        int index = 0;
        DialogueBd unDialogueBd = DialogueBd.getInstance();

        String mysql = "SELECT ANNEE,SUM(QUANTITE),LIBCATEG FROM comporte co NATURAL " +
                " JOIN jouet j JOIN categorie ca ON j.CODECATEG=ca.CODECATEG" +
                " NATURAL JOIN catalogue" +
                " WHERE co.ANNEE>=" + anneeDebut +
                " AND co.ANNEE<=" + nbAnnées +
                " GROUP BY co.ANNEE,ca.LIBCATEG;";
        try {
            rs = unDialogueBd.lecture(mysql);
            while (index < rs.size()) {
                // On crée un stage
                CatalogueQuantites catalogue = new CatalogueQuantites();
                // il faut redecouper la liste pour retrouver les lignes
                catalogue.setId(rs.get(index).toString());
                catalogue.setQuantiteDistribuee(rs.get(index + 1).toString());
                catalogue.setQuantite(rs.get(index + 2).toString());
                // On incrémente tous les 2 champs
                index = index + 3;
                cats.add(catalogue);
            }
            return cats;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Categorie, Integer> rechercherDictionnaire(String annee) {
        return null;
    }

    public ArrayList<Categorie> listerToutesLesCategories() {
        List<Object> rs;
        ArrayList<Categorie> categories = new ArrayList<Categorie>();
        int index = 0;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT j.CODECATEG, LIBCATEG,COUNT(NUMERO) FROM `categorie`c LEFT JOIN jouet j ON j.CODECATEG=c.CODECATEG GROUP BY j.CODECATEG ORDER BY j.CODECATEG*1;";
            rs = bd.lecture(mysql);
            while (index < rs.size()) {
                Categorie cat = new Categorie();
                cat.setCodecateg(rs.get(index).toString());
                cat.setLibcateg(rs.get(index + 1).toString());
                cat.setNbJouets(Integer.valueOf(rs.get(index + 2).toString()));
                categories.add(cat);
                index += 3;
            }
            return categories;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Trancheage> listerToutesLesTranches() {
        List<Object> rs;
        ArrayList<Trancheage> trancheages = new ArrayList<Trancheage>();
        int index = 0;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT t.CODETRANCHE, AGEMIN, AGEMAX, COUNT(NUMERO) FROM `trancheage` t LEFT JOIN jouet j ON t.`CODETRANCHE`=j.`CODETRANCHE` GROUP BY t.CODETRANCHE ORDER BY t.CODETRANCHE*1";
            rs = bd.lecture(mysql);
            while (index < rs.size()) {
                Trancheage trancheage = new Trancheage();
                trancheage.setCodetranche(rs.get(index).toString());
                trancheage.setAgemin(Integer.valueOf(rs.get(index + 1).toString()));
                trancheage.setAgemax(Integer.valueOf(rs.get(index + 2).toString()));
                trancheage.setNbJouets(Integer.valueOf(rs.get(index + 3).toString()));
                trancheages.add(trancheage);
                index += 4;
            }
            return trancheages;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object listerTousLesCatalogues() {
        return null;
    }

    public Object listerTousLesJouets(int categorieCode, int trancheCode) {
        return null;
    }

    public ArrayList<Jouet> findJouet(String name) {
        List<Object> rs;
        ArrayList<Jouet> jouets = new ArrayList<Jouet>();
        int index = 0;
        DialogueBd unDialogueBd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT * FROM jouet NATURAL JOIN categorie NATURAL JOIN trancheage WHERE LIBELLE LIKE '%" + name + "%' ORDER BY NUMERO*1";
            rs = unDialogueBd.lecture(mysql);
            while (index < rs.size()) {
                // On crée un stage
                Jouet jouet = new Jouet();
                // il faut redecouper la liste pour retrouver les lignes
                jouet.setNumero(rs.get(index + 2).toString());
                jouet.setLibelle(rs.get(index + 3).toString());
                Categorie cat = new Categorie();
                cat.setLibcateg(rs.get(index + 4).toString());
                jouet.setCategorie(cat);
                Trancheage tr = new Trancheage();
                tr.setAgemax(Integer.valueOf(rs.get(index + 6).toString()));
                tr.setAgemin(Integer.valueOf(rs.get(index + 5).toString()));
                jouet.setTrancheage(tr);
                // On incrémente tous les 6 champs
                index = index + 7;
                jouets.add(jouet);
            }
            return jouets;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existTranche(String text) {
        List<Object> rs;
        ArrayList<Trancheage> trancheages = new ArrayList<Trancheage>();
        int index = 2;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT CODETRANCHE, AGEMIN, AGEMAX FROM `trancheage` WHERE CODETRANCHE='" + text+"';";
            rs = bd.lecture(mysql);
            if (index == rs.size()-1) {
                return true;
            } else return false;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateTranche(String text, Integer agemin, Integer agemax) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "UPDATE `trancheage` SET AGEMIN=" + agemin + ",AGEMAX=" + agemax + " WHERE CODETRANCHE='" + text+"';";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public void insertTranche(String text, Integer agemin, Integer agemax) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "INSERT INTO `trancheage`(CODETRANCHE,AGEMIN,AGEMAX) VALUES('"+text+"',"+agemin+","+agemax+");";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public Trancheage findTranche(String text){
        List<Object> rs;
        ArrayList<Trancheage> trancheages = new ArrayList<Trancheage>();
        int index = 2;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT CODETRANCHE, AGEMIN, AGEMAX FROM `trancheage` WHERE CODETRANCHE='" + text+"';";
            rs = bd.lecture(mysql);
            if (index == rs.size()-1) {
               Trancheage trancheage=new Trancheage();
                trancheage.setCodetranche(rs.get(0).toString());
                trancheage.setAgemin(Integer.valueOf(rs.get(1).toString()));
                trancheage.setAgemax(Integer.valueOf(rs.get(2).toString()));
                return trancheage;
            } else return null;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
    }

}
