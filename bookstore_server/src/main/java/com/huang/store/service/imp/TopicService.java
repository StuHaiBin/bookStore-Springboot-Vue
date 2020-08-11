package com.huang.store.service.imp;

import com.huang.store.entity.book.Book;
import com.huang.store.entity.book.BookTopic;
import com.huang.store.entity.book.SubBookTopic;
import com.huang.store.entity.dto.TopicBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicService {
    //对于总书单专题的操作
    int addBookTopic(BookTopic bookTopic);
    int delBookTopic(int id);//这个需要多表删除
    int modifyBookTopic(BookTopic bookTopic);//这个是根据编号修改的，这样才可以连同书单名也是可以修改的
    BookTopic getBookTopic(int id);
    List<BookTopic> getBookTopicList(int page, int pageSize);
    int getTopicCount();

    //对于书单内含有的哪些图书的操作
    int addSubBookTopic(SubBookTopic subBookTopic);
    int batchAddSubTopic(List<SubBookTopic> item);//批量添加
    int delSubBookTopic(int topicId,int bookId);
    int batchDelSubTopic(List<SubBookTopic> item);
    int modifySubBookTopic(SubBookTopic subBookTopic);
    SubBookTopic getSubBookTopic(int topicId,int bookId);
    List<Book> getSubBookTopicList(int topicId, int page, int pageSize);
    int getSubTopicCount(int topicId);
    String getReason(int topicId,int bookId);//得到某本书的推荐理由


    //按页得到所有未添加到某书单的所有图书
    List<Book> getNoAddBookPage(int topicId, int page, int pageSize);
    List<TopicBook> getTopicBookList(int topicId);//得到所有添加到指定书单的所有图书
    int getNoAddCount(int topicId);//得到总共有多少书未添加到指定的书单中

}
