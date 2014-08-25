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
import ch.leafit.ul.list_items.ULListItemDataModel;
import ch.leafit.ul.list_items.ULListItemModel;


import java.util.ArrayList;

/**
 * Created by marius on 26/06/14.
 *
 * Opens a ListActivity where the User can select items
 */
public class GDCListDataField extends GDCDataField {

    protected String mFieldName;
    protected ArrayList<? extends ULListItemModel> mListItems;
    protected ULListItemDataModel mCurrentSelection;

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
    public GDCListDataField(Activity activity, int tag, String fieldName, ArrayList<? extends ULListItemModel> listItems,
                            ULListItemDataModel defaultSelection) {
        this(activity, tag, fieldName, listItems,defaultSelection,false,ListView.CHOICE_MODE_SINGLE);
        mCurrentSelection = defaultSelection;
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
    public GDCListDataField(Activity activity, int tag, String fieldName, ArrayList<? extends ULListItemModel> listItems,
                            ULListItemDataModel defaultSelection, boolean searchable, int CHOICE_MODE) {
        this(activity, tag, fieldName, listItems);
        mCurrentSelection = defaultSelection;
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
    public GDCListDataField(Activity activity, int tag, String fieldName, ArrayList<? extends ULListItemModel> listItems) {
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
    public GDCListDataField(Activity activity, int tag, String fieldName, ArrayList<? extends ULListItemModel> listItems,
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

            if(mCurrentSelection != null) {
                this.setCurrentSelection(mCurrentSelection);
            }

            /*add on click listener, which opens the list*/
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*open ListViewActivity*/
                    ULListActivityIntentDatastore intentDatastore = new ULListActivityIntentDatastore(mFieldName,mListItems,
                            mCurrentSelection,mCHOICE_MODE);

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


    public void setCurrentSelection(ULListItemDataModel selection) {
        mCurrentSelection = selection;
        if(mLblValue != null && selection != null) {
            mLblValue.setText(selection.getTitle());
        } else if(mLblValue != null && selection == null) {
            mLblValue.setText("");
        }
    }

    public ULListItemDataModel getCurrentSelection() {
        return mCurrentSelection;
    }

    public void setListItems(ArrayList<? extends ULListItemModel> listItems) {
        mListItems = listItems;
    }
}
