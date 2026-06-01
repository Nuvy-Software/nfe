package com.fincatto.documentofiscal.dce.transformers;

import com.fincatto.documentofiscal.dce.classes.tipos.DCeModalidadeTransporte;
import org.simpleframework.xml.transform.Transform;

public class DCeModalidadeTransporteTransformer implements Transform<DCeModalidadeTransporte> {

    @Override
    public DCeModalidadeTransporte read(final String value) {
        return DCeModalidadeTransporte.valueOfCodigo(value);
    }

    @Override
    public String write(final DCeModalidadeTransporte value) {
        return value.getCodigo();
    }
}
