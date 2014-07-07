package ch.leafit.gdc.styles;

import android.graphics.Color;
import android.graphics.Typeface;
import ch.leafit.gdc.GDCStringDataField;

/**
 * Created by marius on 26/06/14.
 */
public class GDCStringDataFieldDefaultStyle extends GDCDataFieldStyle<GDCStringDataField> {
    @Override
    public void applyStyleToField(GDCStringDataField field) {
        field.mView.setBackgroundColor(Color.BLACK);
        field.mLblFieldName.setTextSize(20);
        field.mLblFieldName.setTypeface(Typeface.DEFAULT_BOLD);

        field.mTxtValue.setTextColor(Color.WHITE);
        field.mTxtValue.setBackgroundColor(Color.argb(30,255,255,255));

        switch (field.getMarking()) {
            case MARKED_AS_INVALID:
                field.mLblFieldName.setTextColor(Color.RED);
                break;
            case MARKED_AS_VALID:
                field.mLblFieldName.setTextColor(Color.GREEN);
                break;
            case NOT_MARKED:
                field.mLblFieldName.setTextColor(Color.WHITE);
                break;
        }

        super.applyStyleToField(field);
        if(field.isDisabled()) {
            field.mTxtValue.setEnabled(false);
        } else {
            field.mTxtValue.setEnabled(true);
        }

    }

}
