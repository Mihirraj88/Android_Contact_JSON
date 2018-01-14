package com.mihir.minix.contactlistapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mihir.minix.contactlistapplication.adapter.ContactListAdapter;
import com.mihir.minix.contactlistapplication.model.Address;
import com.mihir.minix.contactlistapplication.model.Company;
import com.mihir.minix.contactlistapplication.model.Contact;
import com.mihir.minix.contactlistapplication.model.Geo;
import com.mihir.minix.contactlistapplication.retrofit_interface.ApiInterface;
import com.mihir.minix.contactlistapplication.retrofit_interface.ApiUtils;
import com.mihir.minix.contactlistapplication.utils.CustomComparator;
import com.mihir.minix.contactlistapplication.utils.ItemClickSupport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Object> {

    ApiInterface apiInterface;
    private List<Contact> contacts = new ArrayList<>();
    RecyclerView mRecyclerView;
    ProgressDialog pd;
    Button mButton;
    private boolean ascending = false;

    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pd = new ProgressDialog(MainActivity.this);
        pd.setCancelable(false);
        mRecyclerView =  findViewById(R.id.R_view);
        mButton = findViewById(R.id.btn_Sort);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(5000);
        itemAnimator.setRemoveDuration(5000);
        mRecyclerView.setItemAnimator(itemAnimator);

        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        apiInterface = ApiUtils.apiInterface();
        getContact();
        clickOnRecyclearView();

    }


    private void getContact() {
        pd.setMessage("loading");
        pd.show();
        apiInterface.getContact().enqueue(this);
    }

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        Log.v("Response: ", response.body() + "");
        if (!MainActivity.this.isFinishing() && pd != null) {
            pd.dismiss();
        }


        try {
            JSONArray array = new JSONArray(new Gson().toJson(response.body()));
            Log.v("Response: ", array.length() + "");
            parseDataSetInPojo(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        Log.v("Response: ", "Inside onFailure");
        if (!MainActivity.this.isFinishing() && pd != null) {
            pd.dismiss();
        }
        Toast.makeText(getApplicationContext(), "Something Went Wrong ):", Toast.LENGTH_SHORT).show();
    }

    private void parseDataSetInPojo(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            Contact contact = new Contact();
            Address address = new Address();
            Geo geo = new Geo();
            Company company = new Company();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Log.v("ELEMENT ID: ", jsonObject.getString("id"));
            contact.setId(jsonObject.getInt("id"));
            contact.setName(jsonObject.getString("name"));
            contact.setUsername(jsonObject.getString("username"));
            contact.setEmail(jsonObject.getString("email"));
            contact.setPhone(jsonObject.getString("phone"));
            contact.setWebsite(jsonObject.getString("website"));
            address.setStreet(jsonObject.getJSONObject("address").getString("street"));
            address.setSuite(jsonObject.getJSONObject("address").getString("suite"));
            address.setCity(jsonObject.getJSONObject("address").getString("city"));
            address.setZipcode(jsonObject.getJSONObject("address").getString("zipcode"));
            geo.setLat(jsonObject.getJSONObject("address").getJSONObject("geo").getString("lat"));
            geo.setLng(jsonObject.getJSONObject("address").getJSONObject("geo").getString("lng"));
            address.setGeo(geo);
            contact.setAddress(address);
            company.setBs(jsonObject.getJSONObject("company").getString("bs"));
            company.setName(jsonObject.getJSONObject("company").getString("name"));
            company.setCatchPhrase(jsonObject.getJSONObject("company").getString("catchPhrase"));
            contact.setCompany(company);
            contacts.add(contact);
        }
        ContactListAdapter mAdapter = new ContactListAdapter(this, contacts,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void clickOnRecyclearView() {
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Contact contact = new Contact();
                Address address = new Address();
                Geo geo = new Geo();
                Company company = new Company();

                Intent i = new Intent(MainActivity.this, ContactDetails.class);
                contact.setPhone(contacts.get(position).getPhone());
                contact.setEmail(contacts.get(position).getEmail());
                contact.setUsername(contacts.get(position).getUsername());
                contact.setName(contacts.get(position).getName());
                contact.setId(contacts.get(position).getId());
                contact.setWebsite(contacts.get(position).getWebsite());
                geo.setLng(contacts.get(position).getAddress().getGeo().getLng());
                geo.setLat(contacts.get(position).getAddress().getGeo().getLat());
                address.setGeo(geo);
                address.setZipcode(contacts.get(position).getAddress().getZipcode());
                address.setCity(contacts.get(position).getAddress().getCity());
                address.setStreet(contacts.get(position).getAddress().getStreet());
                address.setSuite(contacts.get(position).getAddress().getSuite());
                contact.setAddress(address);
                company.setName(contacts.get(position).getCompany().getName());
                company.setCatchPhrase(contacts.get(position).getCompany().getCatchPhrase());
                company.setBs(contacts.get(position).getCompany().getBs());
                contact.setCompany(company);
                i.putExtra("contact", contact);
                startActivity(i);

            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortData(ascending);

            }
        });

    }

    private void sortData(boolean asc) {
        if (asc) {
            Collections.sort(contacts, new CustomComparator());
            ascending = !ascending;
        } else {
            Collections.reverse(contacts);
        }
        ContactListAdapter mAdapter = new ContactListAdapter(this, contacts,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

    }

    protected void onDestroy () {
        super.onDestroy();
        if (pd != null)
            if (pd.isShowing())
                pd.dismiss();
        pd = null;
    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        // Save list state
        listState = mRecyclerView.getLayoutManager() .onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, listState);
    }
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        // Retrieve list state and list/item positions
        if(state != null)
            listState = state.getParcelable(LIST_STATE_KEY);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            mRecyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

}
