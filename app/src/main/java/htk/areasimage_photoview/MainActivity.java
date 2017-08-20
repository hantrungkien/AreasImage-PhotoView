package htk.areasimage_photoview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import htk.areasimage.ClickableArea;
import htk.areasimage.ClickableAreasImage;
import htk.areasimage.OnClickableAreaClickedListener;

public class MainActivity extends AppCompatActivity implements OnClickableAreaClickedListener {

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = findViewById(R.id.view);

        // Add image
        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.austria_map);

        // Create your image
        ClickableAreasImage clickableAreasImage = new ClickableAreasImage(image, this);

        // Define your clickable area (pixel values: x coordinate, y coordinate, width, height) and assign an object to it
        List<ClickableArea> clickableAreas = getClickableAreas();
        clickableAreasImage.setClickableAreas(clickableAreas);
    }

    // Listen for touches on your images:
    @Override
    public void onClickableAreaTouched(ClickableArea.State item, float positionXOnDrawable, float positionYOnDrawable
            , float positionXOnScreen, float positionYOnScreen) {
        if (item instanceof StateObj) {
            String text = ((StateObj) item).getName();

            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

            ViewPropertyObjectAnimator.animate(mView)
                    .leftMargin((int) positionXOnScreen)
                    .topMargin((int) positionYOnScreen)
                    .setDuration(200).start();
        }
    }

    @NonNull
    private List<ClickableArea> getClickableAreas() {

        List<ClickableArea> clickableAreas = new ArrayList<>();

        // https://inkplant.com/tools/image-coordinates

        clickableAreas.add(new ClickableArea(600, 100, 50, 50, new StateObj("Lower Austria")));
        clickableAreas.add(new ClickableArea(440, 125, 50, 50, new StateObj("Upper Austria")));
        clickableAreas.add(new ClickableArea(700, 126, 50, 50, new StateObj("Vienna")));

        clickableAreas.add(new ClickableArea(685, 270, 50, 50, new StateObj("Burgenland")));
        clickableAreas.add(new ClickableArea(420, 350, 50, 50, new StateObj("Carinthia")));
        clickableAreas.add(new ClickableArea(370, 245, 50, 50, new StateObj("Salzburg")));

        clickableAreas.add(new ClickableArea(170, 280, 50, 50, new StateObj("Tyrol")));
        clickableAreas.add(new ClickableArea(30, 280, 50, 50, new StateObj("Vorarlberg")));
        clickableAreas.add(new ClickableArea(570, 250, 50, 50, new StateObj("Styria")));

        return clickableAreas;
    }
}
