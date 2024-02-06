# uMoove - Projet PolyHack 2024
## https://devpost.com/software/umoove

## Inspiration
Un membre a eu une conférence sur le développement durable et il y a eu une explication sur les émissions de carbone et la possibilité de peut-être en venir à un système de crédit-carbone pour les individus. De plus, on sait que les émissions de carbone sont quelque chose mauvais, mais il est difficile de savoir quelle quantité nous devrions émettre par individus au maximum. De plus, c'est un détail qui n'est pas très commun, mais qui est vital pour réduire notre réchauffement climatique. Dans ces émissions de carbone, les déplacements représentent 24,2 % link des émissions d'une personne. En sachant que les villes ont beaucoup de problèmes à convaincre les automobilistes de délaisser leur automobile à essence pour se diriger vers les transports en commun, le covoiturage et les véhicules électriques. On en est venu à l'idée de Umoove.

## What it does
Umoove est une application mobile qui permet d'échanger sa réduction d'émission de carbone contre des récompenses. En effet, selon le type de transport, l'application calcule le nombre de points que la distance parcourue avec le mode de transport donne. Cela correspond à la différence d'émission carbone entre le moyen de transport et une auto solo à essence (10 points par 50 gCO2e sauvés). Les points gagnés lors des déplacements peuvent être échangés dans l'application mobile contre des prix chez des partenaires de l'application par exemple: la STM donne 20% de rabais sur sa passe mensuelle pour les personnes qui ont 100 points (500 gCO2e de sauvés). Ainsi, les bonnes habitudes sont récompensées et les villes / sociétés de transport peuvent encourager le transport collectif en rendant le rendant plus abordable.

## How we built it
Nous avons conçu Umoove en faisant une API et une application mobile. Pour l'API, nous l'avons fait avec le framework Laravel (version 10), car nous voulions avoir un outil facile qui nous permettrait d'avoir notre API et une potentielle application Web dans le même projet. De plus, vu que nous avions tout fait du PHP, on était plus sûr d'utiliser ce framework pour réduire les difficultés. En effet, pour l'application mobile, nous avons utilisé Androide Studio avec Java, car c'était gratuit et on avait un peu travaillé avec. Finalement, pour héberger l'API, nous avons choisi O2switch et Porkbun pour le nom de domaine.

## Challenges we ran into
Lors du déploiement, nous avons eu des problèmes avec la configuration des serveurs DNS pour le nom de domaine. Aussi, nous avons eu beaucoup de difficulté à travailler avec Android Studio, car nous n'avons pas beaucoup travaillé avec, donc nous devions apprendre beaucoup de notions pour faire l'application mobile. En effet, à la base, nous voulions faire l'application mobile en Web, mais une application mobile était mieux adaptée. Cela à fait que nous nous étions préparés à faire du web à travers des tutoriels, mais pas du mobile qui nous a surpris.

## Accomplishments that we're proud of
Ultimement, nous avons réussi à résoudre le problème de DNS, ce qui nous a permis d'héberger notre API sur le serveur. Nous sommes contents, car nous commencions à douter de notre capacité.

Bien que l'application mobile ne soit pas finie, nous avons réussi à faire quelques fonctionnalités, ce qui nous permet de montrer un peu notre vision.

## What we learned

- Nous avons appris qu'il était important d'élargir nos connaissances sur le développement mobile, car le Web a ses limites dans certains projets.
- Nous avons appris que l'apprentissage d'une technologie en 24h est plus difficile.
- Les tâches les plus difficiles doivent être faites en premier, car la fatigue les complique
What's next for Umoove

Dans un avenir proche, nous voulons implémenter une partie Web pour permettre à des entreprises d'ajouter et de modifier leurs offres de récompense, car il faut les impliquer dans le projet. Ensuite, on veut implémenter une récompense hebdomadaire pour les utilisateurs qui sont dans le 90e rang percentile d'empreinte carbone parmi tous les utilisateurs. En effet, on veut les encourager à essayer de réduire leurs émissions. Améliorer l'interface mobile pour être plus attirante.
Built With

- android-studio
- blade
- git
- hosting
- java
- laravel
- mysql
- o2switch
- php
- sql
