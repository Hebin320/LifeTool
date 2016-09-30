package com.hebin.lifetool.ui.fragment.constellation;


import android.widget.ImageView;
import android.widget.TextView;

import com.hebin.lifetool.R;

class ConstellationSetLogo {

    void setLogo(String mParam1, ImageView ivLogo, TextView tvName) {
        switch (mParam1) {
            case "白羊座":
                ivLogo.setImageResource(R.mipmap.ic_aries);
                tvName.setText("Aries");
                break;
            case "金牛座":
                ivLogo.setImageResource(R.mipmap.ic_taurus);
                tvName.setText("Taurus");
                break;
            case "双子座":
                ivLogo.setImageResource(R.mipmap.ic_gemini);
                tvName.setText("Gemini");
                break;
            case "巨蟹座":
                ivLogo.setImageResource(R.mipmap.ic_cancer);
                tvName.setText("Cancer");
                break;
            case "狮子座":
                ivLogo.setImageResource(R.mipmap.ic_leo);
                tvName.setText("Leo");
                break;
            case "处女座":
                ivLogo.setImageResource(R.mipmap.ic_virgo);
                tvName.setText("Virgo");
                break;
            case "天秤座":
                ivLogo.setImageResource(R.mipmap.ic_libra);
                tvName.setText("Libra");
                break;
            case "天蝎座":
                ivLogo.setImageResource(R.mipmap.ic_scorpio);
                tvName.setText("Scorpio");
                break;
            case "射手座":
                ivLogo.setImageResource(R.mipmap.ic_sagittarius);
                tvName.setText("Sagittarius");
                break;
            case "摩羯座":
                ivLogo.setImageResource(R.mipmap.ic_capricorn);
                tvName.setText("Capricorn");
                break;
            case "水瓶座":
                ivLogo.setImageResource(R.mipmap.ic_aquarius);
                tvName.setText("Aquarius");
                break;
            case "双鱼座":
                ivLogo.setImageResource(R.mipmap.ic_pisces);
                tvName.setText("Pisces");
                break;
            default:
                break;
        }
    }
}
