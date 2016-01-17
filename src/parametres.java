import java.util.Scanner;
/*MAIN*/

public class parametres {

    public static int jeuEntier=1, console, joueur=1, taille=2, pointilles, ordi=1, tracer=1, click1=0, click2=0;
    public static String nomJ1="Joueur 1", nomJ2="Joueur 2";
    public static boolean edit1=false, edit2=false, RecommencerJeu=true;
    // main 
    public static void main(String[] args){    
        
// lance le jeu tant que le boolean est à true
        while (RecommencerJeu){
            
            boolean rejouer = false;
            
            System.out.println("****** DOTS AND BOXES ******\n");
            
            Scanner sc = new Scanner(System.in);
            System.out.println("1 : jouer en mode console \n2 : jouer en mode graphique");
            console=Test(sc.nextLine());
    
            
                if (console ==1) {
                	
                System.out.println("1 : partie contre l'ordinateur \n2 : partie à deux joueurs");
                ordi=Test(sc.nextLine());
                
                System.out.println("Nombre de cases grisées ?\n(celles-ci seront jouées deux fois pour tracer le trait).");
                pointilles=Test(sc.nextLine());
    
                System.out.println("Combien de carrés par ligne et par colonne ?");
                taille=Test(sc.nextLine());
            }
                if (console ==2) {
                finalTourGraphique();
            }
                // Partie du code permettant de determiner le vainqueur en comparant le nombre de point de chacun des joueurs; Permet egalement de continuer à jouer tant que tout le tableau n'est pas remplis
            String[][] fenetre = new String [2*taille+1][2*taille+1];
            int nbrDePoints=0;
            Tableau Tableau1= new Tableau(taille, nbrDePoints, fenetre);
    
            Tableau1.Initialisation(fenetre); 
            if (console==2){ 
                Tableau1.InitialisationGraphique(fenetre);
            }
            Tableau1.Pointilles(pointilles, fenetre);
                    if (ordi==1){    
                while (Tableau1.getnbrDePoints()!=(((fenetre.length-1)/2)*((fenetre.length-1)/2))){
                    rejouer = JoueurVsOrdi(Tableau1, joueur, nbrDePoints, tracer, fenetre);
                    if (rejouer == false){
                        joueur=Changer(joueur);
                    }
                }
            }    
                        if (ordi==2){    
                while (Tableau1.getnbrDePoints()!=(((fenetre.length-1)/2)*((fenetre.length-1)/2))){
                    rejouer = JoueurVsJoueur(Tableau1, joueur, nbrDePoints, fenetre);
                    if (rejouer == false){
                        joueur=Changer(joueur);
                    }
                }
            }
    
                            if (console==1)
                Tableau1.Afficher(fenetre);    
            if (console==1){
                if (ordi==1){
                    if (Tableau1.getpointsDuJoueur1()>Tableau1.getpointsDuJoueur2()){
                        if (Tableau1.getpointsDuJoueur1()==1){
                            System.out.println("Félicitations joueur 1! Vous avez gagné avec "+Tableau1.getpointsDuJoueur1()+" point!");
                        }else{
                            System.out.println("Félicitations joueur 1! Vous avez gagné avec "+Tableau1.getpointsDuJoueur1()+" points!");
                        }
                    }
    
                    if (Tableau1.getpointsDuJoueur2()>Tableau1.getpointsDuJoueur1()){
                        if (Tableau1.getpointsDuJoueur2()==1){
                            System.out.println("Désolé! L'ordinateur vous a battu avec "+Tableau1.getpointsDuJoueur2()+" point(s)!");
                        }
                    }
    
                    if (Tableau1.getpointsDuJoueur1()==Tableau1.getpointsDuJoueur2()){
                        System.out.println("Egalité");
                    }
                }
    
                if (ordi==2){
                    if (Tableau1.getpointsDuJoueur1()>Tableau1.getpointsDuJoueur2()){
                        if (Tableau1.getpointsDuJoueur1()==1){
                            System.out.println("Félicitations joueur 1! Vous avez gagné avec "+Tableau1.getpointsDuJoueur1()+" point(s)!");
                        }
                    }
    
                    if (Tableau1.getpointsDuJoueur2()>Tableau1.getpointsDuJoueur1()){
                        if (Tableau1.getpointsDuJoueur2()==1){
                            System.out.println("Félicitations joueur 2! Vous avez gagné avec "+Tableau1.getpointsDuJoueur2()+" point(s)!");
                    }
    
                    if (Tableau1.getpointsDuJoueur1()==Tableau1.getpointsDuJoueur2()){
                        System.out.println("Egalité");
                    }
                }
                }    
        
// Recommencer le jeu
        System.out.println("\n\nVoulez-vous recommencer le jeu? \nTapez 1 si vous voulez recommencer.\nTapez 2 sinon.");
        jeuEntier=Test(sc.nextLine());
        if (jeuEntier==1){
            RecommencerJeu=true;
        }
        if (jeuEntier==2){
            System.out.println("Game over");
            RecommencerJeu=false;
        }
        
        
            
        }
    }// FIN DU MAIN

    }
    public static boolean JoueurVsJoueur(Tableau Tableau1, int joueur, int nbrDePoints, String[][] fenetre){ 
        boolean finalTour = true; 
        if (console==1){
            while (finalTour){
                Tableau1.Afficher(fenetre); // Determine le tour du joueur en console
                System.out.println("\n");
                System.out.println("Tour du joueur "+joueur);
                finalTour = Tableau1.tour(joueur, fenetre); 
                if (Tableau1.getnbrDePoints()==(((fenetre.length-1)/2)*((fenetre.length-1)/2))){ // Si le tableau est entierement rempli stop la boudle
                    break;             
                }
            }
        }
        if (console==2){
            while (finalTour){ 
                Tableau1.AfficherGraphique(joueur,fenetre);
                finalTour=Tableau1.Hitbox(joueur,fenetre); // Determine le tour du joueur en graphique
                Tableau1.AfficherGraphique(joueur,fenetre);
                StdDraw.clear();
                if (Tableau1.getnbrDePoints()==(((fenetre.length-1)/2)*((fenetre.length-1)/2))){ 
                    break; 
                }
            }
        }
        return false;
    }

