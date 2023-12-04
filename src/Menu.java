import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import System.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Menu extends JDialog {
    private JButton dashboradButton;
    private JButton manageBookButton;
    private JButton issueBookButton;
    private JButton returnBookButton;
    private JButton viewIssuedBooksButton;
    private JButton defaulterListButton;
    private JButton logoutButton;
    private JPanel Application;
    private JButton alertStock;
    private JPanel sideBar;
    private JPanel display;
    private JPanel manageBook;
    private JPanel returnBook;
    private JPanel viewRecord;
    private JPanel defaultList;
    private JPanel viewIssuedBook;
    private JPanel dashboardPn;
    private JPanel alertSt;
    private JPanel totalBookCard;
    private JPanel issuedB;
    private JTable totalBookTable;
    private JTable totalBookTab;
    private JTable manageBookTab;
    private JTextField bookIDField;
    private JTextField bookNameField;
    private JTextField bookAuthorField;
    private JTextField bookPageField;
    private JTextField bookQtyField;
    private JButton addBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JPanel issueDate;
    private JPanel dueDate;
    private JTextField issueBookID;
    private JTextField issueStuName;
    private JTextField issueStuID;
    private JTextField detailBookID;
    private JTextField detailBookName;
    private JTextField detailAuthor;
    private JTextField detailPage;
    private JTextField detailQty;
    private JButton issueBtn;
    private JButton findIssueDetailButton;
    private JTable issueBookTab;
    private JTextField deIssueID;
    private JTextField deIssueBookName;
    private JTextField detailStudentName;
    private JTextField detailIssueDate;
    private JTextField detailDueDate;
    private JTextField returnBookIDField;
    private JTextField returnStuIDField;
    private JButton returnBookBtn;
    private JTable defaulterListTable;
    private JTable alertStockTab;
    private JPanel issueBookCard;
    private JPanel DefaulterCard;
    private JLabel totalBook;
    private JLabel totalissueBook;
    private JLabel totalDefaulterList;
    private JLabel totalBook1;
    private JLabel totalissue1;
    private JLabel totalDefaulterList1;
    private JLabel issueBook;
    private JButton selectedBtn;
    private int lastClickedRow = -1;
    private Color originalButtonColor;
    private JDateChooser dateChooser;
    private JDateChooser dueDateCal;
    CardLayout cardLayout;

    public Menu(JFrame parent) {


        super(parent);
        setTitle("Library Management");
        setContentPane(Application);
        setMinimumSize(new Dimension(1300, 800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        dashboradButton.setFocusable(false);
        manageBookButton.setFocusable(false);
        issueBookButton.setFocusable(false);
        returnBookButton.setFocusable(false);
        viewIssuedBooksButton.setFocusable(false);
        defaulterListButton.setFocusable(false);
        alertStock.setFocusable(false);
        logoutButton.setFocusable(false);
        issueBtn.setFocusable(false);
        returnBookButton.setFocusable(false);
        findIssueDetailButton.setFocusable(false);

//      Disable border of the sidebar button
        dashboradButton.setBorderPainted(false);
        manageBookButton.setBorderPainted(false);
        issueBookButton.setBorderPainted(false);
        returnBookButton.setBorderPainted(false);
        viewIssuedBooksButton.setBorderPainted(false);
        defaulterListButton.setBorderPainted(false);
        alertStock.setBorderPainted(false);

//      display only bottom border
        cardLayout = (CardLayout)(display.getLayout());
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white);

//      Apply the bottom border to the button
        bookIDField.setBorder(bottomBorder);
        bookNameField.setBorder(bottomBorder);
        bookAuthorField.setBorder(bottomBorder);
        bookPageField.setBorder(bottomBorder);
        bookQtyField.setBorder(bottomBorder);
        detailBookID.setBorder(bottomBorder);
        detailBookName.setBorder(bottomBorder);
        detailAuthor.setBorder(bottomBorder);
        detailPage.setBorder(bottomBorder);
        detailQty.setBorder(bottomBorder);
        deIssueID.setBorder(bottomBorder);
        deIssueBookName.setBorder(bottomBorder);
        detailStudentName.setBorder(bottomBorder);
        detailIssueDate.setBorder(bottomBorder);
        detailDueDate.setBorder(bottomBorder);

//        Set up Date
        dateChooser = createCustomDateChooser();
        dateChooser.setBounds(320, 90, 200, 30);
        dateChooser.setPreferredSize(new Dimension(200, 30)); // Set your preferred size

        dueDateCal = createCustomDateChooser();
        dueDateCal.setBounds(320, 90, 200, 30);
        dueDateCal.setPreferredSize(new Dimension(200, 30)); // Set your preferred size

        // Store the original button color
        originalButtonColor = dashboradButton.getBackground();


//      Attach listener to all buttons
        dashboradButton.addMouseListener(createButtonMouseListener());
        manageBookButton.addMouseListener(createButtonMouseListener());
        issueBookButton.addMouseListener(createButtonMouseListener());
        returnBookButton.addMouseListener(createButtonMouseListener());
        viewIssuedBooksButton.addMouseListener(createButtonMouseListener());
        defaulterListButton.addMouseListener(createButtonMouseListener());
        alertStock.addMouseListener(createButtonMouseListener());

//      assign value to the 3 cards
        totalBookCard();  // assign total book to total book card
        totalIssueBookCard();  // assign total issue book to issue book card
        totalDefaulterList();  //  assign total defaulter list to defaulter card

//        Show dashboard when open the application
        display.add(dashboardPn, "dashboardPn");
        loadDashBoardTab();
        cardLayout.show(display, "dashboardPn"); // Show the dashboardPn by default



//        Add action listener to all button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LogForm(null);  // back to log in page
            }
        });
        dashboradButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.removeAll();
                display.add(dashboardPn);
                loadDashBoardTab();
                totalBookCard();  // assign total book to total book card
                totalIssueBookCard();  // assign total issue book to issue book card
                totalDefaulterList();  //  assign total defaulter list to defaulter card
                display.revalidate();
                display.repaint();
            }
        });
        manageBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.removeAll();
                display.add(manageBook);
                loadManageBTab();
                totalBookCard();  // assign total book to total book card
                totalIssueBookCard();  // assign total issue book to issue book card
                totalDefaulterList();  //  assign total defaulter list to defaulter card
                display.revalidate();
                display.repaint();
            }
        });
        issueBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.removeAll();
                display.add(issuedB);
                display.revalidate();
                display.repaint();
            }
        });
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.removeAll();
                display.add(returnBook);
                display.revalidate();
                display.repaint();
            }
        });
        viewIssuedBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.removeAll();
                display.add(viewIssuedBook);
                loadIssueBookTab();
                display.revalidate();
                display.repaint();
            }
        });
        defaulterListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.removeAll();
                display.add(defaultList);
                loadDefaulterTab();
                display.revalidate();
                display.repaint();
            }
        });
        alertStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.removeAll();
                display.add(alertSt);
                loadAlertStockTab();
                display.revalidate();
                display.repaint();
            }
        });


