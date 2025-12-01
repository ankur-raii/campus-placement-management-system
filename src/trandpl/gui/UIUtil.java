package trandpl.gui;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Common UI helper methods.
 */
public class UIUtil {

    private static final String LOGO_PATH = "/images/transparent logo.png";

    /**
     * Set the application logo on the given label, scaling it to a standard size
     * so that even a large source image (e.g. 985x346) fits nicely in the header.
     */
    public static void setAppLogo(JLabel label) {
        // Target size matches existing header label bounds in the forms
        int width = 187;
        int height = 56;

        ImageIcon baseIcon = new ImageIcon(UIUtil.class.getResource(LOGO_PATH));
        Image scaled = baseIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaled));
    }
}
