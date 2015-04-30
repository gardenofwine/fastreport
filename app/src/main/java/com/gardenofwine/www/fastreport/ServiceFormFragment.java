package com.gardenofwine.www.fastreport;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gardenofwine.www.fastreport.adapters.StreetAdapter;
import com.gardenofwine.www.fastreport.db.FastReportDBHelper;
import com.gardenofwine.www.fastreport.db.dao.StreetDao;
import com.gardenofwine.www.fastreport.db.models.Street;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by bweingarten on 4/29/15.
 */
public class ServiceFormFragment extends Fragment {


    @OnTextChanged(R.id.firstNameField) void onFirstNameChanged(CharSequence text) {
        commitStringPref(R.string.first_name_key, text);
    }


    @OnTextChanged(R.id.lastNameField) void onLastNameChanged(CharSequence text) {
        commitStringPref(R.string.last_name_key, text);
    }

    @OnTextChanged(R.id.phoneField) void onPhoneChanged(CharSequence text) {
        commitStringPref(R.string.phone_key, text);
    }

    @OnClick(R.id.submit_button) void onSubmitReport(){
        new PostServiceRequestTask().execute("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        mPrefsEditor = mPrefs.edit();

        FastReportDBHelper dbHelper = OpenHelperManager.getHelper(getActivity(), FastReportDBHelper.class);
        mStreetDao = dbHelper.getDao(Street.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_service_form, container, false);
        ButterKnife.inject(this, rootView);

        mFirstNameEditText.setText(mPrefs.getString(getString(R.string.first_name_key), ""));
        mLastNameEditText.setText(mPrefs.getString(getString(R.string.last_name_key), ""));
        mPhoneEditText.setText(mPrefs.getString(getString(R.string.phone_key), ""));


        mAddressTextView = (AutoCompleteTextView) rootView.findViewById(R.id.addressField);
        mAddressTextView.setAdapter(new StreetAdapter(getActivity(), mStreetDao));

        return rootView;
    }

    private void commitStringPref(int stringKey, CharSequence string){
        mPrefsEditor.putString(getString(stringKey), string.toString());
        mPrefsEditor.commit();
    }

    private AutoCompleteTextView mAddressTextView;
    private StreetDao mStreetDao;
    private SharedPreferences.Editor mPrefsEditor;
    private SharedPreferences mPrefs;

    @InjectView(R.id.firstNameField) EditText mFirstNameEditText;
    @InjectView(R.id.lastNameField) EditText mLastNameEditText;
    @InjectView(R.id.phoneField) EditText mPhoneEditText;

    public static class PostServiceRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
//            new DigitelMobileAPI().postServiceRequest(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }


}
