package ch.leafit.gdc;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import ch.leafit.gdc.R;
import ch.leafit.gdc.callback.GDCDataFieldCallback;
import ch.leafit.gdc.styles.GDCStringDataFieldDefaultStyle;

/**
 * Created by marius on 25/06/14.
 */
public class GDCStringDataField extends GDCDataField {

    protected String mFieldName;
    protected String mValue;
    protected GDCDataFieldCallback mCallback;
    protected GDCStringDataFieldType mType;

    /*UI-Elements*/
    public TextView mLblFieldName;
    public EditText mTxtValue;


    /**
     *
     * @param tag value can be used to recognize the field
     * @param type defines the type of txtValue
     */
    public GDCStringDataField(Activity activity, int tag, String fieldName, String defaultValue, GDCDataFieldCallback<String> callback, GDCStringDataFieldType type) {
        super(activity,tag);
        mFieldName = fieldName;
        mValue = defaultValue;
        mCallback = callback;
        mType = type;
    }

    /**
     *
     * @param tag value can be used to recognize the field
     */
    public GDCStringDataField(Activity activity, int tag, String fieldName, String defaultValue, GDCDataFieldCallback<String> callback) {
        this(activity, tag, fieldName,defaultValue,callback,GDCStringDataFieldType.GDCStringDataFieldTypeNORMAL);
    }


    /**
     *
     * @param tag value can be used to recognize the field
     * @param type defines the type of txtValue
     */
    public GDCStringDataField(Activity activity, int tag, String fieldName, String defaultValue, GDCStringDataFieldType type) {
        this(activity,tag,fieldName,defaultValue,null,type);
    }

    /**
     *
     * @param tag value can be used to recognize the field
     */
    public GDCStringDataField(Activity activity, int tag, String fieldName, String defaultValue) {
        this(activity,tag,fieldName,defaultValue,null,GDCStringDataFieldType.GDCStringDataFieldTypeNORMAL);
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

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        mCallback.valueChanged(mTag, editable.toString());
                    }
                });

                mTxtValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        Log.i("focus", mFieldName);
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
                mStyle = GDCDefaultStyleConfig.stringDataFieldDefaultStyle;
            }
            mStyle.applyStyleToField(this);
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
                    mTxtValue.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                    break;
                case GDCStringDataFieldTypePASSWORD:
                    mTxtValue.setInputType(EditorInfo.TYPE_CLASS_TEXT| EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
                    break;
                case GDCStringDataFieldTypeEMAIL:
                    mTxtValue.setInputType(EditorInfo.TYPE_CLASS_TEXT|EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    break;
                case GDCStringDataFieldTypeNUMERIC:
                    mTxtValue.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                    break;
                case GDCStringDataFieldTypeDATE:
                    mTxtValue.setInputType(EditorInfo.TYPE_CLASS_DATETIME);
                    break;
                default:
                    mTxtValue.setInputType(EditorInfo.TYPE_CLASS_TEXT);
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
