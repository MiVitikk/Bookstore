package backend24.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;;

@Controller
public class BookstoreController {
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "This is the main page";
    }
}
