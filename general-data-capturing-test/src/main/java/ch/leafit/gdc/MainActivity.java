package ch.leafit.gdc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;
import ch.leafit.gdc.callback.GDCClickDataFieldCallback;
import ch.leafit.gdc.callback.GDCDataFieldCallback;
import ch.leafit.ul.activities.intent_datastores.ULListActivityReturnIntentDatastore;
import ch.leafit.ul.list_items.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by marius on 25/06/14.
 */
public class MainActivity extends FragmentActivity{

    private static final int list_data_field_tag = 0;

    private ListView mListView;
    private GDCListAdapter mListAdapter;

    private GDCStringDataField mTestField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.lstMain);
        mListView.setItemsCanFocus(true);


        ArrayList<GDCDataField> dataFields = new ArrayList<GDCDataField>();

        GDCDataFieldCallback<String> callback = new GDCDataFieldCallback<String>() {
            @Override
            public void valueChanged(int tag, String value) {
                Log.i("adsf",value);
            }
        };

        mTestField = new GDCStringDataField(this, 1, "Name","Gächter",callback, GDCStringDataField.GDCStringDataFieldType.GDCStringDataFieldTypePASSWORD);
        mTestField.setDisabled(true);
        dataFields.add(new GDCSectionTitleDataField(this,"Section"));
        dataFields.add(mTestField);
        dataFields.add(new GDCStringDataField(this, 1, "Name","Gächter",callback, GDCStringDataField.GDCStringDataFieldType.GDCStringDataFieldTypeEMAIL));
        dataFields.add(new GDCStringDataField(this,1, "Vorname","Marius",callback));
        dataFields.add(new GDCSectionTitleDataField(this,"asdf"));
        dataFields.add(new GDCStringDataField(this,1,"Name","Marius",callback));
        dataFields.add(new GDCSectionTitleDataField(this,"Marius"));
        dataFields.add(new GDCStringDataField(this,1,"Name","Marius",callback));

        GDCClickDataField clickDataField = new GDCClickDataField(this,-1,"Click Test",new GDCClickDataFieldCallback() {
            @Override
            public void fieldClicked(int tag) {
                if(mTestField.getMarking() == GDCDataField.GDCDataFieldMarking.MARKED_AS_INVALID) {
                    mTestField.setMarking(GDCDataField.GDCDataFieldMarking.MARKED_AS_VALID);
                } else {
                    mTestField.setMarking(GDCDataField.GDCDataFieldMarking.MARKED_AS_INVALID);
                }

            }
        });
        clickDataField.setDisabled(true);
        dataFields.add(clickDataField);


        ArrayList<ULListItemModel> simpleItems = new ArrayList<ULListItemModel>();
        simpleItems.add(new ULOneFieldListItemModel(new ULListItemData("Marikus")));
        simpleItems.add(new ULOneFieldListItemModel(new ULListItemData("Neuchatel")));
        simpleItems.add(new ULOneFieldListItemModel(new ULListItemData("Computer")));
        simpleItems.add(new ULSectionTitleListItemModel(new ULListItemData("Section")));
        simpleItems.add(new ULOneFieldListItemModel(new ULListItemData("IntelliJ")));
        simpleItems.add(new ULOneFieldListItemModel(new ULListItemData("Einkaufen")));
        simpleItems.add(new ULTwoFieldsListItemModel(new ULListItemData("Marius","Gächter")));
        simpleItems.add(new ULTwoFieldsListItemModel(new ULListItemData("Marius","Gächter")));
        simpleItems.add(new ULOneFieldListItemModel(new ULListItemData("Marius")));


        GDCListDataField listDataField = new GDCListDataField(this,list_data_field_tag,"TestList",
                simpleItems,true,ListView.CHOICE_MODE_MULTIPLE);

        dataFields.add(listDataField);

        dataFields.add(new GDCDateDataField(this,1,"Datum",new GDCDataFieldCallback<Date>() {
            @Override
            public void valueChanged(int tag, Date value) {
                Log.i("datum",value.toString());
            }
        }));

        GDCIntegerDataField integerDataField = new GDCIntegerDataField(this,1,"Integer",new GDCDataFieldCallback<Integer>() {
            @Override
            public void valueChanged(int tag, Integer value) {
                Log.i("integer", String.valueOf(value));
            }
        },10);
        integerDataField.setDisabled(true);
        dataFields.add(integerDataField);


        mListAdapter = new GDCListAdapter(dataFields);

        mListView.setAdapter(mListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case list_data_field_tag:
                if(data != null) {
                    ULListActivityReturnIntentDatastore returnIntentDatastore = new ULListActivityReturnIntentDatastore(data);
                    if (returnIntentDatastore != null) {
                        Log.i("adsfasf", String.valueOf(returnIntentDatastore.mSelectedItems.size()));
                    }
                }
                break;
        }

    }
}
