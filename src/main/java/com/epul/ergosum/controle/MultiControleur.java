package com.epul.ergosum.controle;

import com.epul.ergosum.metier.*;
import com.epul.ergosum.service.GestionErgosum;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


/**
 * Created by Antoine CARON on 23/03/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Controller
public class MultiControleur extends MultiActionController {

    /**
     * Simply selects the home view to render by returning its name.
     */
    private Jouet unJouet;

    @RequestMapping("favicon.ico")
    String favicon() {
        return "forward:/resources/images/favicon.ico";
    }

    @RequestMapping("/")
    String index() {
        return "/index";
    }


    @RequestMapping(value = "/index.htm")
    public String home(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "/index";
    }

    /**
     * Affichage de tous les jouets
     */
    @RequestMapping(value = "afficherJouets.htm")
    public ModelAndView afficherLesJouets(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        String destinationPage = "";
        GestionErgosum unService = new GestionErgosum();
        if (unService != null) {
            int categorieCode;
            int trancheCode;
            String categorie = request.getParameter("codecateg");
            String tranche = request.getParameter("codetranche");
            if (categorie == null && tranche == null) {
                categorieCode = 0;
                trancheCode = 0;
            } else {
                categorieCode = Integer.parseInt(categorie);
                trancheCode = Integer.parseInt(tranche);
            }
            ArrayList<Jouet> jouets = unService.listerTousLesJouets();
            request.setAttribute("mesJouets", jouets);

            request.setAttribute("categories", unService.listerToutesLesCategories());
            request.setAttribute("tranches", unService.listerToutesLesTranches());
        }
        destinationPage = "/ListeJouets";

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "afficherTrancheAge.htm")
    public ModelAndView afficherTrancheAge(HttpServletRequest request,
                                           HttpServletResponse response) {
        String destinationPage = "/Erreur";
        GestionErgosum unService = new GestionErgosum();
        ArrayList<Trancheage> trancheages = unService.listerToutesLesTranches();
        if (!trancheages.isEmpty()) {
            request.setAttribute("mesTranchesAge", trancheages);
            destinationPage = "/AfficherTrancheAge";
        }
        return new ModelAndView(destinationPage);
    }

    /**
     * Ajout d'un jouet
     */
    @RequestMapping(value = "ajouterJouet.htm")
    public ModelAndView ajoutJouet(HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        String destinationPage = "";


        GestionErgosum unService = new GestionErgosum();

        if (unService != null) {
            // on passe les num�ros de client et de vendeur
            request.setAttribute("jouet", new Jouet());
            request.setAttribute("categories", unService.listerToutesLesCategories());
            request.setAttribute("tranches", unService.listerToutesLesTranches());
            request.setAttribute("catalogues", unService.listerTousLesCatalogues());

            destinationPage = "/AjouterJouet";
        }


        return new ModelAndView(destinationPage);
    }

    /**
     * S�lection d'une ann�e par cat�gorie
     */
    @RequestMapping(value = "selectionnerAnnee.htm")

    public ModelAndView selectionAnnee(HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {

        String destinationPage = "";
        destinationPage = "/selectionAnneeCat";
        return new ModelAndView(destinationPage);
    }

    /**
     * S�lection d'une ann�e Ctagoriet
     */
    @RequestMapping(value = "listerCatalogue.htm")

    public ModelAndView choixCatalogue(HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {

        String destinationPage = "/Erreur";
        GestionErgosum unService = new GestionErgosum();

        if (unService != null)
            request.setAttribute("catalogues", unService.listerTousLesCatalogues());
        destinationPage = "/ChoixCatalogue";


        return new ModelAndView(destinationPage);
    }


    /**
     * Modifier Jouet
     */
    @RequestMapping(value = "modifierJouet.htm")

    public ModelAndView modifierJouet(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        String destinationPage = "Erreur";
        String id = request.getParameter("id");
        GestionErgosum unService = new GestionErgosum();

        if (unService != null) {
            Jouet unJouet = unService.rechercherJouet(id);
            request.setAttribute("jouet", unJouet);
            request.setAttribute("categories", unService.listerToutesLesCategories());
            request.setAttribute("tranches", unService.listerToutesLesTranches());
            destinationPage = "/ModifierJouet";
        }
        return new ModelAndView(destinationPage);
    }

    /**
     * Sauver jouet
     */
    @RequestMapping(value = "sauverJouet.htm")

    public ModelAndView sauverJouet(HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        String destinationPage = "/Erreur";
        try {
            String id = request.getParameter("id");
            GestionErgosum unService = new GestionErgosum();
            if (unService != null) {

                // fabrication du jouet � partir des param�tres de la requ�te
                // Si le jouet n'est pas � cr�er, il faut le r�cup�rer de la session
                // courante
                // Ensuite on peut modifier ses champs

                if (request.getParameter("type").equals("ajout"))
                    unJouet = new Jouet();
                else { // on r�cup�re le jouet courant

                    unJouet = unService.rechercherJouet(id);
                }
                unJouet.setNumero(request.getParameter("id"));
                unJouet.setLibelle(request.getParameter("libelle"));
                System.out.println("codecateg=" + request.getParameter("codecateg"));
                System.out.println("codetranche=" + request.getParameter("codetranche"));
                Categorie uneCateg = unService.rechercherCategorie(request.getParameter("codecateg"));
                unJouet.setCategorie(uneCateg);

                Trancheage uneTranche = unService.rechercherTrancheage(request.getParameter("codetranche"));
                unJouet.setTrancheage(uneTranche);

                // sauvegarde du jouet
                if (request.getParameter("type").equals("modif")) {
                    unService.modifier(unJouet);
                } else {

                    Catalogue leCatalogue = unService.rechercherCatalogue(request.getParameter("codecatalogue"));
                    System.out.println("Je suis � la quantit� ");
                    ;
                    int quantiteDistribution = Integer.parseInt(request.getParameter("quantiteDistribution"));
                    if (quantiteDistribution > 0) {
                        leCatalogue.setQuantiteDistribuee(leCatalogue.getQuantiteDistribuee() + quantiteDistribution);
                        unService.modifierCatalogue(leCatalogue);
                    }
                    unService.ajouter(unJouet);
                }
                request.setAttribute("mesJouets", unService.listerTousLesJouets());
                destinationPage = "/ListeJouets";

            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
        }

        return new ModelAndView(destinationPage);
    }


    /**
     * effacer  jouet
     */
    @RequestMapping(value = "effacerJouet.htm")
    public ModelAndView effacerJouet(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            String id = request.getParameter("id");
            GestionErgosum unService = new GestionErgosum();
            if (unService != null) {
                // recuperation de la liste des id a effacer
                String[] ids = request.getParameterValues("id");
                // effacement de la liste des id
                if (ids != null) {
                    unService.effacer(ids);
                }
                // preparation de la liste
                request.setAttribute("mesJouets", unService.listerTousLesJouets());
                destinationPage = "/ListeJouets";
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
        }
        return new ModelAndView(destinationPage);
    }

    /**
     * afficher Catalogue
     */
    @RequestMapping(value = "afficherCatalogues.htm")
    public ModelAndView afficherCatalogue(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "/Erreur";
        String id = request.getParameter("id");
        GestionErgosum unService = new GestionErgosum();
        int anneeDebut = Integer.parseInt(request.getParameter("anneeDebut"));
        int nbAnnee = Integer.parseInt(request.getParameter("nbAnnees"));
        if (anneeDebut >= 2000 && nbAnnee >= 0) {
            if (unService != null) {
                // preparation de la liste
                request.setAttribute("mesCataloguesQuantites", unService.listerCatalogueQuantites(anneeDebut, nbAnnee));
                ArrayList<CatalogueQuantites> quantites = unService.listCatalogueByCateg(anneeDebut, nbAnnee);
                request.setAttribute("catégorieQuantité", quantites);
                destinationPage = "/AfficherCatalogues";
            }
        }
        return new ModelAndView(destinationPage);
    }

    /**
     * afficher le Dictionnaire
     */
    @RequestMapping(value = "afficherDictionnaire.htm")
    public ModelAndView afficherDictionnaire(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        String destinationPage = "/Erreur";
        String annee = request.getParameter("annee");
        GestionErgosum unService = new GestionErgosum();
        if (unService != null) {

            HashMap<Categorie, Integer> hashCatInt = unService.rechercherDictionnaire(request.getParameter("annee"));
            request.setAttribute("dictionnaire", hashCatInt);
            request.setAttribute("anneecatalogue", annee);
            destinationPage = "/AfficherDictionnaire";
        }
        return new ModelAndView(destinationPage);
    }

    /**
     * afficher le Dictionnaire
     */
    @RequestMapping(value = "findJouets.htm")
    public ModelAndView findJouet(HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        String destinationPage = "/Erreur";
        String name = request.getParameter("search");
        GestionErgosum unService = new GestionErgosum();
        if (unService != null) {
            ArrayList<Jouet> jouets = unService.findJouet(name);
            if (jouets != null && jouets.isEmpty()) {
                request.setAttribute("search", name);
                destinationPage = "/AucunJouet";
            } else {
                request.setAttribute("mesJouets", jouets);
                destinationPage = "/ListeJouets";
            }
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "afficherCatégories.htm")
    public ModelAndView afficherCatégories(HttpServletRequest request,
                                           HttpServletResponse response) {
        String destinationPage = "/Erreur";
        GestionErgosum unService = new GestionErgosum();
        ArrayList<Categorie> categories;
        categories = unService.listerToutesLesCategories();
        if (categories != null && !categories.isEmpty()) {
            request.setAttribute("categories", categories);
            destinationPage = "/AfficherCategories";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "addTranche.htm")
    public ModelAndView addTranche(HttpServletRequest request,
                                   HttpServletResponse response) {
        String destinationPage = "/AjoutTranche";
        GestionErgosum unService = new GestionErgosum();
        //If it's a modify view asking.
        if(request.getParameter("mtranche")!=null){
            Trancheage mtranche=unService.findTranche(request.getParameter("mtranche"));
            request.setAttribute("tranche", mtranche);
            return new ModelAndView(destinationPage);
        }else{
            //if it's a void add asking.
            Trancheage tranche = new Trancheage();
            tranche.setCodetranche("0");
            tranche.setAgemax(0);
            tranche.setAgemin(0);
            request.setAttribute("tranche", tranche);
        }
        //If it is a modify or add action.
        if (request.getParameter("tranche") != null && request.getParameter("agemin") != null && request.getParameter("agemax") != null) {
            if (unService.existTranche(request.getParameter("tranche"))) {
                unService.updateTranche(request.getParameter("tranche"),
                        Integer.parseInt(request.getParameter("agemin")),
                        Integer.parseInt(request.getParameter("agemax")));
            } else {
                unService.insertTranche(request.getParameter("tranche"),
                        Integer.parseInt(request.getParameter("agemin")),
                        Integer.parseInt(request.getParameter("agemax")));
            }
            ArrayList<Trancheage> trancheages = unService.listerToutesLesTranches();
            request.setAttribute("mesTranchesAge", trancheages);
            destinationPage = "AfficherTrancheAge";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "addCategorie.htm")
    public ModelAndView addCategorie(HttpServletRequest request,
                                   HttpServletResponse response) {
        String destinationPage = "/AjoutCategorie";
        GestionErgosum unService = new GestionErgosum();
        if(request.getParameter("mcategorie")!=null){
            Categorie mcategorie=unService.findCategorie(request.getParameter("mcategorie"));
            request.setAttribute("categorie", mcategorie);
            return new ModelAndView(destinationPage);
        }else{
            //if it's a void add asking.
            Categorie categorie =new Categorie();
            categorie.setCodecateg("0");
            categorie.setLibcateg("");
            request.setAttribute("categorie", categorie);
        }
        if(request.getParameter("codecateg") != null && request.getParameter("libcateg") != null){
            if(unService.existCategorie(request.getParameter("codecateg"))){
                unService.updateCategorie(request.getParameter("codecateg"), request.getParameter("libcateg"));
            }else{
                unService.insertCategorie(request.getParameter("codecateg"),request.getParameter("libcateg"));
            }
            ArrayList<Categorie>categories = unService.listerToutesLesCategories();
            request.setAttribute("categories", categories);
            destinationPage = "/AfficherCategories";
        }
        return new ModelAndView(destinationPage);
    }
}
