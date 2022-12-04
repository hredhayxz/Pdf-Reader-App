package com.example.ipdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;
    private List<File> pdfList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RunTimePermission();
    }
    void RunTimePermission()
    {
        Dexter.withContext(MainActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener(){
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                DisplayAllPdfFile();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }



    public void DisplayAllPdfFile()
    {
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        pdfList=new ArrayList<>();
        pdfList.addAll(findPdf(Environment.getExternalStorageDirectory()));
        mainAdapter=new MainAdapter(this, pdfList);
        recyclerView.setAdapter(mainAdapter);
    }




    public ArrayList<File> findPdf (File file)
    {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File singleFile: files)
        {
            if(singleFile.isDirectory() && !singleFile.isHidden())
            {
                arrayList.addAll(findPdf(singleFile));
            }
            else
            {
                if(singleFile.getName().endsWith(".pdf"))
                {
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }




}