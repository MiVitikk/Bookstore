package backend24.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import backend24.bookstore.domain.Book;
import backend24.bookstore.domain.BookRepository;

@Controller
public class BookstoreController {
    

    private static final Logger log = LoggerFactory.getLogger(BookstoreController.class);

    private final BookRepository bookRepository;
    public BookstoreController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "This is the main page";
    }

    @GetMapping("/booklist")
    public String showBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "bookList";
    }

    @GetMapping("/newbook")
    public String addBook(Model model){
        log.info("Lisätään uusi kirja");
        model.addAttribute("book", new Book());
        return "newBook";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book){
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        log.info("delete book " + id);
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }

    @GetMapping("editBook/{id}")
    public String editBook(@PathVariable("id") Long id, Model model){
        model.addAttribute("editBook", bookRepository.findById(id));
        return "editBook";
    }
}
