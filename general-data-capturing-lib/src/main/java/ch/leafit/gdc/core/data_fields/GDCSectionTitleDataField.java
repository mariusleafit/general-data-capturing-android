package ch.leafit.gdc.core.data_fields;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import ch.leafit.R;
import ch.leafit.gdc.core.data_fields.styles.GDCDataFieldStyle;
import ch.leafit.gdc.core.data_fields.styles.GDCSectionTitleDataFieldDefaultStyle;

import javax.xml.soap.Text;

/**
 * Created by marius on 25/06/14.
 */
public class GDCSectionTitleDataField extends GDCDataField {

    protected  String mTitle;

    /*UI-Elements*/
    public TextView mLblTitle;

    public GDCSectionTitleDataField(Activity activity, String title) {
        super(activity, -1);
        mTitle = title;
    }

    @Override
    public View getView() {
        if(mView == null) {
            mView = mInflater.inflate(R.layout.section_title_data_field, null);
            mLblTitle = (TextView) mView.findViewById(R.id.lblTitle);
            mLblTitle.setText(mTitle);
            this.applyStyle();
        }

        return mView;
    }

    @Override
    protected void applyStyle() {
        if(mView != null) {
            if(mStyle == null) {
                mStyle = new GDCSectionTitleDataFieldDefaultStyle();
            }
            mStyle.applyStyleToField(this);
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;

        mLblTitle.setText(mTitle);
    }
}
