package ch.leafit.gdc.core.data_fields;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import ch.leafit.R;
import ch.leafit.gdc.core.data_fields.callback.GDCClickDataFieldCallback;
import ch.leafit.gdc.core.data_fields.callback.GDCDataFieldCallback;
import ch.leafit.gdc.core.data_fields.styles.GDCClickDataFieldDefaultStyle;
import ch.leafit.gdc.core.data_fields.styles.GDCStringDataFieldDefaultStyle;

/**
 * Created by marius on 26/06/14.
 */
public class GDCClickDataField extends GDCDataField {

    protected String mFieldName;
    protected GDCClickDataFieldCallback mCallback;

    /*UI-Elements*/
    public TextView mLblFieldName;
    public TextView mLblDisclosureIndicator;

    public GDCClickDataField(Activity activity, int tag, String fieldName, GDCClickDataFieldCallback callback) {
        super(activity, tag);
        mCallback = callback;
        mFieldName = fieldName;
    }

    @Override
    public View getView() {
        if(mView == null) {
            mView = mInflater.inflate(R.layout.click_data_field, null);

            mLblFieldName = (TextView)mView.findViewById(R.id.lblFieldName);
            mLblDisclosureIndicator = (TextView)mView.findViewById(R.id.lblDisclosureIndicator);

            this.setFieldName(mFieldName);

            if(mCallback != null) {
                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mCallback.fieldClicked(mTag);
                    }
                });
            }

            this.applyStyle();
        }

        return mView;
    }

    @Override
    protected void applyStyle() {
        if(mView != null) {
            if(mStyle == null) {
                mStyle = new GDCClickDataFieldDefaultStyle();
            }
            mStyle.applyStyleToField(this);
        }
    }

    public String getFieldName() {
        return mFieldName;
    }

    public void setFieldName(String fieldName) {
        if(mLblFieldName != null) {
            mLblFieldName.setText(fieldName);
        }
        mFieldName = fieldName;
    }
}
