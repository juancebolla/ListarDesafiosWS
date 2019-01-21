package cl.ares.bice.ws.servicio;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOf_NameValue", propOrder = { "item" })
public class ListOf_NameValue {
    @XmlElement(name = "item")
    protected List<NameValue> item;

    public void setItem(List<NameValue> item) {
        this.item = item;
    }

    public List<NameValue> getItem() {
        return item;
    }
}
