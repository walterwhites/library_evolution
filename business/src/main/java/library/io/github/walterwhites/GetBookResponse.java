//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.03.24 at 12:44:38 PM CET 
//


package library.io.github.walterwhites;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="book" type="{library.io.github.walterwhites}book" maxOccurs="unbounded"/>
 *         &lt;element name="libraries" type="{library.io.github.walterwhites}libraries" maxOccurs="unbounded"/>
 *         &lt;element name="loans" type="{library.io.github.walterwhites}loans" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "book",
    "libraries",
    "loans"
})
@XmlRootElement(name = "getBookResponse")
public class GetBookResponse {

    @XmlElement(required = true)
    protected List<Book> book;
    @XmlElement(required = true)
    protected List<Libraries> libraries;
    @XmlElement(required = true)
    protected List<Loans> loans;

    /**
     * Gets the value of the book property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the book property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBook().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Book }
     * 
     * 
     */
    public List<Book> getBook() {
        if (book == null) {
            book = new ArrayList<Book>();
        }
        return this.book;
    }

    /**
     * Gets the value of the libraries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the libraries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLibraries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Libraries }
     * 
     * 
     */
    public List<Libraries> getLibraries() {
        if (libraries == null) {
            libraries = new ArrayList<Libraries>();
        }
        return this.libraries;
    }

    /**
     * Gets the value of the loans property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loans property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoans().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Loans }
     * 
     * 
     */
    public List<Loans> getLoans() {
        if (loans == null) {
            loans = new ArrayList<Loans>();
        }
        return this.loans;
    }

}
