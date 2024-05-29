package com.booknetworkapi.service;

import com.booknetworkapi.common.PageResponse;
import com.booknetworkapi.dto.BookResponse;
import com.booknetworkapi.dto.FeedBackMapper;
import com.booknetworkapi.dto.FeedBackResponse;
import com.booknetworkapi.dto.FeedbackRequest;
import com.booknetworkapi.entity.Book;
import com.booknetworkapi.entity.FeedBack;
import com.booknetworkapi.entity.user.SecurityUser;
import com.booknetworkapi.exception.OperationNotPermittedException;
import com.booknetworkapi.repository.BookRepository;
import com.booknetworkapi.repository.FeedBackRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final BookRepository bookRepository;
    private final FeedBackMapper feedBackMapper;
    private final FeedBackRepository feedBackRepository;

    public Long save(FeedbackRequest request, SecurityUser user) {

        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("No book found with id " + request.bookId()));


        if(book.getArchived() == true || !book.getSharable()){
            throw new OperationNotPermittedException("Cannot give feedback to archived or non-sharable book");
        }

        if (Objects.equals(user.getId(), book.getOwner().getId())){
            throw new OperationNotPermittedException("User cannot give feedback to his own book");
        }

        FeedBack feedBack = feedBackMapper.tofeedBack(request);

        return feedBackRepository.save(feedBack).getId();
    }

    public PageResponse<FeedBackResponse> findAllFeedbackByBook(Long bookId, int page, int pageSize, SecurityUser user) {

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<FeedBack> feedBacks = feedBackRepository.findAllByBookId(bookId, pageable);

        List<FeedBackResponse> feedBackResponses = feedBacks.stream()
                .map(f -> feedBackMapper.tofeedBackResponse(f, user.getId()))
                .toList();

        return new PageResponse<>(
                feedBackResponses,
                feedBacks.getNumber(),
                feedBacks.getSize(),
                feedBacks.getTotalElements(),
                feedBacks.getTotalPages(),
                feedBacks.isFirst(),
                feedBacks.isLast()
        );

    }
}
