package cl.ares.bice.ws.servicio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MachineSecret", propOrder = { "machineLabel", "machineNonce", "sequenceNonce", "applicationData" })
public class MachineSecret {
    @XmlElement(name = "machineLabel", nillable = true, required = true)
    protected String machineLabel;
    @XmlElement(name = "machineNonce", nillable = true, required = true)
    protected String machineNonce;
    @XmlElement(name = "sequenceNonce", nillable = true, required = true)
    protected String sequenceNonce;
    @XmlElement(name = "applicationData", nillable = true, required = true)
    protected ListOf_NameValue applicationData;

    public void setMachineLabel(String machineLabel) {
        this.machineLabel = machineLabel;
    }

    public String getMachineLabel() {
        return machineLabel;
    }

    public void setMachineNonce(String machineNonce) {
        this.machineNonce = machineNonce;
    }

    public String getMachineNonce() {
        return machineNonce;
    }

    public void setSequenceNonce(String sequenceNonce) {
        this.sequenceNonce = sequenceNonce;
    }

    public String getSequenceNonce() {
        return sequenceNonce;
    }

    public void setApplicationData(ListOf_NameValue applicationData) {
        this.applicationData = applicationData;
    }

    public ListOf_NameValue getApplicationData() {
        return applicationData;
    }
}
