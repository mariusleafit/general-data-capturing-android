package ch.leafit.gdc.styles;

import android.graphics.Color;
import ch.leafit.gdc.GDCSectionTitleDataField;

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
