package cl.ares.bice.ws.utiles;

import cl.ares.bice.ws.utiles.dao.LimpiarCache;
import cl.ares.bice.ws.utiles.dao.LimpiarCacheResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;

import org.apache.log4j.Logger;

@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@WebService(name = "ToolsPortType", serviceName = "Tools", portName = "ToolsPort", targetNamespace = "http://utiles.ws.bice.cl/")
public class ToolsWS {
    static final Logger oLogger = LoggerUtil.getLoggerInput(ToolsWS.class);
    public ToolsWS() {
        super();
    }

    @WebMethod
    @SOAPBinding(parameterStyle=ParameterStyle.BARE)
    public LimpiarCacheResponse limpiarCache(@WebParam(partName="parameters", name="limpiarCache") LimpiarCache parameters){
        Tools t = new Tools();
        return t.limpiarCache(parameters);
    }
}
