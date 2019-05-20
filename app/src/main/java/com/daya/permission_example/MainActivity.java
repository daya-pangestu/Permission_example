package com.daya.permission_example;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_permission)
    RecyclerView recyclerPermission;

    //list of permission we used
    public static List<String> listPermission = Arrays.asList(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE);



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        List<Permissionmodel> listPermissionAndImage = new ArrayList<>();//put permission onto arraylist with Permissionmodel.class, which is contain permission string and drawable path for image

        listPermissionAndImage.add(new Permissionmodel(listPermission.get(0), R.drawable.ic_camera_alt_black_24dp));
        listPermissionAndImage.add(new Permissionmodel(listPermission.get(1), R.drawable.ic_mic_black_24dp));
        listPermissionAndImage.add(new Permissionmodel(listPermission.get(2), R.drawable.ic_create_new_folder_black_24dp));


        //recyclerview
        PermissionAdapter adapter = new PermissionAdapter();
        adapter.setListPermission(listPermissionAndImage);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
        recyclerPermission.setHasFixedSize(true);
        recyclerPermission.setLayoutManager(manager);
        recyclerPermission.setAdapter(adapter);

    }

    //static method that used in permissionAdapter.java, each one of these represent the corresponding permission
    public static PermissionListener permisionCamera() {
        return new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Camera c = null;
                try {
                    c = Camera.open(); // attempt to get a Camera instance
                } catch (Exception e) {
                    // Camera is not available (in use or does not exist)
                }

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };
    }

    public static PermissionListener permisionRecord () {
        return new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };
    }

    public static PermissionListener permisionExplorer(Context context) {
        return new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                new ChooserDialog(context)
                        .withFilter(true, false)
                        .withChosenListener(new ChooserDialog.Result() {
                            @Override
                            public void onChoosePath(String s, File file) {
                                Toast.makeText(context, "FOLDER :" + s, Toast.LENGTH_SHORT).show();
                            }
                        })
                .build()
                .show();
                Log.i("explorer", "onPermissionGranted: granted");
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };
    }

}
