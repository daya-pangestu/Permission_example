package com.daya.permission_example;

import android.Manifest;
import android.content.Context;
import android.hardware.Camera;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.PermissionHolder> {
    List<Permissionmodel> listPermissionAndImage = new ArrayList<>();


    @NonNull
    @Override
    public PermissionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new PermissionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionHolder holder, int position) {

        Permissionmodel current = listPermissionAndImage.get(position);
        holder.bind(current);//pass the current list onto viewholder

    }

    @Override
    public int getItemCount() {
        return (listPermissionAndImage != null) ? listPermissionAndImage.size() : 0;
    }

    public void setListPermission(List<Permissionmodel> listPermission) {
        this.listPermissionAndImage = listPermission;
    }


    class PermissionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_permission)
        ImageView imgPermission;
        @BindView(R.id.btn_permission)
        AppCompatButton btnPermission;

        public PermissionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            btnPermission.setOnClickListener(this);
        }

        String textPermission;

        void bind(Permissionmodel ps) {

            int imgRes = ps.getImageResource();
            textPermission = ps.getTextButton();

            imgPermission.setImageDrawable(itemView.getContext().getDrawable(imgRes));
            btnPermission.setText(textPermission);
        }

        @Override
        public void onClick(View v) {
            if (textPermission != null) {
                switch (textPermission) {
                    case Manifest.permission.CAMERA:
                        setPermission(v.getContext(), textPermission,MainActivity.permisionCamera());
                        break;

                    case Manifest.permission.RECORD_AUDIO:
                        setPermission(v.getContext(), textPermission,MainActivity.permisionRecord());
                        break;

                    case Manifest.permission.READ_EXTERNAL_STORAGE:
                        setPermission(v.getContext(), textPermission, MainActivity.permisionExplorer(v.getContext()));
                        break;
                }
            }
        }
    }

    //void for setpermission based on TedPermission Library
    void setPermission(Context context,String permission,PermissionListener permissionListener) {
        TedPermission.with(context)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("rejected")
                .setPermissions(permission)
                .check();
    }
}
