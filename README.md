# SP_ISIMA_F2_2022_BELLEC_BOUVARD

Blind quotes

## API

Nous avons codé nous même une API qui permet de récuperer des citations de films, séries...  

Lien vers l'API [BlindQuote](https://blind-quotes-api.herokuapp.com): `https://blind-quotes-api.herokuapp.com`  

### Categories

`/categories` Permet de récuperer les différentes catégories possible.  
Pour chaque catégorie, il y un endpoint `/random` et un endpoint `/complete`.  

**Exemple de retour** :  

```json
[
    {
        "name":"All",
        "description":"Guess quote from anywhere",
        "path":"all",
        "games":["guess","complete"]
    },
]
```

On récupère :  

- `name` Nom de la catégorie
- `description` Description de la catégorie
- `path` Le chemin pour récuperer les citations
- `games` Tableau avec les noms des jeux

### Random quote

`/categorie_name/random` Permet de récuperer une citation pour la categorie.

**Exemple de retour** :
  
```json
[
    {
        "quote":"No, I am your father",
        "title":"Star Wars. The Empire Strikes Back",
        "character":"Darth Vader",
        "actor":"David Prowse",
        "image":"https://www.ecranlarge.com/media/cache/1600x1200/uploads/articles/001/019/152/star-wars-lascension-de-skywalker-affiche-saga-1175304-large.jpg",
        "language":"eng",
        "ff":true
    }
]
```

On récupère :  

- `quote` La citation
- `title` Titre de l'oeuvre
- `character` Nom du personnage
- `actor` Nom de l'acteur si possible ou no infos
- `image` Lien vers l'image
- `language` Le code du langage (fr,en)
- `ff` Booléen qui indique si la citation est family friendly (tout public)

### Complete quote

`/categorie_name/complete` Permet de récuperer une citation avec des trous pour la categorie.

**Exemple de retour** :
  
```json
[
    {
        "quote":"A ruler who kills those devoted to her is not a ruler who inspires devotion.",
        "title":"Game Of Thrones",
        "character":"Tyrion Lannister",
        "actor":"Peter Dinklage",
        "image":"https://images.rtl.fr/rtl/www/1348813-selon-une-theorie-pour-la-saison-tyrion-lannister-serait-un-targaryen.jpg",
        "partialQuote":
            {
                "first":"A ruler who kills those",
                "second":"who inspires devotion.",
                "missingWords":7
            },
        "ff":true,
        "language":"eng",
    }
]
```

On récupère :  

- `quote` La citation
- `title` Titre de l'oeuvre
- `character` Nom du personnage
- `actor` Nom de l'acteur si possible ou no infos
- `image` Lien vers l'image
- `partialQuote` La citation découpée en deux parties avec le nombre de mot manquants au millieu
- `language` Le code du langage (fr,en)
- `ff` Booléen qui indique si la citation est family friendly (tout public)

## Fonctionnement application

L'application propose l'implémentation directe de l'API.

### Menu catégories  

On arrive sur l'application avec la liste des catégories disponibles.  

![Screen Menu Categories](/resources/menu_categories.PNG)  
On voit qu'on a deux boutons pour les deux jeux disponible.  

### Jeu Guess

Le jeu guess consiste à chercher d'ou provient la quote(nom de l'oeuvre, personnage, acteur).  

![Screen Guess Quote](/resources/guess_quote.PNG)  

On peut voir la réponse lorsque l'on clique sur le bouton `Answers`.  

![Screen Guess Answer](/resources/guess_answer.PNG)  

On peut voir une image, et les noms de l'oeuvre, du personnage et de l'acteur.  
On a un bouton `Another One` qui nous permet de passer à une nouvelle citation.  

### Jeu Complete

Le jeu complete consiste à completer la citation proposée à l'écran. Pour aider, on connait le nombre de mots manquant grâce aux nombres de tirets. De plus, on donne les informations sur le personnage, l'acteur et l'oeuvre.  

![Screen Complete Quote](/resources/complete_quote.PNG)  

On peut voir la réponse lorsque l'on clique sur le bouton `Answers`.  

![Screen Complete Answer](/resources/complete_answer.PNG)  

On peut voir la citation remplie.  
On a un bouton `Another One` qui nous permet de passer à une nouvelle citation.  

## Futur

Il y a plusieurs choses à faire pour améliorer l'application si nous voulions rajouter de nouvelles fonctionnalitées.  

- Nous pourrions rajouter des inputs pour récuperer les réponses des utilisateurs et ainsi créer un score final et des classements.  
- Nous pourrions aussi rajouter un systeme de parties (rooms) dans lesquels des gens peuvent s'affronter en direct sur les memes questions.  
- Nous pourrions également ajouter de nouveaux jeux et catégories.  
