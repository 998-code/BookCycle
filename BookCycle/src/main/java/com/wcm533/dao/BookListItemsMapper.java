package com.wcm533.dao;

import com.wcm533.pojo.BookListItems;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName BookListItemsMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 14:11
 **/
public interface BookListItemsMapper {

    /**
     * 保存书单项
     * @param bookListItems
     * @return
     */
    int addBookListItems(BookListItems bookListItems);

    /**
     * 根据书单id查询书单项
     * @param bookListId
     * @return
     */
    List<BookListItems> queryBookListItemsByBookListId(@Param("bookListId") String bookListId);
}
