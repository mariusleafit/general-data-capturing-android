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
    protected View mView;

    protected Activity mActivity;
    protected static LayoutInflater mInflater= null;

    protected GDCDataFieldStyle mStyle;

    public GDCDataField(Activity activity) {
        mActivity = activity;
        mInflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public abstract View getView();

    public void setStyle(GDCDataFieldStyle style) {
        mStyle = style;
        this.applyStyle();
    }

    /**
     * applies the settings from the style to mView
     */
    protected void applyStyle() {
        if(mView != null && mStyle != null) {
            mView.setBackgroundColor(mStyle.backgroundColor);
        }
    }
}
