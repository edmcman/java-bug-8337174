import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.util.logging.ConsoleHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DragAndDropExample extends JFrame {

    public DragAndDropExample() {
        setTitle("Drag and Drop Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea sourceTextArea = new JTextArea("Drag this text");
        sourceTextArea.setDragEnabled(true);
        sourceTextArea.setLineWrap(true);
        sourceTextArea.setWrapStyleWord(true);
        JScrollPane sourceScrollPane = new JScrollPane(sourceTextArea);

        JTextArea targetTextArea = new JTextArea("Drop here");
        targetTextArea.setLineWrap(true);
        targetTextArea.setWrapStyleWord(true);
        JScrollPane targetScrollPane = new JScrollPane(targetTextArea);

        new DropTarget(targetTextArea, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    dtde.acceptDrag(DnDConstants.ACTION_COPY);
                    System.out.println("Drag Enter: Data flavor supported");
                } else {
                    dtde.rejectDrag();
                    System.out.println("Drag Enter: Data flavor not supported");
                }
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {
                // No action needed here
            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
                // No action needed here
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
                System.out.println("Drag Exit");
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    if (dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY);
                        String droppedText = (String) dtde.getTransferable().getTransferData(DataFlavor.stringFlavor);
                        targetTextArea.append(droppedText);
                        dtde.dropComplete(true);
                        System.out.println("Drop: Success");
                    } else {
                        dtde.rejectDrop();
                        System.out.println("Drop: Data flavor not supported");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    dtde.dropComplete(false);
                    System.out.println("Drop: Exception occurred");
                }
            }
        });

        setLayout(new GridLayout(2, 1));
        add(sourceScrollPane);
        add(targetScrollPane);
    }

    public static void main(String[] args) {

        Logger platformLogger = Logger.getLogger("sun.awt.X11.xembed.xdnd.XDnDDropSourceProtocol");
        platformLogger.setLevel(Level.ALL);
        
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        platformLogger.addHandler(consoleHandler);

        SwingUtilities.invokeLater(() -> {
            new DragAndDropExample().setVisible(true);
        });
    }
}
