package top.wanda92632.blog.service;

import top.wanda92632.blog.entity.Message;

import java.util.List;

/**
 * @author ZhiFei
 */
public interface MessageService {
    /**
     * 提交留言
     * @param message
     */
    void serMessage(Message message);

    /**
     * 获取所有留言
     * @return
     */
    List<Message> getAllMessage();
}
