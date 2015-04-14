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
import java.util.*;


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
            if(request.getParameter("DJouet") != null) {
                request.setAttribute("jouet", unService.rechercherJouet(request.getParameter("DJouet")));
                request.setAttribute("showJ", true);
            } else {
                request.setAttribute("jouet", null);
                request.setAttribute("showJ", false);
            }
            // on passe les num�ros de client et de vendeur
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
            destinationPage = "/AjouterJouet";
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
            boolean modification;
            if (unService != null) {

                // fabrication du jouet � partir des param�tres de la requ�te
                // Si le jouet n'est pas � cr�er, il faut le r�cup�rer de la session
                // courante
                // Ensuite on peut modifier ses champs
                unJouet = new Jouet();
                unJouet.setNumero(id);
                unJouet.setLibelle(request.getParameter("libelle"));

                Categorie uneCateg = unService.findCategorie(request.getParameter("codecateg"));
                unJouet.setCategorie(uneCateg);
                Trancheage uneTranche = unService.findTranche(request.getParameter("codetranche"));
                unJouet.setTrancheage(uneTranche);

                // sauvegarde du jouet
                Jouet jouet = unService.rechercherJouet(id);
                if (jouet != null) {
                    unService.updateJouet(unJouet);
                    int quantite = Integer.parseInt(request.getParameter("quantiteDistribution"));
                    unService.updateComporte(Integer.parseInt(request.getParameter("codecatalogue")), unJouet.getNumero(), Integer.parseInt(request.getParameter("quantiteDistribution")));
                } else {
                    int annee = Calendar.getInstance().get(Calendar.YEAR);
                    Catalogue leCatalogue = unService.findCatalogue(request.getParameter("codecatalogue"));
                    if (leCatalogue == null) {
                        leCatalogue = new Catalogue(annee, 0);
                    }
                    int quantiteDistribution = Integer.parseInt(request.getParameter("quantiteDistribution"));
                    if (quantiteDistribution > 0) {
                        leCatalogue.setQuantiteDistribuee(leCatalogue.getQuantiteDistribuee() + quantiteDistribution);
                        unService.updateCatalogue(String.valueOf(leCatalogue.getAnnee()), String.valueOf(leCatalogue.getQuantiteDistribuee()));
                    }
                    unService.addJouet(unJouet);
                    unService.addComporte(Integer.parseInt(request.getParameter("codecatalogue")), unJouet.getNumero(), Integer.parseInt(request.getParameter("quantiteDistribution")));
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
        if (request.getParameter("mtranche") != null) {
            Trancheage mtranche = unService.findTranche(request.getParameter("mtranche"));
            request.setAttribute("tranche", mtranche);
            return new ModelAndView(destinationPage);
        } else {
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
        if (request.getParameter("mcategorie") != null) {
            Categorie mcategorie = unService.findCategorie(request.getParameter("mcategorie"));
            request.setAttribute("categorie", mcategorie);
            return new ModelAndView(destinationPage);
        } else {
            //if it's a void add asking.
            Categorie categorie = new Categorie();
            categorie.setCodecateg("0");
            categorie.setLibcateg("");
            request.setAttribute("categorie", categorie);
        }
        if (request.getParameter("codecateg") != null && request.getParameter("libcateg") != null) {
            if (unService.existCategorie(request.getParameter("codecateg"))) {
                unService.updateCategorie(request.getParameter("codecateg"), request.getParameter("libcateg"));
            } else {
                unService.insertCategorie(request.getParameter("codecateg"), request.getParameter("libcateg"));
            }
            ArrayList<Categorie> categories = unService.listerToutesLesCategories();
            request.setAttribute("categories", categories);
            destinationPage = "/AfficherCategories";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "deleteJouet.htm")
    public  ModelAndView deleteJouet(HttpServletRequest request,
                                     HttpServletResponse response) {
        String destinationPage = "/ListeJouets";
        GestionErgosum unService = new GestionErgosum();
        if(request.getParameter("DJouet")!=null){
            unService.deleteJouet(request.getParameter("DJouet"));
        }
        ArrayList<Jouet> jouets = unService.listerTousLesJouets();
        request.setAttribute("mesJouets", jouets);
        return new ModelAndView(destinationPage);
    }

}
