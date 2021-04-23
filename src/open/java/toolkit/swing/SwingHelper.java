package open.java.toolkit.swing;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class SwingHelper
{
    /**
     * Adds a JButton to a Window.
     * @param frame The Window to add the JButton to.
     * @param text The text of the JButton.
     * @param x The X coordinate of the JButton.
     * @param y The Y coordinate of the JButton.
     * @param width The width of the JButton.
     * @param height The height of the JButton.
     * @return The newly created JButton.
     */
    public static JButton addButton(Window frame, String text, int x, int y, int width, int height)
    {
        JButton btn = new JButton();
        btn.setText(text);
        btn.setBounds(x, y, width, height);

        frame.add(btn);
        return btn;
    }

    /**
     * Adds a JTextField to a Window.
     * @param frame The Window to add the JTextField to.
     * @param text The text of the JTextField.
     * @param editable Whether or not the JTextField is editable.
     * @param x The X coordinate of the JTextField.
     * @param y The Y coordinate of the JTextField.
     * @param width The width of the JTextField.
     * @param height The height of the JTextField.
     * @return The newly created JTextField.
     */
    public static JTextField addTextBox(Window frame, String text, boolean editable, int x, int y, int width, int height)
    {
        JTextField field = new JTextField();
        field.setText(text);
        field.setBounds(x, y, width, height);
        field.setEditable(editable);

        frame.add(field);
        return field;
    }

    /**
     * Adds a JLabel to a Window.
     * @param frame The Window to add the JLabel to.
     * @param text The text of the JLabel.
     * @param x The X coordinate of the JLabel.
     * @param y The Y coordinate of the JLabel.
     * @param width The width of the JLabel.
     * @param height The height of the JLabel.
     * @return The newly created JLabel.
     */
    public static JLabel addLabel(Window frame, String text, int x, int y, int width, int height)
    {
        JLabel label = new JLabel();
        label.setText(text);
        label.setBounds(x, y, width, height);

        frame.add(label);
        return label;
    }

    /**
     * Adds a JTextArea to a Window.
     * @param frame The Window to add the JTextArea to.
     * @param text The text of the JTextArea.
     * @param editable Whether or not the JTextArea is editable.
     * @param x The X coordinate of the JTextArea.
     * @param y The Y coordinate of the JTextArea.
     * @param width The width of the JTextArea.
     * @param height The height of the JTextArea.
     * @return The newly created JTextArea.
     */
    public static JTextArea addTextArea(Window frame, String text, boolean editable, int x, int y, int width, int height)
    {
        JTextArea box = new JTextArea();
        box.setText(text);
        box.setBounds(x, y, width, height);
        box.setEditable(editable);

        frame.add(box);
        return box;
    }

    /**
     * Adds a JComboBox to a Window.
     * @param frame The Window to add the JComboBox to.
     * @param x The X coordinate of the JComboBox.
     * @param y The Y coordinate of the JComboBox.
     * @param width The width of the JComboBox.
     * @param height The height of the JComboBox.
     * @return The newly created JComboBox.
     */
    public static JComboBox<Object> addComboBox(Window frame, int x, int y, int width, int height)
    {
        JComboBox<Object> box = new JComboBox<>();
        box.setBounds(x, y, width, height);

        frame.add(box);
        return box;
    }

    /**
     * Adds a JScrollPane to a Window.
     * @param frame The Window to add the JScrollPane to.
     * @param c The Component to apply the JScrollPane to.
     * @return The Component with the newly created JScrollPane.
     */
    public static Component addScrollPane(Window frame, Component c)
    {
        JScrollPane scroll = new JScrollPane(c);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(c.getBounds());

        frame.add(scroll);
        return c;
    }

    /**
     * Sets a policy to always update the default caret of a JTextArea.
     * @param area The JTextArea to apply the policy to.
     * @return The JTextArea with the newly set policy.
     */
    public static JTextArea alwaysUpdateCaret(JTextArea area)
    {
        DefaultCaret caret = (DefaultCaret)area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        area.setCaret(caret);
        return area;
    }

    /**
     * Creates a new JDialog.
     * @param dimension The dimensions of the JDialog.
     * @param title The title of the JDialog.
     * @return The newly created JDialog.
     */
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
