# AreasImage-PhotoView
An android library that lets you define rectangular clickable areas in your images.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![](https://jitpack.io/v/hantrungkien/AreasImage-PhotoView.svg)](https://jitpack.io/#hantrungkien/AreasImage-PhotoView)

Description
===========

ClickableAreasImages is an android library that lets you define rectangular clickable areas in your images and associate different objects to it. The images are zoomable and orientation changes are also handled.

<a><img src="./images/demo_image.png" width="200"></a>

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

	// set enable zoom by two finger or double tap
	clickableAreasImage.setEnableScalePicture(true);

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

        // Plz using http://getspritexy.com/ or Paint on the Window OS to define the coordinate

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
    
    public  class StateObj extends ClickableArea.State {

    private String name;

    public StateObj(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
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
	        compile 'com.github.hantrungkien:AreasImage-PhotoView:v1.1'
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

LICENCE
=======

    Copyright 2017 Kien Han Trung

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


