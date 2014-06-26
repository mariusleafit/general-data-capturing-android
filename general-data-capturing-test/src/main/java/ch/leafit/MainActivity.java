package ch.leafit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import ch.leafit.gdc.core.GDCListAdapter;
import ch.leafit.gdc.core.data_fields.GDCClickDataField;
import ch.leafit.gdc.core.data_fields.GDCDataField;
import ch.leafit.gdc.core.data_fields.GDCSectionTitleDataField;
import ch.leafit.gdc.core.data_fields.GDCStringDataField;
import ch.leafit.gdc.core.data_fields.callback.GDCClickDataFieldCallback;
import ch.leafit.gdc.core.data_fields.callback.GDCDataFieldCallback;

import java.util.ArrayList;

/**
 * Created by marius on 25/06/14.
 */
public class MainActivity extends Activity{

    private ListView mListView;
    private GDCListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.lstMain);

        ArrayList<GDCDataField> dataFields = new ArrayList<GDCDataField>();

        GDCDataFieldCallback<String> callback = new GDCDataFieldCallback<String>() {
            @Override
            public void valueChanged(int tag, String value) {
                Log.i("adsf",value);
            }
        };

        dataFields.add(new GDCSectionTitleDataField(this,"Section"));
        dataFields.add(new GDCStringDataField(this, 1, "Name","Gächter",callback, GDCStringDataField.GDCStringDataFieldType.GDCStringDataFieldTypeEMAIL));
        dataFields.add(new GDCStringDataField(this,1, "Vorname","Marius",callback));
        dataFields.add(new GDCSectionTitleDataField(this,"asdf"));
        dataFields.add(new GDCStringDataField(this,1,"Name","Marius",callback));
        dataFields.add(new GDCSectionTitleDataField(this,"Marius"));
        dataFields.add(new GDCStringDataField(this,1,"Name","Marius",callback));
        dataFields.add(new GDCClickDataField(this,-1,"Click Test",new GDCClickDataFieldCallback() {
            @Override
            public void fieldClicked(int tag) {
                Log.i("asdfaf","CLICKED!!!");
            }
        }));


        mListAdapter = new GDCListAdapter(dataFields);

        mListView.setAdapter(mListAdapter);
    }
}
