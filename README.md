<!-- TODO: ajouter les images de validation des fichiers xsd à la fin de chaque section -->
# Architecture Orienté Services - Compte Rendu TP1

## Les espaces de noms

Pour *Livres1.xsd* il faut définir un namespace par défaut a savoir celui qui permet de définir le schéma, ainsi que créer un targetNamespace (pour que les deux éléments fils de **schema** soient dans le meme namespace) puis définir le préfixe **bib**.

Pour *Livres2.xsd* il suffit de créer une balise schémas contenant le namespace **xsd** contenant la définition du schéma.

Pour le dernier extrait de schémas il n'est pas possible de définir une entete.

## Création d'un schéma pour une bibliothèque multimédia

### Création de la première version du schéma xml

Contraintes de création du schéma:

1. l'identifiant doit etre une chaine de caractères au format *CCC-DDD* (où *C* correspond à un caractère et *D* à un chiffre)
2. l'attribut **duree** de l'élément **chanson** sera du type **xsd:time**
3. dans l'élément **video**, les éléments **dvd** et **videok7** peuvent apparaitre dans n'importe quel ordre

Pour répondre à la première contrainte, il faut créer un type complexe pour l'attribut **id**. Pour cela on procède par restriction du type `xsd:string` on ajoutant une regex en tant que contrainte. On obtient le code suivant:

```xml
<xsd:attribute name="id" use="required">
    <xsd:simpleType>
        <xsd:restriction base="xsd:string">
            <xsd:pattern value="\c{3}-\d{3}"></xsd:pattern>
        </xsd:restriction>
    </xsd:simpleType>
</xsd:attribute>
```

*ex2/partie1/mediatheque.xsd ligne 36*

Pour la deuxième contrainte, on définit un type simple `xsd:string` qui contient un attribut temporel. Pour cela on utilise le code suivant:

```xml
<xsd:element name="chanson" maxOccurs="unbounded">
    <xsd:complexType>
        <xsd:simpleContent>
            <xsd:extension base="xsd:string">
                <xsd:attribute name="duree" type="xsd:time"/>
            </xsd:extension>
        </xsd:simpleContent>
    </xsd:complexType>
</xsd:element>
```
*ex2/partie1/mediatheque.xsd ligne 60*

Pour la dernière contrainte on utilise la balise `xsd:choice` qui permet de faire un choix parmis certains éléments. Ici on va spécifier le nombre d'occurence du choix. On définit le nombre maximal de choix comme ilimité ce qui permet de choisir les éléments peut importe leur ordre d'apparition.

```xml
<xsd:complexType name="TypeVideo">
    <xsd:choice maxOccurs="unbounded">
        <xsd:element name="dvd" type="TypeEltVideo"/>
        <xsd:element name="videok7" type="TypeEltVideo"/>
    </xsd:choice>
</xsd:complexType>
```

*ex2/partie1/mediatheque.xsd ligne 20*

### Séparation du schéma en plusieurs fichiers distincts

Pour séparer le schéma en plusieurs fichiers distincts, on va utiliser 4 fichiers différents:

1. *ex2/partie2/musique.xsd*: contient la définition des types **TypeMusique**, **TypeCD** et **TypeArtiste**
2. *ex2/partie2/video.xsd*: contient la définition du type **TypeEltVideo**
3. *ex2/partie2/element.xsd*: contient la définition du type **TypeEltMultimedia**
4. *ex2/partie2/mediatheque.xsd*: contient la définition de l'élément racine du schéma

Tout ces fichiers doivent contenir le meme `targetNamespace` (http://mediatheque.org) afin de pouvoir référencer les éléments définis dans d'autres fichiers. Il suffit d'inclure les fichiers nécessaire pour ces références afin de créer le schéma correspondant à celui défini dans la première partie de cet exercice.

---

## Contraintes d'identité

### *Partie 1*

Les contraintes du schéma

1. Deux commandes distinctes ne peuvent pas avoir la meme date **et** la meme heure
2. Tous les produits référencés dans les commandes **doivent** etre présents dans le catalogue
3. Deux produits distincts dans le catalogue ne peuvent pas avoir le meme numéro de série
4. Chaque client associé à une commande doit exister dans l'élément customers
5. Chaque client à un identifiant unique

Pour résoudre la première contrainte on utilise les attributs date et heure comme index pour les commandes, de ce fait, il ne peut exister deux commandes possèdant la meme date et heure.

```xml
<xsd:key name="unicite_commande">
    <xsd:selector xpath="./order"/>
    <xsd:field xpath="@date"/>
    <xsd:field xpath="@time"/>
</xsd:key>
```

*ex3/orders.xsd ligne 20*

Pour la contrainte numéro 2, on référence chaque produit présents dans les commande par leur numéro de série dans le catalogue (cf. contrainte n°3).

```xml
<xsd:keyref name="reference_produit_commande" refer="unicite_produit_catalogue">
    <xsd:selector xpath="./order/product"/>
    <xsd:field xpath="@serial"/>
</xsd:keyref>
```

*ex3/orders.xsd ligne 29*

Pour remplir la troisième contrainte on indexe les produits du catalogue avec leur numéro de série, ce qui permet de rendre chaque numéro de série unique.

```xml
<xsd:key name="unicite_produit_catalogue">
    <xsd:selector xpath="./catalog/product"/>
    <xsd:field xpath="@serial"/>
</xsd:key>
```

*ex3/orders.xsd ligne 37*

Afin d'associer les clients présent dans les commandes a ceux présent dans l'élément `customers`, on utilise la meme technique que dans la deuxième contrainte. On référence donc chaque client a son identifiant présent dans `customers`.

```xml
<xsd:keyref name="reference_customer_commande" refer="unicite_customer">
    <xsd:selector xpath="./order"/>
    <xsd:field xpath="@customer"/>
</xsd:keyref>
```

*ex3/orders.xsd ligne 44*

Pour que chaque client ai un identifiant unique, on utilise la meme méthode que dans la troisième contrainte, a savoir indexer les client par rapport à leur identifiant afin de le rendre unique.

```xml
<xsd:unique name="unicite_customer">
    <xsd:selector xpath="./customers/customer" />
    <xsd:field xpath="@id" />
</xsd:unique>
```
*ex3/orders.xsd ligne 52*