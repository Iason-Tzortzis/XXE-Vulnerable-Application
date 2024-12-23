package xxe_vulnerable_app;

import xxe_vulnerable_app.domain.bookDomain;
import org.springframework.web.bind.annotation.RequestParam;
import xxe_vulnerable_app.repo.bookRepo;
import xxe_vulnerable_app.repo.searchBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    searchBooks searchBooks;
    @Autowired
    bookRepo bookRepo;

    @GetMapping("/")
    public String index(Model model) {
        return "home";
    }

    @GetMapping("/books")
    public String allBooks(Model model){
        model.addAttribute("books", searchBooks.findAllBooks());
        return "books";
    }

    @GetMapping("/search")
    public String search(){
        return "searchForm";
    }

    // Handle form submission
    @PostMapping("/search")
    public String searchBook(@RequestParam("title") String title, Model model) {

        List<bookDomain> book = searchBooks.findBook(title);

        // Add the search result to the model
        model.addAttribute("book", book);

        // Return the view to display the results
        return "searchResult";  // Return the name of the HTML file (searchResult.html)
    }

}
