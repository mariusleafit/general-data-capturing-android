package ch.leafit.gdc;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import ch.leafit.gdc.R;
import ch.leafit.gdc.callback.GDCDataFieldCallback;
import ch.leafit.gdc.styles.GDCIntegerDataFieldDefaultStyle;

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


            mTxtInteger.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    /*call callbakc*/
                    Integer value = 0;
                    try {
                        value = Integer.valueOf(s.toString());
                    } catch (NumberFormatException e){}
                    mCallback.valueChanged(mTag,value);
                }
            });

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
                mStyle = GDCDefaultStyleConfig.integerDataFieldDefaultStyle;
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
    }
}
