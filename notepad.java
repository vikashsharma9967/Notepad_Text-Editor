package com.company;

import java.awt.print.PrinterException;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Notepad extends JFrame implements ActionListener{
    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");

    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("open");
    JMenuItem saveFile = new JMenuItem("save");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem print = new JMenuItem("Print");


    JMenuItem cut = new JMenuItem("cut");
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectAll = new JMenuItem("SelectAll");


    JMenuItem about = new JMenuItem("About");


    JTextArea textArea = new JTextArea();



    public Notepad(){
        setTitle("Notepad Application");
        setBounds(100,100,800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("download.jpg"));
        setIconImage(icon.getImage());

        setJMenuBar(menubar);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);


        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(exit);
        file.add(print);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);


        help.add(about);


        JScrollPane scrollpane = new JScrollPane(textArea);
        add(scrollpane);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(scrollpane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());

        textArea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        about.addActionListener(this);


        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,KeyEvent.CTRL_DOWN_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,KeyEvent.CTRL_DOWN_MASK));






    }

    public static void main(String[] args) throws Exception{

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new Notepad().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equalsIgnoreCase("New")){

            textArea.setText(null);

        }else if(e.getActionCommand().equalsIgnoreCase("Open")){



            JFileChooser fileChooser =new JFileChooser();
            FileNameExtensionFilter  textFilter = new FileNameExtensionFilter("Only Text Files(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action = fileChooser.showOpenDialog(null);

            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }else {
                try {
                    BufferedReader reader = new BufferedReader((new FileReader(fileChooser.getSelectedFile())));
                    textArea.read(reader,null);
                } catch(IOException ex){
                    ex.printStackTrace();
                }

            }




        }else if(e.getActionCommand().equalsIgnoreCase("save")){
            JFileChooser fileChooser =new JFileChooser();
            FileNameExtensionFilter  textFilter = new FileNameExtensionFilter("Only Text Files(.txt)","txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action = fileChooser.showSaveDialog(null);
            if(action != JFileChooser.APPROVE_OPTION){
                return ;
            }else{
                String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
                if(!fileName.contains(".txt"))
                    fileName = fileName +".txt";
                try {
                    BufferedWriter writer = new BufferedWriter((new FileWriter(fileName)));
                    textArea.write(writer);
                } catch(IOException ex){
                    ex.printStackTrace();
                }
            }

        }else if(e.getActionCommand().equalsIgnoreCase("print")){

            try {
                textArea.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE,null,ex);
            }

        }else if(e.getActionCommand().equalsIgnoreCase("exit")){

            System.exit(0);

        }else if(e.getActionCommand().equalsIgnoreCase("cut")){

            textArea.cut();

        }else if(e.getActionCommand().equalsIgnoreCase("copy")){

            textArea.copy();

        }else if(e.getActionCommand().equalsIgnoreCase("paste")){
            textArea.paste();

        }else if(e.getActionCommand().equalsIgnoreCase("select All")){
            textArea.selectAll();

        }else if(e.getActionCommand().equalsIgnoreCase("About")){

            new About().setVisible(true);

        }

    }
}
