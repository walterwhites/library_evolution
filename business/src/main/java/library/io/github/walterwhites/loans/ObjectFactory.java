//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.03.11 at 09:36:16 PM CET 
//


package library.io.github.walterwhites.loans;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the library.io.github.walterwhites.loans package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: library.io.github.walterwhites.loans
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllNotReturnedBookRequest }
     * 
     */
    public GetAllNotReturnedBookRequest createGetAllNotReturnedBookRequest() {
        return new GetAllNotReturnedBookRequest();
    }

    /**
     * Create an instance of {@link GetAllSoonExpiredLoanRequest }
     * 
     */
    public GetAllSoonExpiredLoanRequest createGetAllSoonExpiredLoanRequest() {
        return new GetAllSoonExpiredLoanRequest();
    }

    /**
     * Create an instance of {@link GetAllSoonExpiredLoanResponse }
     * 
     */
    public GetAllSoonExpiredLoanResponse createGetAllSoonExpiredLoanResponse() {
        return new GetAllSoonExpiredLoanResponse();
    }

    /**
     * Create an instance of {@link Loans }
     * 
     */
    public Loans createLoans() {
        return new Loans();
    }

    /**
     * Create an instance of {@link GetAllNotReturnedBookResponse }
     * 
     */
    public GetAllNotReturnedBookResponse createGetAllNotReturnedBookResponse() {
        return new GetAllNotReturnedBookResponse();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

}
