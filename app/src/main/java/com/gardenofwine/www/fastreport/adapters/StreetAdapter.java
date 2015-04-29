/*
 * Copyright (c) 2015 PayPal, Inc.
 *
 * All rights reserved.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */

package com.gardenofwine.www.fastreport.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.gardenofwine.www.fastreport.db.dao.StreetDao;
import com.gardenofwine.www.fastreport.db.models.Street;

import java.util.ArrayList;
import java.util.List;

/**
 * An adapter that auto-completes streets.
 *
 * @author ifeins
 */
public class StreetAdapter extends BaseAdapter implements Filterable {

    private final StreetDao mStreetDao;
    private final Context mContext;
    private Filter mFilter;
    private List<Street> mStreets = new ArrayList<>();

    public StreetAdapter(Context context, StreetDao streetDao) {
        mContext = context;
        mStreetDao = streetDao;
        mFilter = new StreetsFilter();
    }

    @Override
    public int getCount() {
        return mStreets.size();
    }

    @Override
    public Object getItem(int position) {
        return mStreets.get(position);
    }

    public Street getStreet(int position) {
        return (Street) getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView != null) {
            textView = (TextView) convertView;
        } else {
            textView = (TextView) LayoutInflater.from(mContext).inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        }

        textView.setText(getStreet(position).getStreetName());
        return textView;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class StreetsFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Street> streets = mStreetDao.filterStreets(constraint.toString());
            FilterResults results = new FilterResults();
            results.values = streets;
            results.count = streets.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mStreets.clear();
            if (results.values == null) {
                return;
            }

            mStreets.addAll((List<Street>) results.values);

            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
