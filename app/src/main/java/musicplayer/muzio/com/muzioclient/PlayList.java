package musicplayer.muzio.com.muzioclient;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import musicplayer.muzio.com.muzioclient.Adapter.SongsManager;
import musicplayer.muzio.com.muzioclient.R;

/**
 * Created by Sony on 18-04-2017.
 */

public class PlayList  extends Fragment {
   // public static final String Broadcast_PLAY_NEW_AUDIO = "musicplayer.muzio.com.muzioserver.PlayNewAudio";

//    private MediaPlayerService player;
//    boolean serviceBound = false;
//    ArrayList<MediaStore.Audio> audioList;
//
//    ImageView collapsingImageView;
//
//    int imageIndex = 0;
   public ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Toolbar toolbar = (Toolbar) inflater.inflate(R.id.toolbar, container, false);
//        setSupportActionBar(toolbar);
//        collapsingImageView = (ImageView) inflater.inflate(R.id.collapsingImageView);
//
//        loadCollapsingImage(imageIndex);
//        loadAudio();
//        initRecyclerView();
        View view = inflater.inflate(R.layout.playlist_fragment, container, false);
        ArrayList<HashMap<String, String>> songsListData = new ArrayList<HashMap<String, String>>();

        SongsManager plm = new SongsManager();
        // get all songs from sdcard
        this.songsList = plm.getPlayList();

        // looping through playlist
        for (int i = 0; i < songsList.size(); i++) {
            // creating new HashMap
            HashMap<String, String> song = songsList.get(i);

            // adding HashList to ArrayList
            songsListData.add(song);
        }

        ListAdapter adapter = new SimpleAdapter(getActivity(), songsListData,
                R.layout.musiclist_item, new String[] { "songTitle" }, new int[] {
                R.id.songTitle });



        // selecting single ListView item
        ListView lv =(ListView) view.findViewById(R.id.musiclist);
        lv.setAdapter(adapter);

        return view;

    }
}