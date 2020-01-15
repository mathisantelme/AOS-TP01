//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.0.1 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.01.15 à 02:05:24 PM CET 
//


package generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Agenda_QNAME = new QName("", "agenda");
    private final static QName _RecurrenceTypeOrdinal_QNAME = new QName("", "ordinal");
    private final static QName _RecurrenceTypeOccurence_QNAME = new QName("", "occurence");
    private final static QName _RecurrenceTypeStep_QNAME = new QName("", "step");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Agenda }
     * 
     */
    public Agenda createAgenda() {
        return new Agenda();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link RecurrenceType }
     * 
     */
    public RecurrenceType createRecurrenceType() {
        return new RecurrenceType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Agenda }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Agenda }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "agenda")
    public JAXBElement<Agenda> createAgenda(Agenda value) {
        return new JAXBElement<Agenda>(_Agenda_QNAME, Agenda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "ordinal", scope = RecurrenceType.class)
    public JAXBElement<Object> createRecurrenceTypeOrdinal(Object value) {
        return new JAXBElement<Object>(_RecurrenceTypeOrdinal_QNAME, Object.class, RecurrenceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "occurence", scope = RecurrenceType.class)
    public JAXBElement<String> createRecurrenceTypeOccurence(String value) {
        return new JAXBElement<String>(_RecurrenceTypeOccurence_QNAME, String.class, RecurrenceType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "step", scope = RecurrenceType.class)
    public JAXBElement<String> createRecurrenceTypeStep(String value) {
        return new JAXBElement<String>(_RecurrenceTypeStep_QNAME, String.class, RecurrenceType.class, value);
    }

}
