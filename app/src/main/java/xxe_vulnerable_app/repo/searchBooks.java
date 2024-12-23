package xxe_vulnerable_app.repo;

import xxe_vulnerable_app.domain.bookDomain;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class searchBooks {

    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem:libraryDB;DB_CLOSE_DELAY=-1";

    public static final String USER = "sa";
    public static final String PASS = "sMFD43p0AxiILBesFM";

    public List<bookDomain> findAllBooks() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT * FROM books";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<bookDomain> allBooks = createBooksFromResultSet(result);
            System.out.println(allBooks);

            return allBooks;
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    public List<bookDomain> findBook(String title) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM books WHERE title LIKE ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, title);
            ResultSet result = pstmt.executeQuery();
            List<bookDomain> book = createBooksFromResultSet(result);
            System.out.println(book);
            return book;
        } catch (SQLException e) {
            throw new RuntimeException("Database error occurred", e);
        }
    }

    private List<bookDomain> createBooksFromResultSet(ResultSet result) throws SQLException{
        List<bookDomain> books = new ArrayList<>();
        while (result.next()) {
            System.out.println(result);
            bookDomain book = new bookDomain();
            book.setTitle(result.getString("title"));
            book.setType(result.getString("type"));
            book.setPrice(result.getString("price"));
            book.setDescription(result.getString("description"));
            books.add(book);
        }
        System.out.println(books);
        return books;
    }

}
