/**
 * 
 */
package fr.houseofcode.dap.commandLine.rma;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author houseofcode
 *
 */
public class CmdLineLauncher {
    private static final Logger LOG = LogManager.getLogger();

    //    private final static String USER_AGENT = "Mozilla/5.0";

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {

        LOG.debug("Début du Main avec comme argument : " + args);

        if (args.length < 2) {
            System.out.println(usage());
        } else {
            String userKey = args[0];
            UtilsServer server = new UtilsServer();

            final int nUMBERtHREE = 3;
            final int nUMBERnINE = 9;

            int value = Integer.parseInt(args[1]);

            LOG.info("Utilisation d'un switch pour indiquer à l'utilisateur les possibilités" + args);
            switch (value) {
            case 1:
                LOG.info("Recuperation des labels GMAIL");
                server.getLabels(userKey);
                System.out.println(server.getLabels(userKey));
                break;

            case 2:
                LOG.info("Recuperation du nombre d'emails Gmail");
                server.getNbUnreadEmail(userKey);
                System.out.println("vous avez " + server.getNbUnreadEmail(userKey) + " message non lus");
                break;

            case nUMBERtHREE:
                LOG.info("Recuperation du prochain évenements inscrit dans l'API Calendar");
                server.getNextEvent(userKey);
                System.out.println(server.getNextEvent(userKey));
                break;

            case nUMBERnINE:
                LOG.info("Recuperation en un seul appel de tous les éléments sus-mentionnés");
                server.getLabels(userKey);
                System.out.println(server.getLabels(userKey));
                System.out.println("vous avez " + server.getNbUnreadEmail(userKey) + " message non lus");
                System.out.println(server.getNextEvent(userKey));
                break;

            default:
                LOG.warn("Message par défaut retourné en cas de non utilisation des variables 1, 2, 3 ou 9");
                System.out.println("vous devez rentrer un numéro : 1, 2, 3 ou 9");
                break;

            }
        }

    }

    private static String usage() {
        return "xxxx.jar {userKey} {action} \nsample : xxxx.jar djer 2";
    }

    //    private static String getNextEvent() throws IOException {
    //        String event = callServer("/event/next");
    //        return event;
    //    }
    //
    //    private static String getLabels() throws IOException {
    //        String label = callServer("/label/print");
    //        return label;
    //    }
    //
    //    private static String getNbUnreadEmail() throws IOException {
    //        String nbEmail = callServer("/email/nbUnread");
    //
    //        return nbEmail;
    //    }
    //
    //    private static String callServer(String url) throws IOException {
    //
    //        URL obj = new URL("http://localhost:8080/" + url);
    //        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    //
    //        // optional default is GET
    //        con.setRequestMethod("GET");
    //
    //        //add request header
    //        con.setRequestProperty("User-Agent", USER_AGENT);
    //
    //        int responseCode = con.getResponseCode();
    //        System.out.println("\nSending 'GET' request to URL : " + "http://localhost:8080/" + url);
    //
    //        System.out.println("Response Code : " + responseCode);
    //
    //        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    //        String inputLine;
    //        StringBuffer response = new StringBuffer();
    //
    //        while ((inputLine = in.readLine()) != null) {
    //            response.append(inputLine);
    //        }
    //        in.close();
    //
    //        //print result
    //        return response.toString();
    //
    //    }

}
