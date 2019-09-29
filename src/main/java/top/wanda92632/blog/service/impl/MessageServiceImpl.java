package top.wanda92632.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wanda92632.blog.dao.MessageDao;
import top.wanda92632.blog.entity.Message;
import top.wanda92632.blog.service.MessageService;

import java.util.List;

/**
 * @author ZhiFei
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    /**
     * 提交留言
     *
     * @param message
     */
    @Override
    public void serMessage(Message message) {
        messageDao.serMessage(message);
    }

    /**
     * 获取所有留言
     *
     * @return
     */
    @Override
    public List<Message> getAllMessage() {
        return messageDao.getAllMessage();
    }
}
