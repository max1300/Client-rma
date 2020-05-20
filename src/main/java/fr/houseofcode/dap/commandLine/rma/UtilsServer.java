package fr.houseofcode.dap.commandLine.rma;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO RMA by Djer |JavaDoc| En JavaDoc la "description" est sur la première lignes (au pire "les" premières) et les @xxx arivent ensuite).
/**
  * @author rma
 * 5 august. 2019
 * class utils :
 * tools to load correctly the access
 * to google API
 */
public class UtilsServer {
    /** @return access to constant LOG. */
    private static final Logger LOG = LogManager.getLogger();

    //TODO RMA by Djer |JavaDoc| Documentation fausse, ceci est une variable, pas une constante (devrait être "static final" et écrite en MAJUSCULE)
    /**Constant to define which browse is using.*/
    private String userAgent = "Mozilla/5.0";

    //TODO RMA by Djer |POO| Créer une constante pour l'URL du server (http://localhost:9090)

    /**
     * Load web page to add a user account on server.
     * @param userKey accept name of person who use the application
     * @throws IOException exception
     */
    public void addUser(final String userKey) throws IOException {
        String addUser = "http://localhost:9090/user/add?name=" + userKey;
        loadWebPage(addUser);
    }

    /**
     * Load web page to create a Google account on server.
     * @param userKey accept name of person who use the application
     * @throws IOException exception
     */
    public void createAccount(final String userKey) throws IOException {
        String account = "http://localhost:9090/account/add/" + userKey;
        loadWebPage(account);
    }

    //TODO RMA by Djer |Audit Code| Il manque le commentaire JavaDoc.

    private void loadWebPage(final String account) {
        try {
            Desktop.getDesktop().browse(new URI(account));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            //TODO RMA by Djer |Gestion Exception| Evite le "e.printStackTrace", cela affiche en "moche" directement dans la console. Ta Log (juste au dessus) fait le même boulot mais en plus propre et en **configurable**
            e.printStackTrace();
        } catch (URISyntaxException e) {
            LOG.error(e.getMessage(), e);
            //TODO RMA by Djer |Gestion Exception| Evite le "e.printStackTrace", cela affiche en "moche" directement dans la console. Ta Log (juste au dessus) fait le même boulot mais en plus propre et en **configurable**
            e.printStackTrace();
        }
    }

    /**
     * Load event on server.
     * @param userKey
     * @return a String that describe the event
     * @throws IOException
     */
    public String getNextEvent(final String userKey) throws IOException {
        String event = callServer("/event/next", userKey);
        return event;
    }

    /**
     * load labels on server.
     * @param userKey accept name of person who use the application
     * @return the labels on server using identification
     * @throws IOException exception
     */
    public String getLabels(final String userKey) throws IOException {
        String label = callServer("/label/print", userKey);
        return label;
    }

    /**
     * load number of unread email on server.
     * @param userKey accept name of person who use the application
     * @return the number of unread email on server using identification
     * @throws IOException exception
     */
    public String getNbUnreadEmail(final String userKey) throws IOException {
        String nbEmail = callServer("/email/nbUnread", userKey);
        return nbEmail;
    }

    /**
     * call the server.
     * @param userKey accept name of person who use the application
     * @param url that the server calls
     * @return a call to the server using identification
     * @throws IOException exception
     */
    private String callServer(final String url, final String userKey) throws IOException {
        URL obj = new URL("http://localhost:9090" + url + "?userKey=" + userKey);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", userAgent);

        int responseCode = con.getResponseCode();
        //TODO RMA by Djer |POO| Pas de SysOut pour des log ! Utilise le LOGGER (surement du "debug" ici)
        System.out.println("\nSending 'GET' request to URL : " + "http://localhost:9090" + url);

        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //TODO RMA by Djer |POO| Ce commentaire est devenu faux, la ligne ci-dessous n'affiche rien, elle renvoie une chaine de texte (évite le plus possivble les "commentaire" dans le code, ils deviennent vite faux, et le code devrait être lisible sans commentaire)
        //print result
        return response.toString();
    }

}
