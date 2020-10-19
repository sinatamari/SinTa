package com.example.sina.sinta;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class file_safety_class extends AppCompatActivity
{
    private String pas;
    public Integer[] imgs;
    public boolean[] desc;
    public boolean flag_of_paly_or_pause = true , flag_of_video_paly_or_pause = true;
    public String[] extensions = {
            ".mp3",".ogg",".m4a",".3gp",".flac",
            ".gif",".mkv",".mp4",".avi",
            ".jpeg",".JPEG",".jpg",".png",".PNG",".IMG",".img",
            ".pdf",".PDF",
            ".txt",".odx",".zip",".rar",".pptx",".docx",".xlsx",".apk",".exe",".deb",".run",
            ".ico",".psd",".gz",".7z"
    };
    public String[] music_extensions = {".mp3",".ogg",".m4a",".3gp",".flac"};
    public String[] movie_extensions = {".gif",".mkv",".mp4",".avi"};
    public String[] images_extensions = {".jpeg",".JPEG",".jpg",".png",".PNG",".IMG",".img"};
    public String[] pdf_extensions = {".pdf",".PDF"};
    public String[] other_extensions = {".py",".php",".rb",".csharp",".txt",".odx",".zip",".rar",".pptx",".docx",".xlsx",".apk",".exe",".deb",".run", ".ico",".psd",".gz",".7z"};

    public int j = 0 , i = 0 , index = 0 , last_index = 0;
    public String[] Paths;
    public String[] Names;
    private boolean flag = false , close_flag = false;
    private String now_menu_chooshed = "";
    private boolean music_clickable = false,movie_clickable = false,image_clickable = false,pdf_clickable = false,other_clickable = false;
    public ProgressDialog progressDialog;
    public MediaPlayer mp;
    public String selected = "";
    public File external_sdcard2;

    TextView music_sub_icon;
    TextView movie_sub_icon;
    TextView photo_sub_icon;
    TextView pdf_sub_icon;
    TextView other_sub_icon;
    TextView result_of_files_Encrypted;
    TextView result_of_files_Decrypted;
    ListView ll;
    LinearLayout Music_Banner_DialogBox_Alpha;
    LinearLayout Music_Banner_DialogBox_Main_Part_Parent;
    TextView song_name_textview;
    TextView play_music;
    TextView plm;
    LinearLayout Movie_Banner_DialogBox_Alpha;
    LinearLayout Movie_Banner_DialogBox_Main_Part_Parent;
    VideoView videoView;
    TextView show_unable_to_play_this_video;
    ImageView imageView;
    LinearLayout Image_Banner_DialogBox_Alpha;
    LinearLayout Image_Banner_DialogBox_Main_Part_Parent;
    LinearLayout PDF_Banner_DialogBox_Alpha;
    LinearLayout PDF_Banner_DialogBox_Main_Part_Parent;
    TextView pdfView;
    LinearLayout Other_Banner_DialogBox_Alpha;
    LinearLayout Other_Banner_DialogBox_Main_Part_Parent;
    TextView otherView;
    TextView update_icon;
    LinearLayout Search_Banner_DialogBox_Alpha;
    LinearLayout Search_Banner_DialogBox_Main_Part_Parent;
    EditText Search_InputText;
    LinearLayout Enable_Music_Testing;
    RelativeLayout encrypt_music_button_in_banner;
    RelativeLayout decrypt_music_button_in_banner;
    TextView encrypt_music_text;
    TextView decrypt_music_text;
    TextView txt_encrypt_movie_in_banner;
    TextView txt_decrypt_movie_in_banner;
    RelativeLayout encrypt_movie_button_in_banner;
    RelativeLayout decrypt_movie_button_in_banner;
    RelativeLayout play_movie_in_banner;
    RelativeLayout btn_paly_music_in_banner;
    RelativeLayout btn_stop_music_in_banner;
    RelativeLayout encrypt_image_button_in_banner;
    RelativeLayout decrypt_image_button_in_banner;
    TextView txt_encrypt_image_in_banner;
    TextView txt_decrypt_image_in_banner;
    RelativeLayout encrypt_pdf_button_in_banner;
    RelativeLayout decrypt_pdf_button_in_banner;
    TextView txt_encrypt_pdf_in_banner;
    TextView txt_decrypt_pdf_in_banner;
    RelativeLayout encrypt_other_button_in_banner;
    RelativeLayout decrypt_other_button_in_banner;
    TextView txt_encrypt_other_in_banner;
    TextView txt_decrypt_other_in_banner;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_safety_layout);

        Bundle extra = getIntent().getExtras();
        if(extra != null)
        {
            pas = extra.getString("ps");
        }
        else
        {
            finish();
        }

        ll = (ListView)findViewById(R.id.List_Of_All_Files);
        music_sub_icon = (TextView)findViewById(R.id.music_sub_icon);
        movie_sub_icon = (TextView)findViewById(R.id.movie_sub_icon);
        photo_sub_icon = (TextView)findViewById(R.id.photos_sub_icon);
        pdf_sub_icon = (TextView)findViewById(R.id.pdf_sub_icon);
        other_sub_icon = (TextView)findViewById(R.id.others_sub_icon);
        result_of_files_Encrypted = (TextView)findViewById(R.id.result_of_files_Encrypted);
        result_of_files_Decrypted = (TextView)findViewById(R.id.result_of_files_Decrypted);
        Movie_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.Movie_Banner_DialogBox_Alpha);
        Movie_Banner_DialogBox_Main_Part_Parent = (LinearLayout)findViewById(R.id.Movie_Banner_DialogBox_Main_Part_Parent) ;
        imageView = (ImageView)findViewById(R.id.imageView);
        Image_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.Image_Banner_DialogBox_Alpha);
        Image_Banner_DialogBox_Main_Part_Parent = (LinearLayout)findViewById(R.id.Image_Banner_DialogBox_Main_Part_Parent);
        PDF_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.PDF_Banner_DialogBox_Alpha);
        PDF_Banner_DialogBox_Main_Part_Parent = (LinearLayout)findViewById(R.id.PDF_Banner_DialogBox_Main_Part_Parent);
        update_icon = (TextView)findViewById(R.id.update_icon);
        Search_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.Search_Banner_DialogBox_Alpha);
        Search_Banner_DialogBox_Main_Part_Parent = (LinearLayout)findViewById(R.id.Search_Banner_DialogBox_Main_Part_Parent);
        Search_InputText = (EditText)findViewById(R.id.Search_Input_Text);
        Enable_Music_Testing = (LinearLayout)findViewById(R.id.Enable_Music_Testing);
        encrypt_music_button_in_banner = (RelativeLayout)findViewById(R.id.encrypt_music_button_in_dialogbox);
        decrypt_music_button_in_banner = (RelativeLayout)findViewById(R.id.decrypt_music_button_in_dialogbox);
        encrypt_music_text = (TextView)findViewById(R.id.encrypt_music_text);
        decrypt_music_text = (TextView)findViewById(R.id.decrypt_music_text);
        txt_encrypt_movie_in_banner = (TextView)findViewById(R.id.txt_encrypt_video_in_banner);
        txt_decrypt_movie_in_banner = (TextView)findViewById(R.id.txt_decrypt_video_in_banner);
        encrypt_movie_button_in_banner = (RelativeLayout)findViewById(R.id.encrypt_movie_button_in_dialogbox);
        decrypt_movie_button_in_banner = (RelativeLayout)findViewById(R.id.decrypt_movie_button_in_dialogbox);
        play_movie_in_banner = (RelativeLayout)findViewById(R.id.paly_movie_in_banner);
        btn_paly_music_in_banner = (RelativeLayout)findViewById(R.id.btn_paly_music_in_banner);
        btn_stop_music_in_banner = (RelativeLayout)findViewById(R.id.btn_stop_music_in_banner);
        encrypt_image_button_in_banner = (RelativeLayout)findViewById(R.id.encrypt_image_button_in_dialogbox);
        decrypt_image_button_in_banner = (RelativeLayout)findViewById(R.id.decrypt_image_button_in_dialogbox);
        txt_encrypt_image_in_banner = (TextView)findViewById(R.id.txt_encrypt_image_in_banner);
        txt_decrypt_image_in_banner = (TextView)findViewById(R.id.txt_decrypt_image_in_banner);
        encrypt_pdf_button_in_banner = (RelativeLayout)findViewById(R.id.encrypt_pdf_button_in_dialogbox);
        decrypt_pdf_button_in_banner = (RelativeLayout)findViewById(R.id.decrypt_pdf_button_in_dialogbox);
        txt_encrypt_pdf_in_banner = (TextView)findViewById(R.id.txt_encrypt_pdf_in_banner);
        txt_decrypt_pdf_in_banner = (TextView)findViewById(R.id.txt_decrypt_pdf_in_banner);
        encrypt_other_button_in_banner = (RelativeLayout)findViewById(R.id.encrypt_other_button_in_dialogbox);
        decrypt_other_button_in_banner = (RelativeLayout)findViewById(R.id.decrypt_other_button_in_dialogbox);
        txt_encrypt_other_in_banner = (TextView)findViewById(R.id.txt_encrypt_other_in_banner);
        txt_decrypt_other_in_banner = (TextView)findViewById(R.id.txt_decrypt_other_in_banner);


        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please wait ...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        mp = new MediaPlayer();
        videoView = (VideoView)findViewById(R.id.videoView);
        pdfView = (TextView)findViewById(R.id.pdfView);
        otherView = (TextView)findViewById(R.id.otherView);
        Other_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.Other_Banner_DialogBox_Alpha);
        Other_Banner_DialogBox_Main_Part_Parent = (LinearLayout)findViewById(R.id.Other_Banner_DialogBox_Main_Part_Parent);


        MediaController md = new MediaController(getBaseContext());
        md.setAnchorView(videoView);
        videoView.setMediaController(md);
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        Music_Banner_DialogBox_Main_Part_Parent = (LinearLayout)findViewById(R.id.Music_Banner_DialogBox_Main_Part_Parent);
        Music_Banner_DialogBox_Alpha = (LinearLayout)findViewById(R.id.Music_Banner_DialogBox_Alpha);
        song_name_textview = (TextView)findViewById(R.id.song_name_textview);
        play_music = (TextView)findViewById(R.id.play_music);
        plm = (TextView) findViewById(R.id.Play_Movie);
        show_unable_to_play_this_video = (TextView)findViewById(R.id.show_unable_to_play_this_video);

        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(now_menu_chooshed.equals("Music"))
                {
                    selected = parent.getItemAtPosition(position).toString();
                    index = find_file_position(selected);
                    if(index != -1)
                    {
                        Music_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
                        Music_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
                        song_name_textview.setText(selected);
                        if(Check_Encrypted_Or_Not(Paths[index]) == 0)
                        {
                            Enable_Music_Testing.setClickable(true);
                            btn_paly_music_in_banner.setClickable(true);
                            btn_paly_music_in_banner.setEnabled(true);
                            btn_stop_music_in_banner.setEnabled(true);
                            btn_stop_music_in_banner.setClickable(true);
                            decrypt_music_text.setTextColor(getResources().getColor(R.color.gray));
                            encrypt_music_button_in_banner.setClickable(true);
                            encrypt_music_button_in_banner.setEnabled(true);
                            encrypt_music_text.setTextColor(getResources().getColor(R.color.black));
                            decrypt_music_button_in_banner.setClickable(false);
                            decrypt_music_button_in_banner.setEnabled(false);
                            try {

                                mp.reset();
                                mp.setDataSource(Paths[index].toString());
                                mp.prepare();
                                selected = Paths[index].toString();
                            } catch (Exception e) {

                            }
                        }
                        else
                        {
                            Enable_Music_Testing.setClickable(false);
                            btn_paly_music_in_banner.setClickable(false);
                            btn_paly_music_in_banner.setEnabled(false);
                            btn_stop_music_in_banner.setEnabled(false);
                            btn_stop_music_in_banner.setClickable(false);
                            encrypt_music_text.setTextColor(getResources().getColor(R.color.gray));
                            encrypt_music_button_in_banner.setClickable(false);
                            encrypt_music_button_in_banner.setEnabled(false);
                            decrypt_music_text.setTextColor(getResources().getColor(R.color.black));
                            decrypt_music_button_in_banner.setClickable(true);
                            decrypt_music_button_in_banner.setEnabled(true);
                        }
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"Error to find song !!",Toast.LENGTH_LONG).show();
                    }
                }
                else if(now_menu_chooshed.equals("Movie"))
                {
                    selected = parent.getItemAtPosition(position).toString();
                    index = find_file_position(selected);
                    if(index != -1)
                    {
                        Movie_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
                        Movie_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
                        if(Check_Encrypted_Or_Not(Paths[index]) == 0)
                        {
                            txt_decrypt_movie_in_banner.setTextColor(getResources().getColor(R.color.gray));
                            txt_encrypt_movie_in_banner.setTextColor(getResources().getColor(R.color.black));
                            decrypt_movie_button_in_banner.setEnabled(false);
                            decrypt_movie_button_in_banner.setClickable(false);
                            encrypt_movie_button_in_banner.setClickable(true);
                            encrypt_movie_button_in_banner.setEnabled(true);
                            play_movie_in_banner.setEnabled(true);
                            play_movie_in_banner.setClickable(true);

                            try
                            {
                                if((selected.endsWith(".mp4") || selected.endsWith(".MP4")) && last_index != index)
                                {
                                    try
                                    {
                                        videoView.setVisibility(VideoView.VISIBLE);
                                        show_unable_to_play_this_video.setVisibility(TextView.INVISIBLE);
                                        last_index = index;
                                        videoView.setVideoPath(Paths[index]);
                                        //if(videoView.canSeekForward() || videoView.canSeekBackward())
                                        //    videoView.seekTo(10);
                                        selected = Paths[index].toString();
                                    }
                                    catch (Exception e)
                                    {

                                    }
                                }
                                else if(last_index == index)
                                {
                                    selected = Paths[index];
                                    //Uri uri = Uri.parse(selected);
                                    videoView.setVideoPath(selected);
                                /*show_another_types_of_videos = new Intent(Intent.ACTION_VIEW,uri);
                                show_another_types_of_videos.setDataAndType(uri,"video/mp4");*/
                                }
                                else
                                {
                                    videoView.setVisibility(VideoView.INVISIBLE);
                                    show_unable_to_play_this_video.setText("Unable to play this video.\nOnly support MP4 formats to play.\nPath : "+Paths[index]);
                                    show_unable_to_play_this_video.setVisibility(TextView.VISIBLE);
                                }
                            }
                            catch (Exception e)
                            {

                            }
                        }
                        else
                        {
                            txt_decrypt_movie_in_banner.setTextColor(getResources().getColor(R.color.black));
                            txt_encrypt_movie_in_banner.setTextColor(getResources().getColor(R.color.gray));
                            decrypt_movie_button_in_banner.setEnabled(true);
                            decrypt_movie_button_in_banner.setClickable(true);
                            encrypt_movie_button_in_banner.setClickable(false);
                            encrypt_movie_button_in_banner.setEnabled(false);
                            play_movie_in_banner.setEnabled(false);
                            play_movie_in_banner.setClickable(false);
                        }
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"Error to find Video !!",Toast.LENGTH_LONG).show();
                    }
                }
                else if(now_menu_chooshed.equals("Image"))
                {
                    selected = parent.getItemAtPosition(position).toString();
                    index = find_file_position(selected);
                    Image_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
                    Image_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
                    imageView.setImageURI(Uri.parse(Paths[index]));
                    if(Check_Encrypted_Or_Not(Paths[index]) == 0)
                    {
                        txt_encrypt_image_in_banner.setTextColor(getResources().getColor(R.color.black));
                        txt_decrypt_image_in_banner.setTextColor(getResources().getColor(R.color.gray));
                        encrypt_image_button_in_banner.setClickable(true);
                        encrypt_image_button_in_banner.setEnabled(true);
                        decrypt_image_button_in_banner.setClickable(false);
                        decrypt_image_button_in_banner.setEnabled(false);
                    }
                    else
                    {
                        txt_encrypt_image_in_banner.setTextColor(getResources().getColor(R.color.gray));
                        txt_decrypt_image_in_banner.setTextColor(getResources().getColor(R.color.black));
                        encrypt_image_button_in_banner.setClickable(false);
                        encrypt_image_button_in_banner.setEnabled(false);
                        decrypt_image_button_in_banner.setClickable(true);
                        decrypt_image_button_in_banner.setEnabled(true);
                    }

                }
                else if(now_menu_chooshed.equals("Pdf"))
                {
                    selected = parent.getItemAtPosition(position).toString();
                    index = find_file_position(selected);
                    pdfView.setText("Name : "+selected+"\nPath : "+Paths[index]);
                    selected = Paths[index];
                    if(Check_Encrypted_Or_Not(selected) == 0)
                    {
                        txt_decrypt_pdf_in_banner.setTextColor(getResources().getColor(R.color.gray));
                        txt_encrypt_pdf_in_banner.setTextColor(getResources().getColor(R.color.black));
                        encrypt_pdf_button_in_banner.setEnabled(true);
                        encrypt_pdf_button_in_banner.setClickable(true);
                        decrypt_pdf_button_in_banner.setClickable(false);
                        decrypt_pdf_button_in_banner.setEnabled(false);
                    }
                    else {
                        txt_decrypt_pdf_in_banner.setTextColor(getResources().getColor(R.color.black));
                        txt_encrypt_pdf_in_banner.setTextColor(getResources().getColor(R.color.gray));
                        encrypt_pdf_button_in_banner.setEnabled(false);
                        encrypt_pdf_button_in_banner.setClickable(false);
                        decrypt_pdf_button_in_banner.setClickable(true);
                        decrypt_pdf_button_in_banner.setEnabled(true);
                    }
                    PDF_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
                    PDF_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
                }
                else if(now_menu_chooshed.equals("Other"))
                {
                    selected = parent.getItemAtPosition(position).toString();
                    index = find_file_position(selected);
                    otherView.setText("Name : "+selected+"\nPath : "+Paths[index]);
                    selected = Paths[index];
                    if(Check_Encrypted_Or_Not(selected) == 0)
                    {
                        txt_encrypt_other_in_banner.setTextColor(getResources().getColor(R.color.black));
                        txt_decrypt_other_in_banner.setTextColor(getResources().getColor(R.color.gray));
                        encrypt_other_button_in_banner.setEnabled(true);
                        encrypt_other_button_in_banner.setClickable(true);
                        decrypt_other_button_in_banner.setClickable(false);
                        decrypt_other_button_in_banner.setEnabled(false);
                    }
                    else
                    {
                        txt_encrypt_other_in_banner.setTextColor(getResources().getColor(R.color.gray));
                        txt_decrypt_other_in_banner.setTextColor(getResources().getColor(R.color.black));
                        encrypt_other_button_in_banner.setEnabled(false);
                        encrypt_other_button_in_banner.setClickable(false);
                        decrypt_other_button_in_banner.setClickable(true);
                        decrypt_other_button_in_banner.setEnabled(true);
                    }
                    Other_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
                    Other_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
                }
            }
        });



        upd obj_update = new upd();
        obj_update.vi = findViewById(android.R.id.content);
        obj_update.show_progress_snack = true;
        obj_update.execute();
    }

    public void Back_To_Home_Page(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        this.finish();
    }

    public void onMusic_Files_Click(View v)
    {
        if(music_clickable) {
            if (!now_menu_chooshed.equals("Music")) {
                music_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                other_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));

                String[][] ff = Get_Files_For_List(music_extensions);
                int en_number = 0 , un_number = 0;
                int h = ff[0].length;
                imgs = new Integer[h];
                desc = new boolean[h];
                for (int m = 0; m < h; m++) {
                    if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                        imgs[m] = R.drawable.lock_open;
                        desc[m] = false;
                        un_number++;
                    } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                        imgs[m] = R.drawable.locked;
                        desc[m] = true;
                        en_number++;
                    }
                }
                result_of_files_Encrypted.setText(en_number + " Files");
                result_of_files_Decrypted.setText(un_number + " Files");
                CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                ll.setAdapter(customListAdapter);
                now_menu_chooshed = "Music";
            }
        }
    }

    public void onMovie_Files_Click(View v)
    {
        if(movie_clickable) {
            if (!now_menu_chooshed.equals("Movie")) {
                movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                music_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                other_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));

                String[][] ff = Get_Files_For_List(movie_extensions);
                int en_number = 0 , un_number = 0;
                int h = ff[0].length;
                imgs = new Integer[h];
                desc = new boolean[h];
                for (int m = 0; m < h; m++) {
                    if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                        imgs[m] = R.drawable.lock_open;
                        desc[m] = false;
                        un_number++;
                    } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                        imgs[m] = R.drawable.locked;
                        desc[m] = true;
                        en_number++;
                    }
                }
                result_of_files_Encrypted.setText(en_number + " Files");
                result_of_files_Decrypted.setText(un_number + " Files");
                CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                ll.setAdapter(customListAdapter);
                now_menu_chooshed = "Movie";
            }
        }
    }

    public void onPhoto_Files_Click(View v)
    {
        if(image_clickable) {
            if (!now_menu_chooshed.equals("Image")) {
                photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                music_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                other_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));


                String[][] ff = Get_Files_For_List(images_extensions);
                int en_number = 0 , un_number = 0;
                int h = ff[0].length;
                imgs = new Integer[h];
                desc = new boolean[h];
                for (int m = 0; m < h; m++) {
                    if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                        imgs[m] = R.drawable.lock_open;
                        desc[m] = false;
                        un_number++;
                    } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                        imgs[m] = R.drawable.locked;
                        desc[m] = true;
                        en_number++;
                    }
                }
                result_of_files_Encrypted.setText(en_number + " Files");
                result_of_files_Decrypted.setText(un_number + " Files");
                CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                ll.setAdapter(customListAdapter);
                now_menu_chooshed = "Image";
            }
        }
    }

    public void onPdf_Files_Click(View v)
    {
        if(pdf_clickable) {
            if (!now_menu_chooshed.equals("Pdf")) {
                pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                music_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                other_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));

                String[][] ff = Get_Files_For_List(pdf_extensions);
                int en_number = 0 , un_number = 0;
                int h = ff[0].length;
                imgs = new Integer[h];
                desc = new boolean[h];
                for (int m = 0; m < h; m++) {
                    if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                        imgs[m] = R.drawable.lock_open;
                        desc[m] = false;
                        un_number++;
                    } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                        imgs[m] = R.drawable.locked;
                        desc[m] = true;
                        en_number++;
                    }
                }
                result_of_files_Encrypted.setText(en_number + " Files");
                result_of_files_Decrypted.setText(un_number + " Files");
                CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                ll.setAdapter(customListAdapter);
                now_menu_chooshed = "Pdf";
            }
        }
    }

    public void onOther_Files_Click(View v)
    {
        if(other_clickable) {
            if (!now_menu_chooshed.equals("Other")) {
                other_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                music_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
                movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));

                String[][] ff = Get_Files_For_List(other_extensions);
                int en_number = 0 , un_number = 0;
                int h = ff[0].length;
                imgs = new Integer[h];
                desc = new boolean[h];
                for (int m = 0; m < h; m++) {
                    if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                        imgs[m] = R.drawable.lock_open;
                        desc[m] = false;
                        un_number++;
                    } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                        imgs[m] = R.drawable.locked;
                        desc[m] = true;
                        en_number++;
                    }
                }
                result_of_files_Encrypted.setText(en_number + " Files");
                result_of_files_Decrypted.setText(un_number + " Files");
                CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                ll.setAdapter(customListAdapter);
                now_menu_chooshed = "Other";
            }
        }
    }

    public void count_files(File file , String[] extensions)
    {
        try {
            if (file.isFile()) {
                for (String exten : extensions)
                {
                    if (file.getName().endsWith(exten))
                    {
                        j++;
                    }
                }
            } else {
                if (file.isDirectory() && !file.getName().equals("Android") && !file.getName().startsWith(".")) {
                    for (File f : file.listFiles()) {
                        count_files(f, extensions);
                    }

                } else {

                }
            }
        }
        catch (Exception e)
        {

        }
    }

    public void Find_Songs(File file,String[] extensions)
    {
        try {
            if (file.isFile()) {
                for (String exten : extensions)
                {
                    if (file.getName().endsWith(exten))
                    {
                        Names[i] = file.getName();
                        Paths[i] = file.getAbsolutePath();
                        i++;
                    }
                }
            } else {
                if (file.isDirectory() && !file.getName().equals("Android") && !file.getName().startsWith(".")) {
                    for (File f : file.listFiles()) {
                        Find_Songs(f, extensions);
                    }

                } else {

                }
            }
        }
        catch (Exception e)
        {

        }
    }

    public int find_file_position(String selected)
    {
        for(int k=0;k<j;k++)
        {
            if(Names[k].equals(selected))
            {
                return k;
            }
        }
        return -1;
    }

    public void Update_List(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        if(flag)
        {
            if(close_flag)
            {
                close_flag = false;
                update_icon.setBackground(getResources().getDrawable(R.drawable.search));

                if(now_menu_chooshed.equals("Music"))
                {
                    String[][] ff = Get_Files_For_List(music_extensions);
                    int en_number = 0 , un_number = 0;
                    int h = ff[0].length;
                    imgs = new Integer[h];
                    desc = new boolean[h];
                    for (int m = 0; m < h; m++) {
                        if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                            imgs[m] = R.drawable.lock_open;
                            desc[m] = false;
                            un_number++;
                        } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                            imgs[m] = R.drawable.locked;
                            desc[m] = true;
                            en_number++;
                        }
                    }
                    result_of_files_Encrypted.setText(en_number + " Files");
                    result_of_files_Decrypted.setText(un_number + " Files");
                    CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                    ll.setAdapter(customListAdapter);
                }
                else if(now_menu_chooshed.equals("Movie"))
                {
                    String[][] ff = Get_Files_For_List(movie_extensions);
                    int en_number = 0 , un_number = 0;
                    int h = ff[0].length;
                    imgs = new Integer[h];
                    desc = new boolean[h];
                    for (int m = 0; m < h; m++) {
                        if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                            imgs[m] = R.drawable.lock_open;
                            desc[m] = false;
                            un_number++;
                        } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                            imgs[m] = R.drawable.locked;
                            desc[m] = true;
                            en_number++;
                        }
                    }
                    result_of_files_Encrypted.setText(en_number + " Files");
                    result_of_files_Decrypted.setText(un_number + " Files");
                    CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                    ll.setAdapter(customListAdapter);
                }
                else if(now_menu_chooshed.equals("Image"))
                {
                    String[][] ff = Get_Files_For_List(images_extensions);
                    int en_number = 0 , un_number = 0;
                    int h = ff[0].length;
                    imgs = new Integer[h];
                    desc = new boolean[h];
                    for (int m = 0; m < h; m++) {
                        if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                            imgs[m] = R.drawable.lock_open;
                            desc[m] = false;
                            un_number++;
                        } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                            imgs[m] = R.drawable.locked;
                            desc[m] = true;
                            en_number++;
                        }
                    }
                    result_of_files_Encrypted.setText(en_number + " Files");
                    result_of_files_Decrypted.setText(un_number + " Files");
                    CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                    ll.setAdapter(customListAdapter);
                }
                else if(now_menu_chooshed.equals("Pdf"))
                {
                    String[][] ff = Get_Files_For_List(pdf_extensions);
                    int en_number = 0 , un_number = 0;
                    int h = ff[0].length;
                    imgs = new Integer[h];
                    desc = new boolean[h];
                    for (int m = 0; m < h; m++) {
                        if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                            imgs[m] = R.drawable.lock_open;
                            desc[m] = false;
                            un_number++;
                        } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                            imgs[m] = R.drawable.locked;
                            desc[m] = true;
                            en_number++;
                        }
                    }
                    result_of_files_Encrypted.setText(en_number + " Files");
                    result_of_files_Decrypted.setText(un_number + " Files");
                    CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                    ll.setAdapter(customListAdapter);
                }
                else if(now_menu_chooshed.equals("Other"))
                {
                    String[][] ff = Get_Files_For_List(other_extensions);
                    int en_number = 0 , un_number = 0;
                    int h = ff[0].length;
                    imgs = new Integer[h];
                    desc = new boolean[h];
                    for (int m = 0; m < h; m++) {
                        if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                            imgs[m] = R.drawable.lock_open;
                            desc[m] = false;
                            un_number++;
                        } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                            imgs[m] = R.drawable.locked;
                            desc[m] = true;
                            en_number++;
                        }
                    }
                    result_of_files_Encrypted.setText(en_number + " Files");
                    result_of_files_Decrypted.setText(un_number + " Files");
                    CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
                    ll.setAdapter(customListAdapter);
                }
            }
            else {
                Search_Banner_DialogBox_Alpha.setVisibility(LinearLayout.VISIBLE);
                Search_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.VISIBLE);
            }
        }

    }

    public String[][] Get_Files_For_List(String[] extensions)
    {
        int c = 0 , p = 0 , counter = 0;
        for(String s : Names)
        {
            for(String exten : extensions)
            {
                if (s.endsWith(exten))
                {
                    c++;
                }
            }
        }
        String[][] Result = new String[2][c];
        counter = 0;
        for(String s : Names)
        {
            for(String exten : extensions)
            {
                if (s.endsWith(exten))
                {
                    Result[0][p] = s;
                    Result[1][p] = Paths[counter];
                    p++;
                }
            }
            counter ++;
        }

        return Result;
    }

    public void Do_Nothing(View v)
    {
        // Do Nothing
    }

    public void Close_Banner(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mp.stop();
        mp.reset();
        play_music.setBackground(getResources().getDrawable(R.drawable.play_music));
        flag_of_paly_or_pause = true;
        Music_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        Music_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
    }

    public void Play_Music(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));
        if(flag_of_paly_or_pause) {
            mp.start();
            play_music.setBackground(getResources().getDrawable(R.drawable.pause_music));
            flag_of_paly_or_pause = false;
        }
        else
        {
            mp.pause();
            play_music.setBackground(getResources().getDrawable(R.drawable.play_music));
            flag_of_paly_or_pause = true;
        }
    }

    public void Stop_Music(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        mp.stop();
        mp.reset();
        play_music.setBackground(getResources().getDrawable(R.drawable.play_music));
        flag_of_paly_or_pause = true;
        try {
            mp.setDataSource(selected);
            mp.prepare();
        }
        catch (Exception e)
        {

        }
    }

    public void Close_Movie_Banner(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        Movie_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        Movie_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
        videoView.stopPlayback();
        flag_of_video_paly_or_pause = true;
        try
        {
            plm.setBackground(getResources().getDrawable(R.drawable.play_music));
        }
        catch (Exception e)
        {

        }
    }

    public void Play_Movie(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        if(flag_of_video_paly_or_pause && (selected.endsWith(".mp4")||selected.endsWith(".MP4")))
        {
            videoView.start();
            plm.setBackground(getResources().getDrawable(R.drawable.pause_movie));
            flag_of_video_paly_or_pause = false;
        }
        else if(!flag_of_video_paly_or_pause && (selected.endsWith(".mp4")||selected.endsWith(".MP4")))
        {
            videoView.pause();
            plm.setBackground(getResources().getDrawable(R.drawable.play_movie));
            flag_of_video_paly_or_pause = true;
        }
    }

    public File get_external_storage_directory()
    {
        String sd = "/mnt";
        File f = new File(sd);
        for(File df : f.listFiles())
        {
            if(df.canWrite()&&df.canRead() && ( df.getName().equals("ext_card") || df.getName().equals("ext_sdcard") || df.getName().equals("ext_card")
                    || df.getName().equals("ext_sd")||df.getName().equals("external")
                    || df.getName().equals("extSdCard")||df.getName().equals("externalSdCard")
                    || df.getName().equals("sdcard2")||df.getName().equals("sdcard_ext")
                    || df.getName().equals("extsd")))
                return df;
        }
        return null;
    }

    public void Close_Image_Banner(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        Image_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
        Image_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
    }

    public void Close_PDF_Banner(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        PDF_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        PDF_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
    }

    public void ViewPDF(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        Uri path = Uri.fromFile(new File(selected));
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path,"application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try
        {
            startActivity(pdfIntent);
        }
        catch (Exception e)
        {
            Toast.makeText(getBaseContext(),"No application available to view your pdf.",Toast.LENGTH_LONG);
        }
    }

    public void Close_Other_Banner(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        Other_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        Other_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
    }

    public void Close_Search_Banner(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        View view = this.getCurrentFocus();
        if( view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }


        Search_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        Search_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
        Search_InputText.setText("");
    }

    public void Search_Button_Go(View v)
    {

        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        View view = this.getCurrentFocus();
        if( view != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }


        Search_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        Search_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);

        String typed_string = Search_InputText.getText().toString().toLowerCase();
        Search_InputText.setText("");

        update_icon.setBackground(getResources().getDrawable(R.drawable.close_search));
        close_flag = true;

        if(now_menu_chooshed.equals("Music"))
        {

            String[][] ff = Get_Files_For_List(music_extensions);
            String[] names;
            int yt = 0;
            String temp;
            for(String ut : ff[0])
            {
                temp = ut.toLowerCase();
                if(temp.contains(typed_string))
                {
                    yt++;
                }
            }
            int h = yt;
            names = new String[h];
            String[] pthha = new String[h];
            yt = 0;
            int cu = 0;
            for(String kl : ff[0])
            {
                temp = kl.toLowerCase();
                if(temp.contains(typed_string))
                {
                    names[yt] = kl;
                    pthha[yt] = ff[1][cu];
                    yt++;
                }
                cu++;
            }
            int en_number = 0 , un_number = 0;
            Integer[] img = new Integer[h];
            boolean[] des = new boolean[h];

            for (int m = 0; m < h; m++)
            {
                if (Check_Encrypted_Or_Not(pthha[m]) == 0)
                {
                    img[m] = R.drawable.lock_open;
                    des[m] = false;
                    un_number++;
                }
                else
                {
                    img[m] = R.drawable.locked;
                    des[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, names,pthha, img, des);
            ll.setAdapter(customListAdapter);


        }

        else if(now_menu_chooshed.equals("Movie"))
        {
            String[][] ff = Get_Files_For_List(movie_extensions);
            String[] names;
            int yt = 0;
            String temp;
            for(String ut : ff[0])
            {
                temp = ut.toLowerCase();
                if(temp.contains(typed_string))
                {
                    yt++;
                }
            }
            int h = yt;
            names = new String[h];
            yt = 0;
            int cu = 0;
            String[] pthha = new String[h];
            for(String kl : ff[0])
            {
                temp = kl.toLowerCase();
                if(temp.contains(typed_string))
                {
                    names[yt] = kl;
                    pthha[yt] = ff[1][cu];
                    yt++;
                }
                cu++;
            }
            int en_number = 0 , un_number = 0;
            Integer[] img = new Integer[h];
            boolean[] des = new boolean[h];



            for (int m = 0; m < h; m++)
            {
                if (Check_Encrypted_Or_Not(pthha[m]) == 0)
                {
                    img[m] = R.drawable.lock_open;
                    des[m] = false;
                    un_number++;
                }
                else
                {
                    img[m] = R.drawable.locked;
                    des[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, names,pthha, img, des);
            ll.setAdapter(customListAdapter);

        }
        else if(now_menu_chooshed.equals("Image"))
        {
            String[][] ff = Get_Files_For_List(images_extensions);
            String[] names;
            int yt = 0;
            String temp;
            for(String ut : ff[0])
            {
                temp = ut.toLowerCase();
                if(temp.contains(typed_string))
                {
                    yt++;
                }
            }
            int h = yt;
            names = new String[h];
            yt = 0;
            int cu = 0;
            String[] pthha = new String[h];
            for(String kl : ff[0])
            {
                temp = kl.toLowerCase();
                if(temp.contains(typed_string))
                {
                    names[yt] = kl;
                    pthha[yt] = ff[1][cu];
                    yt++;
                }
                cu++;
            }
            int en_number = 0 , un_number = 0;
            Integer[] img = new Integer[h];
            boolean[] des = new boolean[h];

            for (int m = 0; m < h; m++)
            {
                if (Check_Encrypted_Or_Not(pthha[m]) == 0)
                {
                    img[m] = R.drawable.lock_open;
                    des[m] = false;
                    un_number++;
                }
                else
                {
                    img[m] = R.drawable.locked;
                    des[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, names,pthha, img, des);
            ll.setAdapter(customListAdapter);

        }
        else if(now_menu_chooshed.equals("Pdf"))
        {
            String[][] ff = Get_Files_For_List(pdf_extensions);
            String[] names;
            int yt = 0;
            String temp;
            for(String ut : ff[0])
            {
                temp = ut.toLowerCase();
                if(temp.contains(typed_string))
                {
                    yt++;
                }
            }
            int h = yt;
            names = new String[h];
            yt = 0;
            int cu = 0;
            String[] pthha = new String[h];
            for(String kl : ff[0])
            {
                temp = kl.toLowerCase();
                if(temp.contains(typed_string))
                {
                    names[yt] = kl;
                    pthha[yt] = ff[1][cu];
                    yt++;
                }
                cu++;
            }
            int en_number = 0 , un_number = 0;
            Integer[] img = new Integer[h];
            boolean[] des = new boolean[h];


            for (int m = 0; m < h; m++)
            {
                if (Check_Encrypted_Or_Not(pthha[m]) == 0)
                {
                    img[m] = R.drawable.lock_open;
                    des[m] = false;
                    un_number++;
                }
                else
                {
                    img[m] = R.drawable.locked;
                    des[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, names,pthha, img, des);
            ll.setAdapter(customListAdapter);

        }
        else if(now_menu_chooshed.equals("Other"))
        {
            String[][] ff = Get_Files_For_List(other_extensions);
            String[] names;
            int yt = 0;
            String temp;
            for(String ut : ff[0])
            {
                temp = ut.toLowerCase();
                if(temp.contains(typed_string))
                {
                    yt++;
                }
            }
            int h = yt;
            names = new String[h];
            int cu = 0;
            yt = 0;
            String[] pthha = new String[h];
            for(String kl : ff[0])
            {
                temp = kl.toLowerCase();
                if(temp.contains(typed_string))
                {
                    names[yt] = kl;
                    pthha[yt] = ff[1][cu];
                    yt++;
                }
                cu++;
            }
            int en_number = 0 , un_number = 0;
            Integer[] img = new Integer[h];
            boolean[] des = new boolean[h];

            for (int m = 0; m < h; m++)
            {
                if (Check_Encrypted_Or_Not(pthha[m]) == 0)
                {
                    img[m] = R.drawable.lock_open;
                    des[m] = false;
                    un_number++;
                }
                else
                {
                    img[m] = R.drawable.locked;
                    des[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, names,pthha, img, des);
            ll.setAdapter(customListAdapter);

        }
    }

    public void Encrypt_Music(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        if(mp.isPlaying())
        {
            mp.stop();
        }
        endc obj = new endc();
        obj.job = "Music";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }

    public void Decrypt_Music(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        endc obj = new endc();
        obj.job = "Music";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }

    // *******************************************************
    public class upd extends AsyncTask<Void,Void,Void>
    {
        public View vi;
        public boolean show_progress_snack = true;
        ProgressDialog pd = new ProgressDialog(file_safety_class.this);
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            // set your text and object here
            up_on_post_execute();
            if(show_progress_snack) {
                pd.dismiss();
                Snackbar.make(vi, "Updated successfully !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            if(show_progress_snack) {
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setCancelable(false);
                pd.setMessage("Please wait ...");
                pd.setCanceledOnTouchOutside(false);
                pd.setIndeterminate(true);
                //Snackbar.make(vi, "Started Updating ...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                pd.show();
            }
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            //EnDcrypt(pat,pass);
            up_do_in_background();
            return null;
        }

    }

    public void up_do_in_background()
    {
        if(!flag) {
            flag = true;
            external_sdcard2 = get_external_storage_directory();
            if (external_sdcard2 != null) {
                count_files(external_sdcard2, extensions);
            }
            count_files(Environment.getExternalStorageDirectory(), extensions);
            Paths = new String[j];
            Names = new String[j];
        }
        Find_Songs(Environment.getExternalStorageDirectory(), extensions);
        if(external_sdcard2 != null){
            Find_Songs(external_sdcard2,extensions);
        }
        music_clickable = true;
        movie_clickable = true;
        image_clickable = true;
        pdf_clickable = true;
        other_clickable = true;
    }

    public void up_on_post_execute()
    {
        if(now_menu_chooshed.equals("") || now_menu_chooshed.equals("Music")) {
            music_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            other_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));

            String[][] ff = Get_Files_For_List(music_extensions);
            int en_number = 0, un_number = 0;
            int h = ff[0].length;
            imgs = new Integer[h];
            desc = new boolean[h];
            for (int m = 0; m < h; m++) {
                if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                    imgs[m] = R.drawable.lock_open;
                    desc[m] = false;
                    un_number++;
                } else if (Check_Encrypted_Or_Not(ff[1][m]) == 1) {
                    imgs[m] = R.drawable.locked;
                    desc[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0], ff[1], imgs, desc);
            ll.setAdapter(customListAdapter);
            now_menu_chooshed = "Music";
        }
        else if(now_menu_chooshed.equals("Movie"))
        {
            movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            music_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            other_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));

            String[][] ff = Get_Files_For_List(movie_extensions);
            int en_number = 0 , un_number = 0;
            int h = ff[0].length;
            imgs = new Integer[h];
            desc = new boolean[h];
            for (int m = 0; m < h; m++) {
                if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                    imgs[m] = R.drawable.lock_open;
                    desc[m] = false;
                    un_number++;
                } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                    imgs[m] = R.drawable.locked;
                    desc[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
            ll.setAdapter(customListAdapter);
            now_menu_chooshed = "Movie";
        }
        else if(now_menu_chooshed.equals("Image"))
        {
            photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            music_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            other_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));


            String[][] ff = Get_Files_For_List(images_extensions);
            int en_number = 0 , un_number = 0;
            int h = ff[0].length;
            imgs = new Integer[h];
            desc = new boolean[h];
            for (int m = 0; m < h; m++) {
                if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                    imgs[m] = R.drawable.lock_open;
                    desc[m] = false;
                    un_number++;
                } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                    imgs[m] = R.drawable.locked;
                    desc[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
            ll.setAdapter(customListAdapter);
            now_menu_chooshed = "Image";
        }
        else if(now_menu_chooshed.equals("Pdf"))
        {
            pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            music_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            other_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));

            String[][] ff = Get_Files_For_List(pdf_extensions);
            int en_number = 0 , un_number = 0;
            int h = ff[0].length;
            imgs = new Integer[h];
            desc = new boolean[h];
            for (int m = 0; m < h; m++) {
                if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                    imgs[m] = R.drawable.lock_open;
                    desc[m] = false;
                    un_number++;
                } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                    imgs[m] = R.drawable.locked;
                    desc[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
            ll.setAdapter(customListAdapter);
            now_menu_chooshed = "Pdf";
        }
        else if(now_menu_chooshed.equals("Other"))
        {
            other_sub_icon.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            music_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            photo_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            pdf_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));
            movie_sub_icon.setBackgroundColor(getResources().getColor(R.color.gray));

            String[][] ff = Get_Files_For_List(other_extensions);
            int en_number = 0 , un_number = 0;
            int h = ff[0].length;
            imgs = new Integer[h];
            desc = new boolean[h];
            for (int m = 0; m < h; m++) {
                if (Check_Encrypted_Or_Not(ff[1][m]) == 0) {
                    imgs[m] = R.drawable.lock_open;
                    desc[m] = false;
                    un_number++;
                } else if(Check_Encrypted_Or_Not(ff[1][m]) == 1){
                    imgs[m] = R.drawable.locked;
                    desc[m] = true;
                    en_number++;
                }
            }
            result_of_files_Encrypted.setText(en_number + " Files");
            result_of_files_Decrypted.setText(un_number + " Files");
            CustomListAdapter customListAdapter = new CustomListAdapter(this, ff[0],ff[1], imgs, desc);
            ll.setAdapter(customListAdapter);
            now_menu_chooshed = "Other";
        }
    }

    public int Check_Encrypted_Or_Not(String path)
    {
        try
        {
            BufferedInputStream file_reader = new BufferedInputStream(new FileInputStream(new File(path)));
            int po = 0 , c = 0;
            String ps = "";
            String toAdd = "WRCPSQLVT:";
            while (true) {
                c = file_reader.read();
                if (c == -1 || po >= 10)
                    break;
                else {
                    ps += (char) c;
                    po++;
                }
            }
            file_reader.close();
            if(ps.equals(toAdd))
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    public class endc extends AsyncTask<Void,Void,Void>
    {
        public View vi;
        int r = 0;
        public String pth = "" , pass = "" , job = "";
        ProgressDialog pd = new ProgressDialog(file_safety_class.this);
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            // set your text and object here
            endc_on_post_execute(job);
            up_on_post_execute();
            pd.dismiss();
            if(r == 1)
                Snackbar.make(vi, "Encrypted successfully !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            else if(r == 0)
                Snackbar.make(vi, "Decrypted successfully !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            else if(r == -1)
                Snackbar.make(vi, "Wrong password !", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(false);
            pd.setMessage("Please wait ...");
            pd.setCanceledOnTouchOutside(false);
            pd.setIndeterminate(true);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            r = EnDcrypt(pth,pass);
            up_do_in_background();
            return null;
        }
    }

    public String getMD5(String txt)
    {
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(txt.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInteger = new BigInteger(1,digest);
            String hashtext = bigInteger.toString(16);
            while (hashtext.length()<32){
                hashtext = "0"+hashtext;
            }
            return hashtext;
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public int EnDcrypt(String path , String password)
    {
        try {

            String hashed = getMD5(password);
            int length_of_Password = hashed.length();
            String new_name = path + "45";
            int po = 0;
            String ps = "";
            int c = 0;


            try
            {
                // checking that the file is encrypted or not

                BufferedInputStream file_reader = new BufferedInputStream(new FileInputStream(new File(path)));
                po = 0;
                ps = "";
                String extracted = "";
                String toAdd = "WRCPSQLVT:";
                while(true)
                {
                    c = file_reader.read();
                    if(c == -1 || po >= 42)
                        break;
                    else {
                        ps += (char)c;
                        po++;
                    }
                }
                file_reader.close();
                po = 0;
                for(char ch : ps.toCharArray())
                {
                    if(po<10)
                    {
                        extracted += ch;
                        po++;
                    }
                    else
                    {
                        break;
                    }
                }
                if(extracted.equals(toAdd))
                {
                    if(ps.split(toAdd)[1].equals(hashed))
                    {
                        // this file was encrypted, Opening it
                        File for_renaming = new File(path);
                        for_renaming.renameTo(new File(new_name));

                        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(new File(new_name)));
                        BufferedOutputStream file_writer = new BufferedOutputStream(new FileOutputStream(new File(path)));

                        po = 0;
                        int uu = 0;
                        ps = "";
                        toAdd = toAdd + hashed;
                        while(true)
                        {
                            if(po < 42)
                            {
                                c = reader.read();
                                po++;
                            }
                            else
                            {
                                c = reader.read();
                                if(c == -1)
                                {
                                    break;
                                }
                                else
                                {
                                    file_writer.write((char)(c ^ (int)hashed.charAt(uu%length_of_Password)));
                                    uu++;
                                }
                            }
                        }
                        file_writer.flush();
                        reader.close();
                        file_writer.close();

                        File fpo = new File(new_name);
                        fpo.delete();

                        return 0;
                    }
                    else
                    {
                        return -1;
                    }
                }
                else
                {
                    // this file is open , Encrypting it ...
                    File for_renaming = new File(path);
                    for_renaming.renameTo(new File(new_name));

                    BufferedInputStream reader = new BufferedInputStream(new FileInputStream(new File(new_name)));
                    BufferedOutputStream file_writer = new BufferedOutputStream(new FileOutputStream(new File(path)));

                    po = 0;
                    int uu = 0;
                    toAdd = toAdd + hashed;
                    while(true)
                    {
                        if(po<42)
                        {
                            file_writer.write(toAdd.charAt(po));
                            po++;
                        }
                        else
                        {
                            c = reader.read();
                            if(c == -1)
                            {
                                break;
                            }
                            else
                            {
                                file_writer.write((char)(c ^ (int)hashed.charAt(uu%length_of_Password)));
                                uu++;
                            }
                        }
                    }
                    reader.close();
                    file_writer.close();

                    File fpo = new File(new_name);
                    fpo.delete();

                    return 1;
                }
            }
            catch (Exception e)
            {
                return -1;
            }
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    public void endc_on_post_execute(String job)
    {
        if(job.equals("Music"))
        {
            //mp.release();
            play_music.setBackground(getResources().getDrawable(R.drawable.play_music));
            flag_of_paly_or_pause = true;
            Music_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
            Music_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        }
        else if(job.equals("Movie"))
        {
            flag_of_video_paly_or_pause = true;
            Movie_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
            Movie_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
        }
        else if(job.equals("Image"))
        {
            Image_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
            Image_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
        }
        else if(job.equals("Pdf"))
        {
            PDF_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
            PDF_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        }
        else if(job.equals("Other"))
        {
            Other_Banner_DialogBox_Alpha.setVisibility(LinearLayout.INVISIBLE);
            Other_Banner_DialogBox_Main_Part_Parent.setVisibility(LinearLayout.INVISIBLE);
        }
    }

    public void Encrypt_Movie(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));
        if(videoView.isPlaying())
        {
            videoView.stopPlayback();
        }
        endc obj = new endc();
        obj.job = "Movie";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }

    public void Decrypt_Movie(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        endc obj = new endc();
        obj.job = "Movie";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }

    public void Encrypt_Image(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        endc obj = new endc();
        obj.job = "Image";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }

    public void Decrypt_Image(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        endc obj = new endc();
        obj.job = "Image";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }

    public void Encrypt_Pdf(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        endc obj = new endc();
        obj.job = "Pdf";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }

    public void Decrypt_Pdf(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        endc obj = new endc();
        obj.job = "Pdf";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }

    public void Encrypt_Other(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        endc obj = new endc();
        obj.job = "Other";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }

    public void Decrypt_Other(View v)
    {
        v.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setBackgroundColor(getResources().getColor(R.color.gray));
                return false;
            }
        });
        v.setBackgroundColor(getResources().getColor(android.R.color.background_light));

        endc obj = new endc();
        obj.job = "Other";
        obj.pass = pas;
        obj.pth = Paths[index];
        obj.vi = v;
        obj.execute();
    }
}
