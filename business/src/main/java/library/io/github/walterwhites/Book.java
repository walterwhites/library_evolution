//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.05 at 07:30:44 PM CET 
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
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="obtaining_date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="libraries" type="{library.io.github.walterwhites}libraries"/>
 *         &lt;element name="loans" type="{library.io.github.walterwhites}loans"/>
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
    "obtainingDate",
    "libraries",
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
    protected String state;
    @XmlElement(name = "obtaining_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar obtainingDate;
    @XmlElement(required = true)
    protected Libraries libraries;
    @XmlElement(required = true)
    protected Loans loans;

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
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the obtainingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getObtainingDate() {
        return obtainingDate;
    }

    /**
     * Sets the value of the obtainingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setObtainingDate(XMLGregorianCalendar value) {
        this.obtainingDate = value;
    }

    /**
     * Gets the value of the libraries property.
     * 
     * @return
     *     possible object is
     *     {@link Libraries }
     *     
     */
    public Libraries getLibraries() {
        return libraries;
    }

    /**
     * Sets the value of the libraries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Libraries }
     *     
     */
    public void setLibraries(Libraries value) {
        this.libraries = value;
    }

    /**
     * Gets the value of the loans property.
     * 
     * @return
     *     possible object is
     *     {@link Loans }
     *     
     */
    public Loans getLoans() {
        return loans;
    }

    /**
     * Sets the value of the loans property.
     * 
     * @param value
     *     allowed object is
     *     {@link Loans }
     *     
     */
    public void setLoans(Loans value) {
        this.loans = value;
    }

}
