//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.04 at 02:22:02 PM CET 
//


package library.io.github.walterwhites;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the library.io.github.walterwhites package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: library.io.github.walterwhites
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBookResponse }
     * 
     */
    public GetBookResponse createGetBookResponse() {
        return new GetBookResponse();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link Libraries }
     * 
     */
    public Libraries createLibraries() {
        return new Libraries();
    }

    /**
     * Create an instance of {@link Loans }
     * 
     */
    public Loans createLoans() {
        return new Loans();
    }

    /**
     * Create an instance of {@link GetAllBookRequest }
     * 
     */
    public GetAllBookRequest createGetAllBookRequest() {
        return new GetAllBookRequest();
    }

    /**
     * Create an instance of {@link GetAllBookResponse }
     * 
     */
    public GetAllBookResponse createGetAllBookResponse() {
        return new GetAllBookResponse();
    }

    /**
     * Create an instance of {@link GetBookFromIdRequest }
     * 
     */
    public GetBookFromIdRequest createGetBookFromIdRequest() {
        return new GetBookFromIdRequest();
    }

    /**
     * Create an instance of {@link GetBookFromIdResponse }
     * 
     */
    public GetBookFromIdResponse createGetBookFromIdResponse() {
        return new GetBookFromIdResponse();
    }

    /**
     * Create an instance of {@link GetBookRequest }
     * 
     */
    public GetBookRequest createGetBookRequest() {
        return new GetBookRequest();
    }

}
