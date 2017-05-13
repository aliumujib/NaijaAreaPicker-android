/*
 * Copyright (c) 2017. Created by Aliu Abdul-Mujib Ololade.
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Aliu Abdul-Mujib
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.alium.naijaareapicklib.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alium.naijaareapicklib.R;
import com.alium.naijaareapicklib.commons.Constants;
import com.alium.naijaareapicklib.commons.Utils;
import com.alium.naijaareapicklib.dialog.SearchableSpinner;
import com.alium.naijaareapicklib.interfaces.OnFilterInteractionListener;
import com.alium.naijaareapicklib.model.LocalGovernmentArea;
import com.alium.naijaareapicklib.model.State;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdulmujibaliu on 5/2/17.
 */

public class AreaPicker extends LinearLayout {

    View mView = null;
    Context mContext = null;

    private SearchableSpinner mStateSearchableSpinner;
    private SearchableSpinner mLGASearchableSpinner;
    private TextView mStateSearchableSpinnerLinkTextView, mLGASearchableSpinnerLinkTextView, mOtherFiltersTextView;
    private List<String> stateNames = new ArrayList<>();
    private List<String> lgaNames = new ArrayList<>();

    protected OnStateSelected mOnStateSelected;
    protected OnLGASelected mOnLGASelected;
    protected OnFilterInteractionListener mOnFilterInteractionListener;

    protected State mState;
    protected LocalGovernmentArea mLocalGovernmentArea;

    private List<State> stateObjects = new ArrayList<>();
    private List<LocalGovernmentArea> lgaObjects = new ArrayList<>();


    ArrayAdapter<String> stateDataAdapter;
    ArrayAdapter<String> lgaDataAdapter;


