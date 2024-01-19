package savogineros.esbeu2w2d3.payloads;

import java.util.UUID;

public record NewBlogpostRequest(

        UUID authorId,
        String category,
        String cover,
        String content,
        double readingTIme,
        String title) {
}
