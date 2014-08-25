package ch.leafit.gdc;

import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marius on 14/08/14.
 *
 * the listItems can be hidden (referenced by their tags)
 */
public class GDCHideableListAdapter extends GDCListAdapter implements Filterable{

    ArrayList<Integer> mHiddenItemTags;

    private GDCDataFieldFilter mFilter;
    private List<GDCDataField> mUnfilteredMenuItems;


    public GDCHideableListAdapter(ArrayList<GDCDataField> dataFields, ArrayList<Integer> hiddenItemTags) {
        super(dataFields);
        mUnfilteredMenuItems = dataFields;
        mHiddenItemTags = new ArrayList<Integer>();
        if(hiddenItemTags != null) {
            this.addHiddenItems(hiddenItemTags);
        }
    }

    public void addHiddenItem(int tag) {
        mHiddenItemTags.add(tag);
        this.getFilter().filter("");
    }

    public void removeHiddenItem(int tag) {
        if(mHiddenItemTags.contains(tag)) {
            mHiddenItemTags.remove(Integer.valueOf(tag));
        }
        this.getFilter().filter("");
    }

    public void clearHiddenItems() {
        mHiddenItemTags.clear();
        this.getFilter().filter("");
    }

    public void addHiddenItems(ArrayList<Integer> itemTags) {
        if(itemTags != null)
            mHiddenItemTags.addAll(itemTags);
        this.getFilter().filter("");
    }

    public void toggleVisibilityOfItem(int tag) {
        if(mHiddenItemTags.contains(tag)) {
            this.removeHiddenItem(tag);
        } else {
            this.addHiddenItem(tag);
        }
    }

    /*
	 * @Filterable members
	 */

    public Filter getFilter() {
        if(mFilter == null)
            mFilter = new GDCDataFieldFilter();
        return mFilter;
    }


    /*
     * Filter implementation
     *
     * filters the items with the tag in mHiddenItemTags
     */
    private class GDCDataFieldFilter extends Filter {

        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (mHiddenItemTags == null || mHiddenItemTags.size() == 0) {
                // No filter implemented we return all the list
                results.values = mUnfilteredMenuItems;
                results.count = mUnfilteredMenuItems.size();
            } else {
                // We perform filtering operation
                List<GDCDataField> nListItems = new ArrayList<GDCDataField>();

                for (GDCDataField item : mUnfilteredMenuItems) {
                    if(!mHiddenItemTags.contains(item.getTag()))
                        nListItems.add(item);
                }

                results.values = nListItems;
                results.count = nListItems.size();

            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            mDataFields = (ArrayList<GDCDataField>) results.values;
            notifyDataSetChanged();
        }

    }
}
