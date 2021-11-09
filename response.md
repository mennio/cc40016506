Question 1 :

Pour la question une j'ai choisi d'utiliser le patron de création abstract factory car l'interface séréalizer
possède trois méthodes (name, size et write) qui seront utiliser par les deux sous classe (fileSerealize et
ScreenSerializer), le comportement des méthodes va différer en fonction de son utilisation.
exemple pour la méthode write() il devra pour la classe fileSerializer ecrire les chaine de caractère dans
un fichier tandis que la classe screenSerializer cette méthode devra juste afficher la chaine de caractère à
l'écran.

Il y a également le patron de création singleton qui va entrer en jeux dans cette question car on peut apercevoir
une contrainte spécifier dans les sujet. Il n'y aura que un type de serealizeur a sa conception c'est à dire que
le client ne pourra pas agir sur cette contrainte.

Question 2 :

J'ai choisi d'utiliser le patron adapter car il va permttre de changer le comportement d'un objet.
On peut dire qu'il va adapter l'interface de l'objet CookingReference pour que les autres objet puisse le comprendre.
Dans ce cas la , adapter va permttre de convertir l'objet pour qu il soit compris par grossbouf et Marmitor.
L'objet est donc encapsulé pour eviter des conversions trop complexe.

Question 3 :
    Builder

Question 4 :