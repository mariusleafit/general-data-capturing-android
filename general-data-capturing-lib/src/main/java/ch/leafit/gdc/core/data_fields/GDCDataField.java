package ch.leafit.gdc.core.data_fields;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import ch.leafit.gdc.core.data_fields.styles.GDCDataFieldStyle;

/**
 * Created by marius on 25/06/14.
 */
public abstract class GDCDataField  {
    public View mView;
    protected int mTag;

    protected Activity mActivity;
    protected static LayoutInflater mInflater= null;

    protected GDCDataFieldStyle mStyle;

    protected GDCDataFieldMarking mMarking = GDCDataFieldMarking.NOT_MARKED;

    protected boolean mDisabled = false;


    /**
     *
     * @param activity
     * @param tag value can be used to recognize the field
     */
    public GDCDataField(Activity activity, int tag) {
        mActivity = activity;
        mInflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mTag = tag;
    }

    public abstract View getView();

    public void setStyle(GDCDataFieldStyle style) {
        mStyle = style;
        applyStyle();
    }

    /**
     * applies the settings from the style to mView
     */
    protected abstract void applyStyle();

    public int getTag() {
        return mTag;
    }

    /**
     * indicates to the user that the field was or wasn't filled properly with data
     */
    public void setMarking(GDCDataFieldMarking marking) {
        mMarking = marking;
        applyStyle();
    }

    public GDCDataFieldMarking getMarking() {
        return mMarking;
    }

    public boolean isDisabled() {
        return mDisabled;
    }
    public void setDisabled(boolean disabled) {
        mDisabled = disabled;
        applyStyle();
    }

    /**
     * different types of marking
     */
    public enum GDCDataFieldMarking {
        MARKED_AS_INVALID,
        MARKED_AS_VALID,
        NOT_MARKED
    }
}
