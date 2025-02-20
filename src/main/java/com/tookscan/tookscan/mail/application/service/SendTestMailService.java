package com.tookscan.tookscan.mail.application.service;

import com.tookscan.tookscan.mail.application.dto.request.SendTestMailRequestDto;
import com.tookscan.tookscan.mail.application.usecase.SendTestMailUseCase;
import com.tookscan.tookscan.mail.event.EmailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendTestMailService implements SendTestMailUseCase {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void execute(SendTestMailRequestDto requestDto) {
        applicationEventPublisher.publishEvent(EmailEvent.of(requestDto.email()));
    }
}
