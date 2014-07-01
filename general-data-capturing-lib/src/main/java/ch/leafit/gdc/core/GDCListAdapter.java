package ch.leafit.gdc.core;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import ch.leafit.gdc.core.data_fields.GDCDataField;

import java.util.ArrayList;


/**
 * Created by marius on 25/06/14.
 */
public class GDCListAdapter extends BaseAdapter {

    protected ArrayList<GDCDataField> mDataFields;


    public GDCListAdapter(ArrayList<GDCDataField> dataFields) {
        mDataFields = dataFields;
    }

    @Override
    public int getCount() {
        return mDataFields.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataFields.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GDCDataField currenetDataField = mDataFields.get(i);
        if(currenetDataField != null) {
            return currenetDataField.getView();
        } else {
            return null;
        }
    }
}
