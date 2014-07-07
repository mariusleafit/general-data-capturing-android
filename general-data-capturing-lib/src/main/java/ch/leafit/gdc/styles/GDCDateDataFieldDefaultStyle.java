package ch.leafit.gdc.styles;

import android.graphics.Color;
import android.graphics.Typeface;
import ch.leafit.gdc.GDCDateDataField;

/**
 * Created by marius on 01/07/14.
 */
public class GDCDateDataFieldDefaultStyle extends GDCDataFieldStyle<GDCDateDataField> {
    @Override
    public void applyStyleToField(GDCDateDataField field) {
        field.mView.setBackgroundColor(Color.BLACK);

        field.mLblFieldName.setTextSize(20);

        field.mLblDateTitle.setTextColor(Color.WHITE);
        field.mLblDateTitle.setTextSize(15);
        field.mLblDateTitle.setTypeface(Typeface.DEFAULT_BOLD);

        field.mLblTimeTitle.setTextColor(Color.WHITE);
        field.mLblTimeTitle.setTextSize(15);
        field.mLblTimeTitle.setTypeface(Typeface.DEFAULT_BOLD);

        field.mLblTime.setTextColor(Color.WHITE);
        field.mLblTime.setTextSize(15);

        field.mLblDate.setTextColor(Color.WHITE);
        field.mLblDate.setTextSize(15);

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
    }

}
