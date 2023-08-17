package cn.ning.money.book.business;

import com.alibaba.fastjson.JSON;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapMergeTest {


    @Builder
    public static class MessageDTO {
        private String type;
        private String content;
        private Date createDate;

        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
        public Date getCreateDate() {
            return createDate;
        }
        public void setCreateDate(Date createDate) {
            this.createDate = createDate;
        }
    }

    public static void main(String[] args) {
        List<MessageDTO> messages = new ArrayList<>();
        // Add your MessageDTO objects to the list

        Date current = new Date();
        MessageDTO obj = MessageDTO.builder().type("type1").content("content1").createDate(current).build();
        messages.add(obj);
        MessageDTO dto = MessageDTO.builder().type("type1").content("content2").createDate(new Date(current.getTime() + 2343)).build();
        messages.add(dto);

        Map<String, MessageDTO> latestMessages = messages.stream()
            .collect(Collectors.toMap(
                MessageDTO::getType,
                message -> message,
                (existing, replacement) -> {
                    if (existing.getCreateDate().before(replacement.getCreateDate())) {
                        return replacement;
                    }
                    return existing;
                }
            ));
        
        List<MessageDTO> filteredMessages = new ArrayList<>(latestMessages.values());
        System.out.println(JSON.toJSON(filteredMessages));
    }
}
