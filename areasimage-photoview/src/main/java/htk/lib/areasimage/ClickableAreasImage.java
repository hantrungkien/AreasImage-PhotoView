package htk.lib.areasimage;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import htk.lib.photoview.PhotoViewAttacher;


/**
 * Created by Lukas on 10/22/2015.
 */
public class ClickableAreasImage implements PhotoViewAttacher.OnPhotoTapListener {

    private PhotoViewAttacher attacher;
    private OnClickableAreaClickedListener listener;

    private List<ClickableArea> clickableAreas;

    private int imageWidthInPx;
    private int imageHeightInPx;

    public ClickableAreasImage(ImageView imageView, OnClickableAreaClickedListener listener) {
        this.attacher = new PhotoViewAttacher(imageView);
        init(listener);
    }

    private void init(OnClickableAreaClickedListener listener) {
        this.listener = listener;
        getImageDimensions(attacher.getImageView());
        attacher.setOnPhotoTapListener(this);
    }


    private void getImageDimensions(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        imageWidthInPx = (int) (drawable.getBitmap().getWidth() / Resources.getSystem().getDisplayMetrics().density);
        imageHeightInPx = (int) (drawable.getBitmap().getHeight() / Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onPhotoTap(View view, float positionXOnDrawable, float positionYOnDrawable
            , float positionXOnScreen, float positionYOnScreen) {
        PixelPosition pixel = ImageUtils.getPixelPosition(positionXOnDrawable, positionYOnDrawable, imageWidthInPx, imageHeightInPx);


        List<ClickableArea> clickableAreas = getClickAbleAreas(pixel.getX(), pixel.getY());
        for (ClickableArea ca : clickableAreas) {
            listener.onClickableAreaTouched(ca.getItem(), positionXOnDrawable, positionYOnDrawable, positionXOnScreen, positionYOnScreen);
        }
    }

    private List<ClickableArea> getClickAbleAreas(int x, int y) {
        List<ClickableArea> clickableAreas = new ArrayList<>();
        for (ClickableArea ca : getClickableAreas()) {
            if (isBetween(ca.getX(), (ca.getX() + ca.getW()), x)) {
                if (isBetween(ca.getY(), (ca.getY() + ca.getH()), y)) {
                    clickableAreas.add(ca);
                }
            }
        }
        return clickableAreas;
    }

    public void setEnableScalePicture(boolean isEnableScale) {
        attacher.setEnableScale(isEnableScale);
    }

    private boolean isBetween(int start, int end, int actual) {
        return (start <= actual && actual <= end);
    }

    public void setClickableAreas(List<ClickableArea> clickableAreas) {
        this.clickableAreas = clickableAreas;
    }

    public List<ClickableArea> getClickableAreas() {
        return clickableAreas;
    }
}
