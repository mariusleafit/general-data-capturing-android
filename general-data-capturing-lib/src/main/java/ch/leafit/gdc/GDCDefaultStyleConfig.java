package ch.leafit.gdc;

import ch.leafit.gdc.styles.*;

/**
 * Created by marius on 04/07/14.
 */
public class GDCDefaultStyleConfig {
    public static GDCDataFieldStyle<GDCClickDataField> clickDataFieldDefaultStyle;
    public static GDCDataFieldStyle<GDCDateDataField> dateDataFieldDefaultStyle;
    public static GDCDataFieldStyle<GDCIntegerDataField> integerDataFieldDefaultStyle;
    public static GDCDataFieldStyle<GDCListDataField> listDataFieldDefaultStyle;
    public static GDCDataFieldStyle<GDCSectionTitleDataField> sectionTitleDataFieldDefaultStyle;
    public static GDCDataFieldStyle<GDCStringDataField> stringDataFieldDefaultStyle;

    static {
        clickDataFieldDefaultStyle = new GDCClickDataFieldDefaultStyle();
        dateDataFieldDefaultStyle = new GDCDateDataFieldDefaultStyle();
        integerDataFieldDefaultStyle = new GDCIntegerDataFieldDefaultStyle();
        listDataFieldDefaultStyle = new GDCListDataFieldDefaultStyle();
        sectionTitleDataFieldDefaultStyle = new GDCSectionTitleDataFieldDefaultStyle();
        stringDataFieldDefaultStyle = new GDCStringDataFieldDefaultStyle();
    }

}
