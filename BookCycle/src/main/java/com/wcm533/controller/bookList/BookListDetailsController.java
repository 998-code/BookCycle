package com.wcm533.controller.bookList;

import com.wcm533.pojo.BookListItems;
import com.wcm533.pojo.ItemsDetails;
import com.wcm533.service.impl.BookListServiceImpl;
import com.wcm533.service.impl.EndowBookListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName BookListDetailsController
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/24 21:19
 **/

@RestController
@RequestMapping("/details")
public class BookListDetailsController {

    @Autowired
    @Qualifier("BookListServiceImpl")
    private BookListServiceImpl bookListService;

    @Autowired
    @Qualifier("EndowBookListServiceImpl")
    private EndowBookListServiceImpl endowBookListService;

    @PostMapping("/bookList")
    public List<ItemsDetails> bookDetails(String bookListId){

        return bookListService.queryBookListItems(bookListId);
    }

    @PostMapping("/endowBookList")
    public List<ItemsDetails> endowBookDetails(String bookListId){

        return endowBookListService.queryBookListItems(bookListId);
    }
}
