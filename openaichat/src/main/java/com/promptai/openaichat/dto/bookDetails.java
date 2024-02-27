package com.promptai.openaichat.dto;

public record bookDetails(
        String category,
        String book,
        String year,
        String review,
        String author,
        String summary) {
}
