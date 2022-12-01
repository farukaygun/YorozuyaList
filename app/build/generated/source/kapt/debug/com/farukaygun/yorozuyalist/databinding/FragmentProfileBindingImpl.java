package com.farukaygun.yorozuyalist.databinding;
import com.farukaygun.yorozuyalist.R;
import com.farukaygun.yorozuyalist.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentProfileBindingImpl extends FragmentProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imageViewPicture, 1);
        sViewsWithIds.put(R.id.textUsername, 2);
        sViewsWithIds.put(R.id.textBirthday, 3);
        sViewsWithIds.put(R.id.textLocation, 4);
        sViewsWithIds.put(R.id.textJoinedDate, 5);
        sViewsWithIds.put(R.id.donutProgressView, 6);
        sViewsWithIds.put(R.id.textWatching, 7);
        sViewsWithIds.put(R.id.textOnHold, 8);
        sViewsWithIds.put(R.id.textDropped, 9);
        sViewsWithIds.put(R.id.textCompleted, 10);
        sViewsWithIds.put(R.id.textPlanToWatch, 11);
        sViewsWithIds.put(R.id.linearLayoutOtherStatistics, 12);
        sViewsWithIds.put(R.id.textDay, 13);
        sViewsWithIds.put(R.id.textEpisodes, 14);
        sViewsWithIds.put(R.id.textMeanScore, 15);
    }
    // views
    @NonNull
    private final androidx.appcompat.widget.LinearLayoutCompat mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private FragmentProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (app.futured.donut.DonutProgressView) bindings[6]
            , (com.google.android.material.imageview.ShapeableImageView) bindings[1]
            , (androidx.appcompat.widget.LinearLayoutCompat) bindings[12]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[7]
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