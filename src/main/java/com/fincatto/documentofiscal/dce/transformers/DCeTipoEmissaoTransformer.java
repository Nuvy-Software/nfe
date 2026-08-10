package com.fincatto.documentofiscal.dce.transformers;

import com.fincatto.documentofiscal.dce.classes.tipos.DCeTipoEmissao;
import org.simpleframework.xml.transform.Transform;

public class DCeTipoEmissaoTransformer implements Transform<DCeTipoEmissao> {

    @Override
    public DCeTipoEmissao read(final String value) {
        return DCeTipoEmissao.valueOfCodigo(value);
    }

    @Override
    public String write(final DCeTipoEmissao value) {
        return value.getCodigo();
    }
}
