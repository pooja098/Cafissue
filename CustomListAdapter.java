package com.example.pooja.cafissues;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pooja on 10/9/16.
 */
public class CustomListAdapter extends BaseAdapter {

    /***********
     * Declare Used Variables
     *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    Issue tempValues = null;
    int i = 0;

    /*************
     * CustomAdapter Constructor
     *****************/
    public CustomListAdapter(Activity a, ArrayList d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /********
     * What is the size of Passed Arraylist Size
     ************/
    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {

        public TextView Sub;
        public TextView issue;

    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
                vi = inflater.inflate(R.layout.list_item,null);

            holder = new ViewHolder();
            holder.Sub = (TextView) vi.findViewById(R.id.subj);
            holder.issue = (TextView) vi.findViewById(R.id.num);
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0) {

        } else {
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (Issue) data.get(position);

            /************  Set Model values in Holder elements ***********/

            holder.Sub.setText(tempValues.getSubject());
            holder.issue.setText(" " + tempValues.getIssueno());
        }

        return vi;
    }
}

