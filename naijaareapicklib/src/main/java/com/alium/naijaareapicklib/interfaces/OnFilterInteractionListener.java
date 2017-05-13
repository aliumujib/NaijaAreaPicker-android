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

package com.alium.naijaareapicklib.interfaces;



/**
 * Created by abdulmujibaliu on 5/3/17.
 */


import com.alium.naijaareapicklib.model.LocalGovernmentArea;
import com.alium.naijaareapicklib.model.State;

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p/>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnFilterInteractionListener {
    // TODO: Update argument type and name
    void onFiltersInteraction(State state, LocalGovernmentArea localGovernmentArea); /*TODO NOT SURE IF I NEED YOUS, int stateIndex, int lgaIndex)*/;
}
