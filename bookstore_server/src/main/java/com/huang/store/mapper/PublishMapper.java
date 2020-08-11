package com.huang.store.mapper;

import com.huang.store.entity.book.Publish;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishMapper {
    int addPublish(Publish publish);
    int deletePublish(int id);
    int modifyPublish(Publish publish);
    int modifyIsShow(int id);
    int getPublishCount();
    List<Publish> getPublishByPage(int page,int pageSize);
    List<String> getPublishNames();
    Publish getPublishById(int id);
    Publish getPublishByName(String name);
}
