package ch.leafit.gdc.callback;

/**
 * Created by marius on 26/06/14.
 */
public  interface GDCDataFieldCallback<T> {
    public void valueChanged(int tag, T value);
}
