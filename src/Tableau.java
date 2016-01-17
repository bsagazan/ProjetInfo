import java.awt.Font;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;
public class Tableau{
	/*variables*/
	static Scanner question = new Scanner(System.in);
	static int taille,COL=0,LIG=0,nbrDePoints,pointsDuJoueur1,pointsDuJoueur2;
	static boolean rejouer,finalTour=true;

	/*Tableau*/
	public Tableau(int taille, int nbrDePoints, String[][] affichage){
	this.taille=taille;
	this.rejouer=false;
	this.nbrDePoints=nbrDePoints;
	this.pointsDuJoueur1=pointsDuJoueur1;
	this.pointsDuJoueur2=pointsDuJoueur2;
	}
	
	/*Fonctions*/
		/*fonction pour jouer entre deux joueurs*/

	public boolean tour(int joueur, String affichage[][]){ /*Gere le tour de chaque joueur.*/
		LIG=COL;
		while((LIG==COL || (LIG%2==0 && COL%2==0) || (LIG%2==1 && COL%2==1)) &&joueur==1){
		System.out.println("entrez une ligne et une colonne pour jouer");
		LIG=question.nextInt(); COL=question.nextInt();}
		finalTour = afficherTableau(LIG, COL, joueur, affichage); /*on et à jour le tableau pour afficher le jeu*/
		return finalTour; /*/ et on finit le tour*/
	}
	
	public static boolean afficherTableau(int LIG, int COL, int joueur, String[][] affichage){ /*affichage du tableau*/
		if (taille<5){		
			rejouer = afficherTraits(LIG, COL, joueur, affichage);/*on detecte si le point est là ou pas*/
			if ((LIG)%2==0 && (COL)%2!=0){
				if (affichage[LIG-1][COL-1]!="+ ") affichage[LIG-1][COL-1]="| ";/* on affiche un trait vetical*/
				else affichage[LIG-1][COL-1]="  ";/*espace pour que ça soit en face*/
			}
			if ((LIG)%2!=0 && (COL)%2==0){
				if (affichage[LIG-1][COL-1]!=" + ")	affichage[LIG-1][COL-1]="———";
				else affichage[LIG-1][COL-1]="   ";
			}

		nbrDePoints=pointsDuJoueur1+pointsDuJoueur2;
		}
		
		if (taille>=5){ /*qd il y a bcp de lignes*/
			rejouer = afficherTraits(LIG, COL, joueur, affichage); /*on detecte si le point est là ou pas*/
			if ((LIG)%2==0 && (COL)%2!=0){
				if (affichage[LIG-1][COL-1]!="+  ")	affichage[LIG-1][COL-1]="|  ";
				else affichage[LIG-1][COL-1]="   ";}
			if ((LIG)%2!=0 && (COL)%2==0){
				if (affichage[LIG-1][COL-1]!="  +  ") affichage[LIG-1][COL-1]="—————";
				else affichage[LIG-1][COL-1]="     ";
			}
		nbrDePoints=pointsDuJoueur1+pointsDuJoueur2;
		}
		while(true) return rejouer;	//si true, alors on rejoue.
	}
	
