package com.degree.GraduateWork.service.impl;

import com.degree.GraduateWork.Repository.RequestStatusRepository;
import com.degree.GraduateWork.models.RequestStatus;
import com.degree.GraduateWork.service.interfaces.RequestStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RequestStatusServiceImpl implements RequestStatusService {
    private final RequestStatusRepository requestStatusRepository;

    public RequestStatusServiceImpl(RequestStatusRepository requestStatusRepository) {
        this.requestStatusRepository = requestStatusRepository;
    }

    @Override
    public void createRequestStatus(Set<RequestStatus> requestStatuses) {
        requestStatusRepository.saveAll(requestStatuses);
    }

    @Override
    public Set<RequestStatus> getAllRequestStatuses() {
        Iterable<RequestStatus> iterable = requestStatusRepository.findAll();
        Set<RequestStatus> set = new HashSet<>();
        iterable.forEach(requestStatus -> set.add(requestStatus));
        return set;
    }
}
