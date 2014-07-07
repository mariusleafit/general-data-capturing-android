package ch.leafit.gdc.styles;

import android.view.animation.AlphaAnimation;
import ch.leafit.gdc.GDCDataField;

/**
 * Created by marius on 25/06/14.
 */
public abstract class GDCDataFieldStyle<T extends GDCDataField> {

    public void applyStyleToField(T field) {
         /*disable / enable*/
        if(field.isDisabled()) {
            field.mView.setClickable(false);
            field.mView.setEnabled(false);
            //field.mView.setAlpha((float)0.8);

            /*set alpha*/
            AlphaAnimation alpha = new AlphaAnimation(0.8F, 0.8F);
            alpha.setDuration(0); // Make animation instant
            alpha.setFillAfter(true); // Tell it to persist after the animation ends
            field.mView.startAnimation(alpha);
        } else {
            field.mView.setClickable(true);
            field.mView.setEnabled(true);
            //field.mView.setAlpha((float)1.0);
            /*set alpha*/
            AlphaAnimation alpha = new AlphaAnimation(1.0F, 1.0F);
            alpha.setDuration(0); // Make animation instant
            alpha.setFillAfter(true); // Tell it to persist after the animation ends
            field.mView.startAnimation(alpha);
        }
    }
}
