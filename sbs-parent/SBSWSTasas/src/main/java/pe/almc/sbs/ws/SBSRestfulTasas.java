package pe.almc.sbs.ws;

import javax.xml.transform.Source;
import javax.xml.ws.BindingType;
import javax.xml.ws.Provider;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.http.HTTPBinding;

@WebServiceProvider
@ServiceMode(value = Mode.MESSAGE)
@BindingType(value = HTTPBinding.HTTP_BINDING)
public class SBSRestfulTasas implements Provider<Source>{

	@Override
	public Source invoke(Source source) {
		// TODO Auto-generated method stub
		return null;
	}

}