	public static boolean afficherTraits(int LIG, int COL, int joueur, String[][] affichage) { 	// verif si on marque un point
		rejouer=false;
		if (taille<5){
			if (COL==1){	
				if (affichage[LIG-1][COL+1]=="| " && affichage[LIG][COL]=="———" && affichage[LIG-2][COL]=="———" && affichage[LIG-1][COL-1]!="+ "){
					affichage[LIG-1][COL]=Integer.toString(joueur)+" ";
					if (joueur==1) pointsDuJoueur1++;
					if (joueur==2) pointsDuJoueur2++;
					rejouer=true;
				}
			}
		
			if (COL==affichage.length){	
				if (affichage[LIG-1][COL-3]=="| " && affichage[LIG-2][COL-2]=="———" && affichage[LIG][COL-2]=="———" && affichage[LIG-1][COL-1]!="+ "){
					affichage[LIG-1][COL-2]=Integer.toString(joueur)+" ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}	
			}
		
			if (LIG==1){	
				if (affichage[LIG][COL-2]=="| " && affichage[LIG+1][COL-1]=="———" && affichage[LIG][COL]=="| "  && affichage[LIG-1][COL-1]!=" + "){
					affichage[LIG][COL-1]=Integer.toString(joueur)+" ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouerr!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}
			}
		
			if (LIG==affichage.length){	
				if (affichage[LIG-2][COL-2]=="| " && affichage[LIG-2][COL]=="| " && affichage[LIG-3][COL-1]=="———" && affichage[LIG-1][COL-1]!=" + "){
					affichage[LIG-2][COL-1]=Integer.toString(joueur)+" ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouerr!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}
			}
		
			if (LIG!=1 && COL!=1 && LIG!=affichage.length && COL!=affichage.length){ //evite les collisions avec les conditions aux bords
				
				if (LIG%2==0 && COL%2!=0){ // si barre verticale tracée
					if ((affichage[LIG-2][COL-2]=="———" && affichage[LIG-1][COL-3]=="| " && affichage[LIG][COL-2]=="———"  && affichage[LIG-1][COL-1]!="+ ") || (affichage[LIG-2][COL]=="———" && affichage[LIG-1][COL+1]=="| " && affichage[LIG][COL]=="———"  && affichage[LIG-1][COL-1]!="+ ")){ 
						if (affichage[LIG-2][COL-2]=="———" && affichage[LIG-1][COL-3]=="| " && affichage[LIG][COL-2]=="———"  && affichage[LIG-1][COL-1]!="+ "){
							affichage[LIG-1][COL-2]=Integer.toString(joueur)+" ";
						}
						if (affichage[LIG-2][COL]=="———" && affichage[LIG-1][COL+1]=="| " && affichage[LIG][COL]=="———"  && affichage[LIG-1][COL-1]!="+ "){
							affichage[LIG-1][COL]=Integer.toString(joueur)+" ";
						}
						if ((affichage[LIG-2][COL-2]=="———" && affichage[LIG-1][COL-3]=="| " && affichage[LIG][COL-2]=="———"  && affichage[LIG-1][COL-1]!="+ ") && (affichage[LIG-2][COL]=="———" && affichage[LIG-1][COL+1]=="| " && affichage[LIG][COL]=="———"  && affichage[LIG-1][COL-1]!="+ ")){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
						//	rejouer=true;
							if (joueur==1){
								pointsDuJoueur1=pointsDuJoueur1+2;
							}
							if (joueur==2){
								pointsDuJoueur2=pointsDuJoueur2+2;
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejouer=true;
							if (joueur==1){
								pointsDuJoueur1++;
							}
							if (joueur==2){
								pointsDuJoueur2++;
	
							}
						}
					rejouer=true;
					}	
				}
			
				if (LIG%2!=0 && COL%2==0){ //barre horizontale tracée
					if ((affichage[LIG-2][COL-2]=="| " && affichage[LIG-3][COL-1]=="———" && affichage[LIG-2][COL]=="| " && affichage[LIG-1][COL-1]!=" + ") || ((affichage[LIG][COL-2]=="| " && affichage[LIG][COL]=="| " && affichage[LIG+1][COL-1]=="———" && affichage[LIG-1][COL-1]!=" + "))){ 
						if (affichage[LIG-2][COL-2]=="| " && affichage[LIG-3][COL-1]=="———" && affichage[LIG-2][COL]=="| " && affichage[LIG-1][COL-1]!=" + "){
							affichage[LIG-2][COL-1]=Integer.toString(joueur)+" ";
						}
						if (affichage[LIG][COL-2]=="| " && affichage[LIG][COL]=="| " && affichage[LIG+1][COL-1]=="———" && affichage[LIG-1][COL-1]!=" + "){
							affichage[LIG][COL-1]=Integer.toString(joueur)+" ";
						}
						
						if ((affichage[LIG-2][COL-2]=="| " && affichage[LIG-3][COL-1]=="———" && affichage[LIG-2][COL]=="| " && affichage[LIG-1][COL-1]!=" + ") && ((affichage[LIG][COL-2]=="| " && affichage[LIG][COL]=="| " && affichage[LIG+1][COL-1]=="———" && affichage[LIG-1][COL-1]!=" + "))){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
							if (joueur==1){
								pointsDuJoueur1=pointsDuJoueur1+2;
							}
							if (joueur==2){
								pointsDuJoueur2=pointsDuJoueur2+2;
	
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejouer=true;
							if (joueur==1){
								pointsDuJoueur1++;
							}
							if (joueur==2){
								pointsDuJoueur2++;
	
							}
						}
					rejouer=true;
					}
				}
			}
		}
		
		if (taille>=5){ // NECESSAIRE POUR LES CAS OU LE Tableau A PLUS DE 10 COLS
			if (COL==1){	
				if (affichage[LIG-1][COL+1]=="|  " && affichage[LIG][COL]=="—————" && affichage[LIG-2][COL]=="—————" && affichage[LIG-1][COL-1]!="+  "){
					affichage[LIG-1][COL]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
					//	System.out.println("Vous pouvez rejouerr!");
					rejouer=true;
				}
			}
		
			if (COL==affichage.length){	
				if (affichage[LIG-1][COL-3]=="|  " && affichage[LIG-2][COL-2]=="—————" && affichage[LIG][COL-2]=="—————" && affichage[LIG-1][COL-1]!="+  "){
					affichage[LIG-1][COL-2]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}	
			}
		
			if (LIG==1){	
				if (affichage[LIG][COL-2]=="|  " && affichage[LIG+1][COL-1]=="—————" && affichage[LIG][COL]=="|  " && affichage[LIG-1][COL-1]!="  +  "){
					affichage[LIG][COL-1]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouerr!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}
			}
		
			if (LIG==affichage.length){	
				if (affichage[LIG-2][COL-2]=="|  " && affichage[LIG-2][COL]=="|  " && affichage[LIG-3][COL-1]=="—————" && affichage[LIG-1][COL-1]!="  +  "){
					affichage[LIG-2][COL-1]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouerr!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}
			}
		
			if (LIG!=1 && COL!=1 && LIG!=affichage.length && COL!=affichage.length){ //evite les collisions avec les conditions aux bords
				
				if (LIG%2==0 && COL%2!=0){ // si barre verticale tracée
					if ((affichage[LIG-2][COL-2]=="—————" && affichage[LIG-1][COL-3]=="|  " && affichage[LIG][COL-2]=="—————" && affichage[LIG-1][COL-1]!="+  ") || (affichage[LIG-2][COL]=="—————" && affichage[LIG-1][COL+1]=="|  " && affichage[LIG][COL]=="—————" && affichage[LIG-1][COL-1]!="+  ")){ // A MODIFIER
						if (affichage[LIG-2][COL-2]=="—————" && affichage[LIG-1][COL-3]=="|  " && affichage[LIG][COL-2]=="—————" && affichage[LIG-1][COL-1]!="+  "){
							affichage[LIG-1][COL-2]=Integer.toString(joueur)+"  ";
						}
						if (affichage[LIG-2][COL]=="—————" && affichage[LIG-1][COL+1]=="|  " && affichage[LIG][COL]=="—————" && affichage[LIG-1][COL-1]!="+  "){
							affichage[LIG-1][COL]=Integer.toString(joueur)+"  ";
						}
						
						if ((affichage[LIG-2][COL-2]=="—————" && affichage[LIG-1][COL-3]=="|  " && affichage[LIG][COL-2]=="—————" && affichage[LIG-1][COL-1]!="+  ") && (affichage[LIG-2][COL]=="—————" && affichage[LIG-1][COL+1]=="|  " && affichage[LIG][COL]=="—————" && affichage[LIG-1][COL-1]!="+  ")){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
							if (joueur==1){
								pointsDuJoueur1=pointsDuJoueur1+2;
							}
							if (joueur==2){
								pointsDuJoueur2=pointsDuJoueur2+2;
	
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejouer=true;
							if (joueur==1){
								pointsDuJoueur1++;
							}
							if (joueur==2){
								pointsDuJoueur2++;
	
							}
						}
					rejouer = true;
					}	
				}
			
				if (LIG%2!=0 && COL%2==0){ //barre horizontale tracée
					if ((affichage[LIG-2][COL-2]=="|  " && affichage[LIG-3][COL-1]=="—————" && affichage[LIG-2][COL]=="|  " && affichage[LIG-1][COL-1]!="  +  ") || ((affichage[LIG][COL-2]=="|  " && affichage[LIG][COL]=="|  " && affichage[LIG+1][COL-1]=="—————" && affichage[LIG-1][COL-1]!="  +  "))){ 
						if (affichage[LIG-2][COL-2]=="|  " && affichage[LIG-3][COL-1]=="—————" && affichage[LIG-2][COL]=="|  " && affichage[LIG-1][COL-1]!="  +  "){
							affichage[LIG-2][COL-1]=Integer.toString(joueur)+"  ";
						}
						if (affichage[LIG][COL-2]=="|  " && affichage[LIG][COL]=="|  " && affichage[LIG+1][COL-1]=="—————" && affichage[LIG-1][COL-1]!="  +  "){
							affichage[LIG][COL-1]=Integer.toString(joueur)+"  ";
						}
						if ((affichage[LIG-2][COL-2]=="|  " && affichage[LIG-3][COL-1]=="—————" && affichage[LIG-2][COL]=="|  " && affichage[LIG-1][COL-1]!="  +  ") && ((affichage[LIG][COL-2]=="|  " && affichage[LIG][COL]=="|  " && affichage[LIG+1][COL-1]=="—————" && affichage[LIG-1][COL-1]!="  +  "))){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
							if (joueur==1){
								pointsDuJoueur1=pointsDuJoueur1+2;
							}
							if (joueur==2){
								pointsDuJoueur2=pointsDuJoueur2+2;
	
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejouer=true;
							if (joueur==1){
								pointsDuJoueur1++;
							}
							if (joueur==2){
								pointsDuJoueur2++;
	
							}
						}
					rejouer=true;
					}
				}
			}
		}
		return rejouer; 
	}
	
	public boolean Verifier(int LIG, int COL, String[][] affichage) {// Vérifie que les LIGs et COLs demandées sont bonnes

		if (LIG>affichage.length || COL>affichage.length || LIG<1 || COL<1){
			System.out.println("Les valeurs entrées ne sont pas dans le tableau! Veuillez rejouerr.");
			return false;
		}
		
		if (taille<5){			
			if (affichage[LIG-1][COL-1]=="| " || affichage[LIG-1][COL-1]=="———"){
				System.out.println("Cette case n'est pas disponible! Elle a déjÃ  été jouée! rejouerz.");
				return false;
			}
		}
		if (taille>=5){			
			if (affichage[LIG-1][COL-1]=="|  " || affichage[LIG-1][COL-1]=="—————"){
				System.out.println("Cette case n'est pas disponible! Elle a déjÃ  été jouée! rejouerz.");
				return false;
			}
		}
		
		if (affichage[LIG-1][COL-1]=="O"){
			System.out.println("Cette case n'est pas disponible! C'est un point! rejouerz.");
			return false;
		}
		if (LIG%2==0 && COL%2==0){
			System.out.println("Cette case n'est pas disponible! Elle est déstinée Ã  la lettre du gagnant de la case! rejouerz.");
			return false;
		}	
		else{		//if infos rentrées sont disponibles, return true. Else false. 
			return true;
		}
	}
	
	
	
		//PARTIE JOUEUR VS ORDI.
	public boolean IA(int difficulté, String[][] affichage) { // Fais marcher l'IA.
		boolean finalTour=true;
		int tableau[]={0,0};
		int LIG=0, COL=0;
		int joueur=2;
		if (difficulté==1){
			LIG = 1 + (int)(Math.random() *(affichage[0].length));
			COL = 1 + (int)(Math.random() *(affichage[0].length));
		}
		
		if (VerifierOrdi(LIG, COL, affichage)){
			System.out.println("L'ordi joue Ã  la LIG : " +LIG + ", et Ã  la COL : " + COL);
			finalTour = afficherTableauOrdi(LIG, COL, joueur, affichage);
		}
	
		return finalTour;
	}

	public boolean afficherTableauOrdi(int LIG, int COL, int joueur, String[][] visuel){ //Met à jour le Tableau selon les valeurs choisies par l'IA
		if (taille<5){		
			rejouer = afficherTraitsOrdi(LIG, COL, joueur, visuel);//doit se situer ici et non pas après sinon l'afficherTableau de la case ne détectera jamais le point.
			if ((LIG)%2==0 && (COL)%2!=0){
				if (visuel[LIG-1][COL-1]!=". "){
					visuel[LIG-1][COL-1]="| ";//NE PAS ENLEVER LESPACE SINON CA FOUT LA MERDE ;_;
				}else{
					System.out.println("Et une case grisée de moins!");
					visuel[LIG-1][COL-1]="  ";//NE PAS ENLEVER LESPACE SINON CA FOUT LA MERDE ;_;
				}
			}
			if ((LIG)%2!=0 && (COL)%2==0){
				if (visuel[LIG-1][COL-1]!=" . "){
					visuel[LIG-1][COL-1]="———";
					}else{
						System.out.println("Et une case grisée de moins!");
						visuel[LIG-1][COL-1]="   ";//NE PAS ENLEVER LESPACE SINON CA FOUT LA MERDE ;_;
				}	
			} 
			// finalTourr de vérifier si la case demandée compléte un carré, auquel cas, le joueur peut rejouerr
		nbrDePoints=pointsDuJoueur1+pointsDuJoueur2;
		}
		
		if (taille>=5){ // NECESSAIRE POUR LES CAS OU LE Tableau A PLUS DE 10 COLS
			rejouer = afficherTraitsOrdi(LIG, COL, joueur, visuel); //doit se situer ici et non pas après sinon l'afficherTableau de la case ne détectera jamais le point.
			if ((LIG)%2==0 && (COL)%2!=0){
				if (visuel[LIG-1][COL-1]!=".  "){
					visuel[LIG-1][COL-1]="|  ";//NE PAS ENLEVER LESPACE SINON CA FOUT LA MERDE ;_;
				}else{
					System.out.println("Et une case grisée de moins!");
					visuel[LIG-1][COL-1]="   ";//NE PAS ENLEVER LESPACE SINON CA FOUT LA MERDE ;_;
				}
			}
			if ((LIG)%2!=0 && (COL)%2==0){
				if (visuel[LIG-1][COL-1]!="  .  "){
					visuel[LIG-1][COL-1]="—————";
					}else{
						System.out.println("Et une case grisée de moins!");
						visuel[LIG-1][COL-1]="     ";//NE PAS ENLEVER LESPACE SINON CA FOUT LA MERDE ;_;
				}	
			}
		nbrDePoints=pointsDuJoueur1+pointsDuJoueur2;
		}
		return rejouer;	
	}

	public boolean afficherTraitsOrdi(int ligne, int colonne, int joueur, String[][] visuel) { 	// Vérifie si la position rentrée par le joueur complète une case ou non
		rejouer=false; // false par défaut, car le joueur ne peut pas rejouer tant qu'il ne coche pas une case.
		if (taille<5){
			if (colonne==1){	
				if (visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="———" && visuel[ligne-2][colonne]=="———" && visuel[ligne-1][colonne-1]!=". "){
					visuel[ligne-1][colonne]=Integer.toString(joueur)+" ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
					//	System.out.println("Vous pouvez rejouer!");
					rejouer=true; // si la case demandée coche une case, return true et permet au joueur de rejouer.
				}
			}
		
			if (colonne==visuel.length){	
				if (visuel[ligne-1][colonne-3]=="| " && visuel[ligne-2][colonne-2]=="———" && visuel[ligne][colonne-2]=="———" && visuel[ligne-1][colonne-1]!=". "){
					visuel[ligne-1][colonne-2]=Integer.toString(joueur)+" ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}	
			}
		
			if (ligne==1){	
				if (visuel[ligne][colonne-2]=="| " && visuel[ligne+1][colonne-1]=="———" && visuel[ligne][colonne]=="| "  && visuel[ligne-1][colonne-1]!=" . "){
					visuel[ligne][colonne-1]=Integer.toString(joueur)+" ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouer!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}
			}
		
			if (ligne==visuel.length){	
				if (visuel[ligne-2][colonne-2]=="| " && visuel[ligne-2][colonne]=="| " && visuel[ligne-3][colonne-1]=="———" && visuel[ligne-1][colonne-1]!=" . "){
					visuel[ligne-2][colonne-1]=Integer.toString(joueur)+" ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouer!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}
			}
		
			if (ligne!=1 && colonne!=1 && ligne!=visuel.length && colonne!=visuel.length){ //evite les collisions avec les conditions aux bords
				
				if (ligne%2==0 && colonne%2!=0){ // si barre verticale tracée
					if ((visuel[ligne-2][colonne-2]=="———" && visuel[ligne-1][colonne-3]=="| " && visuel[ligne][colonne-2]=="———"  && visuel[ligne-1][colonne-1]!=". ") || (visuel[ligne-2][colonne]=="———" && visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="———"  && visuel[ligne-1][colonne-1]!=". ")){ 
						if (visuel[ligne-2][colonne-2]=="———" && visuel[ligne-1][colonne-3]=="| " && visuel[ligne][colonne-2]=="———"  && visuel[ligne-1][colonne-1]!=". "){
							visuel[ligne-1][colonne-2]=Integer.toString(joueur)+" ";
						}
						if (visuel[ligne-2][colonne]=="———" && visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="———"  && visuel[ligne-1][colonne-1]!=". "){
							visuel[ligne-1][colonne]=Integer.toString(joueur)+" ";
						}
						if ((visuel[ligne-2][colonne-2]=="———" && visuel[ligne-1][colonne-3]=="| " && visuel[ligne][colonne-2]=="———"  && visuel[ligne-1][colonne-1]!=". ") && (visuel[ligne-2][colonne]=="———" && visuel[ligne-1][colonne+1]=="| " && visuel[ligne][colonne]=="———"  && visuel[ligne-1][colonne-1]!=". ")){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
						//	rejoue=true;
							if (joueur==1){
								pointsDuJoueur1=pointsDuJoueur1+2;
							}
							if (joueur==2){
								pointsDuJoueur2=pointsDuJoueur2+2;
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejoue=true;
							if (joueur==1){
								pointsDuJoueur1++;
							}
							if (joueur==2){
								pointsDuJoueur2++;
	
							}
						}
					rejouer=true;
					}	
				}
			
				if (ligne%2!=0 && colonne%2==0){ //barre horizontale tracée
					if ((visuel[ligne-2][colonne-2]=="| " && visuel[ligne-3][colonne-1]=="———" && visuel[ligne-2][colonne]=="| " && visuel[ligne-1][colonne-1]!=" . ") || ((visuel[ligne][colonne-2]=="| " && visuel[ligne][colonne]=="| " && visuel[ligne+1][colonne-1]=="———" && visuel[ligne-1][colonne-1]!=" . "))){ 
						if (visuel[ligne-2][colonne-2]=="| " && visuel[ligne-3][colonne-1]=="———" && visuel[ligne-2][colonne]=="| " && visuel[ligne-1][colonne-1]!=" . "){
							visuel[ligne-2][colonne-1]=Integer.toString(joueur)+" ";
						}
						if (visuel[ligne][colonne-2]=="| " && visuel[ligne][colonne]=="| " && visuel[ligne+1][colonne-1]=="———" && visuel[ligne-1][colonne-1]!=" . "){
							visuel[ligne][colonne-1]=Integer.toString(joueur)+" ";
						}
						
						if ((visuel[ligne-2][colonne-2]=="| " && visuel[ligne-3][colonne-1]=="———" && visuel[ligne-2][colonne]=="| " && visuel[ligne-1][colonne-1]!=" . ") && ((visuel[ligne][colonne-2]=="| " && visuel[ligne][colonne]=="| " && visuel[ligne+1][colonne-1]=="———" && visuel[ligne-1][colonne-1]!=" . "))){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
							if (joueur==1){
								pointsDuJoueur1=pointsDuJoueur1+2;
							}
							if (joueur==2){
								pointsDuJoueur2=pointsDuJoueur2+2;
	
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejoue=true;
							if (joueur==1){
								pointsDuJoueur1++;
							}
							if (joueur==2){
								pointsDuJoueur2++;
	
							}
						}
					rejouer=true;
					}
				}
			}
		}
		
		if (taille>=5){ // NECESSAIRE POUR LES CAS OU LE PLATEAU A PLUS DE 10 COLONNES
			if (colonne==1){	
				if (visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="—————" && visuel[ligne-2][colonne]=="—————" && visuel[ligne-1][colonne-1]!=".  "){
					visuel[ligne-1][colonne]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
					//	System.out.println("Vous pouvez rejouer!");
					rejouer=true;
				}
			}
		
			if (colonne==visuel.length){	
				if (visuel[ligne-1][colonne-3]=="|  " && visuel[ligne-2][colonne-2]=="—————" && visuel[ligne][colonne-2]=="—————" && visuel[ligne-1][colonne-1]!=".  "){
					visuel[ligne-1][colonne-2]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}	
			}
		
			if (ligne==1){	
				if (visuel[ligne][colonne-2]=="|  " && visuel[ligne+1][colonne-1]=="—————" && visuel[ligne][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  "){
					visuel[ligne][colonne-1]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouer!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}
			}
		
			if (ligne==visuel.length){	
				if (visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-2][colonne]=="|  " && visuel[ligne-3][colonne-1]=="—————" && visuel[ligne-1][colonne-1]!="  .  "){
					visuel[ligne-2][colonne-1]=Integer.toString(joueur)+"  ";
					System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
					//	System.out.println("Vous pouvez rejouer!");
					rejouer=true;
					if (joueur==1){
						pointsDuJoueur1++;
					}
					if (joueur==2){
						pointsDuJoueur2++;
					}
				}
			}
		
			if (ligne!=1 && colonne!=1 && ligne!=visuel.length && colonne!=visuel.length){ //evite les collisions avec les conditions aux bords
				
				if (ligne%2==0 && colonne%2!=0){ // si barre verticale tracée
					if ((visuel[ligne-2][colonne-2]=="—————" && visuel[ligne-1][colonne-3]=="|  " && visuel[ligne][colonne-2]=="—————" && visuel[ligne-1][colonne-1]!=".  ") || (visuel[ligne-2][colonne]=="—————" && visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="—————" && visuel[ligne-1][colonne-1]!=".  ")){ // A MODIFIER
						if (visuel[ligne-2][colonne-2]=="—————" && visuel[ligne-1][colonne-3]=="|  " && visuel[ligne][colonne-2]=="—————" && visuel[ligne-1][colonne-1]!=".  "){
							visuel[ligne-1][colonne-2]=Integer.toString(joueur)+"  ";
						}
						if (visuel[ligne-2][colonne]=="—————" && visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="—————" && visuel[ligne-1][colonne-1]!=".  "){
							visuel[ligne-1][colonne]=Integer.toString(joueur)+"  ";
						}
						
						if ((visuel[ligne-2][colonne-2]=="—————" && visuel[ligne-1][colonne-3]=="|  " && visuel[ligne][colonne-2]=="—————" && visuel[ligne-1][colonne-1]!=".  ") && (visuel[ligne-2][colonne]=="—————" && visuel[ligne-1][colonne+1]=="|  " && visuel[ligne][colonne]=="—————" && visuel[ligne-1][colonne-1]!=".  ")){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
							if (joueur==1){
								pointsDuJoueur1=pointsDuJoueur1+2;
							}
							if (joueur==2){
								pointsDuJoueur2=pointsDuJoueur2+2;
	
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejoue=true;
							if (joueur==1){
								pointsDuJoueur1++;
							}
							if (joueur==2){
								pointsDuJoueur2++;
	
							}
						}
					rejouer = true;
					}	
				}
			
				if (ligne%2!=0 && colonne%2==0){ //barre horizontale tracée
					if ((visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-3][colonne-1]=="—————" && visuel[ligne-2][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  ") || ((visuel[ligne][colonne-2]=="|  " && visuel[ligne][colonne]=="|  " && visuel[ligne+1][colonne-1]=="—————" && visuel[ligne-1][colonne-1]!="  .  "))){ 
						if (visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-3][colonne-1]=="—————" && visuel[ligne-2][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  "){
							visuel[ligne-2][colonne-1]=Integer.toString(joueur)+"  ";
						}
						if (visuel[ligne][colonne-2]=="|  " && visuel[ligne][colonne]=="|  " && visuel[ligne+1][colonne-1]=="—————" && visuel[ligne-1][colonne-1]!="  .  "){
							visuel[ligne][colonne-1]=Integer.toString(joueur)+"  ";
						}
						if ((visuel[ligne-2][colonne-2]=="|  " && visuel[ligne-3][colonne-1]=="—————" && visuel[ligne-2][colonne]=="|  " && visuel[ligne-1][colonne-1]!="  .  ") && ((visuel[ligne][colonne-2]=="|  " && visuel[ligne][colonne]=="|  " && visuel[ligne+1][colonne-1]=="—————" && visuel[ligne-1][colonne-1]!="  .  "))){
							System.out.println("Bravo joueur "+joueur+ ", vous gagnez deux points!");
							if (joueur==1){
								pointsDuJoueur1=pointsDuJoueur1+2;
							}
							if (joueur==2){
								pointsDuJoueur2=pointsDuJoueur2+2;
	
							}
						}else{
							System.out.println("Bravo joueur "+joueur+" vous gagnez un point!");
						//	rejoue=true;
							if (joueur==1){
								pointsDuJoueur1++;
							}
							if (joueur==2){
								pointsDuJoueur2++;
	
							}
						}
					rejouer=true;
					}
				}
			}
		}
		return rejouer; 
	}
		
	public boolean VerifierOrdi(int LIG, int COL, String[][] fenetre) { /*Vérifie les lignes et colonnes de l'ia*/

		if (LIG>fenetre.length || COL>fenetre.length || LIG<1 || COL<1)	return false;
		if (taille<5){ if (fenetre[LIG-1][COL-1]=="| " || fenetre[LIG-1][COL-1]=="———"){return false;}}
		if (taille>=5){	if (fenetre[LIG-1][COL-1]=="|  " || fenetre[LIG-1][COL-1]=="—————"){return false;}}	
		if (fenetre[LIG-1][COL-1]=="O") return false;		
		if (LIG%2==0 && COL%2==0)return false;	
		else return true;}
	
	
	
	// CREATION ET AFFICHAGE DU Tableau DE JEU
		//PARTIE CONSOLE
	public void Afficher(String[][] affichage) {
		if (taille<5){
			System.out.print(" ");
		}
		if (affichage.length<10){
			for (int i=0;i<affichage.length;i++){
			System.out.print(" "+(i+1)); //pas de décallage tant que taille inférieure Ã  5.. faudra améliorer en augmentant l'espace selon un log
			}
			for (int i=0;i<affichage.length;i++){ //sur la console.
				System.out.print("\n");
					System.out.print(i+1+" ");
				for (int j=0;j<affichage.length;j++){
					System.out.print(affichage[i][j]);
				}
			}
			System.out.print("\n");
			
		}else{
			System.out.print("     ");
			for (int i=0;i<affichage.length;i++){ //AFFICHE LA LIG AU DESSUS DU TABLEAU.
				if (i>7){ //me finalTour pas pk 7 mais ca marche XD
					System.out.print((i+1)+" ");
				}else{
				System.out.print((i+1)+"  ");//pas de décallage tant que taille inférieure Ã  5.. faudra améliorer en augmentant l'espace selon un log
				}
			}
			
			for (int i=0;i<affichage.length;i++){ //AFFICHE LES CHIFFRES SUR LES COTES DU TABLEAU.
				System.out.println("");
				if (i>10){
					if (i%2==0){
					System.out.print(i+1+"   ");
					}else{ 
						System.out.print(i+1+"   ");
					}
				}else{
					System.out.print(i+1+"   ");
				}
				if (i<9){ //finalTour pas pk non plus.. mÃªme histoire qu'avant ahha
					System.out.print(" ");
				}
			for (int j=0;j<affichage.length;j++){
				System.out.print(affichage[i][j]);
			}
			}
			System.out.println("");
		}
	}

	public void Initialisation(String[][] affichage){
		System.out.println("\n\n");
		if (affichage.length<10){ //traite le cas d'un petit jeu
			for (int i=0;i<affichage.length;i++){ //création du Tableau avec des "O"
				if (i%2==0){ // SI LIG PAIRE
					for (int j=0;j<affichage.length;j++){
						if(j%2==0){
							affichage[i][j]="O";
						}else{
							affichage[i][j]="   ";
						}
					}
				}else{
					for (int j=0;j<affichage.length;j++){
						affichage[i][j]="  "; //Espace nÂ° joueur gagnant
					}
				}
			}
		}else{ // si taille>10, pour gérer les décallages dans l'affichage.
			for (int i=0;i<affichage.length;i++){ //création du Tableau avec des "."
				if (i%2==0){
					for (int j=0;j<affichage.length;j++){
						if(j%2==0){
							affichage[i][j]="O";
						}else{
							affichage[i][j]="     ";
						}
					}
				}else{
					for (int j=0;j<affichage.length;j++){
						affichage[i][j]="   ";
					}
				}
			}

		}
	}
	
		//PARTIE GRAPHIQUE
	public void AfficherGraphique(int joueur, String[][] fenetre) { // Necessite d'import le joueur sinon bug étrange.
		StdDraw.clear();
		int a=0;
		if (nbrDePoints!=(((fenetre.length-1)/2)*((fenetre.length-1)/2))){


			if (taille<7){
				if (joueur==1) StdDraw.text(fenetre.length/2, -(taille+1.35)/taille,"Tour de "+ parametres.nomJ1);
				if (joueur==2) StdDraw.text(fenetre.length/2, -(taille+1.35)/taille,"Tour de "+ parametres.nomJ2);
				
				StdDraw.text(fenetre.length/2, fenetre.length-0.3,"Score :");

				StdDraw.text(fenetre.length/2-2.8, fenetre.length+0.5,parametres.nomJ1 + " : " + pointsDuJoueur1);
				StdDraw.text(fenetre.length/2+2.8, fenetre.length+0.5,parametres.nomJ2 + " : " + pointsDuJoueur2);
			}else{

				StdDraw.text(fenetre.length/2, -1.4-(0.05*taille),"Tour du joueur "+joueur);
			}
		}else{
				StdDraw.text(fenetre.length/2, -1.4-(taille*0.03),"Fin du jeu!");
				if (pointsDuJoueur1>pointsDuJoueur2){
					if (pointsDuJoueur1==1) StdDraw.text(fenetre.length/2, fenetre.length-0.5, parametres.nomJ1+" gagne : "+pointsDuJoueur1+" point!");
					else StdDraw.text(fenetre.length/2, fenetre.length-0.5, parametres.nomJ1+ " gagne : "+pointsDuJoueur1+" points!");
				}
				
				if (pointsDuJoueur2>pointsDuJoueur1){
					if (pointsDuJoueur2==1) StdDraw.text(fenetre.length/2, fenetre.length-0.5, parametres.nomJ2+ " gagne : "+pointsDuJoueur2+" point!");
					else StdDraw.text(fenetre.length/2, fenetre.length-0.5, parametres.nomJ2+ " gagne : "+pointsDuJoueur2+" points!");
				}
				
				if (pointsDuJoueur1==pointsDuJoueur2){
					StdDraw.text(fenetre.length/2, fenetre.length-0.5,"match nul");
				}}		
		
		if (taille<5){
			for (int i=0;i<fenetre.length;i++){
				for (int j=0;j<fenetre.length;j++){
					if (fenetre[i][j]=="———"){
						StdDraw.setPenRadius(0.01);
						StdDraw.line(j-1, i, j+1, i);
					}

					if (fenetre[i][j]=="   "){
						if(DetectPos(j-1+0.07, i-0.3, (1+j), i+0.3-0.07)){
							StdDraw.setPenRadius(0.015);
							StdDraw.line(j-1, i, 1+j, i);	
						}
					}
					
					if (i%2!=0 && j%2==0){
						if (fenetre[i][j]=="  "){
							if(DetectPos(j-0.07, i-1+0.1, j+0.07, i+1-0.1)){
								StdDraw.setPenRadius(0.015);
								StdDraw.line(j, i-1, j, i+1);	
							}
						}
					}
					
					if (fenetre[i][j]=="| "){
						StdDraw.setPenRadius(0.01);
						StdDraw.line(j, i-1, j, i+1);					
					}
					
					if (fenetre[i][j]==" + "){
						StdDraw.setPenRadius(0.01);
						StdDraw.line(j-1, i, j+1, i);					
					}
					
					if (fenetre[i][j]==" + "){
						if(DetectPos(j-1+0.1, i-0.1, j+1+0.1, i+0.1)){
							StdDraw.setPenRadius(0.015);
							StdDraw.line(j-1, i, j+1, i);	
						}
					}
					
					if (fenetre[i][j]=="+ "){
						StdDraw.setPenRadius(0.01);
						StdDraw.line(j, i-1, j, i+1);					
					}
					
					if (fenetre[i][j]=="+ "){
						if(DetectPos(j-0.1, i-0.1-1, j+0.1, i+0.1+1)){
							StdDraw.setPenRadius(0.015);
							StdDraw.line(j, i-1, j, i+1);	
						}}
					
					if (i%2!=0 && j%2!=0){ // moins de calculs
						if (fenetre[i][j]!="  "){ 
							a=Integer.parseInt(fenetre[i][j].substring(0, 1)); 

							StdDraw.text(j, i, Integer.toString(a));
						}}}}
			
			for (int i=0;i<fenetre.length;i++){ //RONDS
				for (int j=0;j<fenetre.length;j++){
					if (fenetre[i][j]=="O"){
						StdDraw.setPenRadius(0.03);
						StdDraw.point(i, j);
					}}}}
		
		if (taille>=5){
			for (int i=0;i<fenetre.length;i++){ //PLUS
				for (int j=0;j<fenetre.length;j++){
					if (fenetre[i][j]=="—————"){
						StdDraw.setPenRadius(0.01);
						StdDraw.line(j-1, i, j+1, i);			}
					
					if (fenetre[i][j]=="     "){
						if(DetectPos(j-1+0.07, i-0.3, (1+j), i+0.3-0.07)){
							StdDraw.setPenRadius(0.02);
							StdDraw.line(j-1, i, 1+j, i);	}}
					
					if (fenetre[i][j]=="|  "){
						StdDraw.setPenRadius(0.01);
						StdDraw.line(j, i-1, j, i+1);					}
					
					if (i%2!=0 && j%2==0){
						if (fenetre[i][j]=="   "){
							if(DetectPos(j-0.07, i-1+0.1, j+0.07, i+1-0.1)){
								StdDraw.setPenRadius(0.02);
								StdDraw.line(j, i-1, j, i+1);	}}}
					
					
					if (fenetre[i][j]=="  +  "){
						StdDraw.setPenRadius(0.01);
						StdDraw.line(j-1, i, j+1, i);					}
					
					if (fenetre[i][j]=="  +  "){
						if(DetectPos(j-0.1, i-0.1-1, j+0.1, i+0.1+1)){
							StdDraw.setPenRadius(0.015);
							StdDraw.line(j-1, i, j+1, i);	}}
					
					if (fenetre[i][j]=="+  "){
						StdDraw.setPenRadius(0.01);
						StdDraw.line(j, i-1, j, i+1);					}
					
					if (fenetre[i][j]=="+  "){
						if(DetectPos(j-0.1, i-0.1-1, j+0.1, i+0.1+1)){
							StdDraw.setPenRadius(0.015);
							StdDraw.line(j, i-1, j, i+1);	}}
					
					if (i%2!=0 && j%2!=0){ 
						if (fenetre[i][j]!="   "){ 
							a=Integer.parseInt(fenetre[i][j].substring(0, 1));
							StdDraw.text(j, i, Integer.toString(a));}}}}
			
			for (int i=0;i<fenetre.length;i++){ //création du Tableau : des "O"
				for (int j=0;j<fenetre.length;j++){
					if (fenetre[i][j]=="O"){
						if (taille<=8){
							StdDraw.setPenRadius(0.03);
							StdDraw.point(i, j);
						}
						if (taille>8){
							StdDraw.setPenRadius(0.03-(0.001*taille));
							StdDraw.point(i, j);}}}}}}
	
	public void InitialisationGraphique(String[][] fenetre){ /* on initilalise la partie graphique */
		if (taille<8) StdDraw.setCanvasSize(100*taille, 100*taille);
		if (taille>=8) StdDraw.setCanvasSize(1100, 1100);
		StdDraw.setXscale(-1.5, fenetre.length+1);
		StdDraw.setYscale(fenetre.length+1, -1.5);
		StdDraw.clear();
	}
		
	public boolean Hitbox(int joueur, String[][] fenetre){ //autre fonction tour()	
		double largeur=1;
		boolean finalTour=true;
		if (taille<5){
			for (int i=0;i<fenetre.length;i++){ //création du Tableau : des "O"
				for (int j=0;j<fenetre.length;j++){
					if (fenetre[i][j]=="———"  && StdDraw.mousePressed()){//evite les reclick sur les cases déjÃ  utilisées!
						if(DetectPos(j-1, i-(largeur/2), j+1, i+(largeur/2))){ //utilise l'autre fonction qui n'afficherTableau pas, ce qui évite les erreurs de nbrDePointss et de joueurs.
							rejouer=false;
						}
					} 

					if (fenetre[i][j]=="   "  && StdDraw.mousePressed()){
						finalTour = detectPos(i+1, j+1, joueur, fenetre, j-1, i-(largeur/2), j+1, i+(largeur/2));
						rejouer=true;
					}
					
					if (i%2!=0 && j%2==0){ //evite de confondre les cases verticales : les cases : les nombres.
						if (fenetre[i][j]=="| "  && StdDraw.mousePressed()){
							if(DetectPos(j-(largeur/2), i-1, j+(largeur/2), i+1)){
								rejouer=false;
							}
						}
					}
					
					if (i%2!=0 && j%2==0){ 
						if (fenetre[i][j]=="  " && StdDraw.mousePressed()){
							finalTour = detectPos(i+1, j+1, joueur, fenetre, j-(largeur/2), i-1, j+(largeur/2), i+1);
							rejouer=true;
						}
					}

					if (fenetre[i][j]==" + " && StdDraw.mousePressed()){
						finalTour = detectPos(i+1, j+1, joueur, fenetre, j-1, i-(largeur/2), j+1, i+(largeur/2));
						rejouer=true;
						
					}
					if (i%2!=0 && j%2==0){
						if (fenetre[i][j]=="+ " && StdDraw.mousePressed()){
							finalTour = detectPos(i+1, j+1, joueur, fenetre, j-(largeur/2), i-1, j+(largeur/2), i+1);
							rejouer=true;
						}
					}
				}
			}
		}

		if (taille>=5){
			for (int i=0;i<fenetre.length;i++){ //création du Tableau : des "O"
				for (int j=0;j<fenetre.length;j++){
					if (fenetre[i][j]=="—————"  && StdDraw.mousePressed()){//evite les reclick sur les cases déjÃ  utilisées!
						if(DetectPos(j-1, i-(largeur/2), j+1, i+(largeur/2))){ //utilise l'autre fonction qui n'afficherTableau pas, ce qui évite les erreurs de nbrDePointss et de joueurs.
							rejouer=false;
						}} 
					
					if (fenetre[i][j]=="     "  && StdDraw.mousePressed()){
						finalTour = detectPos(i+1, j+1, joueur, fenetre, j-1, i-(largeur/2), j+1, i+(largeur/2));
						rejouer=true;
					}
					
					if (fenetre[i][j]=="|  "  && StdDraw.mousePressed()){//evite les reclick sur les cases déjÃ  utilisées!
						if(DetectPos(j-(largeur/2), i-1, j+(largeur/2), i+1)){ //utilise l'autre fonction qui n'afficherTableau pas, ce qui évite les erreurs de nbrDePointss et de joueurs.
							rejouer=false;
						}} 
					
					if (i%2!=0 && j%2==0){
						if (fenetre[i][j]=="   " && StdDraw.mousePressed()){
							finalTour = detectPos(i+1, j+1, joueur, fenetre, j-(largeur/2), i-1, j+(largeur/2), i+1);
							rejouer=true;
						}}
					
					
					if (fenetre[i][j]=="  +  " && StdDraw.mousePressed()){
						finalTour = detectPos(i+1, j+1, joueur, fenetre, j-1, i-(largeur/2), j+1, i+(largeur/2));
						rejouer=true;
					}
					if (i%2!=0 && j%2==0){
						if (fenetre[i][j]=="+  " && StdDraw.mousePressed()){
							finalTour = detectPos(i+1, j+1, joueur, fenetre, j-(largeur/2), i-1, j+(largeur/2), i+1);
							rejouer=true;
						}}}}}
	return finalTour; 
	}
	
	public boolean detectPos(int LIG, int COL, int joueur, String[][]fenetre, double x0, double y0, double x1, double y1){ 
		double x=StdDraw.mouseX();
		double y=StdDraw.mouseY(); 
		if (x>x0 && x<x1 && y>y0 && y<y1){
			while(StdDraw.mousePressed()){}	 //evite le fait de laisser appuyer la souris.
			afficherTableau(LIG, COL, joueur, fenetre);
		}
		return rejouer;
	}

	public boolean DetectPos(double x0, double y0, double x1, double y1){ 
		double x=StdDraw.mouseX();
		double y=StdDraw.mouseY();
		if (x0<x && x<x1 && y0<y && y<y1){
			return true;
		}
		return false;
	}
	//CREATION DES POINTILLES
	public void Pointilles(int pointilles, String[][] fenetre) {
		if (pointilles>((taille*(taille+1))+(taille*(taille+1)))){
			System.out.println("Il y a plus de pointillés que des cases disponibles... Tout le Tableau sera donc rempli de points.");
			if (taille<5){
				for (int i=0;i<fenetre.length;i++){
					for (int j=0;j<fenetre.length;j++){
						if ((i%2!=0 && j%2==0)){
							fenetre[i][j]="+ ";
						}
						if ((i%2==0 && j%2!=0)){
							fenetre[i][j]=" + ";
						}
							
					}
				}
			}
			if (taille>=5){
				for (int i=0;i<fenetre.length;i++){
					for (int j=0;j<fenetre.length;j++){
						if ((i%2!=0 && j%2==0)){
							fenetre[i][j]="+  ";
						}
						if ((i%2==0 && j%2!=0)){
							fenetre[i][j]="  +  ";
						}			
					}
				}
			}
			
		}else{
			for (int k=0; k<pointilles;k++){	
				int LIGAleatoire = 1 + (int)(Math.random() *(fenetre[0].length));
				int COLAleatoire = 1 + (int)(Math.random() *(fenetre[0].length));
				if (taille<5){		
					if ((fenetre[LIGAleatoire-1][COLAleatoire-1]!="+ ") && (fenetre[LIGAleatoire-1][COLAleatoire-1]!=" + ")){ //Evite de remplacer "+ " par "+ "
						if ((COLAleatoire==LIGAleatoire) || (LIGAleatoire%2==0 && COLAleatoire%2==0) || (LIGAleatoire%2!=0 && COLAleatoire%2!=0)){
							k--;
						}else{
							if (LIGAleatoire%2==0){
								fenetre[LIGAleatoire-1][COLAleatoire-1]="+ ";
							}else{
								fenetre[LIGAleatoire-1][COLAleatoire-1]=" + ";
							}
						}	
					}else{
						k--;
					}
				}
				
				if (taille>=5){
					if ((fenetre[LIGAleatoire-1][COLAleatoire-1]!="+  ") && (fenetre[LIGAleatoire-1][COLAleatoire-1]!="  +  ")){
						if ((COLAleatoire==LIGAleatoire) || (LIGAleatoire%2==0 && COLAleatoire%2==0 || (LIGAleatoire%2!=0 && COLAleatoire%2!=0))){
							k--;
						}else{
							if (LIGAleatoire%2==0){
								fenetre[LIGAleatoire-1][COLAleatoire-1]="+  ";
							}else{
								fenetre[LIGAleatoire-1][COLAleatoire-1]="  +  ";
							}
						}
					}else{
						k--;
					}
				}
			}
		}
	}
	// SETTERS AND GETTERS
	public int getnbrDePoints() {
		return nbrDePoints;
	}

	public int getpointsDuJoueur1() {
		return pointsDuJoueur1;
	}

	public int getpointsDuJoueur2() {
		return pointsDuJoueur2;
	}
}	
