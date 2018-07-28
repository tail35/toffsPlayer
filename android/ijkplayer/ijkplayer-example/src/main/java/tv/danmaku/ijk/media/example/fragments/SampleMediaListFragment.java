/*
 * Copyright (C) 2015 Bilibili
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tv.danmaku.ijk.media.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.activities.VideoActivity;

import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;
import static android.os.ParcelFileDescriptor.MODE_WORLD_WRITEABLE;

public class SampleMediaListFragment extends Fragment {
    private ListView mFileListView;
    //dhlu
    private EditText EditText_url;
    private Button Button_play;

    //end dhlu
    private SampleMediaAdapter mAdapter;

    public static SampleMediaListFragment newInstance() {
        SampleMediaListFragment f = new SampleMediaListFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_file_list, container, false);
        //dhlu
        //mFileListView = (ListView) viewGroup.findViewById(R.id.file_list_view);
        EditText_url = (EditText) viewGroup.findViewById(R.id.editText_url);
        //set default text
        String url = GetKeyValue(getActivity(),"url");
        if( 0!=url.length() ) {
            EditText_url.setText(url);
        }
        Button_play = (Button) viewGroup.findViewById(R.id.button_play);
        //end dhlu
        return viewGroup;
    }

    public  static String  GetKeyValue(Activity activity,String key){
        SharedPreferences read = activity.getSharedPreferences("lock", activity.MODE_PRIVATE);
        if(null==read)
            return "";
        String value = read.getString("url", "");
        return  value;
    }
    public  static void  WriteKeyValue(Activity activity,String key,String value){
        SharedPreferences.Editor editor = activity.getSharedPreferences("lock", activity.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Activity activity = getActivity();
        //dhlu

        Button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =  EditText_url.getText().toString();
                SampleMediaListFragment.WriteKeyValue(activity,"url",url);
                Toast tot = Toast.makeText(
                        v.getContext(),
                        url,
                        Toast.LENGTH_LONG);
                tot.show();

                VideoActivity.intentTo(activity, url, "toffsplayer");
            }
        });
        //create player button
//        mAdapter = new SampleMediaAdapter(activity);
        //mFileListView.setAdapter(mAdapter);
//        mFileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
//                SampleMediaItem item = mAdapter.getItem(position);
//                String name = item.mName;
//                String url = item.mUrl;
//                VideoActivity.intentTo(activity, url, name);
//            }
//        });

        //mAdapter.addItem("rtmp://103.21.118.166/toffs/live1", "rtmp");
        //mAdapter.addItem("http://103.21.118.166:8080/toffs/live1/index.m3u8", "m3u8");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8", "bipbop basic 640x480 @ 650 kbps");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear3/prog_index.m3u8", "bipbop basic 640x480 @ 1 Mbps");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear4/prog_index.m3u8", "bipbop basic 960x720 @ 2 Mbps");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear0/prog_index.m3u8", "bipbop basic 22.050Hz stereo @ 40 kbps");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8", "bipbop advanced master playlist");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear1/prog_index.m3u8", "bipbop advanced 416x234 @ 265 kbps");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear2/prog_index.m3u8", "bipbop advanced 640x360 @ 580 kbps");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear3/prog_index.m3u8", "bipbop advanced 960x540 @ 910 kbps");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear4/prog_index.m3u8", "bipbop advanced 1289x720 @ 1 Mbps");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear5/prog_index.m3u8", "bipbop advanced 1920x1080 @ 2 Mbps");
//        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear0/prog_index.m3u8", "bipbop advanced 22.050Hz stereo @ 40 kbps");
        //end dhlu
    }

    final class SampleMediaItem {
        String mUrl;
        String mName;

        public SampleMediaItem(String url, String name) {
            mUrl = url;
            mName = name;
        }
    }

    final class SampleMediaAdapter extends ArrayAdapter<SampleMediaItem> {
        public SampleMediaAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_2);
        }

        public void addItem(String url, String name) {
            add(new SampleMediaItem(url, name));
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
            }

            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (viewHolder == null) {
                viewHolder = new ViewHolder();
                viewHolder.mNameTextView = (TextView) view.findViewById(android.R.id.text1);
                viewHolder.mUrlTextView = (TextView) view.findViewById(android.R.id.text2);
            }

            SampleMediaItem item = getItem(position);
            viewHolder.mNameTextView.setText(item.mName);
            viewHolder.mUrlTextView.setText(item.mUrl);

            return view;
        }

        final class ViewHolder {
            public TextView mNameTextView;
            public TextView mUrlTextView;
        }
    }
}
