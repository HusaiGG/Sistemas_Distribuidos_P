
package org.me.practica8;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.practica8 package. 
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

    private final static QName _Comprar_QNAME = new QName("http://practica8.me.org/", "comprar");
    private final static QName _ComprarResponse_QNAME = new QName("http://practica8.me.org/", "comprarResponse");
    private final static QName _Pagar_QNAME = new QName("http://practica8.me.org/", "pagar");
    private final static QName _PagarResponse_QNAME = new QName("http://practica8.me.org/", "pagarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.practica8
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Comprar }
     * 
     */
    public Comprar createComprar() {
        return new Comprar();
    }

    /**
     * Create an instance of {@link ComprarResponse }
     * 
     */
    public ComprarResponse createComprarResponse() {
        return new ComprarResponse();
    }

    /**
     * Create an instance of {@link Pagar }
     * 
     */
    public Pagar createPagar() {
        return new Pagar();
    }

    /**
     * Create an instance of {@link PagarResponse }
     * 
     */
    public PagarResponse createPagarResponse() {
        return new PagarResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comprar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Comprar }{@code >}
     */
    @XmlElementDecl(namespace = "http://practica8.me.org/", name = "comprar")
    public JAXBElement<Comprar> createComprar(Comprar value) {
        return new JAXBElement<Comprar>(_Comprar_QNAME, Comprar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComprarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ComprarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://practica8.me.org/", name = "comprarResponse")
    public JAXBElement<ComprarResponse> createComprarResponse(ComprarResponse value) {
        return new JAXBElement<ComprarResponse>(_ComprarResponse_QNAME, ComprarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pagar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Pagar }{@code >}
     */
    @XmlElementDecl(namespace = "http://practica8.me.org/", name = "pagar")
    public JAXBElement<Pagar> createPagar(Pagar value) {
        return new JAXBElement<Pagar>(_Pagar_QNAME, Pagar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PagarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://practica8.me.org/", name = "pagarResponse")
    public JAXBElement<PagarResponse> createPagarResponse(PagarResponse value) {
        return new JAXBElement<PagarResponse>(_PagarResponse_QNAME, PagarResponse.class, null, value);
    }

}