    public static boolean JoueurVsOrdi(Tableau Tableau1, int joueur, int nbrDePoints, int tracer, String[][] fenetre){

        boolean finalTour = true;
        boolean rejouer = false;    
        if (console==1){ 
            if (joueur==2){
                System.out.println("\n");
                Tableau1.Afficher(fenetre);
                System.out.println("\n");
                System.out.println("Tour de l'ordi.");
            }

            while (finalTour){ // determine fin du tour
                if (joueur==1){
                    Tableau1.Afficher(fenetre);
                    System.out.println("\n");
                    System.out.println("Tour du joueur 1");
                    finalTour = Tableau1.tour(joueur, fenetre);
                    if (Tableau1.getnbrDePoints()==(((fenetre.length-1)/2)*((fenetre.length-1)/2))){
                        break; 
                    }
                }

                if (joueur==2){ 
                    finalTour = Tableau1.IA(tracer, fenetre);
                    if (Tableau1.getnbrDePoints()==(((fenetre.length-1)/2)*((fenetre.length-1)/2))){
                        break; 
                    }
                }
            }
        }

        if (console==2){
            if (joueur==2){ // L'ia etant aleatoire l'oridnateur test plusieur possibilité permet de n'afficher que celle qui nous interesse
                Tableau1.AfficherGraphique(joueur, fenetre);
            }
            while (finalTour){
                if (joueur==1){
                    Tableau1.AfficherGraphique(joueur, fenetre);
                    finalTour=Tableau1.Hitbox(joueur,fenetre);
                    Tableau1.AfficherGraphique(joueur,fenetre);
                    StdDraw.show(16);
                    if (Tableau1.getnbrDePoints()==(((fenetre.length-1)/2)*((fenetre.length-1)/2))){ 
                        break; 
                    }
                }

                if (joueur==2){
                    finalTour = Tableau1.IA(tracer, fenetre);
                    Tableau1.AfficherGraphique(joueur, fenetre);
                    if (Tableau1.getnbrDePoints()==(((fenetre.length-1)/2)*((fenetre.length-1)/2))){
                        break; 
                    }
                }
            }
            Tableau1.AfficherGraphique(joueur,fenetre);
        }
        return rejouer;
    }

    public static int Test(String test){
        Scanner sc = new Scanner(System.in);
        int test1=0;
        try{
            test1 = Integer.parseInt(test);
        } catch(NumberFormatException e){
            System.out.println(" Veuillez rentrer soit 1, soit 2");
            test1 = Test(sc.next());
        }
        return test1;
    }

    public static int Changer(int joueur){ // changement de joueur
        if (joueur==1){
            joueur=2;
        }else{
            joueur=1;
        }
        return joueur;
    }

    public static void finalTourGraphique(){  // affiche dans l'ordre les deux fenetres graphiques
        graphique.fenetre1(); 
        graphique.fenetre2(); 
    }

    public static boolean DetectPos(double x0, double x1, double y0, double y1){ //Detecte la position de la souris
        double x=StdDraw.mouseX();
        double y=StdDraw.mouseY();
        if (x0<x && x<x1 && y0<y && y<y1){
            return true;
        }else{
            return false;
        }
    }

 }