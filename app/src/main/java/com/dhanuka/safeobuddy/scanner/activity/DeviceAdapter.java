package com.dhanuka.safeobuddy.scanner.activity;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.recyclerview.widget.RecyclerView;

import com.dhanuka.safeobuddy.R;

import java.util.List;

public class DeviceAdapter extends
        RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    private final List<BluetoothDevice> devices;

    private final OnDeviceClickListener onConnectClick;

    private final OnDeviceClickListener onOpenClick;

    // =========================
    // INTERFACE
    // =========================

    public interface OnDeviceClickListener {

        void onClick(
                BluetoothDevice device
        );
    }

    // =========================
    // CONSTRUCTOR
    // =========================

    public DeviceAdapter(
            List<BluetoothDevice> devices,
            OnDeviceClickListener onConnectClick,
            OnDeviceClickListener onOpenClick
    ) {

        this.devices = devices;

        this.onConnectClick = onConnectClick;

        this.onOpenClick = onOpenClick;
    }

    // =========================
    // VIEW HOLDER
    // =========================

    public static class DeviceViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvName;

        TextView tvAddress;

        Button btnConnect;

        Button btnOpen;

        public DeviceViewHolder(
                @NonNull View view
        ) {

            super(view);

            tvName =
                    view.findViewById(R.id.tvName);

            tvAddress =
                    view.findViewById(R.id.tvAddress);

            btnConnect =
                    view.findViewById(R.id.btnConnect);

            btnOpen =
                    view.findViewById(R.id.btnOpen);
        }
    }

    // =========================
    // CREATE VIEW HOLDER
    // =========================

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view =
                LayoutInflater.from(
                        parent.getContext()
                ).inflate(
                        R.layout.item_device,
                        parent,
                        false
                );

        return new DeviceViewHolder(view);
    }

    // =========================
    // BIND VIEW HOLDER
    // =========================

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    @Override
    public void onBindViewHolder(
            @NonNull DeviceViewHolder holder,
            int position
    ) {

        BluetoothDevice device =
                devices.get(position);

        holder.tvName.setText(
                device.getName() != null
                        ? device.getName()
                        : "Unknown Device"
        );

        holder.tvAddress.setText(
                device.getAddress()
        );

        holder.btnConnect.setOnClickListener(v -> {

            onConnectClick.onClick(device);
        });

        holder.btnOpen.setOnClickListener(v -> {

            onOpenClick.onClick(device);
        });
    }

    // =========================
    // ITEM COUNT
    // =========================

    @Override
    public int getItemCount() {

        return devices.size();
    }
}