//        In the manage book page, we can click on any book to get detail information about that book
        manageBookTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedRow = manageBookTab.rowAtPoint(e.getPoint());

                if (clickedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) manageBookTab.getModel();

                    // Get data from the clicked row
                    int id = (int) model.getValueAt(clickedRow, 0); // Assuming the first column is the ID
                    String name = (String) model.getValueAt(clickedRow, 1);
                    String author = (String) model.getValueAt(clickedRow, 2);
                    int page = (int) model.getValueAt(clickedRow, 3);
                    int quantity = (int) model.getValueAt(clickedRow, 4);

                    // Now you have the information of the clicked row
                    bookIDField.setText(String.valueOf(id));
                    bookNameField.setText(name);
                    bookAuthorField.setText(author);
                    bookPageField.setText(String.valueOf(page));
                    bookQtyField.setText(String.valueOf(quantity));

                    // Remove the background color from the last clicked row
                    if (lastClickedRow != -1) {
                        manageBookTab.repaint(); // Force repaint to remove the color
                    }

                    // Set the background color for the clicked row
                    manageBookTab.setRowSelectionInterval(clickedRow, clickedRow);

                    // Update the last clicked row
                    lastClickedRow = clickedRow;
                }
            }
        });


//        action listener to button
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookId = Integer.parseInt(bookIDField.getText());
                deleteBook(bookId);
            }
        });

        issueBookID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookId = Integer.parseInt(issueBookID.getText());
                getBookDetailByID(bookId);
            }
        });

        issueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (issueBookID.getText().isEmpty() || issueStuID.getText().isEmpty() ||
                        issueStuName.getText().isEmpty() || dateChooser.getDate() == null ||
                        dueDateCal.getDate() == null) {
                    // Display a message if any field is empty
                    JOptionPane.showMessageDialog(null,"Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    issueBook();
                }
            }
        });

        findIssueDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (returnBookIDField.getText().isEmpty() || returnStuIDField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int bookID = Integer.parseInt(returnBookIDField.getText());
                    int studentID = Integer.parseInt(returnStuIDField.getText());
                    getIssueDetailByID(bookID, studentID);
                }
            }
        });

        returnBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookID = Integer.parseInt(returnBookIDField.getText());
                int studentID = Integer.parseInt(returnStuIDField.getText());
                returnBookHandle(bookID, studentID);
            }
        });


        issueDate.add(dateChooser);  // add date to the panel
        dueDate.add(dueDateCal);    // add date to the panel

        setVisible(true);
    }

