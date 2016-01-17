import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
public class graphique {
	//methode servant à utiliser la souris et gerer l'interface graphique
	// Met en place la methode pour construire les boutons: C'est a dire un rectange d'une couleur definit par sa position dans la fenetre 
    public static void Bouton(double PenRad, Color colorBorder, double xRec, double yRec, double LargRec, double HautRec, Color colorFilled, Color colorText, Color newcolorFilled, Color newcolorText, String text){
        StdDraw.setPenRadius(PenRad);
        StdDraw.setPenColor(colorBorder);
        StdDraw.rectangle(xRec, yRec, LargRec, HautRec);
        StdDraw.setPenColor(colorFilled);
        StdDraw.filledRectangle(xRec, yRec, LargRec, HautRec);
        StdDraw.setPenColor(colorText);
        StdDraw.text(xRec, yRec-5, text);
    
    }
    // Afficheur entre deux boutons utilisé dans la premiere fenetre comme precedemment un rectangle d'une couleur dont on precise les coordonnées dans la fenetre
    public static void AfficherEntreTexte(double PenRad, Color colorBorder, double xRec, double yRec, double LargRec, double HautRec, Color colorFilled, Color colorText, Color newcolorFilled, Color newcolorText, double Xtext, double Ytext, String text){ // Presque Pareil que Bouton(..), sauf que l'on peut choisir la position du texte.
        StdDraw.setPenRadius(PenRad);
        StdDraw.setPenColor(colorBorder);
        StdDraw.rectangle(xRec, yRec, LargRec, HautRec);
        StdDraw.setPenColor(colorFilled);
        StdDraw.filledRectangle(xRec, yRec, LargRec, HautRec);
        StdDraw.setPenColor(colorText);
        StdDraw.textLeft(Xtext, Ytext, text);
    }
    

    // Encadre un texte comme pour le titre
    public static void TextEncadre(Color colorText, int xText, int yText, String text, Font font, Color colorFilledRec, int xRec, int yRec, int LargRec, int HautRec, Color colorRec, double PenRad){ // Pas de Hover si on passe la souris dessus.
        StdDraw.setPenColor(colorFilledRec);
        StdDraw.filledRectangle(xRec, yRec, LargRec, HautRec);
        StdDraw.setPenColor(colorRec);
        StdDraw.setPenRadius(PenRad);
        StdDraw.rectangle(xRec, yRec, LargRec, HautRec);
        StdDraw.setFont(font);
        StdDraw.setPenColor(colorText);
        StdDraw.text(xText, yText, text);

    }

    public static int click1=0, click2=0, incorrect=0;
    public static boolean edit1=false, edit2=false;
    
    public static boolean sourisXY(double x0, double x1, double y0, double y1){ // Permet de definir le "click" d'une souris cela  definit l'intervalle disponible pour cliquer
        double x=StdDraw.mouseX();
        double y=StdDraw.mouseY();
        if (x0<x && x<x1 && y0<y && y<y1){
            return true;
        }else{
            return false;
        }
    }
    
