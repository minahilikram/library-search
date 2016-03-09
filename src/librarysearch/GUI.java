package librarysearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame {
    
    Container pane;
    CardLayout layout;
    static String selectedYear = "";
    static String selectedType = "";
    static String selectedYear2 = "";
    static String selectedYear3 = "";
    static String selectedYear4 = "";

    public GUI() {
        super("Library Search");
    	layout = new CardLayout();
    	setLayout(layout);
        
        pane = this.getContentPane();
    	
        JPanel newPanel;
    	newPanel = mainPanel();
    	pane.add("Main", newPanel);
        
    	JPanel addPanel;
    	addPanel = addPanel();
    	pane.add("Add", addPanel);
        
        JPanel searchPanel;
    	searchPanel = searchPanel();
    	pane.add("Search", searchPanel);
        
        JPanel printPanel;
    	printPanel = printPanel();
    	pane.add("Print", printPanel);
        
        JPanel deletePanel;
    	deletePanel = deletePanel();
    	pane.add("Delete", deletePanel);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Commands");
        JMenuItem menuItem;
        
        menuBar.setBackground(Color.white);
        menuBar.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        menuBar.add(menu);
        menu.setBackground(Color.white);
        
        menuItem = new JMenuItem("Add", KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                layout.show(pane, "Add"); 
            }
        });
        menuItem.setBackground(Color.white);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                LibrarySearch.save();
                layout.show(pane, "Main"); 
            }
        });
        menuItem.setBackground(Color.white);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Search", KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                layout.show(pane, "Search"); 
            }
        });
        menuItem.setBackground(Color.white);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Print", KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                printPanel();
                layout.show(pane, "Print");
            }
        });
        menuItem.setBackground(Color.white);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Delete", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                layout.show(pane, "Delete"); 
            }
        });
        menuItem.setBackground(Color.white);
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        menuItem.setBackground(Color.white);
        menu.add(menuItem);
        
        setTitle("Library Search");  
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(screenSize.width-900, screenSize.height-300));
        
    }
    
    public static JPanel mainPanel() {
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        
        main.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 160, 210), 1, true), "Welcome to Library Search", TitledBorder.CENTER, TitledBorder.TOP));
        main.setBackground(Color.white);
        
        JTextArea text = new JTextArea();
        text.setText("Choose a command from the 'Commands' menu.");
        text.setEditable(false);
        main.add(text);
        
        return main;
    }
    
    public static JPanel printPanel() {
        JPanel print = new JPanel(new BorderLayout());
        JPanel both = new JPanel(null);
        
        print.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 160, 210), 1, true), "Print", TitledBorder.LEFT, TitledBorder.TOP));
        print.setBackground(Color.white);
        
        JTextArea tBoth = new JTextArea();
        tBoth.setText(LibrarySearch.printAll());
        tBoth.setEditable(false);
        tBoth.setBackground(new Color(252, 252, 255));
        
        JScrollPane sBoth = new JScrollPane(both.add(tBoth));
        
        sBoth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createDashedBorder(new Color(225, 225, 255), 1, 5, 5, true), "", TitledBorder.CENTER, TitledBorder.TOP));
        sBoth.setBackground(Color.white);
        
        print.add(sBoth);
        
        return print;
    }
   
    public static JPanel deletePanel() {
         final JPanel edit = new JPanel(new BorderLayout());
        edit.setLayout(null);
        edit.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 160, 210), 1, true), "Search", TitledBorder.LEFT, TitledBorder.TOP));
        edit.setBackground(Color.white);
        
        Integer yearA[] = new Integer[375];
        int y = 1640;
        
        for (int i=0; i<375; i++) {
            yearA[i] = y;
            y++;
        }
        
        JLabel callNumber = new JLabel("Call No: ");
        callNumber.setBounds(10, 15, 100, 20);
        edit.add(callNumber);
        final JTextField cField = new JTextField();
        cField.setBounds(70, 15, 100, 20);
        cField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        edit.add(cField);
        
        JLabel title = new JLabel("Title: ");
        title.setBounds(10, 45, 150, 20);
        edit.add(title);
        final JTextField tField = new JTextField();
        tField.setBounds(70, 45, 150, 20);
        tField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        edit.add(tField);
        
        JLabel year = new JLabel("Year: ");
        year.setBounds(10, 75, 75, 20);
        edit.add(year);
        final JComboBox yChooser = new JComboBox(yearA);
        yChooser.setBounds(70, 75, 75, 20);
        yChooser.setBackground(Color.white);
        yChooser.setForeground(new Color(0, 160, 210));
        yChooser.setToolTipText("Type in the value for the year");
        yChooser.setSelectedItem(1950);
        selectedYear3 = "1950";
        edit.add(yChooser);
        yChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer sYear = (Integer)yChooser.getSelectedItem();
                selectedYear3 = String.valueOf(sYear);
            }
        });
        
        JLabel and = new JLabel("-");
        and.setBounds(150, 75, 75, 20);
        and.setToolTipText("Leave blank if needed");
        edit.add(and);
        
        final JComboBox yChooser2 = new JComboBox(yearA);
        yChooser2.setBounds(160, 75, 75, 20);
        yChooser2.setBackground(Color.white);
        yChooser2.setForeground(new Color(0, 160, 210));
        yChooser2.setToolTipText("Type in the value for the year");
        yChooser2.setSelectedItem(2013);
        selectedYear4 = "2013";
        edit.add(yChooser2);
        yChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer sYear = (Integer)yChooser.getSelectedItem();
                selectedYear4 = String.valueOf(sYear);
            }
        });
        
        JLabel n = new JLabel("Results: ");
        n.setBounds(10, 105, 70, 20);
        edit.add(n);
        final JTextArea nField = new JTextArea();
        nField.setEditable(false);
        nField.setBackground(new Color(235, 235, 210));
        JScrollPane scroll = new JScrollPane(nField);
        scroll.setBounds(10, 125, 375, 125);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        scroll.setBackground(new Color(235, 235, 210));
        edit.add(scroll);
        
        JButton rButton = new JButton("Reset");
        rButton.setBounds(250, 32, 75, 20);
        rButton.setBackground(Color.white);
        rButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.white, Color.white, new Color(0, 160, 210), new Color(0, 160, 210)), BorderFactory.createEmptyBorder()));
        edit.add(rButton);
        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                cField.setText(null);
                tField.setText(null);
                yChooser.setSelectedItem(1950);
                yChooser2.setSelectedItem(2013);
                selectedYear3 = "1950";
                selectedYear4 = "2013";
                nField.setText(null);
                nField.setBackground(new Color(235, 235, 210));
            }
        });
        
        JButton eButton = new JButton("Search");
        eButton.setBounds(250, 63, 75, 20);
        eButton.setBackground(Color.white);
        eButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.white, Color.white, new Color(0, 160, 210), new Color(0, 160, 210)), BorderFactory.createEmptyBorder()));
        edit.add(eButton);
        eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String callNumner = cField.getText();
                String title = tField.getText();
                nField.setBackground(Color.white);
                nField.append(LibrarySearch.delete(title, callNumner, selectedYear3+"-"+selectedYear4));
            }
        });
        
        
        return edit;
    }
    public static JPanel searchPanel() {
        final JPanel edit = new JPanel(new BorderLayout());
        edit.setLayout(null);
        edit.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 160, 210), 1, true), "Search", TitledBorder.LEFT, TitledBorder.TOP));
        edit.setBackground(Color.white);
        
        Integer yearA[] = new Integer[375];
        int y = 1640;
        
        for (int i=0; i<375; i++) {
            yearA[i] = y;
            y++;
        }
        
        JLabel callNumber = new JLabel("Call No: ");
        callNumber.setBounds(10, 15, 100, 20);
        edit.add(callNumber);
        final JTextField cField = new JTextField();
        cField.setBounds(70, 15, 100, 20);
        cField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        edit.add(cField);
        
        JLabel title = new JLabel("Title: ");
        title.setBounds(10, 45, 150, 20);
        edit.add(title);
        final JTextField tField = new JTextField();
        tField.setBounds(70, 45, 150, 20);
        tField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        edit.add(tField);
        
        JLabel year = new JLabel("Year: ");
        year.setBounds(10, 75, 75, 20);
        edit.add(year);
        final JComboBox yChooser = new JComboBox(yearA);
        yChooser.setBounds(70, 75, 75, 20);
        yChooser.setBackground(Color.white);
        yChooser.setForeground(new Color(0, 160, 210));
        yChooser.setToolTipText("Type in the value for the year");
        yChooser.setSelectedItem(1950);
        selectedYear = "1950";
        edit.add(yChooser);
        yChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer sYear = (Integer)yChooser.getSelectedItem();
                selectedYear = String.valueOf(sYear);
            }
        });
        
        JLabel and = new JLabel("-");
        and.setBounds(150, 75, 75, 20);
        and.setToolTipText("Leave blank if needed");
        edit.add(and);
        
        final JComboBox yChooser2 = new JComboBox(yearA);
        yChooser2.setBounds(160, 75, 75, 20);
        yChooser2.setBackground(Color.white);
        yChooser2.setForeground(new Color(0, 160, 210));
        yChooser2.setToolTipText("Type in the value for the year");
        yChooser2.setSelectedItem(2013);
        selectedYear2 = "2013";
        edit.add(yChooser2);
        yChooser2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer sYear2 = (Integer)yChooser2.getSelectedItem();
                selectedYear2 = String.valueOf(sYear2);
            }
        });
        
        JLabel n = new JLabel("Results: ");
        n.setBounds(10, 105, 70, 20);
        edit.add(n);
        final JTextArea nField = new JTextArea();
        nField.setEditable(false);
        nField.setBackground(new Color(235, 235, 210));
        JScrollPane scroll = new JScrollPane(nField);
        scroll.setBounds(10, 125, 375, 125);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        scroll.setBackground(new Color(235, 235, 210));
        edit.add(scroll);
        
        JButton rButton = new JButton("Reset");
        rButton.setBounds(250, 32, 75, 20);
        rButton.setBackground(Color.white);
        rButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.white, Color.white, new Color(0, 160, 210), new Color(0, 160, 210)), BorderFactory.createEmptyBorder()));
        edit.add(rButton);
        rButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                cField.setText(null);
                tField.setText(null);
                yChooser.setSelectedItem(1950);
                yChooser2.setSelectedItem(2013);
                selectedYear = "1950";
                selectedYear2 = "2013";
                nField.setText(null);
                nField.setBackground(new Color(235, 235, 210));
            }
        });
        
        JButton eButton = new JButton("Search");
        eButton.setBounds(250, 63, 75, 20);
        eButton.setBackground(Color.white);
        eButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.white, Color.white, new Color(0, 160, 210), new Color(0, 160, 210)), BorderFactory.createEmptyBorder()));
        edit.add(eButton);
        eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String callNumner = cField.getText();
                String title = tField.getText();
                nField.setBackground(Color.white);
                nField.append(LibrarySearch.getSearchInput(title, callNumner, selectedYear+"-"+selectedYear2));
            }
        });
        
        
        return edit;
    }
    
    public static JPanel addPanel() {
        JPanel add = new JPanel();
        add.setLayout(null);
        add.setBackground(Color.white);
        add.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 160, 210), 1, true), "Add", TitledBorder.LEFT, TitledBorder.TOP));
        
        String reference[] = {"Book", "Journal"};
        Integer yearA[] = new Integer[375];
        int y = 1640;
        
        for (int i=0; i<375; i++) {
            yearA[i] = y;
            y++;
        }
        
        JLabel callNumber = new JLabel("Call No: ");
        callNumber.setBounds(10, 45, 100, 20);
        add.add(callNumber);
        final JTextField cField = new JTextField();
        cField.setBounds(90, 45, 100, 20);
        cField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        add.add(cField);
        
        JLabel title = new JLabel("Title: ");
        title.setBounds(10, 75, 150, 20);
        add.add(title);
        final JTextField tField = new JTextField();
        tField.setBounds(90, 75, 200, 20);
        tField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        add.add(tField);
        
        JLabel year = new JLabel("Year: ");
        year.setBounds(10, 105, 75, 20);
        add.add(year);
        final JComboBox yChooser = new JComboBox(yearA);
        yChooser.setBounds(90, 105, 75, 20);
        yChooser.setBackground(Color.white);
        yChooser.setForeground(new Color(0, 160, 210));
        yChooser.setToolTipText("Type in the value for the year");
        yChooser.setSelectedItem(2013);
        selectedYear = "2013";
        add.add(yChooser);
        yChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer sYear = (Integer)yChooser.getSelectedItem();
                selectedYear = String.valueOf(sYear);
            }
        });
        
        final JLabel org = new JLabel("Organization: ");
        org.setBounds(10, 135, 200, 20);
        add.add(org);
        final JTextField oField = new JTextField();
        oField.setBounds(90, 135, 200, 20);
        oField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        add.add(oField);
        org.setVisible(false);
        oField.setVisible(false);
        
        final JLabel auth = new JLabel("Author(s): ");
        auth.setBounds(10, 135, 200, 20);
        add.add(auth);
        final JTextField aField = new JTextField();
        aField.setBounds(90, 135, 150, 20);
        aField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        add.add(aField);
        
        final JLabel pub = new JLabel("Publisher: ");
        pub.setBounds(10, 165, 200, 20);
        add.add(pub);
        final JTextField pField = new JTextField();
        pField.setBounds(90, 165, 200, 20);
        pField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        add.add(pField);
        
        JLabel type = new JLabel("Type: ");
        type.setBounds(10, 15, 70, 20);
        add.add(type);
        final JComboBox rChooser = new JComboBox(reference);
        rChooser.setBounds(90, 15, 70, 20);
        rChooser.setBackground(Color.white);
        rChooser.setForeground(new Color(0, 160, 210));
        rChooser.setSelectedItem("Book");
        selectedType = "Book";
        add.add(rChooser);
        rChooser.addActionListener(new ActionListener() {
            @Override
            final public void actionPerformed(ActionEvent event) {
                Object sType = rChooser.getSelectedItem();
                selectedType = String.valueOf(sType);
                if (selectedType.equals("Book")) {
                    org.setVisible(false);
                    oField.setVisible(false);
                    auth.setVisible(true);
                    aField.setVisible(true);
                    pub.setVisible(true);
                    pField.setVisible(true);
                }
                if (selectedType.equals("Journal")) {
                    org.setVisible(true);
                    oField.setVisible(true);
                    auth.setVisible(false);
                    aField.setVisible(false);
                    pub.setVisible(false);
                    pField.setVisible(false);
                }
            }
        });
        
        JLabel n = new JLabel("Messages: ");
        n.setBounds(10, 205, 70, 20);
        add.add(n);
        final JTextArea nField = new JTextArea();
        nField.setBounds(10, 225, 375, 125);
        nField.setBorder(BorderFactory.createLineBorder(new Color(0, 160, 210)));
        nField.setBackground(new Color(235, 235, 210));
        nField.setEditable(false);
        add.add(nField);
        
        JButton eButton = new JButton("Reset");
        eButton.setBounds(312, 58, 50, 20);
        eButton.setBackground(Color.white);
        eButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.white, Color.white, new Color(0, 160, 210), new Color(0, 160, 210)), BorderFactory.createEmptyBorder()));
        add.add(eButton);
        eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                cField.setText("");
                tField.setText("");
                nField.setText("");
                oField.setText("");
                aField.setText("");
                pField.setText("");
                yChooser.setSelectedItem(2013);
                rChooser.setSelectedItem("Book");
                nField.setBackground(new Color(235, 235, 210));
            }
        });
        
        JButton aB = new JButton("Add");
        aB.setBounds(313, 117, 50, 20);
        aB.setBackground(Color.white);
        aB.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, Color.white, Color.white, new Color(0, 160, 210), new Color(0, 160, 210)), BorderFactory.createEmptyBorder()));
        add.add(aB);
        aB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String callNumner = cField.getText();
                String title = tField.getText();
                String organization = oField.getText();
                String pub = pField.getText();
                String author = aField.getText();
                nField.setBackground(Color.white);
                nField.setText(LibrarySearch.add(selectedType, title, callNumner, selectedYear, author, pub, organization));
            }
        });
        
        return add;
    }
    
    public static void main(String[] args) {
        LibrarySearch.main(args);
        
        GUI frame = new GUI();
        frame.setBackground(Color.white);
        frame.setResizable(false);
    	frame.setVisible(true);
    }
}
