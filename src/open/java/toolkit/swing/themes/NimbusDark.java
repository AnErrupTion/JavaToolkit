package open.java.toolkit.swing.themes;

import open.java.toolkit.Errors;

import javax.swing.*;
import java.awt.*;

public class NimbusDark
{
    /**
     * Configures the NimbusDark there.
     * @param frame The JFrame to configure the theme to.
     */
    public static void configure(JFrame frame)
    {
        UIManager.put("control", new Color(60, 60, 60));
        frame.getContentPane().setBackground((Color) UIManager.get("control"));
        UIManager.put("info", UIManager.get("control"));
        UIManager.put("nimbusBase", new Color(18, 30, 49));
        UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
        UIManager.put("nimbusDisabledText", new Color(128, 128, 128));
        UIManager.put("nimbusFocus", new Color(115,164,209));
        UIManager.put("nimbusGreen", new Color(176,179,50));
        UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
        UIManager.put("nimbusLightBackground", new Color(58, 30, 49));
        UIManager.put("nimbusOrange", new Color(191,98,4));
        UIManager.put("nimbusRed", new Color(169,46,34));
        UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
        UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
        UIManager.put("text", new Color(230, 230, 230));
        UIManager.put("TextField.font", new Font(Font.SANS_SERIF, Font.PLAIN, 11));

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            if (info.getName().equals("Nimbus"))
            {
                try
                {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) { Errors.newError(ex); }

                break;
            }
    }
}
