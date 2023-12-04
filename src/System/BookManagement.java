package System;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookManagement {

    public List<Books> getAllBooks(Connection connection){
        List<Books> booksList =new ArrayList<>();
        String sqlStr = "SELECT * FROM book;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title" );
                String author = resultSet.getString("author");
                int page = resultSet.getInt("pages");
                int qty = resultSet.getInt("quantity");

                booksList.add(new Books(id, title, author, page, qty));
            }

            preparedStatement.close();
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return booksList;
    }

    public void insertBook(String title, String author, int page, int quantity, Connection connection){
        String sqlStr = "insert into book(title, author, pages, quantity) values (?, ?, ?, ?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, page);
            preparedStatement.setInt(4, quantity);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean updateBook(int id, String title, String author, int page, int quantity, Connection connection){
        String sqlStr = "UPDATE book SET title = ?, author = ?, pages = ?, quantity = ? WHERE book_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1, title); // replace newName with the new name value
            preparedStatement.setString(2, author); // replace newDescription with the new description value
            preparedStatement.setInt(3, page); // replace newPrice with the new price value
            preparedStatement.setInt(4, quantity); // replace productId with the specific product id
            preparedStatement.setInt(5, id); // replace productId with the specific product id
            int rowsUpdated = preparedStatement.executeUpdate();
            preparedStatement.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteBook(int id, Connection connection) {

        String sqlStr = "delete from book where book_id = ?";
        String sqlQry = "select title from book where book_id = ?";
        boolean deleteSuccessfully = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1, id);
            PreparedStatement preparedStatementQry = connection.prepareStatement(sqlQry);
            preparedStatementQry.setInt(1, id);
            ResultSet resultSet = preparedStatementQry.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("title");
                if (name != null) {
                    preparedStatement.executeUpdate();
                    deleteSuccessfully = true;
                }
            } else if (!resultSet.next()) {
                deleteSuccessfully = false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return deleteSuccessfully;
    }


    public List<Books> searchProduct(int ID, Connection connection) {
        List<Books> booksList = new ArrayList<>();

        String sqlString = "SELECT * FROM book WHERE book_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, ID + "");
            ResultSet resultSet = preparedStatement.executeQuery();

            int id = 0;
            String title = "";
            String author = "";
            int page = 0;
            int QTY = 0;

            while (resultSet.next()) {
                id = resultSet.getInt("book_id");
                title = resultSet.getString("title");
                author = resultSet.getString("author");
                page = resultSet.getInt("pages");
                QTY = resultSet.getInt("quantity");

                booksList.add(new Books(id, title, author, QTY, page));
            }

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return booksList;
    }

    public void insertIssueBook(String title, String stuName, int studentID, java.sql.Date issueDate, java.sql.Date dueDate, Connection connection){
        String sqlStr = "insert into issuebook (book_title, studentName, studentID, issueDate, dueDate, status) values (?, ?, ?,  ?, ?, ?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, stuName);
            preparedStatement.setInt(3, studentID);
            preparedStatement.setDate(4, issueDate);
            preparedStatement.setDate(5, dueDate);
            preparedStatement.setString(6, "Pending");

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public List<issueBook> getAllIssueBooks(Connection connection){
        List<issueBook> issueBookList =new ArrayList<>();
        String sqlStr = "SELECT * FROM issuebook;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("issue_id");
                String title = resultSet.getString("book_title" );
                String studentName = resultSet.getString("studentName");
                int studentID = resultSet.getInt("studentID");
                java.sql.Date issueDate = resultSet.getDate("issueDate");
                java.sql.Date dueDate = resultSet.getDate("dueDate");
                String status = resultSet.getString("status");
                issueBookList.add(new issueBook(id, title, studentName, studentID, issueDate, dueDate, status));
            }

            preparedStatement.close();
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return issueBookList;
    }

    public void lendBook(int bookId, Connection connection) {
        try {
            String updateSql = "UPDATE book SET quantity = quantity - 1 WHERE book_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setInt(1, bookId);

            // Execute the update statement
            updateStatement.executeUpdate();

            // Close the PreparedStatement
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<issueBook> findIssueDetail(String title, int studentID, Connection connection) {
        List<issueBook> issueBookList = new ArrayList<>();

        String sqlString = "SELECT * FROM issuebook WHERE studentID = ? AND book_title = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setInt(1, studentID);
            preparedStatement.setString(2, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            int id = 0;
            String bookTitle = "";
            String studentName = "";
            java.sql.Date issueDate;
            java.sql.Date duedate;

            while (resultSet.next()) {
                id = resultSet.getInt("issue_id");
                bookTitle = resultSet.getString("book_title");
                studentName = resultSet.getString("studentName");
                issueDate = resultSet.getDate("issueDate");
                duedate = resultSet.getDate("dueDate");

                issueBookList.add(new issueBook(id, bookTitle, studentName, issueDate, duedate));
            }

            preparedStatement.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return issueBookList;
    }

    public boolean deleteIssue(int id, Connection connection) {

        String sqlStr = "delete from issuebook where issue_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1, id);
            // Execute the delete statement
            int rowsDeleted = preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();

            // Return true if at least one row is deleted
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void returnBook(int bookId, Connection connection) {
        try {
            String updateSql = "UPDATE book SET quantity = quantity + 1 WHERE book_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);
            updateStatement.setInt(1, bookId);

            // Execute the update statement
            updateStatement.executeUpdate();

            // Close the PreparedStatement
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<issueBook> getOverdueBooks(Connection connection) {
        List<issueBook> overdueBooks = new ArrayList<>();

        try {
            // Get the current date
            java.util.Date currentDate = new java.util.Date();

            // Query to retrieve overdue books
            String sql = "SELECT * FROM issuebook WHERE dueDate < ? AND status = 'Pending'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(currentDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("issue_id");
                String title = resultSet.getString("book_title");
                String studentName = resultSet.getString("studentName");
                int studentID = resultSet.getInt("studentID");
                java.sql.Date issueDate = resultSet.getDate("issueDate");
                java.sql.Date dueDate = resultSet.getDate("dueDate");
                String status = resultSet.getString("status");

                overdueBooks.add(new issueBook(id, title, studentName, studentID, issueDate, dueDate, status));
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return overdueBooks;
    }
    public List<Books> alertStock(Connection connection) {
        List<Books> booksList = new ArrayList<>();
        int alertThreshold = 5;

        try {
            String sql = "SELECT * FROM book WHERE quantity < ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, alertThreshold);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title" );
                String author = resultSet.getString("author");
                int page = resultSet.getInt("pages");
                int qty = resultSet.getInt("quantity");

                booksList.add(new Books(id, title, author, page, qty));
            }

            preparedStatement.close();
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return booksList;
    }

    public int getTotalBooksQuantity(Connection connection) {
        int totalBooksQuantity = 0;

        try {
            String sqlStr = "SELECT SUM(quantity) AS total_books FROM book";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalBooksQuantity = resultSet.getInt("total_books");
            }

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalBooksQuantity;
    }

    public int getTotalBookIssue(Connection connection){
        int  numberOfBooksLent = 0;
        try {
            // Create the SQL query
            String sqlQuery = "SELECT COUNT(*) AS numberOfBooksLent FROM issuebook";

            // Create a PreparedStatement
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Check if the result set has data
                    if (resultSet.next()) {
                        // Retrieve the value of numberOfPeople
                        numberOfBooksLent = resultSet.getInt("numberOfBooksLent");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numberOfBooksLent;
    }

    public int getTotalOverdueBookQuantity(Connection connection) {
        List<issueBook> overdueBooks = getOverdueBooks(connection);
        return overdueBooks.size();
    }


}
