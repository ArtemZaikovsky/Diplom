package com.degree.GraduateWork.service.impl;

import com.degree.GraduateWork.Repository.RequestRepository;
import com.degree.GraduateWork.Repository.UserRepository;
import com.degree.GraduateWork.models.Request;
import com.degree.GraduateWork.service.interfaces.RequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public Iterable<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public void updateRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Request request) {
        requestRepository.delete(request);
    }

    @Override
    public void deleteRequestById(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }
}
