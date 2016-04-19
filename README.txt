ARNEAU Megan - BONNOTTE Benjamin - GUISSET Abdoul

Les fichiers grid.xml et ships.xml sont dans le répertoire bin. Le lancement depuis le terminal en ligne de commande fonctionne.

Étapes réalisées
-------------------

- Étape 1 : Mise en place du système de grille
	- Bateaux
	- Grille de jeu
	- Position initiale des bateaux
	- Ligne de commande

- Étape 2 : Mise en place du système de jeu
	- Détermination du premier joueur
	- Commande view
	- Commande debug
	- Commande fire(x,y)
	- Gestion des exceptions
	- Gestion de l'IA
	- Fin de la partie

- Étape 3 : Mise en place d'une intelligence artificielle avancée
	- Placement : séparationde la grille en quatre sous-grilles
		- pack : regroupement des bateaux dans la sous-grille haute gauche.
		- far : placement des bateaux dans chacune des sous-grilles de manière itérative.
	- Tir : 
		- pack : tir dans une des sous-grilles.
		- far : tir dans une sous-grille, puis dans la suivante, et ainsi de suite.
	- Nouvel stratégie de tir : detection (tir dans la même zone)


Étapes non réalisées
-------------------

- Étape 3 : Mise en place d'une intelligence artificielle avancée
	- Nouvelle stratégie de placement
