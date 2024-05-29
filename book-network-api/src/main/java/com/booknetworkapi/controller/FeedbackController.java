package com.booknetworkapi.controller;

import com.booknetworkapi.common.PageResponse;
import com.booknetworkapi.dto.FeedBackResponse;
import com.booknetworkapi.dto.FeedbackRequest;
import com.booknetworkapi.entity.user.SecurityUser;
import com.booknetworkapi.service.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Long> saveFeedback(
            @RequestBody FeedbackRequest request,
            @AuthenticationPrincipal SecurityUser user){

        return ResponseEntity.ok(feedbackService.save(request, user));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<PageResponse<FeedBackResponse>> findAllFeedbackByBook(
            @PathVariable Long bookId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int pageSize,
            @AuthenticationPrincipal SecurityUser user
    ){
        return ResponseEntity.ok(feedbackService.findAllFeedbackByBook(bookId, page, pageSize, user));
    }
}
