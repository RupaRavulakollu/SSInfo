package com.citchennai.dous.careercompass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Random;
import java.util.TreeMap;

public class SUpport_Ui extends AppCompatActivity {
    TreeMap<Integer, String[]> list_of_partipi;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onBackPressed()
    {
       // ((BoomMenuButton) ((ListView)findViewById(R.id.btn_signups_page)).findViewById(R.id.boom_circle)).boom();
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boom_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        startService(new Intent(SUpport_Ui.this,Chat_Icon_Service.class));
        setTitle("Support");
        list_of_partipi = new TreeMap<>();
        String data_temp[]=new String[5];
//        data_temp[0] = "Subash G";
//        data_temp[1] = "WIN Chennai,India";
//        data_temp[2] = "+919840820191";
//        data_temp[3] = "subash.g@wabco-auto.com";
//        list_of_partipi.put(8, data_temp);
        data_temp = null;

//        data_temp = new String[5];
//        data_temp[0] = "Sriram B";
//        data_temp[1] = "WIN Chennai,India";
//        data_temp[2] = "+917358016833";
//        data_temp[3] = "sriram.b@wabco-auto.com";
//        list_of_partipi.put(7, data_temp);
//        data_temp = null;

//        data_temp = new String[5];
//        data_temp[0] = "Parthiban G";
//        data_temp[1] = "WIN Chennai,India";
//        data_temp[2] = "+919600036309";
//        data_temp[3] = "parthiban.g@wabco-auto.com";
//        list_of_partipi.put(3, data_temp);
//        data_temp = null;

//        data_temp = new String[5];
//        data_temp[0] = "Ravishankar N";
//        data_temp[1] = "WIN Mahindra City,India";
//        data_temp[2] = "+919677067951";
//        data_temp[3] = "ravishankar.n@wabco-auto.com";
//        list_of_partipi.put(6, data_temp);
//        data_temp = null;

//        data_temp = new String[5];
//        data_temp[0] = "Jeganathan P";
//        data_temp[1] = "WIN Chennai,India";
//        data_temp[2] = "+919940674194";
//        data_temp[3] = "jeganathan.p@wabco-auto.com";
//        list_of_partipi.put(2, data_temp);
//        data_temp = null;

//        data_temp = new String[5];
//        data_temp[0] = "Ramkumar K";
//        data_temp[1] = "WIN Chennai,India";
//        data_temp[2] = "+919840983368";
//        data_temp[3] = "ramkumar.k@wabco-auto.com";
//        list_of_partipi.put(5, data_temp);

//        data_temp = null;
//        data_temp = new String[5];
//        data_temp[0] = "Pratyaya Chatterjee";
//        data_temp[1] = "WIN Chennai,India";
//        data_temp[2] = "+919600035915";
//        data_temp[3] = "pratyaya.chatterjee@wabco-auto.com";
//        list_of_partipi.put(4, data_temp);

        data_temp = new String[5];
        data_temp[0] = "Meenakshi";
        data_temp[1] = "Chennai Institute Of Technology";
        data_temp[2] = "+919176152382";
        data_temp[3] = "careercompass@citchennai.net";
        list_of_partipi.put(0, data_temp);

        data_temp = new String[5];
        data_temp[0] = "Niranjani ";
        data_temp[1] = "Chennai Institute Of Technology";
        data_temp[2] = "+918838649044";
        data_temp[3] = "careercompass@citchennai.net";
        list_of_partipi.put(1, data_temp);


        ((ListView) findViewById(R.id.list_view)).setAdapter(new SUpport_Ui.MyAdapter());
        ((ListView) findViewById(R.id.list_view)).setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ((BoomMenuButton) view.findViewById(R.id.boom_circle)).boom();
                    }
                });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);

                viewHolder = new SUpport_Ui.MyAdapter.ViewHolder();
                viewHolder.tv = (TextView) convertView.findViewById(R.id.text_view);
                viewHolder.tv1 = (TextView) convertView.findViewById(R.id.text_view1);
                viewHolder.circleBoomMenuButton = (BoomMenuButton) convertView.findViewById(R.id.boom_circle);
//                viewHolder.hamBoomMenuButton = (BoomMenuButton) convertView.findViewById(R.id.boom_ham);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (SUpport_Ui.MyAdapter.ViewHolder) convertView.getTag();
            }

            viewHolder.tv.setText(list_of_partipi.get(position)[0]);
            viewHolder.tv1.setText(list_of_partipi.get(position)[1]);
