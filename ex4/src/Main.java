import javax.xml.bind.*;
import java.io.*;
import java.util.*;
import generated.*;

public class Main {
    public static void main (String[] args) {
        // on désérialize les données
        try {
            // on définit le contexte
            JAXBContext jc = JAXBContext.newInstance("Main");

            Unmarshaller unmarshaller = jc.createUnmarshaller();

            SchemaFactory sf =SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            Schema agendaSchema = sf.newSchema(new File("ressources/agenda.xsd"));

            unmarshaller.setSchema(agendaSchema);
            unmarshaller.setValidating(true);

            // on récupère les données présente dans le fichier spécifié
            Agenda agenda = (Agenda) unmarshaller.unmarshal(new File("ressources/agenda.xml"));

            // on récupère les éléments event
            List events = agenda.getEvent();

            for (int i = 0; i < events.size(); i++) {
                System.out.println(events.get(i)); // on affiche l'event courant
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
}
