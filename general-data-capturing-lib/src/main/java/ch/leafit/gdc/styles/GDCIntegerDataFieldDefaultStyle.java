package ch.leafit.gdc.styles;

import android.graphics.Color;
import android.graphics.Typeface;
import ch.leafit.gdc.GDCIntegerDataField;

/**
 * Created by marius on 30/06/14.
 */
public class GDCIntegerDataFieldDefaultStyle extends GDCDataFieldStyle<GDCIntegerDataField> {
    @Override
    public void applyStyleToField(GDCIntegerDataField field) {
        field.mView.setBackgroundColor(Color.BLACK);

        field.mLblFieldName.setTextSize(20);
        field.mLblFieldName.setTypeface(Typeface.DEFAULT_BOLD);

        field.mTxtInteger.setTextColor(Color.WHITE);
        field.mTxtInteger.setBackgroundColor(Color.argb(30,255,255,255));

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
            field.mTxtInteger.setEnabled(false);
            field.mBtnPlus.setEnabled(false);
            field.mBtnMinus.setEnabled(false);
        } else {
            field.mTxtInteger.setEnabled(true);
            field.mBtnPlus.setEnabled(true);
            field.mBtnMinus.setEnabled(true);
        }
    }

}
