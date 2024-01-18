package savogineros.esbeu2w2d3.payloads;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NewBlogpostPayload {

    private UUID authorId;

    private String category;

    private String content;

    private double readingTime;

    private String title;
}