//   customize when click on the sidebar button
    private MouseAdapter createButtonMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton clickedButton = (JButton) e.getSource();

                // Restore the background color of the last selected button
                if (selectedBtn != null) {
                    selectedBtn.setBackground(originalButtonColor);
                }

                // Set the background color for the clicked button
                clickedButton.setBackground(Color.LIGHT_GRAY);

                // Update the last selected button
                selectedBtn = clickedButton;

            }
        };
    }

//    Load table in DashBoard page
    void loadDashBoardTab() {
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        BookManagement bookManagement = new BookManagement();
        // Get books data
        List<Books> books = bookManagement.getAllBooks(connection);

        // Create table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Page");
        model.addColumn("Quantity");

        // Add books data to model
        for (Books b : books) {
            model.addRow(
                    new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.getPage(), b.getQty()}
            );
        }

        // Set model on table
        totalBookTab.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply the center renderer to all columns
        for (int i = 0; i < totalBookTab.getColumnCount(); i++) {
            totalBookTab.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set font size for the table header
        Font headerFont = new Font("Arial", Font.BOLD, 14); // Adjust the font and size based on your preference
        totalBookTab.getTableHeader().setFont(headerFont);

        // Set the height of the table header
        totalBookTab.getTableHeader().setPreferredSize(new Dimension(totalBookTab.getTableHeader().getWidth(), 35)); // Adjust the height based on your preference

//        Set font height each cell
        totalBookTab.setRowHeight(40); // Adjust the value based on your preference

        totalBookTab.getColumnModel().getColumn(0).setPreferredWidth(150); // 150 pixels width

        // Set font size for cells
        Font font = new Font("Arial", Font.PLAIN, 15); // Adjust the font and size based on your preference
        totalBookTab.setFont(font);


        if (totalBookTab.getColumnCount() > 0) {
            // Set table column widths
            totalBookTab.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
            totalBookTab.getColumnModel().getColumn(1).setPreferredWidth(550); // Title
            totalBookTab.getColumnModel().getColumn(2).setPreferredWidth(265); // Author
            totalBookTab.getColumnModel().getColumn(3).setPreferredWidth(80); // Page
            totalBookTab.getColumnModel().getColumn(4).setPreferredWidth(101); // Quantity


//            // Enable horizontal scrolling
            totalBookTab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//            // Center-align column headers
            ((DefaultTableCellRenderer) totalBookTab.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);


        }
    }

