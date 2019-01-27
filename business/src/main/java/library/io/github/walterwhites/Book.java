//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.27 at 11:43:15 PM CET 
//


package library.io.github.walterwhites;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for book complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="book">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="languages" type="{library.io.github.walterwhites}language"/>
 *         &lt;element name="state" type="{library.io.github.walterwhites}state"/>
 *         &lt;element name="loan_start_date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="loan_end_date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="libraries" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="clients" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="loans" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book", propOrder = {
    "id",
    "title",
    "author",
    "languages",
    "state",
    "loanStartDate",
    "loanEndDate",
    "libraries",
    "clients",
    "loans"
})
public class Book {

    protected int id;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String author;
    @XmlElement(required = true)
    protected Language languages;
    @XmlElement(required = true)
    protected State state;
    @XmlElement(name = "loan_start_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar loanStartDate;
    @XmlElement(name = "loan_end_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar loanEndDate;
    @XmlElement(required = true)
    protected Object libraries;
    @XmlElement(required = true)
    protected Object clients;
    @XmlElement(required = true)
    protected Object loans;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the languages property.
     * 
     * @return
     *     possible object is
     *     {@link Language }
     *     
     */
    public Language getLanguages() {
        return languages;
    }

    /**
     * Sets the value of the languages property.
     * 
     * @param value
     *     allowed object is
     *     {@link Language }
     *     
     */
    public void setLanguages(Language value) {
        this.languages = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link State }
     *     
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link State }
     *     
     */
    public void setState(State value) {
        this.state = value;
    }

    /**
     * Gets the value of the loanStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLoanStartDate() {
        return loanStartDate;
    }

    /**
     * Sets the value of the loanStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLoanStartDate(XMLGregorianCalendar value) {
        this.loanStartDate = value;
    }

    /**
     * Gets the value of the loanEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLoanEndDate() {
        return loanEndDate;
    }

    /**
     * Sets the value of the loanEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLoanEndDate(XMLGregorianCalendar value) {
        this.loanEndDate = value;
    }

    /**
     * Gets the value of the libraries property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLibraries() {
        return libraries;
    }

    /**
     * Sets the value of the libraries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLibraries(Object value) {
        this.libraries = value;
    }

    /**
     * Gets the value of the clients property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getClients() {
        return clients;
    }

    /**
     * Sets the value of the clients property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setClients(Object value) {
        this.clients = value;
    }

    /**
     * Gets the value of the loans property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLoans() {
        return loans;
    }

    /**
     * Sets the value of the loans property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLoans(Object value) {
        this.loans = value;
    }

}
