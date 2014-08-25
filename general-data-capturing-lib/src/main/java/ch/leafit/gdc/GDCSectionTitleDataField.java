package ch.leafit.gdc;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import ch.leafit.gdc.styles.GDCSectionTitleDataFieldDefaultStyle;

/**
 * Created by marius on 25/06/14.
 */
public class GDCSectionTitleDataField extends GDCDataField {

    protected  String mTitle;

    /*UI-Elements*/
    public TextView mLblTitle;

    public GDCSectionTitleDataField(Activity activity, String title) {
        this(activity,title,-1);
    }

    public GDCSectionTitleDataField(Activity activity, String title, int tag) {
        super(activity,tag);
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
                mStyle = GDCDefaultStyleConfig.sectionTitleDataFieldDefaultStyle;
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
