package com.farukaygun.yorozuyalist;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.farukaygun.yorozuyalist.databinding.FragmentAnimeBindingImpl;
import com.farukaygun.yorozuyalist.databinding.FragmentHomeBindingImpl;
import com.farukaygun.yorozuyalist.databinding.FragmentMangaBindingImpl;
import com.farukaygun.yorozuyalist.databinding.FragmentProfileBindingImpl;
import com.farukaygun.yorozuyalist.databinding.ItemSeasonalAnimeRecyclerBindingImpl;
import com.farukaygun.yorozuyalist.databinding.ItemSuggestedAnimeRecyclerBindingImpl;
import com.farukaygun.yorozuyalist.databinding.ItemTodayAnimeRecyclerBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTANIME = 1;

  private static final int LAYOUT_FRAGMENTHOME = 2;

  private static final int LAYOUT_FRAGMENTMANGA = 3;

  private static final int LAYOUT_FRAGMENTPROFILE = 4;

  private static final int LAYOUT_ITEMSEASONALANIMERECYCLER = 5;

  private static final int LAYOUT_ITEMSUGGESTEDANIMERECYCLER = 6;

  private static final int LAYOUT_ITEMTODAYANIMERECYCLER = 7;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(7);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.farukaygun.yorozuyalist.R.layout.fragment_anime, LAYOUT_FRAGMENTANIME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.farukaygun.yorozuyalist.R.layout.fragment_home, LAYOUT_FRAGMENTHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.farukaygun.yorozuyalist.R.layout.fragment_manga, LAYOUT_FRAGMENTMANGA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.farukaygun.yorozuyalist.R.layout.fragment_profile, LAYOUT_FRAGMENTPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.farukaygun.yorozuyalist.R.layout.item_seasonal_anime_recycler, LAYOUT_ITEMSEASONALANIMERECYCLER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.farukaygun.yorozuyalist.R.layout.item_suggested_anime_recycler, LAYOUT_ITEMSUGGESTEDANIMERECYCLER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.farukaygun.yorozuyalist.R.layout.item_today_anime_recycler, LAYOUT_ITEMTODAYANIMERECYCLER);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTANIME: {
          if ("layout/fragment_anime_0".equals(tag)) {
            return new FragmentAnimeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_anime is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTHOME: {
          if ("layout/fragment_home_0".equals(tag)) {
            return new FragmentHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMANGA: {
          if ("layout/fragment_manga_0".equals(tag)) {
            return new FragmentMangaBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_manga is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILE: {
          if ("layout/fragment_profile_0".equals(tag)) {
            return new FragmentProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSEASONALANIMERECYCLER: {
          if ("layout/item_seasonal_anime_recycler_0".equals(tag)) {
            return new ItemSeasonalAnimeRecyclerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_seasonal_anime_recycler is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMSUGGESTEDANIMERECYCLER: {
          if ("layout/item_suggested_anime_recycler_0".equals(tag)) {
            return new ItemSuggestedAnimeRecyclerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_suggested_anime_recycler is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMTODAYANIMERECYCLER: {
          if ("layout/item_today_anime_recycler_0".equals(tag)) {
            return new ItemTodayAnimeRecyclerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_today_anime_recycler is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(4);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "listener");
      sKeys.put(2, "seasonalAnimeData");
      sKeys.put(3, "suggestedAnimeData");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(7);

    static {
      sKeys.put("layout/fragment_anime_0", com.farukaygun.yorozuyalist.R.layout.fragment_anime);
      sKeys.put("layout/fragment_home_0", com.farukaygun.yorozuyalist.R.layout.fragment_home);
      sKeys.put("layout/fragment_manga_0", com.farukaygun.yorozuyalist.R.layout.fragment_manga);
      sKeys.put("layout/fragment_profile_0", com.farukaygun.yorozuyalist.R.layout.fragment_profile);
      sKeys.put("layout/item_seasonal_anime_recycler_0", com.farukaygun.yorozuyalist.R.layout.item_seasonal_anime_recycler);
      sKeys.put("layout/item_suggested_anime_recycler_0", com.farukaygun.yorozuyalist.R.layout.item_suggested_anime_recycler);
      sKeys.put("layout/item_today_anime_recycler_0", com.farukaygun.yorozuyalist.R.layout.item_today_anime_recycler);
    }
  }
}
