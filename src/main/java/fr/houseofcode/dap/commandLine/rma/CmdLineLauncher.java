package fr.houseofcode.dap.commandLine.rma;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author rma.
 * 5 august 2019.
 */

public class CmdLineLauncher {

    /**
     * @return access to constant LOG.
     */
    private static  Logger LOG = LogManager.getLogger();

    //    private  static String USER_AGENT = "Mozilla/5.0";

    /**
     * @param args for method main
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {

        LOG.debug("D�but du Main avec comme argument : " + args);

        
            
            UtilsServer server = new UtilsServer();
            
        	 final int nUMBERtHREE = 3;
             final int nUMBERnINE = 9;

             int value = Integer.parseInt(args[0]);
             String userKey = args[1];
             
            

            LOG.info("Utilisation d'un switch pour indiquer"
                    + " a l'utilisateur les possibilit�s" + args);
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

            case nUMBERtHREE:
                LOG.info("Recuperation du prochain "
                        + "evenements inscrit dans l'API Calendar");
                
                System.out.println(server.getNextEvent(userKey));
                break;

            case nUMBERnINE:
                LOG.info("Recuperation en un seul"
                        + " appel de tous les �l�ments sus-mentionn�s");
                
                System.out.println(server.getLabels(userKey));
                System.out.println("vous avez "
                + server.getNbUnreadEmail(userKey) + " message non lus");
                System.out.println(server.getNextEvent(userKey));
                break;
                
            case 5:
            	 LOG.info("creation d'un compte");
            	server.createAccount(userKey);

            default:
                LOG.warn("Message par d�faut retourne"
                        + " en cas de non utilisation des variables"
                        + " 1, 2, 3 ou 9");
                System.out.println("vous devez rentrer un numero"
                        + " : 1, 2, 3 ou 9");
                break;

            }
        

    }

    


}
