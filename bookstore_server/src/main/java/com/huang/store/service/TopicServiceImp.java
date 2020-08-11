package com.huang.store.service;

import com.huang.store.entity.book.Book;
import com.huang.store.entity.book.BookTopic;
import com.huang.store.entity.book.SubBookTopic;
import com.huang.store.entity.dto.TopicBook;
import com.huang.store.mapper.TopicMapper;
import com.huang.store.service.imp.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("firstTopic")
public class TopicServiceImp implements TopicService {

    @Autowired
    TopicMapper topicMapper;

    @Override
    public int addBookTopic(BookTopic bookTopic) {
        int result = topicMapper.addBookTopic(bookTopic);
        return result;
    }

    @Override
    public int delBookTopic(int id) {
        int result = topicMapper.delBookTopic(id);
        return result;
    }

    @Override
    public int modifyBookTopic(BookTopic bookTopic) {
        int result =  topicMapper.modifyBookTopic(bookTopic);
        return result;
    }

    @Override
    public BookTopic getBookTopic(int id) {
        return topicMapper.getBookTopic(id);
    }

    @Override
    public List<BookTopic> getBookTopicList(int page, int pageSize) {
        int start = (page-1)*pageSize;
        return topicMapper.getBookTopicList(start,pageSize);
    }

    @Override
    public int getTopicCount() {
        return topicMapper.getTopicCount();
    }

    @Override
    public int addSubBookTopic(SubBookTopic subBookTopic) {
        return topicMapper.addSubBookTopic(subBookTopic);
    }

    @Override
    public int batchAddSubTopic(List<SubBookTopic> item) {
        return topicMapper.batchAddSubTopic(item);
    }

    @Override
    public int delSubBookTopic(int topicId, int bookId) {
        return topicMapper.delSubBookTopic(topicId, bookId);
    }

    @Override
    public int batchDelSubTopic(List<SubBookTopic> item) {
        return topicMapper.batchDelSubTopic(item);
    }

    @Override
    public int modifySubBookTopic(SubBookTopic subBookTopic) {
        return topicMapper.modifySubBookTopic(subBookTopic);
    }

    @Override
    public SubBookTopic getSubBookTopic(int topicId, int bookId) {
        return topicMapper.getSubBookTopic(topicId, bookId);
    }

    @Override
    public List<Book> getSubBookTopicList(int topicId, int page, int pageSize) {
        int start = (page-1)*pageSize;
        return topicMapper.getSubBookTopicList(topicId, start, pageSize);
    }

    @Override
    public int getSubTopicCount(int topicId) {
        return topicMapper.getSubTopicCount(topicId);
    }


    @Override
    public List<Book> getNoAddBookPage(int topicId, int page, int pageSize) {
        page = (page-1)*pageSize;
        return topicMapper.getNoAddBookPage(topicId, page, pageSize);
    }

    @Override
    public List<TopicBook> getTopicBookList(int topicId) {
        return topicMapper.getTopicBookList(topicId);
    }

    @Override
    public int getNoAddCount(int topicId) {
        return topicMapper.getNoAddCount(topicId);
    }

    @Override
    public String getReason(int topicId, int bookId) {
        return topicMapper.getReason(topicId, bookId);
    }
}
