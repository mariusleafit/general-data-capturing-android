package ch.leafit.gdc.core.data_fields;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import ch.leafit.R;

import java.awt.*;

/**
 * Created by marius on 25/06/14.
 */
public class GDCStringDataField extends GDCDataField {

    protected String mFieldName;
    protected String mValue;

    /*UI-Elements*/
    protected TextView mLblFieldName;
    protected EditText mTxtValue;

    public GDCStringDataField(Activity activity, String fieldName, String defaultValue) {
        super(activity);
        mFieldName = fieldName;
        mValue = defaultValue;
    }

    @Override
    public View getView() {
        if(mView == null) {
            mView = mInflater.inflate(R.layout.string_data_field, null);

            mLblFieldName = (TextView)mView.findViewById(R.id.lblFieldName);
            mTxtValue = (EditText)mView.findViewById(R.id.txtValue);

            this.setValue(mValue);
            this.setFieldName(mFieldName);

            this.applyStyle();
        }

        return mView;
    }

    @Override
    protected void applyStyle() {
        super.applyStyle();

        if(mView != null && mStyle != null) {

        }
    }

    public String getValue() {
        if(mTxtValue != null) {
            return mTxtValue.getText().toString();
        } else {
            return null;
        }
    }

    public void setValue(String value) {
        if(mTxtValue != null) {
            mTxtValue.setText(value);
        }
        mValue = value;
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
