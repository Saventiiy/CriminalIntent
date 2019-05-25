package com.bignerdranch.android.criminalintent;


import java.util.List;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class CrimeListFragment extends Fragment{

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter  = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
//        mCrimeRecyclerView.getAdapter().notifyItemMoved(0, 14); maybe its works but not for me
    }



    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDataTextView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;

        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            //mTitleTextView = (TextView)itemView;

            mTitleTextView = itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDataTextView = itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime){
            mCrime = crime;                            //why we use this if we can write crime.getTitle() for example

            mTitleTextView.setText(mCrime.getTitle());
            mDataTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());

        }


        @Override
        public void onClick(View v){
            //Toast.makeText(getActivity(), mCrime.getTitle() + " Clicked", Toast.LENGTH_SHORT).show();

            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);

        }
    }


    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType){

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity()); //we also can use getLayoutInflater();

            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }


        @Override
        public void onBindViewHolder(CrimeHolder holder, int position){
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }


        @Override
        public int getItemCount(){
            return mCrimes.size();
        }



    }

}
















































