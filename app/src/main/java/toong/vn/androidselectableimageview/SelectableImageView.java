package toong.vn.androidselectableimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by PhanVanLinh on 13/01/2018.
 * phanvanlinh.94vn@gmail.com
 */

public class SelectableImageView extends AppCompatImageView {

    public SelectableImageView(Context context) {
        this(context, null);
    }

    public SelectableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SelectableImageView);
        try {
            Drawable pressedDrawable = getPressedDrawable(ta);
            Drawable selectedDrawable = getSelectedDrawable(ta);
            Drawable disableDrawable = getDisableDrawable(ta);
            if (pressedDrawable != null || selectedDrawable != null || disableDrawable != null) {
                StateListDrawable stateList = new StateListDrawable();
                if (pressedDrawable != null) {
                    stateList.addState(new int[] { android.R.attr.state_pressed }, pressedDrawable);
                }
                if (selectedDrawable != null) {
                    stateList.addState(new int[] { android.R.attr.state_selected },
                            selectedDrawable);
                }
                if (disableDrawable != null) {
                    stateList.addState(new int[] { -android.R.attr.state_enabled },
                            disableDrawable);
                }
                stateList.addState(new int[] {}, getDrawable()); // must add it at last of all state
                setImageDrawable(stateList);
            }
        } finally {
            ta.recycle();
        }
    }

    @Nullable
    private Drawable getPressedDrawable(TypedArray ta) {
        int drawableId = ta.getResourceId(R.styleable.SelectableImageView_siv_srcPressed, 0);
        if (drawableId == 0) {
            return null;
        }
        return AppCompatResources.getDrawable(getContext(), drawableId);
    }

    @Nullable
    private Drawable getSelectedDrawable(TypedArray ta) {
        int drawableId = ta.getResourceId(R.styleable.SelectableImageView_siv_srcSelected, 0);
        if (drawableId == 0) {
            return null;
        }
        return AppCompatResources.getDrawable(getContext(), drawableId);
    }

    private Drawable getDisableDrawable(TypedArray ta) {
        int drawableId = ta.getResourceId(R.styleable.SelectableImageView_siv_srcDisable, 0);
        if (drawableId == 0) {
            return null;
        }
        return AppCompatResources.getDrawable(getContext(), drawableId);
    }
}
