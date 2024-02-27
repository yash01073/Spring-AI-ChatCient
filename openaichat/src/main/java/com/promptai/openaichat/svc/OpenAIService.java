package com.promptai.openaichat.svc;

import com.promptai.openaichat.dto.bookDetails;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.metadata.OpenAiChatResponseMetadata;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIService {

    @Autowired
    OpenAiChatClient aiClient;

    public String getJoke(String topic){
        PromptTemplate promptTemplate = new PromptTemplate("""
                Please act as a funny person and  create a joke on the given {topic}?
                Please be mindful and sensitive about the content though.
               """);
        promptTemplate.add("topic", topic);
        return aiClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }

    public bookDetails getBooksInJson(String category, String year){
        BeanOutputParser<bookDetails> bookBeanOutputParser = new BeanOutputParser<>(bookDetails.class);
        PromptTemplate promptTemplate = new PromptTemplate("""
                Please provide me best book for the given {category} and the {year}.
                Please do provide a summary of the book as well, the information should be
                limited and not much in depth. The response should be containing this information :
                category, book, year, review, author, summary
                {format}
                """);
        promptTemplate.add("category", category);
        promptTemplate.add("year", year);
        promptTemplate.add("format", bookBeanOutputParser.getFormat());
        promptTemplate.setOutputParser(bookBeanOutputParser);
        return bookBeanOutputParser.parse(aiClient.call(promptTemplate.create()).getResult().getOutput().getContent());
    }
}
