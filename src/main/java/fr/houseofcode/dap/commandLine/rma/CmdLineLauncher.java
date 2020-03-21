package fr.houseofcode.dap.commandLine.rma;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author rma.
 * 5 august 2019.
 */

public final class CmdLineLauncher {

    /**
     * @return access to constant LOG.
     */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Constructor private of CmdLineLauncher.
     */
    private CmdLineLauncher() {
    }

    /**
     * @param args for method main
     * @throws IOException exception
     */
    public static void main(final String[] args) throws IOException {
        LOG.debug("Début du Main avec comme argument : {}.", args);
        UtilsServer server = new UtilsServer();
        LOG.info("Instanciation d'un UtilsServer");
        final int numberThree = 3;
        final int numberFive = 5;
        final int numberHeight = 8;
        final int numberNine = 9;
        Integer value = Integer.parseInt(args[0]);
        String userKey = args[1];
        LOG.info("Utilisation d'un switch pour indiquer a l'utilisateur les possibilités : {}.", args);
        switch (value) {
            case 1:
                LOG.info("Recuperation des labels GMAIL");
                System.out.println(server.getLabels(userKey));
                break;
            case 2:
                LOG.info("Recuperation du nombre d'emails Gmail");
                System.out.println("vous avez "
                    + server.getNbUnreadEmail(userKey)
                    + " message non lus");
                break;
            case numberThree:
                LOG.info("Recuperation du prochain "
                    + "evenements inscrit dans l'API Calendar");
                System.out.println(server.getNextEvent(userKey));
                break;
            case numberFive:
                LOG.info("Recuperation en un seul"
                    + " appel de tous les éléments"
                    + " sus-mentionnés");
                System.out.println(server.getLabels(userKey));
                System.out.println("vous avez "
                    + server.getNbUnreadEmail(userKey)
                    + " message non lus");
                System.out.println(server.getNextEvent(userKey));
                break;
            case numberHeight:
                LOG.info("Ajout d'un user");
                server.addUser(userKey);
                break;
            case numberNine:
                LOG.info("creation d'un compte");
                server.createAccount(userKey);
                break;
            default:
                LOG.warn("Message par défaut retourne"
                    + " en cas de non utilisation des variables"
                    + " 1, 2, 3 ou 9");
                System.out.println("vous devez rentrer un numero"
                    + " : 1, 2, 3 ou 9");
                break;
        }
    }
}
