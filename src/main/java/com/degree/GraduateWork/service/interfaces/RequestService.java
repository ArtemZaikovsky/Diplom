package com.degree.GraduateWork.service.interfaces;

import com.degree.GraduateWork.models.Request;

import java.util.Optional;

public interface RequestService {
    void createRequest(Request request);
    Iterable<Request> getAllRequests();
    void updateRequest(Request request);
    void deleteRequest(Request request);
    void deleteRequestById(Long id);
    Optional<Request> getRequestById(Long id);
}