    public AreaPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;
        initialize();
    }

    public AreaPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initialize();
    }


    public View getmView() {
        return mView;
    }

    public void setmView(View mView) {
        this.mView = mView;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    //TODO CHECK THAT CONTEXT AND VIEW ARE INIT, IF NOT THROW ILLEGAL STATE EXCEPTION!

    //topick =0 for state ... 1 dor LGA
    public void initialize() {

        mView = LayoutInflater.from(mContext).inflate(R.layout.filter_controllers_layout, this, true);

        mStateSearchableSpinner = (SearchableSpinner) mView.findViewById(R.id.state_spinner);
        mStateSearchableSpinner.set_titleString("Pick a state");
        mLGASearchableSpinner = (SearchableSpinner) mView.findViewById(R.id.lga_spinner);
        mLGASearchableSpinner.set_titleString("Pick a LGA");
        mStateSearchableSpinnerLinkTextView = (TextView) mView.findViewById(R.id.state_spinner_link);
        mLGASearchableSpinnerLinkTextView = (TextView) mView.findViewById(R.id.lga_spinner_link);
        mOtherFiltersTextView = (TextView) mView.findViewById(R.id.filters);

        if (mState != null) {
            mStateSearchableSpinnerLinkTextView.setText(mState.getName());
        }

        if (mLocalGovernmentArea != null) {
            mLGASearchableSpinnerLinkTextView.setText(mLocalGovernmentArea.getName());
        }

        if (mOtherFiltersTextView != null) {
            mOtherFiltersTextView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mState == null){
                        Toast.makeText(mContext, "Please pick a state first", Toast.LENGTH_SHORT).show();
                    }else if(mLocalGovernmentArea == null){
                        Toast.makeText(mContext, "Please pick a LGA first", Toast.LENGTH_SHORT).show();
                    }else {
                        mOnFilterInteractionListener.onFiltersInteraction(mState, mLocalGovernmentArea);
                    }
                }
            });
        }

        createStateSearchableSpinner();

    }

    private void createStateSearchableSpinner() {
        // Spinner click listener
        mStateSearchableSpinner.setOnPositionClickedListener(mOnStatePositionClicked);

        // Creating adapter for spinner
        stateDataAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, getStates());
        // attaching data adapter to spinner
        mStateSearchableSpinner.setAdapter(stateDataAdapter);

        mStateSearchableSpinnerLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStateSearchableSpinner.performClick();
            }
        });
    }

    private void createLGASearchableSpinner(int index) {
        // Spinner click listener
        mLGASearchableSpinner.setOnPositionClickedListener(mOnLGAPositionClicked);


        // Creating adapter for spinner
        lgaDataAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, getLGAs(stateObjects.get(index).getmLGAArray()));
        // attaching data adapter to spinner
        mLGASearchableSpinner.setAdapter(lgaDataAdapter);

        mLGASearchableSpinnerLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLGASearchableSpinner.performClick();
            }
        });
    }

    public void setmOnStateSelected(OnStateSelected mOnStateSelected) {
        this.mOnStateSelected = mOnStateSelected;
    }

    public void setmOnLGASelected(OnLGASelected mOnLGASelected) {
        this.mOnLGASelected = mOnLGASelected;
    }

    public void setmOnFilterInteractionListener(OnFilterInteractionListener mOnFilterInteractionListener) {
        this.mOnFilterInteractionListener = mOnFilterInteractionListener;
    }

    private List<String> getStates() {
        stateNames.clear();
        stateObjects.clear();
        try {
            JSONArray m_jArry = new JSONArray(Utils.loadJSONFromAsset(Constants.STATES_LGA_JSON_FILE_NAME, mContext));

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i).getJSONObject(Constants.STATES_LGA_JSON_FILE_STATE_KEY);
                String nameValue = jo_inside.getString(Constants.STATES_LGA_JSON_FILE_STATE_NAME);
                Integer idValue = jo_inside.getInt(Constants.STATES_LGA_JSON_FILE_STATE_ID);
                JSONArray lga_array = jo_inside.getJSONArray(Constants.STATES_LGA_JSON_FILE_LGA_ARRAY_KEY);

                stateNames.add(nameValue);

                State state = new State(nameValue, idValue, lga_array);
                stateObjects.add(state);

                Log.d("Details-->", nameValue);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stateNames;
    }


    private List<String> getLGAs(JSONArray jsonArray) {
        lgaObjects.clear();
        lgaNames.clear();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo_insideSub = jsonArray.getJSONObject(i);
                String nameValueSub = jo_insideSub.getString(Constants.STATES_LGA_JSON_FILE_LGA_NAME);
                Integer idValueSub = jo_insideSub.getInt(Constants.STATES_LGA_JSON_FILE_LGA_ID);

                LocalGovernmentArea lga = new LocalGovernmentArea(nameValueSub, idValueSub);
                lgaObjects.add(lga);

                lgaNames.add(nameValueSub);

                Log.d("Details-->", nameValueSub);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lgaNames;
    }


    private SearchableSpinner.OnPositionClicked mOnStatePositionClicked = new SearchableSpinner.OnPositionClicked() {
        @Override
        public void onPositionClicked(int position) {
            Log.d("Selected state", "" + stateNames.get(position));
            mStateSearchableSpinnerLinkTextView.setText(stateNames.get(position));
            mState = stateObjects.get(position);
            //reset LGA
            mLocalGovernmentArea = null;
            mLGASearchableSpinnerLinkTextView.setText(R.string.local_govt_area_abbrev);
            if (mOnStateSelected != null) {
                mOnStateSelected.onStateSelected(position, mState);
            }
            createLGASearchableSpinner(position);
        }
    };


    private SearchableSpinner.OnPositionClicked mOnLGAPositionClicked = new SearchableSpinner.OnPositionClicked() {
        @Override
        public void onPositionClicked(int position) {
            Log.d("Selected LGA", "" + lgaNames.get(position));
            mLGASearchableSpinnerLinkTextView.setText(lgaNames.get(position));
            mLocalGovernmentArea = lgaObjects.get(position);
            if (mOnLGASelected != null) {
                mOnLGASelected.onLGASelected(position, mLocalGovernmentArea);
            }
        }
    };

    public interface OnStateSelected {
        void onStateSelected(int position, State state);
    }

    public interface OnLGASelected {
        void onLGASelected(int position, LocalGovernmentArea localGovernmentArea);
    }


}
