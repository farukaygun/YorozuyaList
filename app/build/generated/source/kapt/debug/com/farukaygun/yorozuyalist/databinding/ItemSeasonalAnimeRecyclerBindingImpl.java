package com.farukaygun.yorozuyalist.databinding;
import com.farukaygun.yorozuyalist.R;
import com.farukaygun.yorozuyalist.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemSeasonalAnimeRecyclerBindingImpl extends ItemSeasonalAnimeRecyclerBinding implements com.farukaygun.yorozuyalist.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final com.google.android.material.card.MaterialCardView mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemSeasonalAnimeRecyclerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ItemSeasonalAnimeRecyclerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.imageview.ShapeableImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            );
        this.imageViewPicture.setTag(null);
        this.mboundView0 = (com.google.android.material.card.MaterialCardView) bindings[0];
        this.mboundView0.setTag(null);
        this.textViewName.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new com.farukaygun.yorozuyalist.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.seasonalAnimeData == variableId) {
            setSeasonalAnimeData((com.farukaygun.yorozuyalist.model.anime.AnimeNode) variable);
        }
        else if (BR.listener == variableId) {
            setListener((com.farukaygun.yorozuyalist.adapter.ISeasonalAnimeClickListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setSeasonalAnimeData(@Nullable com.farukaygun.yorozuyalist.model.anime.AnimeNode SeasonalAnimeData) {
        this.mSeasonalAnimeData = SeasonalAnimeData;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.seasonalAnimeData);
        super.requestRebind();
    }
    public void setListener(@Nullable com.farukaygun.yorozuyalist.adapter.ISeasonalAnimeClickListener Listener) {
        this.mListener = Listener;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.listener);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.farukaygun.yorozuyalist.model.anime.AnimeNode seasonalAnimeData = mSeasonalAnimeData;
        com.farukaygun.yorozuyalist.model.anime.MainPicture seasonalAnimeDataMainPicture = null;
        java.lang.String seasonalAnimeDataMainPictureMedium = null;
        com.farukaygun.yorozuyalist.adapter.ISeasonalAnimeClickListener listener = mListener;
        java.lang.String seasonalAnimeDataTitle = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (seasonalAnimeData != null) {
                    // read seasonalAnimeData.mainPicture
                    seasonalAnimeDataMainPicture = seasonalAnimeData.getMainPicture();
                    // read seasonalAnimeData.title
                    seasonalAnimeDataTitle = seasonalAnimeData.getTitle();
                }


                if (seasonalAnimeDataMainPicture != null) {
                    // read seasonalAnimeData.mainPicture.medium
                    seasonalAnimeDataMainPictureMedium = seasonalAnimeDataMainPicture.getMedium();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            com.farukaygun.yorozuyalist.util.UtilKt.downloadImage(this.imageViewPicture, seasonalAnimeDataMainPictureMedium);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textViewName, seasonalAnimeDataTitle);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback2);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // seasonalAnimeData
        com.farukaygun.yorozuyalist.model.anime.AnimeNode seasonalAnimeData = mSeasonalAnimeData;
        // listener != null
        boolean listenerJavaLangObjectNull = false;
        // listener
        com.farukaygun.yorozuyalist.adapter.ISeasonalAnimeClickListener listener = mListener;



        listenerJavaLangObjectNull = (listener) != (null);
        if (listenerJavaLangObjectNull) {




            listener.onSeasonalAnimeClicked(callbackArg_0, seasonalAnimeData);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): seasonalAnimeData
        flag 1 (0x2L): listener
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}