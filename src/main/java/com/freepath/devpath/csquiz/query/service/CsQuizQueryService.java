package com.freepath.devpath.csquiz.query.service;

import com.freepath.devpath.common.dto.Pagination;
import com.freepath.devpath.csquiz.query.dto.*;
import com.freepath.devpath.csquiz.query.mapper.CsQuizQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CsQuizQueryService {
    private final CsQuizQueryMapper csQuizQueryMapper;

    public List<CsQuizPreviewDTO> getWeeklyQuiz() {
        return csQuizQueryMapper.findWeeklyQuiz();
    }
    public CsQuizResponse getQuizById(Long csquizId) {
        return csQuizQueryMapper.findQuizById(csquizId);
    }
    @Transactional(readOnly = true)
    public CsQuizListResponse getAllQuizzes(CsQuizSearchRequest csQuizSearchRequest) {
        List<CsQuizDetailResultDTO> quizzes = csQuizQueryMapper.selectAllQuizzes(csQuizSearchRequest);
        Long totalItems = csQuizQueryMapper.countProducts(csQuizSearchRequest);

        int page = csQuizSearchRequest.getPage();
        int size = csQuizSearchRequest.getSize();

        return CsQuizListResponse.builder()
                .csQuizList(quizzes)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage((int) Math.ceil((double) totalItems / size))
                        .totalItems(totalItems)
                        .build())
                .build();
    }
    public int getCorrectAnswerCount(int userId) {
        return csQuizQueryMapper.countCorrectAnswersByUserId(userId);
    }
    public List<CsQuizDetailResultDTO> getResultsByUserId(int userId) {
        return csQuizQueryMapper.findQuizResultsByUserId(userId);
    }

}

