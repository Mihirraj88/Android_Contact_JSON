package com.mihir.minix.contactlistapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.widget.TextView;

import com.mihir.minix.contactlistapplication.model.Contact;

public class ContactDetails extends AppCompatActivity {
    TextView mName, mUsername,mEmail, mAddress, mWebsite, mPhoneNumber, mCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        initView();
        loadDetailsContent();

    }

    private void loadDetailsContent() {
        Contact contact = getIntent().getExtras().getParcelable("contact");

        mName.setText("NAME: "+contact.getName());
        mUsername.setText("USERNAME:  " + contact.getUsername());
        mEmail.setText("EMAIL:  "+contact.getEmail());
        mWebsite.setText("WEBSITE:  "+contact.getWebsite());
        mPhoneNumber.setText("PHONE NUMBER:  "+contact.getPhone());
        setAddress(contact) ;
    }

    private void initView() {
        mName = findViewById(R.id.name);
        mAddress = findViewById(R.id.Address);
        mUsername = findViewById(R.id.username);
        mEmail = findViewById(R.id.email_id);
        mWebsite = findViewById(R.id.website);
        mPhoneNumber = findViewById(R.id.PhoneNumber);
        mCompany = findViewById(R.id.CompanyName);

    }

    public void setAddress(Contact contact){
        SpannableString styledString
                = new SpannableString("ADDRESS: "
                        +contact.getAddress().getStreet()+"\n"
                +contact.getAddress().getSuite()+"\n"
                +contact.getAddress().getCity()+"\n"
                +contact.getAddress().getZipcode()+"\n"
                +contact.getAddress().getGeo().getLat()+" "
                +contact.getAddress().getGeo().getLng());


        SpannableString spannableString = new SpannableString ("COMPANY: "+
                contact.getCompany().getName()+ "\n"
                + contact.getCompany().getCatchPhrase()+"\n"
                +contact.getCompany().getBs()
        );

        mCompany.setText(spannableString);
        mAddress.setText(styledString);
    }




}
