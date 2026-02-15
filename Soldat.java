import java.util.Scanner;

class Arme {
    String nom;
    int degats;

    public Arme(String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
    }
}
class Soldat {
    String nom;
    int vie;
    int position;
    Arme arme;

    public Soldat(String nom, Arme arme) {
        this.nom = nom;
        this.vie = 100;
        this.position = 0;
        this.arme = arme;
    }

    public void avancer() {
        position += 1;
        System.out.println(nom + " avance à la position " + position);
    }

    public void reculer() {
        if(position > 0) position -= 1;
        System.out.println(nom + " recule à la position " + position);
    }

    public void attaquer(Soldat ennemi) {
        ennemi.vie -= arme.degats;
        System.out.println(nom + " attaque " + ennemi.nom + " avec " + arme.nom + " ! Vie restante de " + ennemi.nom + ": " + ennemi.vie);
    }

    public boolean estVivant() {
        return vie > 0;
    }

    public void changerArme(Arme nouvelleArme) {
        arme = nouvelleArme;
        System.out.println(nom + " change d'arme pour " + arme.nom + " (" + arme.degats + " dégâts)");
    }
}

public class MiniJeuSoldat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Arme epee = new Arme("Épée", 20);
        Arme lance = new Arme("Lance", 30);
        Arme arc = new Arme("Arc", 15);

        Soldat joueur = new Soldat("Joueur", epee);
        Soldat ennemi = new Soldat("Ennemi", lance);

        System.out.println("=== Mini Jeu Soldat avec Armes ===");

        while(joueur.estVivant() && ennemi.estVivant()) {
            System.out.println("\nActions: 1-Avancer 2-Reculer 3-Attaquer 4-Changer Arme");
            int action = sc.nextInt();

            switch(action) {
                case 1 -> joueur.avancer();
                case 2 -> joueur.reculer();
                case 3 -> joueur.attaquer(ennemi);
                case 4 -> {
                    System.out.println("Choisir une arme: 1-Épée 2-Lance 3-Arc");
                    int choixArme = sc.nextInt();
                    switch(choixArme) {
                        case 1 -> joueur.changerArme(epee);
                        case 2 -> joueur.changerArme(lance);
                        case 3 -> joueur.changerArme(arc);
                        default -> System.out.println("Arme invalide !");
                    }
                }
                default -> System.out.println("Action invalide !");
            }

            if(ennemi.estVivant()) {
                double rand = Math.random();
                if(rand < 0.4) ennemi.avancer();
                else if(rand < 0.7) ennemi.reculer();
                else ennemi.attaquer(joueur);
            }
        }

        if(joueur.estVivant()) System.out.println(" Vous avez gagné !");
        else System.out.println("Vous avez perdu !");
    }
}