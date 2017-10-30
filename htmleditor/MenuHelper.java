package htmleditor;

import htmleditor.actions.*;
import htmleditor.listeners.TextEditMenuListener;
import htmleditor.listeners.UndoMenuListener;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuHelper {
    public static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        parent.add(menuItem);
        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, String text, Action action) {
        JMenuItem menuItem = addMenuItem(parent, action);
        menuItem.setText(text);
        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, Action action) {
        JMenuItem menuItem = new JMenuItem(action);
        parent.add(menuItem);
        return menuItem;
    }

    public static void initHelpMenu(View view, JMenuBar menuBar) {
        JMenu helpMenu = new JMenu("help");
        menuBar.add(helpMenu);

        addMenuItem(helpMenu, "about", view);
    }

    public static void initFontMenu(View view, JMenuBar menuBar) {
        JMenu fontMenu = new JMenu("font");
        menuBar.add(fontMenu);

        JMenu fontTypeMenu = new JMenu("font");
        fontMenu.add(fontTypeMenu);

        String[] fontTypes = {Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED, Font.DIALOG, Font.DIALOG_INPUT};
        for (String fontType : fontTypes) {
            addMenuItem(fontTypeMenu, fontType, new StyledEditorKit.FontFamilyAction(fontType, fontType));
        }

        JMenu fontSizeMenu = new JMenu("font size");
        fontMenu.add(fontSizeMenu);

        String[] fontSizes = {"6", "8", "10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
        for (String fontSize : fontSizes) {
            addMenuItem(fontSizeMenu, fontSize, new StyledEditorKit.FontSizeAction(fontSize, Integer.parseInt(fontSize)));
        }

        fontMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initColorMenu(View view, JMenuBar menuBar) {
        JMenu colorMenu = new JMenu("color");
        menuBar.add(colorMenu);

        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("red", Color.red));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("orange", Color.orange));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("yellow", Color.yellow));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("green", Color.green));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("blue", Color.blue));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("white blue", Color.cyan));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("purple", Color.magenta));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("black", Color.black));

        colorMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initAlignMenu(View view, JMenuBar menuBar) {
        JMenu alignMenu = new JMenu("alignment");
        menuBar.add(alignMenu);

        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("left side", StyleConstants.ALIGN_LEFT));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("center side", StyleConstants.ALIGN_CENTER));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("right side", StyleConstants.ALIGN_RIGHT));

        alignMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initStyleMenu(View view, JMenuBar menuBar) {
        JMenu styleMenu = new JMenu("style");
        menuBar.add(styleMenu);

        addMenuItem(styleMenu, "bold", new StyledEditorKit.BoldAction());
        addMenuItem(styleMenu, "underlined", new StyledEditorKit.UnderlineAction());
        addMenuItem(styleMenu, "italics", new StyledEditorKit.ItalicAction());

        styleMenu.addSeparator();

        addMenuItem(styleMenu, "subscript", new SubscriptAction());
        addMenuItem(styleMenu, "superscript", new SuperscriptAction());
        addMenuItem(styleMenu, "strikethrough", new StrikeThroughAction());

        styleMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initEditMenu(View view, JMenuBar menuBar) {
        JMenu editMenu = new JMenu("edit");
        menuBar.add(editMenu);

        JMenuItem undoItem = addMenuItem(editMenu, "undo", new UndoAction(view));
        JMenuItem redoItem = addMenuItem(editMenu, "redo", new RedoAction(view));
        addMenuItem(editMenu, "cut", new DefaultEditorKit.CutAction());
        addMenuItem(editMenu, "copy", new DefaultEditorKit.CopyAction());
        addMenuItem(editMenu, "paste", new DefaultEditorKit.PasteAction());

        editMenu.addMenuListener(new UndoMenuListener(view, undoItem, redoItem));
    }

    public static void initFileMenu(View view, JMenuBar menuBar) {
        JMenu fileMenu = new JMenu("file");
        menuBar.add(fileMenu);

        addMenuItem(fileMenu, "new", view);
        addMenuItem(fileMenu, "open", view);
        addMenuItem(fileMenu, "save", view);
        addMenuItem(fileMenu, "save as...", view);
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "exit", view);
    }
}
