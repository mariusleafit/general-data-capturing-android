package ch.leafit.gdc.core.data_fields;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import ch.leafit.R;
import ch.leafit.gdc.core.data_fields.callback.GDCDataFieldCallback;
import ch.leafit.gdc.core.data_fields.styles.GDCIntegerDataFieldDefaultStyle;

/**
 * Created by marius on 30/06/14.
 */
public class GDCIntegerDataField extends GDCDataField {

    protected String mFieldName;
    protected GDCDataFieldCallback<Integer> mCallback;
    protected Integer mDefaultValue;

    /*UI-Elements*/
    public TextView mLblFieldName;
    public EditText mTxtInteger;
    public ImageButton mBtnPlus;
    public ImageButton mBtnMinus;

    public GDCIntegerDataField(Activity activity, int tag, String fieldName, GDCDataFieldCallback<Integer> callback) {
        this(activity,tag,fieldName,callback,null);
    }

    public GDCIntegerDataField(Activity activity, int tag, String fieldName, GDCDataFieldCallback<Integer> callback, Integer defaultValue) {
        super(activity, tag);
        mCallback = callback;
        mFieldName = fieldName;
        mDefaultValue = defaultValue;
    }

    @Override
    public View getView() {
        if(mView == null) {
            mView = mInflater.inflate(R.layout.integer_data_field, null);

            mLblFieldName = (TextView)mView.findViewById(R.id.lblFieldName);
            mTxtInteger = (EditText)mView.findViewById(R.id.txtInteger);
            mBtnPlus = (ImageButton)mView.findViewById(R.id.btnPlus);
            mBtnMinus = (ImageButton)mView.findViewById(R.id.btnMinus);

            mBtnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setInteger(getInteger() + 1);
                }
            });

            mBtnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setInteger(getInteger() - 1);
                }
            });

            this.setFieldName(mFieldName);
            this.setInteger(mDefaultValue);

            this.applyStyle();
        }

        return mView;
    }

    @Override
    protected void applyStyle() {
        if(mView != null) {
            if(mStyle == null) {
                mStyle = new GDCIntegerDataFieldDefaultStyle();
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

    public Integer getInteger() {
        return Integer.valueOf(mTxtInteger.getText().toString());
    }

    public void setInteger(Integer integer) {
        mTxtInteger.setText(String.valueOf(integer));
        /*call callbakc*/
        mCallback.valueChanged(mTag,integer);
    }
}
