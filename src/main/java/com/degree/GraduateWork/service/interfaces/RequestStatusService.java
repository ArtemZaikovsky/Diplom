package com.degree.GraduateWork.service.interfaces;

import com.degree.GraduateWork.models.RequestStatus;

import java.util.Set;

public interface RequestStatusService {
    void createRequestStatus(Set<RequestStatus> requestStatuses);
    Set<RequestStatus> getAllRequestStatuses();
}
