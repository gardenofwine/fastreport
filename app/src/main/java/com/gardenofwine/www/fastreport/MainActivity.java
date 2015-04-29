package com.gardenofwine.www.fastreport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.gardenofwine.www.fastreport.adapters.StreetAdapter;
import com.gardenofwine.www.fastreport.db.FastReportDBHelper;
import com.gardenofwine.www.fastreport.db.dao.StreetDao;
import com.gardenofwine.www.fastreport.db.models.Street;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ServiceRequestFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ServiceRequestFragment extends Fragment {

        private AutoCompleteTextView mAddressTextView;

        private StreetDao mStreetDao;

        public ServiceRequestFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            FastReportDBHelper dbHelper = OpenHelperManager.getHelper(getActivity(), FastReportDBHelper.class);
            mStreetDao = dbHelper.getDao(Street.class);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mAddressTextView = (AutoCompleteTextView) rootView.findViewById(R.id.addressField);
            mAddressTextView.setAdapter(new StreetAdapter(mStreetDao));

            final Button button = (Button) rootView.findViewById(R.id.submit_button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
//                    new PostServiceRequestTask().execute("");
                }
            });

            return rootView;
        }

        @Override
        public void onDestroy() {
            OpenHelperManager.releaseHelper();
            super.onDestroy();
        }
    }

    public static class PostServiceRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            new DigitelMobileAPI().postServiceRequest(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }
}
