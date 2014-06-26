package ch.leafit.gdc.core.data_fields;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import ch.leafit.R;
import ch.leafit.gdc.core.data_fields.styles.GDCDataFieldStyle;

import javax.xml.soap.Text;

/**
 * Created by marius on 25/06/14.
 */
public class GDCSectionTitleDataField extends GDCDataField {

    protected  String mTitle;

    /*UI-Elements*/
    protected TextView mLblTitle;

    public GDCSectionTitleDataField(Activity activity, String title) {
        super(activity);
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
        super.applyStyle();

        if(mView != null && mStyle != null) {
            
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
