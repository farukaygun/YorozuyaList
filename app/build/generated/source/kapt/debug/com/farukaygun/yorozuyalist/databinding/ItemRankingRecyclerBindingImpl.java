package com.farukaygun.yorozuyalist.databinding;
import com.farukaygun.yorozuyalist.R;
import com.farukaygun.yorozuyalist.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemRankingRecyclerBindingImpl extends ItemRankingRecyclerBinding implements com.farukaygun.yorozuyalist.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearLayoutCompat, 6);
    }
    // views
    @NonNull
    private final com.google.android.material.card.MaterialCardView mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemRankingRecyclerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private ItemRankingRecyclerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.imageview.ShapeableImageView) bindings[1]
            , (androidx.appcompat.widget.LinearLayoutCompat) bindings[6]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            );
        this.imageView.setTag(null);
        this.mboundView0 = (com.google.android.material.card.MaterialCardView) bindings[0];
        this.mboundView0.setTag(null);
        this.textViewEpisodes.setTag(null);
        this.textViewName.setTag(null);
        this.textViewPoint.setTag(null);
        this.textViewPopularity.setTag(null);
        setRootTag(root);
        // listeners
        mCallback3 = new com.farukaygun.yorozuyalist.generated.callback.OnClickListener(this, 1);
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
        if (BR.rankingAnimeData == variableId) {
            setRankingAnimeData((com.farukaygun.yorozuyalist.model.anime.Node) variable);
        }
        else if (BR.listener == variableId) {
            setListener((com.farukaygun.yorozuyalist.adapter.IRankingAnimeClickListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setRankingAnimeData(@Nullable com.farukaygun.yorozuyalist.model.anime.Node RankingAnimeData) {
        this.mRankingAnimeData = RankingAnimeData;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.rankingAnimeData);
        super.requestRebind();
    }
    public void setListener(@Nullable com.farukaygun.yorozuyalist.adapter.IRankingAnimeClickListener Listener) {
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
        java.lang.String rankingAnimeDataMean = null;
        java.lang.String rankingAnimeDataNumListUsers = null;
        com.farukaygun.yorozuyalist.model.anime.Node rankingAnimeData = mRankingAnimeData;
        com.farukaygun.yorozuyalist.adapter.IRankingAnimeClickListener listener = mListener;
        java.lang.String rankingAnimeDataNumEpisodes = null;
        com.farukaygun.yorozuyalist.model.anime.MainPicture rankingAnimeDataMainPicture = null;
        java.lang.String rankingAnimeDataTitle = null;
        java.lang.String rankingAnimeDataMainPictureMedium = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (rankingAnimeData != null) {
                    // read rankingAnimeData.mean
                    rankingAnimeDataMean = rankingAnimeData.getMean();
                    // read rankingAnimeData.numListUsers
                    rankingAnimeDataNumListUsers = rankingAnimeData.getNumListUsers();
                    // read rankingAnimeData.numEpisodes
                    rankingAnimeDataNumEpisodes = rankingAnimeData.getNumEpisodes();
                    // read rankingAnimeData.mainPicture
                    rankingAnimeDataMainPicture = rankingAnimeData.getMainPicture();
                    // read rankingAnimeData.title
                    rankingAnimeDataTitle = rankingAnimeData.getTitle();
                }


                if (rankingAnimeDataMainPicture != null) {
                    // read rankingAnimeData.mainPicture.medium
                    rankingAnimeDataMainPictureMedium = rankingAnimeDataMainPicture.getMedium();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            com.farukaygun.yorozuyalist.util.UtilKt.downloadImage(this.imageView, rankingAnimeDataMainPictureMedium);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textViewEpisodes, rankingAnimeDataNumEpisodes);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textViewName, rankingAnimeDataTitle);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textViewPoint, rankingAnimeDataMean);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.textViewPopularity, rankingAnimeDataNumListUsers);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback3);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // rankingAnimeData
        com.farukaygun.yorozuyalist.model.anime.Node rankingAnimeData = mRankingAnimeData;
        // listener != null
        boolean listenerJavaLangObjectNull = false;
        // listener
        com.farukaygun.yorozuyalist.adapter.IRankingAnimeClickListener listener = mListener;



        listenerJavaLangObjectNull = (listener) != (null);
        if (listenerJavaLangObjectNull) {




            listener.onRankingAnimeClicked(callbackArg_0, rankingAnimeData);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): rankingAnimeData
        flag 1 (0x2L): listener
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}