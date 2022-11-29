package com.farukaygun.yorozuyalist.databinding;
import com.farukaygun.yorozuyalist.R;
import com.farukaygun.yorozuyalist.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemSuggestedAnimeRecyclerBindingImpl extends ItemSuggestedAnimeRecyclerBinding implements com.farukaygun.yorozuyalist.generated.callback.OnClickListener.Listener {

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
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemSuggestedAnimeRecyclerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ItemSuggestedAnimeRecyclerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        mCallback1 = new com.farukaygun.yorozuyalist.generated.callback.OnClickListener(this, 1);
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
        if (BR.listener == variableId) {
            setListener((com.farukaygun.yorozuyalist.adapter.ISuggestedAnimeClickListener) variable);
        }
        else if (BR.suggestedAnimeData == variableId) {
            setSuggestedAnimeData((com.farukaygun.yorozuyalist.model.anime.AnimeNode) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setListener(@Nullable com.farukaygun.yorozuyalist.adapter.ISuggestedAnimeClickListener Listener) {
        this.mListener = Listener;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.listener);
        super.requestRebind();
    }
    public void setSuggestedAnimeData(@Nullable com.farukaygun.yorozuyalist.model.anime.AnimeNode SuggestedAnimeData) {
        this.mSuggestedAnimeData = SuggestedAnimeData;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.suggestedAnimeData);
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
        java.lang.String suggestedAnimeDataTitle = null;
        java.lang.String suggestedAnimeDataMainPictureMedium = null;
        com.farukaygun.yorozuyalist.model.anime.MainPicture suggestedAnimeDataMainPicture = null;
        com.farukaygun.yorozuyalist.adapter.ISuggestedAnimeClickListener listener = mListener;
        com.farukaygun.yorozuyalist.model.anime.AnimeNode suggestedAnimeData = mSuggestedAnimeData;

        if ((dirtyFlags & 0x6L) != 0) {



                if (suggestedAnimeData != null) {
                    // read suggestedAnimeData.title
                    suggestedAnimeDataTitle = suggestedAnimeData.getTitle();
                    // read suggestedAnimeData.mainPicture
                    suggestedAnimeDataMainPicture = suggestedAnimeData.getMainPicture();
                }


                if (suggestedAnimeDataMainPicture != null) {
                    // read suggestedAnimeData.mainPicture.medium
                    suggestedAnimeDataMainPictureMedium = suggestedAnimeDataMainPicture.getMedium();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            com.farukaygun.yorozuyalist.util.UtilKt.downloadImage(this.imageViewPicture, suggestedAnimeDataMainPictureMedium);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textViewName, suggestedAnimeDataTitle);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback1);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // listener != null
        boolean listenerJavaLangObjectNull = false;
        // listener
        com.farukaygun.yorozuyalist.adapter.ISuggestedAnimeClickListener listener = mListener;
        // suggestedAnimeData
        com.farukaygun.yorozuyalist.model.anime.AnimeNode suggestedAnimeData = mSuggestedAnimeData;



        listenerJavaLangObjectNull = (listener) != (null);
        if (listenerJavaLangObjectNull) {




            listener.onSuggestedAnimeClicked(callbackArg_0, suggestedAnimeData);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): listener
        flag 1 (0x2L): suggestedAnimeData
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}