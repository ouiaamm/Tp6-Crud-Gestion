package dao;

import java.util.ArrayList;
import java.util.List;
import model.Produit;

public class ProduitImpl implements ProduitDAO {
    private List<Produit> produits = new ArrayList<Produit>();

    public void init(){
        addProduit(new Produit("PC ", "Dell XPS 13", 15000.0));
        addProduit(new Produit("Water", "Sidi ali", 6.0));
        addProduit(new Produit("Smart Watch", "Samsung", 8000.0));
    }

    public void addProduit(Produit p) {
        p.setIdProduit(new Long(produits.size() + 1));
        produits.add(p);
    }

    public void deleteProduit(Long id) {
        produits.remove(getProduitById(id));
    }

    public Produit getProduitById(Long id) {
        for(Produit p : produits){
            if(p.getIdProduit().equals(id)) return p;
        }
        return null;
    }

    public List<Produit> getAllProduits() {
        return produits;
    }

    public void updateProduit(Produit p) {
        for (int i = 0; i < produits.size(); i++) {
            Produit existing = produits.get(i);
            if (existing.getIdProduit().equals(p.getIdProduit())) {
                existing.setNom(p.getNom());
                existing.setDescription(p.getDescription());
                existing.setPrix(p.getPrix());
                break;
            }
        }
    }
}
