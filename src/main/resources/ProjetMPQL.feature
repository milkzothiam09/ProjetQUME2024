# language: fr
Fonctionnalité: Gestion du catalogue de boissons
  En tant que gestionnaire de distributeur
  Je veux gérer les différentes boissons disponibles
  Afin de maintenir une offre actualisée pour les clients

  Règle:
* Une boisson doit avoir un prix positif
* L'ID d'une boisson doit être unique
* Les champs obligatoires sont : nom, prix, catégorie, marque

  Scénario: Création d'une nouvelle boisson valide
    Étant donné les détails suivants pour une nouvelle boisson:
      | id | nom   | prix | description            | categorie | marque     |
      | 4  | Fanta | 800  | Boisson gazeuse orange | SODA      | Coca-Cola  |
    Quand je crée cette boisson
    Alors elle doit être disponible dans le catalogue avec:
      | id | nom   | prix | description            | categorie | marque     |
      | 4  | Fanta | 800  | Boisson gazeuse orange | SODA      | Coca-Cola  |

  Scénario: Mise à jour valide du prix
    Étant donné la boisson existante:
      | id | nom    | prix |
      | 1  | Sprite | 1000 |
    Quand je mets à jour son prix à 1200
    Alors son prix doit être 1200
    Et ses autres propriétés doivent rester inchangées

    Scénario: Tentative de mise à jour avec prix invalide
      Étant donné la boisson existante "Jus d'orange" avec un prix de 500
      Quand j'essaie de mettre à jour son prix à -100
      Alors une erreur "Le prix doit être positif" doit être levée
      Et le prix doit rester à 500

  Scénario: Tentative de mise à jour avec prix invalide
    Étant donné la boisson existante:
      | id | nom         | prix |
      | 3  | Jus d'orange | 500  |
    Quand j'essaie de mettre à jour son prix à -100
    Alors une erreur "Le prix doit être positif" doit être affichée
    Et le prix doit rester à 500

  Scénario: Consultation des détails d'une boisson
    Étant donné la boisson existante:
      | id | nom         | prix | description           | categorie | marque     |
      | 2  | Cappuccino | 500  | Café avec mousse de lait | CAFE      | Starbucks  |
    Quand je consulte ses détails
    Alors je dois voir:
      """
      Boisson [id=2, nom=Cappuccino, prix=5.00, description=Café avec mousse de lait, catégorie=CAFE, marque=Starbucks]
      """

Fonctionnalité: Gestion du portefeuille
  En tant qu'utilisateur du distributeur
  Je veux gérer les transactions monétaires
  Afin de payer mes boissons et recevoir la monnaie

  Scénario: Insertion d'un montant valide
    Étant donné un portefeuille initialisé
    Quand j'insère un montant de 2000
    Alors le montant inséré doit être 2000


  Scénario: Insertion d'un montant négatif
    Étant donné un portefeuille initialisé
    Quand j'insère un montant de -5.0
    Alors une erreur "Le montant doit être positif" doit être levée

  Scénario: Vérification de montant suffisant
    Étant donné un portefeuille avec 500 inséré
    Quand je vérifie si le montant est suffisant pour un prix de 200
    Alors le résultat doit être vrai

  Scénario: Vérification de montant insuffisant
    Étant donné un portefeuille avec 50 inséré
    Quand je vérifie si le montant est suffisant pour un prix de 500
    Alors le résultat doit être faux

Fonctionnalité: Gestion des utilisateurs du distributeur
  En tant que système de gestion des utilisateurs
  Je veux gérer différents types d'utilisateurs
  Afin de contrôler l'accès et suivre les activités

  Scénario: Création simplifiée d'un client
    Étant donné que je crée un utilisateur avec ID "C123" et nom "Atta Fall"
    Quand j'utilise le constructeur simplifié
    Alors le type d'utilisateur doit être "CLIENT"
    Et l'ID doit être "C123"
    Et le nom doit être "Atta Fall"
    Et la date de création doit être aujourd'hui
    Et la date du dernier accès ne doit pas être nulle

  Scénario: Création d'un administrateur
    Étant donné que je crée un utilisateur avec ID "A176", nom "Yacine Seck" et type "ADMINISTRATEUR"
    Quand l'utilisateur est créé
    Alors le type d'utilisateur doit être "ADMINISTRATEUR"
    Et l'ID doit être "A176"
    Et le nom doit être "Yacine Seck"
    Et il doit être identifié comme administrateur
    Et il ne doit pas être identifié comme client

  Scénario: Authentification des utilisateurs
    Étant donné un administrateur avec ID "A176"
    Et un client avec ID "C123"
    Quand l'administrateur s'authentifie avec le mot de passe "admin123"
    Alors l'authentification doit réussir
    Quand l'administrateur s'authentifie avec le mot de passe "perdu"
    Alors l'authentification doit échouer
    Quand le client s'authentifie avec n'importe quel mot de passe
    Alors l'authentification doit toujours réussir

  Scénario: Vérification des permissions
    Étant donné un client de type "CLIENT"
    Et un administrateur de type "ADMINISTRATEUR"
    Et un technicien de type "TECHNICIEN"
    Quand je vérifie les permissions
    Alors le client doit être identifié comme client mais pas comme administrateur
    Et l'administrateur doit être identifié comme administrateur mais pas comme client
    Et le technicien ne doit être identifié ni comme client ni comme administrateur

Fonctionnalité: Gestion du stock des boissons
En tant que gestionnaire de stock
Je veux gérer les quantités de boissons disponibles
Afin de maintenir un stock optimal dans le distributeur

    Scénario: Ajout de stock avec quantité positive
    Étant donné un stock initial vide pour la boisson #101
    Quand j'ajoute 5 unités de la boisson #101
    Alors la quantité disponible pour #101 doit être 5

    Scénario: Tentative d'ajout avec quantité négative
    Étant donné un stock initial vide pour la boisson #102
    Quand j'essaie d'ajouter -3 unités de la boisson #102
    Alors une exception doit être levée
    Et le stock pour #102 doit rester à 0

    Scénario: Retrait de stock avec quantité suffisante
    Étant donné un stock initial de 10 unités pour la boisson #103
    Quand je retire 4 unités de la boisson #103
    Alors la quantité disponible pour #103 doit être 6

Fonctionnalité: Gestion des transactions d'achat
En tant que distributeur automatique
Je veux gérer les transactions d'achat
Afin de suivre les ventes et gérer les paiements

    Scénario: Transaction réussie avec monnaie rendue
    Étant donné une boisson "Coca" au prix de 300fr
    Et une transaction #1001 avec 500fr insérés
    Quand je valide la transaction
    Alors le statut doit être "REUSSIE"
    Et la monnaie rendue doit être 200fr
    Et la durée de transaction doit être enregistrée

    Scénario: Transaction échouée par fonds insuffisants
    Étant donné une boisson "Eau" au prix de 400
    Et une transaction #1002 avec 250 insérés
    Quand je valide la transaction
    Alors le statut doit rester "EN_COURS"
    Et la monnaie rendue doit être 0.00
    Et la transaction ne doit pas être marquée comme réussie

    Scénario: Annulation de transaction
    Étant donné une boisson "Café" au prix de 150fr
    Et une transaction #1003 avec 200fr insérés
    Quand j'annule la transaction
    Alors le statut doit être "ANNULEE"
    Et la monnaie rendue doit être 200fr
    Et la date de transaction doit être enregistrée