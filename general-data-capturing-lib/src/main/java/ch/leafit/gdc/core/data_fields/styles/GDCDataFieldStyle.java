package ch.leafit.gdc.core.data_fields.styles;

import android.graphics.Color;
import ch.leafit.gdc.core.data_fields.GDCDataField;

/**
 * Created by marius on 25/06/14.
 */
public abstract class GDCDataFieldStyle<T extends GDCDataField> {

    public void applyStyleToField(T field) {
         /*disable / enable*/
        if(field.isDisabled()) {
            field.mView.setClickable(false);
            field.mView.setEnabled(false);
            field.mView.setAlpha((float)0.8);
        } else {
            field.mView.setClickable(true);
            field.mView.setEnabled(true);
            field.mView.setAlpha((float)1.0);
        }
    }
}
