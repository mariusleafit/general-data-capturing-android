package ch.leafit.gdc.core.data_fields;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import ch.leafit.R;
import ch.leafit.gdc.core.data_fields.callback.GDCDataFieldCallback;
import ch.leafit.gdc.core.data_fields.styles.GDCStringDataFieldDefaultStyle;

/**
 * Created by marius on 25/06/14.
 */
public class GDCStringDataField extends GDCDataField {

    protected String mFieldName;
    protected String mValue;
    protected GDCDataFieldCallback mCallback;
    protected GDCStringDataFieldType mType;

    /*UI-Elements*/
    protected TextView mLblFieldName;
    protected EditText mTxtValue;


    /**
     *
     * @param tag value can be used to recognize the field
     * @param type defines the type of txtValue
     */
    public GDCStringDataField(Activity activity, int tag, String fieldName, String defaultValue, GDCDataFieldCallback<String> callback, GDCStringDataFieldType type) {
        this(activity,tag,fieldName,defaultValue,callback);
        mType = type;
    }

    /**
     *
     * @param tag value can be used to recognize the field
     */
    public GDCStringDataField(Activity activity, int tag, String fieldName, String defaultValue, GDCDataFieldCallback<String> callback) {
        this(activity, tag, fieldName,defaultValue);
        mCallback = callback;
    }


    /**
     *
     * @param tag value can be used to recognize the field
     * @param type defines the type of txtValue
     */
    public GDCStringDataField(Activity activity, int tag, String fieldName, String defaultValue, GDCStringDataFieldType type) {
        this(activity,tag,fieldName,defaultValue);
        mType = type;
    }

    /**
     *
     * @param tag value can be used to recognize the field
     */
    public GDCStringDataField(Activity activity, int tag, String fieldName, String defaultValue) {
        super(activity, tag);
        mFieldName = fieldName;
        mValue = defaultValue;
    }

    @Override
    public View getView() {
        if(mView == null) {
            mView = mInflater.inflate(R.layout.string_data_field, null);

            mLblFieldName = (TextView)mView.findViewById(R.id.lblFieldName);
            mTxtValue = (EditText)mView.findViewById(R.id.txtValue);

            /*configure TextView*/
            mTxtValue.setImeOptions(EditorInfo.IME_ACTION_DONE);
            mTxtValue.setSingleLine(true);

            this.setValue(mValue);
            this.setFieldName(mFieldName);
            this.setType(mType);

            if(mCallback != null) {
                mTxtValue.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        mCallback.valueChanged(mTag, charSequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }

            this.applyStyle();
        }

        return mView;
    }

    @Override
    protected void applyStyle() {
        super.applyStyle();

        if(mView != null) {
            if(mStyle == null) {
                mStyle = new GDCStringDataFieldDefaultStyle();
            }


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

    public GDCStringDataFieldType getType() {
        return mType;
    }

    public void setType(GDCStringDataFieldType type) {
        if(type == null) {
            type = GDCStringDataFieldType.GDCStringDataFieldTypeNORMAL;
        }

        mType = type;

        if(mTxtValue != null) {
            switch (type) {
                case GDCStringDataFieldTypeNORMAL:
                    mTxtValue.setInputType(EditorInfo.TYPE_TEXT_VARIATION_NORMAL);
                    break;
                case GDCStringDataFieldTypePASSWORD:
                    mTxtValue.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    break;
                case GDCStringDataFieldTypeEMAIL:
                    mTxtValue.setInputType(EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    break;
                case GDCStringDataFieldTypeNUMERIC:
                    mTxtValue.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                    break;
                case GDCStringDataFieldTypeDATE:
                    mTxtValue.setInputType(EditorInfo.TYPE_CLASS_DATETIME);
                    break;
            }
        }
    }

    /**
     * defines the type of txtValue
     */
    public enum GDCStringDataFieldType {
        GDCStringDataFieldTypeNORMAL,
        GDCStringDataFieldTypePASSWORD,
        GDCStringDataFieldTypeEMAIL,
        GDCStringDataFieldTypeNUMERIC,
        GDCStringDataFieldTypeDATE
    }
}