//    load table in management page
    void loadManageBTab() {
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        BookManagement bookManagement = new BookManagement();
        // Get books data
        List<Books> books = bookManagement.getAllBooks(connection);

        // Create table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Page");
        model.addColumn("Quantity");

        // Add books data to model
        for (Books b : books) {
            model.addRow(
                    new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.getPage(), b.getQty()}
            );
        }

        // Set model on table
        manageBookTab.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply the center renderer to all columns
        for (int i = 0; i < manageBookTab.getColumnCount(); i++) {
            manageBookTab.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set font size for the table header
        Font headerFont = new Font("Arial", Font.BOLD, 13); // Adjust the font and size based on your preference
        manageBookTab.getTableHeader().setFont(headerFont);

        // Set the height of the table header
        manageBookTab.getTableHeader().setPreferredSize(new Dimension(manageBookTab.getTableHeader().getWidth(), 30)); // Adjust the height based on your preference

//        Set font height each cell
        manageBookTab.setRowHeight(35); // Adjust the value based on your preference

        manageBookTab.getColumnModel().getColumn(0).setPreferredWidth(130); // 150 pixels width

        // Set font size for cells
        Font font = new Font("Arial", Font.PLAIN, 12); // Adjust the font and size based on your preference
        manageBookTab.setFont(font);


        if (manageBookTab.getColumnCount() > 0) {
            // Set table column widths
            manageBookTab.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
            manageBookTab.getColumnModel().getColumn(1).setPreferredWidth(300); // Title
            manageBookTab.getColumnModel().getColumn(2).setPreferredWidth(150); // Author
            manageBookTab.getColumnModel().getColumn(3).setPreferredWidth(50); // Page
            manageBookTab.getColumnModel().getColumn(4).setPreferredWidth(80); // Quantity


//            // Enable horizontal scrolling
            manageBookTab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//            // Center-align column headers
            ((DefaultTableCellRenderer) manageBookTab.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);


        }
    }