//            viewHolder.tv1.setText("WCN Qingdao,China");

            final Drawable[] circleSubButtonDrawables = new Drawable[2];
            int[] drawablesResource = new int[]{
                    R.drawable.phn,
                    R.drawable.email

            };
            for (int i = 0; i < 2; i++)
                circleSubButtonDrawables[i]
                        = ContextCompat.getDrawable(parent.getContext(), drawablesResource[i]);

            final Drawable[] hamSubButtonDrawables = new Drawable[2];
            drawablesResource = new int[]{
                    R.drawable.java,
                    R.drawable.java,

            };
            for (int i = 0; i < 2; i++)
                hamSubButtonDrawables[i]
                        = ContextCompat.getDrawable(parent.getContext(), drawablesResource[i]);

            final String[] circleSubButtonTexts = new String[]{
                    "Call",
                    "Email"
                    };


            final int[][] subButtonColors = new int[3][2];
            for (int i = 0; i < 2; i++) {
                subButtonColors[i][1] = GetRandomColor();
                subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
            }

            // init the BMB with delay

            viewHolder.circleBoomMenuButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Now with Builder, you can init BMB more convenient
                    new BoomMenuButton.Builder()
                            .subButtons(circleSubButtonDrawables, subButtonColors, circleSubButtonTexts)
                            .button(ButtonType.CIRCLE)
                            .boom(BoomType.PARABOLA)
                            .place(PlaceType.CIRCLE_3_1)
                            .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                            .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
                                @Override
                                public void onClick(int buttonIndex) {

                                    switch (buttonIndex) {
                                        case 0:
                                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                                            callIntent.setData(Uri.parse("tel:" + list_of_partipi.get(position)[2]));
                                            if (ActivityCompat.checkSelfPermission(SUpport_Ui.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                                // TODO: Consider calling
                                                //    ActivityCompat#requestPermissions
                                                // here to request the missing permissions, and then overriding
                                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                //                                          int[] grantResults)
                                                // to handle the case where the user grants the permission. See the documentation
                                                // for ActivityCompat#requestPermissions for more details.
                                                ActivityCompat.requestPermissions(SUpport_Ui.this,
                                                        new String[]{Manifest.permission.CALL_PHONE},
                                                        144);

                                                return;
                                            }
                                            startActivity(callIntent);
                                            break;
                                        case 1:

                                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                                    "mailto", list_of_partipi.get(position)[3], null));
                                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                                            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                                            startActivity(Intent.createChooser(emailIntent, "Send email..."));
                                            break;
//                                        case 2:
////                                            Intent whatsAppintent = new Intent(Intent.ACTION_SEND,Uri.parse("smsto:"+list_of_partipi.get(position)[2]));
////                                            whatsAppintent.setType("text/plain");
////                                            whatsAppintent.setPackage("com.whatsapp");
////                                            startActivity(whatsAppintent);
//                                            Uri uri = Uri.parse("smsto: +91 9100838454");
//                                            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
//                                            i.setPackage("com.whatsapp");
//                                            startActivity(Intent.createChooser(i, ""));
//                                            break;
                                    }
                                }
                            })
                            .init(viewHolder.circleBoomMenuButton);
                }
            }, 1);

//            viewHolder.hamBoomMenuButton.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    // Now with Builder, you can init BMB more convenient
//                    new BoomMenuButton.Builder()
//                            .subButtons(hamSubButtonDrawables, subButtonColors, hamSubButtonTexts)
//                            .button(ButtonType.HAM)
//                            .boom(BoomType.PARABOLA)
//                            .place(PlaceType.HAM_3_1)
//                            .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
//                            .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
//                                @Override
//                                public void onClick(int buttonIndex) {
//                                    Toast.makeText(
//                                            parent.getContext(),
//                                            "On click " + hamSubButtonTexts[buttonIndex],
//                                            Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .init(viewHolder.hamBoomMenuButton);
//                }
//            }, 1);

            return convertView;
        }

        class ViewHolder {
            public TextView tv;
            public TextView tv1;
            public BoomMenuButton circleBoomMenuButton;
//            public BoomMenuButton hamBoomMenuButton;
        }
    }

    private static String[] Colors = {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722",
            "#795548",
            "#9E9E9E",
            "#607D8B"};

    public static int GetRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 144: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(this, "Suessfully granted for calling please press again", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(this, "Please give permission to make an call", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


}
