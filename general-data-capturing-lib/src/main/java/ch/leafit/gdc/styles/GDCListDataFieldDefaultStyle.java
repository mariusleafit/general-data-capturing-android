package ch.leafit.gdc.styles;


import android.graphics.Color;
import android.graphics.Typeface;
import ch.leafit.gdc.GDCListDataField;

/**
 * Created by marius on 30/06/14.
 */
public class GDCListDataFieldDefaultStyle extends GDCDataFieldStyle<GDCListDataField>{
    @Override
    public void applyStyleToField(GDCListDataField field) {
        field.mView.setBackgroundColor(Color.BLACK);


        field.mLblFieldName.setTextSize(20);
        field.mLblFieldName.setTypeface(Typeface.DEFAULT_BOLD);

        field.mLblValue.setTextSize(15);
        field.mLblFieldName.setTypeface(Typeface.DEFAULT);
        field.mLblFieldName.setTextColor(Color.BLACK);

        field.mLblDisclosureIndicator.setTextColor(Color.WHITE);
        field.mLblDisclosureIndicator.setTextSize(20);
        field.mLblDisclosureIndicator.setTypeface(Typeface.DEFAULT_BOLD);

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
