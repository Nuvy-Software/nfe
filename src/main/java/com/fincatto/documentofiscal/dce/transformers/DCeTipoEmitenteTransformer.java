package com.fincatto.documentofiscal.dce.transformers;

import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmitente;
import org.simpleframework.xml.transform.Transform;

public class DCeTipoEmitenteTransformer implements Transform<DCeTipoEmitente> {

    @Override
    public DCeTipoEmitente read(final String value) {
        return DCeTipoEmitente.valueOfCodigo(value);
    }

    @Override
    public String write(final DCeTipoEmitente value) {
        return value.getCodigo();
    }
}
