package com.gardenofwine.www.fastreport;

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
import butterknife.OnTextChanged;

/**
 * Created by bweingarten on 4/29/15.
 */
public class ServiceFormFragment extends Fragment {

    @OnTextChanged(R.id.firstNameField) void onFirstNameChanged(CharSequence text) {
        Toast.makeText(getActivity(), "Text changed: " + text, Toast.LENGTH_SHORT).show();
    }

    @OnTextChanged(R.id.lastNameField) void onLastNameChanged(CharSequence text) {
        Toast.makeText(getActivity(), "Text changed: " + text, Toast.LENGTH_SHORT).show();
    }

    private AutoCompleteTextView mAddressTextView;
    private StreetDao mStreetDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FastReportDBHelper dbHelper = OpenHelperManager.getHelper(getActivity(), FastReportDBHelper.class);
        mStreetDao = dbHelper.getDao(Street.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_service_form, container, false);
        ButterKnife.inject(this, rootView);

        mAddressTextView = (AutoCompleteTextView) rootView.findViewById(R.id.addressField);
        mAddressTextView.setAdapter(new StreetAdapter(getActivity(), mStreetDao));

        final Button button = (Button) rootView.findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new PostServiceRequestTask().execute("");
            }
        });

        return rootView;
    }

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
