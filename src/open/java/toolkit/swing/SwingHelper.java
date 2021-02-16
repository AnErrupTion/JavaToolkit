package open.java.toolkit.swing;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class SwingHelper
{
    public static JButton addButton(Window frame, String text, int x, int y, int width, int height)
    {
        JButton btn = new JButton();
        btn.setText(text);
        btn.setBounds(x, y, width, height);

        frame.add(btn);
        return btn;
    }

    public static JTextField addTextBox(Window frame, String text, boolean editable, int x, int y, int width, int height)
    {
        JTextField field = new JTextField();
        field.setText(text);
        field.setBounds(x, y, width, height);
        field.setEditable(editable);

        frame.add(field);
        return field;
    }

    public static JLabel addLabel(Window frame, String text, int x, int y, int width, int height)
    {
        JLabel label = new JLabel();
        label.setText(text);
        label.setBounds(x, y, width, height);

        frame.add(label);
        return label;
    }

    public static JTextArea addTextArea(Window frame, String text, boolean editable, int x, int y, int width, int height)
    {
        JTextArea box = new JTextArea();
        box.setText(text);
        box.setBounds(x, y, width, height);
        box.setEditable(editable);

        frame.add(box);
        return box;
    }

    public static JComboBox<Object> addComboBox(Window frame, int x, int y, int width, int height)
    {
        JComboBox<Object> box = new JComboBox<>();
        box.setBounds(x, y, width, height);

        frame.add(box);
        return box;
    }

    public static Component addScrollPane(Window frame, Component c)
    {
        JScrollPane scroll = new JScrollPane(c);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(c.getBounds());

        frame.add(scroll);
        return c;
    }

    public static JTextArea alwaysUpdateCaret(JTextArea area)
    {
        DefaultCaret caret = (DefaultCaret)area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        area.setCaret(caret);
        return area;
    }

    public static JDialog createNewDialog(Dimension dimension, String title)
    {
        JDialog dialog = new JDialog();
        dialog.setMinimumSize(dimension);
        dialog.setMaximumSize(dimension);
        dialog.setPreferredSize(dimension);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        dialog.setResizable(false);
        dialog.setLayout(null);
        dialog.setTitle(title);

        return dialog;
    }
}
