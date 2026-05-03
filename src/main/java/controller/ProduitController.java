package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import model.Produit;
import model.User;
import services.ProduitMetier;

@Controller
public class ProduitController {

    @Autowired
    ProduitMetier services;
    
    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        return user != null && user.isAdmin();
    }
    
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("loggedUser") != null;
    }

    @RequestMapping(value={"/", "/index"})
    public String pageIndex(Model model, HttpSession session){
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        model.addAttribute("listeProduit", services.getAllProduits());
        model.addAttribute("currentUser", session.getAttribute("loggedUser"));
        return "produits";
    }

    @RequestMapping(value="/searchProduct")
    public String searchProduct(Model model, @RequestParam(value ="idProduit") Long id, HttpSession session){
        if (!isLoggedIn(session)) return "redirect:/login";
        
        List<Produit> liste = new ArrayList<Produit>();
        Produit p = services.getProduitById(id);
        if (p != null) {
            liste.add(p);
        }
        model.addAttribute("listeProduit", liste);
        model.addAttribute("idProduit", id);
        model.addAttribute("currentUser", session.getAttribute("loggedUser"));
        return "produits";
    }

    @RequestMapping(value="/addProduct")
    public String addProduct(Model model, Produit p, HttpSession session){
        if (!isAdmin(session)) return "redirect:/index?unauthorized=true";
        
        services.addProduit(p);
        model.addAttribute("listeProduit", services.getAllProduits());
        model.addAttribute("currentUser", session.getAttribute("loggedUser"));
        return "produits";
    }

    @RequestMapping(value="/deleteProduit")
    public String supprimerProduit(Model model, @RequestParam Long id, HttpSession session){
        if (!isAdmin(session)) return "redirect:/index?unauthorized=true";
        
        services.deleteProduit(id);
        model.addAttribute("listeProduit", services.getAllProduits());
        model.addAttribute("currentUser", session.getAttribute("loggedUser"));
        return "produits";
    }

    @RequestMapping(value="/editProduit", method=RequestMethod.GET)
    public String editProduit(Model model, @RequestParam Long id, HttpSession session){
        if (!isAdmin(session)) return "redirect:/index?unauthorized=true";
        
        Produit p = services.getProduitById(id);
        model.addAttribute("produitEdit", p);
        model.addAttribute("listeProduit", services.getAllProduits());
        model.addAttribute("currentUser", session.getAttribute("loggedUser"));
        return "produits";
    }

    @RequestMapping(value="/updateProduit", method=RequestMethod.POST)
    public String updateProduitPost(Model model,
            @RequestParam Long idProduit,
            @RequestParam String nom,
            @RequestParam String description,
            @RequestParam Double prix,
            HttpSession session) {
        
        if (!isAdmin(session)) return "redirect:/index?unauthorized=true";
        
        Produit p = new Produit();
        p.setIdProduit(idProduit);
        p.setNom(nom);
        p.setDescription(description);
        p.setPrix(prix);
        
        services.updateProduit(p);
        model.addAttribute("listeProduit", services.getAllProduits());
        model.addAttribute("currentUser", session.getAttribute("loggedUser"));
        return "produits";
    }
}
