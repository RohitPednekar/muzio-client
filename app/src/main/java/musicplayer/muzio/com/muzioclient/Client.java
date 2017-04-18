package musicplayer.muzio.com.muzioclient;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by Sony on 18-04-2017.
 */

public class Client extends Fragment {
    @Nullable

    EditText editTextAddress;
    Button buttonConnect;
    TextView textPort;

    static final int SocketServerPORT = 8080;
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
        View view = inflater.inflate(R.layout.client_fragment, container, false);
        editTextAddress = (EditText)view. findViewById(R.id.address);
        textPort = (TextView)view. findViewById(R.id.port);
        textPort.setText("port: " + SocketServerPORT);
        buttonConnect = (Button)view. findViewById(R.id.connect);

        buttonConnect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Client.ClientRxThread clientRxThread =
                        new Client.ClientRxThread(
                                editTextAddress.getText().toString(),
                                SocketServerPORT);

                clientRxThread.start();
            }});

        return view;
    }

    private class ClientRxThread extends Thread {
        String dstAddress;
        int dstPort;

        ClientRxThread(String address, int port) {
            dstAddress = address;
            dstPort = port;
        }

        @Override
        public void run() {
            Socket socket = null;

            try {
                socket = new Socket(dstAddress, dstPort);

                File dir = new File(
                        Environment.getExternalStorageDirectory() + "/muzio");
                dir.mkdir();
                File file = new File(dir, "test.mp3");

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                byte[] bytes;
                FileOutputStream fos = null;
                try {
                    bytes = (byte[]) ois.readObject();
                    fos = new FileOutputStream(file);
                    fos.write(bytes);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        fos.close();
                    }

                }

                socket.close();

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                "Finished",
                                Toast.LENGTH_LONG).show();
                    }
                });

            } catch (IOException e) {

                e.printStackTrace();

                final String eMsg = "Something wrong: " + e.getMessage();
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),
                                eMsg,
                                Toast.LENGTH_LONG).show();
                    }
                });

            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}


