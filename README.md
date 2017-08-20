# AreasImage-PhotoView
An android library that lets you define rectangular clickable areas in your images.

Description
===========

ClickableAreasImages is an android library that lets you define rectangular clickable areas in your images and associate different objects to it. The images are zoomable and orientation changes are also handled.

![demo image](https://raw.githubusercontent.com/Lukle/ClickableAreasImages/master/images/demo_image.png "Demo Image")

Sample Usage
========

```java
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

        // Plz using https://inkplant.com/tools/image-coordinates to define the coordinate

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
```

Download
========

Add it in your root build.gradle:

```gradle
    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

    dependencies {
	        compile 'com.github.Lukle:ClickableAreasImages:v0.1'
	}
```

Thanks to
=========
[ClickableAreasImages](https://github.com/Lukle/ClickableAreasImages), which serves as foundation for this library.

[PhotoView](https://github.com/chrisbanes/PhotoView), which serves as foundation for this library.

Contribution
=======

If you've found an error, please file an issue.

Patches and new samples are encouraged, and may be submitted by forking this project and submitting a pull request through GitHub.

