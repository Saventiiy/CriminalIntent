package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.Context;
import java.util.UUID;


public class CrimeActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";;

    public static Intent newIntent(Context packageContext, UUID crimeId){

        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);

        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new CrimeFragment();
    }
}
