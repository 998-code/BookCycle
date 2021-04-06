package com.wcm533.dao;

import com.wcm533.pojo.EndowBookListItems;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName EndowBookListItemsMapper
 * @Descripyion TODO
 * @Author 吴超民
 * @Date 2021/04/06 14:58
 **/
public interface EndowBookListItemsMapper {

    /**
     * 保存书单项
     * @param endowBookListItems
     * @return
     */
    int addBookListItems(EndowBookListItems endowBookListItems);

    /**
     * 根据书单id查询书单项
     * @param bookListId
     * @return
     */
    List<EndowBookListItems> queryBookListItemsByBookListId(@Param("bookListId") String bookListId);
}
