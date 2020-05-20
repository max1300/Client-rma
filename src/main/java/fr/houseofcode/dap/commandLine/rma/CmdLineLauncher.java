package fr.houseofcode.dap.commandLine.rma;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO RMA by Djer |IDE| PMD n'était pas activé (et config manquante). Je te l'ai ajouté, je ne sais pas si IntelliJIdea va le détercter automatiquement....
//TODO RMA by Djer |IDE| (encodage) Tes fichiers ne sont pas encodé en UTF-8 (dans Eclipse help -> perform Setup Tasks).
/**
 * @author rma.
 * 5 august 2019.
 */
public final class CmdLineLauncher {

    /** @return access to constant LOG. */
    private static final Logger LOG = LogManager.getLogger();

    /** Constructor private of CmdLineLauncher. */
    private CmdLineLauncher() {
    }

    /**
     * @param args for method main
     * @throws IOException exception
     */
    public static void main(final String[] args) throws IOException {
        //TODO RMA by Djer |Log4J| attention, tu n'affiches pas les arguments cependant ton message semble indiquer qu'il les affiche. Cela peut donner l'impression d'un bug (paramètres non récupérés par l'application)
        //TODO RMA by Djer |IDE| (encodage) Attention tu n'as pas enregistré ce fichier en UTF-8 (surement laissé par défaut à ISO-8852-2)
        LOG.debug("DÃ©but du Main avec comme argument");
        UtilsServer server = new UtilsServer();
        //TODO RMA by Djer |Log4J| Devrait plutot être du debug (pas vraiment compréhensible par un "utilsiateur avancé")
        LOG.info("Instanciation d'un UtilsServer");
        //TODO RMA by Djer |POO| C'est bien d'éviter les "magic number", mais le nom de ta variable laisse ce chiffre "magique" : "menuActionNextEvent" clarifierait ce qu'est ce "3".
        final int numberThree = 3;
        final int numberFive = 5;
        final int numberHeight = 8;
        final int numberNine = 9;
        //TODO RMA by Djer |Command Line| Tu devrais d'abord vérifier qu'il y a bien au moins un argument.
        Integer value = Integer.parseInt(args[0]);
        String userKey = args[1];
        //TODO RMA by Djer |Log4J| Comme se message est en "info" il devrait être compréhenssible par un "utilisateur avancé" : "Affichage du menu du choix d'action" serait mieux.
        LOG.info("Utilisation d'un switch pour indiquer a l'utilisateur les possibilitÃ©s");
        switch (value) {
        case 1:
            LOG.info("Recuperation des labels GMAIL");
            System.out.println(server.getLabels(userKey));
            break;
        case 2:
            LOG.info("Recuperation du nombre d'emails Gmail");
            System.out.println("vous avez " + server.getNbUnreadEmail(userKey) + " message non lus");
            break;
        case numberThree:
            LOG.info("Recuperation du prochain " + "evenements inscrit dans l'API Calendar");
            System.out.println(server.getNextEvent(userKey));
            break;
        case numberFive:
            LOG.info("Recuperation en un seul" + " appel de tous les Ã©lÃ©ments" + " sus-mentionnÃ©s");
            System.out.println(server.getLabels(userKey));
            System.out.println("vous avez " + server.getNbUnreadEmail(userKey) + " message non lus");
            System.out.println(server.getNextEvent(userKey));
            break;
        case numberHeight:
            //TODO RMA by Djer |Log4J| Contextualise tes messages, "... avec comme clef utilisateur : " + userKey serait mieux
            LOG.info("Ajout d'un user");
            server.addUser(userKey);
            break;
        case numberNine:
            //TODO RMA by Djer |Log4J| Contextualise tes messages, "... avec comme clef utilisateur : " + userKey serait mieux
            LOG.info("creation d'un compte");
            server.createAccount(userKey);
            break;
        default:
            //TODO RMA by Djer |Log4J| Tu "répètes" ta règle de gestion (nombre {1,2,3,9}) ce qui risque de provoquer une log qui va devenir fausse. "demande : " + value + " non reconnue" serait suffisante dans la log.
            LOG.warn("Message par dÃ©faut retourne" + " en cas de non utilisation des variables" + " 1, 2, 3 ou 9");
            //TODO RMA by Djer |POO| Ce message n'est plus juste, les valeurs 5 et 8 sont ausis autorisées. Il est courrant de créer une méthode "usage()" qui contient le texte d'aide a afficher si les paramètre sont incorectes, qui est utilsiée à chaque fois qu'un paramètre est invalide.
            System.out.println("vous devez rentrer un numero" + " : 1, 2, 3 ou 9");
            break;
        }
    }
}