    // definit de la premiere fenetre graphique
    public static void fenetre1(){
        int largeur=848, hauteur=480;
        StdDraw.setCanvasSize(largeur, hauteur); 
        StdDraw.setXscale(-largeur, largeur); 
        StdDraw.setYscale(-hauteur, hauteur); 
    
        Font font = new Font("Arial", Font.PLAIN, 30);
        
        while(true){

         
            StdDraw.filledRectangle(0, 0, 1000, 1000);
            
    
            TextEncadre(StdDraw.BLACK, 0, 475, "Dots and Boxes", font, StdDraw.WHITE, 0, 480, 300, 50, StdDraw.BLACK, 0.005);
            
            
            //J1 contre IA
            StdDraw.setFont(font);
            Bouton(0.004, StdDraw.WHITE, -350, 250, 150, 30, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, StdDraw.WHITE, "Un joueur");
            if(sourisXY(-350, -200, 220, 280) && StdDraw.mousePressed()){
                parametres.ordi=1;
            }
            if (parametres.ordi==1){
                StdDraw.setPenColor(255,255,255);
                StdDraw.setPenColor(0,0,0);
                
            }

            //J1 contre J2
            Bouton(0.004, StdDraw.WHITE, 350, 250, 150, 30, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, StdDraw.WHITE, "Deux joueurs");
            if(sourisXY(220, 370, 210, 290) && StdDraw.mousePressed()){
                parametres.ordi=2;
            }
            if (parametres.ordi==2){
                StdDraw.setPenColor(255,255,255);
                StdDraw.setPenColor(0,0,0);
                
            }

            //taille du jeu en carré
            TextEncadre(StdDraw.BLACK, 0, 120, "Taille désirée", font, StdDraw.WHITE, 0, 80, 260, 92, StdDraw.WHITE, 0.005);

            StdDraw.setFont(font);
            Bouton(0.008, StdDraw.WHITE, -120, 40, 30, 30, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, "-");
            
            if(sourisXY(-150, -90, 10, 70) && StdDraw.mousePressed()){ //Décrémente la taille si le bouton est cliqué.
                if (parametres.taille>2){
                    int temps= parametres.taille-1;
                    while(StdDraw.mousePressed()){
                        parametres.taille=temps;
                    }
                }
            }
            
            StdDraw.setFont(font);
            AfficherEntreTexte(0.008, StdDraw.WHITE, 120, 40, 30, 30, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, 103, 38, "+"); 
            if(sourisXY(90, 150, 10, 70) && StdDraw.mousePressed()){ //Incrémente la taille si le bouton est cliqué.
                if (parametres.taille>=2){
                    int test= parametres.taille+1;
                    while(StdDraw.mousePressed()){
                        parametres.taille=test;
                    }
                }
            }
// Nombre de case grisée voulues
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(0, 35, Integer.toString(parametres.taille));

            TextEncadre(StdDraw.BLACK, 0, -60, "Nombre de cases grisées voulues", font, StdDraw.WHITE, 0, -103, 510, 82, StdDraw.WHITE, 0.005);


            Bouton(0.008, StdDraw.WHITE, -120, -140, 30, 30, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, "-");
            if(sourisXY(-150, -90, -170, -110) && StdDraw.mousePressed()){
                if (parametres.pointilles>0){
                    int test= parametres.pointilles-1;
                    while(StdDraw.mousePressed()){
                        parametres.pointilles=test;
                    }
                }
            }
// Affiche le compteur pour savoir à quel nombre de case nous nous en sommes
            AfficherEntreTexte(0.008, StdDraw.WHITE, 120, -140, 30, 30, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, 103, -140, "+");
            if(sourisXY(90, 150, -170, -110) && StdDraw.mousePressed()){
                if (parametres.pointilles>=0){
                    int test= parametres.pointilles+1;
                    while(StdDraw.mousePressed()){
                        parametres.pointilles=test;
                    }
                }
            }

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(0, -145, Integer.toString(parametres.pointilles));
                StdDraw.setFont(font);
            Bouton(0.01, StdDraw.WHITE, 450, -450, 80, 60, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, "Next");

            //Verifie si les conditions sur la taille sont respectées et lance le second menu
            if (parametres.taille!=0 && parametres.ordi!=0){
                if(sourisXY(370, 530, -510, -390) && StdDraw.mousePressed()){
                    break;
                }
            }    
            StdDraw.show(16);
            StdDraw.clear();
        }
    }
    // seconde fenetre grapgiquye qui affiche le nom des joueurs
    public static void fenetre2(){
        int largeur=848, hauteur=480;
        StdDraw.setCanvasSize(largeur, hauteur); 
        StdDraw.setXscale(-largeur, largeur); 
        StdDraw.setYscale(-hauteur, hauteur); 
        
        Font font = new Font("Arial", Font.PLAIN, 40);
        while (true) {
        	 TextEncadre(StdDraw.BLACK, 0, 475, "Dots and Boxes", font, StdDraw.WHITE, 0, 480, 300, 50, StdDraw.BLACK, 0.005);
                if (parametres.ordi == 1) {

                Font font2 = new Font("Arial", Font.PLAIN, 28);
                        
                // PARTIE ENTRE JOUEUR ET ORDI.
                StdDraw.setFont(font2);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.textLeft(-400, -200, "Joueur 1 : ");
                


                if (click1 == 0) {
                    AfficherEntreTexte(0.004, StdDraw.BLACK, 150, -200, 200, 37, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, 0, -205, parametres.nomJ1);
                }

                if (StdDraw.mousePressed() && sourisXY(-50, 350, -237, -163)) {// Si le joueur clique sur la case à editer.
                    click1 = 1;
                    edit1 = true;
                }

                if (click1 == 1) {
                    TextEncadre(StdDraw.BLACK , 100, -205, parametres.nomJ1, font2, StdDraw.WHITE, 150, -200, 200, 37, StdDraw.BLACK, 0.005);
                }

                if (edit1 == true && click1 == 1) {
                    if (StdDraw.hasNextKeyTyped() && parametres.nomJ1.length() <= 10) {
                        parametres.nomJ1 += StdDraw.nextKeyTyped();
                    }
                    if (StdDraw.isKeyPressed(KeyEvent.VK_BACK_SPACE)) {
                        if (parametres.nomJ1.length() > 0) {
                            parametres.nomJ1 = parametres.nomJ1.substring(0, parametres.nomJ1.length() - 1);
                        }
                    }
                }

                StdDraw.setFont(font);
                Bouton(0.005, StdDraw.WHITE, 0, -465, 250, 50, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, "Valider");
                

                if (sourisXY(-250, 250, -515, -415) && StdDraw.mousePressed()) {
                    if (!parametres.nomJ1.equals("")) {
                        break;
                    } else {
                        if (parametres.nomJ1.equals("")) {
                            incorrect = 1; 
                        }
                    }
                }

                if (incorrect == 1) { // Si non plus grand que 10 caracteres ou ne repondant pas aux conditions
                    if (parametres.nomJ1.equals("")) {
                        StdDraw.setFont(font);
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.text(-135, 100, "Retapez un nom s.v.p pour le J1");
                        StdDraw.setPenColor(206, 255, 255);
                        StdDraw.filledRectangle(150, -200, 200, 37);
                        if (sourisXY(-50, 350, -237, -163)){
                            StdDraw.setPenRadius(0.005);
                            StdDraw.setPenColor(StdDraw.BLACK);
                            StdDraw.rectangle(150, -200, 200, 37);
                            StdDraw.setPenColor(150, 255, 255);
                            StdDraw.filledRectangle(150, -200, 200, 37);
                        }
                    }
                }
                parametres.nomJ2="l'ordi";
            }

            // PARTIE ENTRE 2 JOUEURS

            if (parametres.ordi == 2) {
                Font font2 = new Font("Arial", Font.PLAIN, 28);            
                StdDraw.setFont(font2);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.textLeft(-400, -200, "Joueur 1 : ");

                if (click1 == 0) {
                    AfficherEntreTexte(0.004, StdDraw.BLACK, 150, -200, 200, 37, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, 0, -205, parametres.nomJ1);
                }

                if (StdDraw.mousePressed() && sourisXY(-50, 350, -237, -163)) {// Si le joueur clique sur  la case à editer.
                    StdDraw.setFont(font2);
                    click1 = 1;
                    edit1 = true;
                    edit2 = false;
                }

                if (click1 == 1) {
                    TextEncadre(StdDraw.BLACK , 100, -205, parametres.nomJ1, font2, StdDraw.WHITE, 150, -200, 200, 37, StdDraw.BLACK, 0.005);

                }

                if (edit1 == true && click1 == 1) {
                    if (StdDraw.hasNextKeyTyped() && parametres.nomJ1.length() <= 10) {
                        parametres.nomJ1 += StdDraw.nextKeyTyped();
                    }
                    if (StdDraw.isKeyPressed(KeyEvent.VK_BACK_SPACE)) {
                        if (parametres.nomJ1.length() > 0) {
                            parametres.nomJ1 = parametres.nomJ1.substring(0, parametres.nomJ1.length() - 1);
                        }
                    }
                    click2 = 0;
                }

                StdDraw.setFont(font2);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.textLeft(-400, -300, "Joueur 2 : ");

                if (click2 == 0) { 
                    AfficherEntreTexte(0.004, StdDraw.BLACK, 150, -300, 200, 37, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, 0, -305, parametres.nomJ2);
                }

                if (StdDraw.mousePressed() && sourisXY(-50, 350, -337, -263)) {
                    StdDraw.setFont(font2);
                    click2 = 1;
                    edit1 = false;
                    edit2 = true;
                }

                if (click2 == 1) {
                    TextEncadre(StdDraw.BLACK , 100, -305, parametres.nomJ2, font2, StdDraw.WHITE, 150, -300, 200, 37, StdDraw.BLACK, 0.005);
                }

                if (edit2 == true && click2 == 1) {
                    if (StdDraw.hasNextKeyTyped() && parametres.nomJ2.length() <= 10) {
                        parametres.nomJ2 += StdDraw.nextKeyTyped();
                    }
                    if (StdDraw.isKeyPressed(KeyEvent.VK_BACK_SPACE)) {
                        if (parametres.nomJ2.length() > 0) {
                            parametres.nomJ2 = parametres.nomJ2.substring(0, parametres.nomJ2.length() - 1);
                        }
                    }
                    click1 = 0;
                }
                
                StdDraw.setFont(font);
                Bouton(0.005, StdDraw.WHITE, 0, -465, 250, 50, StdDraw.BLACK, StdDraw.WHITE, StdDraw.BLACK, StdDraw.WHITE, "Valider");

                if (sourisXY(-250, 250, -515, -415) && StdDraw.mousePressed()) {
                    if (!parametres.nomJ1.equals("") && !parametres.nomJ2.equals("")) {
                        break;
                    } else {
                        if (parametres.nomJ1.equals("")) {
                            incorrect = 1;

                        }
                        if (parametres.nomJ2.equals("")) {
                            incorrect = 1;
                        }
                    }
                }

                if (incorrect == 1) {
                    if (parametres.nomJ1.equals("")) {
                        StdDraw.setFont(font);
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.text(-135, 0, "Retapez un nom s.v.p pour le joueur 1");
                        StdDraw.setPenColor(255, 255, 255);
                        StdDraw.filledRectangle(150, -200, 200, 37);
                        if (sourisXY(-50, 350, -237, -163)){
                            StdDraw.setPenColor(255, 255, 255);
                            StdDraw.filledRectangle(150, -200, 200, 37);
                        }
                    }
                    if (parametres.nomJ2.equals("")) {
                        StdDraw.setFont(font);
                        StdDraw.setPenColor(StdDraw.BLACK);
                        StdDraw.text(-135, -75, "Retapez un nom s.v.p pour le joueur 2");
                        StdDraw.setPenColor(255, 255, 255);
                        StdDraw.filledRectangle(150, -300, 200, 37);
                        if (sourisXY(-50, 350, -337, -263)){
                            StdDraw.setPenColor(255, 255, 255);
                            StdDraw.filledRectangle(150, -300, 200, 37);
                        }
                    }
                }
            }
            StdDraw.show(16);
            StdDraw.clear();
        }
    }
    

}