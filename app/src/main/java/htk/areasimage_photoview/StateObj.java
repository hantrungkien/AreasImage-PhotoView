package htk.areasimage_photoview;

import htk.areasimage.ClickableArea;

/**
 * Created by Lukas on 10/24/2015.
 */
public class StateObj extends ClickableArea.State {

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
