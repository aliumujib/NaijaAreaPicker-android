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

package com.alium.naijaareapicklib.model;

import org.json.JSONArray;

/**
 * Created by abdulmujibaliu on 5/2/17.
 */

public class State {

    String name;
    int id;
    JSONArray mLGAArray;

    public State(String name, int id, JSONArray lGAArray) {
        this.name = name;
        this.id = id;
        this.mLGAArray = lGAArray;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public JSONArray getmLGAArray() {
        return mLGAArray;
    }
}
