package ch.leafit.gdc.core.data_fields;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import ch.leafit.R;
import ch.leafit.gdc.core.data_fields.callback.GDCDataFieldCallback;
import ch.leafit.gdc.core.data_fields.styles.GDCDateDataFieldDefaultStyle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by marius on 30/06/14.
 */
public class GDCDateDataField extends GDCDataField implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{
    protected String mFieldName;
    protected Date mSelectedDate;
    protected GDCDataFieldCallback<Date> mCallback;

    /*UI-Elements*/
    public TextView mLblFieldName;
    public TextView mLblDateTitle;
    public TextView mLblDate;
    public TextView mLblTimeTitle;
    public TextView mLblTime;

    protected FragmentActivity mActivity;

    public GDCDateDataField(FragmentActivity activity, int tag, String fieldName, GDCDataFieldCallback<Date> callback) {
        this(activity,tag,fieldName,new Date(),callback);
        mCallback = callback;
        mFieldName = fieldName;
    }

    public GDCDateDataField(FragmentActivity activity, int tag, String fieldName, Date defaultDate, GDCDataFieldCallback<Date> callback) {
        super(activity, tag);
        mCallback = callback;
        mFieldName = fieldName;
        mSelectedDate = defaultDate;
        mActivity = activity;
    }

    @Override
    public View getView() {
        if(mView == null) {
            mView = mInflater.inflate(R.layout.date_data_field, null);

            mLblFieldName = (TextView)mView.findViewById(R.id.lblFieldName);
            mLblDateTitle = (TextView)mView.findViewById(R.id.lblDateTitle);
            mLblDate = (TextView)mView.findViewById(R.id.lblDate);
            mLblTimeTitle = (TextView)mView.findViewById(R.id.lblTimeTitle);
            mLblTime = (TextView)mView.findViewById(R.id.lblTime);

            View.OnClickListener dateClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*show date-picker dialog*/
                    DialogFragment newFragment = new DatePickerFragment(mSelectedDate,GDCDateDataField.this);
                    newFragment.show(mActivity.getSupportFragmentManager(),"datePicker");
                }
            };

            mLblDateTitle.setOnClickListener(dateClickListener);
            mLblDate.setOnClickListener(dateClickListener);

            View.OnClickListener timeClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*show time-picker dialog*/
                    DialogFragment newFragment = new TimePickerFragment(mSelectedDate,GDCDateDataField.this);
                    newFragment.show(mActivity.getSupportFragmentManager(), "timePicker");
                }
            };

            mLblTimeTitle.setOnClickListener(timeClickListener);
            mLblTime.setOnClickListener(timeClickListener);

            this.setFieldName(mFieldName);
            this.setDate(mSelectedDate);

            this.applyStyle();
        }
        return mView;
    }

    /*OnTimeSetListener*/
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        final Calendar c = Calendar.getInstance();
        c.setTime(mSelectedDate);
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE, minute);
        setDate(c.getTime());
    }

    /*OnDateSetListener*/

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        final Calendar c = Calendar.getInstance();
        c.setTime(mSelectedDate);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        setDate(c.getTime());
    }

    @Override
    protected void applyStyle() {
        if(mView != null) {
            if(mStyle == null) {
                mStyle = new GDCDateDataFieldDefaultStyle();
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

    public Date getDate() {
        return mSelectedDate;
    }

    public void setDate(Date date) {
        mSelectedDate = date;

        mLblDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
        mLblTime.setText(new SimpleDateFormat("HH:mm").format(date));
    }

    public static class TimePickerFragment extends DialogFragment{

        private Date mDate;
        private TimePickerDialog.OnTimeSetListener mOnTimeSetListener;

        public TimePickerFragment(Date date, TimePickerDialog.OnTimeSetListener onTimeSetListener) {
            mDate = date;
            mOnTimeSetListener = onTimeSetListener;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            c.setTime(mDate);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), mOnTimeSetListener, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }
    }

    public static class DatePickerFragment extends DialogFragment{

        private Date mDate;
        private DatePickerDialog.OnDateSetListener mOnDateSetListener;

        public DatePickerFragment(Date date, DatePickerDialog.OnDateSetListener onDateSetListener) {
            mDate = date;
            mOnDateSetListener = onDateSetListener;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            c.setTime(mDate);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), mOnDateSetListener, year, month, day);
        }
    }
}
