package com.kingmonkey.fire2road.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.kingmonkey.fire2road.Adapters.ProductsAdapter;
import com.kingmonkey.fire2road.Domain.ProductObject;
import com.kingmonkey.fire2road.R;

public class CascosFragment extends Fragment {
    Context c;
    SliderLayout sliderCascosDestacados;
    ListView lista;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        c = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        c = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cascos, container, false);
        sliderCascosDestacados = (SliderLayout) rootView.findViewById(R.id.cascosDestacados);
        sliderCascosDestacados.setBackgroundColor(Color.parseColor("#050505"));
        TextSliderView textSliderView1 = new TextSliderView(c);
        textSliderView1
                .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                .description("Arai Helmets")
                .image("http://images.mcn.bauercdn.com/upload/303870/images/1752x1168/arai-helmets.jpg?mode=max&quality=90&scale=down");
        TextSliderView textSliderView2 = new TextSliderView(c);
        textSliderView2
                .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                .description("Arai Helmets")
                .image("http://www.funbike.com/wp-content/uploads/2013/01/arai.jpg");
        TextSliderView textSliderView3 = new TextSliderView(c);
        textSliderView3
                .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                .description("Arai Helmets")
                .image("http://www.amaproracing.com/images/content/story/ama-pro-tech-tuesday-helmet-myths-facts-tips-arai.jpg");
        sliderCascosDestacados.addSlider(textSliderView1);
        sliderCascosDestacados.addSlider(textSliderView2);
        sliderCascosDestacados.addSlider(textSliderView3);
        sliderCascosDestacados.stopAutoCycle();
        sliderCascosDestacados.setCustomIndicator((PagerIndicator) rootView.findViewById(R.id.custom_indicator));

        lista = (ListView)rootView.findViewById(R.id.listView);
        ProductObject[] productObjectsVector = new ProductObject[50];
        for (int i=0;i<productObjectsVector.length;i++){
            ProductObject productObject = new ProductObject("http://www.cycleworld.com/sites/cycleworld.com/files/Arai-Corsair-X-RX-7X_HAYDEN_P.jpg","Corsair-X","2'750.000 COP","Descripción corta...");
            productObjectsVector[i] = productObject;
        }
        ProductsAdapter productsAdapter = new ProductsAdapter(c,R.layout.product_item,productObjectsVector);
        lista.setAdapter(productsAdapter);
        return rootView;
    }
}
