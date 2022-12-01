package com.farukaygun.yorozuyalist.databinding;
import com.farukaygun.yorozuyalist.R;
import com.farukaygun.yorozuyalist.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.linearLayoutButtons, 1);
        sViewsWithIds.put(R.id.buttonAnimeRanking, 2);
        sViewsWithIds.put(R.id.buttonMangaRanking, 3);
        sViewsWithIds.put(R.id.relativeLayoutSeasonal, 4);
        sViewsWithIds.put(R.id.textViewSeasonal, 5);
        sViewsWithIds.put(R.id.recyclerViewSeasonalAnime, 6);
        sViewsWithIds.put(R.id.progressBarSeasonal, 7);
        sViewsWithIds.put(R.id.linearLayoutSuggested, 8);
        sViewsWithIds.put(R.id.textViewSuggested, 9);
        sViewsWithIds.put(R.id.recyclerViewSuggestedAnime, 10);
        sViewsWithIds.put(R.id.progressBarSuggested, 11);
    }
    // views
    @NonNull
    private final androidx.appcompat.widget.LinearLayoutCompat mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[2]
            , (android.widget.Button) bindings[3]
            , (androidx.appcompat.widget.LinearLayoutCompat) bindings[1]
            , (android.widget.RelativeLayout) bindings[8]
            , (com.google.android.material.progressindicator.CircularProgressIndicator) bindings[7]
            , (com.google.android.material.progressindicator.CircularProgressIndicator) bindings[11]
            , (androidx.recyclerview.widget.RecyclerView) bindings[6]
            , (androidx.recyclerview.widget.RecyclerView) bindings[10]
            , (android.widget.RelativeLayout) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[9]
            );
        this.mboundView0 = (androidx.appcompat.widget.LinearLayoutCompat) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}