//    load table in issue book page
    void loadIssueBookTab() {
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        BookManagement bookManagement = new BookManagement();
        // Get books data
        List<issueBook> issueBookList = bookManagement.getAllIssueBooks(connection);

        // Create table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Book Title");
        model.addColumn("Student Name");
        model.addColumn("Student ID");
        model.addColumn("Issued Date");
        model.addColumn("Due Date");
        model.addColumn("Status");

        // Add books data to model
        for (issueBook b : issueBookList) {
            model.addRow(
                    new Object[]{b.getId(), b.getTitle(), b.getStudentName(), b.getStudentID(), b.getIssueDate(), b.getDueDate(), b.getStatus()}
            );
        }

        // Set model on table
        issueBookTab.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply the center renderer to all columns
        for (int i = 0; i < issueBookTab.getColumnCount(); i++) {
            issueBookTab.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set font size for the table header
        Font headerFont = new Font("Arial", Font.BOLD, 14); // Adjust the font and size based on your preference
        issueBookTab.getTableHeader().setFont(headerFont);

        // Set the height of the table header
        issueBookTab.getTableHeader().setPreferredSize(new Dimension(issueBookTab.getTableHeader().getWidth(), 35)); // Adjust the height based on your preference

//        Set font height each cell
        issueBookTab.setRowHeight(36); // Adjust the value based on your preference

        issueBookTab.getColumnModel().getColumn(0).setPreferredWidth(150); // 150 pixels width

        // Set font size for cells
        Font font = new Font("Arial", Font.PLAIN, 15); // Adjust the font and size based on your preference
        issueBookTab.setFont(font);


        if (issueBookTab.getColumnCount() > 0) {
            // Set table column widths
            issueBookTab.getColumnModel().getColumn(0).setPreferredWidth(30); // ID
            issueBookTab.getColumnModel().getColumn(1).setPreferredWidth(350); // Title
            issueBookTab.getColumnModel().getColumn(2).setPreferredWidth(160); // student name
            issueBookTab.getColumnModel().getColumn(3).setPreferredWidth(95); // student ID
            issueBookTab.getColumnModel().getColumn(4).setPreferredWidth(135); // issue date
            issueBookTab.getColumnModel().getColumn(5).setPreferredWidth(135); // due date
            issueBookTab.getColumnModel().getColumn(6).setPreferredWidth(80); // status


//            // Enable horizontal scrolling
            issueBookTab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//            // Center-align column headers
            ((DefaultTableCellRenderer) issueBookTab.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);


        }
    }

//    load table in Defaulter list  page
    public void loadDefaulterTab() {
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        BookManagement bookManagement = new BookManagement();
        // Get books data
        List<issueBook> issueBookList = bookManagement.getOverdueBooks(connection);

        // Create table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Book Title");
        model.addColumn("Student Name");
        model.addColumn("Student ID");
        model.addColumn("Issued Date");
        model.addColumn("Due Date");
        model.addColumn("Status");

        // Add books data to model
        for (issueBook b : issueBookList) {
            model.addRow(
                    new Object[]{b.getId(), b.getTitle(), b.getStudentName(), b.getStudentID(), b.getIssueDate(), b.getDueDate(), b.getStatus()}
            );
        }

        // Set model on table
        defaulterListTable.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply the center renderer to all columns
        for (int i = 0; i < defaulterListTable.getColumnCount(); i++) {
            defaulterListTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set font size for the table header
        Font headerFont = new Font("Arial", Font.BOLD, 14); // Adjust the font and size based on your preference
        defaulterListTable.getTableHeader().setFont(headerFont);

        // Set the height of the table header
        defaulterListTable.getTableHeader().setPreferredSize(new Dimension(defaulterListTable.getTableHeader().getWidth(), 35)); // Adjust the height based on your preference

//        Set font height each cell
        defaulterListTable.setRowHeight(36); // Adjust the value based on your preference

        defaulterListTable.getColumnModel().getColumn(0).setPreferredWidth(150); // 150 pixels width

        // Set font size for cells
        Font font = new Font("Arial", Font.PLAIN, 15); // Adjust the font and size based on your preference
        defaulterListTable.setFont(font);


        if (defaulterListTable.getColumnCount() > 0) {
            // Set table column widths
            defaulterListTable.getColumnModel().getColumn(0).setPreferredWidth(40); // ID
            defaulterListTable.getColumnModel().getColumn(1).setPreferredWidth(390); // Title
            defaulterListTable.getColumnModel().getColumn(2).setPreferredWidth(140); // student name
            defaulterListTable.getColumnModel().getColumn(3).setPreferredWidth(60); // student ID
            defaulterListTable.getColumnModel().getColumn(4).setPreferredWidth(135); // issue date
            defaulterListTable.getColumnModel().getColumn(5).setPreferredWidth(135); // due date
            defaulterListTable.getColumnModel().getColumn(6).setPreferredWidth(80); // status


//            // Enable horizontal scrolling
            defaulterListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//            // Center-align column headers
            ((DefaultTableCellRenderer) defaulterListTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);


        }
    }

//    load table in Alert stock page
    void loadAlertStockTab() {
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        BookManagement bookManagement = new BookManagement();
        // Get books data
        List<Books> books = bookManagement.alertStock(connection);

        // Create table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Page");
        model.addColumn("Quantity");

        // Add books data to model
        for (Books b : books) {
            model.addRow(
                    new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.getPage(), b.getQty()}
            );
        }

        // Set model on table
        alertStockTab.setModel(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply the center renderer to all columns
        for (int i = 0; i < alertStockTab.getColumnCount(); i++) {
            alertStockTab.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set font size for the table header
        Font headerFont = new Font("Arial", Font.BOLD, 13); // Adjust the font and size based on your preference
        alertStockTab.getTableHeader().setFont(headerFont);

        // Set the height of the table header
        alertStockTab.getTableHeader().setPreferredSize(new Dimension(alertStockTab.getTableHeader().getWidth(), 35)); // Adjust the height based on your preference

//        Set font height each cell
        alertStockTab.setRowHeight(30); // Adjust the value based on your preference

        alertStockTab.getColumnModel().getColumn(0).setPreferredWidth(150); // 150 pixels width

        // Set font size for cells
        Font font = new Font("Arial", Font.PLAIN, 14); // Adjust the font and size based on your preference
        alertStockTab.setFont(font);


        if (alertStockTab.getColumnCount() > 0) {
            // Set table column widths
            alertStockTab.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
            alertStockTab.getColumnModel().getColumn(1).setPreferredWidth(400); // Title
            alertStockTab.getColumnModel().getColumn(2).setPreferredWidth(200); // Author
            alertStockTab.getColumnModel().getColumn(3).setPreferredWidth(80); // Page
            alertStockTab.getColumnModel().getColumn(4).setPreferredWidth(95); // Quantity


//            // Enable horizontal scrolling
            alertStockTab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

//            // Center-align column headers
            ((DefaultTableCellRenderer) alertStockTab.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);


        }
    }

    void addBook(){
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        String bookTitle = bookNameField.getText();
        String author = bookAuthorField.getText();
        int page = Integer.parseInt(bookPageField.getText());
        int quantity = Integer.parseInt(bookQtyField.getText());

        BookManagement  bookManagement = new BookManagement();
        bookManagement.insertBook(bookTitle, author, page, quantity, connection);
        JOptionPane.showMessageDialog(null, "Book Added");
    }
    public void updateBook(){
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        int bookId = Integer.parseInt(bookIDField.getText());
        String bookTitle = bookNameField.getText();
        String author = bookAuthorField.getText();
        int page = Integer.parseInt(bookPageField.getText());
        int quantity = Integer.parseInt(bookQtyField.getText());

        BookManagement  bookManagement = new BookManagement();
        boolean update = bookManagement.updateBook(bookId, bookTitle, author, page, quantity, connection);
        if(update) {
            JOptionPane.showMessageDialog(null, "Book updated");
        }else {
            JOptionPane.showMessageDialog(null, "Book update fail");
        }
    }

    public void deleteBook(int bookId){
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();

        BookManagement bookManagement = new BookManagement();
        boolean delete = bookManagement.deleteBook(bookId, connection);
        if(delete) {
            JOptionPane.showMessageDialog(null, "Book deleted");
        } else {
            JOptionPane.showMessageDialog(null, "Book delete fail");
        }

    }

    //    Fill book ID in book issue and get the detail of the book
    public void getBookDetailByID(int bookId){
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();

        BookManagement  bookManagement = new BookManagement();

        List<Books> bookList = bookManagement.searchProduct(bookId, connection);

        if (bookList.size() == 0){
            JOptionPane.showMessageDialog(null, "No Book Found");
        } else {
            for(Books book:bookList){
                detailBookID.setText(String.valueOf(book.getId()));
                detailBookName.setText(book.getTitle());
                detailAuthor.setText(book.getAuthor());
                detailPage.setText(String.valueOf(book.getPage()));
                detailQty.setText(String.valueOf(book.getQty()));
            }
        }
    }


//    Fill book id and student id in return book to find issue information
    public void getIssueDetailByID(int bookId, int studentID){
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();

        BookManagement  bookManagement = new BookManagement();

        List<Books> bookList = bookManagement.searchProduct(bookId, connection);

        String bookTitle = "";
        for (Books book : bookList) {
            bookTitle = book.getTitle();
        }

        List<issueBook> issueBookList = bookManagement.findIssueDetail(bookTitle, studentID, connection);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        if (issueBookList.size() == 0){
            JOptionPane.showMessageDialog(null, "No issue Found");
        } else {
            for(issueBook b:issueBookList){
                deIssueID.setText(String.valueOf(b.getId()));
                deIssueBookName.setText(b.getTitle());
                detailStudentName.setText(b.getStudentName());
                String IssueDate = dateFormat.format(b.getIssueDate());
                String dueDate = dateFormat.format(b.getDueDate());
                detailIssueDate.setText(IssueDate);
                detailDueDate.setText(dueDate);
            }
        }
    }


//    the process of issue book
    public void issueBook() {
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        BookManagement bookManagement = new BookManagement();


        int bookID = Integer.parseInt(issueBookID.getText());

        List<Books> bookList = bookManagement.searchProduct(bookID, connection);
        String bookTitle = "";
        for (Books book : bookList) {
            bookTitle = book.getTitle();
        }

        String studentName = issueStuName.getText();
        int studentID = Integer.parseInt(issueStuID.getText());

        Date selectedDate = dateChooser.getDate();
        java.sql.Date issueDate = new java.sql.Date(selectedDate.getTime());

        Date dueDate = dueDateCal.getDate();
        java.sql.Date issueDueDate = new java.sql.Date(dueDate.getTime());


//      minus book quantity by one
        bookManagement.lendBook(bookID, connection);

//      add book to issueBook table
        bookManagement.insertIssueBook(bookTitle, studentName, studentID, issueDate, issueDueDate, connection);
        JOptionPane.showMessageDialog(null, "Book ID: " + bookID + " issued to student " + studentName);
    }


//    the process of return book
    public void returnBookHandle(int bookID, int studentID){
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();

        BookManagement bookManagement = new BookManagement();


        List<Books> bookList = bookManagement.searchProduct(bookID, connection);

        String bookTitle = "";
        int issueID = 0;
        for (Books book : bookList) {
            bookTitle = book.getTitle();

            List<issueBook> issueBookList = bookManagement.findIssueDetail(bookTitle, studentID, connection);

            if (issueBookList.size() == 0) {
                JOptionPane.showMessageDialog(null, "No issue Found");
            } else {
                for (issueBook b : issueBookList) {
                    issueID = b.getId();
                }
            }

            boolean delete = bookManagement.deleteIssue(issueID, connection);
            if (delete) {
                bookManagement.returnBook(bookID, connection);
                JOptionPane.showMessageDialog(null, "Book Returned ");
            } else {
                JOptionPane.showMessageDialog(null, "Return book fail");
            }
        }
    }


//    display number in the three cards
    public void totalBookCard(){
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        BookManagement bookManagement = new BookManagement();

        int totalBooksQuantity = bookManagement.getTotalBooksQuantity(connection);
        totalBook.setText(String.valueOf(totalBooksQuantity));
        totalBook1.setText(String.valueOf(totalBooksQuantity));
    }
    public void totalIssueBookCard(){
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        BookManagement bookManagement = new BookManagement();

        int totalIssueBookCard = bookManagement.getTotalBookIssue(connection);
        totalissueBook.setText(String.valueOf(totalIssueBookCard));
        totalissue1.setText(String.valueOf(totalIssueBookCard));
    }
    public void totalDefaulterList(){
        Repositary repositary = new Repositary();
        Connection connection = repositary.setConnection();
        BookManagement bookManagement = new BookManagement();

        int totalDefaulter = bookManagement.getTotalOverdueBookQuantity(connection);
        totalDefaulterList.setText(String.valueOf(totalDefaulter));
        totalDefaulterList1.setText(String.valueOf(totalDefaulter));
    }


//    Customize date chooser box
    private static JDateChooser createCustomDateChooser() {
        JDateChooser dateChooser = new JDateChooser();

        String blackBlue = "#008994";
        Color calanderColor = Color.decode(blackBlue);

        // Customize the background color
        dateChooser.setBackground(Color.white);

        // Customize the text color directly on JTextFieldDateEditor
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setBackground(calanderColor);
        editor.setForeground(Color.white);

        // Customize the date format (optional)
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        editor.setDateFormatString(dateFormat.toPattern());

        return dateChooser;
    }

}
