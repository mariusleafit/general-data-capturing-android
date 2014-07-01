package ch.leafit.gdc.core.data_fields.styles;

import android.graphics.Color;
import ch.leafit.gdc.core.data_fields.GDCClickDataField;
import ch.leafit.gdc.core.data_fields.GDCDataField;
import ch.leafit.gdc.core.data_fields.GDCSectionTitleDataField;

/**
 * Created by marius on 01/07/14.
 */
public class GDCSectionTitleDataFieldDefaultStyle extends GDCDataFieldStyle<GDCSectionTitleDataField> {
    @Override
    public void applyStyleToField(GDCSectionTitleDataField field) {
        field.mLblTitle.setTextColor(Color.WHITE);
        field.mLblTitle.setBackgroundColor(Color.argb(255,100,100,100));
        field.mLblTitle.setTextSize(15);
    }

}
