package com.osh.jntest.service;

import com.osh.jntest.repository.MasterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final MasterRepository masterRepository;


    public int countUserByName(String userName) {
        return masterRepository.countUsersByName(userName);
    }

}
