package ch.leafit.gdc;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import ch.leafit.gdc.styles.GDCDataFieldStyle;
import ch.leafit.ul.activities.ULListActivity;
import ch.leafit.ul.activities.ULSearchListActivity;
import ch.leafit.ul.activities.intent_datastores.ULListActivityIntentDatastore;
import ch.leafit.ul.list_items.ULListItemBaseModel;


import java.util.ArrayList;

/**
 * Created by marius on 26/06/14.
 *
 * Opens a ListActivity where the User can select items
 */
public class GDCListDataField extends GDCDataField {

    protected String mFieldName;
    protected ArrayList<ULListItemBaseModel> mListItems;
    protected ULListItemBaseModel mDefaultSelection;

    protected boolean mSearchable;
    protected int mCHOICE_MODE;

    /*UI-Elements*/
    public TextView mLblFieldName;
    public TextView mLblDisclosureIndicator;
    public TextView mLblValue;
    /**
     *
     * @param activity parent activity
     * @param tag int to identify the field
     * @param fieldName string to be shown in the name-field
     * @param listItems items to be showed in the list
     * @param defaultSelection defaultValue
     */
    public GDCListDataField(Activity activity, int tag, String fieldName, ArrayList<ULListItemBaseModel> listItems,
                            ULListItemBaseModel defaultSelection) {
        this(activity, tag, fieldName, listItems,defaultSelection,false,ListView.CHOICE_MODE_SINGLE);
        mDefaultSelection = defaultSelection;
    }

    /**
     *
     * @param activity parent activity
     * @param tag int to identify the field
     * @param fieldName string to be shown in the name-field
     * @param listItems items to be showed in the list
     * @param defaultSelection defaultValue
     * @param searchable is the list searchable? (default false)
     * @param CHOICE_MODE ListView.CHOICE_MODE (default SINGLE)
     */
    public GDCListDataField(Activity activity, int tag, String fieldName, ArrayList<ULListItemBaseModel> listItems,
                            ULListItemBaseModel defaultSelection, boolean searchable, int CHOICE_MODE) {
        this(activity, tag, fieldName, listItems);
        mDefaultSelection = defaultSelection;
        mSearchable = searchable;
        mCHOICE_MODE = CHOICE_MODE;
    }

    /**
     *
     * @param activity parent activity
     * @param tag int to identify the field
     * @param fieldName string to be shown in the name-field
     * @param listItems items to be showed in the list
     */
    public GDCListDataField(Activity activity, int tag, String fieldName, ArrayList<ULListItemBaseModel> listItems) {
        this(activity,tag,fieldName,listItems,false, ListView.CHOICE_MODE_SINGLE);
        mFieldName = fieldName;
        mListItems = listItems;
    }

    /**
     *
     * @param activity parent activity
     * @param tag int to identify the field
     * @param fieldName string to be shown in the name-field
     * @param listItems items to be showed in the list
     * @param searchable is the list searchable? (default false)
     * @param CHOICE_MODE ListView.CHOICE_MODE (default SINGLE)
     */
    public GDCListDataField(Activity activity, int tag, String fieldName, ArrayList<ULListItemBaseModel> listItems,
                            boolean searchable, int CHOICE_MODE) {
        super(activity, tag);
        mFieldName = fieldName;
        mListItems = listItems;
        mSearchable = searchable;
        mCHOICE_MODE = CHOICE_MODE;
    }

    @Override
    public View getView() {
        if(mView == null) {
            mView = mInflater.inflate(R.layout.list_data_field, null);

            mLblFieldName = (TextView)mView.findViewById(R.id.lblFieldName);
            mLblValue = (TextView)mView.findViewById(R.id.lblValue);
            mLblDisclosureIndicator = (TextView)mView.findViewById(R.id.lblDisclosureIndicator);

            this.setFieldName(mFieldName);

            if(mDefaultSelection != null) {
                this.setValue(mDefaultSelection.getValueString());
            }

            /*add on click listener, which opens the list*/
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*open ListViewActivity*/
                    ULListActivityIntentDatastore intentDatastore = new ULListActivityIntentDatastore(mFieldName,mListItems,
                            mDefaultSelection,mCHOICE_MODE);

                    Intent listIntent = null;
                    if(mSearchable) {
                        listIntent = intentDatastore.getIntent(mActivity, ULSearchListActivity.class);
                    } else {
                        listIntent = intentDatastore.getIntent(mActivity, ULListActivity.class);
                    }

                    mActivity.startActivityForResult(listIntent,mTag);
                }
            });

            this.applyStyle();
        }

        return mView;
    }

    @Override
    protected void applyStyle() {
        if(mView != null) {
            if(mStyle == null || !(mStyle instanceof GDCDataFieldStyle)) {
                mStyle = GDCDefaultStyleConfig.listDataFieldDefaultStyle;
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

    public String getValue() {
        return mLblValue.getText().toString();
    }
    public void setValue(String value) {
        mLblValue.setText(value);
    }
}
