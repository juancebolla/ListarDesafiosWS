//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-257 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.16 at 09:28:23 PM GMT 
//


package cl.ares.bice.seguridad.sfa.schema;

import javax.xml.bind.annotation.XmlEnum;


/**
 * <p>Java class for TipoDesafioType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoDesafioType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GRID"/>
 *     &lt;enumeration value="SMS"/>
 *     &lt;enumeration value="SFTKN"/>
 *     &lt;enumeration value="TOKEN"/>
 *     &lt;enumeration value="PIN_BICE"/>
 *     &lt;enumeration value="TODOS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum TipoDesafioType {

    GRID,
    SMS,
    SFTKN,
    TOKEN,
    PIN_BICE,
    TODOS;

    public String value() {
        return name();
    }

    public static TipoDesafioType fromValue(String v) {
        return valueOf(v);
    }

}
