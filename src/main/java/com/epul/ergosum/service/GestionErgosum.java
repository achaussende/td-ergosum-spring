package com.epul.ergosum.service;

import com.epul.ergosum.meserreurs.MonException;
import com.epul.ergosum.metier.*;
import com.epul.ergosum.persistance.DialogueBd;

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
            String mysql = "SELECT * FROM jouet NATURAL JOIN categorie NATURAL JOIN trancheage WHERE numero=" + id + ";";
            rs = DialogueBd.lecture(mysql);
            if (rs.size() == 7) {
                jouet.setNumero(rs.get(2).toString());
                jouet.setLibelle(rs.get(3).toString());
                Categorie cat = new Categorie();
                cat.setCodecateg(rs.get(1).toString());
                cat.setLibcateg(rs.get(4).toString());
                jouet.setCategorie(cat);
                Trancheage tr = new Trancheage();
                tr.setCodetranche(rs.get(0).toString());
                tr.setAgemax(Integer.valueOf(rs.get(6).toString()));
                tr.setAgemin(Integer.valueOf(rs.get(5).toString()));
                jouet.setTrancheage(tr);
                return jouet;
            }

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return null;
    }


    public void updateJouet(Jouet jouet) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "UPDATE `jouet` SET CODECATEG ='" + jouet.getCategorie().getCodecateg() + "', CODETRANCHE ='" +
                    jouet.getTrancheage().getCodetranche() + "', LIBELLE ='" + jouet.getLibelle() + "' WHERE NUMERO='" + jouet.getNumero() + "';";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public void addJouet(Jouet jouet) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "INSERT INTO `jouet` (`NUMERO`, `CODECATEG`, `CODETRANCHE`, `LIBELLE`) VALUES('" + jouet.getNumero() + "','" + jouet.getCategorie().getCodecateg() + "','" + jouet.getTrancheage().getCodetranche() + "','" + jouet.getLibelle() + "');";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }

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

    public void addCatalogue(String annee, String quantiteDistribuee) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "INSERT INTO `catalogue`(ANNEE, quantiteDistribuee) VALUES('" + annee + "','" + quantiteDistribuee + "');";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public void updateCatalogue(String annee, String quantiteDistribuee) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "UPDATE `catalogue` SET quantiteDistribuee ='" + quantiteDistribuee + "' WHERE ANNEE ='" + annee + "'";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public Catalogue findCatalogue(String annee) {
        List<Object> rs;
        int index = 2;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT * FROM catalogue WHERE annee = '" + annee + "';";
            rs = bd.lecture(mysql);
            if (index == rs.size()) {
                Catalogue catalogue = new Catalogue();
                catalogue.setAnnee(Integer.parseInt(rs.get(0).toString()));
                catalogue.setQuantiteDistribuee(Integer.parseInt(rs.get(1).toString()));
                return catalogue;
            } else return null;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
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
            String mysql = "SELECT c.CODECATEG, LIBCATEG,COUNT(NUMERO) FROM `categorie`c LEFT JOIN jouet j ON j.CODECATEG=c.CODECATEG GROUP BY c.CODECATEG ORDER BY c.CODECATEG*1;";
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
        List<Object> rs;
        ArrayList<Catalogue> catalogues = new ArrayList<Catalogue>();
        int index = 0;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT * FROM catalogue";
            rs = bd.lecture(mysql);
            while (index < rs.size()) {
                Catalogue catalogue = new Catalogue();
                catalogue.setAnnee(Integer.parseInt(rs.get(index).toString()));
                catalogue.setQuantiteDistribuee(Integer.parseInt(rs.get(index + 1).toString()));
                catalogues.add(catalogue);
                index += 2;
            }
            return catalogues;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object listerTousLesJouets(int categorieCode, int trancheCode) {
        return null;
    }


    public boolean existTranche(String text) {
        List<Object> rs;
        ArrayList<Trancheage> trancheages = new ArrayList<Trancheage>();
        int index = 2;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT CODETRANCHE, AGEMIN, AGEMAX FROM `trancheage` WHERE CODETRANCHE='" + text + "';";
            rs = bd.lecture(mysql);
            if (index == rs.size() - 1) {
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
            String mysql = "UPDATE `trancheage` SET AGEMIN=" + agemin + ",AGEMAX=" + agemax + " WHERE CODETRANCHE='" + text + "';";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public void insertTranche(String text, Integer agemin, Integer agemax) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "INSERT INTO `trancheage`(CODETRANCHE,AGEMIN,AGEMAX) VALUES('" + text + "'," + agemin + "," + agemax + ");";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public Trancheage findTranche(String text) {
        List<Object> rs;
        ArrayList<Trancheage> trancheages = new ArrayList<Trancheage>();
        int index = 2;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT CODETRANCHE, AGEMIN, AGEMAX FROM `trancheage` WHERE CODETRANCHE='" + text + "';";
            rs = bd.lecture(mysql);
            if (index == rs.size() - 1) {
                Trancheage trancheage = new Trancheage();
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

    public Categorie findCategorie(String text) {
        List<Object> rs;
        int index = 2;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT * FROM `categorie` WHERE CODECATEG='" + text + "';";
            rs = bd.lecture(mysql);
            if (index == rs.size()) {
                Categorie categorie = new Categorie();
                categorie.setCodecateg(rs.get(0).toString());
                categorie.setLibcateg(rs.get(1).toString());
                return categorie;
            } else return null;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existCategorie(String text) {
        List<Object> rs;
        int index = 2;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT * FROM `categorie` WHERE CODECATEG='" + text + "';";
            rs = bd.lecture(mysql);
            if (index == rs.size()) {
                return true;
            } else return false;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateCategorie(String codecateg, String libcateg) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "UPDATE `categorie` SET LIBCATEG='" + libcateg + "' WHERE CODECATEG='" + codecateg + "';";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public void insertCategorie(String codecateg, String libcateg) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "INSERT INTO `categorie` VALUES('" + codecateg + "','" + libcateg + "');";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public void deleteJouet(String numero) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "DELETE FROM comporte WHERE`NUMERO`='" + numero + "';";
            bd.insertionBD(mysql);
            mysql = "DELETE FROM jouet WHERE `NUMERO`='" + numero + "';";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public void addComporte(Integer année, String numero, Integer quantité) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "INSERT INTO comporte VALUES(" + année + ",'" + numero + "'," + quantité + ");";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public void updateComporte(Integer année, String numero, Integer quantité) {
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "UPDATE comporte SET QUANTITE=" + quantité + " WHERE ANNEE=" + année + " AND NUMERO='" + numero + "';";
            bd.insertionBD(mysql);
        } catch (MonException e) {
            e.printStackTrace();
        }
    }

    public boolean existComporte(Integer année, String numero) {
        List<Object> rs;
        int index = 3;
        DialogueBd bd = DialogueBd.getInstance();
        try {
            String mysql = "SELECT * FROM `comporte` WHERE ANNEE=" + année + " AND NUMERO='" + numero + "';";
            rs = bd.lecture(mysql);
            if (index == rs.size()) {
                return true;
            } else return false;
        } catch (MonException e) {
            e.printStackTrace();
        }
        return false;
    }
}
